package app;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import models.warehouse;

import java.net.*;

import java.io.*;

import java.net.Socket;
class Client{
	public static void main(String[] args) throws Exception {
		// Имя хоста и номер порта
		String host = "localhost";
		
		int port = 3333;
		// Протокол передачи
		// Запрос (3 целых чила): [операция][аргумент 1][аргумент 2]
		// Ответ (1 целое число): [результат]
		// Операции: 0 - сложение, 1 - умножение
		try {
			System.out.println("Client is running");
			// запрос клиента на соединение
			Scanner input = new Scanner(System.in);
			Socket sock = new Socket(host, port);
			// Исходящий поток
			DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
			DataInputStream inStream = new DataInputStream(sock.getInputStream());
			// Отправляем запрос и аргументы
			 String query = input.nextLine(); // ждём пока клиент что-нибудь
			 outStream.writeUTF(query);
             switch(query) {
             	case "AddProduct":
             		System.out.println("Введите id продукта");
             		int id = input.nextInt();
             		System.out.println("Введите название продукта");
             		String name= input.nextLine();
             		name= input.nextLine();
             		System.out.println("Введите помещение продукта");
             		int room = input.nextInt();
             		System.out.println("Введите полку продукта");
             		int desk = input.nextInt();
             		System.out.println("Введите тип продукта");
             		int type = input.nextInt();
             		boolean available;
             		int width,height,status,st_time,quantity;
             		String time;
             		status=0;
             		System.out.println("Введите количество продукта");
             		quantity = input.nextInt();
             		if(quantity>0) {
            			available = true;
             		} else {
             			available=false;
             		}
             		if(type==0 || type==1) {
             			height = 0;
             			width = 0;
             		} else {
             			System.out.println("Введите высоту продукта");
             			height = input.nextInt();
             			System.out.println("Введите ширину продукта");
             			width = input.nextInt();
             		}
             		System.out.println("Введите время хранения продукта на складе");
             		time = input.nextLine();
             		time = input.nextLine();
             		if(type==3 || type==4) {
             			st_time=0;
             		} else {
             			System.out.println("Введите срок годности продукта в сутках");
             			st_time=input.nextInt();
             		}
             		outStream.writeInt(id);
        			outStream.writeUTF(name);
        			outStream.writeInt(room);
        			outStream.writeInt(desk);
        			outStream.writeInt(type);
        			outStream.writeInt(status);
        			outStream.writeInt(quantity);
        			outStream.writeBoolean(available);
        			outStream.writeInt(height);
        			outStream.writeUTF(time);
        			outStream.writeInt(width);
        			outStream.writeInt(st_time);
        			System.out.println(inStream.readUTF());
             	break;
             	case "ListAllProducts":
             		System.out.println(inStream.readUTF());
             		break;
             	case "AddPerson":
             		System.out.println("Введите id персоны");
             		id = input.nextInt();
             		System.out.println("Введите имя персоны");
             		name= input.nextLine();
             		name= input.nextLine();
             		String email,telephone,address;
             		System.out.println("Введите email персоны");
             		email= input.nextLine();
             		System.out.println("Введите телефон персоны");
             		telephone= input.nextLine();
             		System.out.println("Введите адресс персоны");
             		address= input.nextLine();
             		System.out.println("Введите тип персоны");
             		type = input.nextInt();
             		outStream.writeInt(id);
        			outStream.writeUTF(name);
        			outStream.writeUTF(email);
        			outStream.writeUTF(telephone);
        			outStream.writeUTF(address);
        			outStream.writeInt(type);
        			System.out.println(inStream.readUTF());
             		break;
             	case "ListAllPersons":
             		System.out.println(inStream.readUTF());
             		break;
             	case "AddOperation":
             		System.out.println("Введите id операции");
             		id = input.nextInt();
             		System.out.println("Введите дату операции");
             		String date= input.nextLine();
             		date= input.nextLine();
             		System.out.println("Введите id продукта");
             		int pr = input.nextInt();
             		System.out.println("Введите количество");
             		quantity = input.nextInt();
             		System.out.println("Введите id клиента");
             		int buyer = input.nextInt();
             		outStream.writeInt(id);
        			outStream.writeUTF(date);
             		outStream.writeInt(pr);
        			outStream.writeInt(quantity);
        			outStream.writeInt(buyer);
        			System.out.println(inStream.readUTF());
             		break;
             }
			// Завершаем потоки и закрываем сокет
            System.out.println("Клиент завершил работу");
            input.close();
			inStream.close();
			outStream.close();
			sock.close();
		}

		catch(Exception e) { System.err.println(e); }
	}
}
 // /:~
/*public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                // если соединение произошло и потоки успешно созданы - мы можем
                //  работать дальше и предложить клиенту что то ввести
                // если нет - вылетит исключение
                String word = reader.readLine(); // ждём пока клиент что-нибудь
                switch(word) {
                case "0":
                	System.out.println("Введите id продукта");
                	int id = sc.nextInt();
                	System.out.println("Введите название продукта");
                	String name= sc.nextLine();
                	//String date= sc.nextLine();
                	System.out.println("Введите помещение продукта");
                	int room = sc.nextInt();
                	System.out.println("Введите полку продукта");
                	int desk = sc.nextInt();
                	System.out.println("Введите тип продукта");
                	int type = sc.nextInt();
                	boolean available;
                	int width,height,status,st_time,quantity;
                	String time;
                	System.out.println("Введите доступность продукта");
                	available = sc.nextBoolean();
                	
                	break;
                }
                // не напишет в консоль
                out.write(word + "\n"); // отправляем сообщение на сервер
                out.flush();
                String serverWord = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverWord); // получив - выводим на экран
            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}*/
