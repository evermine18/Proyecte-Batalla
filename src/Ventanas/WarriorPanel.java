package Ventanas;


import java.awt.Color;

import java.awt.GridLayout;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


class WarriorPanel extends JPanel{
	private JLabel label, power, agility, speed, defense;
	private BufferedImage img = null;
	private Image dimg;
	private ImageIcon imagen;
	protected JProgressBar pr, pbPower, pbAgility, pbSpeed, pbDefense;
	private JPanel statsPanel;
	
	
	public WarriorPanel(String ImageURL,int hp,int vPower,int vAgility, int vSpeed,int vDefense) {
		
		//Carga la imagen como BufferedImage para poderla redimensionar y la convertimos a ImageIcon
		if(ReadPicture(ImageURL)==true) {
			imagen = new ImageIcon(dimg);
			label = new JLabel(imagen);
		}
		else {
			label = new JLabel("Imagen no encontrada");
		}
		//Barra de Vida
		pr=new JProgressBar(0,100);
		pr.setBackground(Color.WHITE);
		pr.setBorder(BorderFactory.createLineBorder(new Color(60,174,163)));
		pr.setForeground(new Color(44,128,120));
		pr.setValue(hp);
		pr.setStringPainted(true);
		this.add(pr);
		this.add(label);
		//Llamada a las barras de Fuerza,Agilidad, etc..
		stats(vPower,vAgility,vSpeed,vDefense);
		
		
		
	}
	public void stats(int vPower,int vAgility, int vSpeed,int vDefense) {
		statsPanel= new JPanel(new GridLayout(4,2,10,10));
		//POWER BAR AND LABEL
		power=new JLabel("Power");
		statsPanel.add(power);
		pbPower=new JProgressBar(0,100);
		pbPower.setBorder(BorderFactory.createLineBorder(new Color(197,69,47)));
		pbPower.setForeground(new Color(237,85,59));
		pbPower.setValue(vPower);
		pbPower.setStringPainted(true);
		statsPanel.add(pbPower);
		//AGILITY BAR AND LABEL
		agility=new JLabel("Agility");
		statsPanel.add(agility);
		pbAgility=new JProgressBar(0,100);
		pbAgility.setBorder(BorderFactory.createLineBorder(new Color(211,182,77)));
		pbAgility.setForeground(new Color(246,213,92));
		pbAgility.setValue(vAgility);
		pbAgility.setStringPainted(true);;
		statsPanel.add(pbAgility);
		//SPEED BAR AND LABEL
		speed=new JLabel("Speed");
		statsPanel.add(speed);
		pbSpeed=new JProgressBar(0,100);
		pbSpeed.setBorder(BorderFactory.createLineBorder(new Color(23,70,110)));
		pbSpeed.setForeground(new Color(32,99,155));
		pbSpeed.setValue(vSpeed);
		pbSpeed.setStringPainted(true);
		statsPanel.add(pbSpeed);
		//DEFENSE BAR AND LABEL
		defense=new JLabel("Defense");
		statsPanel.add(defense);
		pbDefense=new JProgressBar(0,100);
		pbDefense.setBorder(BorderFactory.createLineBorder(new Color(10,26,39)));
		pbDefense.setForeground(new Color(23,63,95));
		pbDefense.setValue(vDefense);
		pbDefense.setStringPainted(true);
		statsPanel.add(pbDefense);
		this.add(statsPanel);
		
		
	}
	//Funcion la cual le pasas una imagen cargada y la actualiza
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
	}
	//Funcion la cual le paas la URL de la imagen y la carga si el directorio es valido
	public boolean ReadPicture(String ImageURL) {
		try {
		    img = ImageIO.read(new File(ImageURL));
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("Debug: ERROR AL CARGAR LA IMAGEN");
		    return false; //Return false si no se ha podido cargar
		}
		dimg = img.getScaledInstance(195,258,
		        Image.SCALE_SMOOTH);
		return true; //Return true si no se ha podido cargar
	}
}