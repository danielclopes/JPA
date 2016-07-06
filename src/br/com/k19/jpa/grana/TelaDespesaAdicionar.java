package br.com.k19.jpa.grana;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManager;

public class TelaDespesaAdicionar implements Tela {

	private Tela anterior;

	public TelaDespesaAdicionar(Tela anterior) {
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
		Map<Integer, String> tipos = 
				new LinkedHashMap<Integer, String>();
		tipos.put(1, "Aluguel");
		tipos.put(2, "Alimentação");
		tipos.put(3, "Transporte");
		tipos.put(4, "Lazer");
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
		
		Despesa despesa = new Despesa();
		despesa.setNome(nome);
		despesa.setValor(valor);
		despesa.setTipo(tipos.get(tipo));
		despesa.setData(data);
		
		DespesaRepositorio despesaRepositorio = 
				new DespesaRepositorio(manager);
		despesaRepositorio.adiciona(despesa);

		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Despesa adicionada");

		return this.anterior;
	}

	@Override
	public String getNome() {
		return "Adicionar Despesa";
	}
}
