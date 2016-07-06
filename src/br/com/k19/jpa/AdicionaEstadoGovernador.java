package br.com.k19.jpa;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaEstadoGovernador {
	public static void main(String[] args) {
		EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("livraria-pu");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		Scanner entrada = new Scanner(System.in);
		
		Governador g = new Governador();
		System.out.println("Digite o nome do governador:");
		g.setNome(entrada.nextLine());

		Estado e = new Estado();
		System.out.println("Digite o nome do estado:");
		e.setNome(entrada.nextLine());
		
		entrada.close();
		
		e.setGovernador(g);

		manager.persist(g);
		manager.persist(e);
		
		System.out.println("Governador cadastrado com id " + g.getId());
		System.out.println("Estado cadastrado com id " + e.getId());

		manager.getTransaction().commit();

		manager.close();
		factory.close();
	}
}