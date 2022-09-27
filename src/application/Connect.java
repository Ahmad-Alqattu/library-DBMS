package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {
	private String dbURL;
	private String dbUsername = "root";
	private String dbPassword = "ahmad.1luay.1";
	private String URL = "127.0.0.1";
	private String port = "3306";
	private String dbName = "mydb";
	private Connection con;
	
	public Connection getCon() throws ClassNotFoundException, SQLException {
			
			dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
			Properties p = new Properties();
			p.setProperty("user", dbUsername);
			p.setProperty("password", dbPassword);
			p.setProperty("useSSL", "true");
			p.setProperty("autoReconnect", "true");
			Class.forName("com.mysql.jdbc.Driver");
		
			return con = DriverManager.getConnection (dbURL, p);

		
	}
	public void setCon(Connection con) {
		this.con = con;
	}

}
