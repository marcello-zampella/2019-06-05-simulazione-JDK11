package it.polito.tdp.crimes.model;

public class AgenteScelto {
	Agente agente;
	double tempo;
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	public AgenteScelto(Agente agente, double tempo) {
		super();
		this.agente = agente;
		this.tempo = tempo;
	}
	

}
