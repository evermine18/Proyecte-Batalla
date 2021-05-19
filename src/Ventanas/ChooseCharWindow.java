package Ventanas;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import SQLCore.Querys;

import javax.imageio.ImageIO;
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
	JButton[] bWarriors;
	private Warrior[] warriorList;
	private Warrior warriorSelected;
	//private ImageIcon[] iIcon=new ImageIcon[Querys.rowCount()];
	
	public ChooseCharWindow(){
		Querys q = new Querys();
		warriorList=q.allWarriors();
		System.out.println(warriorList[1].getId());
		this.setTitle("Projecte Batalla");
		this.setSize(770,600);
		this.setMinimumSize(new Dimension(770, 600));
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//w=-1;
		//i=-1;
		loadWarriors(warriorList);
		this.setVisible(true);
	}
	
	public void loadWarriors(Warrior[] warriors) {
		panel1=new JPanel();
		
		bWarriors = new JButton[warriorList.length];
		/*
		for(i=0;i<warriors.length;i++) {
			if(ReadPicture("./img/"+warriors[i].getImagePath())==true) {
				imagen = new ImageIcon(dimg);
				bWarriors[i] = new JButton(imagen);
				bWarriors[i].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					//	VentanaPrincipal.setWarrior1(warriorList[i].getWarriorName(),warriors[i].getImagePath(),warriors[i].getRaceName(),warriors[i].getHp(),warriors[i].getStrenght(),warriors[i].getSpeed(),warriors[i].getAgility(),warriors[i].getDefense());
						System.out.println(i);
						selectWarrior(i);
						System.out.println(warriorSelected);
					}
					
				});
			}
			else {
				bWarriors[i] = new JButton(warriors[i].getWarriorName());
			}
			panel1.add(bWarriors[i]);
		}
		*/
		//For Each que me va a salvar la vida y me la ha salvado
		for (Warrior warrior: warriorList) {
			if(ReadPicture("./img/"+warrior.getImagePath())==true) {
				final int id = warrior.getId()-1;
				imagen = new ImageIcon(dimg);
				bWarriors[id] = new JButton(imagen);
				bWarriors[id].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//	VentanaPrincipal.setWarrior1(warriorList[i].getWarriorName(),warriors[i].getImagePath(),warriors[i].getRaceName(),warriors[i].getHp(),warriors[i].getStrenght(),warriors[i].getSpeed(),warriors[i].getAgility(),warriors[i].getDefense());
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
