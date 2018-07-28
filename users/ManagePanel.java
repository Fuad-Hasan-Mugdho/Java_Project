package users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Main;
import users.adminuser.AdminLoginPanel;
import users.adminuser.club.AddMember;
import users.adminuser.club.ViewMember;
import users.adminuser.event.EventPanel;

public class ManagePanel extends JPanel {

	JButton button1;
	JButton button2;
	JButton button3;
	JLabel label1;
	
	
	public static ManagePanel Managepanel = null;
	public ManagePanel()
	{
		setVisible(true);
		setBounds(10,10,350,250);
		setLayout(null);
		this.label1 = new JLabel();
		this.label1.setText("The Member Manage Panel");
		this.label1.setBounds(200, 10, 300, 30);
		add(this.label1);
		this.button1 = new JButton();
		this.button1.setBounds(190, 110, 130, 30);
		this.button1.setText("View Member");
		add(this.button1);
		this.button1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				Main.MainRef().remove(ManagepanellRef());
				Main.MainRef().add(ViewMember.ViewMemberlRef());
				Main.MainRef().pack();
            	Main.MainRef().setBounds(50,100,970,550);
			}	
			
		});
		
		this.button2 = new JButton();
		this.button2.setBounds(190, 150, 130, 30);
		this.button2.setText("Add Member");
		add(this.button2);
		this.button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.MainRef().remove(ManagepanellRef());
				Main.MainRef().add(AddMember.AddMemberRef());
				Main.MainRef().pack();
				Main.MainRef().setBounds(50,100,970,550);
			}	
			
		});
		this.button3=new JButton();
		this.button3.setBounds(190, 190, 130, 30);
		this.button3.setText("<Back");
		add(this.button3);
		this.button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
				{
					 try {
						 Main.MainRef().remove(ManagepanellRef());
						Main.MainRef().add(AdminLoginPanel.adminloginlRef());
						Main.MainRef().pack();
						Main.MainRef().setBounds(5,5,500,400);
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
	        }
				}});
		
}
			
	
	public static ManagePanel ManagepanellRef(){
		if(Managepanel==null)
		{
			Managepanel = new ManagePanel();
		}
		return Managepanel; 
	}
}
