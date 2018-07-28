package main;

import javax.swing.*;

import login.LoginPanel;

import java.awt.*;


public class Main extends JFrame {
	
	Image im = Toolkit.getDefaultToolkit().getImage("aiub.jpg");
	public static Main main = null;
	public Main(){
		this.setIconImage(im);
		this.setLayout(null);
		this.add(LoginPanel.LogInlRef());
		
		this.setVisible(true);
		this.setBounds(50,100,400,350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		main = this;
	}

	public static void main(String[] args) {

		Main main = new Main();
		
		
		
	}
	public static Main MainRef(){
		if(main==null)
			main = new Main();
		
		return main;
	}


}
