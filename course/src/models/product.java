package models;

public class product {
	public product(int id, String name, int type, int height, String time, int width, int status, boolean available,
			int st_time, int quantity, int room, int desk) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.height = height;
		this.time = time;
		this.width = width;
		this.status = status;
		this.available = available;
		this.st_time = st_time;
		this.quantity = quantity;
		this.room = room;
		this.desk = desk;
	}
	private int id;
	private String name;
	private int type;
	private int height;
	private String time;
	private int width;
	private int status;
	private boolean available;
	private int st_time;
	private int quantity;
	private int room;
	private int desk;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public int getSt_time() {
		return st_time;
	}
	public void setSt_time(int st_time) {
		this.st_time = st_time;
	}
	public int getQuantyty() {
		return quantity;
	}
	public void setQuantyty(int quantyty) {
		this.quantity = quantyty;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public int getDesk() {
		return desk;
	}
	public void setDesk(int desk) {
		this.desk = desk;
	}
	
}
