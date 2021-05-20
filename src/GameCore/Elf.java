package GameCore;

public class Elf extends Warrior{
	public Elf() {
		super(0,"","","Elf",100,5,5,5,5);
	}
	public Elf(int id, String warriorName, String imagePath) {
		super(id,warriorName,imagePath,"elf",40,4,7,7,2);
		
	}

}
