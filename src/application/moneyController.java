package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class moneyController {
	
	@FXML
	private Button okButton;
	@FXML
	private Label warningLabel;
	
	@FXML
	public void initialize() {
		String war = Data.warningDays;
		warningLabel.setText(war);
	}
	
	public void toBorrowTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("borrowTable.fxml");
	}

}
