package br.com.k19.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaRevista {
	public static void main(String[] args) {
		EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("livraria-pu");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		for(int i = 0; i < 15; i++) {
			Revista revista = new Revista();
			revista.setNome("Revista " + (i + 1));
			revista.setPreco(10.0 * (i + 1));
			manager.persist(revista);
		}
				
		manager.getTransaction().commit();

		manager.close();
		factory.close();
	}
}