package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.*;

import db.DBConnectionProvider;
import login.LoginPanel;
import login.UserLoginPanel;
import main.Main;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Vector;

public class EventLists extends JPanel{
	JTable eventTable;
	public static EventLists EventLists = null;
	Connection con;
	Statement st;

	JButton button1;
	
	
	public EventLists(){
			this.setVisible(true);
			this.setBounds(0,0,1000,400);
			this.setLayout(null);
			
			Vector<Vector<String>> vectorRow=new Vector<Vector<String>>();
		try{
			con = DBConnectionProvider.getDBConnection();
			st=con.createStatement();
				String sql="SELECT * FROM assigned WHERE STUDENT_ID='"+LoginPanel.user.getUserName()+"' ";
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("EVENT_ID"));
				row.add(rs.getString("EVENT_NAME"));
				row.add(rs.getString("STUDENT_ID"));
				row.add(rs.getString("FIRST_NAME"));
				row.add(rs.getString("LAST_NAME"));
				
				vectorRow.add(row);
				}
			}catch(Exception ex){
			    JOptionPane.showMessageDialog(this, ex);
	    }  	
		
		Vector<String> columnNames = new Vector<String>();
			columnNames.add("EVENT_ID");
			columnNames.add("EVENT_NAME");
			columnNames.add("STUDENT_ID");
			columnNames.add("FIRST_NAME");
			columnNames.add("LAST_NAME");
			
			this.eventTable=new JTable(vectorRow,columnNames);
			JScrollPane sp = new JScrollPane(eventTable);
			sp.setBounds(30,30,940,250);
			this.add(sp);
		
			this.button1=new JButton();
			this.button1.setBounds(450,300,100,20);
			this.button1.setText("<Back");
			add(this.button1);
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
					{
						 try {
							 Main.MainRef().remove(EventListslRef());
								Main.MainRef().add(UserLoginPanel.UserLoginlRef());
								Main.MainRef().pack();
								Main.MainRef().setBounds(50,100,500,400);
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
		        }
					}});
	}
				
		
		public static EventLists EventListslRef(){
			if(EventLists==null)
			{
				EventLists = new EventLists();
			}
			return EventLists; 
		}
	
}