package GameCore;

public class Weapons {
	private int id;
	private String weaponName;
	private String imagePath;
	private int strenght;
	private int speed;
	
	public Weapons() {
		
	}
	
	public Weapons(int id, String weaponName, String imagePath, int strenght, int speed) {
		this.id = id;
		this.weaponName = weaponName;
		this.imagePath = imagePath;
		this.strenght = strenght;
		this.speed = speed;
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
	
	

}
