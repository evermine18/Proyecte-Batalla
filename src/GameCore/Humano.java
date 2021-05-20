package GameCore;

public class Humano extends Warrior{
	public Humano() {
		super(0,"","","",0,0,0,0,0,0);
	}
	public Humano(int id, String warriorName, String imagePath, int points) {
		super(id,warriorName,imagePath,"human",50,5,5,6,3,points);
	}

}
