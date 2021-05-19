package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import GameCore.Warrior;

public class TopPanel extends JPanel{
	private JButton bChooseCh,bChooseWe,bRanking;
	
	private JPanel content;
	
	public TopPanel(){
		content=new JPanel();
		bChooseCh=new JButton("Choose Character");
		bChooseWe=new JButton("Choose Weapon");
		bRanking=new JButton("Ranking");
		bChooseCh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new ChooseCharWindow();
				
			}
			
		});
		content.add(bChooseCh);
		content.add(bChooseWe);
		content.add(bRanking);
		this.add(content,BorderLayout.CENTER);
	}

}
