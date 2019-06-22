package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private ChoiceBox<?> selectNerc;

    @FXML
    private TextField maxYears;

    @FXML
    private TextField maxHours;

    @FXML
    private Button btnWorstCase;
    
    Model model;

    @FXML
    void doWorstCaseAnalysis(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert selectNerc != null : "fx:id=\"selectNerc\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert maxYears != null : "fx:id=\"maxYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert maxHours != null : "fx:id=\"maxHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnWorstCase != null : "fx:id=\"btnWorstCase\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
	}
}
