package app;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.warehouse;

import javax.swing.*;
import java.net.*;

import java.io.*;

import java.net.Socket;


class Client extends JFrame{
	private JLabel countLabel;
	private JButton addCrow;
	private JButton removeCrow;
	public Client() {
		super("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
		countLabel = new JLabel("Crows:" );
	    addCrow = new JButton("Add Crow");
	    removeCrow = new JButton("Remove Crow");

	    //Подготавливаем временные компоненты
	    JPanel buttonsPanel = new JPanel(new FlowLayout()); 
	    //Расставляем компоненты по местам
	    buttonsPanel.add(countLabel, BorderLayout.NORTH); //О размещении компонент поговорим позже

	    buttonsPanel.add(addCrow);
	    buttonsPanel.add(removeCrow);

	    add(buttonsPanel, BorderLayout.SOUTH);
	}
	public static void main(String[] args) throws Exception {
	    Client app = new Client();
	    app.setVisible(true);
		String host = "localhost";
		int port = 3334;
		try {
			System.out.println("Client is running");
			// запрос клиента на соединение
			Scanner input = new Scanner(System.in);
			boolean loop = true;
			Socket sock = new Socket(host, port);
			Gson gson = new GsonBuilder().create();
			DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
			DataInputStream inStream = new DataInputStream(sock.getInputStream());
			while(loop) {
				Request query = new Request();
				query.type = input.nextLine(); // ждём пока клиент что-нибудь
				switch (query.type) {
					case "AddProduct":
						System.out.println("Введите id продукта");
						query.data.put("id", input.nextLine());
						System.out.println("Введите название продукта");
						query.data.put("name", input.nextLine());
						System.out.println("Введите помещение продукта");
						query.data.put("room", input.nextLine());
						System.out.println("Введите полку продукта");
						query.data.put("desk", input.nextLine());
						System.out.println("Введите тип продукта");
						query.data.put("type", input.nextLine());
						System.out.println("Введите количество продукта");
						query.data.put("quantity", input.nextLine());
						int type=Integer.parseInt(query.data.get("quantity"));
						if (type == 0 || type == 1) {
							query.data.put("height", "0");
							query.data.put("width", "0");
						} else {
							System.out.println("Введите высоту продукта");
							query.data.put("height", input.nextLine());
							System.out.println("Введите ширину продукта");
							query.data.put("width", input.nextLine());
						}
						System.out.println("Введите время хранения продукта на складе");
						query.data.put("time", input.nextLine());
						if (type == 3 || type == 4) {
							query.data.put("st_time", "0");
						} else {
							System.out.println("Введите срок годности продукта в сутках");
							query.data.put("st_time", input.nextLine());
						}				
						query.data.put("status", "0");	
						break;
					case "EditProduct":
						System.out.println("Введите id продукта");
						query.data.put("product", input.nextLine());
						System.out.println("Введите  имя параметра продукта, новое значение");
						query.data.put(input.nextLine(), input.nextLine());
						break;
					case "DeleteProduct":
						System.out.println("Введите id продукта");
						query.data.put("product", input.nextLine());
						break;
					case "AddPerson":
						System.out.println("Введите id персоны");
						query.data.put("id", input.nextLine());
						System.out.println("Введите имя персоны");
						query.data.put("name", input.nextLine());
						System.out.println("Введите email персоны");
						query.data.put("email", input.nextLine());
						System.out.println("Введите телефон персоны");
						query.data.put("telephone", input.nextLine());
						System.out.println("Введите адрес персоны");
						query.data.put("address", input.nextLine());
						System.out.println("Введите тип персоны");
						query.data.put("type", input.nextLine());
						break;
					case "EditPerson":
						System.out.println("Введите id персоны");
						query.data.put("person", input.nextLine());
						System.out.println("Введите  имя параметра продукта, новое значение");
						query.data.put(input.nextLine(), input.nextLine());
						break;
					case "DeletePerson":
						System.out.println("Введите id персоны");
						query.data.put("person", input.nextLine());
						break;
					case "AddOperation":
						System.out.println("Введите id операции");
						query.data.put("id", input.nextLine());
						System.out.println("Введите дату операции");
						query.data.put("date", input.nextLine());
						System.out.println("Введите id продукта");
						query.data.put("product", input.nextLine());
						System.out.println("Введите количество");
						query.data.put("quantity", input.nextLine());
						System.out.println("Введите id клиента");
						query.data.put("person", input.nextLine());
						System.out.println("Введите тип операции");
						query.data.put("type", input.nextLine());
						break;
					case "ShowOperations":
						System.out.println("Введите начальную дату");
						query.data.put("from", input.nextLine());
						System.out.println("Введите конечную дату");
						query.data.put("to", input.nextLine());
						break;
					case "IsEmpty":
						System.out.println("Введите номер помещения");
						query.data.put("room", input.nextLine());
						System.out.println("Введите номер полки");
						query.data.put("desk", input.nextLine());
						break;
					case "exit":
						loop=false;
						break;
				}
				if(loop && query.type!="") {
					System.out.println(gson.toJson(query));
					outStream.writeUTF(gson.toJson(query));
					System.out.println(inStream.readUTF());
				}
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