package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import SQLCore.Querys;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameCore.Warrior;
import Ventanas.VentanaPrincipal;

public class ChooseCharWindow extends JFrame {
	
	private JPanel panel1;
	private BufferedImage img = null;
	private ImageIcon imagen;
	private Image dimg;
	private int unaid;
	private JButton[] bWarriors;
	private Warrior[] warriorList;
	private Warrior warriorSelected;
	
	public ChooseCharWindow(){
		Querys q = new Querys();
		warriorList=q.allWarriors();
		System.out.println(warriorList[1].getId());
		this.setTitle("Projecte Batalla");
		this.setSize(770,600);
		this.setMinimumSize(new Dimension(770, 600));
		loadWarriors(warriorList);
		this.setVisible(true);
	}
	
	public void loadWarriors(Warrior[] warriors) {
		panel1=new JPanel();
		
		bWarriors = new JButton[warriorList.length];
		
		//For Each que me va a salvar la vida y me la ha salvado
		for (Warrior warrior: warriorList) {
			if(ReadPicture("./img/"+warrior.getImagePath())==true) {
				final int id = warrior.getId()-1;
				imagen = new ImageIcon(dimg);
				bWarriors[id] = new JButton(imagen);
				bWarriors[id].setBackground(Color.WHITE);
				bWarriors[id].setBorder(BorderFactory.createLineBorder(new Color(15,37,87)));
				bWarriors[id].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(id);
						selectWarrior(id);
						VentanaPrincipal.setWarrior(warriorList[id]);
						System.out.println(warriorSelected);
					}
					
				});
			}
			else {
				bWarriors[warrior.getId()-1] = new JButton(warriors[warrior.getId()-1].getWarriorName());
			}
			
			panel1.add(bWarriors[warrior.getId()-1]);
		}
		
		this.add(panel1);
		
	}
	public Warrior getW() {
		return warriorSelected;
	}
	
	public void selectWarrior(int numWar) {
		warriorSelected=warriorList[numWar];
		VentanaPrincipal.updateWar1(warriorList[numWar].getImagePath(),warriorList[numWar].getHp(),warriorList[numWar].getStrenght(),warriorList[numWar].getSpeed(),warriorList[numWar].getAgility(),warriorList[numWar].getDefense());
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public boolean ReadPicture(String ImageURL) {
		try {
		    img = ImageIO.read(new File(ImageURL));
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Debug: ERROR AL CARGAR LA IMAGEN");
		    return false;
		}
		dimg = img.getScaledInstance(195,258,
		        Image.SCALE_SMOOTH);
		return true;
	}
}
