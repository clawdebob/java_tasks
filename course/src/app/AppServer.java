package app;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.Gson;

import models.*;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

class Server {
	public static void main(String[] args){
		try {
			warehouse.JSONtoOperation();
			warehouse.JSONtoProduct();
			warehouse.JSONtoPerson();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("Server is running");
			int port = 3334;
			// создание серверного сокета
			ServerSocket ss = new ServerSocket(port);
			// Ждет клиентов и для каждого создает отдельный поток
			while (true) {
				Socket s = ss.accept();
				ServerConnectionProcessor p = new ServerConnectionProcessor(s);
				p.start();
			}
		}
		catch(Exception e) { System.out.println(e);}
		}
}

class ServerConnectionProcessor extends Thread {
	private Socket sock;
	public ServerConnectionProcessor(Socket s) {
		sock = s;
	}
	public void run() {
		JFrame myWindow = new JFrame("Пробное окно");
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.setSize(400, 300);
		myWindow.setVisible(true);

		try {
			// Получает запрос
			DataInputStream inStream = new DataInputStream(sock.getInputStream());
			DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
			boolean loop = true;
			String res;
			while(loop) {
			res = inStream.readUTF();
			Request query = new Gson().fromJson(res,Request.class);
			System.out.println(res);
			switch(query.type) {
			case "AddProduct":
				boolean available;
				int id = Integer.parseInt(query.data.get("id"));
				String name = query.data.get("name");
    			int room = Integer.parseInt(query.data.get("room"));
    			int desk = Integer.parseInt(query.data.get("desk"));
    			int type = Integer.parseInt(query.data.get("type"));
    			int status = Integer.parseInt(query.data.get("status"));
    			int quantity = Integer.parseInt(query.data.get("quantity"));
				if (type > 0) {
					available=true;
				} else {
					available=false;
				}
    			int height = Integer.parseInt(query.data.get("height"));
    			String time = query.data.get("time");
    			int width = Integer.parseInt(query.data.get("width"));
    			int st_time = Integer.parseInt(query.data.get("st_time"));
    			characteristics chr = new characteristics(height,width,time,status, available, st_time,quantity);
    			warehouse.AddProduct(id, name, type, room, desk, chr);
    			outStream.writeUTF(warehouse.ProductsToJSON());
				break;
			case "EditProduct":
				int val;
				id = Integer.parseInt(query.data.get("product"));
				product pr = warehouse.products.get(id);
				characteristics cr = pr.getChars();
				for(Map.Entry<String, String> entry : query.data.entrySet()) {
					String p = entry.getKey();
					switch(p) {
					case "id":
						val = Integer.parseInt(entry.getValue());
						if (warehouse.products.containsKey(val)) {
							outStream.writeUTF("product with such id is already present");
						}
						else {
							warehouse.products.remove(id);
							id=val;
							pr.setId(id);
						}
						break;
					case "type":
						val = Integer.parseInt(entry.getValue());
						pr.setType(val);
						break;
					case "room":
						val = Integer.parseInt(entry.getValue());
						pr.setRoom(val);
						break;
					case "desk":
						val = Integer.parseInt(entry.getValue());
						pr.setDesk(val);
						break;
					case "name":
						pr.setName(entry.getValue());
						break;
					case "width":
						if(pr.getType()==3 || pr.getType()==4) {
							val = Integer.parseInt(entry.getValue());
							cr.setWidth(val);
						} else {
							outStream.writeUTF("Parameter cant be edited for this product type");
						}
						break;
					case "height":
						if(pr.getType()==3 || pr.getType()==4) {
							val = Integer.parseInt(entry.getValue());
							cr.setHeight(val);
						} else {
							outStream.writeUTF("Parameter cant be edited for this product type");
						}
						break;
					case "quantity":
						val = Integer.parseInt(entry.getValue());
						cr.setQuantity(val);
						if(val==0) {
							cr.setAvailable(false);
						} else if(val>0){
							cr.setAvailable(true);
						}else {
							outStream.writeUTF("invalid value");
						}
						break;
					case "time":
						cr.setTime(entry.getValue());
						break;
					case "st_time":
						val = Integer.parseInt(entry.getValue());
						cr.setSt_time(val);
						break;
					}
					pr.setChars(cr);
					warehouse.products.put(id,pr);
				}
				outStream.writeUTF(warehouse.ProductsToJSON());
				break;
			case "DeleteProduct":
				id = Integer.parseInt(query.data.get("product"));
				warehouse.products.remove(id);
				outStream.writeUTF(warehouse.ProductsToJSON());
				break;
     		case "ListAllProducts":
     			outStream.writeUTF(warehouse.ProductsToJSON());
     			break;
     		case "AddPerson":
         		id = Integer.parseInt(query.data.get("id"));
    			name = query.data.get("name");
    			String email = query.data.get("email");
    			String telephone = query.data.get("telephone");
    			String address = query.data.get("address");
    			type = Integer.parseInt(query.data.get("type"));
    			warehouse.AddPerson(id, name, address, email, telephone, type);
    			outStream.writeUTF(warehouse.PersonsToJSON());
     			break;
     		case "EditPerson":
     			id = Integer.parseInt(query.data.get("person"));
     			person per = warehouse.persons.get(id);
     			for(Map.Entry<String, String> entry : query.data.entrySet()) {
					String p = entry.getKey();
					switch(p) {
					case "id":
						val = Integer.parseInt(entry.getValue());
						if (warehouse.persons.containsKey(val)) {
							outStream.writeUTF("product with such id is already present");
						}
						else {
							warehouse.persons.remove(id);
							id=val;
							per.setId(id);
						}
						break;
					case "type":
						val = Integer.parseInt(entry.getValue());
						per.setType(val);
						break;
					case "name":
						per.setName(entry.getValue());
						break;
					case "email":
						per.setEmail(entry.getValue());
						break;
					case "address":
						per.setAddress(entry.getValue());
						break;
					case "telephone":
						per.setTelephone(entry.getValue());
						break;
					}
					warehouse.persons.put(id,per);
     			}
     			outStream.writeUTF(warehouse.PersonsToJSON());
     			break;
     		case "DeletePerson":
				id = Integer.parseInt(query.data.get("person"));
				warehouse.persons.remove(id);
				outStream.writeUTF(warehouse.PersonsToJSON());
     			break;
     		case "AddOperation":
         		id = Integer.parseInt(query.data.get("id"));
    			String date = query.data.get("date");
    			int product = Integer.parseInt(query.data.get("product"));
    			quantity = Integer.parseInt(query.data.get("quantity"));
    			int person = Integer.parseInt(query.data.get("person"));
    			warehouse.AddOperation(person, product, quantity, id, date);
    			outStream.writeUTF(warehouse.OperationsToJSON());
    			break;
     		case "ListAllPersons":
     			outStream.writeUTF(warehouse.ProductsToJSON());
     			break;
     		case "exit":
     			loop=false;
     			outStream.writeUTF("Session is over");
     		    break;
     		default:
                outStream.writeUTF("Unknown command");
                break;
			}
			}

			// Отправляет ответ

			//outStream.writeInt(result);
			// Подождем немного и завершим поток
			sleep(1000);
			inStream.close(); 
			outStream.close(); 
			sock.close();
			
		}
		catch(Exception e) { System.out.println(e); }
	}
}