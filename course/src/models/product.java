package models;

public class product {
	public product( int id, String name, int type, int room, int desk,characteristics chars) {
		super();
		this.chars = chars;
		this.id = id;
		this.name = name;
		this.type = type;
		this.room = room;
		this.desk = desk;
	}
	private characteristics chars;
	private int id;
	private String name;
	private int type;
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
	public characteristics getChars() {
		return chars;
	}
	public void setChars(characteristics chars) {
		this.chars = chars;
	}
	
}
