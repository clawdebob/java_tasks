import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import entities.Catalogue;

public class Main {
	static Catalogue catalogue = new Catalogue();

	public static void main(String[] args) {
		booksFromXML("F:\\git\\javaprojects\\LAB1\\data\\book.xml");
		catalogue.bookAdd(3,"fff", "fff", "fff", 11, 2010);
		booksToXML("F:\\git\\javaprojects\\LAB1\\data\\book.xml");
		readersFromXML("F:\\git\\javaprojects\\LAB1\\data\\reader.xml");
		readersToXML("F:\\git\\javaprojects\\LAB1\\data\\reader.xml");
		librariansFromXML("F:\\git\\javaprojects\\LAB1\\data\\librarian.xml");
		//librariansToXML("F:\\git\\javaprojects\\LAB1\\data\\librarian.xml");
	}
	
	static void booksFromXML(String path) {
		 File fXml=new File(path);
	        
	        try
	        {
	            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	            DocumentBuilder db=dbf.newDocumentBuilder();
	            Document doc=db.parse(fXml);
	            
	            doc.getDocumentElement().normalize();
	            System.out.println("������ ����");
	            
	            NodeList nodeLst=doc.getElementsByTagName("book");
	           	            
	            for(int je=0;je<nodeLst.getLength();je++)
	            {
	                Node fstNode=nodeLst.item(je);
	                if(fstNode.getNodeType()==Node.ELEMENT_NODE)
	                {
	                	
	                	 Element elj=(Element)fstNode;
	                	 
	                	//���������� id	                 
	                    NodeList nljx=elj.getElementsByTagName("id");
	                    Element eljx=(Element)nljx.item(0);
	                    NodeList nljxc=eljx.getChildNodes();
	                    Integer id = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());	                    
	                  //���������� title
	                    nljx=elj.getElementsByTagName("title");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String title = ""+((Node)nljxc.item(0)).getNodeValue();
	                	//���������� author
	                    nljx=elj.getElementsByTagName("author");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String author = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� publisher
	                    nljx=elj.getElementsByTagName("publisher");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String publisher = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� publisher
	                    nljx=elj.getElementsByTagName("pages");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    Integer pages = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());
	                  //���������� year
	                    nljx=elj.getElementsByTagName("year");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    Integer year = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());
	                    
	                    //���������� ����� � ������
	                    catalogue.bookAdd(id,title, author, publisher, pages, year);
	                    System.out.println((catalogue.bookFind(id)).toString());
	                }
	            }
	        }
	        catch(Exception ei){}
	    }
	
	
	static void readersFromXML(String path) {
		 File fXml=new File(path);
	        
	        try
	        {
	            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	            DocumentBuilder db=dbf.newDocumentBuilder();
	            Document doc=db.parse(fXml);
	            
	            doc.getDocumentElement().normalize();
	            System.out.println("������ ���������");
	            
	            NodeList nodeLst=doc.getElementsByTagName("reader");
	           	            
	            for(int je=0;je<nodeLst.getLength();je++)
	            {
	                Node fstNode=nodeLst.item(je);
	                if(fstNode.getNodeType()==Node.ELEMENT_NODE)
	                {
	                	
	                	 Element elj=(Element)fstNode;
	                	 
	                	//���������� id	                 
	                    NodeList nljx=elj.getElementsByTagName("id");
	                    Element eljx=(Element)nljx.item(0);
	                    NodeList nljxc=eljx.getChildNodes();
	                    Integer id = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());	                    
	                  //���������� title
	                    nljx=elj.getElementsByTagName("name");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String name = ""+((Node)nljxc.item(0)).getNodeValue();
	                	//���������� author
	                    nljx=elj.getElementsByTagName("surname");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String surname = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� publisher
	                    nljx=elj.getElementsByTagName("patronymic");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String patronymic = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� publisher
	                    nljx=elj.getElementsByTagName("telephone");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String telephone = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� year
	                    nljx=elj.getElementsByTagName("birthday");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String birthday = ""+((Node)nljxc.item(0)).getNodeValue();
	                    nljx=elj.getElementsByTagName("adress");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String adress = ""+((Node)nljxc.item(0)).getNodeValue();
	                    
	                    //���������� ����� � ������
	                    catalogue.readerAdd(id,name,surname,patronymic,telephone,birthday,adress);
	                    System.out.println((catalogue.readerFind(id)).toString());
	                }
	            }
	        }
	        catch(Exception ei){}
	    }
	static void librariansFromXML(String path) {
		 File fXml=new File(path);
	        
	        try
	        {
	            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	            DocumentBuilder db=dbf.newDocumentBuilder();
	            Document doc=db.parse(fXml);
	            
	            doc.getDocumentElement().normalize();
	            System.out.println("������ �������������");
	            
	            NodeList nodeLst=doc.getElementsByTagName("librarian");
	           	            
	            for(int je=0;je<nodeLst.getLength();je++)
	            {
	                Node fstNode=nodeLst.item(je);
	                if(fstNode.getNodeType()==Node.ELEMENT_NODE)
	                {
	                	
	                	 Element elj=(Element)fstNode;
	                	 
	                	//���������� id	                 
	                    NodeList nljx=elj.getElementsByTagName("id");
	                    Element eljx=(Element)nljx.item(0);
	                    NodeList nljxc=eljx.getChildNodes();
	                    Integer id = Integer.parseInt(""+((Node)nljxc.item(0)).getNodeValue());	                    
	                  //���������� title
	                    nljx=elj.getElementsByTagName("name");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String name = ""+((Node)nljxc.item(0)).getNodeValue();
	                	//���������� author
	                    nljx=elj.getElementsByTagName("surname");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String surname = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� publisher
	                    nljx=elj.getElementsByTagName("patronymic");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String patronymic = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� publisher
	                    nljx=elj.getElementsByTagName("telephone");
	                    eljx=(Element)nljx.item(0);
	                    nljxc=eljx.getChildNodes();
	                    String telephone = ""+((Node)nljxc.item(0)).getNodeValue();
	                  //���������� year
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
	                    
	                    //���������� ����� � ������
	                    catalogue.libAdd(id,name,surname,patronymic,telephone,birthday,adress,salary,experience);
	                    System.out.println((catalogue.libFind(id)).toString());
	                }
	            }
	        }
	        catch(Exception ei){}
	    }
	static void booksToXML(String path) {
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(path);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
		
			String booksXML = catalogue.booksToXML();
			bufferedWriter.append(booksXML);
			bufferedWriter.flush();
			
		} catch (IOException ex) {
			// handle exception
		} finally { try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}			  
	}
	static void readersToXML(String path) {
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(path);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
		
			String booksXML = catalogue.readersToXML();
			bufferedWriter.append(booksXML);
			bufferedWriter.flush();
			
		} catch (IOException ex) {
			// handle exception
		} finally { try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}			  
	}
	static void librariansToXML(String path) {
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileOutputStream = new FileOutputStream(path);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
		
			String booksXML = catalogue.librariansToXML();
			bufferedWriter.append(booksXML);
			bufferedWriter.flush();
			
		} catch (IOException ex) {
			// handle exception
		} finally { try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}			  
	}

	}

