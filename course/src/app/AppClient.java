package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.warehouse;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import java.net.*;

import java.io.*;

import java.net.Socket;


class Client extends JFrame{
	//product
	public transient static DateFormat format = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a", Locale.ENGLISH);
	private static JButton getAllPr;
	private static JButton AddPr;
	private static JButton CheckPr;
	private static JButton SearchPr;
	private static JButton DeletePr;
	private static JButton EditPr;
	private static JButton getAllPer;
	private static JButton AddPer;
	private static JButton DeletePer;
	private static JButton EditPer;
	private static JButton AddOp;
	private static JButton getAllOp;
	private static JButton getOp;
	private static JPanel panel1;
	private static JPanel panel2;
	private static JPanel panel3;
	private static JPanel form1;
	private static JPanel form2;
	private static JPanel form3;
	private static JTabbedPane tabbedPane;
	private JTable table;
	private static JTextArea area1;
	private static JTextArea area2;
	private static JTextArea area3;
	private static JScrollPane scroll1;
	private static JScrollPane scroll2;
	private static JScrollPane scroll3;
	public Client() {
		super("Складской учет");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 580);
		setVisible(true);
		tabbedPane = new JTabbedPane();
		area1 = new JTextArea();
		area2 = new JTextArea();
		area3 = new JTextArea();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		form1 = new JPanel();
		form2 = new JPanel();
		form3 = new JPanel();
		panel1.setLayout(null);
		panel2.setLayout(null);
		panel3.setLayout(null);
		tabbedPane.addTab("Продукты", null, panel1,
		                  "Does nothing");
		tabbedPane.addTab("Персоны", null, panel2,
                "Does nothing");
		tabbedPane.addTab("Операции", null, panel3,
                "Does nothing");
	    //Продукты
		getAllPr = new JButton("Вывести все продукты");
		AddPr = new JButton("Добавить продукт");
		SearchPr = new JButton("Найти продукт");
		DeletePr = new JButton("Удалить продукт");
		EditPr = new JButton("Редактировать продукт");
		CheckPr = new JButton("Проверить место");
		getAllPr.setSize(250, 30);
		getAllPr.setLocation(520,10);
		AddPr.setSize(250, 30);
		AddPr.setLocation(520,50);
		SearchPr.setSize(250, 30);
		SearchPr.setLocation(520,90);
		EditPr.setSize(250, 30);
		EditPr.setLocation(520,130);
		DeletePr.setSize(250, 30);
		DeletePr.setLocation(520,170);
		CheckPr.setSize(250, 30);
		CheckPr.setLocation(520,210);
		area1.setSize(500, 500);
		area1.setLocation(10,10);
		scroll1 = new JScrollPane(area1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll1.setSize(500, 500);
		scroll1.setLocation(10,10);
		panel1.add(AddPr);
	    panel1.add(getAllPr);
	    panel1.add(SearchPr);
	    panel1.add(EditPr);
	    panel1.add(DeletePr);
	    panel1.add(CheckPr);
	    panel1.add(scroll1);
	    //Персоны
		getAllPer = new JButton("Вывести всех персон");
		AddPer = new JButton("Добавить персону");
		DeletePer = new JButton("Удалить персону");
		EditPer = new JButton("Редактировать персону");
		getAllPer.setSize(250, 30);
		getAllPer.setLocation(520,10);
		AddPer.setSize(250, 30);
		AddPer.setLocation(520,50);
		EditPer.setSize(250, 30);
		EditPer.setLocation(520,90);
		DeletePer.setSize(250, 30);
		DeletePer.setLocation(520,130);
		area2.setSize(500, 500);
		area2.setLocation(10,10);
		scroll2 = new JScrollPane(area2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setSize(500, 500);
		scroll2.setLocation(10,10);
		panel2.add(AddPer);
	    panel2.add(getAllPer);
	    panel2.add(EditPer);
	    panel2.add(DeletePer);
	    panel2.add(scroll2);
	    //Операции
	    getAllOp = new JButton("Вывести все операции");
	    getOp = new JButton("Вывести операции по дате");
	    AddOp = new JButton("Сделать поставку/выдачу");
	    getAllOp.setSize(250, 30);
	    getAllOp.setLocation(520,10);
	    getOp.setSize(250, 30);
	    getOp.setLocation(520,50);
	    AddOp.setSize(250, 30);
	    AddOp.setLocation(520,90);
		scroll3 = new JScrollPane(area3,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll3.setSize(500, 500);
		scroll3.setLocation(10,10);
	    panel3.add(getOp);
	    panel3.add(AddOp);
	    panel3.add(getAllOp);
	    panel3.add(scroll3);
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
			getAllPer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Request query = new Request();
					query.type = "ListAllPersons";
					try {
						outStream.writeUTF(gson.toJson(query));
						area2.setText(inStream.readUTF().replace(',', '\n'));	
					} catch (IOException e) {
						e.printStackTrace();
					}
				}   
		    });
		    getAllOp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Request query = new Request();
					query.type = "ListAllOperations";
					try {
						outStream.writeUTF(gson.toJson(query));
						area3.setText(inStream.readUTF().replace(',', '\n'));	
					} catch (IOException e) {
						e.printStackTrace();
					}
				}   
		    });
		    getAllPr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Request query = new Request();
					query.type = "ListAllProducts";
					try {
						outStream.writeUTF(gson.toJson(query));
						area1.setText(inStream.readUTF().replace(',', '\n'));	
					} catch (IOException e) {
						e.printStackTrace();
					}
				}   
		    });
		    AddPr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel1.remove(form1);
					form1.removeAll();
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
					JButton submit = new JButton("Добавить");
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
					form1 = new JPanel();
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
					submit.setLocation(10,370);

					form1.setLayout(null);
					form1.setSize(270, 410);
					form1.setLocation(800,10);
					form1.add(id_l);
					form1.add(id);
					form1.add(name_l);
					form1.add(name);
					form1.add(type_l);
					form1.add(type);
					form1.add(room_l);
					form1.add(room);
					form1.add(desk_l);
					form1.add(desk);
					form1.add(quantity_l);
					form1.add(quantity);
					form1.add(st_time_l);
					form1.add(st_time);
					form1.add(width_l);
					form1.add(width);
					form1.add(height_l);
					form1.add(height);
					form1.add(submit);
					form1.setBackground(Color.WHITE);
					width.setEditable(false);
					height.setEditable(false);
					st_time.setEditable(true);
					panel1.add(form1);
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
					panel1.remove(form1);
					form1.removeAll();
					app.repaint();
					JTextField id = new JTextField();
					JButton submit = new JButton("Удалить");
					JLabel id_l = new JLabel("ID продукта");
					Request query = new Request();
					form1 = new JPanel();
					id_l.setSize(100, 30);
					id_l.setLocation(10,10);
					submit.setSize(250, 30);
					submit.setLocation(10,50);
					id.setLocation(110,10);
					id.setSize(100, 30);
					form1.setLayout(null);
					form1.setSize(270, 100);
					form1.setLocation(800,10);
					form1.add(id_l);
					form1.add(submit);
					form1.add(id);
					form1.setBackground(Color.WHITE); 
					panel1.add(form1);
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
		    EditPr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel1.remove(form1);
					form1.removeAll();
					app.repaint();
					Request query = new Request();
					String []ops = {
							"Бытовая химия",
							"Продукты",
							"Одежда-обувь",
							"Бытовая электроника"
					};
					String []ops2 = {
							"id",
							"name",
							"type",
							"quantity",
							"st_time",
							"width",
							"height",
							"room",
							"desk",
							"roomdesk"
					};
					JComboBox param = new JComboBox(ops2);
					JComboBox type = new JComboBox(ops);
					JTextField value = new JTextField();
					JTextField id = new JTextField();
					
					boolean state = false;
					JButton submit = new JButton("Применить");
					JLabel param_l = new JLabel("Параметр:");
					JLabel id_l = new JLabel("Id продукта:");
					JLabel val_l = new JLabel("Значение:");
					form1 = new JPanel();
					param_l.setSize(100, 30);
					param_l.setLocation(10,10);
					id_l.setLocation(10,50);
					id_l.setSize(100, 30);
					id.setLocation(110,50);
					id.setSize(100, 30);
					val_l.setLocation(10,90);
					val_l.setSize(100, 30);
					value.setSize(100, 30);
					value.setLocation(110, 90);
					type.setSize(100, 30);
					type.setLocation(110, 90);
					submit.setSize(250, 30);
					submit.setLocation(10,130);
					param.setLocation(110,10);
					param.setSize(100, 30);
					form1.setLayout(null);
					form1.setSize(270, 170);
					form1.setLocation(800,10);
					form1.add(param_l);
					form1.add(val_l);
					form1.add(id_l);
					form1.add(id);
					form1.add(submit);
					form1.add(value);
					form1.add(param);
					form1.setBackground(Color.WHITE); 
					panel1.add(form1);
					app.repaint();
					app.add(tabbedPane);
					param.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if((String) param.getSelectedItem() == "type") {
								form1.remove(value);
								form1.add(type);
							}else {
								if(type.getParent()!=null) {
									form1.remove(type);
									form1.add(value);
								}
							}
							app.repaint();
						}
					});
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "EditProduct";
							if((String) param.getSelectedItem() == "type") {
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
							}
							else {
								query.data.put((String) param.getSelectedItem(), value.getText());
								value.setText("");
							}
							try {
								query.data.put("product", id.getText());
								outStream.writeUTF(gson.toJson(query));
								area1.setText(inStream.readUTF().replace(',', '\n'));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}   
		    });
		    SearchPr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel1.remove(form1);
					form1.removeAll();
					app.repaint();
					Request query = new Request();
					String []ops = {
							"Бытовая химия",
							"Продукты",
							"Одежда-обувь",
							"Бытовая электроника"
					};
					String []ops2 = {
							"room",
							"desk",
							"name",
							"type",
					};
					JComboBox param = new JComboBox(ops2);
					JComboBox type = new JComboBox(ops);
					JTextField value = new JTextField();
					boolean state = false;
					JButton submit = new JButton("Поиск");
					JLabel id_l = new JLabel("Параметр:");
					JLabel val_l = new JLabel("Значение:");
					form1 = new JPanel();
					id_l.setSize(100, 30);
					id_l.setLocation(10,10);
					val_l.setLocation(10,50);
					val_l.setSize(100, 30);
					value.setSize(100, 30);
					value.setLocation(110, 50);
					type.setSize(100, 30);
					type.setLocation(110, 50);
					submit.setSize(250, 30);
					submit.setLocation(10,90);
					param.setLocation(110,10);
					param.setSize(100, 30);
					form1.setLayout(null);
					form1.setSize(270, 130);
					form1.setLocation(800,10);
					form1.add(id_l);
					form1.add(val_l);
					form1.add(submit);
					form1.add(value);
					form1.add(param);
					form1.setBackground(Color.WHITE); 
					panel1.add(form1);
					app.repaint();
					app.add(tabbedPane);
					param.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if((String) param.getSelectedItem() == "type") {
								form1.remove(value);
								form1.add(type);
							}else {
								if(type.getParent()!=null) {
									form1.remove(type);
									form1.add(value);
								}
							}
							app.repaint();
						}
					});
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "SearchProduct";
							query.data.put("param", (String) param.getSelectedItem());
							if((String) param.getSelectedItem() == "type") {
								switch((String) type.getSelectedItem()) {
								case "Бытовая химия":
									query.data.put("value", "0");
									break;
								case "Продукты":
									query.data.put("value", "1");
									break;
								case "Одежда-обувь":
									query.data.put("value", "3");
									break;
								case "Бытовая электроника":
									query.data.put("value", "4");
									break;
								}
							}
							else {
								query.data.put("value", value.getText());
								value.setText("");
							}
							try {
								outStream.writeUTF(gson.toJson(query));
								area1.setText(inStream.readUTF().replace(',', '\n'));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}   
		    });
		    CheckPr.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel1.remove(form1);
					form1.removeAll();
					app.repaint();
					Request query = new Request();
					JTextField desk = new JTextField();
					JTextField room = new JTextField();
					JButton submit = new JButton("Проверка");
					JLabel id_l = new JLabel("Комната:");
					JLabel val_l = new JLabel("Полка:");
					form1 = new JPanel();
					id_l.setSize(100, 30);
					id_l.setLocation(10,10);
					val_l.setLocation(10,50);
					val_l.setSize(100, 30);
					room.setSize(100, 30);
					room.setLocation(110, 10);
					desk.setSize(100, 30);
					desk.setLocation(110, 50);
					submit.setSize(250, 30);
					submit.setLocation(10,90);
					form1.setLayout(null);
					form1.setSize(270, 130);
					form1.setLocation(800,10);
					form1.add(id_l);
					form1.add(val_l);
					form1.add(submit);
					form1.add(desk);
					form1.add(room);
					form1.setBackground(Color.WHITE); 
					panel1.add(form1);
					app.repaint();
					app.add(tabbedPane);
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "IsEmpty";
							query.data.put("room",room.getText());
							query.data.put("desk",desk.getText());
							room.setText("");
							desk.setText("");
							try {
								outStream.writeUTF(gson.toJson(query));
								area1.setText(inStream.readUTF().replace(',', '\n'));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}   
		    });
		    AddPer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel2.remove(form2);
					form2.removeAll();
					app.repaint();
					String []ops = {
							"Потребитель",
							"Поставщик",
					};
					JTextField id = new JTextField();
					JTextField name = new JTextField();
					JTextField email = new JTextField();
					JTextField address = new JTextField();
					JTextField telephone = new JTextField();
					JComboBox type = new JComboBox(ops);
					JButton submit = new JButton("Добавить");
					JLabel id_l = new JLabel("ID персоны:");
					JLabel name_l = new JLabel("Имя:");
					JLabel type_l = new JLabel("Тип:");
					JLabel address_l = new JLabel("Адрес:");
					JLabel email_l = new JLabel("Email:");
					JLabel telephone_l = new JLabel("Телефон:");
					Request query = new Request();
					form2 = new JPanel();
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
					
					address_l.setSize(100, 30);
					address_l.setLocation(10,130);
					address.setLocation(110,130);
					address.setSize(100, 30);
					
					email_l.setSize(100, 30);
					email_l.setLocation(10,170);
					email.setLocation(110,170);
					email.setSize(100, 30);
					
					telephone_l.setSize(100, 30);
					telephone_l.setLocation(10,210);
					telephone.setLocation(110,210);
					telephone.setSize(100, 30);
					
					submit.setSize(250, 30);
					submit.setLocation(10,250);

					form2.setLayout(null);
					form2.setSize(270, 290);
					form2.setLocation(800,10);
					form2.add(id_l);
					form2.add(id);
					form2.add(name_l);
					form2.add(name);
					form2.add(type_l);
					form2.add(type);
					form2.add(telephone);
					form2.add(telephone_l);
					form2.add(address);
					form2.add(address_l);
					form2.add(email);
					form2.add(email_l);
					form2.add(submit);
					form2.setBackground(Color.WHITE);
					panel2.add(form2);
					app.repaint();				
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "AddPerson";
							query.data.put("id", id.getText());
							query.data.put("name", name.getText());
							query.data.put("email", email.getText());
							query.data.put("address", address.getText());
							query.data.put("telephone", telephone.getText());
							switch((String) type.getSelectedItem()) {
							case "Потребитель":
								query.data.put("type", "0");
								break;
							case "Поставщик":
								query.data.put("type", "1");
								break;
							}
							
							int types=Integer.parseInt(query.data.get("type"));
							try {
								outStream.writeUTF(gson.toJson(query));
								area2.setText(inStream.readUTF().replace(',', '\n'));
								name.setText("");
								id.setText("");
								name.setText("");
								telephone.setText("");
								address.setText("");
								email.setText("");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
		 });
		    DeletePer.addActionListener(new ActionListener() {
		    	@Override
				public void actionPerformed(ActionEvent arg0) {
					panel2.remove(form2);
					form2.removeAll();
					app.repaint();
					JTextField id = new JTextField();
					JButton submit = new JButton("Удалить");
					JLabel id_l = new JLabel("ID персоны");
					Request query = new Request();
					form2 = new JPanel();
					id_l.setSize(100, 30);
					id_l.setLocation(10,10);
					submit.setSize(250, 30);
					submit.setLocation(10,50);
					id.setLocation(110,10);
					id.setSize(100, 30);
					form2.setLayout(null);
					form2.setSize(270, 100);
					form2.setLocation(800,10);
					form2.add(id_l);
					form2.add(submit);
					form2.add(id);
					form2.setBackground(Color.WHITE); 
					panel2.add(form2);
					app.repaint();
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "DeletePerson";
							query.data.put("person", id.getText());
							try {
								outStream.writeUTF(gson.toJson(query));
								area2.setText(inStream.readUTF().replace(',', '\n'));
								id.setText("");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}   
		    });
		    EditPer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel2.remove(form2);
					form2.removeAll();
					app.repaint();
					Request query = new Request();
					String []ops = {
							"Поставщик",
							"Потребитель",
					};
					String []ops2 = {
							"id",
							"name",
							"type",
							"address",
							"telephone",
							"email",
					};
					JComboBox param = new JComboBox(ops2);
					JComboBox type = new JComboBox(ops);
					JTextField value = new JTextField();
					JTextField id = new JTextField();
					
					boolean state = false;
					JButton submit = new JButton("Применить");
					JLabel param_l = new JLabel("Параметр:");
					JLabel id_l = new JLabel("Id персоны:");
					JLabel val_l = new JLabel("Значение:");
					form2 = new JPanel();
					param_l.setSize(100, 30);
					param_l.setLocation(10,10);
					id_l.setLocation(10,50);
					id_l.setSize(100, 30);
					id.setLocation(110,50);
					id.setSize(100, 30);
					val_l.setLocation(10,90);
					val_l.setSize(100, 30);
					value.setSize(100, 30);
					value.setLocation(110, 90);
					type.setSize(100, 30);
					type.setLocation(110, 90);
					submit.setSize(250, 30);
					submit.setLocation(10,130);
					param.setLocation(110,10);
					param.setSize(100, 30);
					form2.setLayout(null);
					form2.setSize(270, 170);
					form2.setLocation(800,10);
					form2.add(param_l);
					form2.add(val_l);
					form2.add(id_l);
					form2.add(id);
					form2.add(submit);
					form2.add(value);
					form2.add(param);
					form2.setBackground(Color.WHITE); 
					panel2.add(form2);
					app.repaint();
					param.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if((String) param.getSelectedItem() == "type") {
								form2.remove(value);
								form2.add(type);
							}else {
								if(type.getParent()!=null) {
									form2.remove(type);
									form2.add(value);
								}
							}
							app.repaint();
						}
					});
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "EditPerson";
							if((String) param.getSelectedItem() == "type") {
								switch((String) type.getSelectedItem()) {
								case "Потребитель":
									query.data.put("type", "0");
									break;
								case "Поставщик":
									query.data.put("type", "1");
									break;
								}
							}
							else {
								query.data.put((String) param.getSelectedItem(), value.getText());
								value.setText("");
							}
							try {
								query.data.put("person", id.getText());
								outStream.writeUTF(gson.toJson(query));
								area2.setText(inStream.readUTF().replace(',', '\n'));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}   
		    });
		    AddOp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel3.remove(form3);
					form3.removeAll();
					app.repaint();
					String []ops = {
							"Поставка",
							"Выдача",
					};
					JTextField id = new JTextField();
					JTextField person = new JTextField();
					JTextField product = new JTextField();
					JTextField quantity = new JTextField();
					JComboBox type = new JComboBox(ops);
					
			        JXDatePicker date = new JXDatePicker();
			        date.setDate(Calendar.getInstance().getTime());
			        date.setFormats(format);
					JButton submit = new JButton("Заказать");
					JLabel id_l = new JLabel("ID операции:");
					JLabel person_l = new JLabel("ID клиента");
					JLabel type_l = new JLabel("Тип:");
					JLabel product_l = new JLabel("ID продукта");
					JLabel date_l = new JLabel("Дата:");
					JLabel quantity_l = new JLabel("Кол-во:");
					Request query = new Request();
					form3 = new JPanel();
					id_l.setSize(100, 30);
					id_l.setLocation(10,10);
					id.setLocation(110,10);
					id.setSize(100, 30);
					
					type_l.setSize(100, 30);
					type_l.setLocation(10,50);
					type.setLocation(110,50);
					type.setSize(100, 30);
					
					person_l.setSize(100, 30);
					person_l.setLocation(10,90);
					person.setLocation(110,90);
					person.setSize(100, 30);
					
					product_l.setSize(100, 30);
					product_l.setLocation(10,130);
					product.setLocation(110,130);
					product.setSize(100, 30);
					
					quantity_l.setSize(100, 30);
					quantity_l.setLocation(10,170);
					quantity.setLocation(110,170);
					quantity.setSize(100, 30);
					
					date_l.setSize(100, 30);
					date_l.setLocation(10,210);
					date.setLocation(110,210);
					date.setSize(100, 30);
					
					submit.setSize(250, 30);
					submit.setLocation(10,250);

					form3.setLayout(null);
					form3.setSize(270, 290);
					form3.setLocation(800,10);
					form3.add(id_l);
					form3.add(id);
					form3.add(product_l);
					form3.add(product);
					form3.add(type_l);
					form3.add(type);
					form3.add(person);
					form3.add(person_l);
					form3.add(date);
					form3.add(date_l);
					form3.add(submit);
					form3.add(quantity);
					form3.add(quantity_l);
					form3.setBackground(Color.WHITE);
					panel3.add(form3);
					app.repaint();				
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "AddOperation";
							query.data.put("id", id.getText());
							query.data.put("product", product.getText());
							query.data.put("person", person.getText());
							query.data.put("date", format.format(date.getDate()));
							query.data.put("quantity", quantity.getText());
							switch((String) type.getSelectedItem()) {
							case "Выдача":
								query.data.put("type", "0");
								break;
							case "Поставка":
								query.data.put("type", "1");
								break;
							}
							
							int types=Integer.parseInt(query.data.get("type"));
							try {
								outStream.writeUTF(gson.toJson(query));
								area3.setText(inStream.readUTF().replace(',', '\n'));
								product.setText("");
								id.setText("");
								person.setText("");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
		    });
		    getOp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel3.remove(form3);
					form3.removeAll();
					app.repaint();
			        JXDatePicker date1 = new JXDatePicker();
			        JXDatePicker date2 = new JXDatePicker();
			        date1.setDate(Calendar.getInstance().getTime());
			        date1.setFormats(format);
					JButton submit = new JButton("Вывести");
					JLabel date1_l = new JLabel("Начальная:");
					JLabel date2_l = new JLabel("Конечная:");
					Request query = new Request();
					form3 = new JPanel();
					date1_l.setSize(100, 30);
					date1_l.setLocation(10,10);
					date1.setLocation(110,10);
					date1.setSize(100, 30);
					
					date2_l.setSize(100, 30);
					date2_l.setLocation(10,50);
					date2.setLocation(110,50);
					date2.setSize(100, 30);
					
					submit.setSize(250, 30);
					submit.setLocation(10,90);

					form3.setLayout(null);
					form3.setSize(270, 130);
					form3.setLocation(800,10);
					form3.add(date1_l);
					form3.add(date1);
					form3.add(date2_l);
					form3.add(date2);
					form3.add(submit);
					form3.setBackground(Color.WHITE);
					panel3.add(form3);
					app.repaint();				
					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							query.type = "ShowOperations";
							query.data.put("from", format.format(date1.getDate()));
							query.data.put("to", format.format(date2.getDate()));
							try {
								outStream.writeUTF(gson.toJson(query));
								area3.setText(inStream.readUTF().replace(',', '\n'));
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
					case "DeleteProduct":
						System.out.println("Введите id продукта");
						query.data.put("product", input.nextLine());
						break;
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