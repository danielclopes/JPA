package br.com.k19.jpa;

import java.util.Calendar;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaPedidoConsumidor {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("livraria-pu");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();

		Scanner entrada = new Scanner(System.in);

		Consumidor c = new Consumidor();
		System.out.println("Digite o nome do consumidor");
		c.setNome(entrada.nextLine());

		Pedido p = new Pedido();
		p.setData(Calendar.getInstance());
		
		p.setConsumidor(c);
	
		entrada.close();
	
		manager.persist(c);
		manager.persist(p);
		
		System.out.println("Consumidor cadastrado com id " + c.getId());
		System.out.println("Pedido cadastrado com id " + p.getId());

		manager.getTransaction().commit();

		manager.close();
		factory.close();
	}
}