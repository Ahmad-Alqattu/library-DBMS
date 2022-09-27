package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.util.StringUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class logInController {

    

    @FXML
    private Button logInbutton;
    @FXML
    private Button newUserButton;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField idText;
    @FXML
    private PasswordField password;
    @FXML
    private BorderPane scene_01;
    
    public logInController() {
    }

    public void userLogIn(ActionEvent event) throws IOException, InterruptedException {
        checkLogin();
    }

    private void checkLogin() throws IOException, InterruptedException {
        Main m = new Main();
        int foundId=0;
        String foundPassword="";
        String name="";
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
			String query = "Select * from employee e Where e.id=?";
			PreparedStatement st = con.prepareStatement(query);
			if (!idText.getText().isEmpty() && !password.getText().isEmpty()) {
				st.setString(1, idText.getText().toString());
			    ResultSet rs  = st.executeQuery();
			    if(rs.next()){
			    	   foundId = rs.getInt("id");
			    	   name= rs.getString("Name");
			    	   foundPassword= rs.getString("password");
			    }
			    if(StringUtils.isStrictlyNumeric(idText.getText().toString()) && foundId == Integer.parseInt(idText.getText().toString()) && password.getText().toString().equals(foundPassword)) {
			    	Data.text = idText.getText();
			    	Data.nameText = name;
		            m.changeScene("Sample.fxml");
		            m.changeSize(700, 510);
		        }else {
		            wrongLogIn.setText("Wrong ID or Password!");
		        }
			}else {
	            wrongLogIn.setText("Please enter your data.");
	        }
		    
		    st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
        
        
    }
    
    public void createNewUser(ActionEvent event) throws IOException{
    	Main m = new Main();
    	m.changeScene("createNewUser.fxml");
    }

}