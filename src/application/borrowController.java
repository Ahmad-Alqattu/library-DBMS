package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.time.temporal.ChronoUnit;

public class borrowController {
	
	@FXML
	private TableView<borrow> tableView;
    @FXML
    private TextField BorrowSNText1;

    @FXML
    private TableColumn<borrow, DatePicker> DueDateColumn;

    @FXML
    private TableColumn<borrow, Float> Late_FeeColumn;

    @FXML
    private CheckBox notReternd;
	@FXML
	private TableColumn<borrow, Integer> empIdColumn;
	@FXML
	private TableColumn<borrow, Integer> bookIdColumn;
	@FXML
	private TableColumn<borrow, Integer> memberIdColumn;
	@FXML
	private TableColumn<borrow, String> empNameColumn;
	@FXML
	private TableColumn<borrow, String> bookTitleColumn;
	@FXML
	private TableColumn<borrow, String> memberNameColumn;
	@FXML
	private TableColumn<borrow, DatePicker> borrowDateColumn;
	
	@FXML
	private TableColumn<borrow, Integer> Borrow_SNColumn;
	@FXML
	private TableColumn<borrow, DatePicker> returnDateColumn;
	@FXML
    private TextField empIdText;
	@FXML
    private TextField bookIdText;
	@FXML
    private TextField membIdText;
	@FXML
	private DatePicker borrow_date;
	@FXML
	private DatePicker return_date;

	@FXML
	private Label noOfOpLabel;
	@FXML
	private Label wrongUpdate;
	
	@FXML
	public void initialize() {		Borrow_SNColumn.setCellValueFactory(new PropertyValueFactory<borrow, Integer>("Borrow_SN"));
		empIdColumn.setCellValueFactory(new PropertyValueFactory<borrow, Integer>("empid"));
		empNameColumn.setCellValueFactory(new PropertyValueFactory<borrow, String>("empName"));
		bookIdColumn.setCellValueFactory(new PropertyValueFactory<borrow, Integer>("Bookid"));
		bookTitleColumn.setCellValueFactory(new PropertyValueFactory<borrow, String>("bookTitle"));
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<borrow, Integer>("Memberid"));
		memberNameColumn.setCellValueFactory(new PropertyValueFactory<borrow, String>("memberName"));
		borrowDateColumn.setCellValueFactory(new PropertyValueFactory<borrow, DatePicker>("Borrow_date"));
		returnDateColumn.setCellValueFactory(new PropertyValueFactory<borrow, DatePicker>("Return_date"));
		DueDateColumn.setCellValueFactory(new PropertyValueFactory<borrow, DatePicker>("Due_date"));
		Late_FeeColumn.setCellValueFactory(new PropertyValueFactory<borrow, Float>("Late_Fee"));
		

		
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date ,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
	        	System.out.println("sss");

				ResultSet rs = stmt.executeQuery(Query);
	        	System.out.println("ssss");

				tableView.setItems(getBorrow(rs));	
	        	System.out.println("sssdds");

				Query = "SELECT COUNT(employee.id)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
				rs = stmt.executeQuery(Query);
				while (rs.next()) {
					noOfOpLabel.setText(rs.getString(1));
				}
				con.close();
		} catch (Exception e) {
				System.out.println(e);
			}
		
	}
	
    @FXML
    void printinvoice(ActionEvent event) {

		try {
			    // check the table's selected item and get selected item
			    if (tableView.getSelectionModel().getSelectedItem() != null) {
					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("invoice.fxml"));
					
					Parent root = null;
					root = loader.load();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Edit Stage");
				//	System.out.println(	((borrow)loader.getController()).getMemberid());
					borrow selectedmaintenanc = tableView.getSelectionModel().getSelectedItem();
					invoiceController ic=((invoiceController)loader.getController());
					ic.getBookTitle().setText(String.valueOf(selectedmaintenanc.getBookTitle()));
					ic.getDueDate().setText(String.valueOf(selectedmaintenanc.getDue_date()));
					ic.getBorrowSn().setText(String.valueOf(selectedmaintenanc.getBorrow_SN()));
					ic.getSellerName().setText(String.valueOf(selectedmaintenanc.getEmpName()));
					ic.getBorrowDate().setText(String.valueOf(selectedmaintenanc.getBorrow_date()));
					ic.getMemberName().setText(String.valueOf(selectedmaintenanc.getMemberName()));
					ic.getSellerName().setText(String.valueOf(selectedmaintenanc.getEmpName()));
					ic.getMemberID().setText(String.valueOf(selectedmaintenanc.getMemberid()));

					if(selectedmaintenanc.getReturn_date()==null) {
						ic.getF().setText("");

						ic.getReturnDate().setText("Not Retund");
					}else {
						ic.getReturnDate().setText(String.valueOf(selectedmaintenanc.getReturn_date()));

					if(selectedmaintenanc.getLate_Fee()==null)
						ic.getF().setText("");
					else
						ic.getFee().setText(String.valueOf(selectedmaintenanc.getLate_Fee()));


					}



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
	
	public ObservableList<borrow> getBorrow(ResultSet rs)    { 
	System.out.println("ss");

    
        ObservableList<borrow> borrows = FXCollections.observableArrayList();
        try {
        	System.out.println("sss");

			while (rs.next()) {
				System.out.println( rs.getInt(11));
				
				borrows.add(new borrow(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6), rs.getDate(7).toLocalDate(),(rs.getDate(8)==null) ? null : rs.getDate(8).toLocalDate(),rs.getDate(9).toLocalDate(),rs.getFloat(10),rs.getInt(11)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
        return borrows;
    }
	
	@FXML
	private Button returnButton;
	@FXML
	private Button returnBookButton;
	
	
	public void toMainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("Sample.fxml");
		m.changeSize(701, 507);
	}
	
	public void toBorrowOp(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("borrowOp.fxml");
	}
	
	public void toSellOp(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("sellOp.fxml");
	}
	
	public void returnBook(ActionEvent event) throws IOException{
		Main m = new Main();
		borrow br = tableView.getSelectionModel().getSelectedItem();
		if(br == null) {
			wrongUpdate.setText("Select a Book.");
		}else {
			int i =br.getBookid();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				String query = "DELETE FROM borrow WHERE book_id = ?";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, Integer.toString(i));
			    st.executeUpdate();
			    query = "UPDATE book SET quantity=quantity+1 WHERE book_id = ?";
			    st = con.prepareStatement(query);
			    st.setString(1, Integer.toString(i));
			    st.executeUpdate();
			    st.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			m.changeScene("borrowTable.fxml");
		}
	}
	
    @FXML
    void notReternd(ActionEvent event) {
    	
    	if (notReternd.isSelected()== true) {
			String sql = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
					+ "FROM borrow\n"
					+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
					+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
					+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
					+ "WHERE borrow.Return_date is NULL;";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<borrow> list = getBorrow(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(borrow.Borrow_date)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
						+ "WHERE borrow.Return_date is NULL;";
				rs = stmt.executeQuery(sql);
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
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBorrow(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
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
	
	public void searchEmployeeId(ActionEvent event) throws ClassNotFoundException, IOException{
    	BorrowSNText1.setText("");

		membIdText.setText("");
		bookIdText.setText("");
		borrow_date.setValue(null);
		return_date.setValue(null);
		if (empIdText.getText().toString() != "") {
			String sql = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
					+ "FROM borrow\n"
					+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
					+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
					+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
					+ "WHERE borrow.emp_id = " + empIdText.getText().toString()+";";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<borrow> list = getBorrow(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(employee.id)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
						+ "WHERE borrow.emp_id = " + empIdText.getText().toString()+";";
				rs = stmt.executeQuery(sql);
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
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBorrow(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
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
    	BorrowSNText1.setText("");

		membIdText.setText("");
		empIdText.setText("");
		borrow_date.setValue(null);
		return_date.setValue(null);
		if (bookIdText.getText().toString() != "") {
			String sql = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
					+ "FROM borrow\n"
					+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
					+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
					+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
					+ "WHERE borrow.book_id = " + bookIdText.getText().toString()+";";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<borrow> list = getBorrow(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
						+ "WHERE borrow.book_id = " + bookIdText.getText().toString()+";";
				rs = stmt.executeQuery(sql);
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
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBorrow(rs));
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
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
    	BorrowSNText1.setText("");

		bookIdText.setText("");
		empIdText.setText("");
		borrow_date.setValue(null);
		return_date.setValue(null);
		if (membIdText.getText().toString() != "") {
			String sql = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
					+ "FROM borrow\n"
					+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
					+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
					+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
					+ "WHERE borrow.member_id = " + membIdText.getText().toString()+";";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<borrow> list = getBorrow(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(member.member_id)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
						+ "WHERE borrow.member_id = " + membIdText.getText().toString()+";";
				rs = stmt.executeQuery(sql);
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
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBorrow(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
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
	public void searchBorrowDate(ActionEvent event) throws ClassNotFoundException, IOException{
    	BorrowSNText1.setText("");
		bookIdText.setText("");
		empIdText.setText("");
		membIdText.setText("");
		//return_date.setValue(null);
		if (borrow_date.getValue() != null) {
			String sql = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
					+ "FROM borrow\n"
					+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
					+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
					+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
					+ "WHERE borrow.borrow_date = '" + borrow_date.getValue().toString()+"';";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<borrow> list = getBorrow(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(borrow.Borrow_date)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
						+ "WHERE borrow.borrow_date = '" + borrow_date.getValue().toString()+"';";
				rs = stmt.executeQuery(sql);
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
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBorrow(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
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
    @FXML
    void searchBorrow_SN(ActionEvent event) {
    	bookIdText.setText("");
    	membIdText.setText("");
		empIdText.setText("");
		borrow_date.setValue(null);
		return_date.setValue(null);
		if (BorrowSNText1.getText().toString() != "") {
			String sql = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
					+ "FROM borrow\n"
					+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
					+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
					+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
					+ "WHERE borrow.Borrow_SN =" + BorrowSNText1.getText().toString()+";";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<borrow> list = getBorrow(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
						+ "WHERE borrow.Borrow_SN =" + BorrowSNText1.getText().toString()+";";
				rs = stmt.executeQuery(sql);
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
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBorrow(rs));
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
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
	public void searchReturnDate(ActionEvent event) throws ClassNotFoundException, IOException{
    	BorrowSNText1.setText("");
		bookIdText.setText("");
		empIdText.setText("");
		membIdText.setText("");
		borrow_date.setValue(null);
		if (return_date.getValue() != null) {
			String sql = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
					+ "FROM borrow\n"
					+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
					+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
					+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
					+ "WHERE borrow.Due_date = '" + return_date.getValue().toString()+"';";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<borrow> list = getBorrow(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(employee.id)\n"
						+ "FROM borrow\n"
						+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
						+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
						+ "INNER JOIN member ON member.member_id = borrow.Member_id\n"
						+ "WHERE borrow.Due_date = '" + return_date.getValue().toString()+"';";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					noOfOpLabel.setText(rs.getString(1));
				}
			}catch(Exception e) {
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else{
			 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					String Query = "SELECT employee.id, employee.name, book.Book_ID, book.Title, member.member_id, member.name, borrow.Borrow_date, borrow.Return_date,borrow.Due_date, borrow.Late_Fee,borrow.Borrow_SN\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBorrow(rs));	
					Query = "SELECT COUNT(employee.id)\n"
							+ "FROM borrow\n"
							+ "INNER JOIN book ON book.Book_ID=borrow.Book_id\n"
							+ "INNER JOIN employee ON employee.id = borrow.emp_id\n"
							+ "INNER JOIN member ON member.member_id = borrow.Member_id;";
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
	
	public void returnBorrowBook(ActionEvent event) throws ClassNotFoundException, IOException, SQLException{
		Main m = new Main();
		LocalDate today = LocalDate.now();
		borrow br = tableView.getSelectionModel().getSelectedItem();
		if(br == null) {
			wrongUpdate.setText("Select a Book.");
		}else {
			if(today.isAfter(br.getDue_date())) {
		        try {
		        	System.out.println(today.toString());
		        	
		        	long noOfDaysBetween = ChronoUnit.DAYS.between(br.getDue_date(), today);
		            double amountMoney = noOfDaysBetween/7;
		    		String warning = "Member have been late about " + noOfDaysBetween + " days, Member have to pay " + amountMoney + " ILS";
		    		Data.warningDays = String.valueOf(warning);
		    		
		    		Class.forName("com.mysql.cj.jdbc.Driver");
		        	System.out.println(today.toString());

					Connection con=  new Connect().getCon();

					String query = "UPDATE borrow set Return_date ='"+today.toString()+"',Late_Fee ="+amountMoney+"WHERE Borrow_SN = "+br.getBorrow_SN()+";";

					PreparedStatement st = con.prepareStatement(query);


				    st.executeUpdate();
				    st.close();
					con.close();
					m.changeScene("money.fxml"); 
		        }catch (IOException e) {
		            e.printStackTrace();
		        }
			}else if(today.isBefore(br.getDue_date()) || today.isEqual(br.getDue_date())) {
				int i = br.getBookid();
				int j = br.getMemberid();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=  new Connect().getCon();
					String query = "UPDATE borrow set Return_date ='"+today.toString()+"',Late_Fee = 0.0"+"WHERE Borrow_SN = "+br.getBorrow_SN()+";";
					PreparedStatement st = con.prepareStatement(query);

				    st.executeUpdate();
				  //  m.changeScene("borrowTable.fxml");
				    st.close();
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
		}
	}
}
