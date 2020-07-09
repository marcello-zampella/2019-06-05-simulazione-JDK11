package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.model.Crimine.TipoEvento;
import javafx.util.Duration;

public class Simulatore {
	
	private int agentiLiberi;
	private Quartiere caserma;
	private ArrayList<Crimine> crimini;
	private SimpleWeightedGraph<Quartiere, DefaultWeightedEdge> grafo;
	private Duration gestione;
	private int malGestito;
	private PriorityQueue<Crimine> queue;
	private Random rand;
	private ArrayList<Agente> agenti;

	public void init(int n, int inizio, ArrayList<Crimine> crimini, SimpleWeightedGraph<Quartiere, DefaultWeightedEdge> grafo) {
		agentiLiberi=n;
		caserma=new Quartiere(inizio,null);
		this.crimini=crimini;
		this.grafo=grafo;
		this.malGestito=0;
		gestione=Duration.minutes(15);
		queue=new PriorityQueue<Crimine>();
		queue.addAll(crimini);
		rand=new Random();
		agenti=new ArrayList<Agente>();
		for(int i=0;i<n;i++) {
			agenti.add(new Agente(true,caserma));
		}
		
		
	}

	public void simula() {
		
		while(!queue.isEmpty()) {
			Crimine ev=queue.poll();
			System.out.println(ev);
			switch(ev.getAccadimento()) {
			
			case INIZIO_EVENTO:
				AgenteScelto sc=this.cercaAgenteVicino(ev.getQuartiere());
				if(sc==null) {
					this.malGestito++;
					break;
				}
				int oreGestione;
				if(ev.getTipo().equals("all-other-crimes"))
					oreGestione=rand.nextInt(2)+1;
				else {
					oreGestione=2;
				}
				sc.getAgente().setLocazione(ev.getQuartiere());
				sc.getAgente().setLibero(false);
				if (sc.getTempo()>15) {
					this.malGestito++;
				}
				queue.add(new Crimine((ev.getOra().plusHours(oreGestione)).plusMinutes((long) sc.getTempo()),ev.getQuartiere(),TipoEvento.FINE_EVENTO,sc.getAgente()));
				break;
				
			case FINE_EVENTO:
				ev.getAgente().setLibero(true);
				this.agentiLiberi++;
				break;
			}
		}
		
	}

	private AgenteScelto cercaAgenteVicino(Quartiere quartiere) {
		double temp;
		double minimo=99999;
		Agente scelto = null;
		boolean trovato=false;
		for(Agente a:agenti) {
			if(a.isLibero()) {
				trovato=true;
				if(a.getLocazione().equals(quartiere)) {
					scelto=a;
					break;
				}
			temp=grafo.getEdgeWeight(grafo.getEdge(a.getLocazione(), quartiere));
			if(temp<minimo) {
				minimo=temp;
				scelto=a;
			}
		}
		}
		if(!trovato)
			return null;
		return new AgenteScelto(scelto, minimo);
		
	}

	public int getMalGestito() {
		return malGestito;
	}
	
	
	

}
