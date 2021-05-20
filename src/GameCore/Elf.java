package GameCore;

public class Elf extends Warrior{
	public Elf() {
		super(0,"","","Elf",100,5,5,5,5,0);
	}
	public Elf(int id, String warriorName, String imagePath, int points) {
		super(id,warriorName,imagePath,"elf",40,4,7,7,2,points);
		
	}

}
