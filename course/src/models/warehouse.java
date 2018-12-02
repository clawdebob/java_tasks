package models;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class warehouse {
	private static HashMap<Integer,person> persons = new HashMap<Integer,person>();
	private static HashMap<Integer,product> products = new HashMap<Integer,product>();
	private static HashMap<Integer,operation> operations = new HashMap<Integer,operation>();
	static GsonBuilder builder = new GsonBuilder();
	static Gson gson = builder.create();
	public static void JSONtoProduct() throws FileNotFoundException, IOException{
		  product[] ps = new Gson().fromJson(new FileReader("/home/andrew/git/java_tasks/course/bin/products.json"), product[].class);
		  for(product p : ps) {
			  products.put(p.getId(),p);
		  }	  
	}
	public static void JSONtoPerson() throws FileNotFoundException, IOException {
		  person[] ps = new Gson().fromJson(new FileReader("/home/andrew/git/java_tasks/course/bin/persons.json"), person[].class);
		  for(person p : ps) {
			  persons.put(p.getId(),p);
		  }
	}
	public static void JSONtoOperation() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		  operation[] ps = new Gson().fromJson(new FileReader("/home/andrew/git/java_tasks/course/bin/operations.json"), operation[].class);
		  for(operation p : ps) {
			operations.put(p.getId(),p);
		  }	
	}
	public static void OperationsToJSON() {
		ArrayList<operation> obj = new ArrayList<operation>();
		for(Map.Entry<Integer, operation> entry : operations.entrySet()) {
			operation p = entry.getValue();
			obj.add(p);
			System.out.println("GSON:" + gson.toJson(p));
		}
		try (Writer writer = new FileWriter("/home/andrew/git/java_tasks/course/bin/operations.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(obj, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void ProductsToJSON() {
		ArrayList<product> obj = new ArrayList<product>();
		for(Map.Entry<Integer, product> entry : products.entrySet()) {
			product p = entry.getValue();
			obj.add(p);
			System.out.println("GSON:" + gson.toJson(p));
		}
		try (Writer writer = new FileWriter("/home/andrew/git/java_tasks/course/bin/products.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(obj, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void PersonsToJSON() throws IOException {
		ArrayList<person> obj = new ArrayList<person>();
		for(Map.Entry<Integer, person> entry : persons.entrySet()) {
			person p = entry.getValue();
			obj.add(p);
			System.out.println("GSON:" + gson.toJson(p));
		}
		try (Writer writer = new FileWriter("/home/andrew/git/java_tasks/course/bin/persons.json")) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(obj, writer);
		}
	}
}
