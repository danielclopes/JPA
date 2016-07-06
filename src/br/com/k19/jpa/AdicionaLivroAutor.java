package br.com.k19.jpa;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaLivroAutor {
	public static void main(String[] args) {
		EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("livraria-pu");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		Scanner entrada = new Scanner(System.in);

		Autor a = new Autor();
		System.out.println("Digite o nome do autor:");
		a.setNome(entrada.nextLine());

		Livro l = new Livro();
		System.out.println("Digite o nome do livro:");
		l.setNome(entrada.nextLine());
		
		l.getAutores().add(a);
		
		entrada.close();

		manager.persist(a);
		manager.persist(l);
		
		System.out.println("Autor cadastrado com id " + a.getId());
		System.out.println("Livro cadastrado com id " + l.getId());

		manager.getTransaction().commit();

		manager.close();
		factory.close();
	}
}