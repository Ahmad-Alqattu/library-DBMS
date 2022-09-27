package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class updateBookController {
	@FXML
	private Button cancelButton;
	@FXML
	private Button updateButton;
	@FXML
	private TextField titleText;
	@FXML
	private TextField authorText;
	@FXML
	private TextField priceText;
	@FXML
	private TextField quantityText;
	@FXML
	private ComboBox<String> pubNameList;
	@FXML
	private Label wrongInput;
	@FXML
	
	private ComboBox<String> categoryText;

    @FXML
    private TextField Buying_price;
	public void initialize(){
		titleText.setText(Data.oldBookTitle);
		authorText.setText(Data.oldBookAuthor);
		categoryText.setValue(Data.oldBookCategory);
		priceText.setText(String.valueOf(Data.oldBookPrice));
		quantityText.setText(String.valueOf(Data.oldBookQuantity));
		pubNameList.setValue(Data.oldBookpubName);
		BindCombo();
	}
	public void BindCombo() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =   new Connect().getCon();  
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from publisher");
			while (rs.next()) {
				pubNameList.getItems().add(rs.getString(2));
			}
			 rs = stmt.executeQuery("select * from Category");
			while (rs.next()) {
				categoryText.getItems().add(rs.getString(2));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void toBookTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(1090,700);
		m.changeScene("bookTable.fxml");
		
	}
	
	public void successUpdate(ActionEvent event) throws IOException{
		if(titleText.getText().toString().isEmpty() || authorText.getText().toString().isEmpty() || categoryText.getValue().toString().isEmpty()
				|| priceText.getText().toString().isEmpty() || quantityText.getText().toString().isEmpty()) {
			wrongInput.setText("You must fill all data");
		}else {
			try {
				int pub_id_found=0,cat=0;
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con =  new Connect().getCon(); 
				String query = "UPDATE book SET title=?, author=?, Category_id=?, buying_price=?,selling_price=?, quantity=?, pub_id=? WHERE book_id = ?";
				Statement stmt = con.createStatement();
				String Query = "select * from publisher where publisher.name='" + pubNameList.getValue().toString() +"';";
				ResultSet rs = stmt.executeQuery(Query);
				
				if(rs.next()) {
					pub_id_found=rs.getInt(1);
				}
				
				 Query = "select * from Category where Category.category_name='" + categoryText.getValue().toString() +"';";
				 rs = stmt.executeQuery(Query);
				if(rs.next()) {
					cat=rs.getInt(3);
				}
				PreparedStatement st = con.prepareStatement(query);
				st.setString(8,String.valueOf(Data.oldBookId));
				st.setString(1, titleText.getText().toString());
			    st.setString(2, authorText.getText().toString());
			    st.setString(3,  String.valueOf(cat));
			    st.setString(5, priceText.getText().toString());
			    st.setString(4, Buying_price.getText().toString());
			    st.setString(6, quantityText.getText().toString());
			    st.setString(7, String.valueOf(pub_id_found));
				st.executeUpdate();
				Main m = new Main();
				m.changeSize(1090,700);
			    m.changeScene("BookTable.fxml");
			} catch (Exception e) {
				System.out.println(e);
			}
		}	
	}
	
}
