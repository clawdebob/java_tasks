package app;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import models.*;

public class Main {
	private static warehouse a;
	public static void main(String args[]) throws IOException, ParseException {
		try {
			a.JSONtoPerson();
			a.JSONtoProduct();
			a.PersonsToJSON();
			a.ProductsToJSON();
			a.JSONtoOperation();
			a.OperationsToJSON();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
