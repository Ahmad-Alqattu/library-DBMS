package application;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addMemberController {
	@FXML
	private Button cancelButton;
	@FXML
	private Button addButton;
	@FXML
	private TextField nameText;
	@FXML
	private ComboBox<String> genderList;
	@FXML
	private TextField phoneText;
	@FXML
	private TextField addressText;
	@FXML
	private DatePicker birthdate;
	@FXML
	private DatePicker startdate;
	@FXML
	private Label wrongInput;
	
	@FXML
	public void initialize(){
		genderList.getItems().add("Male");
		genderList.getItems().add("Female");
	}
	
	public void toMemberTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("memberTable.fxml");
		m.changeSize(884,600);
	}
	
	public void successAdd(ActionEvent event) throws IOException{
		Main m = new Main();
		if(nameText.getText().toString().isEmpty() || genderList.getValue().toString().isEmpty() || phoneText.getText().toString().isEmpty()
				|| addressText.getText().toString().isEmpty() || birthdate==null || startdate==null) {
			wrongInput.setText("You must fill all data");
		}else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				String query = "INSERT INTO member (Name, Gender, Phone, Address, Birthdate, Start_date) VALUES (?,?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, nameText.getText().toString());
			    st.setString(2, genderList.getValue().toString());
			    st.setString(3, phoneText.getText().toString());
			    st.setString(4, addressText.getText().toString());
			    st.setString(5, birthdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			    st.setString(6, startdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			    st.executeUpdate();
			    st.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			m.changeScene("memberTable.fxml");
			m.changeSize(884,600);

		}
			
	}
}
