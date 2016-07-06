package br.com.k19.jpa.grana;

import java.util.ArrayList;
import java.util.List;

public class TelaMenu implements Tela {

	private String nome;

	private List<Tela> filhas = new ArrayList<Tela>();

	public TelaMenu(String nome) {
		this.nome = nome;
	}

	public Tela mostra() {
		System.out.println(">>> " + this.nome + " <<<");
		System.out.println();

		for (int i = 0; i < this.filhas.size(); i++) {
			System.out.println((i + 1) + ". " + this.filhas.get(i).getNome());
		}

		System.out.println();
		System.out.print("Escolha a opção: ");
		int indiceDaOpcao = Grana.getEntrada().nextInt() - 1;

		System.out.println();
		
		return this.filhas.get(indiceDaOpcao);
	}

	public void adicionaFilha(Tela tela) {
		this.filhas.add(tela);
	}
	
	public String getNome() {
		return this.nome;
	}
}