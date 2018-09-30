import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class Main {
	static Catalogue catalogue = new Catalogue();;

	public static void main(String[] args) {
		booksShow("F:\\git\\javaprojects\\LAB1\\data\\book.xml");
		readersShow("F:\\git\\javaprojects\\LAB1\\data\\reader.xml");
		librariansShow("F:\\git\\javaprojects\\LAB1\\data\\librarian.xml");
	}
	
	static void booksShow(String path) {
		 File fXml=new File(path);
	        
	        try
	        {
	            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	            DocumentBuilder db=dbf.newDocumentBuilder();
	            Document doc=db.parse(fXml);
	            
	            doc.getDocumentElement().normalize();
	            System.out.println("Список книг");
	            
	            NodeList nodeLst=doc.getElementsByTagName("book");
	           	            
	            for(int je=0;je<nodeLst.getLength();je++)
	            {
	                Node fstNode=nodeLst.item(je);
	                if(fstNode.getNodeType()==Node.ELEMENT_NODE)
	                {
	                	
	                	 Element elj=(Element)fstNode;
	                	 
	                	//считывание id	                 
	                    NodeList nljx=elj.getElementsByTagName("id");
	                    Element eljx=(Element)nljx.item(0);
	                    NodeList nljxc=eljx.getChildNodes();
	                    Integer id = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());	                    
	                  //считывание title
	                    nljx=elj.getElementsByTagName("title");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String title = ""+((Node)nljxc.item(0)).getNodeValue();
	                	//считывание author
	                    nljx=elj.getElementsByTagName("author");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String author = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание publisher
	                    nljx=elj.getElementsByTagName("publisher");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String publisher = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание publisher
	                    nljx=elj.getElementsByTagName("pages");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    Integer pages = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());
	                  //считывание year
	                    nljx=elj.getElementsByTagName("year");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    Integer year = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());
	                    
	                    //добавление книги в список
	                    catalogue.bookAdd(id,title, author, publisher, pages, year);
	                    System.out.println((catalogue.bookFind(id)).toString());
	                }
	            }
	        }
	        catch(Exception ei){}
	    }
	
	static void readersShow(String path) {
		 File fXml=new File(path);
	        
	        try
	        {
	            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	            DocumentBuilder db=dbf.newDocumentBuilder();
	            Document doc=db.parse(fXml);
	            
	            doc.getDocumentElement().normalize();
	            System.out.println("Список читателей");
	            
	            NodeList nodeLst=doc.getElementsByTagName("reader");
	           	            
	            for(int je=0;je<nodeLst.getLength();je++)
	            {
	                Node fstNode=nodeLst.item(je);
	                if(fstNode.getNodeType()==Node.ELEMENT_NODE)
	                {
	                	
	                	 Element elj=(Element)fstNode;
	                	 
	                	//считывание id	                 
	                    NodeList nljx=elj.getElementsByTagName("id");
	                    Element eljx=(Element)nljx.item(0);
	                    NodeList nljxc=eljx.getChildNodes();
	                    Integer id = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());	                    
	                  //считывание title
	                    nljx=elj.getElementsByTagName("name");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String name = ""+((Node)nljxc.item(0)).getNodeValue();
	                	//считывание author
	                    nljx=elj.getElementsByTagName("surname");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String surname = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание publisher
	                    nljx=elj.getElementsByTagName("patronymic");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String patronymic = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание publisher
	                    nljx=elj.getElementsByTagName("telephone");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String telephone = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание year
	                    nljx=elj.getElementsByTagName("birthday");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String birthday = ""+((Node)nljxc.item(0)).getNodeValue();
	                    nljx=elj.getElementsByTagName("adress");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String adress = ""+((Node)nljxc.item(0)).getNodeValue();
	                    
	                    //добавление книги в список
	                    catalogue.readerAdd(id,name,surname,patronymic,telephone,birthday,adress);
	                    System.out.println((catalogue.readerFind(id)).toString());
	                }
	            }
	        }
	        catch(Exception ei){}
	    }
	static void librariansShow(String path) {
		 File fXml=new File(path);
	        
	        try
	        {
	            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	            DocumentBuilder db=dbf.newDocumentBuilder();
	            Document doc=db.parse(fXml);
	            
	            doc.getDocumentElement().normalize();
	            System.out.println("Список библиотекарей");
	            
	            NodeList nodeLst=doc.getElementsByTagName("librarian");
	           	            
	            for(int je=0;je<nodeLst.getLength();je++)
	            {
	                Node fstNode=nodeLst.item(je);
	                if(fstNode.getNodeType()==Node.ELEMENT_NODE)
	                {
	                	
	                	 Element elj=(Element)fstNode;
	                	 
	                	//считывание id	                 
	                    NodeList nljx=elj.getElementsByTagName("id");
	                    Element eljx=(Element)nljx.item(0);
	                    NodeList nljxc=eljx.getChildNodes();
	                    Integer id = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());	                    
	                  //считывание title
	                    nljx=elj.getElementsByTagName("name");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String name = ""+((Node)nljxc.item(0)).getNodeValue();
	                	//считывание author
	                    nljx=elj.getElementsByTagName("surname");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String surname = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание publisher
	                    nljx=elj.getElementsByTagName("patronymic");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String patronymic = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание publisher
	                    nljx=elj.getElementsByTagName("telephone");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String telephone = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //считывание year
	                    nljx=elj.getElementsByTagName("birthday");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String birthday = ""+((Node)nljxc.item(0)).getNodeValue();
	                    nljx=elj.getElementsByTagName("adress");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String adress = ""+((Node)nljxc.item(0)).getNodeValue();
	                    nljx=elj.getElementsByTagName("salary");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    Integer salary = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());
	                    nljx=elj.getElementsByTagName("experience");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    Integer experience = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());
	                    
	                    //добавление книги в список
	                    catalogue.libAdd(id,name,surname,patronymic,telephone,birthday,adress,salary,experience);
	                    System.out.println((catalogue.libFind(id)).toString());
	                }
	            }
	        }
	        catch(Exception ei){}
	    }

	


	}

