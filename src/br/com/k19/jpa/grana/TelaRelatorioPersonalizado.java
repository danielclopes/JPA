package br.com.k19.jpa.grana;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

public class TelaRelatorioPersonalizado implements Tela {

	private Tela anterior;

	public TelaRelatorioPersonalizado(Tela anterior) {
		this.anterior = anterior;
	}

	@Override
	public String getNome() {
		return "Relatório Personalizado";
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
