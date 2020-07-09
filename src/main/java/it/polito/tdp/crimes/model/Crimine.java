package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;

public class Crimine {
	
	String tipo;
	LocalDateTime ora;
	Quartiere quartiere;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public LocalDateTime getOra() {
		return ora;
	}
	public void setOra(LocalDateTime ora) {
		this.ora = ora;
	}
	public Quartiere getQuartiere() {
		return quartiere;
	}
	public void setQuartiere(Quartiere quartiere) {
		this.quartiere = quartiere;
	}
	public Crimine(String tipo, LocalDateTime ora, Quartiere quartiere) {
		super();
		this.tipo = tipo;
		this.ora = ora;
		this.quartiere = quartiere;
	}
	@Override
	public String toString() {
		return "Crimine " + tipo + " ora=" + ora + ", quartiere=" + quartiere;
	}
	
	

}
