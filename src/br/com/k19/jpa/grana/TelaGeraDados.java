package br.com.k19.jpa.grana;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;

public class TelaGeraDados implements Tela {

	private Tela anterior;
	
	public TelaGeraDados(Tela anterior) {
		this.anterior = anterior;
	}

	@Override
	public Tela mostra() {
		System.out.println(">>> " + this.getNome() + " <<<");
		System.out.println();
		
		System.out.println("Gerando dados...");
		
		EntityManager manager = Grana.getEntityManager();
		
		ReceitaRepositorio receitaRepositorio = 
			new ReceitaRepositorio(manager);
		
		DespesaRepositorio despesaRepositorio =
			new DespesaRepositorio(manager);
		
		manager.getTransaction().begin();
		
		/* Receitas */
		for (int i = 0; i < 36; i++) {
			Receita receita = new Receita();
			receita.setNome("Salário K19");
			receita.setTipo("Salário");
			receita.setValor(8000.0);
			
			Calendar data = new GregorianCalendar(2014, 0, 10);
			data.add(Calendar.MONTH, i);
			receita.setData(data);
			
			receitaRepositorio.adiciona(receita);
		}
		
		for (int i = 0; i < 36; i++) {
			Receita receita = new Receita();
			receita.setNome("VA");
			receita.setTipo("Vale Alimentação");
			receita.setValor(400.00);
			
			Calendar data = new GregorianCalendar(2014, 0, 15);
			data.add(Calendar.MONTH, i);
			receita.setData(data);
			
			receitaRepositorio.adiciona(receita);
		}
		
		for (int i = 0; i < 36; i++) {
			Receita receita = new Receita();
			receita.setNome("VR");
			receita.setTipo("Vale Refeição");
			receita.setValor(500.0);
			
			Calendar data = new GregorianCalendar(2014, 0, 20);
			data.add(Calendar.MONTH, i);
			receita.setData(data);
			
			receitaRepositorio.adiciona(receita);
		}
		
		for (int i = 0; i < 36; i++) {
			Receita receita = new Receita();
			receita.setNome("Aluguel Casa BH");
			receita.setTipo("Aluguel");
			receita.setValor(1000.0);
			
			Calendar data = new GregorianCalendar(2014, 0, 1);
			data.add(Calendar.MONTH, i);
			receita.setData(data);
			
			receitaRepositorio.adiciona(receita);
		}
		
		/* Despesas */
		for (int i = 0; i < 36; i++) {
			Despesa despesa = new Despesa();
			despesa.setNome("Aluguel Apto SP");
			despesa.setTipo("Aluguel");
			despesa.setValor(1800.0);
			
			Calendar data = new GregorianCalendar(2014, 0, 1);
			data.add(Calendar.MONTH, i);
			despesa.setData(data);
			
			despesaRepositorio.adiciona(despesa);
		}
		
		for (int i = 0; i < 36; i++) {
			Despesa despesa = new Despesa();
			despesa.setNome("Compras Supermercado");
			despesa.setTipo("Alimentação");
			despesa.setValor(1000.0);
			
			Calendar data = new GregorianCalendar(2014, 0, 10);
			data.add(Calendar.MONTH, i);
			despesa.setData(data);
			
			despesaRepositorio.adiciona(despesa);
		}
		
		for (int i = 0; i < 36; i++) {
			Despesa despesa = new Despesa();
			despesa.setNome("Combustível");
			despesa.setTipo("Transporte");
			despesa.setValor(400.00);
			
			Calendar data = new GregorianCalendar(2014, 0, 15);
			data.add(Calendar.MONTH, i);
			despesa.setData(data);
			
			despesaRepositorio.adiciona(despesa);
		}
		
		for (int i = 0; i < 36; i++) {
			Despesa despesa = new Despesa();
			despesa.setNome("Cinema");
			despesa.setTipo("Lazer");
			despesa.setValor(200.0);
			
			Calendar data = new GregorianCalendar(2014, 0, 20);
			data.add(Calendar.MONTH, i);
			despesa.setData(data);
			
			despesaRepositorio.adiciona(despesa);
		}
		
		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Dados gerados");
		System.out.println();
		
		return this.anterior;
	}

	@Override
	public String getNome() {
		return "Gera Dados";
	}
}
