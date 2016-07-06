package br.com.k19.jpa.grana;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

public class TelaRelatorioConsolidado implements Tela {

	private Tela anterior;

	public TelaRelatorioConsolidado(Tela anterior) {
		this.anterior = anterior;
	}

	@Override
	public Tela mostra() {
		System.out.println(">>> " + this.getNome() + " <<<");
		System.out.println();

		Calendar data = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		while (data == null) {
			System.out.print("Digite uma data (ex: 05/08/2017): ");

			try {
				Date d = sdf.parse(Grana.getEntrada().next());
				data = Calendar.getInstance();
				data.setTime(d);
			} catch (ParseException e) {
				System.out.println("Data incorreta");
			}
		}

		EntityManager manager = Grana.getEntityManager();
		
		ReceitaRepositorio receitaRepositorio = 
				new ReceitaRepositorio(manager);
		
		DespesaRepositorio despesaRepositorio =
				new DespesaRepositorio(manager);
		
		Double receitas = receitaRepositorio.somaReceitasAte(data);
		Double despesas = despesaRepositorio.somaDespesasAte(data);
		
		manager.close();
		
		System.out.println("Saldo: " + (receitas - despesas));

		System.out.println();

		return this.anterior;
	}

	@Override
	public String getNome() {
		return "Relatório Consolidado";
	}
}