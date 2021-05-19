package SQLCore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import GameCore.Elf;
import GameCore.Humano;
import GameCore.Nan;
import GameCore.Warrior;


public class Querys {
	public static ResultSet ejecutarConsulta(String query) {
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			return stmnt.executeQuery(query);
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido ejecutar la consulta");
			return null;
		}
		
	}
	public static Warrior[] allWarriors(){
		Warrior[] warriors = new Warrior[rowCount()];
		int contador=0;
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT warrior_id, warrior_name, warrior_image_path, race.race_id, race.race_name, race.hp, race.strength, race.speed, race.agility, race.defense FROM warriors JOIN race ON warriors.race_id=race.race_id;");
			while (rs.next()) {
				if(rs.getString(5).equals("Humano")) {
					warriors[contador]=new Humano(rs.getString(2),rs.getString(3));
				}
				else if(rs.getString(5).contains("Nan")) {
					warriors[contador]=new Nan(rs.getString(2),rs.getString(3));
				}
				else if(rs.getString(5).equals("Elf")) {
					warriors[contador]=new Elf(rs.getString(2),rs.getString(3));
				}
				//warriors[contador]=new Warrior(rs.getString(2),rs.getString(3),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9), rs.getInt(10));
				/*
				warriors[contador][1]=rs.getString(2);
				warriors[contador][2]=rs.getString(3);
				warriors[contador][3]=Integer.toString(rs.getInt(4));
				warriors[contador][4]=rs.getString(5);
				warriors[contador][5]=Integer.toString(rs.getInt(6));
				warriors[contador][6]=Integer.toString(rs.getInt(7));
				warriors[contador][7]=Integer.toString(rs.getInt(8));
				warriors[contador][8]=Integer.toString(rs.getInt(9));
				warriors[contador][9]=Integer.toString(rs.getInt(10));
				*/
				contador++;
			}
			
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido ejecutar la consulta");
			return null;
		}
		return warriors;
	}
	public static int rowCount() {
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT COUNT(warrior_id) FROM warriors;");
			while(rs.next()) {
				return rs.getInt(1);
		}
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido ejecutar la consulta");
			return 0;
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResultSet rs = ejecutarConsulta("SELECT * FROM warriors;");
		try {
			while(rs.next()) {
					System.out.println("ID: "+rs.getString(1));
					System.out.println("NOMBRE: "+rs.getString(2));
					System.out.println("DESC: "+rs.getString(3));
					System.out.println("DINERO: "+rs.getString(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

