package GameCore;

public class Weapon {
	private int id;
	private String weaponName;
	private String imagePath;
	private int strenght;
	private int speed;
	private int points;
	
	public Weapon() {
		
	}
	
	public Weapon(int id, String weaponName, String imagePath, int strenght, int speed, int points) {
		this.id = id;
		this.weaponName = weaponName;
		this.imagePath = imagePath;
		this.strenght = strenght;
		this.speed = speed;
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	

}
