package GameCore;

import java.util.Random;

import Ventanas.VentanaPrincipal;

public class Fight {
	private int turn, injuresP1,injuresP2;
	private int rand1, rand2;
	public Fight(Warrior warrior1, Weapon weapon1, Warrior warrior2, Weapon weapon2) {
		injuresP1=0;
		injuresP2=0;
		Random rand = new Random();
		while(warrior1.getHp()>0 && warrior2.getHp()>0) {
			if((warrior1.getSpeed()+weapon1.getSpeed())==(warrior2.getSpeed()+weapon2.getSpeed())) {
				if(warrior1.getAgility()==warrior2.getAgility()) {
					turn=rand.nextInt(2)+1;
				}
				else if(warrior1.getAgility()>warrior2.getAgility()){
					turn=1;
				}
				else {
					turn=2;
				}
			}
			else if((warrior1.getSpeed()+weapon1.getSpeed())>(warrior2.getSpeed()+weapon2.getSpeed())) {
				turn=1;
			}
			else {
				turn=2;
			}
			//Printamos turno
			VentanaPrincipal.printConsole("Info: Player "+turn+" turn");
			if (turn==1) {
				rand1=rand.nextInt(101);
				if(warrior1.getAgility()*10>rand1) {
					rand2=rand.nextInt(51);
					if(warrior2.getAgility()<rand2) {
						warrior2.setHp(warrior2.getHp()-((warrior1.getStrenght()+weapon1.getStrenght())-warrior2.getDefense()));
						VentanaPrincipal.printConsole("Info: Player 2 has taken "+(warrior2.getHp()-((warrior1.getStrenght()+weapon1.getStrenght())-warrior2.getDefense()))+" points of damage ");
						injuresP2=injuresP2+((warrior1.getStrenght()+weapon1.getStrenght())-warrior2.getDefense());
						VentanaPrincipal.printConsole("Info: Player 1 health "+ warrior1.getHp() +"Player 2 health "+warrior2.getHp());
						VentanaPrincipal.setHpValueW2(warrior2.getHp());
						if((warrior1.getSpeed()+weapon1.getSpeed())<=(warrior2.getSpeed()+weapon2.getSpeed())){
							turn=2;
						}
						else {
							rand1=rand.nextInt(101);
							if((warrior1.getSpeed()+weapon1.getSpeed())-(warrior2.getSpeed()+weapon2.getSpeed())*10>rand1) {
								turn=1;
							}
							else {
								turn=2;
							}
						}
					}
					else {
						VentanaPrincipal.printConsole("Info: Player 2 has dodged the attack");
					}
				}
				else {
					VentanaPrincipal.printConsole("Info: Player "+turn+" attack was unsuccessful ");
				}
			}
			else {
				rand1=rand.nextInt(101);
				if(warrior2.getAgility()*10>rand1) {
					rand2=rand.nextInt(51);
					if(warrior1.getAgility()<rand2) {
						warrior1.setHp(warrior1.getHp()-((warrior2.getStrenght()+weapon2.getStrenght())-warrior1.getDefense()));
						VentanaPrincipal.printConsole("Info: Player 1 has taken "+(warrior1.getHp()-((warrior2.getStrenght()+weapon2.getStrenght())-warrior1.getDefense()))+" points of damage ");
						VentanaPrincipal.printConsole("Info: Player 1 health "+ warrior1.getHp() +"Player 2 health "+warrior2.getHp());
						VentanaPrincipal.setHpValueW1(warrior1.getHp());
						injuresP1=injuresP1+((warrior2.getStrenght()+weapon2.getStrenght())-warrior1.getDefense());
						if((warrior2.getSpeed()+weapon2.getSpeed())<=(warrior1.getSpeed()+weapon1.getSpeed())){
							turn=1;
						}
						else {
							rand1=rand.nextInt(101);
							if((warrior2.getSpeed()+weapon2.getSpeed())-(warrior1.getSpeed()+weapon1.getSpeed())*10>rand1) {
								turn=2;
							}
							else {
								turn=1;
							}
						}
					}
					else {
						VentanaPrincipal.printConsole("Info: Player 1 has dodged the attack");
					}
				}
				else {
					VentanaPrincipal.printConsole("Info: Player "+turn+" attack was unsuccessful ");
				}
			}
		}
		if(warrior1.getHp()<=0) {
			VentanaPrincipal.printConsole("Info: Player 2 wins ");
			SQLCore.Querys.InsertarBattle(warrior2.getId(), weapon2.getId(), warrior1.getId(), weapon1.getId(), injuresP1, injuresP2, warrior1.getPoints()+weapon1.getPoints());
			VentanaPrincipal.endFight(2);
		}
		else {
			VentanaPrincipal.printConsole("Info: Player 1 wins ");
			SQLCore.Querys.InsertarBattle(warrior1.getId(), weapon1.getId(), warrior2.getId(), weapon2.getId(), injuresP2, injuresP1, warrior2.getPoints()+weapon2.getPoints());
			VentanaPrincipal.endFight(1);
		}
	}

}
