package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class updatePublisherController {
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
	private TextField emailText;
	@FXML
	private Label publisherIdLabel;
	@FXML
	private Label wrongInput;
	
	@FXML
	public void initialize(){
		publisherIdLabel.setText(String.valueOf(Data.oldPubId));
		nameText.setText(Data.oldPubName);
		phoneText.setText(Data.oldPubPhone);
		addressText.setText(Data.oldPubAddress);
		emailText.setText(Data.oldPubEmail);
	}
	
	public void toPubTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("publisherTable.fxml");
		m.changeSize(660,500);

	}
	
	public void successUpdate(ActionEvent event) throws IOException{
		Main m = new Main();
		if(nameText.getText().toString().isEmpty() || phoneText.getText().toString().isEmpty() || addressText.getText().toString().isEmpty()
				|| emailText.getText().toString().isEmpty()) {
			wrongInput.setText("You must fill all data");
		}else{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				String query = "UPDATE publisher SET name=?, phone=?, address=?, email=? WHERE pub_id = ?";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, nameText.getText().toString());
			    st.setString(2, phoneText.getText().toString());
			    st.setString(3, addressText.getText().toString());
			    st.setString(4, emailText.getText().toString());
			    st.setString(5, String.valueOf(Data.oldPubId));
			    st.executeUpdate();
			    st.close();
				con.close();
				m.changeScene("publisherTable.fxml");
				m.changeSize(660,500);

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
