package users;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import db.DBConnectionProvider;
import login.LoginPanel;
import main.Main;
import model.User;
import users.adminuser.AdminLoginPanel;
import users.normaluser.UserLoginPanel;


public class ProfilePanel extends JPanel {
	JLabel label;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JTextField textfield1;
	JTextField textfield2;
	JPasswordField passwordfield1;
	JPasswordField passwordfield2;
	JButton button1;
	private static ProfilePanel ProfilePanellRef = null;
	Connection conn = null;
	public ProfilePanel()
	{
		
		setVisible(true);
		setBounds(5,5,400,450);
		setLayout(null);
		
		this.label = new JLabel();
		this.label.setBounds(150,10,400,20);
		this.label.setText("User Name :: "+LoginPanel.user.getUserName());
		add(this.label);
		
		this.label1 = new JLabel();
		this.label1.setBounds(10,50,100,20);
		this.label1.setText("Email");
		add(this.label1);
		
		this.textfield1 = new JTextField();
		this.textfield1.setBounds(150,50,200,20);
		this.textfield1.setText(null);
		add(this.textfield1);
		
		this.label2 = new JLabel();
		this.label2.setBounds(10,90,100,20);
		this.label2.setText("Phone");
		add(this.label2);
		
		this.textfield2 = new JTextField();
		this.textfield2.setBounds(150,90,200,20);
		this.textfield2.setText(null);
		add(this.textfield2);
		
		this.label4 = new JLabel();
		this.label4.setBounds(10,130,200,20);
		this.label4.setText("Password");
		add(this.label4);
		
		this.passwordfield1 = new JPasswordField();
		this.passwordfield1.setBounds(150,130,200,20);
		this.passwordfield1.setText(null);
		add(this.passwordfield1);
		
		this.label5 = new JLabel();
		this.label5.setBounds(10,170,200,20);
		this.label5.setText("Confirm Password");
		add(this.label5);
		
		this.passwordfield2 = new JPasswordField();
		this.passwordfield2.setBounds(150,170,200,20);
		this.passwordfield2.setText(null);
		add(this.passwordfield2);
		
		try {
            
			conn = DBConnectionProvider.getDBConnection();
			        
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM user where USER_NAME ='"+LoginPanel.user.getUserName()+"'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				textfield1.setText(rs.getString("EMAIL"));
			    passwordfield1.setText(rs.getString("PASSWORD"));
			    textfield2.setText(rs.getString("PHONE"));
			    
		    }
			
		    
			} catch (Exception ex) {
				 System.out.println(ex.getMessage());
			}
	
	
	
		
		this.button1 = new JButton();
		this.button1.setText("Update");
		this.button1.setBounds(150, 210, 100, 20);
		add(this.button1);
		this.button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
			            
						conn = DBConnectionProvider.getDBConnection();
						        
						Statement stmt = conn.createStatement();
						String query = "UPDATE `clubmanagement`.`user` SET `PASSWORD`='"+passwordfield1.getText()+"', `EMAIL`='"+textfield1.getText()+"', `PHONE`='"+textfield2.getText()+"' WHERE `USER_NAME`='"+LoginPanel.user.getUserName()+"'";
						 stmt.executeUpdate(query);
						 JOptionPane.showMessageDialog(null,"Update successfull");
						 Main.MainRef().remove(ProfilePanel.ProfilelRef());
						if(LoginPanel.user.getType().matches("admin"))	
						{
							 Main.MainRef().add(AdminLoginPanel.adminloginlRef());
						}
						else if(LoginPanel.user.getType().matches("user"))
						{
							Main.MainRef().add(UserLoginPanel.UserLoginlRef());
						}
						 Main.MainRef().pack();
						 Main.MainRef().setBounds(5,5,500,400);
					    
						} catch (Exception ex) {
							 System.out.println(ex.getMessage());
						}
				}
		 });
		conn = DBConnectionProvider.getDBConnection();
			        
					
}
	public static void cl()
	{
		ProfilePanellRef = null;
	}
	public static ProfilePanel ProfilelRef(){
		if(ProfilePanellRef==null)
		{
			ProfilePanellRef = new ProfilePanel();
		}
		return ProfilePanellRef;
	}
	
}