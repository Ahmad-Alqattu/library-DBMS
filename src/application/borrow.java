package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;

public class borrow {
	private IntegerProperty empid, bookid, memberid,Borrow_SN;
	private SimpleStringProperty empName, bookTitle, memberName;
	private ObjectProperty<LocalDate> borrow_date, return_date,Due_date;
	private Float Late_Fee;

	public borrow(int empid,String empName, int bookid,String bookTitle,int memberid,String memberName, LocalDate borrow_date, LocalDate return_date,LocalDate Due_date,Float late_fee,int Borrow_SN) {
		this.empid = new SimpleIntegerProperty(empid);
		this.empName = new SimpleStringProperty(empName);
		this.bookid = new SimpleIntegerProperty(bookid);
		this.bookTitle = new SimpleStringProperty(bookTitle);
		this.memberid = new SimpleIntegerProperty(memberid);
		this.memberName = new SimpleStringProperty(memberName);
		this.borrow_date= new SimpleObjectProperty<>(borrow_date);
		this.return_date = new SimpleObjectProperty<>(return_date);
		this.Due_date=new SimpleObjectProperty<>(Due_date);
		this.setLate_Fee(late_fee);
		this.Borrow_SN= new SimpleIntegerProperty(Borrow_SN);
		
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

	public LocalDate getBorrow_date() {
		return borrow_date.get();
	}

	public void setBorrow_date(ObjectProperty<LocalDate> borrow_date) {
		this.borrow_date = borrow_date;
	}

	public LocalDate getReturn_date() {
		return return_date.get();
	}

	public void setReturn_date(ObjectProperty<LocalDate> return_date) {
		this.return_date = return_date;
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

	public LocalDate getDue_date() {
		return Due_date.get();
	}

	public void setDue_date(ObjectProperty<LocalDate> due_date) {
		Due_date = due_date;
	}



	public Float getLate_Fee() {
		return Late_Fee;
	}

	public void setLate_Fee(Float late_Fee) {
		Late_Fee = late_Fee;
	}

	public int getBorrow_SN() {
		return Borrow_SN.get();
	}

	public void setBorrow_SN(IntegerProperty borrow_SN) {
		Borrow_SN = borrow_SN;
	}
	
}
