package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;


public class Crimine implements Comparable<Crimine>{
	
	private String tipo;
	private LocalDateTime ora;
	private Quartiere quartiere;
	private Agente agente;
	
	private TipoEvento accadimento;
	
	public enum TipoEvento {
		INIZIO_EVENTO,
		FINE_EVENTO
	}
	
	
	
	
	
	public TipoEvento getAccadimento() {
		return accadimento;
	}
	public void setAccadimento(TipoEvento accadimento) {
		this.accadimento = accadimento;
	}
	public Crimine(LocalDateTime ora, Quartiere quartiere, TipoEvento accadimento,Agente agente) {
		super();
		this.ora = ora;
		this.quartiere = quartiere;
		this.accadimento = accadimento;
		this.agente=agente;
		this.accadimento=TipoEvento.FINE_EVENTO;
	}
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
		this.accadimento=TipoEvento.INIZIO_EVENTO;
	}
	
	
	
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	@Override
	public String toString() {
		return "Crimine " + tipo +"  "+this.accadimento+" ora=" + ora + ", quartiere=" + quartiere +" "+agente;
	}
	
	@Override
	public int compareTo(Crimine other) {
		// TODO Auto-generated method stub
		return ora.compareTo(other.ora);
	}
	
	

}
