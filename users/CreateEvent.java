package users;

import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;

import javax.swing.*;
import java.util.Date;
import db.DBConnectionProvider;
import main.Main;
import model.Club;
import model.Event;
import users.adminuser.AdminLoginPanel;
import users.adminuser.event.AssaingMember;

import com.toedter.calendar.JDateChooser;

public class CreateEvent extends JPanel{
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JTextField textfield1;
	JTextField textfield2;
	JButton button1;
	JButton button2;
	JComboBox combobox;
	JDateChooser sdate;
	JDateChooser edate;
	Connection conn = null;
	public static CreateEvent CreateEvent = null;
	public static Club club = new Club();
	public static Event event = new Event();
	public static String s;
	public CreateEvent()
	{
		setVisible(true);
		setBounds(30,70,500,400);
		setLayout(null);
		label1 = new JLabel();
		label1.setBounds(30,30,100,20);
		label1.setText("Event ID");
		add(label1);
		textfield1 = new JTextField();
		textfield1.setBounds(150,30,200,20);
		textfield1.setText(null);
		add(textfield1);
		
		label2 = new JLabel();
		label2.setBounds(30,60,100,20);
		label2.setText("Event name");
		add(label2);
		
		textfield2 = new JTextField();
		textfield2.setBounds(150,60,200,20);
		textfield2.setText(null);
		add(textfield2);
		
		label3 = new JLabel();
		label3.setBounds(30,90,100,20);
		label3.setText("Start Time");
		add(label3);
		
		sdate = new JDateChooser("yyyy-MM-dd","####-##-##",'_');
		sdate.setBounds(150,90,200,20);
		add(sdate);
		
		label4 = new JLabel();
		label4.setBounds(30,120,100,20);
		label4.setText("End Time");
		add(label4);
		
		
		edate = new JDateChooser("yyyy-MM-dd","####-##-##",'_');
		edate.setBounds(150,120,200,20);
		add(edate);
		
		
		
		this.label5 = new JLabel();
		this.label5.setText("Club Name");
		this.label5.setBounds(30,150,100,20);
		add(this.label5);
		this.combobox = new JComboBox();
		this.combobox.setBounds(150,150,200,20);
		add(this.combobox);
		
		 try {
			 
				conn = DBConnectionProvider.getDBConnection();
				combobox.addItem("---");        
				Statement stmt = conn.createStatement();
				String query = "SELECT Club_Name  FROM clubs";
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
				    combobox.addItem(""+rs.getString("Club_Name"));
			    }
				            
				} catch (Exception ex) {
					 System.out.println(ex.getMessage());
				}
		 combobox.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					club.setClubName(String.valueOf(combobox.getSelectedItem()));
					
				}});

		 
		button1 =new JButton();
		button1.setText("Submit");
		button1.setBounds(270,180,100,20);
		add(button1);
		this.button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 try {
			            
					 event.setEvent(textfield1.getText());
					 event.setEventName(textfield2.getText());
					 conn = DBConnectionProvider.getDBConnection();
					 Statement stmt = conn.createStatement();
					 String query = "INSERT INTO `clubmanagement`.`events` (`EVENT_ID`, `EVENT_NAME`, `EVENT_STARD`, `EVENT_END`, `CLUB_NAME`) VALUES ('"+textfield1.getText()+"', '"+textfield2.getText()+"', '"+sdate.getDate().toString()+"', '"+edate.getDate().toString()+"', '"+combobox.getSelectedItem()+"');";
				     s=query;
				     AssaingMember.clear();
					 Main.MainRef().remove(CreateEventRef());
					 Main.MainRef().add(AssaingMember.AssaingMemberlRef());
					 Main.MainRef().pack();
					 Main.MainRef().setBounds(50,100,970,550);
			            
			        } catch (Exception ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(null,"failed plz try again");					
					}
		}
	});
		
		button2 =new JButton();
		button2.setText("< Back");
		button2.setBounds(100,180,100,20);
		add(button2);
		
		this.button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.MainRef().remove(CreateEventRef());
				Main.MainRef().add(AdminLoginPanel.adminloginlRef());
				Main.MainRef().pack();
				Main.MainRef().setBounds(50,100,500,400);
			}	
			
		});
		
		Main.MainRef().pack();
    	Main.MainRef().setBounds(0,0,500,400);
	}
	public static void clean()
	{
		CreateEvent = null;
	}
	public static CreateEvent CreateEventRef(){
		if(CreateEvent==null)
		{
			CreateEvent = new CreateEvent();
		}
		return CreateEvent; 
	}
}

