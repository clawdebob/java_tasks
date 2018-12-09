package app;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import models.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
			int port = 3333;
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
		try {
			// Получает запрос
			DataInputStream inStream = new DataInputStream(sock.getInputStream());
			DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
			boolean loop = true;
			while(loop) {
			String query = inStream.readUTF();
			// Выполняет расчет
			int result = 0;
			switch(query) {
			case "AddProduct":
				int id = inStream.readInt();
				String name = inStream.readUTF();
    			int room = inStream.readInt();
    			int desk = inStream.readInt();
    			int type = inStream.readInt();
    			int status = inStream.readInt();
    			int quantity = inStream.readInt();
    			boolean available = inStream.readBoolean();
    			int height = inStream.readInt();
    			String time = inStream.readUTF();
    			int width = inStream.readInt();
    			int st_time = inStream.readInt();
    			characteristics chr = new characteristics(height,width,time,status, available, st_time,quantity);
    			warehouse.AddProduct(id, name, type, room, desk, chr);
    			outStream.writeUTF(warehouse.ProductsToJSON());
				break;
     		case "ListAllProducts":
     			outStream.writeUTF(warehouse.ProductsToJSON());
     		case "AddPerson":
         		id = inStream.readInt();
    			name = inStream.readUTF();
    			String email = inStream.readUTF();
    			String telephone = inStream.readUTF();
    			String address = inStream.readUTF();
    			type = inStream.readInt();
    			warehouse.AddPerson(id, name, address, email, telephone, type);
    			outStream.writeUTF(warehouse.PersonsToJSON());
     			break;
     		case "AddOperation":
         		id = inStream.readInt();
    			String date = inStream.readUTF();
    			int product = inStream.readInt();
    			quantity = inStream.readInt();
    			int person = inStream.readInt();
    			warehouse.AddOperation(person, product, quantity, id, date);
    			outStream.writeUTF(warehouse.OperationsToJSON());
     		case "ListAllPersons":
     			outStream.writeUTF(warehouse.ProductsToJSON());
     		case "exit":
     			loop=false;
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