package models;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;



public class warehouse {
	public static HashMap<Integer,person> persons = new HashMap<Integer,person>();
	public static HashMap<Integer,product> products = new HashMap<Integer,product>();
	public static HashMap<Integer,operation> operations = new HashMap<Integer,operation>();
	static GsonBuilder builder = new GsonBuilder();
	static Gson gson = builder.create();
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
	public static void AddOperation(int person, int product, int quantity, int id, String date) {
		if (operations.containsKey(id)) {
			//error
		}
		else {
		    operation pr = new operation(person,product,quantity,id,date);
		    operations.put(id, pr);
		}
	}
	public String SearchProduct(String param,String val) {
		ArrayList<product> obj = new ArrayList<product>();
		for(Map.Entry<Integer, product> entry : products.entrySet()) {
			product p = entry.getValue();
			switch(param) {
			case "type":
				if(p.getType() == Integer.parseInt(val)) {
					obj.add(p);
				}
				break;
			case "time":
				if(p.getChars().getTime() == val) {
					obj.add(p);
				}
				break;
			}
			obj.add(p);
			System.out.println("GSON:" + gson.toJson(p));
		}
		return gson.toJson(obj);
	}
}
