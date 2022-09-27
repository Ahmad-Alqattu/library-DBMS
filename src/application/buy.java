package application;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;

public class buy {
	private Float price;
	private IntegerProperty empid, bookid, memberid ;
	private SimpleStringProperty empName, bookTitle, memberName;
	private ObjectProperty<LocalDate> date;
	Float pr;
	int des;
	
	public buy(int empid, String empName, int bookid, String bookTitle,int memberid, String memberName, LocalDate date ,int des,Float pr ) {
		this.price= (float) ((pr*(-(des/100.0)+1)));
System.out.println(((pr*(-(des/100)+1))));
		this.empid = new SimpleIntegerProperty(empid);
		this.empName = new SimpleStringProperty(empName);
		this.bookid = new SimpleIntegerProperty(bookid);
		this.bookTitle = new SimpleStringProperty(bookTitle);
		this.memberid = new SimpleIntegerProperty(memberid);
		this.memberName = new SimpleStringProperty(memberName);
		this.date= new SimpleObjectProperty<>(date);
		this.pr=pr;
		this.des=des;
	}
	
	
	public Float getSelling_price() {
		return pr;
	}

	public int getdes(){
		return des;
	}

	public int getEmpid() {
		return empid.get();
	}

	public void setEmpid(IntegerProperty empid) {
		this.empid = empid;
	}

	public int getBookid() {
		return bookid.get();
	}

	public void setBookid(IntegerProperty bookid) {
		this.bookid = bookid;
	}

	public int getMemberid() {
		return memberid.get();
	}

	public void setMemberid(IntegerProperty memberid) {
		this.memberid = memberid;
	}

	public LocalDate getDate() {
		return date.get();
	}

	public void setDate(ObjectProperty<LocalDate> date) {
		this.date = date;
	}

	public String getEmpName() {
		return empName.get();
	}

	public void setEmpName(SimpleStringProperty empName) {
		this.empName = empName;
	}

	public String getBookTitle() {
		return bookTitle.get();
	}

	public void setBookTitle(SimpleStringProperty bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getMemberName() {
		return memberName.get();
	}

	public void setMemberName(SimpleStringProperty memberName) {
		this.memberName = memberName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
}
