package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;

public class member {
	private SimpleStringProperty name, gender, phone, address;
	private IntegerProperty id;
	private ObjectProperty<LocalDate> birthdate, start_date;
	
	public member(int id, String name,String gender, String phone, String address, LocalDate birthdate, LocalDate start_date) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.gender = new SimpleStringProperty(gender);
		this.phone = new SimpleStringProperty(phone);
		this.address = new SimpleStringProperty(address);
		this.birthdate= new SimpleObjectProperty<>(birthdate);
		this.start_date = new SimpleObjectProperty<>(start_date);
	}

	public String getName() {
		return name.get();
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(SimpleStringProperty gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(SimpleStringProperty phone) {
		this.phone = phone;
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

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public LocalDate getBirthdate() {
		return birthdate.get();
	}

	public void setBirthdate(ObjectProperty<LocalDate> birthdate) {
		this.birthdate = birthdate;
	}

	public LocalDate getStart_date() {
		return start_date.get();
	}

	public void setStart_date(ObjectProperty<LocalDate> start_date) {
		this.start_date = start_date;
	}
	
}