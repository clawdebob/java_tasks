package entities;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.Map;
//import java.util.Set;

public class Catalogue {
	
	private int bookCount;
	private int readerCount;
	private int librarianCount;
	private Map<Integer,Book> books = new HashMap<Integer,Book>();
	private Map<Integer,Reader> readers = new HashMap<Integer,Reader>();
	private Map<Integer,Librarian> librarians = new HashMap<Integer,Librarian>();
	
	public void bookAdd(int _id, String _title, String _author, String _publisher, int _pages, int _year) {
		if (books.containsKey(_id)) {
			//error
		}
		else {
			bookCount++;
		    Book book = new Book(_id,_title, _author, _publisher, _pages, _year);
		    books.put(_id, book);
		}
	};
	
	public String bookFind(int _id) {
		Book book = books.get(_id);
		if (book != null) return book.toString();
		else return "";
	}
	public void bookEdit(int _id, String _title, String _author, String _publisher, int _pages, int _year) {
		Book book = books.get(_id);
		book.setAuthor(_author);
		book.setPages(_pages);
		book.setPublisher(_publisher);
		book.setTitle(_title);
		book.setYear(_year);
	};
	public void bookDelete(int _id) {
		if (!books.containsKey(_id)) {
			//error
		}
		else {
			books.remove(_id);
			bookCount--;
		}
		
	};
	
	public void readerAdd(int id, String name, String surname, String patronymic, String telephone, String birthday,
			String adress) {
		if (readers.containsKey(id)) {
			//error
		}
		else {
			readerCount++;
			Reader reader = new Reader(id,name,surname,patronymic,telephone,birthday, adress);
		    readers.put(id, reader);
		}
	};
	public void readerEdit(int id, String name, String surname, String patronymic, String telephone, String birthday,
			String adress) {
		if (readers.containsKey(id)) {
			//error
		}
		else {
			Reader reader = readers.get(id);
			reader.setName(name);
			reader.setSurname(surname);
			reader.setPatronymic(patronymic);
			reader.setTelephone(telephone);
			reader.setBirthday(birthday);
			reader.setAdress(adress);
		}
	};
	public void readerDelete(int id) {
		if (!readers.containsKey(id)) {
			//error
		}
		else {
			readers.remove(id);
			readerCount--;
		}
	};
	public String readerFind(int id) {
		Reader reader = readers.get(id);
		if (reader != null) return reader.toString();
		else return "";
	}
	public void libAdd(int id, String name, String surname, String patronymic, String telephone, String birthday,
			String adress, int salary, int experience) {
		if (librarians.containsKey(id)) {
			//error
		}
		else {
			readerCount++;
			Librarian lib = new Librarian(id,name,surname,patronymic,telephone,birthday, adress,salary,experience);
		    librarians.put(id, lib);
		}
	};
	public void libEdit(int id, String name, String surname, String patronymic, String telephone, String birthday,
			String adress, int salary, int experience) {
		if (librarians.containsKey(id)) {
			//error
		}
		else {
			Librarian lib = librarians.get(id);
			lib.setName(name);
			lib.setSurname(surname);
			lib.setPatronymic(patronymic);
			lib.setTelephone(telephone);
			lib.setBirthday(birthday);
			lib.setAdress(adress);
			lib.setSalary(salary);
			lib.setExperience(experience);
		}
	};
	public void libDelete(int id) {
		if (!librarians.containsKey(id)) {
			//error
		}
		else {
			librarians.remove(id);
			librarianCount--;
		}
	};
	public String libFind(int id) {
		Librarian lib = librarians.get(id);
		if (lib != null) return lib.toString();
		else return "";
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	public int getReaderCount() {
		return readerCount;
	}
	public void setReaderCount(int readerCount) {
		this.readerCount = readerCount;
	}
	public int getLibrarianCount() {
		return librarianCount;
	}
	public void setLibrarianCount(int librarianCount) {
		this.librarianCount = librarianCount;
	}
	public String booksToXML (){
		String xmlString = "<?xml version=\"1.0\"?> \r\n<books>";
		for (Map.Entry<Integer, Book> entry : books.entrySet()) {
		    
		    Book book = entry.getValue();
		    xmlString += "<book>";
		    xmlString+= "<id>"+book.getId()+"</id>";
		    xmlString+= "<author>"+book.getAuthor()+"</author>";
		    xmlString+= "<title>"+book.getTitle()+"</title>";
		    xmlString+= "<year>"+book.getYear()+"</year>";
		    xmlString+= "<pages>"+book.getPages()+"</pages>";
		    xmlString+= "<publisher>"+book.getPublisher()+"</publisher>";
		    xmlString+= "</book>\r\n";
		}
		xmlString+= "</books>";
		return xmlString;
	};
	public String readersToXML (){
		String xmlString = "<?xml version=\"1.0\"?> \r\n<readers>";
		for (Map.Entry<Integer, Reader> entry : readers.entrySet()) {
		    
		    Reader reader = entry.getValue();
		    xmlString += "<reader>";
		    xmlString+= "<id>"+reader.getId()+"</id>";
		    xmlString+= "<name>"+reader.getName()+"</name>";
		    xmlString+= "<surname>"+reader.getSurname()+"</surname>";
		    xmlString+= "<patronymic>"+reader.getPatronymic()+"</patronymic>";
		    xmlString+= "<telephone>"+reader.getTelephone()+"</telephone>";
		    xmlString+= "<birthday>"+reader.getBirthday()+"</birthday>";
		    xmlString+= "<adress>"+reader.getAdress()+"</adress>";
		    xmlString+= "</reader>\r\n";
		}
		xmlString+= "</readers>";
		return xmlString;
	};
	public String librariansToXML (){
		String xmlString = "<?xml version=\"1.0\"?> \r\n<librarians>";
		for (Map.Entry<Integer, Librarian> entry :librarians.entrySet()) {
		    
		    Librarian reader = entry.getValue();
		    xmlString += "<librarian>";
		    xmlString+= "<id>"+reader.getId()+"</id>";
		    xmlString+= "<name>"+reader.getName()+"</name>";
		    xmlString+= "<surname>"+reader.getSurname()+"</surname>";
		    xmlString+= "<patronymic>"+reader.getPatronymic()+"</patronymic>";
		    xmlString+= "<telephone>"+reader.getTelephone()+"</telephone>";
		    xmlString+= "<birthday>"+reader.getBirthday()+"</birthday>";
		    xmlString+= "<adress>"+reader.getAdress()+"</adress>";
		    xmlString+= "<salary>"+reader.getSalary()+"</salary>";
		    xmlString+= "<experience>"+reader.getExperience()+"</experience>";
		    xmlString+= "</librarian>\r\n";
		}
		xmlString+= "</librarians>";
		return xmlString;
	};

	
	public Catalogue() {
		bookCount = 0;
		readerCount = 0;
		librarianCount = 0;
		
	}
}

