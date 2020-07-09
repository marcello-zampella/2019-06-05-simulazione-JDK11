package it.polito.tdp.crimes.model;

public class Centro {
	
	private double longitudine;
	private double latitudine;
	public Centro(double longitudine, double latitudine) {
		super();
		this.longitudine = longitudine;
		this.latitudine = latitudine;
	}
	public double getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}
	public double getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}
	@Override
	public String toString() {
		return "Centro [longitudine=" + longitudine + ", latitudine=" + latitudine + "]";
	}
	
	

}
