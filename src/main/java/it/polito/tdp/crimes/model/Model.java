package it.polito.tdp.crimes.model;

import java.util.ArrayList;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import com.javadocmd.simplelatlng.*;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {

	private EventsDao dao;
	private SimpleWeightedGraph<Quartiere, DefaultWeightedEdge> grafo;
	private ArrayList<Integer> anni;
	private ArrayList<Quartiere> quartieri;
	
	
	public Model() {
		dao= new EventsDao();
	}
	
	
	public ArrayList<Integer> getAllYears() {
		anni=dao.getAllYears();
		return anni;
	}
	
	
	public void creaGrafo(Integer anno) {
		grafo=new SimpleWeightedGraph<Quartiere, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		quartieri=new ArrayList<Quartiere>();
		for(Integer i=1;i<8;i++) {
			
			quartieri.add(new Quartiere(i,dao.getCentro(i,anno)));
		}
		Graphs.addAllVertices(grafo, quartieri);
		
		//faccio gli archi
		for(Quartiere q1:quartieri) {
			for(Quartiere q2:quartieri) {
				if(!q1.equals(q2)) {
					LatLng punto1=new LatLng(q1.getCentro().getLatitudine(), q1.getCentro().getLongitudine());
					LatLng punto2=new LatLng(q2.getCentro().getLatitudine(), q2.getCentro().getLongitudine());
					if(!(grafo.containsEdge(q1, q2) || grafo.containsEdge(q2, q1)))
						Graphs.addEdge(grafo, q1, q2, LatLngTool.distance(punto1, punto2, LengthUnit.KILOMETER));
				}
			}
		}
		
		return;
		
		
	}


	public SimpleWeightedGraph<Quartiere, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}


	public ArrayList<Quartiere> getQuartieri() {
		return quartieri;
	}


	public void simula(int anno, int mese, int giorno, int n) {
		int inizio=dao.getDistrettoIniziale();
		if(inizio<0) {
			System.out.println("problema");
			return;
		}
		ArrayList<Crimine> crimini=dao.getAllEventiGiorno(anno,mese,giorno);
		Simulatore sim=new Simulatore();
		sim.init(n,inizio,crimini,grafo);
		sim.simula();
		System.out.println(crimini.size()+"   "+sim.getMalGestito());
		
		
	}
	
	
	
	
}
