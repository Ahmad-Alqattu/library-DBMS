package application;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class buyOpController {
	@FXML
	private Button cancelButton;
	@FXML
	private Button addButton;
	@FXML
	private Label empIdLabel;
	@FXML
	private Label bookIdLabel;
	@FXML
	private TextField memberIdText;
	@FXML
	private DatePicker Date;
	@FXML
	private Label wrongInput;
	@FXML
	private TextField Discount_percent;
	
	
	@FXML
	public void initialize() {
		empIdLabel.setText(Data.text);
		bookIdLabel.setText(String.valueOf(Data.oldBookId));
		Date.setValue(LocalDate.now());
	}
	
	public void toBookTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(1090,700);
		m.changeScene("BookTable.fxml");
	}
	
	public void successAdd(ActionEvent event) throws IOException{
		Main m = new Main();
		boolean isThereQuantity=false;
		boolean isThereABook=true;
		if(empIdLabel.getText().toString().isEmpty() || bookIdLabel.getText().toString().isEmpty() || memberIdText.getText().toString().isEmpty()
				|| Date==null||Discount_percent.getText().toString().isEmpty()) {
			wrongInput.setText("You must fill all data");
		}else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				String query = "Select * from book Where book_id=?";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, bookIdLabel.getText().toString());
				ResultSet rs  = st.executeQuery();
				if (rs.next()) {
					if (rs.getInt(6)>0) {
						isThereQuantity = true;
					}
				}else {
					isThereABook=false;
				}
				if (isThereABook) {
					if (isThereQuantity) {
					query = "INSERT INTO buy (emp_id, Book_id, Member_id, Date,Discount_percent) VALUES (?,?,?,?,?)";
				    st = con.prepareStatement(query);
					st.setString(1, empIdLabel.getText().toString());
					st.setString(2, bookIdLabel.getText().toString());
					st.setString(3, memberIdText.getText().toString());
					st.setString(4, Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					st.setString(5, Discount_percent.getText().toString());
					st.executeUpdate();
					query = "UPDATE book SET quantity=quantity-1 WHERE book_id = ?";
					st = con.prepareStatement(query);
					st.setString(1, bookIdLabel.getText().toString());
					st.executeUpdate();
				    st.close();
					con.close();
					m.changeSize(1090,700);
					m.changeScene("BookTable.fxml");
					}else{
						wrongInput.setText("There is no quantity of That book");
					}
				}else{
					wrongInput.setText("There is no book with that ID");
				}
			} catch (Exception e) {
					wrongInput.setText("There is no member with that ID.");
			}
			
		}
			
	}
	
	

}
