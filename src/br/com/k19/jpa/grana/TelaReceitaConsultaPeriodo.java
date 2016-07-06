package br.com.k19.jpa.grana;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import javax.persistence.EntityManager;

public class TelaReceitaConsultaPeriodo implements Tela {

	private Tela anterior;

	public TelaReceitaConsultaPeriodo(Tela anterior) {
		this.anterior = anterior;
	}

	@Override
	public Tela mostra() {
		System.out.println(">>> " + this.getNome() + " <<<");
		System.out.println();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Calendar dataInicial = null;

		while (dataInicial == null) {
			System.out.print("Digite a data inicial (ex: 05/08/2016): ");

			try {
				Date d = sdf.parse(Grana.getEntrada().next());
				dataInicial = Calendar.getInstance();
				dataInicial.setTime(d);
			} catch (ParseException e) {
				System.out.println("Data incorreta");
			}
		}

		Calendar dataFinal = null;

		while (dataFinal == null) {
			System.out.print("Digite a data final (ex: 05/12/2016): ");

			try {
				Date d = sdf.parse(Grana.getEntrada().next());
				dataFinal = Calendar.getInstance();
				dataFinal.setTime(d);
			} catch (ParseException e) {
				System.out.println("Data incorreta");
			}
		}
		
		
		EntityManager manager = Grana.getEntityManager();
		
		ReceitaRepositorio receitaRepositorio = 
				new ReceitaRepositorio(manager);
		
		List<Receita> receitas = 
			receitaRepositorio.buscaPorPeriodo(dataInicial, dataFinal);
		
		int opcao = -1;
		
		while(opcao < 0 || opcao > receitas.size()) {
			for (int i = 0; i < receitas.size(); i++) {
				System.out.println((i + 1) + ". " + receitas.get(i));
			}

			System.out.println();
			System.out.println("Digite o número da despesa que deseja remover.");
			System.out.println("Digite 0 para voltar.");
			
			try {
				opcao = Grana.getEntrada().nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Opção incorreta");
				continue;
			}

			if (opcao < 0 || opcao > 1000) {
				System.out.println("Opção incorreto");
				continue;
			}
			
			if(opcao != 0) {
				manager.getTransaction().begin();
				
				receitaRepositorio.remove(receitas.get(opcao - 1));
				
				manager.getTransaction().commit();
				
				receitas.remove(opcao - 1);
				
				opcao = -1;
				
				System.out.println();
				System.out.println("Receita Removida");
				System.out.println();
			}
		}
		
		System.out.println();
		
		return this.anterior;
	}

	@Override
	public String getNome() {
		return "Receitas por Período";
	}
}