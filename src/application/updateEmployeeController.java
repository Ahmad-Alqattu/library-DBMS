package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class updateEmployeeController {
	@FXML
	private Button cancelButton;
	@FXML
	private Button updateButton;
	@FXML
	private TextField nameText;
	@FXML
	private TextField phoneText;
	@FXML
	private TextField addressText;
	@FXML
	private PasswordField oldPassword;
	@FXML
	private PasswordField newPassword;
	@FXML
	private Label employeeIdLabel;
	@FXML
	private Label wrongInput;
	
	@FXML
	public void initialize(){
		employeeIdLabel.setText(Data.text);
		nameText.setText(Data.oldEmployeeName);
		phoneText.setText(Data.oldEmployeePhone);
		addressText.setText(Data.oldEmployeeAddress);
	}
	
	public void toEmpTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("empTable.fxml");
		m.changeSize(683, 500);

	}
	
	public void successUpdate(ActionEvent event) throws IOException{
		Main m = new Main();
		if(nameText.getText().toString().isEmpty() || phoneText.getText().toString().isEmpty() || addressText.getText().toString().isEmpty()
				|| oldPassword.getText().toString().isEmpty() || newPassword.getText().toString().isEmpty()) {
			wrongInput.setText("You must fill all data");
		}else if(oldPassword.getText().toString().equals(Data.oldEmployeePassword)) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				String query = "UPDATE employee SET name=?, phone=?, address=?, password=? WHERE id = ?";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, nameText.getText().toString());
			    st.setString(2, phoneText.getText().toString());
			    st.setString(3, addressText.getText().toString());
			    st.setString(4, newPassword.getText().toString());
			    st.setString(5, Data.text);
			    st.executeUpdate();
			    st.close();
				con.close();
				m.changeScene("empTable.fxml");
				m.changeSize(683, 500);
			} catch (Exception e) {
				System.out.println(e);
			}
		}else
			wrongInput.setText("Your old password is wrong.");
			
	}

}
