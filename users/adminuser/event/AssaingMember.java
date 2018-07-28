package users.adminuser.event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.*;

import db.DBConnectionProvider;
import login.LoginPanel;
import main.Main;
import model.Assigned;
import users.CreateEvent;
import users.adminuser.AdminLoginPanel;
import users.normaluser.UserLoginPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Vector;

public class AssaingMember extends JPanel{
	JTable eventTable;
	public static AssaingMember AssaingMember = null;
	Connection con;
	Statement st;
    Assigned assi;
	JButton button1;
	JButton button2;
	String Uname,Fname,Lname,Email,Phone;
	public AssaingMember(){
			this.setVisible(true);
			this.setBounds(0,0,1000,400);
			this.setLayout(null);
			
			Vector<Vector<String>> vectorRow=new Vector<Vector<String>>();
		try{
			con = DBConnectionProvider.getDBConnection();
			st=con.createStatement();
				String sql="SELECT * FROM user WHERE CLUB_NAME='"+CreateEvent.club.getClubName()+"' ";
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("USER_NAME"));
				row.add(rs.getString("FIRST_NAME"));
				row.add(rs.getString("LAST_NAME"));
				row.add(rs.getString("Department"));
				row.add(rs.getString("EMAIL"));
				row.add(rs.getString("PHONE"));
				
				vectorRow.add(row);
				}
			}catch(Exception ex){
			    JOptionPane.showMessageDialog(this, ex);
	    }  	
		
		Vector<String> columnNames = new Vector<String>();
			columnNames.add("USER_NAME");
			columnNames.add("FIRST_NAME");
			columnNames.add("LAST_NAME");
			columnNames.add("Department");
			columnNames.add("EMAIL");
			columnNames.add("PHONE");
			
			this.eventTable=new JTable(vectorRow,columnNames);
			JScrollPane sp = new JScrollPane(eventTable);
			sp.setBounds(30,30,940,250);
			this.add(sp);
		
			eventTable.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e)
				{
					try {
						int row = eventTable.getSelectedRow();
					 Uname = (String.valueOf(eventTable.getModel().getValueAt(row, 0)));
					Fname = (String.valueOf(eventTable.getModel().getValueAt(row, 1)));	
					 Lname = (String.valueOf(eventTable.getModel().getValueAt(row, 2)));
					 Email = (String.valueOf(eventTable.getModel().getValueAt(row, 4)));
					 Phone = (String.valueOf(eventTable.getModel().getValueAt(row, 5)));
					 
					    
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			});
			
			
			this.button1=new JButton();
			this.button1.setBounds(420,300,100,20);
			this.button1.setText("Create");
			add(this.button1);
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
					{
						 try {
							 Connection conn = DBConnectionProvider.getDBConnection();
							 Statement stmt = conn.createStatement();
							 String query = "SELECT * FROM assigned where EVENT_ID ='"+CreateEvent.event.getEventId()+"'";
							 int count = 0 ;
							 ResultSet rs = stmt.executeQuery(query);
							 while(rs.next())
							 {
								 count=1;
							 }
							 if(count == 1)
							 {
								 try{
									 stmt.executeUpdate(CreateEvent.s);
									 JOptionPane.showMessageDialog(null,"Event created");
									    Main.MainRef().remove(AssaingMemberlRef());
									    AssaingMember = null;
									    Main.MainRef().add(AdminLoginPanel.adminloginlRef());
										Main.MainRef().pack();
										Main.MainRef().setBounds(5,5,500,400);
								}catch (Exception ex) {
									System.out.println(ex.getMessage());
						        }
							 }else if(count == 0)
							 {
								 JOptionPane.showMessageDialog(null,"Add atleast one member");
							 }
								 
							 }
				catch (Exception ex) {
					System.out.println(ex.getMessage());
		        }
					}});
			
			this.button2 = new JButton();
			this.button2.setBounds(280,300,100,20);
			this.button2.setText("Add");
			add(this.button2);
			button2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
					{
						 try {
							 Connection conn = DBConnectionProvider.getDBConnection();
							 Statement stmt = conn.createStatement();
							 String query = "INSERT INTO `clubmanagement`.`assigned` (`EVENT_ID`, `EVENT_NAME`, `STUDENT_ID`, `FIRST_NAME`, `LAST_NAME`, `PHONE`, `EMAIL`, `CLUB_NAME`) VALUES ('"+CreateEvent.event.getEventId()+"', '"+CreateEvent.event.getEventName()+"', '"+Uname+"', '"+Fname+"', '"+Lname+"', '"+Phone+"', '"+Email+"', '"+CreateEvent.club.getClubName()+"')";
							 stmt.executeUpdate(query);
							 JOptionPane.showMessageDialog(null,"Member added");
							 
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Already added");
		        }
					}});
			
	}
				
		public static void clear()
		{
			AssaingMember = null;
		}
		public static AssaingMember AssaingMemberlRef(){
			if(AssaingMember==null)
			{
				AssaingMember = new AssaingMember();
			}
			return AssaingMember; 
		}
	
}