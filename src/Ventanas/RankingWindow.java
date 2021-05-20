package Ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RankingWindow extends JFrame{
	private JTable taula;
	private JScrollPane jsp;
	
	public RankingWindow(){
		String []nomCol= {"Battle ID","Player ID", "Warrior ID", "Weapon ID", "Opponent Id", "Opponent Weapon ID","Injures Caused","Injures Suffered", "Battle Points"};
		String [][]datos= SQLCore.Querys.getRanking();
		taula= new JTable(datos,nomCol);
		taula.setRowHeight(24);
		taula.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jsp=new JScrollPane(taula);
		this.add(jsp,BorderLayout.CENTER);
		this.setTitle("Projecte Batalla");
		this.setSize(700,320);
		this.setMinimumSize(new Dimension(700, 300));
		this.setVisible(true);
	}
}
