package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addUserController {
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
	private PasswordField adminPassword;
	@FXML
	private Label wrongInput;
	
	public void successUserAdd(ActionEvent event) throws IOException{
		int foundId;
		Main m = new Main();
		if((nameText.getText().toString().isEmpty() || phoneText.getText().toString().isEmpty() || addressText.getText().toString().isEmpty()
				|| password.getText().toString().isEmpty()) && adminPassword.getText().toString().equals("admin5050") ) {
			wrongInput.setText("You must fill all data");
		}else if (!adminPassword.getText().toString().equals("admin5050")) {
			wrongInput.setText("Wrong Admin Password!");
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
			    String query1 = "SELECT * FROM employee e WHERE e.name=?";
				PreparedStatement st1 = con.prepareStatement(query1);
				st1.setString(1, nameText.getText().toString());
			    ResultSet rs  = st.executeQuery();
			    if(rs.next()){
			    	   foundId = rs.getInt("id");
			    }
			    Stage pop = new Stage();
			    pop.setResizable(false);
				Parent root = FXMLLoader.load(getClass().getResource("pop.fxml"));
				Scene scene = new Scene(root,300,100);
				pop.setTitle("User Information");
				pop.setScene(scene);
				pop.show();
			    st.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			m.changeScene("logIn.fxml");
		}
			
	}
	
	public void cancelUser(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("logIn.fxml");
	}
}