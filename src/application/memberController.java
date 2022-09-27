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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;

public class memberController {
	
	@FXML
	private TableView<member> tableView;
	@FXML
	private TableColumn<member, Integer> idColumn;
	@FXML
	private TableColumn<member, String> nameColumn;
	@FXML
	private TableColumn<member, String> genderColumn;
	@FXML
	private TableColumn<member, String> phoneColumn;
	@FXML
	private TableColumn<member, String> addressColumn;
	@FXML
	private TableColumn<member, DatePicker> birthdateColumn;
	@FXML
	private TableColumn<member, DatePicker> start_dateColumn;
	@FXML
	private ComboBox<String> genderList;
	@FXML
	private ComboBox<String> addressList;
	@FXML
    private TextField nameText;
	@FXML
    private TextField idText;
	@FXML
	private DatePicker start_date;
	@FXML
	private Label wrongUpdate;
	
	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<member, Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<member, String>("name"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<member, String>("Gender"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<member, String>("Phone"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<member, String>("Address"));
		birthdateColumn.setCellValueFactory(new PropertyValueFactory<member, DatePicker>("Birthdate"));
		start_dateColumn.setCellValueFactory(new PropertyValueFactory<member, DatePicker>("start_date"));
		BindCombo();
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from member");
				tableView.setItems(getMembers(rs));	
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		
	}
	
	public ObservableList<member> getMembers(ResultSet rs)
  {     
      ObservableList<member> members = FXCollections.observableArrayList();
      try {
			while (rs.next()) {
				members.add(new member(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
      return members;
  }
	
	public void BindCombo() {
		try {
			genderList.getItems().add(".....");
			genderList.getItems().add("Male");
			genderList.getItems().add("Female");
			addressList.getItems().add(".....");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = new Connect().getCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from member");
			while (rs.next()) {
				if(!addressList.getItems().contains(rs.getString(5))) {
					addressList.getItems().add(rs.getString(5));
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
	
	public void toMainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(700, 508);
		m.changeScene("Sample.fxml");
	}
	
	public void toUpdateMenu(ActionEvent event) throws IOException{
		Main m = new Main();
		member memb = tableView.getSelectionModel().getSelectedItem();
		if(memb == null) {
			wrongUpdate.setText("Select a Member to update.");
		}else{
			Data.oldMemberId = memb.getId();
			Data.oldMemberName = memb.getName();
			Data.oldMemberGender = memb.getGender();
			Data.oldMemberPhone = memb.getPhone();
			Data.oldMemberAddress = memb.getAddress();
			Data.oldMemberBirthdate = memb.getBirthdate();
			Data.oldMemberStartdate = memb.getStart_date();
			m.changeSize(605, 440);
			m.changeScene("updateMember.fxml");
		}
	}
	
	public void toAddMenu(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(605, 440);
		m.changeScene("addMember.fxml");
	}
	
	public void deleteMember(ActionEvent event) throws IOException{
		Main m = new Main();
		member memb = tableView.getSelectionModel().getSelectedItem();
		int i =memb.getId();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = new Connect().getCon();
			String query = "DELETE FROM member WHERE member_id = ?";
			PreparedStatement st = con.prepareStatement(query);
		    st.setString(1, Integer.toString(i));
		    st.executeUpdate();
		    st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		m.changeScene("memberTable.fxml");
	}
	
	public void searchMemberId(ActionEvent event) throws ClassNotFoundException, IOException{
		if (idText.getText().toString() != "") {
			String sql = "select * from member where member_id = " + idText.getText().toString();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<member> list = getMembers(rs);
				tableView.setItems(list);
			}catch(Exception e) {
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from member");
					tableView.setItems(getMembers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	public void searchMemberName(ActionEvent event) throws ClassNotFoundException, IOException{
		if (nameText.getText().toString() != "") {
			String sql = "select * from member where Name like '" + nameText.getText().toString() +"%'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<member> list = getMembers(rs);
				tableView.setItems(list);
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from member");
					tableView.setItems(getMembers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	
	public void searchMemberGender(ActionEvent event) throws ClassNotFoundException, IOException{
		if (genderList.getValue().toString() != ".....") {
			String sql = "select * from member where gender = '" + genderList.getValue().toString()+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<member> list = getMembers(rs);
				tableView.setItems(list);
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from member");
					tableView.setItems(getMembers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	
	public void searchMemberAddress(ActionEvent event) throws ClassNotFoundException, IOException{
		if (addressList.getValue().toString() != ".....") {
			String sql = "select * from member where address = '" + addressList.getValue().toString()+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<member> list = getMembers(rs);
				tableView.setItems(list);
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from member");
					tableView.setItems(getMembers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	
	public void searchMembershipDate(ActionEvent event) throws ClassNotFoundException, IOException{
		if (start_date.getValue().toString() != "") {
			String sql = "select * from member where start_date >= '" + start_date.getValue().toString() + "'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<member> list = getMembers(rs);
				tableView.setItems(list);
			}catch(Exception e) {
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from member");
					tableView.setItems(getMembers(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
}
