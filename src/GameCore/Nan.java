package GameCore;

public class Nan extends Warrior{
	public Nan() {
		super(0,"","","",0,0,0,0,0,0);
	}
	public Nan(int id, String warriorName, String imagePath, int points) {
		super(id,warriorName,imagePath,"nan",60,6,3,5,4,points);
	}
}
