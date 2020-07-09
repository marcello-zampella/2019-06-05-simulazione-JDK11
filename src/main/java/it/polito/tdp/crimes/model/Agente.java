package it.polito.tdp.crimes.model;

public class Agente {
	
	private boolean libero;
	private Quartiere locazione;
	public boolean isLibero() {
		return libero;
	}
	public void setLibero(boolean libero) {
		this.libero = libero;
	}
	public Quartiere getLocazione() {
		return locazione;
	}
	public void setLocazione(Quartiere locazione) {
		this.locazione = locazione;
	}
	public Agente(boolean libero, Quartiere locazione) {
		super();
		this.libero = libero;
		this.locazione = locazione;
	}
	
	
	
	

}
