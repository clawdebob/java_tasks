package models;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;



public class warehouse{
	public static HashMap<Integer,person> persons = new HashMap<Integer,person>();
	public static HashMap<Integer,product> products = new HashMap<Integer,product>();
	public static HashMap<Integer,operation> operations = new HashMap<Integer,operation>();
	public transient static DateFormat format = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a", Locale.ENGLISH);
	public static int [][]rooms = new int [6][10];
	public static GsonBuilder builder = new GsonBuilder();
	public static Gson gson = builder.setDateFormat("MMM d, yyyy HH:mm:ss a").create();
	public static void init(){
		for (int c=0;c<6;c++) {
			for (int i=0;i<10;i++) {
				rooms[c][i]=-1;
			}
		}
		try {
			JSONtoProduct();
			JSONtoPerson();
			JSONtoOperation();
			RelocateProducts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void RelocateProducts() {
		for(Map.Entry<Integer, product> entry : products.entrySet()) {
			product p = entry.getValue();
			rooms[p.getRoom()-1][p.getDesk()-1] = p.getId();
		}
	}
	public static void JSONtoProduct() throws FileNotFoundException, IOException{
		  Reader reader = new FileReader("/home/andrew/git/java_tasks/course/products.json");
		  product[] ps = new Gson().fromJson(reader, product[].class);
		  for(product p : ps) {
			  products.put(p.getId(),p);
		  }
		  reader.close();
	}
	public static void JSONtoPerson() throws FileNotFoundException, IOException {
		  Reader reader = new FileReader("/home/andrew/git/java_tasks/course/persons.json");
		  person[] ps = new Gson().fromJson(reader, person[].class);
		  for(person p : ps) {
			  persons.put(p.getId(),p);
		  }
		  reader.close();
	}
	public static void JSONtoOperation() throws JsonSyntaxException, JsonIOException, IOException {
		  Reader reader= new FileReader("/home/andrew/git/java_tasks/course/operations.json");
		  operation[] ps = new Gson().fromJson(reader, operation[].class);
		  for(operation p : ps) {
			operations.put(p.getId(),p);
		  }
		  reader.close();
	}
	public static String OperationsToJSON() {
		ArrayList<operation> obj = new ArrayList<operation>();
		for(Map.Entry<Integer, operation> entry : operations.entrySet()) {
			operation p = entry.getValue();
			obj.add(p);
			System.out.println("GSON:" + gson.toJson(p));
		}
		try (Writer writer = new FileWriter("/home/andrew/git/java_tasks/course/operations.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(obj, writer);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(obj);
	}
	public static String ProductsToJSON() {
		ArrayList<product> obj = new ArrayList<product>();
		for(Map.Entry<Integer, product> entry : products.entrySet()) {
			product p = entry.getValue();
			obj.add(p);
			System.out.println("GSON:" + gson.toJson(p));
		}
		try (Writer writer = new FileWriter("/home/andrew/git/java_tasks/course/products.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(obj, writer);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return gson.toJson(obj);
	}
	public static String PersonsToJSON() throws IOException {
		ArrayList<person> obj = new ArrayList<person>();
		for(Map.Entry<Integer, person> entry : persons.entrySet()) {
			person p = entry.getValue();
			obj.add(p);
			System.out.println("GSON:" + gson.toJson(p));
		}
		try (Writer writer = new FileWriter("/home/andrew/git/java_tasks/course/persons.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(obj, writer);
		    writer.close();
		}
		return gson.toJson(obj);
	}
	public static void AddProduct(int id, String name, int type, int room, int desk,characteristics chars) {
		if (products.containsKey(id)) {
			//error
		}
		else {
		    product pr = new product(id,name,type,room,desk,chars);
		    products.put(id, pr);
		}
	}
	public static void AddPerson(int id,String name, String address, String email, String telephone, int type) {
		if (persons.containsKey(id)) {
			//error
		}
		else {
		    person pr = new person(id,name,address,email,telephone,type);
		    persons.put(id, pr);
		}
	}
	public static void AddOperation(int person, int product, int quantity, int id, Date date,int type) {
		if (operations.containsKey(id)) {
			//error
		}
		else {
		    operation pr = new operation(person,product,quantity,id,date,type);
		    operations.put(id, pr);
		}
	}
	public static String SearchProduct(String param,String val) {
		ArrayList<product> obj = new ArrayList<product>();
		for(Map.Entry<Integer, product> entry : products.entrySet()) {
			product p = entry.getValue();
			switch(param) {
			case "type":
				if(p.getType() == Integer.parseInt(val)) {
					System.out.println(p.getType());
					obj.add(p);
				}
				break;
			case "time":
				if(p.getChars().getTime() == val) {
					obj.add(p);
				}
				break;
			case "room":
				if(p.getRoom() == Integer.parseInt(val)) {
					obj.add(p);
				}
				break;
			case "desk":
				if(p.getDesk() == Integer.parseInt(val)) {
					obj.add(p);
				}
				break;
			}
			//System.out.println("GSON:" + gson.toJson(p));
		}
		return gson.toJson(obj);
	}
	public static String GetDates(String start,String end) throws ParseException {
		ArrayList<operation> obj = new ArrayList<operation>();
		ArrayList<operation> objs = new ArrayList<operation>();
		Date date1 = null, date2 = null,mdate = null;
		end+=" 12:00:00 PM";
		start+=" 12:00:00 PM";
		for(Map.Entry<Integer, operation> entry : operations.entrySet()) {
			operation p = entry.getValue();
			obj.add(p);
		}
		Collections.sort(obj);
		date1 = format.parse(start);
		date2 = format.parse(end);
		for(operation op : obj) {
			mdate=op.getDate();
			if(mdate.after(date1) && mdate.before(date2)) {
				objs.add(op);
			}
		}
		
		return gson.toJson(objs);
	}

}
