package application;

import java.awt.geom.Arc2D.Float;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class buyController {
	
	@FXML
	private TableView<buy> tableView;
	@FXML
	private TableColumn<buy, Integer> empIdColumn;
	@FXML
	private TableColumn<buy, Integer> bookIdColumn;
	@FXML
	private TableColumn<buy, Integer> memberIdColumn;
	@FXML
	private TableColumn<buy, String> empNameColumn;
	@FXML
	private TableColumn<buy, String> bookTitleColumn;
	@FXML
	private TableColumn<buy, String> memberNameColumn;
	@FXML
	private TableColumn<buy, DatePicker> dateColumn;
	@FXML
	private TableColumn<buy, Float> price;
	
	@FXML
    private TextField empIdText;
	@FXML
    private TextField bookIdText;
	@FXML
    private TextField membIdText;
	@FXML
    private Label wrongUpdate;
	@FXML
    private Label noOfOpLabel;
	@FXML
	private DatePicker date;

	
	
	@FXML
    void printinvoice(ActionEvent event) {

		try {
			    // check the table's selected item and get selected item
			    if (tableView.getSelectionModel().getSelectedItem() != null) {
					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("buyinvoice.fxml"));
					
					Parent root = null;
					root = loader.load();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Edit Stage");
				//	System.out.println(	((borrow)loader.getController()).getMemberid());
					buy selectedmaintenanc = tableView.getSelectionModel().getSelectedItem();
					buyinvoicec ic=((buyinvoicec)loader.getController());
					ic.getBookTitle_Data().setText(String.valueOf(selectedmaintenanc.getBookTitle()));
					ic.getDate().setText(String.valueOf(selectedmaintenanc.getDate()));
					ic.getAmount_Paid().setText(String.valueOf(selectedmaintenanc.getPrice()));
					ic.getEmployeeID().setText(String.valueOf(selectedmaintenanc.getEmpid()));
					ic.getDiscount_Data().setText(String.valueOf(selectedmaintenanc.getdes()));
					ic.getMemberName_Data().setText(String.valueOf(selectedmaintenanc.getMemberName()));
					ic.getBookPrice_Data().setText(String.valueOf(selectedmaintenanc.getSelling_price()));
					ic.getMemberID_Data().setText(String.valueOf(selectedmaintenanc.getMemberid()));
					ic.getBookID_Data().setText(String.valueOf(selectedmaintenanc.getBookid()));

					stage.show();
			    }else {
					wrongUpdate.setText("Select opration");
			    }
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Edit Start Error");
			alert.show();
		}
    }
	
	@FXML
	public void initialize() {
		empIdColumn.setCellValueFactory(new PropertyValueFactory<buy, Integer>("empid"));
		empNameColumn.setCellValueFactory(new PropertyValueFactory<buy, String>("empName"));
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<buy, Integer>("Bookid"));
		bookTitleColumn.setCellValueFactory(new PropertyValueFactory<buy, String>("bookTitle"));
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<buy, Integer>("Memberid"));
		memberNameColumn.setCellValueFactory(new PropertyValueFactory<buy, String>("memberName"));
		price.setCellValueFactory(new PropertyValueFactory<buy, Float>("price"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<buy, DatePicker>("date"));
		 try {				System.out.println("s1");

				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				System.out.println("s2");
				String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
						+ "FROM buy\n"
						+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
						+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
						+ "INNER JOIN member ON member.member_id = buy.Member_id;";
				System.out.println("s3");

				ResultSet rs = stmt.executeQuery(Query);
				System.out.println("s4");

				tableView.setItems(getbuy(rs));
				System.out.println("s5");

				Query = "SELECT COUNT(employee.id)\n"
						+ "FROM buy\n"
						+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
						+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
						+ "INNER JOIN member ON member.member_id = buy.Member_id;";
				rs = stmt.executeQuery(Query);
				while (rs.next()) {
					noOfOpLabel.setText(rs.getString(1));
				}
				con.close();
		} catch (Exception e) {
				System.out.println(e);
			}
		
	}
	
	public ObservableList<buy> getbuy(ResultSet rs)
    {     
		System.out.println("ss");

        ObservableList<buy> buys = FXCollections.observableArrayList();
        try {
			while (rs.next()) {
				System.out.println("ss");
				buys.add(new buy(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6), rs.getDate(7).toLocalDate(),rs.getInt(8),rs.getFloat(9)));
				System.out.println("dd");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return buys;
    }
	
	@FXML
	private Button returnButton;
	@FXML
	private Button deleteButton;
		
	public void toMainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(701, 507);
		m.changeScene("Sample.fxml");
	}
	
	public void searchEmployeeId(ActionEvent event) throws ClassNotFoundException, IOException{
		membIdText.setText("");
		bookIdText.setText("");
		date.setValue(null);
		if (empIdText.getText().toString() != "") {
			String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
					+ "FROM buy\n"
					+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
					+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
					+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
					+ "WHERE buy.emp_id = " + empIdText.getText().toString()+";";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(Query);
				ObservableList<buy> list = getbuy(rs);
				tableView.setItems(list);
				Query = "SELECT COUNT(employee.id)\n"
						+ "FROM buy\n"
						+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
						+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
						+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
						+ "WHERE buy.emp_id = " + empIdText.getText().toString()+";";
				rs = stmt.executeQuery(Query);
				while (rs.next()) {
					noOfOpLabel.setText(rs.getString(1));
				}
			}catch(Exception e) {
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getbuy(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					rs = stmt.executeQuery(Query);
					while (rs.next()) {
						noOfOpLabel.setText(rs.getString(1));
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	public void searchBookId(ActionEvent event) throws ClassNotFoundException, IOException{
		membIdText.setText("");
		empIdText.setText("");
		date.setValue(null);
		if (bookIdText.getText().toString() != "") {
			String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
					+ "FROM buy\n"
					+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
					+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
					+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
					+ "WHERE buy.book_id = " + bookIdText.getText().toString()+";";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(Query);
				ObservableList<buy> list = getbuy(rs);
				tableView.setItems(list);
				Query = "SELECT COUNT(employee.id)\n"
						+ "FROM buy\n"
						+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
						+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
						+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
						+ "WHERE buy.book_id = " + bookIdText.getText().toString()+";";
				rs = stmt.executeQuery(Query);
				while (rs.next()) {
					noOfOpLabel.setText(rs.getString(1));
				}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getbuy(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					rs = stmt.executeQuery(Query);
					while (rs.next()) {
						noOfOpLabel.setText(rs.getString(1));
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	
	public void searchMemberId(ActionEvent event) throws ClassNotFoundException, IOException{
		empIdText.setText("");
		bookIdText.setText("");
		date.setValue(null);
		if (membIdText.getText().toString() != "") {
			String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
					+ "FROM buy\n"
					+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
					+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
					+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
					+ "WHERE buy.member_id = " + membIdText.getText().toString()+";";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(Query);
				ObservableList<buy> list = getbuy(rs);
				tableView.setItems(list);
				Query = "SELECT COUNT(employee.id)\n"
						+ "FROM buy\n"
						+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
						+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
						+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
						+ "WHERE buy.member_id = " + membIdText.getText().toString()+";";
				rs = stmt.executeQuery(Query);
				while (rs.next()) {
					noOfOpLabel.setText(rs.getString(1));
				}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getbuy(rs));
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					rs = stmt.executeQuery(Query);
					while (rs.next()) {
						noOfOpLabel.setText(rs.getString(1));
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	public void searchDate(ActionEvent event) throws ClassNotFoundException, IOException{
		membIdText.setText("");
		bookIdText.setText("");
		empIdText.setText("");
		if (date.getValue() != null) {
			String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
					+ "FROM buy\n"
					+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
					+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
					+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
					+ "WHERE buy.date = '" + date.getValue().toString() +"';";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(Query);
				ObservableList<buy> list = getbuy(rs);
				tableView.setItems(list);
				Query = "SELECT COUNT(employee.id)\n"
						+ "FROM buy\n"
						+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
						+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
						+ "INNER JOIN member ON member.member_id = buy.Member_id\n"
						+ "WHERE buy.date = '" + date.getValue().toString() +"';";
				rs = stmt.executeQuery(Query);
				while (rs.next()) {
					noOfOpLabel.setText(rs.getString(1));
				}
			}catch(Exception e) {
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else {
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, buy.date,buy.Discount_percent,book.selling_price \n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getbuy(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM buy\n"
							+ "INNER JOIN book ON book.Book_ID=buy.Book_id\n"
							+ "INNER JOIN employee ON employee.id = buy.emp_id\n"
							+ "INNER JOIN member ON member.member_id = buy.Member_id;";
					rs = stmt.executeQuery(Query);
					while (rs.next()) {
						noOfOpLabel.setText(rs.getString(1));
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	
	public void deleteBook(ActionEvent event) throws IOException{
		Main m = new Main();
		buy buyOp = tableView.getSelectionModel().getSelectedItem();
		if(buyOp == null) {
			wrongUpdate.setText("Select an operation to delete.");
		}else {
			int id_book =buyOp.getBookid();
			int id_emp =buyOp.getEmpid();
			int id_member =buyOp.getMemberid();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				String query = "delete from buy where book_id=? and emp_id=? and member_id = ?;";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, Integer.toString(id_book));
			    st.setString(2, Integer.toString(id_emp));
			    st.setString(3, Integer.toString(id_member));
			    st.executeUpdate();
			    query = "UPDATE book SET quantity=quantity+1 WHERE book_id = ?";
				st = con.prepareStatement(query);
				st.setString(1, Integer.toString(id_book));
				st.executeUpdate();
			    st.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			m.changeScene("buyTable.fxml");
		}
	}
}

