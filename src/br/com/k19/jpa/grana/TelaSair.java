package br.com.k19.jpa.grana;

public class TelaSair implements Tela {

	@Override
	public String getNome() {
		return "Sair";
	}

	@Override
	public Tela mostra() {
		return null;
	}
}