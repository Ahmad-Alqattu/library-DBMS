package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class invoiceController {

    @FXML
    private Label BorrowSn;
	@FXML
    private Label DueDate;

    @FXML
    private Label MemberID;

    @FXML
    private Label MemberName;

    @FXML
    private Label SellerName;

    @FXML
    private Label bookTitle;

    @FXML
    private Label borrowDate;

    @FXML
    private Label fee;

    @FXML
    private Label f;

    @FXML
    private Label r;
    
    public Label getF() {
		return f;
	}

	public void setF(Label f) {
		this.f = f;
	}

	public Label getR() {
		return r;
	}

	public void setR(Label r) {
		this.r = r;
	}

	public Label getBorrowSn() {
		return BorrowSn;
	}

	public void setBorrowSn(Label borrowSn) {
		BorrowSn = borrowSn;
	}

	@FXML
    private Label returnDate;

	public Label getDueDate() {
		return DueDate;
	}

	public void setDueDate(Label dueDate) {
		DueDate = dueDate;
	}

	public Label getMemberID() {
		return MemberID;
	}

	public void setMemberID(Label memberID) {
		MemberID = memberID;
	}

	public Label getMemberName() {
		return MemberName;
	}

	public void setMemberName(Label memberName) {
		MemberName = memberName;
	}

	public Label getSellerName() {
		return SellerName;
	}

	public void setSellerName(Label sellerName) {
		SellerName = sellerName;
	}

	public Label getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(Label bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Label getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Label borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Label getFee() {
		return fee;
	}

	public void setFee(Label fee) {
		this.fee = fee;
	}

	public Label getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Label returnDate) {
		this.returnDate = returnDate;
	}

    
    
    
    
    
}
