package br.com.k19.jpa.grana;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManager;

public class TelaReceitaAdicionar implements Tela {

	private Tela anterior;

	public TelaReceitaAdicionar(Tela anterior) {
		this.anterior = anterior;
	}

	@Override
	public Tela mostra() {
		System.out.println(">>> " + this.getNome() + " <<<");
		System.out.println();

		System.out.print("Digite o nome: ");
		String nome = Grana.getEntrada().next();

		double valor = -1;
		while (valor < 0) {
			System.out.print("Digite o valor: ");

			try {
				valor = Grana.getEntrada().nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Valor incorreto");
				continue;
			}

			if (valor < 0) {
				System.out.println("Valor incorreto");
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Calendar data = null;
		while (data == null) {
			System.out.print("Digite a data (ex: 05/08/2016): ");

			try {
				Date d = sdf.parse(Grana.getEntrada().next());
				data = Calendar.getInstance();
				data.setTime(d);
			} catch (ParseException e) {
				System.out.println("Data incorreta");
			}
		}
		
		System.out.println();
		Map<Integer, String> tipos = new LinkedHashMap<Integer, String>();
		tipos.put(1, "Salário");
		tipos.put(2, "Vale Alimentação");
		tipos.put(3, "Vale Refeição");
		tipos.put(4, "Aluguel");
		tipos.put(5, "Outros");

		for (Integer key : tipos.keySet()) {
			System.out.println(key + ". " + tipos.get(key));
		}

		int tipo = -1;
		while (tipo < 1 || tipo > 5) {
			System.out.print("Escolha o tipo (número de 1 a 5): ");

			try {
				tipo = Grana.getEntrada().nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Tipo incorreto");
				continue;
			}

			if (tipo < 1 || tipo > 5) {
				System.out.println("Tipo incorreto");
			}
		}

		EntityManager manager = Grana.getEntityManager();
		manager.getTransaction().begin();
		
		Receita receita = new Receita();
		receita.setNome(nome);
		receita.setValor(valor);
		receita.setTipo(tipos.get(tipo));
		receita.setData(data);
		
		ReceitaRepositorio receitaRepositorio = new ReceitaRepositorio(manager);
		receitaRepositorio.adiciona(receita);

		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Receita adicionada");
		System.out.println();

		return this.anterior;
	}

	@Override
	public String getNome() {
		return "Adicionar Receita";
	}
}