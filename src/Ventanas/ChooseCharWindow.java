package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import SQLCore.Querys;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GameCore.Warrior;

public class ChooseCharWindow extends JFrame implements WindowListener {
	
	private JPanel panel1;
	private BufferedImage img = null;
	private ImageIcon imagen;
	private Image dimg;
	private JButton[] bWarriors;
	private Warrior[] warriorList;
	private Warrior warriorSelected;
	private boolean isSelected;
	
	public ChooseCharWindow(){
		isSelected=false;
		Querys q = new Querys();
		warriorList=q.allWarriors();
		System.out.println(warriorList[1].getId());
		this.setLocationRelativeTo(null);
		this.setTitle("Projecte Batalla");
		this.addWindowListener(this);
		this.setSize(770,600);
		this.setMinimumSize(new Dimension(770, 600));
		loadWarriors();
		this.setVisible(true);
	}
	
	public void loadWarriors() {
		panel1=new JPanel();
		panel1.setBackground(new Color(80,227,194));
		bWarriors = new JButton[warriorList.length];
		
		//For Each que me va a salvar la vida y me la ha salvado
		for (Warrior warrior: warriorList) {
			final int id = warrior.getId()-1;
			if(ReadPicture("."+File.separator+"img"+File.separator+warriorList[id].getImagePath())==true) {
				System.out.println("ImagePath "+warriorList[id].getImagePath());
				imagen = new ImageIcon(dimg);
				bWarriors[id] = new JButton(imagen);
				bWarriors[id].setBackground(Color.WHITE);
				bWarriors[id].setBorder(BorderFactory.createLineBorder(new Color(15,37,87)));
				bWarriors[id].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(id);
						isSelected=true;
						selectWarrior(id);
						MainWindow.setWarrior(warriorList[id]);
						System.out.println(warriorSelected);
					}
					
				});
			}
			else {
				bWarriors[id] = new JButton(warriorList[id].getWarriorName());
			}
			
			panel1.add(bWarriors[id]);

		}
		
		this.add(panel1);
		
	}
	public Warrior getW() {
		return warriorSelected;
	}
	
	public void selectWarrior(int numWar) {
		warriorSelected=warriorList[numWar];
		MainWindow.updateWar1(warriorList[numWar].getImagePath(),warriorList[numWar].getHp(),warriorList[numWar].getStrenght(),warriorList[numWar].getSpeed(),warriorList[numWar].getAgility(),warriorList[numWar].getDefense());
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public boolean ReadPicture(String ImageURL) {
		try {
		    img = ImageIO.read(new File(ImageURL));
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Debug: Error to load the image");
		    return false;
		}
		dimg = img.getScaledInstance(195,258,
		        Image.SCALE_SMOOTH);
		return true;
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		if (isSelected==true) {
			MainWindow.enableCWe();
		}
		else {
			MainWindow.enableCW();
		}
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
