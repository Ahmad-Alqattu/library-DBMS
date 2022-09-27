package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReturnController implements Initializable {
    @FXML
    private Label Expenses;

    @FXML
    private DatePicker From;

    @FXML
    private DatePicker To;

    @FXML
    private Label loan;

    @FXML
    private Label Total;



    @FXML
    private Label sale;

    @FXML
    private AnchorPane sale_Income;

	public void toMainTable(ActionEvent event) throws IOException{
		Main m = new Main();
		m.changeScene("Sample.fxml");
		m.changeSize(701, 507);
	}
	
    @FXML
    void From(ActionEvent event) {

		if (From.getValue() != null ||To.getValue() != null) {
		if (From.getValue() != null &&To.getValue() == null) {
			String sql = "  select sum((selling_price * ((100 - Discount_percent) / 100))) \n"
					+ "  from book,buy b \n"
					+ "  where book.Book_ID=b.Book_ID and\n"
					+ " b.Date > '" + From.getValue().toString()+"';";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = new Connect().getCon(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					sale.setText(rs.getString(1));
				}

		 sql = "      select sum(late_fee) \n"
				+ "  from book,Borrow b \n"
				+ "  where book.Book_ID=b.Book_ID and\n"
				+ " b.Return_date > '" + From.getValue().toString()+"';";
		

			 stmt = con.createStatement();
			 rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString(1));loan.setText(rs.getString(1));
			}

			 sql = " select sum(buying_price * Quantity)\r\n"
					+ "  from book b where\n"
					+ " b.buy_date > '" + From.getValue().toString()+"';";
			

				 stmt = con.createStatement();
				 rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Expenses.setText(rs.getString(1));
				}
           
				double s= ((loan.getText()!=null)? Double.parseDouble(loan.getText()):0)+((sale.getText()!=null)? Double.parseDouble(sale.getText()):0)-((Expenses.getText()!=null)? Double.parseDouble(Expenses.getText()):0);
   				Total.setText(String.valueOf(s));
					con.close();
				} catch (Exception e1) {
					System.out.println(e1);
				}
		}else if (From.getValue() != null &&To.getValue() != null) {
				String sql = "  select sum((selling_price * ((100 - Discount_percent) / 100))) \n"
						+ "  from book,buy b \n"
						+ "  where book.Book_ID=b.Book_ID and\n"
						+ " b.Date BETWEEN  '" + From.getValue().toString()+"'"
								+ "and  '" + To.getValue().toString()+"';";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);

					while (rs.next()) {
						sale.setText(rs.getString(1));
					}

			 sql = "      select sum(late_fee) \n"
					+ "  from book,Borrow b \n"
					+ "  where book.Book_ID=b.Book_ID and\n"
					+ " b.Return_date BETWEEN  '" + From.getValue().toString()+"'"
							+ "and  '" + To.getValue().toString()+"';";
			

				 stmt = con.createStatement();
				 rs = stmt.executeQuery(sql);

				while (rs.next()) {
					System.out.println();
					System.out.println(rs.getString(1));
					loan.setText(rs.getString(1));
				}

				 sql = " select sum(buying_price * Quantity) \n"
						+ "  from book b "
						+ "where\n"
						+ " b.buy_date BETWEEN '" + From.getValue().toString()+"'"
								+ "and '" + To.getValue().toString()+"';";
				

					 stmt = con.createStatement();
					 rs = stmt.executeQuery(sql);

					while (rs.next()) {
						Expenses.setText(rs.getString(1));
					}

					double s= ((loan.getText()!=null)? Double.parseDouble(loan.getText()):0)+((sale.getText()!=null)? Double.parseDouble(sale.getText()):0)-((Expenses.getText()!=null)? Double.parseDouble(Expenses.getText()):0);
	   				Total.setText(String.valueOf(s));
						con.close();
					} catch (Exception e1) {
						System.out.println(e1);
					}
			
			
		}
		
		}
		
	}
    
    @FXML
    void To(ActionEvent event) {
    if (From.getValue() != null ||To.getValue() != null) {
		if (From.getValue() == null &&To.getValue() != null) {
			
				String sql = "  select sum((selling_price * ((100 - Discount_percent) / 100))) \n"
						+ "  from book,buy b\n"
						+ "  where book.Book_ID=b.Book_ID and\n"
						+ " b.Date < '" + To.getValue().toString()+"';";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = new Connect().getCon(); 
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);

					while (rs.next()) {
						sale.setText(rs.getString(1));
					}

			 sql = "      select sum(late_fee)\n"
					+ "  from book,Borrow b \n"
					+ "  where book.Book_ID=b.Book_ID and\n"
					+ " b.Return_date < '" + To.getValue().toString()+"';";
			

				 stmt = con.createStatement();
				 rs = stmt.executeQuery(sql);

				while (rs.next()) {
					System.out.println(rs.getString(1));
					loan.setText(rs.getString(1));
				}

				 sql = " select sum(buying_price * Quantity) \n"
						+ "  from book b \n"
						+ "where b.buy_date < '" + To.getValue().toString()+"' ;";
				

					 stmt = con.createStatement();
					 rs = stmt.executeQuery(sql);

					while (rs.next()) {
						Expenses.setText(rs.getString(1));
					}

						double s= ((loan.getText()!=null)? Double.parseDouble(loan.getText()):0)+((sale.getText()!=null)? Double.parseDouble(sale.getText()):0)-((Expenses.getText()!=null)? Double.parseDouble(Expenses.getText()):0);
	   				Total.setText(String.valueOf(s));
	   				System.out.println(s);
						con.close();
					} catch (Exception e1) {
						System.out.println(e1);
					}
			}else if (From.getValue() != null &&To.getValue() != null) {
					String sql = "  select sum((selling_price * ((100 - Discount_percent) / 100))) \n"
							+ "  from book,buy b\n"
							+ "  where book.Book_ID=b.Book_ID and\n"
							+ " b.Date BETWEEN  '" + From.getValue().toString()+"'"
									+ " and '" + To.getValue().toString()+"';";
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = new Connect().getCon(); 
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(sql);

						while (rs.next()) {
							sale.setText(rs.getString(1));
						}

				 sql = "      select sum(late_fee) \n"
						+ "  from book,Borrow b \n"
						+ "  where book.Book_ID=b.Book_ID and \n"
						+ " b.Return_date BETWEEN '" + From.getValue().toString()+"'"
								+ " and  '" + To.getValue().toString()+"';";
				

					 stmt = con.createStatement();
					 rs = stmt.executeQuery(sql);

					while (rs.next()) {
						System.out.println(rs.getString(1));
					
						loan.setText(rs.getString(1));
					}

					 sql = " select sum(buying_price * Quantity) \n"
							+ "  from book b "
							+ "where \n" 
							+ " b.buy_date BETWEEN '" + From.getValue().toString()
									+ "' and '" + To.getValue().toString()+"';";
					

						 stmt = con.createStatement();
						 rs = stmt.executeQuery(sql);

						while (rs.next()) {
							Expenses.setText(rs.getString(1));
						}
		            	   
						double s= ((loan.getText()!=null)? Double.parseDouble(loan.getText()):0)+((sale.getText()!=null)? Double.parseDouble(sale.getText()):0)-((Expenses.getText()!=null)? Double.parseDouble(Expenses.getText()):0);
		   				Total.setText(String.valueOf(s));
		   				System.out.println(s);

							con.close();
						} catch (Exception e1) {
							System.out.println(e1);
						}
		
		
		}
		}
}

	@Override
	public void initialize(URL location, ResourceBundle resources){
		

		
		

	
	

	}	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
