package it.polito.tdp.crimes;

import java.util.Comparator;

import it.polito.tdp.crimes.model.Quartiere;

public class ComparatorePeso implements Comparator {
	
	public int compare (Object o1, Object o2) {
		Collegamento a1=(Collegamento) o1;
		Collegamento a2 =(Collegamento) o2;
		if(a1.getPeso()>a2.getPeso())
			return 1;
		if(a1.getPeso()<a2.getPeso())
			return -1;
		return 0;
		
	}

}
