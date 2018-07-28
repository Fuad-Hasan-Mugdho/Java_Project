package users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.DBConnectionProvider;
import login.AdminLoginPanel;
import main.Main;


public class CerateClub extends JPanel{
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JTextField textfield1;
	JTextField textfield2;
	JButton button1;
	JButton button2;
	Connection conn = null;
	public static CerateClub clubpanel = null;
	public CerateClub()
	{
		setVisible(true);
		setBounds(30,70,500,400);
		setLayout(null);
		label1 = new JLabel();
		label1.setBounds(30,70,100,20);
		label1.setText("Club ID");
		add(label1);
		textfield1 = new JTextField();
		textfield1.setBounds(150,70,200,20);
		textfield1.setText(null);
		add(textfield1);
		
		label2 = new JLabel();
		label2.setBounds(30,100,100,20);
		label2.setText("Club name");
		add(label2);
		
		textfield2 = new JTextField();
		textfield2.setBounds(150,100,200,20);
		textfield2.setText(null);
		add(textfield2);
		
		button1 =new JButton();
		button1.setText("Submit");
		button1.setBounds(270,160,100,20);
		add(button1);
		this.button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 try {
			            
					 conn = DBConnectionProvider.getDBConnection();

					 Statement stmt = conn.createStatement();
					 String query = "INSERT INTO `clubmanagement`.`clubs` (`Club_ID`, `Club_Name`) VALUES ('"+textfield1.getText()+"', '"+textfield2.getText()+"');";
					 stmt.executeUpdate(query);
					 JOptionPane.showMessageDialog(null,"Registration successfull");
			            
			        } catch (Exception ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(null,"failed plz try again");					
					}
		}
	});
		
		button2 =new JButton();
		button2.setText("< Back");
		button2.setBounds(100,160,100,20);
		add(button2);
		
		this.button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main.MainRef().remove(cerateclubRef());
				Main.MainRef().add(AdminLoginPanel.adminloginlRef());
				Main.MainRef().pack();
				Main.MainRef().setBounds(50,100,500,400);
			}	
			
		});
		
		Main.MainRef().pack();
    	Main.MainRef().setBounds(0,0,500,400);
	}
	public static CerateClub cerateclubRef(){
		if(clubpanel==null)
		{
			clubpanel = new CerateClub();
		}
		return clubpanel; 
	}
}
