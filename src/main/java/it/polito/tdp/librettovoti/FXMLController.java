package it.polito.tdp.librettovoti;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
	private Libretto model;
	
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtVoto;
    
    @FXML
    private TextArea txtArea;

    @FXML
    private DatePicker dateEsame;
    
    @FXML
    void handleInserisci(ActionEvent event) {
    	String nomeEsame = txtNome.getText();
    	if(nomeEsame.length()==0) {
    		txtArea.setText("ERRORE: nome esame vuoto");
    		return;
    	}
    	String votoEsame = txtVoto.getText();
    	
    	int votoInt = 0;
    	try{
    		votoInt = Integer.parseInt(votoEsame);
    	}catch(NumberFormatException e) {
    		txtArea.setText("ERRORE: il voto deve essere numerico");
    		return;
    	}
    	if(votoInt<18 || votoInt>30) {
    		txtArea.setText("ERRORE: il voto deve essere compreso tra 18 e 30");
    		return;
    	}
    	/*String dataEsame = txtData.getText();
    	LocalDate data;
    	try{
    		data = LocalDate.parse(dataEsame);
    	}catch(DateTimeParseException e) {
    		txtArea.setText("ERRORE: la data non è nel formato corretto");
    		return;
    	}*/
    	LocalDate data = dateEsame.getValue();

    	if(data==null) {
    		txtArea.setText("ERRORE: la data è obbligatoria!");
    	}
    	Voto voto = new Voto(nomeEsame, votoInt, data);
    	model.add(voto);
    	
    	txtArea.setText(model.toString());
    	txtNome.clear();
    	//txtData.clear();
    	txtVoto.clear();
    	dateEsame.setValue(null);
    }

    @FXML
    void initialize() {
    	 assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
         assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
         assert txtVoto != null : "fx:id=\"txtVoto\" was not injected: check your FXML file 'Scene.fxml'.";
         assert dateEsame != null : "fx:id=\"dateEsame\" was not injected: check your FXML file 'Scene.fxml'.";
    }   
    
    public void setModel(Libretto model) {
    	this.model=model;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
