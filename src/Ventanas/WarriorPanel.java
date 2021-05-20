package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import SQLCore.Querys;

class WarriorPanel extends JPanel{
	private JLabel label, power, agility, speed, defense;
	private BufferedImage img = null;
	private Image dimg;
	private ImageIcon imagen;
	protected JProgressBar pr, pbPower, pbAgility, pbSpeed, pbDefense;
	private JPanel statsPanel, warriorPanel;
	
	
	public WarriorPanel(String ImageURL,int hp,int vPower,int vAgility, int vSpeed,int vDefense) {
		
		warriorPanel=new JPanel();
		//Carga la imagen como BufferedImage para poderla redimensionar y la convertimos a ImageIcon
		if(ReadPicture(ImageURL)==true) {
			imagen = new ImageIcon(dimg);
			label = new JLabel(imagen);
		}
		else {
			label = new JLabel("Imagen no encontrada");
		}
		pr=new JProgressBar(0,100);
		pr.setBackground(Color.WHITE);
		pr.setForeground(Color.GREEN);
		pr.setValue(hp);
		pr.setStringPainted(true);
		this.add(pr);
		this.add(label);
		//this.add(warriorPanel, BorderLayout.CENTER);
		stats(vPower,vAgility,vSpeed,vDefense);
		
		
		
	}
	public void stats(int vPower,int vAgility, int vSpeed,int vDefense) {
		statsPanel= new JPanel(new GridLayout(4,2,10,10));
		//POWER BAR AND LABEL
		power=new JLabel("Power");
		statsPanel.add(power);
		pbPower=new JProgressBar(0,100);
		pbPower.setForeground(Color.RED);
		pbPower.setValue(vPower);
		pbPower.setStringPainted(true);
		statsPanel.add(pbPower);
		//AGILITY BAR AND LABEL
		agility=new JLabel("Agility");
		statsPanel.add(agility);
		pbAgility=new JProgressBar(0,100);
		pbAgility.setForeground(Color.PINK);
		pbAgility.setValue(vAgility);
		pbAgility.setStringPainted(true);;
		statsPanel.add(pbAgility);
		//SPEED BAR AND LABEL
		speed=new JLabel("Speed");
		statsPanel.add(speed);
		pbSpeed=new JProgressBar(0,100);
		pbSpeed.setForeground(Color.YELLOW);
		pbSpeed.setValue(vSpeed);
		pbSpeed.setStringPainted(true);
		statsPanel.add(pbSpeed);
		//DEFENSE BAR AND LABEL
		defense=new JLabel("Defense");
		statsPanel.add(defense);
		pbDefense=new JProgressBar(0,100);
		pbDefense.setForeground(Color.BLUE);
		pbDefense.setValue(vDefense);
		pbDefense.setStringPainted(true);
		statsPanel.add(pbDefense);
		this.add(statsPanel);
		
		
	}
	
	public void updateImage(String imagePath) {
		System.out.println("./img/"+imagePath);
		try {
		    img = ImageIO.read(new File("./img/"+imagePath));
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Debug: ERROR AL CARGAR LA IMAGEN");
		}
		dimg = img.getScaledInstance(195,258,
		        Image.SCALE_SMOOTH);
		imagen=imagen = new ImageIcon(dimg);
		label.setIcon(imagen);
		this.revalidate();
		this.repaint();
		//JLabel tempimg = new JLabel(new ImageIcon(dimg));
		//lImg= tempimg;
		
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