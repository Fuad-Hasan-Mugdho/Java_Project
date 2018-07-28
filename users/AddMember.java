package users;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import db.DBConnectionProvider;
import main.Main;
import model.User;
public class AddMember extends JPanel {
	JComboBox combobox;
	JComboBox combobox1;
	JLabel label,label1;
	JTable table;
	JButton button;
	JButton button1;
	JButton button2;
	JButton button3;
	JTextField txt;
	User us;
	String Uname,Fname,Lname,Email,Phone,Dname,cgpa,Cname,pass;
	
	public Connection conn =null;
	Statement st;
	JScrollPane sp;
	Vector<Vector<String>> vectorRow=new Vector<Vector<String>>();
	Vector<String> columnNames = new Vector<String>();
	public static AddMember AddMember = null;
	public AddMember()
	{
		setVisible(true);
		setBounds(10,10,900,550);
		setLayout(null);
		
		this.label1 = new JLabel();
		this.label1.setText("Club Name");
		this.label1.setBounds(10, 10, 100, 20);
		add(this.label1);
		
		this.label = new JLabel();
		this.label.setText("User Name");
		this.label.setBounds(10, 35, 100, 20);
		add(this.label);
		
		this.txt = new JTextField();
		this.txt.setBounds(150,35,200,20);
		this.txt.setText(null);
		add(this.txt);
		
		this.combobox1 = new JComboBox();
		this.combobox1.setBounds(380,35,200,20);
		add(this.combobox1);
		combobox1.addItem("---");
		combobox1.addItem("admin");
		combobox1.addItem("user");
		combobox1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			combobox1.getSelectedItem();		
			}});
		
		this.combobox = new JComboBox();
		this.combobox.setBounds(150,10,200,20);
		add(this.combobox);
		columnNames.add("USER_NAME");
		columnNames.add("FIRST_NAME");
		columnNames.add("LAST_NAME");
		columnNames.add("DEPARTMENT");
		columnNames.add("CGPA");
		columnNames.add("Club_Name");
		columnNames.add("Email");
		columnNames.add("Phone");
		table=new JTable(vectorRow,columnNames);
		sp = new JScrollPane(table);
		sp.setBounds(30,70,860,350);
		add(sp);
		 try {
			 
				conn = DBConnectionProvider.getDBConnection();
				combobox.addItem("---");        
				Statement st = conn.createStatement();
				String query = "SELECT Club_Name  FROM clubs";
				ResultSet rs = st.executeQuery(query);
				while(rs.next())
				{
				    combobox.addItem(""+rs.getString("Club_Name"));
			    }
				            
				} catch (Exception ex) {
					 System.out.println(ex.getMessage());
				}
		 combobox.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
				combobox.getSelectedItem();		
				}});	
		this.button=new JButton();
		this.button.setBounds(380,10,100,20);
		this.button.setText("Show");
		add(this.button);
		this.button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
				{
					 try {
						 vectorRow.clear();
						 txt.setText(null);
							combobox.getSelectedItem();
							Statement st;
							st=conn.createStatement();
							String sql="SELECT * FROM registration Where Club_Name ='"+combobox.getSelectedItem()+"'";
							System.out.println(combobox.getSelectedItem());
							ResultSet rs = st.executeQuery(sql);
							
							while(rs.next()){
							Vector row = new Vector();
							row.add(rs.getString("USER_NAME"));
							row.add(rs.getString("FIRST_NAME"));
							row.add(rs.getString("LAST_NAME"));
							row.add(rs.getString("DEPARTMENT"));
							row.add(rs.getString("CGPA"));
							row.add(rs.getString("Club_Name"));
							row.add(rs.getString("Email"));
							row.add(rs.getString("Phone"));
							vectorRow.add(row);
							}
							add(sp);
							
							
							
				        } catch (Exception ex) {
							System.out.println(ex.getMessage());
				        }
			

			}
				});
		this.button1=new JButton();
		this.button1.setBounds(580,10,75,20);
		this.button1.setText("<Back");
		add(this.button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
				{
					 try {
						 Main.MainRef().remove(AddMemberRef());
						 AddMember = null;
						 Main.MainRef().add(ManagePanel.ManagepanellRef());
						 Main.MainRef().pack();
						 Main.MainRef().setBounds(50,100,500,400);
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
	        }
				}});
		button2=new JButton();
		button2.setBounds(230,440,150,20);
		button2.setText("Calcel Registration");
		add(button2);
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				try {
					int row = table.getSelectedRow();
					Uname = (table.getModel().getValueAt(row, 0).toString());
					Fname = (table.getModel().getValueAt(row, 1).toString());
					 Lname = (table.getModel().getValueAt(row, 2).toString());
					Dname = (table.getModel().getValueAt(row, 3).toString());
					cgpa = (table.getModel().getValueAt(row, 4).toString());
					Cname = (table.getModel().getValueAt(row, 5).toString());
					Email = (table.getModel().getValueAt(row, 6).toString());
					Phone = (table.getModel().getValueAt(row, 7).toString());
					
				txt.setText(Uname);
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		this.button2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){	
		String sql2 ="DELETE FROM registration WHERE USER_NAME= '"+Uname+"'";
		try{
			st=conn.createStatement();
			st.executeUpdate(sql2);
			JOptionPane.showMessageDialog(null,"Data Deleted");
			refresh();
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Failed to Delete.\nFirst select a row ");
				}
			}	
		});
		
		button3 = new JButton();
		button3.setBounds(450,440,150,20);
		button3.setText("Add Into Club");
		add(button3);
		this.button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				try{
					
					
					String sq11 = null;
					if(String.valueOf(combobox1.getSelectedItem()).matches("admin"))
					{
						sq11 ="INSERT INTO `clubmanagement`.`user` (`USER_NAME`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `TYPE`, `CLUB_NAME`, `Department`, `EMAIL`, `PHONE`, `CGPA`) VALUES ('"+Uname+"', '"+Fname+"', '"+Lname+"', '"+pass+"', '"+String.valueOf(combobox1.getSelectedItem())+"', 'all', '"+Dname+"', '"+Email+"', '"+Phone+"', '"+cgpa+"');";
					}
					
					else if(String.valueOf(combobox1.getSelectedItem()).matches("user"))
					{
						sq11 ="INSERT INTO `clubmanagement`.`user` (`USER_NAME`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `TYPE`, `CLUB_NAME`, `Department`, `EMAIL`, `PHONE`, `CGPA`) VALUES ('"+Uname+"', '"+Fname+"', '"+Lname+"', '"+pass+"', '"+String.valueOf(combobox1.getSelectedItem())+"', '"+Cname+"', '"+Dname+"', '"+Email+"', '"+Phone+"', '"+cgpa+"');";
					}
					String sql2 ="DELETE FROM registration WHERE USER_NAME= '"+Uname+"'";
					Statement st = null;
					st=conn.createStatement();
					st.executeUpdate(sq11);
					st.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null,"Member Addded");
					refresh();
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"Failed to Delete.\nFirst select a row ");
						}
			}});
		
}
			
public void refresh()
	{
		 try {
			 vectorRow.clear();
				combobox.getSelectedItem();
				Statement st;
				st=conn.createStatement();
				String sql="SELECT * FROM registration Where Club_Name ='"+combobox.getSelectedItem()+"'";
				System.out.println(combobox.getSelectedItem());
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("FIRST_NAME"));
				row.add(rs.getString("LAST_NAME"));
				row.add(rs.getString("USER_NAME"));
				row.add(rs.getString("DEPARTMENT"));
				row.add(rs.getString("CGPA"));
				row.add(rs.getString("Club_Name"));
				row.add(rs.getString("Email"));
				row.add(rs.getString("Phone"));
				vectorRow.add(row);
				}
				add(sp);
				
				
				
	        } catch (Exception ex) {
				System.out.println(ex.getMessage());
	        }
	}	

	public static AddMember AddMemberRef(){
		if(AddMember==null)
		{
			AddMember = new AddMember();
		}
		return AddMember; 
	}
}