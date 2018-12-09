package models;

public class characteristics {
	public characteristics(int height, int width,String time, int status, boolean available, int st_time,int quantity) {
		super();
		this.height = height;
		this.time = time;
		this.width = width;
		this.status = status;
		this.available = available;
		this.st_time = st_time;
		this.quantity = quantity;
	}
	public int height;
	public String time;
	public int width;
	public int status;
	public boolean available;
	public int st_time;
	public int quantity;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
