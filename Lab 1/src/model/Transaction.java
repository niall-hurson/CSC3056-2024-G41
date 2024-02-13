package model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaction {
	String account_number;
	double transaction_amount;
	Date transaction_date;
	
	
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public double getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	
	public Transaction(String account_number, double transaction_amount, Date transaction_date) {
		super();
		this.account_number = account_number;
		this.transaction_amount = transaction_amount;
		this.transaction_date = transaction_date;
	}
	
	public String toString() {
		return account_number + ", " + transaction_amount + ", " + transaction_date;
	}

}
