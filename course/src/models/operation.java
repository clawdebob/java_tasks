package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class operation implements Comparable<operation>{
	public operation(int person, int product, int quantity, int id, Date date,int type) {
		super();
		this.person = person;
		this.product = product;
		this.quantity = quantity;
		this.id = id;
		this.date = date;
		this.setType(type);
	}
	public transient DateFormat format = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a", Locale.ENGLISH);
	private int person;
	private int product;
	private int quantity;
	private int id;
	private int type;
	private Date date;
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int compareTo(operation arg0) {
		return getDate().compareTo(arg0.getDate());
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
