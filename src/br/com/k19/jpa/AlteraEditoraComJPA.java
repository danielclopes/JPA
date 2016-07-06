package br.com.k19.jpa;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AlteraEditoraComJPA {

	public static void main(String[] args) {
		EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("livraria-pu");

		EntityManager manager = factory.createEntityManager();

		Scanner entrada = new Scanner(System.in);

		System.out.println("Digite o id da editora que deseja alterar: ");
		Long id = Long.parseLong(entrada.nextLine());

		Editora e = manager.find(Editora.class, id);

		System.out.println("Digite o novo nome da editora: ");
		e.setNome(entrada.nextLine());

		System.out.println("Digite o novo email da editora: ");
		e.setEmail(entrada.nextLine());

		entrada.close();

		manager.getTransaction().begin();
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}
}