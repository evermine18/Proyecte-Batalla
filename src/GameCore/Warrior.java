package GameCore;

public class Warrior {
	private int id;
	private String warriorName;
	private String imagePath;
	private String raceName;
	private int hp;
	private int strenght;
	private int speed;
	private int agility;
	private int defense;
	
	public Warrior(int id, String warriorName, String imagePath, String raceName, int hp, int strenght, int speed, int agility, int defense) {
		this.id=id;
		this.warriorName=warriorName;
		this.imagePath=imagePath;
		this.raceName=raceName;
		this.hp=hp;
		this.strenght=strenght;
		this.speed=speed;
		this.agility=agility;
		this.defense=defense;
	}

	public String getWarriorName() {
		return warriorName;
	}

	public void setWarriorName(String warriorName) {
		this.warriorName = warriorName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public int getStrenght() {
		return strenght;
	}

	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
