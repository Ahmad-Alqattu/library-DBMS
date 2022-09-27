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

public class empController {
	
	@FXML
	private TableView<Employee> tableView;
	@FXML
	private TableColumn<Employee, Integer> idColumn;
	@FXML
	private TableColumn<Employee, String> NameColumn;
	@FXML
	private TableColumn<Employee, String> phoneColumn;
	@FXML
	private TableColumn<Employee, String> addressColumn;
	@FXML
	private TableColumn<Employee, String> passwordColumn;
	@FXML
	private ComboBox<String> addressList;
	@FXML
    private TextField nameText;
	@FXML
    private TextField idText;
	@FXML
	private Label wrongUpdate;
	
	
	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("Name"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("Phone"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("Address"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
		BindCombo();
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from employee");
				tableView.setItems(getEmployees(rs));	
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		
	}
	
	public ObservableList<Employee> getEmployees(ResultSet rs)
   {     
       ObservableList<Employee> employees = FXCollections.observableArrayList();
       try {
			while (rs.next()) {
				employees.add(new Employee(rs.getInt("id"), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
       return employees;
   }
	
	public void BindCombo() {
		try {
			addressList.getItems().add(".....");
			Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee");
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
	
	public void toMainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("Sample.fxml");
		m.changeSize(701, 507);
	}
	
	public void toAddMenu(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("addEmp.fxml");
		m.changeSize(600, 440);
	}
	
	public void toUpdateMenu(ActionEvent event) throws IOException{
		Main m = new Main();
		Employee emp = tableView.getSelectionModel().getSelectedItem();
		if(emp == null) {
			wrongUpdate.setText("Select an Employee to update.");
		}else if (Data.text.equals(String.valueOf(emp.getId()))||Data.text.equals("101")) {
			Data.oldEmployeeId = emp.getId();
			Data.oldEmployeeName = emp.getName();
			Data.oldEmployeePhone = emp.getPhone();
			Data.oldEmployeeAddress = emp.getAddress();
			Data.oldEmployeePassword = emp.getPassword();
			m.changeScene("updateEmployee.fxml");
			m.changeSize(610, 440);

		}else
			wrongUpdate.setText("Sorry. You can't update others information.");
	}
	
	public void deleteEmp(ActionEvent event) throws IOException{
		Main m = new Main();
		Employee emp = tableView.getSelectionModel().getSelectedItem();
		if(emp == null) {
			wrongUpdate.setText("Select a Book to delete.");
		}else {
		int i =emp.getId();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
			String query = "DELETE FROM employee WHERE id = ?";
			PreparedStatement st = con.prepareStatement(query);
		    st.setString(1, Integer.toString(i));
		    st.executeUpdate();
		    m.changeScene("empTable.fxml");
		    st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		}
	}
	
	public void searchEmployeeId(ActionEvent event) throws ClassNotFoundException, IOException{
		if (idText.getText().toString() != "") {
			String sql = "select * from employee where id = " + idText.getText().toString();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Employee> list = getEmployees(rs);
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
					ResultSet rs = stmt.executeQuery("select * from employee");
					tableView.setItems(getEmployees(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	public void searchEmployeeName(ActionEvent event) throws ClassNotFoundException, IOException{
		if (nameText.getText().toString() != "") {
			String sql = "select * from employee where name like '" + nameText.getText().toString() +"%'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Employee> list = getEmployees(rs);
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
					ResultSet rs = stmt.executeQuery("select * from employee");
					tableView.setItems(getEmployees(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	
	public void searchEmployeeAddress(ActionEvent event) throws ClassNotFoundException, IOException{
		if (addressList.getValue().toString() != ".....") {
			String sql = "select * from employee where address = '" + addressList.getValue().toString()+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Employee> list = getEmployees(rs);
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
					ResultSet rs = stmt.executeQuery("select * from employee");
					tableView.setItems(getEmployees(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
}
