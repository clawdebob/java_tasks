package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	//product
	private static JButton getAllPr;
	private static JButton AddPr;
	private static JButton SearchPr;
	private static JButton DeletePr;
	private static JPanel panel1;
	private static JPanel form;
	private static JTabbedPane tabbedPane;
	private JTable table;
	private static JTextArea area1;
	private static JScrollPane scroll;
	public Client() {
		super("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setVisible(true);
		tabbedPane = new JTabbedPane();
		area1 = new JTextArea();
		panel1 = new JPanel();
		form = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel1.setLayout(null);
		panel2.setLayout(null);
		panel3.setLayout(null);
		tabbedPane.addTab("Продукты", null, panel1,
		                  "Does nothing");
		tabbedPane.addTab("Персоны", null, panel2,
                "Does nothing");
		tabbedPane.addTab("Операции", null, panel3,
                "Does nothing");
		getAllPr = new JButton("Вывести все продукты");
		AddPr = new JButton("Добавить продукт");
		SearchPr = new JButton("Найти продукт");
		DeletePr = new JButton("Удалить продукт");
		getAllPr.setSize(250, 30);
		getAllPr.setLocation(520,10);
		AddPr.setSize(250, 30);
		AddPr.setLocation(520,50);
		SearchPr.setSize(250, 30);
		SearchPr.setLocation(520,90);
		DeletePr.setSize(250, 30);
		DeletePr.setLocation(520,130);
		area1.setSize(500, 500);
		area1.setLocation(10,10);
		scroll = new JScrollPane(area1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setSize(500, 500);
		scroll.setLocation(10,10);
		panel1.add(AddPr);
	    panel1.add(getAllPr);
	    panel1.add(SearchPr);
	    panel1.add(DeletePr);
	    panel1.add(scroll);
	    add(tabbedPane);
	}
	public static void main(String[] args) throws Exception {
	    Client app = new Client();
	    app.setVisible(true);
		String host = "localhost";
		int port = 3334;
		try {
			System.out.println("Client is running");
			Scanner input = new Scanner(System.in);
			boolean loop = true;
			Socket sock = new Socket(host, port);
			Gson gson = new GsonBuilder().create();
			DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
			DataInputStream inStream = new DataInputStream(sock.getInputStream());
			Request query = new Request();
		    getAllPr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Request query = new Request();
					query.type = "ListAllProducts";
					try {
						outStream.writeUTF(gson.toJson(query));
						area1.setText(inStream.readUTF().replace(',', '\n'));	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}   
		    });
		    AddPr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel1.remove(form);
					form.removeAll();
					app.repaint();
					String []ops = {
							"Бытовая химия",
							"Продукты",
							"Одежда-обувь",
							"Бытовая электроника"
					};
					JTextField id = new JTextField();
					JTextField name = new JTextField();
					JTextField room = new JTextField();
					JTextField desk = new JTextField();
					JTextField st_time = new JTextField();
					JTextField width = new JTextField();
					JTextField height = new JTextField();
					JTextField quantity = new JTextField();
					JComboBox type = new JComboBox(ops);
					JButton submit = new JButton("Добавить продукт");
					JLabel id_l = new JLabel("ID продукта:");
					JLabel name_l = new JLabel("Название:");
					JLabel type_l = new JLabel("Тип:");
					JLabel room_l = new JLabel("Помещение:");
					JLabel desk_l = new JLabel("Полка:");
					JLabel quantity_l = new JLabel("Кол-во:");
					JLabel width_l = new JLabel("Ширина:");
					JLabel height_l = new JLabel("Высота:");
					JLabel st_time_l = new JLabel("Срок годности в сутках:");
					Request query = new Request();
					form = new JPanel();
					id_l.setSize(100, 30);
					id_l.setLocation(10,10);
					id.setLocation(110,10);
					id.setSize(100, 30);
					
					name_l.setSize(100, 30);
					name_l.setLocation(10,50);
					name.setLocation(110,50);
					name.setSize(100, 30);
					
					type_l.setSize(40, 30);
					type_l.setLocation(10,90);
					type.setLocation(50,90);
					type.setSize(200, 30);
					
					room_l.setSize(100, 30);
					room_l.setLocation(10,130);
					room.setLocation(110,130);
					room.setSize(100, 30);
					
					desk_l.setSize(100, 30);
					desk_l.setLocation(10,170);
					desk.setLocation(110,170);
					desk.setSize(100, 30);
					
					quantity_l.setSize(100, 30);
					quantity_l.setLocation(10,210);
					quantity.setLocation(110,210);
					quantity.setSize(100, 30);
					
					st_time_l.setSize(200, 30);
					st_time_l.setLocation(10,250);
					st_time.setLocation(210,250);
					st_time.setSize(50, 30);
					
					width_l.setSize(100, 30);
					width_l.setLocation(10,290);
					width.setLocation(110,290);
					width.setSize(100, 30);
					
					height_l.setSize(100, 30);
					height_l.setLocation(10,330);
					height.setLocation(110,330);
					height.setSize(100, 30);
					
					submit.setSize(250, 30);
					submit.setLocation(10,600);

					form.setLayout(null);
					form.setSize(270, 700);
					form.setLocation(800,10);
					form.add(id_l);
					form.add(id);
					form.add(name_l);
					form.add(name);
					form.add(type_l);
					form.add(type);
					form.add(room_l);
					form.add(room);
					form.add(desk_l);
					form.add(desk);
					form.add(quantity_l);
					form.add(quantity);
					form.add(st_time_l);
					form.add(st_time);
					form.add(width_l);
					form.add(width);
					form.add(height_l);
					form.add(height);
					form.add(submit);
					form.setBackground(Color.WHITE);
					width.setEditable(false);
					height.setEditable(false);
					st_time.setEditable(true);
					panel1.add(form);
					app.repaint();
					type.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							switch((String) type.getSelectedItem()) {
							case "Бытовая химия":
							case "Продукты":
								width.setEditable(false);
								height.setEditable(false);
								st_time.setEditable(true);
								break;
							case "Одежда-обувь":
							case "Бытовая электроника":
								width.setEditable(true);
								height.setEditable(true);
								st_time.setEditable(false);
								break;
							}
						}
					});
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "AddProduct";
							query.data.put("id", id.getText());
							query.data.put("name", name.getText());
							query.data.put("room", room.getText());
							query.data.put("desk", desk.getText());
							
							query.data.put("quantity", quantity.getText());
							switch((String) type.getSelectedItem()) {
							case "Бытовая химия":
								query.data.put("type", "0");
								break;
							case "Продукты":
								query.data.put("type", "1");
								break;
							case "Одежда-обувь":
								query.data.put("type", "3");
								break;
							case "Бытовая электроника":
								query.data.put("type", "4");
								break;
							}
							
							int types=Integer.parseInt(query.data.get("type"));
							if (types == 0 || types == 1) {
								query.data.put("height", "0");
								query.data.put("width", "0");
							} else {
								query.data.put("height", height.getText());
								query.data.put("width", height.getText());
							}
							query.data.put("time", "0");
							if (types == 3 || types == 4) {
								query.data.put("st_time", "0");
							} else {
								query.data.put("st_time", st_time.getText());
							}				
							query.data.put("status", "0");
							try {
								outStream.writeUTF(gson.toJson(query));
								area1.setText(inStream.readUTF().replace(',', '\n'));
								name.setText("");
								id.setText("");
								st_time.setText("");
								width.setText("");
								height.setText("");
								quantity.setText("");
								room.setText("");
								desk.setText("");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					
				}   
		    });
		    DeletePr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel1.remove(form);
					form.removeAll();
					app.repaint();
					JTextField id = new JTextField();
					JButton submit = new JButton("Удалить продукт");
					JLabel id_l = new JLabel("ID продукта");
					Request query = new Request();
					form = new JPanel();
					id_l.setSize(100, 30);
					id_l.setLocation(10,10);
					submit.setSize(250, 30);
					submit.setLocation(10,50);
					id.setLocation(110,10);
					id.setSize(100, 30);
					form.setLayout(null);
					form.setSize(270, 200);
					form.setLocation(800,10);
					form.add(id_l);
					form.add(submit);
					form.add(id);
					form.setBackground(Color.WHITE); 
					panel1.add(form);
					app.repaint();
					//tabbedPane.addTab("Продукты",panel1);
					app.add(tabbedPane);
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "DeleteProduct";
							query.data.put("product", id.getText());
							try {
								outStream.writeUTF(gson.toJson(query));
								area1.setText(inStream.readUTF().replace(',', '\n'));
								id.setText("");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}   
		    });
			
		    while(loop) {
				query = new Request();
				query.type = input.nextLine(); // ждём пока клиент что-нибудь
				switch (query.type) {
					/*case "AddProduct":
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
					/*case "DeleteProduct":
						System.out.println("Введите id продукта");
						query.data.put("product", input.nextLine());
						break;*/
					case "EditProduct":
					System.out.println("Введите id продукта");
					query.data.put("product", input.nextLine());
					System.out.println("Введите  имя параметра продукта, новое значение");
					query.data.put(input.nextLine(), input.nextLine());
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
					case "SearchProduct":
						System.out.println("Введите параметр");
						query.data.put("param", input.nextLine());
						System.out.println("Введите значение");
						query.data.put("value", input.nextLine());
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