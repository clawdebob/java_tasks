package models;

public class operation {
	public operation(int person, int product, int quantity, int id, String date) {
		super();
		this.person = person;
		this.product = product;
		this.quantity = quantity;
		this.id = id;
		this.date = date;
	}
	private int person;
	private int product;
	private int quantity;
	private int id;
	private String date;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
