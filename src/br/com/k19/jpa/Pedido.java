package br.com.k19.jpa;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {
	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	private Calendar data;

	@ManyToOne
	private Consumidor consumidor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}
}