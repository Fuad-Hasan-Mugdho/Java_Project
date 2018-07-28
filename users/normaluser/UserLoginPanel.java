package users.normaluser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import login.LoginPanel;
import users.ManagePanel;
import users.ProfilePanel;
import users.adminuser.club.CerateClub;
import users.normaluser.event.EventLists;
import users.normaluser.event.UserEventHistory;
import main.Main;

public class UserLoginPanel extends JPanel {
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JLabel label1;
	public static UserLoginPanel UserLoginpanel = null;
	public UserLoginPanel()
	{
		setVisible(true);
		setBounds(0,0,500,400);
		setLayout(null);
		this.label1 = new JLabel();
		this.label1.setText("The User Panel");
		this.label1.setBounds(200, 10, 300, 20);
		add(this.label1);
		this.button1 = new JButton();
		this.button1.setBounds(190, 110, 130, 20);
		this.button1.setText("view member");
		add(this.button1);
		this.button1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				Main.MainRef().remove(UserLoginlRef());
				Main.MainRef().add(UserViewMember.UserViewMemberlRef());
				Main.MainRef().pack();
            	Main.MainRef().setBounds(50,100,970,550);
			}	
			
		});
		
		this.button2 = new JButton();
		this.button2.setBounds(190, 150, 130, 20);
		this.button2.setText("user event History");
		add(this.button2);
		this.button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.MainRef().remove(UserLoginlRef());
				Main.MainRef().add(EventLists.EventListslRef());
				Main.MainRef().pack();
				Main.MainRef().setBounds(50,100,1000,400);
			}	
			
		});
		
		this.button3 = new JButton();
		this.button3.setBounds(190, 190, 130, 20);
		this.button3.setText("All event History");
		add(this.button3);
		this.button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.MainRef().remove(UserLoginlRef());
				Main.MainRef().add(UserEventHistory.UserEventHistorylRef());
				Main.MainRef().pack();
				Main.MainRef().setBounds(50,100,970,550);
			}	
			
		});
		
		this.button4 = new JButton();
		this.button4.setBounds(190, 230, 130, 20);
		this.button4.setText("Logout");
		add(this.button4);
		this.button4.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				LoginPanel.clean();
				Main.MainRef().remove(UserLoginlRef());
				Main.MainRef().add(LoginPanel.LogInlRef());
				Main.MainRef().pack();
	        	Main.MainRef().setBounds(50,100,400,350);
	        	
			}	
			
		});
		this.button5 = new JButton();
		this.button5.setBounds(190, 70, 130, 20);
		this.button5.setText("Manage profile");
		add(this.button5);
        this.button5.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				ProfilePanel.cl();
				Main.MainRef().remove(UserLoginlRef());
				Main.MainRef().add(ProfilePanel.ProfilelRef());
				Main.MainRef().pack();
	        	Main.MainRef().setBounds(50,100,400,350);
	        	
			}	
			
		});
	}
	public static UserLoginPanel UserLoginlRef(){
		if(UserLoginpanel==null)
		{
			UserLoginpanel = new UserLoginPanel();
		}
		return UserLoginpanel; 
	}

}

