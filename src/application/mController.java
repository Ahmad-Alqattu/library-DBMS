package application;

import java.io.IOException;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class mController {
	
	public mController(){
	}
	
	@FXML
	private Button empButton;
	@FXML
	private Button bookButton;
	@FXML
	private Button pubButton;
	@FXML
	private Button borrowButton;
	@FXML
	private Button memberButton;
	@FXML
	private Button buyButton;
	@FXML
	private Button logOutButton;
	@FXML
	private Label username;
	
	@FXML
	public void initialize() {
		username.setText(Data.nameText);
	}
	
	public void mainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("empTable.fxml");
		m.changeSize(683, 500);

	}
	
	public void toLogIn(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("logIn.fxml");
		m.changeSize(650, 460);

	}
	
	public void toBookTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(1090,700);
		m.changeScene("BookTable.fxml");
	}
	
	public void toPubTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("publisherTable.fxml");
		m.changeSize(660,500);

	}
	
	public void toMemberTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(884,600);
		m.changeScene("memberTable.fxml");
	}
	
	public void toBorrowTable(ActionEvent event) throws IOException{
		
		Main m = new Main();
		m.changeSize(1260,540);
		m.changeScene("borrowTable.fxml");
	}
	
	public void toBuyTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(1115,555);
		m.changeScene("buyTable.fxml");
	}
	

    


    @FXML
    void toStatisticsTable(ActionEvent event)throws IOException {
		Main m = new Main();
		m.changeSize(623,480);
		m.changeScene("Return.fxml");
    }
	
	
}
