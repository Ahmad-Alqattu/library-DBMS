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

public class bookController {
	
	@FXML
	private TableView<Book> tableView;
	@FXML
	private TableColumn<Book, Integer> idColumn;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, String> authorColumn;
	@FXML
	private TableColumn<Book, String> categoryColumn;
	@FXML
	private TableColumn<Book, Integer> quantityColumn;
	@FXML
	private TableColumn<Book, String> pubNameColumn;
	@FXML
    private TextField titleText;
	@FXML
    private TextField idText;
	@FXML
	private ComboBox<String> authorList;
	@FXML
	private ComboBox<String> categoryList;
	@FXML
	private ComboBox<String> publisherList;
	  @FXML
	    private TableColumn<Book, Float> buying_price;

	    @FXML
	    private TableColumn<Book, Integer> locker_id;

	    @FXML
	    private TableColumn<Book, Float> selling_price;
	
	@FXML
	private Label noOfItemsLabel;
	
	@FXML
	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Category"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("Quantity"));
		pubNameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("pubName"));
		locker_id.setCellValueFactory(new PropertyValueFactory<Book, Integer>("locker_id"));
		buying_price.setCellValueFactory(new PropertyValueFactory<Book, Float>("buying_price"));
		selling_price.setCellValueFactory(new PropertyValueFactory<Book, Float>("selling_price"));
System.out.println("sd");

		BindCombo();
		System.out.println("ssd");

		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				String Query = "SELECT book.Book_ID, book.Title, book.author, book.selling_price, book.quantity, publisher.name, book.buying_price,Category.category_name,Category.locker_id\n"
						+ "FROM book\n"
						+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
						+ "INNER JOIN Category ON book.Category_id=Category.Category_id ;";
				ResultSet rs = stmt.executeQuery(Query);
				tableView.setItems(getBooks(rs));	
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
		
	}
	
	public ObservableList<Book> getBooks(ResultSet rs)
    {     
        ObservableList<Book> books = FXCollections.observableArrayList();
        try {
			while (rs.next()) {
				books.add(new Book(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getFloat(4),rs.getInt(5),rs.getString(6), rs.getFloat(7),rs.getString(8),rs.getInt(9)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
        return books;
    }
	
	public void BindCombo() {
		try {
			categoryList.getItems().add(".....");
			authorList.getItems().add(".....");
			publisherList.getItems().add(".....");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = new Connect().getCon(); 
			Statement stmt = con.createStatement();
			String Query = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id ;";
			ResultSet rs = stmt.executeQuery(Query);
			while (rs.next()) {
				if(!categoryList.getItems().contains(rs.getString(8))) {
					categoryList.getItems().add(rs.getString(8));
				}
				if(!authorList.getItems().contains(rs.getString(3))) {
					authorList.getItems().add(rs.getString(3));
				}
				if(!publisherList.getItems().contains(rs.getString(6))) {
					publisherList.getItems().add(rs.getString(6));
				}
			}
			Query = "SELECT COUNT(book.Book_ID) from book";
			rs = stmt.executeQuery(Query);
			while (rs.next()) {
				noOfItemsLabel.setText(rs.getString(1));
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
	private Button borrowButton;
	@FXML
	private Button sellButton;
	@FXML
	private Button updateButton;
	@FXML
	private Label wrongUpdate;
	
	
	
	public void toMainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(701, 507);
		m.changeScene("Sample.fxml");
		
	}
	
	public void toBorrowOp(ActionEvent event) throws IOException{
		Main m = new Main();	
		Book book = tableView.getSelectionModel().getSelectedItem();
		if(book == null) {
			wrongUpdate.setText("Select a Book.");
		}else {
			Data.oldBookId = book.getId();
			m.changeSize(605, 440);
			m.changeScene("borrowOp.fxml");
		}
	}
	
	public void toSellOp(ActionEvent event) throws IOException{
		Main m = new Main();
		Book book = tableView.getSelectionModel().getSelectedItem();
		if(book == null) {
			wrongUpdate.setText("Select a Book.");
		}else {
			Data.oldBookId = book.getId();
			m.changeSize(605, 440);
			m.changeScene("buyOp.fxml");
		}
	}
	
	public void toAddMenu(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(605, 440);
		m.changeScene("addBook.fxml");
	}
	
	public void toUpdateMenu(ActionEvent event) throws IOException{
		try {
		Main m = new Main();
		Book book = tableView.getSelectionModel().getSelectedItem();
		if(book == null) {
			wrongUpdate.setText("Select a Book to update.");
		}else {
			Data.oldBookId = book.getId();
			Data.oldBookTitle = book.getTitle();
			Data.oldBookAuthor = book.getAuthor();
			Data.oldBookCategory = book.getCategory();
			//Data.oldBookPrice = book.getPrice();
			Data.oldBookQuantity = book.getQuantity();
			Data.oldBookpubName = book.getPubName();
			m.changeSize(605, 440);
			m.changeScene("updateBook.fxml");
		}
		}catch (Exception e) {
e.printStackTrace();		}
	}
	
	public void deleteBook(ActionEvent event) throws IOException{
		Main m = new Main();
		Book book = tableView.getSelectionModel().getSelectedItem();
		if(book == null) {
			wrongUpdate.setText("Select a Book to delete.");
		}else {
			int i =book.getId();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				String query = "DELETE FROM book WHERE book_id = ?";
				PreparedStatement st = con.prepareStatement(query);
			    st.setString(1, Integer.toString(i));
			    st.executeUpdate();
			    st.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			m.changeScene("BookTable.fxml");
		}
	}
	
	public void searchBookId(ActionEvent event) throws ClassNotFoundException, IOException{
		authorList.setValue(".....");
		categoryList.setValue(".....");
		titleText.setText("");
		publisherList.setValue(".....");
		if (idText.getText().toString() != "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where book_id = " + idText.getText().toString();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID)\n"
						+ "FROM book\n"
						+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
						+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
						+ "where book_id = " + idText.getText().toString();
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
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
					String Query = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
							+ "FROM book\n"
							+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
							+ "INNER JOIN Category ON book.Category_id=Category.Category_id ;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBooks(rs));	
					Query = "SELECT COUNT(book.Book_ID)\n"
							+ "FROM book\n"
							+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  \n";
						rs = stmt.executeQuery(Query);
						while (rs.next()) {
							noOfItemsLabel.setText(rs.getString(1));
						}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	public void searchBookTitle(ActionEvent event) throws ClassNotFoundException, IOException{
		if (titleText.getText().toString() != "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id where title like '" + titleText.getText().toString() +"%'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
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
					String Query = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
							+ "FROM book\n"
							+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
							+ "INNER JOIN Category ON book.Category_id=Category.Category_id ;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBooks(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	@FXML
	public void searchBookPublisher(ActionEvent event) throws ClassNotFoundException, IOException{
		System.out.println("gg");

		authorList.setValue(".....");
		categoryList.setValue(".....");
		titleText.setText("");
		idText.setText("");
		if (publisherList.getValue().toString() != ".....") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where publisher.name = '" + publisherList.getValue().toString()+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
System.out.println(publisherList.getValue().toString());
				 	sql = "SELECT COUNT(book.Book_ID) from book "	
								+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
								+ "where publisher.name = '" + publisherList.getValue().toString()+"'";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
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
					String Query = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
							+ "FROM book\n"
							+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
							+ "INNER JOIN Category ON book.Category_id=Category.Category_id; ";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBooks(rs));	
					Query = "SELECT COUNT(book.Book_ID) from book";
						rs = stmt.executeQuery(Query);
						while (rs.next()) {
							noOfItemsLabel.setText(rs.getString(1));
						}
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}
		}
	}
	
	public void searchBookAuthor(ActionEvent event) throws ClassNotFoundException, IOException{
		if (authorList.getValue().toString() != "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where author = '" + authorList.getValue().toString()+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
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
					String Query = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
							+ "FROM book\n"
							+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
							+ "INNER JOIN Category ON book.Category_id=Category.Category_id ;";
					ResultSet rs = stmt.executeQuery(Query);
					tableView.setItems(getBooks(rs));	
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
		}
	}
	public void search(ActionEvent event) throws ClassNotFoundException, IOException{
		String authorValue=authorList.getValue();
		String categoryValue=categoryList.getValue();
		String titleValue=titleText.getText();
		if ((authorValue != "....." && authorValue != null) && (categoryValue != "....." && categoryValue != null)  && titleValue != "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where author = '" + authorList.getValue().toString()+"' and "
					+ "Category.category_name = '" + categoryList.getValue().toString() + "' and "
					+ "title like '" + titleText.getText().toString() +"%'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID) from book\n"
					+ "where author = '" + authorList.getValue().toString()+"' and "
					+ "Category.category_name = '" + categoryList.getValue().toString() + "' and "
					+ "title like '" + titleText.getText().toString() +"%'";;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					noOfItemsLabel.setText(rs.getString(1));
				}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else if ((authorValue != "....." && authorValue != null) && (categoryValue != "....." && categoryValue != null) && titleValue == "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where author = '" + authorList.getValue().toString()+"' and "
					+ "category = '" + categoryList.getValue().toString() + "';";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID) from book\n"
								+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
								+ "INNER JOIN Category ON book.Category_id=Category.Category_id "						+ "where author = '" + authorList.getValue().toString()+"' and "
						+ "Category.category_name = '" + categoryList.getValue().toString() + "';";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
					}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else if ((authorValue != "....." && authorValue != null) && (categoryValue == "....." || categoryValue == null) && titleValue != "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where author = '" + authorList.getValue().toString()+"' and "
					+ "title like '" + titleText.getText().toString() +"%'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID) from book\n"
						+ "where author = '" + authorList.getValue().toString()+"' and "
						+ "title like '" + titleText.getText().toString() +"%'";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
					}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}
		else if ((authorValue != "....." && authorValue != null) && (categoryValue == "....." || categoryValue == null) && titleValue == "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where author = '" + authorList.getValue().toString()+"'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID) from book\n"
						+ "where author = '" + authorList.getValue().toString()+"'";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
					}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else if ((authorValue == "....." || authorValue == null) && (categoryValue != "....." && categoryValue != null)  && titleValue != "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where category_name = '" + categoryList.getValue().toString() + "' and "
					+ "title like '" + titleText.getText().toString() +"%';";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID) from book\n"
						+ "INNER JOIN Category ON book.Category_id=Category.Category_id "

						+ "where category_name = '" + categoryList.getValue().toString() + "' and "
						+ "title like '" + titleText.getText().toString() +"%';";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
					}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else if ((authorValue == "....." || authorValue == null) && (categoryValue != "....." && categoryValue != null) && titleValue == "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where category_name = '" + categoryList.getValue().toString() + "'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID) from book\n"
						+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
						+ "where category_name = '" + categoryList.getValue().toString() + "'";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
					}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}else if ((authorValue == "....." || authorValue == null) && (categoryValue == "....." || categoryValue == null) && titleValue != "") {
			String sql = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id "
					+ "where title like '" + titleText.getText().toString() +"%'";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				sql = "SELECT COUNT(book.Book_ID) from book\n"
						+ "where title like '" + titleText.getText().toString() +"%'";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						noOfItemsLabel.setText(rs.getString(1));
					}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}
		else if ((authorValue == "....." || authorValue == null) && (categoryValue == "....." || categoryValue == null) && titleValue == "") {
			String Query = "SELECT book.Book_ID, book.Title, book.author,book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id\n"
					+ "FROM book\n"
					+ "INNER JOIN publisher ON book.pub_id=publisher.pub_id  "
					+ "INNER JOIN Category ON book.Category_id=Category.Category_id ;";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(Query);
				ObservableList<Book> list = getBooks(rs);
				tableView.setItems(list);
				Query = "SELECT COUNT(book.Book_ID) from book";
				rs = stmt.executeQuery(Query);
				while (rs.next()) {
					noOfItemsLabel.setText(rs.getString(1));
				}
			}catch(Exception e){
				System.out.println("Error ocured while searching the record " + e);
				e.printStackTrace();
			}
		}
	}
}