package br.com.k19.jpa.grana;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TelaLimpaDados implements Tela {

	private Tela anterior;
	
	public TelaLimpaDados(Tela anterior) {
		this.anterior = anterior;
	}

	@Override
	public Tela mostra() {
		System.out.println(">>> " + this.getNome() + " <<<");
		System.out.println();
		
		System.out.println("Limpando dados...");
		
		EntityManager manager = Grana.getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("DELETE FROM Receita");
		query.executeUpdate();
		
		query = manager.createQuery("DELETE FROM Despesa");
		query.executeUpdate();
		
		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Dados limpos");
		
		return this.anterior;
	}

	@Override
	public String getNome() {
		return "Limpa Dados";
	}
}