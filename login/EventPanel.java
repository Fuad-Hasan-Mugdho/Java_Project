package login;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import db.DBConnectionProvider;
import main.Main;
import users.CreateEvent;

public class EventPanel extends JPanel {
	JComboBox combobox;
	JLabel label1;
	JTable table;
	JButton button;
	JButton button1;
	JButton button2;
	Connection conn =null;
	Vector<Vector<String>> vectorRow=new Vector<Vector<String>>();
	Vector<String> columnNames = new Vector<String>();
	public static EventPanel eventpanel = null;
	public EventPanel()
	{
		setVisible(true);
		setBounds(10,10,900,550);
		setLayout(null);
		
		this.label1 = new JLabel();
		this.label1.setText("Club Name");
		this.label1.setBounds(10, 10, 100, 20);
		add(this.label1);
		this.combobox = new JComboBox();
		this.combobox.setBounds(150,10,200,20);
		add(this.combobox);
		columnNames.add("EVENT_ID");
		columnNames.add("EVENT_NAME");
		columnNames.add("EVENT_STARD");
		columnNames.add("EVENT_END");
		table=new JTable(vectorRow,columnNames);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(30,70,860,350);
		add(sp);
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
							Statement st;
								st=conn.createStatement();
								String sql="SELECT * FROM events Where CLUB_NAME ='"+combobox.getSelectedItem()+"'";
								ResultSet rs = st.executeQuery(sql);
								
								while(rs.next()){
								Vector row = new Vector();
								row.add(rs.getString("EVENT_ID"));
								row.add(rs.getString("EVENT_NAME"));
								row.add(rs.getString("EVENT_STARD"));
								row.add(rs.getString("EVENT_END"));
								
								vectorRow.add(row);

							}
							
							
							
							add(sp);
							
				        } catch (Exception ex) {
							System.out.println(ex.getMessage());
				        }
			

			}
				});
		this.button=new JButton();
		this.button.setBounds(500,10,100,20);
		this.button.setText("Create");
		add(this.button);
		this.button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
				{
					 try {
						 Main.MainRef().remove(EventPanellRef());
			            	Main.MainRef().add(CreateEvent.CreateEventRef());
			            	Main.MainRef().pack();
			            	Main.MainRef().setBounds(5,5,500,400);
				         } catch (Exception ex) {
							System.out.println(ex.getMessage());
				         }
			

			}
				});
		this.button1=new JButton();
		this.button1.setBounds(630,10,75,20);
		this.button1.setText("<Back");
		add(this.button1);
		this.button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
				{
					 try {
						 Main.MainRef().remove(EventPanellRef());
						 eventpanel = null;
						Main.MainRef().add(AdminLoginPanel.adminloginlRef());
						Main.MainRef().pack();
						Main.MainRef().setBounds(50,100,500,400);
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
	        }
				}});
}
			
	
	public static EventPanel EventPanellRef(){
		if(eventpanel==null)
		{
			eventpanel = new EventPanel();
		}
		return eventpanel; 
	}
}