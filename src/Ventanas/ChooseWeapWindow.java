package Ventanas;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GameCore.Warrior;
import GameCore.Weapon;
import SQLCore.Querys;

public class ChooseWeapWindow extends JFrame{
	private JPanel panel1;
	private BufferedImage img = null;
	private ImageIcon imagen;
	private Image dimg;
	private int unaid;
	private JButton[] bWarriors;
	private Weapon[] weaponList;
	private Weapon weaponSelected;
	
	public ChooseWeapWindow(String raceName){
		Querys q = new Querys();
		weaponList=q.allWeapons(raceName);
		//System.out.println(weaponList[1].getId());
		this.setTitle("Projecte Batalla");
		this.setSize(770,600);
		this.setMinimumSize(new Dimension(770, 600));
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//w=-1;
		//i=-1;
		loadWeapons(weaponList);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public void loadWeapons(Weapon[] weapon) {
		panel1=new JPanel();
		System.out.println("entroo");
		bWarriors = new JButton[weaponList.length];
		//For Each que me va a salvar la vida y me la ha salvado
		for (Weapon weapons: weaponList) {
			if(ReadPicture("./img/"+weapons.getImagePath())==true) {
				final int id = weapons.getId()-1;
				imagen = new ImageIcon(dimg);
				bWarriors[id] = new JButton(imagen);
				bWarriors[id].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//	VentanaPrincipal.setWarrior1(warriorList[i].getWarriorName(),warriors[i].getImagePath(),warriors[i].getRaceName(),warriors[i].getHp(),warriors[i].getStrenght(),warriors[i].getSpeed(),warriors[i].getAgility(),warriors[i].getDefense());
						System.out.println(id);
						selectWarrior(id);
						VentanaPrincipal.setWeapon(weaponList[id]);
						System.out.println(weaponSelected);
					}
					
				});
			}
			else {
				//bWarriors[weapon.getId()-1] = new JButton(weapon[weapon.getId()-1].getWeaponName());
			}
			panel1.add(bWarriors[weapons.getId()-1]);
		}
		
		this.add(panel1);
		
	}
	public Weapon getW() {
		return weaponSelected;
	}
	
	public void selectWarrior(int numWar) {
		weaponSelected=weaponList[numWar];
		VentanaPrincipal.updateWar1(weaponList[numWar].getStrenght(), weaponList[numWar].getSpeed());
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public boolean ReadPicture(String ImageURL) {
		try {
		    img = ImageIO.read(new File(ImageURL));
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Debug: ERROR AL CARGAR LA IMAGEN" + ImageURL);
		    return false;
		}
		dimg = img.getScaledInstance(195,258,
		        Image.SCALE_SMOOTH);
		return true;
	}
}
