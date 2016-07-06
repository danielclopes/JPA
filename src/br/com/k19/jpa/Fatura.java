package br.com.k19.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Fatura {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy="fatura")
	private Collection<Ligacao> ligacoes = new ArrayList<Ligacao>();

	@Temporal(TemporalType.DATE)
	private Calendar vencimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Ligacao> getLigacoes() {
		return ligacoes;
	}

	public void setLigacoes(Collection<Ligacao> ligacoes) {
		this.ligacoes = ligacoes;
	}

	public Calendar getVencimento() {
		return vencimento;
	}

	public void setVencimento(Calendar vencimento) {
		this.vencimento = vencimento;
	}
}