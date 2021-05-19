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
	JButton[] bWarriors;
	private Warrior[] warriorList;
	int  w,i;
	//private ImageIcon[] iIcon=new ImageIcon[Querys.rowCount()];
	
	public ChooseCharWindow(){
		Querys q = new Querys();
		warriorList=q.allWarriors();
		this.setTitle("Projecte Batalla");
		this.setSize(770,600);
		this.setMinimumSize(new Dimension(770, 600));
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w=-1;
		i=-1;
		loadWarriors(warriorList);
		this.setVisible(true);
	}
	
	public void loadWarriors(Warrior[] warriors) {
		panel1=new JPanel();
		
		bWarriors = new JButton[warriors.length];
		for( i=0;i<warriors.length;i++) {
			if(ReadPicture("./img/"+warriors[i].getImagePath())==true) {
				imagen = new ImageIcon(dimg);
				bWarriors[i] = new JButton(imagen);
				bWarriors[i].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
					//	VentanaPrincipal.setWarrior1(warriorList[i].getWarriorName(),warriors[i].getImagePath(),warriors[i].getRaceName(),warriors[i].getHp(),warriors[i].getStrenght(),warriors[i].getSpeed(),warriors[i].getAgility(),warriors[i].getDefense());
						w=i;
						System.out.println(w);
					}
					
				});
			}
			else {
				bWarriors[i] = new JButton(warriors[i].getWarriorName());
			}
			panel1.add(bWarriors[i]);
		}
		this.add(panel1);
	}
	public int getW() {
		return w;
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
