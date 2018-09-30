public class Book {
	private String title;
	private String author;
	private String publisher;
	private int pages;
	private int year;
	private int id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher="
				+ publisher + ", pages=" + pages + ", year=" + year + ", id="
				+ id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	};
	Book(int _id,String _title, String _author, String _publisher, int _pages, int _year) {
		setId(_id);
		title = _title;
		author = _author;
		publisher = _publisher;
		pages = _pages;
		year = _year;
	}
}

