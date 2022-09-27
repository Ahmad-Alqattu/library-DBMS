package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
	private SimpleStringProperty Name, phone, address, password;
	private IntegerProperty id;
	
	public Employee(int id, String name,String phone, String address, String password) {
		this.Name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.id = new SimpleIntegerProperty(id);
		this.phone = new SimpleStringProperty(phone);
		this.password = new SimpleStringProperty(password);
	}
	
	public String getName() {
		return Name.get();
	}
	
	public void setName(SimpleStringProperty name) {
		Name = name;
	}
	
	public String getAddress() {
		return address.get();
	}
	
	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone.get();
	}
	
	public void setPhone(SimpleStringProperty phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(SimpleStringProperty password) {
		this.password = password;
	}
	
	
	
	
}
