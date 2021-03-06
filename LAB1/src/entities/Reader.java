package entities;

public class Reader {
	private int id;
	private String name;
	private String surname;
	private String patronymic;
	private String telephone;
	private String birthday;
	private String adress;
	
	public Reader(int id, String name, String surname, String patronymic, String telephone, String birthday,
			String adress) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.telephone = telephone;
		this.birthday = birthday;
		this.adress = adress;
	}
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	@Override
	public String toString() {
		return "Reader [name=" + name + ", surname=" + surname + ", patronymic="
				+ patronymic + ", telephone=" + telephone + ", birthday=" + birthday + ", adress=" + adress + ", id="
				+ id + "]";
	}

}
