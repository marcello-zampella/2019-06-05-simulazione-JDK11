package it.polito.tdp.crimes.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Centro;
import it.polito.tdp.crimes.model.Crimine;
import it.polito.tdp.crimes.model.Event;
import it.polito.tdp.crimes.model.Quartiere;



public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public ArrayList<Integer> getAllYears() {
		String sql = "SELECT DISTINCT YEAR(e.reported_date) AS anno " + 
				"FROM `events` e " + 
				"ORDER BY e.reported_date " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ArrayList<Integer> list = new ArrayList<Integer>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getInt("anno"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public Centro getCentro(Integer i,Integer anno) {
		String sql = "SELECT AVG(e.geo_lon) AS longitudine, AVG(e.geo_lat) AS latitudine " + 
				"FROM `events` e " + 
				"WHERE YEAR(e.reported_date)=? AND e.district_id=? " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			st.setInt(2, i);			
			ResultSet res = st.executeQuery() ;
			
			res.next();
			Centro c=null;
				try {
					c=new Centro(res.getDouble("longitudine"),res.getDouble("latitudine"));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("PROBLEMA"));
				}
			
			conn.close();
			return c ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public int getDistrettoIniziale() {
		String sql = "SELECT e.district_id AS distretto, COUNT(*) AS conta " + 
				"FROM `events` e " + 
				"GROUP BY e.district_id " + 
				"ORDER BY conta " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;		
			ResultSet res = st.executeQuery() ;
			
			res.next();
			int c=-1;
				try {
					c=res.getInt("distretto");
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("PROBLEMA"));
				}
			
			conn.close();
			return c ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1 ;
		}
	}

	public ArrayList<Crimine> getAllEventiGiorno(int anno, int mese, int giorno) {
		String sql = "SELECT e.offense_category_id AS tipo, e.reported_date AS tempo, e.district_id AS id " + 
				"FROM `events` e " + 
				"WHERE CAST(e.reported_date AS DATE)=? " + 
				"ORDER BY e.reported_date " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			String s=anno+"-"+mese+"-"+giorno;
			st.setString(1, s);
			ResultSet res = st.executeQuery() ;
			ArrayList<Crimine> c=new ArrayList<Crimine>();
			while(res.next()) {
				try {
					c.add(new Crimine(res.getString("tipo"),res.getTimestamp("tempo").toLocalDateTime(),new Quartiere(res.getInt("id"),null)));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			
			conn.close();
			return c ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

}
