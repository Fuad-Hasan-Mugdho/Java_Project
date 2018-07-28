package users.adminuser.club;

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
import users.ManagePanel;
public class ViewMember extends JPanel {
	JComboBox combobox;
	JLabel label,label1;
	JTable table;
	JButton button;
	JButton button1;
	JButton button2;
	JTextField txt;
	User us;
	public static String uname;
	
	public Connection conn =null;
	Statement st;
	JScrollPane sp;
	Vector<Vector<String>> vectorRow=new Vector<Vector<String>>();
	Vector<String> columnNames = new Vector<String>();
	public static ViewMember ViewMember = null;
	public ViewMember()
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
		
		this.combobox = new JComboBox();
		this.combobox.setBounds(150,10,200,20);
		add(this.combobox);
		columnNames.add("USER_NAME");
		columnNames.add("FIRST_NAME");
		columnNames.add("LAST_NAME");
		columnNames.add("Department");
		columnNames.add("EMAIL");
		columnNames.add("PHONE");
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
						    txt.setText(null);
						    vectorRow.clear();
							combobox.getSelectedItem();
							st=conn.createStatement();
							String sql="SELECT * FROM user Where CLUB_NAME ='"+combobox.getSelectedItem()+"'";
							System.out.println(combobox.getSelectedItem());
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
						 Main.MainRef().remove(ViewMemberlRef());
						 ViewMember = null;
						 Main.MainRef().add(ManagePanel.ManagepanellRef());
						 Main.MainRef().pack();
						 Main.MainRef().setBounds(50,100,500,400);
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
	        }
				}});
		button2=new JButton();
		button2.setBounds(350,440,200,20);
		button2.setText("Delete Club Member");
		add(button2);
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				try {
					int row = table.getSelectedRow();
					uname = (table.getModel().getValueAt(row, 0).toString());
					
				txt.setText(uname);
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		this.button2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){	
		String sql2 ="DELETE FROM user WHERE USER_NAME= '"+txt.getText()+"'";  			
		try{
			st.executeUpdate(sql2);
			JOptionPane.showMessageDialog(null,"Data Deleted");
			refresh();
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Failed to Delete.\nFirst select a row ");
				}
			}	
		});
		
		
		
}
			
public void refresh()
	{
		 try {
			    vectorRow.clear();
				combobox.getSelectedItem();
				Statement st1;
				st1=conn.createStatement();
				String sql1="SELECT * FROM user Where CLUB_NAME ='"+combobox.getSelectedItem()+"'";
				System.out.println(combobox.getSelectedItem());
				ResultSet rs = st1.executeQuery(sql1);
				
				while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("USER_NAME"));
				row.add(rs.getString("TYPE"));
				row.add(rs.getString("CLUB_NAME"));
				row.add(rs.getString("Department"));
				row.add(rs.getString("EMAIL"));
				row.add(rs.getString("PHONE"));
				vectorRow.add(row);
				}
				this.add(sp);
				
				
				
	        } catch (Exception ex) {
				System.out.println(ex.getMessage());
	        }
	}	

	public static ViewMember ViewMemberlRef(){
		if(ViewMember==null)
		{
			ViewMember = new ViewMember();
		}
		return ViewMember; 
	}
}
