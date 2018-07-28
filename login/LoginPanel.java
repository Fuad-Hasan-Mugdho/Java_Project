package login;

import javax.swing.*;

import java.awt.event.*;
import java.sql.*;
import register.RegisterPanel;
import users.adminuser.AdminLoginPanel;
import users.normaluser.UserLoginPanel;
import db.DBConnectionProvider;
import main.Main;
import model.User;

public class LoginPanel extends JPanel
{
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JTextField textfield1;
	JPasswordField passwordfield;
	public static User user = new User();
	private static LoginPanel logInPanelRef = null;
	Connection conn = null;
	
	public LoginPanel()
	{
		
		setVisible(true);
		setBounds(50,100,350,200);
		setLayout(null);
		
		
		this.label1 = new JLabel();
		this.label1.setBounds(10,10,100,20);
		this.label1.setText("User Name");
		add(this.label1);
		
		this.textfield1 = new JTextField();
		this.textfield1.setBounds(110,10,200,20);
		this.textfield1.setText(null);
		add(this.textfield1);
		
		this.label2 = new JLabel();
		this.label2.setBounds(10,40,100,20);
		this.label2.setText("Password");
		add(this.label2);
		
		this.passwordfield = new JPasswordField();
		this.passwordfield.setText(null);
		this.passwordfield.setBounds(110,40,200,20);
		add(this.passwordfield);
		
		
		this.button1 = new JButton();
		this.button1.setText("Login");
		this.button1.setBounds(220,90,70,20);
		add(this.button1);
		
		this.button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			        try {
			            
			            conn = DBConnectionProvider.getDBConnection();
			            user.setUname(null);
			            Statement stmt = conn.createStatement();
			            String query = "SELECT * FROM user WHERE USER_NAME = '"+textfield1.getText()+"' and PASSWORD = '"+passwordfield.getText()+"'";
			            ResultSet rs = stmt.executeQuery(query);
			            while(rs.next()){ 
			                
			                user.setType(rs.getString("TYPE"));
			                user.setUname(rs.getString("USER_NAME"));
			                user.setClubName(rs.getString("CLUB_NAME"));
			            }
			            System.out.println(user.getUserName());
			            if (user.getType().matches("admin"))
				        {
			            	Main.MainRef().remove(LoginPanel.LogInlRef());
			            	Main.MainRef().add(AdminLoginPanel.adminloginlRef());
			            	Main.MainRef().pack();
			            	Main.MainRef().setBounds(5,5,500,400);
				        }
			            else if(user.getType().matches("user"))
			            {
			            	System.out.println("User");
			            	
			            	Main.MainRef().remove(LoginPanel.LogInlRef());
			            	Main.MainRef().add(UserLoginPanel.UserLoginlRef());
			            	Main.MainRef().pack();
			            	Main.MainRef().setBounds(5,5,500,400);
			            }
			            
			        } catch (Exception ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(null,"Fail to login");
			        }
			       
			
			}	
		});
		this.button2= new JButton();
		this.button2.setText("Clean");
		this.button2.setBounds(130,90,70,20);
		add(this.button2);
		
		this.button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textfield1.setText(null);
				passwordfield.setText(null);
			}	
		});
		
		this.button3= new JButton();
		this.button3.setText("Register >");
		this.button3.setBounds(135,260,100,20);
		add(this.button3);
		
		this.button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.MainRef().remove(LoginPanel.LogInlRef());
				Main.MainRef().add(RegisterPanel.RegisterRef());
				Main.MainRef().pack();
				Main.MainRef().setBounds(50,100,450,480);
			}	
			
		});
		this.button4= new JButton();
		this.button4.setText("Cancel");
		this.button4.setBounds(250,260,100,20);
		add(this.button4);
		
		this.button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.MainRef().dispose();
				
			}	
			
		});
		setBounds(5,5,400,350);
	}
	public static void clean()
	{
		logInPanelRef = null;
	}

	public static LoginPanel LogInlRef(){
		if(logInPanelRef==null)
		{
			logInPanelRef = new LoginPanel();
		}
		return logInPanelRef; 
	}

}