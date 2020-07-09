/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.model.Centro;
import it.polito.tdp.crimes.model.Model;
import it.polito.tdp.crimes.model.Quartiere;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	if(this.boxAnno.getValue()==null) {
    		this.txtResult.setText("SELEZIONA UN ANNO");
    		return;
    	}
    	model.creaGrafo(this.boxAnno.getValue());
    	SimpleWeightedGraph<Quartiere, DefaultWeightedEdge> grafo=model.getGrafo();
    	ArrayList<Collegamento> collegamenti=new ArrayList<Collegamento>();
    	for(Quartiere q:model.getQuartieri()) {
    		this.txtResult.appendText("LISTA PER IL QUARTIERE "+q.getId()+"\n");
    		for(DefaultWeightedEdge e:grafo.outgoingEdgesOf(q)) {
    			Quartiere secondo;
    			if(grafo.getEdgeTarget(e).equals(q)) {
    				secondo=grafo.getEdgeSource(e);
    			} else
    				secondo=grafo.getEdgeTarget(e);
    			collegamenti.add(new Collegamento(secondo,grafo.getEdgeWeight(e)));
    		}
    		Collections.sort(collegamenti, new ComparatorePeso());
    		for(Collegamento c:collegamenti) {
    			this.txtResult.appendText(c.getQ().getId()+" con peso "+c.getPeso()+"\n");
    		}
    		collegamenti.clear();
    	}

    }

    @FXML
    void doSimula(ActionEvent event) {
    	String s=this.txtN.getText();
    	if(!this.isNumeric(s)) {
    		this.txtResult.setText("INSERISCI UN NUMERO INTERO COMPRESO TRA 1 E 10");
    		return;
    	}
    	int n=Integer.parseInt(s);
    	if(n<1 || n>10) {
    		this.txtResult.setText("INSERISCI UN NUMERO INTERO COMPRESO TRA 1 E 10");
    		return;
    	}
    	int mese=this.boxMese.getValue();
    	int giorno=this.boxGiorno.getValue();
    	int anno=this.boxAnno.getValue();
		try {
			LocalDateTime scelta=LocalDateTime.of(anno, mese, giorno, 0, 0);
		} catch (Throwable t) {
			t.printStackTrace();
			this.txtResult.appendText("INSERISCI UNA DATA ESISTENTE");
			return;
		}
		this.txtResult.setText("simulo...\n");
    	this.model.simula(anno,mese, giorno,n);

    }
    
    public static boolean isNumeric(String str) { 
  	  try {  
  	    Integer.parseInt(str);  
  	    return true;
  	  } catch(NumberFormatException e){  
  	    return false;  
  	  }  
  	}


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxAnno.getItems().addAll(model.getAllYears());
    	ArrayList<Integer> mesi= new ArrayList<Integer>();
    	for(int i=1;i<13;i++) {
    		mesi.add(i);
    	}
    	this.boxMese.getItems().addAll(mesi);
    	ArrayList<Integer> giorni= new ArrayList<Integer>();
    	for(int i=1;i<32;i++) {
    		giorni.add(i);
    	}
    	this.boxGiorno.getItems().addAll(giorni);
    }
}
