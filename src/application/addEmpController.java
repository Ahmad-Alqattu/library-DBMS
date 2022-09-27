package application;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class addEmpController {
	@FXML
	private Button cancelButton;
	@FXML
	private Button addButton;
	@FXML
	private TextField nameText;
	@FXML
	private TextField phoneText;
	@FXML
	private TextField addressText;
	@FXML
	private PasswordField password;
	@FXML
	private Label wrongInput;
	
	public void toEmpTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("empTable.fxml");
		m.changeSize(683, 500);

	}
	
	public void successAdd(ActionEvent event) throws IOException{
		Main m = new Main();
		if(nameText.getText().toString().isEmpty() || phoneText.getText().toString().isEmpty() || addressText.getText().toString().isEmpty()
				|| password.getText().toString().isEmpty()) {
			wrongInput.setText("You must fill all data");
		}else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = new Connect().getCon(); 
				//Statement stmt = con.createStatement();
				String query = "INSERT INTO employee (Name, Phone, address, password) VALUES (?,?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, nameText.getText().toString());
			    st.setString(2, phoneText.getText().toString());
			    st.setString(3, addressText.getText().toString());
			    st.setString(4, password.getText().toString());
			    st.executeUpdate();
			    st.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			m.changeScene("empTable.fxml");
			m.changeSize(683, 500);
		}
			
	}
}
