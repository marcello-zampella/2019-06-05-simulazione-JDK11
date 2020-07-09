package it.polito.tdp.crimes.model;

public class Quartiere {
	
	private int id;
	private Centro centro;
	public Quartiere(int id, Centro centro) {
		super();
		this.id = id;
		this.centro = centro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quartiere other = (Quartiere) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	@Override
	public String toString() {
		return "Quartiere [id=" + id + "]";
	}
	
	
	
	
	

}
