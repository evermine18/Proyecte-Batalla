package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import GameCore.Fight;
import GameCore.Warrior;
import GameCore.Weapon;
import SQLCore.Querys;


public class MainWindow extends JFrame {
	private static JButton bChooseCh, bChooseWe, bRanking, bFight, bClearConsole;
	private static int w1maxHealth;
	private static int w2maxHealth;
	private JPanel topPanel, fightPanel;
	private static WarriorPanel panel1;
	private static WarriorPanel panel2;
	private static Warrior warrior1,warrior2;
	private Warrior[] warriorList;
	private static Weapon weapon1, weapon2;
	private static JTextArea console;
	private JScrollPane jsp;
	
	public MainWindow() {
		//Comprobamos si hay conexion a la bd
		if(SQLCore.SQLCore.Connection()==null) {
			System.out.println("ERROR: The connection to the database could not be created ");
			JOptionPane.showOptionDialog(rootPane, "Could not connect to the database!!!","DB ERROR", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			System.exit(0);//Sale del programa si no ha tenido exito
		}
		//TopPanel
		topPanel=new JPanel();
		topPanel.setBackground(new Color(80,227,194));
		//Character Button
		bChooseCh=new JButton("Choose Character");
		bChooseCh.setBackground(new Color(5,151,242));
		bChooseCh.setForeground(Color.WHITE);
		bChooseCh.setFont(new Font("Helvetica", Font.BOLD, 15));
		bChooseCh.setBorderPainted(false);
		bChooseCh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bChooseCh.setEnabled(false);
				bChooseWe.setEnabled(false);
				bRanking.setEnabled(false);
				new ChooseCharWindow();
			}
			
		});
		topPanel.add(bChooseCh);
		//Weapon Button
		bChooseWe=new JButton("Choose Weapon");
		bChooseWe.setForeground(Color.WHITE);
		bChooseWe.setBackground(new Color(5,151,242));
		bChooseWe.setFont(new Font("Helvetica", Font.BOLD, 15));
		bChooseWe.setBorderPainted(false);
		bChooseWe.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println(warrior1);
				bChooseCh.setEnabled(false);
				bChooseWe.setEnabled(false);
				bRanking.setEnabled(false);
				new ChooseWeapWindow(warrior1.getRaceName());
			}
			
		});
		topPanel.add(bChooseWe);
		//Ranking Button
		bRanking=new JButton("Ranking");
		bRanking.setForeground(Color.WHITE);
		bRanking.setBackground(new Color(5,151,242));
		bRanking.setFont(new Font("Helvetica", Font.BOLD, 15));
		bRanking.setBorderPainted(false);
		bRanking.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new RankingWindow();
			}
			
		});
		bChooseWe.setEnabled(false);
		topPanel.add(bRanking);
		
		//Llamadas a los constructores de el Panel 1 y 2 donde se muestran los stats de los personajes
		warriorList=new Warrior[Querys.rowCount("warriors")];
		warriorList=Querys.allWarriors();
		panel1=new WarriorPanel("./img/"+ warrior1.getImagePath(),warrior1.getHp(),warrior1.getStrenght(),warrior1.getAgility(),warrior1.getSpeed(),warrior1.getDefense());
		panel1.setPreferredSize(new Dimension(350,470));
		panel1.setBackground(new Color(132,235,212));
		panel2=new WarriorPanel("./img/"+ warrior2.getImagePath(),warrior2.getHp(),warrior2.getStrenght(),warrior2.getAgility(),warrior2.getSpeed(),warrior2.getDefense());
		panel2.setPreferredSize(new Dimension(350,470));
		panel2.setBackground(new Color(132,235,212));
		
		//Panel Fight and Console
		fightPanel=new JPanel();
		bFight=new JButton("Fight");
		bFight.setForeground(new Color(58,124,207));
		bFight.setBackground(new Color(254,253,151));
		bFight.setFont(new Font("Helvetica", Font.BOLD, 15));
		bFight.setBorderPainted(false);
		bFight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(warrior2.getRaceName().equals("none")) {
					updateWar2();
				}
				new Fight(warrior1,weapon1,warrior2,weapon2);
			}
			
		});
		fightPanel.add(bFight);
		fightPanel.setBackground(new Color(80,227,194));
		bClearConsole=new JButton("Clear Console");
		//bClearConsole.setBorder(new RoundedBorder(10));
		bClearConsole.setForeground(new Color(58,124,207));
		bClearConsole.setBackground(new Color(241,151,192));
		bClearConsole.setFont(new Font("Helvetica", Font.BOLD, 15));
		bClearConsole.setBorderPainted(false);
		bClearConsole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				console.setText("");//Seteamos la consola en blanco
			}
			
		});
		fightPanel.add(bClearConsole);
		bFight.setEnabled(false);
		console=new JTextArea("",8,60);
		console.setEditable(false);
		console.setFont(new Font("Helvetica", Font.BOLD, 12));
		jsp=new JScrollPane(console);
		fightPanel.add(jsp);
		fightPanel.setPreferredSize(new Dimension(100,200));
		
		// JFRAME Config
		this.setLocationRelativeTo(null);
		this.add(topPanel,BorderLayout.PAGE_START);
		this.add(panel1,BorderLayout.LINE_START);
		this.add(panel2,BorderLayout.LINE_END);
		this.add(fightPanel,BorderLayout.PAGE_END);
		this.getContentPane().setBackground(new Color(80,227,194));
		this.setTitle("Projecte Batalla");
		this.setSize(770,720);
		this.setMinimumSize(new Dimension(770, 720));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void noSelected() {
		bChooseCh.setEnabled(true);
	}
	//Set Warriors Player 1 por atributos
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
	//Set Warriors Player 1 pasandole el objeto
	public static void setWarrior(Warrior warriorSelected) {
		warrior1=warriorSelected;
		w1maxHealth=warrior1.getHp();
	}
	//Set Warriors Player 1 pasandole el objeto
	public static void setWeapon(Weapon weaponSelected) {
		weapon1=weaponSelected;
		bFight.setEnabled(true);
	}
	//Set Warriors Player 2 por atributos
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
	//Actualiza los stats del panel de el Player 1
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
	//Metodos que setean la vida del Warrior1 y 2 y lo actualiza en el Panel
	public static void setHpValueW1(int hp) {
		panel1.pr.setValue(hp);
		warrior1.setHp(hp);
		
	}
	public static void setHpValueW2(int hp) {
		panel2.pr.setValue(hp);
		warrior2.setHp(hp);
	}
	//Llamada a la funcion cuando se acaba la Lucha
	public static void endFight(int playerWins) {
		bChooseCh.setEnabled(false);
		bChooseWe.setEnabled(false);
		bRanking.setEnabled(false);
		bFight.setEnabled(false);
		//bClearConsole.setEnabled(false);
		if(playerWins==1) {
			new EndFightWindow(w1maxHealth, playerWins);
		}
		else {
			new EndFightWindow(w2maxHealth, playerWins);
		}
		
	}
	

	//Activa los botones para elegir Personaje otra vez
	public static void enableCW() {
		bChooseCh.setEnabled(true);
		bChooseWe.setEnabled(false);
		bRanking.setEnabled(true);
	}
	public static void enableCWe() {
		bChooseCh.setEnabled(true);
		bChooseWe.setEnabled(true);
		bRanking.setEnabled(true);
	}
	//Metodo para printar por consola un mensaje
	public static void printConsole(String message) {
		if (console.getText().equals("")) { //Si no hay nada escribe en la 1a linea
			console.setText(message);
		}
		else {
			console.setText(console.getText()+"\n"+message);
		}
		
	}
	//Llamada principal
		public static void main(String[] args) throws InterruptedException {
			// TODO Auto-generated method stub
			warrior1= new Warrior(0,"No Selected","noselected.png","none",0,0,0,0,0,0);
			warrior2= new Warrior(0,"No Selected","noselected.png","none",0,0,0,0,0,0);
			System.out.println(warrior1.getWarriorName());
			new MainWindow();
		}
}
