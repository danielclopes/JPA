package br.com.k19.jpa.grana;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Grana {

	private static Scanner entrada = new Scanner(System.in);
	
	private static EntityManagerFactory factory; 

	public static void main(String[] args) {
		/* Menu Principal */
		TelaMenu principal = 
			new TelaMenu("Menu Principal");

		TelaMenu relatorios = 
			new TelaMenu("Relatórios");
		TelaMenu receitas = 
			new TelaMenu("Receitas");
		TelaMenu despesas = 
			new TelaMenu("Despesas");
		TelaGeraDados geraDados =
			new TelaGeraDados(principal);
		TelaLimpaDados limpaDados =
			new TelaLimpaDados(principal);
		TelaSair sair = 
			new TelaSair();				

		principal.adicionaFilha(relatorios);
		principal.adicionaFilha(receitas);
		principal.adicionaFilha(despesas);
		principal.adicionaFilha(geraDados);
		principal.adicionaFilha(limpaDados);
		principal.adicionaFilha(sair);
		
		/* Relatórios */
		TelaRelatorioConsolidado consolidado = 
			new TelaRelatorioConsolidado(relatorios);
		TelaRelatorioMensal mensal = 
			new TelaRelatorioMensal(relatorios);
		TelaRelatorioPersonalizado personalizado = 
			new TelaRelatorioPersonalizado(relatorios);
		
		relatorios.adicionaFilha(consolidado);
		relatorios.adicionaFilha(mensal);
		relatorios.adicionaFilha(personalizado);
		relatorios.adicionaFilha(principal);

		/* Receitas */
		TelaReceitaAdicionar receitaAdicionar = 
			new TelaReceitaAdicionar(receitas);
		TelaMenu receitaConsultar = 
			new TelaMenu("Consultar");
		
		receitas.adicionaFilha(receitaAdicionar);
		receitas.adicionaFilha(receitaConsultar);
		receitas.adicionaFilha(principal);

		/* Receitas - Consultar */
		TelaReceitaConsultaPeriodo receitaConsultaPeriodo = 
			new TelaReceitaConsultaPeriodo(receitaConsultar); 
		TelaReceitaConsultaRecentes receitaConsultaRecentes = 
			new TelaReceitaConsultaRecentes(receitaConsultar);		
		
		receitaConsultar.adicionaFilha(receitaConsultaPeriodo);
		receitaConsultar.adicionaFilha(receitaConsultaRecentes);
		receitaConsultar.adicionaFilha(receitas);		
		
		/* Despesas */
		TelaDespesaAdicionar despesaAdicionar = 
			new TelaDespesaAdicionar(despesas);
		TelaMenu despesaConsultar = 
			new TelaMenu("Consultar");
		
		despesas.adicionaFilha(despesaAdicionar);
		despesas.adicionaFilha(despesaConsultar);
		despesas.adicionaFilha(principal);

		/* Despesas - Consultar */
		TelaDespesaConsultaPeriodo despesaConsultaPeriodo = 
			new TelaDespesaConsultaPeriodo(despesaConsultar); 
		TelaDespesaConsultaRecentes despesaConsultaRecentes = 
			new TelaDespesaConsultaRecentes(despesaConsultar);		
		
		despesaConsultar.adicionaFilha(despesaConsultaPeriodo);
		despesaConsultar.adicionaFilha(despesaConsultaRecentes);
		despesaConsultar.adicionaFilha(despesas);		
		
		Grana.factory = Persistence.createEntityManagerFactory("grana-pu");
		
		Tela atual = principal;
		while (atual != null) {
			atual = atual.mostra();
		}
		
		Grana.factory.close();
		Grana.entrada.close();
	}

	public static Scanner getEntrada() {
		return entrada;
	}
	
	public static EntityManager getEntityManager() {
		return Grana.factory.createEntityManager();
	}
}
