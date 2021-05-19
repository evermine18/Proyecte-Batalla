package GameCore;

public class Humano extends Warrior{
	public Humano() {
		super("","","",0,0,0,0,0);
	}
	public Humano(String warriorName, String imagePath) {
		super(warriorName,imagePath,"Humano",50,5,5,6,3);
	}

}
