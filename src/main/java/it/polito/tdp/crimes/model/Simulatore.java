package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import javafx.util.Duration;

public class Simulatore {
	
	private int agenti;
	private Quartiere caserma;
	private ArrayList<Crimine> crimini;
	private SimpleWeightedGraph<Quartiere, DefaultWeightedEdge> grafo;
	Duration gestione;
	int malGestito;
	PriorityQueue<Crimine> queue;

	public void init(int n, int inizio, ArrayList<Crimine> crimini, SimpleWeightedGraph<Quartiere, DefaultWeightedEdge> grafo) {
		agenti=n;
		caserma=new Quartiere(n,null);
		this.crimini=crimini;
		this.grafo=grafo;
		this.malGestito=0;
		gestione=Duration.minutes(15);
		queue.addAll(crimini);
		
		
	}

}
