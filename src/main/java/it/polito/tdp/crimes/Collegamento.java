package it.polito.tdp.crimes;

import it.polito.tdp.crimes.model.Quartiere;

public class Collegamento {
	
	Quartiere q;
	double peso;
	public Quartiere getQ() {
		return q;
	}
	public void setQ(Quartiere q) {
		this.q = q;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Collegamento(Quartiere q, double peso) {
		super();
		this.q = q;
		this.peso = peso;
	}
	
	

}
