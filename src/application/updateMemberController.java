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

public class updateMemberController {
	@FXML
	private Button cancelButton;
	@FXML
	private Button updateButton;
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
	private Label memberIdLabel;
	
	@FXML
	public void initialize(){
		genderList.getItems().add("Male");
		genderList.getItems().add("Female");
		memberIdLabel.setText(String.valueOf(Data.oldMemberId));
		nameText.setText(Data.oldMemberName);
		genderList.setValue(Data.oldMemberGender);
		phoneText.setText(Data.oldMemberPhone);
		addressText.setText(Data.oldMemberAddress);
		birthdate.setValue(Data.oldMemberBirthdate);
		startdate.setValue(Data.oldMemberStartdate);
	}
	
	public void toMemberTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("memberTable.fxml");
		m.changeSize(884,600);

	}
	
	public void successUpdate(ActionEvent event) throws IOException{
		Main m = new Main();
		if(nameText.getText().toString().isEmpty() || genderList.getValue().toString().isEmpty() || phoneText.getText().toString().isEmpty()
				|| addressText.getText().toString().isEmpty() || birthdate==null || startdate==null) {
			wrongInput.setText("You must fill all data");
		}else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				String query = "UPDATE member SET name=?, gender=?, phone=?, address=?, Birthdate=?, start_date=? WHERE member_id = ?";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, nameText.getText().toString());
			    st.setString(2, genderList.getValue().toString());
			    st.setString(3, phoneText.getText().toString());
			    st.setString(4, addressText.getText().toString());
			    st.setString(5, birthdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			    st.setString(6, startdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			    st.setString(7, startdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			    st.executeUpdate(String.valueOf(Data.oldMemberId));
			    st.close();
				con.close();
				m.changeScene("memberTable.fxml");
				m.changeSize(884,600);

			} catch (Exception e) {
				System.out.println(e);
			}
		}
			
	}
}