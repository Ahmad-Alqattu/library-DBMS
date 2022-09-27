package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;

public class pubController {
	
	@FXML
	private TableView<publisher> tableView;
	@FXML
	private TableColumn<publisher, Integer> idColumn;
	@FXML
	private TableColumn<publisher, String> nameColumn;
	@FXML
	private TableColumn<publisher, String> addressColumn;
	@FXML
	private TableColumn<publisher, String> phoneColumn;
	@FXML
	private TableColumn<publisher, String> emailColumn;
	@FXML
	private ComboBox<String> addressList;
	@FXML
    private TextField nameText;
	@FXML
    private TextField idText;
	@FXML
	private Label wrongUpdate;
	
	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<publisher, Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<publisher, String>("name"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<publisher, String>("address"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<publisher, String>("phone"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<publisher, String>("email"));
		BindCombo();
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from publisher");
				tableView.setItems(getPublishers(rs));	
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		
	}
	
	public ObservableList<publisher> getPublishers(ResultSet rs)
 {     
     ObservableList<publisher> publishers = FXCollections.observableArrayList();
     try {
			while (rs.next()) {
				publishers.add(new publisher(rs.getInt(1), rs.getString(2),rs.getString(4), rs.getString(3),rs.getString(5)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
     return publishers;
 }
	
	public void BindCombo() {
		try {
			addressList.getItems().add(".....");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from publisher");
			while (rs.next()) {
				if(!addressList.getItems().contains(rs.getString(4))) {
					addressList.getItems().add(rs.getString(4));
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@FXML
	private Button returnButton;
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button updateButton;
	
	public void toUpdateMenu(ActionEvent event) throws IOException{
		Main m = new Main();
		publisher pub = tableView.getSelectionModel().getSelectedItem();
		if(pub == null) {
			wrongUpdate.setText("Select a publisher to update.");
		}else {
			Data.oldPubId = pub.getId();
			Data.oldPubName = pub.getName();
			Data.oldPubPhone = pub.getPhone();
			Data.oldPubAddress = pub.getAddress();
			Data.oldPubEmail = pub.getEmail();
			m.changeScene("updatePublisher.fxml");
			m.changeSize(600, 440);

		}
	}
	
	public void toMainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("Sample.fxml");
		m.changeSize(884,600);

	}
	
	public void toAddMenu(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("addPublisher.fxml");
		m.changeSize(600, 440);

	}
	
	public void deletePub(ActionEvent event) throws IOException{
		Main m = new Main();
		publisher pub = tableView.getSelectionModel().getSelectedItem();
		int i =pub.getId();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
			String query = "DELETE FROM publisher WHERE pub_id = ?";
			PreparedStatement st = con.prepareStatement(query);
		    st.setString(1, Integer.toString(i));
		    st.executeUpdate();
		    st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		m.changeScene("publisherTable.fxml");
	}
	
	public void searchPublisherId(ActionEvent event) throws ClassNotFoundException, IOException{
		if (idText.getText().toString() != "") {
			String sql = "select * from publisher where pub_id = " + idText.getText().toString();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<publisher> list = getPublishers(rs);
				tableView.setItems(list);
			}catch(Exception e) {
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from publisher");
					tableView.setItems(getPublishers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	public void searchPublisherName(ActionEvent event) throws ClassNotFoundException, IOException{
		if (nameText.getText().toString() != "") {
			String sql = "select * from publisher where Name like '" + nameText.getText().toString() +"%'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<publisher> list = getPublishers(rs);
				tableView.setItems(list);
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from publisher");
					tableView.setItems(getPublishers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	
	public void searchPublisherAddress(ActionEvent event) throws ClassNotFoundException, IOException{
		if (addressList.getValue().toString() != ".....") {
			String sql = "select * from publisher where address = '" + addressList.getValue().toString()+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<publisher> list = getPublishers(rs);
				tableView.setItems(list);
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root"); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from publisher");
					tableView.setItems(getPublishers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
}