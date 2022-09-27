package application;

import java.io.IOException;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class borrowOpController {
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
	private DatePicker borrowDate;
	@FXML
	private DatePicker returnDate;
	@FXML
	private Label wrongInput;

	@FXML
	public void initialize() {
		borrowDate.setValue(LocalDate.now());
		empIdLabel.setText(Data.text);
		bookIdLabel.setText(String.valueOf(Data.oldBookId));
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		      @Override
		      public DateCell call(final DatePicker datePicker) {
		        return new DateCell() {
		          @Override
		          public void updateItem(LocalDate item, boolean empty) {
		            super.updateItem(item, empty);

		            long p = ChronoUnit.DAYS.between(borrowDate.getValue(), item);
		            setTooltip(new Tooltip("You'll borrow this book for " + p + " days"));
		          }
		        };
		      }
		    };
		    returnDate.setDayCellFactory(dayCellFactory);
	}
	
	public void toBookTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeSize(1090,700);
		m.changeScene("BookTable.fxml");
	}
	

	
	public void successAdd(ActionEvent event) throws IOException{
		Main m = new Main();
		int borrowedOperations=0;
		boolean isThereQuantity=false;
		boolean isThereABook=true;
		if(empIdLabel.getText().toString().isEmpty() || bookIdLabel.getText().toString().isEmpty() || memberIdText.getText().toString().isEmpty()
				|| borrowDate==null || returnDate==null) {
			wrongInput.setText("You must fill all data");
		}else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
				String query = "Select * from borrow Where Member_id=?";
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, memberIdText.getText().toString());
				ResultSet rs  = st.executeQuery();
				while (rs.next()) {
					if (rs.getInt(3)==Integer.parseInt(memberIdText.getText().toString())) {
						borrowedOperations++;
					}
				}
				query = "Select * from book Where book_id=?";
				st = con.prepareStatement(query);
				st.setString(1, bookIdLabel.getText().toString());
				rs  = st.executeQuery();
				while (rs.next()) {
					if (rs.getInt(7)>0) {
						isThereQuantity = true;
					}
				}
				if(isThereABook) {
					if (isThereQuantity) {
						if (borrowedOperations>5) {
							wrongInput.setText("This member can't borrow more books");
						}else {
							if(borrowDate.getValue().isBefore(returnDate.getValue())) {
								query = "INSERT INTO borrow (emp_id, Book_id, Member_id, Borrow_date, Due_date,late_fee) VALUES (?,?,?,?,?,0.0)";
								st = con.prepareStatement(query);
							    st.setString(1, empIdLabel.getText().toString());
							    st.setString(2, bookIdLabel.getText().toString());
							    st.setString(3, memberIdText.getText().toString());
							    st.setString(4, borrowDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							    st.setString(5, returnDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
							    st.executeUpdate();
							    query = "UPDATE book SET quantity=quantity-1 WHERE book_id = ?";
							    st = con.prepareStatement(query);
							    st.setString(1, bookIdLabel.getText().toString());
							    st.executeUpdate();
								m.changeSize(1090,700);
							    m.changeScene("BookTable.fxml");
						    }else {
								wrongInput.setText("Borrow date cannot be after Return date");
							}
					    }
					    st.close();
						con.close();
						Stage stage = new Stage();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("invoice.fxml"));
						
						Parent root = null;
						root = loader.load();
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.setTitle("Edit Stage");
					//	System.out.println(	((borrow)loader.getController()).getMemberid());
			



						stage.show();
						
						
						
					}else{
						wrongInput.setText("There is no quantity of That book");
					}
				}else{
					wrongInput.setText("There is no book with that ID");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				wrongInput.setText("There is no member with that ID.");
			}
		}	
	}
	
	 void printinvoice(ActionEvent event) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 

//				String query = "select from  borrow where emp_id=? Book_id=?, Member_id=?, Borrow_date=?, Due_date=?";
//				PreparedStatement	st = con.prepareStatement(query);
//			    st.setString(1, empIdLabel.getText().toString());
//			    st.setString(2, bookIdLabel.getText().toString());
//			    st.setString(3, memberIdText.getText().toString());
//			    st.setString(4, borrowDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//			    st.setString(5, returnDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//			    st.executeUpdate();
//			    st = con.prepareStatement(query);
//			    st.setString(1, bookIdLabel.getText().toString());
//			    st.execute();
//				    // check the table's selected item and get selected item
//				    if (tableView.getSelectionModel().getSelectedItem() != null) {
//						Stage stage = new Stage();
//						FXMLLoader loader = new FXMLLoader(getClass().getResource("buyinvoice.fxml"));
//						
//						Parent root = null;
//						root = loader.load();
//						Scene scene = new Scene(root);
//						stage.setScene(scene);
//						stage.setTitle("Edit Stage");
//					//	System.out.println(	((borrow)loader.getController()).getMemberid());
//						buy selectedmaintenanc = tableView.getSelectionModel().getSelectedItem();
//						buyinvoicec ic=((buyinvoicec)loader.getController());
//						ic.getBookTitle_Data().setText(String.valueOf(selectedmaintenanc.getBookTitle()));
//						ic.getDate().setText(String.valueOf(selectedmaintenanc.getDate()));
//						ic.getAmount_Paid().setText(String.valueOf(selectedmaintenanc.getPrice()));
//						ic.getEmployeeID().setText(String.valueOf(selectedmaintenanc.getEmpid()));
//						ic.getDiscount_Data().setText(String.valueOf(selectedmaintenanc.getdes()));
//						ic.getMemberName_Data().setText(String.valueOf(selectedmaintenanc.getMemberName()));
//						ic.getBookPrice_Data().setText(String.valueOf(selectedmaintenanc.getSelling_price()));
//						ic.getMemberID_Data().setText(String.valueOf(selectedmaintenanc.getMemberid()));
//						ic.getBookID_Data().setText(String.valueOf(selectedmaintenanc.getBookid()));
//
//						stage.show();
//				    }else {
//						wrongUpdate.setText("Select opration");
//				    }
			} catch (Exception e) {
				e.printStackTrace();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Edit Start Error");
				alert.show();
			}
	    }
}
