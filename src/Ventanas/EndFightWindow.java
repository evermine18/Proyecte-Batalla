package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndFightWindow extends JFrame implements WindowListener{
	private JPanel panel1,buttons;
	private JLabel label1;
	private JButton bYes,bNo;
	private int pWins,hp;
	
	public EndFightWindow(final int hp, final int pWins){
		panel1=new JPanel();
		panel1.setBackground(new Color(80,227,194));
		this.pWins=pWins;
		this.hp=hp;
		label1=new JLabel("Do you want to keep fighting?");
		//Boton en el caso que si con su ActionListener
		buttons=new JPanel();
		buttons.setBackground(new Color(80,227,194));
		bYes=new JButton("Yes");
		bYes.setForeground(new Color(58,124,207));
		bYes.setBackground(new Color(254,253,151));
		bYes.setFont(new Font("Helvetica", Font.BOLD, 15));
		bYes.setBorderPainted(false);
		bYes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (pWins==1) {
					MainWindow.updateWar2();
					MainWindow.setHpValueW1(hp);
				}
				else {
					MainWindow.updateWar1("noselected.png", 0, 0, 0, 0, 0);
					MainWindow.setHpValueW2(hp);
					MainWindow.enableCW();
				}
				CloseWindow();
				
				
				
			}
			
		});
		//Boton en el caso que no con su ActionListener
		bNo=new JButton("No");
		bNo.setForeground(new Color(58,124,207));
		bNo.setBackground(new Color(241,151,192));
		bNo.setFont(new Font("Helvetica", Font.BOLD, 15));
		bNo.setBorderPainted(false);
		bNo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		//JFrame Config
		panel1.add(label1);
		buttons.add(bYes);
		buttons.add(bNo);
		this.setLocationRelativeTo(null);
		this.add(panel1,BorderLayout.PAGE_START);
		this.add(buttons,BorderLayout.CENTER);
		this.setTitle("Projecte Batalla");
		this.setSize(200,150);
		this.setMinimumSize(new Dimension(200, 150));
		this.setVisible(true);
	}
	public void CloseWindow() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (pWins==1) {
			MainWindow.updateWar2();
			MainWindow.setHpValueW1(hp);
		}
		else {
			MainWindow.updateWar1("noselected.png", 0, 0, 0, 0, 0);
			MainWindow.setHpValueW2(hp);
			MainWindow.enableCW();
		}
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
