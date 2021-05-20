package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GameCore.Fight;
import GameCore.Warrior;
import GameCore.Weapon;
import SQLCore.Querys;


public class VentanaPrincipal extends JFrame {
	private static JButton bChooseCh, bChooseWe, bRanking, bFight, bClearConsole;
	private static int w1maxHealth;
	private static int w2maxHealth;
	private JPanel content,topPanel, fightPanel,consolePanel;
	private static WarriorPanel panel1;
	private static WarriorPanel panel2;
	private static Warrior warrior1,warrior2;
	private Warrior[] warriorList;
	private static Weapon weapon1, weapon2;
	private static JTextArea console;
	private JScrollPane jsp;
	
	public VentanaPrincipal() {
		//TopPanel 
		topPanel=new JPanel();
		fightPanel=new JPanel();
		consolePanel= new JPanel();
		bChooseCh=new JButton("Choose Character");
		bChooseWe=new JButton("Choose Weapon");
		bRanking=new JButton("Ranking");
		bChooseCh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bChooseCh.setEnabled(false);
				bChooseWe.setEnabled(false);
				bRanking.setEnabled(false);
				new ChooseCharWindow();
			}
			
		});
		topPanel.add(bChooseCh);
		bChooseWe.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(warrior1);
				bChooseCh.setEnabled(false);
				bChooseWe.setEnabled(false);
				bRanking.setEnabled(false);
				new ChooseWeapWindow(warrior1.getRaceName());
			}
			
		});
		bChooseWe.setEnabled(false);
		topPanel.add(bChooseWe);
		bRanking.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateWar2();
			}
			
		});
		topPanel.add(bRanking);
		this.add(topPanel,BorderLayout.CENTER);
		warriorList=new Warrior[Querys.rowCount("warriors")];
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
		//Botones de Fight y Clear Console
		bFight=new JButton("Fight");
		bFight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Fight(warrior1,weapon1,warrior2,weapon2);
			}
			
		});
		fightPanel.add(bFight);
		bClearConsole=new JButton("Clear Console");
		bClearConsole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				console.setText("");
			}
			
		});
		fightPanel.add(bClearConsole);
		
		console=new JTextArea("",8,60);
		jsp=new JScrollPane(console);
		fightPanel.add(jsp);
		fightPanel.setPreferredSize(new Dimension(100,200));
		
		this.add(topPanel,BorderLayout.PAGE_START);
		this.add(panel1,BorderLayout.LINE_START);
		this.add(panel2,BorderLayout.LINE_END);
		this.add(fightPanel,BorderLayout.PAGE_END);
		//this.add(consolePanel,BorderLayout.PAGE_END);
		this.setTitle("Projecte Batalla");
		this.setSize(770,700);
		this.setMinimumSize(new Dimension(770, 700));
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
	public static void noSelected() {
		bChooseCh.setEnabled(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		warrior1= new Warrior(0,"No Selected","noselected.png","none",100,3,4,5,6,0);
		warrior2= new Warrior(0,"No Selected","noselected.png","none",100,3,4,5,6,0);
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
	public static void setWarrior(Warrior warriorSelected) {
		warrior1=warriorSelected;
		w1maxHealth=warrior1.getHp();
	}
	public static void setWeapon(Weapon weaponSelected) {
		weapon1=weaponSelected;
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
	public static void updateWar1(String ImageURL, int hp, int power, int speed, int agility, int defense){
		panel1.updateImage(ImageURL);
		panel1.pr.setValue(hp);
		panel1.pbPower.setValue(power*10);
		panel1.pbSpeed.setValue(speed*10);
		panel1.pbAgility.setValue(agility*10);
		panel1.pbDefense.setValue(defense*10);
		bChooseCh.setEnabled(true);
		bChooseWe.setEnabled(true);
		bRanking.setEnabled(true);
		
	}
	public static void updateWar1(int strength, int speed){
		panel1.pbPower.setValue((warrior1.getAgility()+strength)*10);
		panel1.pbSpeed.setValue((warrior1.getSpeed()+speed)*10);
		//System.out.println("Arma:" + weapon1.getWeaponName()+"Velocidad:"+weapon1.getSpeed());
		bChooseCh.setEnabled(true);
		bChooseWe.setEnabled(true);
		bRanking.setEnabled(true);
	}
	//Generacion del bot
	public static void updateWar2(){
		Random rand = new Random();
		Warrior[] warriors = SQLCore.Querys.allWarriors();
		System.out.println("Longitud de warriors " + warriors.length);
		warrior2= warriors[rand.nextInt(warriors.length)];
		for (int i = 0; i<20; i++) {
			System.out.println(rand.nextInt(warriors.length));
		}
		Weapon[] weapons = SQLCore.Querys.allWeapons(warrior2.getRaceName());
		System.out.println("Longitud de weapons " + weapons.length);
		weapon2 = weapons[rand.nextInt(weapons.length)];
		System.out.println("Arma:" + weapon2.getWeaponName()+"Velocidad:"+weapon2.getSpeed());
		panel2.updateImage(warrior2.getImagePath());
		panel2.pr.setValue(warrior2.getHp());
		panel2.pbPower.setValue((warrior2.getStrenght()+weapon2.getStrenght())*10);
		panel2.pbSpeed.setValue((warrior2.getSpeed()+weapon2.getSpeed())*10);
		panel2.pbAgility.setValue(warrior2.getAgility()*10);
		panel2.pbDefense.setValue(warrior2.getDefense()*10);
		w2maxHealth=warrior2.getHp();
		bChooseCh.setEnabled(true);
		bChooseWe.setEnabled(true);
		bRanking.setEnabled(true);
		bFight.setEnabled(true);
		bClearConsole.setEnabled(true);
		
	}
	public static void setHpValueW1(int hp) {
		panel1.pr.setValue(hp);
		warrior1.setHp(hp);
	}
	public static void setHpValueW2(int hp) {
		panel2.pr.setValue(hp);
		warrior2.setHp(hp);
	}
	public static void endFight(int playerWins) {
		bChooseCh.setEnabled(false);
		bChooseWe.setEnabled(false);
		bRanking.setEnabled(false);
		bFight.setEnabled(false);
		bClearConsole.setEnabled(false);
		if(playerWins==1) {
			new EndFightWindow(w1maxHealth, playerWins);
		}
		else {
			new EndFightWindow(w2maxHealth, playerWins);
		}
		
	}
	public static void enableCW() {
		bChooseCh.setEnabled(true);
		bChooseWe.setEnabled(false);
		bRanking.setEnabled(false);
	}
	public static void printConsole(String message) {
		if (console.getText().equals("")) {
			console.setText(message);
		}
		else {
			console.setText(console.getText()+"\n"+message);
		}
		
	}
}
