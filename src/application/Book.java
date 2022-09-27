package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private Float buying_price,selling_price;
	private SimpleStringProperty title, author, category, pubName;
	private IntegerProperty id, quantity,locker_id;
	
	public Book(int id, String title,String author,float selling_price, int quantity, String pubName,float buying_price, String category, int locker_id) {
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.category = new SimpleStringProperty(category);
		this.selling_price =selling_price;
		this.buying_price =buying_price;
		this.locker_id = new SimpleIntegerProperty(locker_id);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.pubName = new SimpleStringProperty(pubName);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(SimpleStringProperty title) {
		this.title = title;
	}

	public String getAuthor() {
		return author.get();
	}

	public void setAuthor(SimpleStringProperty author) {
		this.author = author;
	}

	public String getCategory() {
		return category.get();
	}

	public void setCategory(SimpleStringProperty category) {
		this.category = category;
	}

	public int getId() {
		return id.get();
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}



	public int getQuantity() {
		return quantity.get();
	}

	public void setQuantity(IntegerProperty quantity) {
		this.quantity = quantity;
	}

	public String getPubName() {
		return pubName.get();
	}

	public void setPubName(SimpleStringProperty pubName) {
		this.pubName = pubName;
	}

	public float getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(Float selling_price) {
		this.selling_price = selling_price;
	}

	public float getBuying_price() {
		return buying_price;
	}

	public void setBuying_price(Float buying_price) {
		this.buying_price = buying_price;
	}

	public int getLocker_id() {
		return locker_id.get();
	}

	public void setLocker_id(IntegerProperty locker_id) {
		this.locker_id = locker_id;
	}


	
	
	
	
	
}