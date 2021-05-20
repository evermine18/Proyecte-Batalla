package Ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndFightWindow extends JFrame{
	private JPanel panel1,buttons;
	private JLabel label1;
	private JButton bYes,bNo;
	
	public EndFightWindow(final int hp, final int pWins){
		panel1=new JPanel();
		label1=new JLabel("Do you want to keep fighting?");
		buttons=new JPanel();
		bYes=new JButton("Yes");
		bYes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (pWins==1) {
					VentanaPrincipal.updateWar2();
					VentanaPrincipal.setHpValueW1(hp);
				}
				else {
					VentanaPrincipal.updateWar1("noselected.png", 0, 0, 0, 0, 0);
					VentanaPrincipal.setHpValueW2(hp);
					VentanaPrincipal.enableCW();
				}
				
				
				
			}
			
		});
		bNo=new JButton("No");
		panel1.add(label1);
		buttons.add(bYes);
		buttons.add(bNo);
		this.add(panel1,BorderLayout.PAGE_START);
		this.add(buttons,BorderLayout.CENTER);
		this.setTitle("Projecte Batalla");
		this.setSize(200,150);
		this.setMinimumSize(new Dimension(200, 150));
		this.setVisible(true);
	}

}
