package models;

public class person {
	public person(int id,String name, String address, String email, String telephone, int type) {
		super();
		this.id=id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.type = type;
	}
	private String name;
	private String address;
	private String email;
	private String telephone;
	private int type;
	private int id;
	//private ArrayList<Integer> actions;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
