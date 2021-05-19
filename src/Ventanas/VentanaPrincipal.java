package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import GameCore.Warrior;
import SQLCore.Querys;


public class VentanaPrincipal extends JFrame {
	private JButton bChooseCh,bChooseWe,bRanking;
	private int w;
	private JPanel content,topPanel;
	private WarriorPanel panel1, panel2;
	private static Warrior warrior1,warrior2;
	private Warrior[] warriorList;
	
	public VentanaPrincipal() {
		//TopPanel 
		topPanel=new JPanel();
		bChooseCh=new JButton("Choose Character");
		bChooseWe=new JButton("Choose Weapon");
		bRanking=new JButton("Ranking");
		bChooseCh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseCharWindow cw=	new ChooseCharWindow();
				warrior1=cw.getW();
			}
			
		});
		topPanel.add(bChooseCh);
		bChooseWe.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(warrior1);
			}
			
		});
		topPanel.add(bChooseWe);
		topPanel.add(bRanking);
		this.add(topPanel,BorderLayout.CENTER);
		warriorList=new Warrior[Querys.rowCount()];
		warriorList=Querys.allWarriors();
		//(topPanel=new TopPanel();
		//Call to the Warriors Panel Constructor
		panel1=new WarriorPanel("./img/"+ warrior1.getImagePath(),warrior1.getHp(),warrior1.getStrenght(),warrior1.getAgility(),warrior1.getSpeed(),warrior1.getDefense());
		panel1.setPreferredSize(new Dimension(350,460));
		panel2=new WarriorPanel("./img/"+ warrior2.getImagePath(),warrior2.getHp(),warrior2.getStrenght(),warrior2.getAgility(),warrior2.getSpeed(),warrior2.getDefense());
		panel2.setPreferredSize(new Dimension(350,460));
		//panel1.add(boton);
		//personaje
		//end
		initWarriors();
		
		panel1.pbPower.setValue(10);
		this.add(topPanel,BorderLayout.PAGE_START);
		this.add(panel1,BorderLayout.LINE_START);
		this.add(panel2,BorderLayout.LINE_END);
		this.setTitle("Projecte Batalla");
		this.setSize(770,600);
		this.setMinimumSize(new Dimension(770, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		/*
		while (true) {
			System.out.println(this.getSize());
		}
		*/
	
	}
	public void  initWarriors() {
		Querys q=new Querys();
		warriorList=q.allWarriors();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		warrior1= new Warrior(0,"No Selected","noselected.png","none",100,3,4,5,6);
		warrior2= new Warrior(0,"No Selected","noselected.png","none",100,3,4,5,6);
		System.out.println(warrior1.getWarriorName());
		new VentanaPrincipal();
	}
	public static void setWarrior1(String warriorName, String imagePath, String raceName, int hp, int strenght, int speed, int agility, int defense) {
		warrior1.setWarriorName(warriorName);
		warrior1.setImagePath(imagePath);
		warrior1.setRaceName(raceName);
		warrior1.setHp(hp);
		warrior1.setStrenght(strenght);
		warrior1.setSpeed(speed);
		warrior1.setAgility(agility);
		warrior1.setDefense(defense);
	}
	public void setWarrior2(String warriorName, String imagePath, String raceName, int hp, int strenght, int speed, int agility, int defense) {
		warrior2.setWarriorName(warriorName);
		warrior2.setImagePath(imagePath);
		warrior2.setRaceName(raceName);
		warrior2.setHp(hp);
		warrior2.setStrenght(strenght);
		warrior2.setSpeed(speed);
		warrior2.setAgility(agility);
		warrior2.setDefense(defense);
	}
}
