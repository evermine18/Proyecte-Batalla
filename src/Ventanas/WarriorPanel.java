package Ventanas;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		pr.setBorder(BorderFactory.createLineBorder(new Color(174,241,65)));
		pr.setForeground(new Color(174,241,65));
		pr.setBackground(new Color(220,248,238));
		pr.setPreferredSize( new Dimension (330, 30));
		pr.setFont(new Font("Helvetica", Font.BOLD, 15));
		pr.setBorderPainted(false);
		pr.setValue(hp);
		pr.setStringPainted(true);
		this.add(pr);
		this.add(label);
		//Llamada a las barras de Fuerza,Agilidad, etc..
		stats(vPower,vAgility,vSpeed,vDefense);
		
		
		
	}
	public void stats(int vPower,int vAgility, int vSpeed,int vDefense) {
		statsPanel= new JPanel(new GridLayout(4,2,10,10));
		statsPanel.setBackground(new Color(132,235,212));
		//POWER BAR AND LABEL
		power=new JLabel("Power");
		power.setFont(new Font("Helvetica", Font.BOLD, 12));
		statsPanel.add(power);
		pbPower=new JProgressBar(0,100);
		pbPower.setBorder(BorderFactory.createLineBorder(new Color(255,154,162)));
		pbPower.setForeground(new Color(255,154,162));
		pbPower.setBackground(new Color(220,248,238));
		pbPower.setFont(new Font("Helvetica", Font.BOLD, 13));
		pbPower.setBorderPainted(false);
		pbPower.setValue(vPower);
		pbPower.setStringPainted(true);
		statsPanel.add(pbPower);
		//AGILITY BAR AND LABEL
		agility=new JLabel("Agility");
		agility.setFont(new Font("Helvetica", Font.BOLD, 12));
		statsPanel.add(agility);
		pbAgility=new JProgressBar(0,100);
		pbAgility.setBorder(BorderFactory.createLineBorder(new Color(255,218,193)));
		pbAgility.setForeground(new Color(255,218,193));
		pbAgility.setBackground(new Color(220,248,238));
		pbAgility.setFont(new Font("Helvetica", Font.BOLD, 13));
		pbAgility.setBorderPainted(false);
		pbAgility.setValue(vAgility);
		pbAgility.setStringPainted(true);;
		statsPanel.add(pbAgility);
		//SPEED BAR AND LABEL
		speed=new JLabel("Speed");
		speed.setFont(new Font("Helvetica", Font.BOLD, 12));
		statsPanel.add(speed);
		pbSpeed=new JProgressBar(0,100);
		pbSpeed.setBorder(BorderFactory.createLineBorder(new Color(226,240,203)));
		pbSpeed.setForeground(new Color(226,240,203));
		pbSpeed.setBackground(new Color(220,248,238));
		pbSpeed.setFont(new Font("Helvetica", Font.BOLD, 13));
		pbSpeed.setBorderPainted(false);
		pbSpeed.setValue(vSpeed);
		pbSpeed.setStringPainted(true);
		statsPanel.add(pbSpeed);
		//DEFENSE BAR AND LABEL
		defense=new JLabel("Defense");
		defense.setFont(new Font("Helvetica", Font.BOLD, 12));
		statsPanel.add(defense);
		pbDefense=new JProgressBar(0,100);
		pbDefense.setBorder(BorderFactory.createLineBorder(new Color(199,206,234)));
		pbDefense.setForeground(new Color(199,206,234));
		pbDefense.setBackground(new Color(220,248,238));
		pbDefense.setFont(new Font("Helvetica", Font.BOLD, 13));
		pbDefense.setBorderPainted(false);
		pbDefense.setValue(vDefense);
		pbDefense.setStringPainted(true);
		statsPanel.add(pbDefense);
		this.add(statsPanel);
		
		
	}
	//Funcion la cual le pasas una imagen cargada y la actualiza
	public void updateImage(String imagePath) {
		System.out.println("./img/"+imagePath);
		try {
		    img = ImageIO.read(new File("."+File.separator+"img"+File.separator+imagePath));
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