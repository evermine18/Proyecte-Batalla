package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GameCore.Weapon;
import SQLCore.Querys;

public class ChooseWeapWindow extends JFrame implements WindowListener{
	private JPanel panel1;
	private BufferedImage img = null;
	private ImageIcon imagen;
	private Image dimg;
	private JButton[] bWeapons;
	private Weapon[] weaponList;
	private Weapon weaponSelected;
	
	public ChooseWeapWindow(String raceName){
		Querys q = new Querys();
		weaponList=q.allWeapons(raceName);
		//System.out.println(weaponList[1].getId());
		this.setLocationRelativeTo(null);
		this.setTitle("Projecte Batalla");
		this.addWindowListener(this);
		this.setSize(770,600);
		this.setMinimumSize(new Dimension(770, 600));
		
		loadWeapons(weaponList);
		this.setVisible(true);
	}
	
	public void loadWeapons(Weapon[] weapon) {
		panel1=new JPanel();
		panel1.setBackground(new Color(80,227,194));
		System.out.println("entroo");
		bWeapons = new JButton[weaponList.length];
		//For Each que me va a salvar la vida y me la ha salvado
		for (Weapon weapons: weaponList) {
			final int id = weapons.getId()-1;
			if(ReadPicture("."+File.separator+"img"+File.separator+weaponList[id].getImagePath())==true) {
				imagen = new ImageIcon(dimg);
				bWeapons[id] = new JButton(imagen);
				bWeapons[id].setBackground(Color.WHITE);
				bWeapons[id].setBorder(BorderFactory.createLineBorder(new Color(15,37,87)));
						bWeapons[id].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(id);
						selectWarrior(id);
						MainWindow.setWeapon(weaponList[id]);
						System.out.println(weaponSelected);
					}
					
				});
			}
			else {
				bWeapons[weapons.getId()-1] = new JButton(weapon[weapons.getId()-1].getWeaponName());
			}
			panel1.add(bWeapons[weapons.getId()-1]);
		}
		
		this.add(panel1);
		
	}
	public Weapon getW() {
		return weaponSelected;
	}
	
	public void selectWarrior(int numWar) {
		weaponSelected=weaponList[numWar];
		MainWindow.updateWar1(weaponList[numWar].getStrenght(), weaponList[numWar].getSpeed());
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public boolean ReadPicture(String ImageURL) {
		try {
		    img = ImageIO.read(new File(ImageURL));
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Debug: Error to load the image" + ImageURL);
		    return false;
		}
		dimg = img.getScaledInstance(195,258,
		        Image.SCALE_SMOOTH);
		return true;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		MainWindow.enableCWe();
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
