package br.com.k19.jpa.grana;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

import javax.persistence.EntityManager;

public class TelaRelatorioMensal implements Tela {

	private Tela anterior;

	public TelaRelatorioMensal(Tela anterior) {
		this.anterior = anterior;
	}

	@Override
	public String getNome() {
		return "Relatório Mensal";
	}

	@Override
	public Tela mostra() {
		System.out.println(">>> " + this.getNome() + " <<<");
		System.out.println();

		int mes = -1;

		while (mes < 1 || mes > 12) {
			System.out.print("Digite o mês (número de 1 a 12): ");

			try {
				mes = Grana.getEntrada().nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Mês incorreto");
				continue;
			}

			if (mes < 1 || mes > 12) {
				System.out.println("Mês incorreto");
			}
		}

		int ano = -1;

		while (ano < 1900 || ano > 3000) {
			System.out.print("Digite o ano (número de 1900 a 3000): ");

			try {
				ano = Grana.getEntrada().nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Ano incorreto");
				continue;
			}

			if (ano < 1900 || ano > 3000) {
				System.out.println("Ano incorreto");
			}
		}
		
		Calendar dataInicial = new GregorianCalendar(ano, mes - 1, 1);
		Calendar dataFinal = new GregorianCalendar(ano, mes - 1, 1);
		dataFinal.add(Calendar.MONTH, 1);
		dataFinal.add(Calendar.DAY_OF_MONTH, -1);
		
		EntityManager manager = Grana.getEntityManager();
		
		ReceitaRepositorio receitaRepositorio = 
				new ReceitaRepositorio(manager);
		
		DespesaRepositorio despesaRepositorio =
				new DespesaRepositorio(manager);
		
		Double receitas = 
			receitaRepositorio.somaReceitas(dataInicial, dataFinal);
		Double despesas = 
			despesaRepositorio.somaDespesas(dataInicial, dataFinal);
		
		manager.close();
		
		System.out.println("Receitas: " + receitas);
		System.out.println("Despesas: " + despesas);
		System.out.println("Saldo do Período: " + (receitas - despesas));

		System.out.println();

		return this.anterior;
	}
}
