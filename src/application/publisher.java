package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class publisher {
	private SimpleStringProperty name, address, phone, email;
	private IntegerProperty id;
	
	public publisher(int id, String name,String address, String phone, String email) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
	}

	public String getName() {
		return name.get();
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(SimpleStringProperty phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}

	public int getId() {
		return id.get();
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}
	
}
