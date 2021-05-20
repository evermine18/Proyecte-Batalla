package SQLCore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import GameCore.Elf;
import GameCore.Humano;
import GameCore.Nan;
import GameCore.Warrior;
import GameCore.Weapon;


public class Querys {
	//Metodo principal de ejecutar consulta
	public ResultSet ejecutarConsulta(String query) {
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			return stmnt.executeQuery(query);
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido ejecutar la consulta");
			return null;
		}
		
	}
	
	//Devuelve un array de warriors extraida de la base de datos
	public static Warrior[] allWarriors(){
		Warrior[] warriors = new Warrior[rowCount("warriors")];
		int contador=0;
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT warrior_id, warrior_name, warrior_image_path, race.race_id, race.race_name, race.hp, race.strength, race.speed, race.agility, race.defense, race.points FROM warriors JOIN race ON warriors.race_id=race.race_id;");
			while (rs.next()) {
				if(rs.getString(5).equals("human")) {
					warriors[contador]=new Humano(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(11));
				}
				else if(rs.getString(5).contains("nan")) {
					warriors[contador]=new Nan(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(11));
				}
				else if(rs.getString(5).equals("elf")) {
					warriors[contador]=new Elf(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(11));
				}
				contador++;
			}
			
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido ejecutar la consulta");
			return null;
		}
		return warriors;
	}
	//Devuelve un array de weapons que saca directamente de la Base de Datos
	public static Weapon[] allWeapons(String raceName){
		Weapon[] weapons = new Weapon[rowCount("weapons", raceName)];
		int contador=0;
		try {
			System.out.println(raceName);
			Statement stmnt = SQLCore.Connection().createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points FROM weapons WHERE weapon_race LIKE '%"+raceName+"%';");
			while (rs.next()) {
					weapons[contador]=new Weapon(contador+1,rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(7));
				contador++;
			}
			
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido ejecutar la consulta");
			return null;
		}
		return weapons;
	}
	//Metodo que devuelve el numero de filas de la tabla la cual le proporcionas
	public static int rowCount(String table) {
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT COUNT(warrior_id) FROM "+table+";");
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
	//Metodo que devuelve el numero de filas de la tabla la cual le proporcionas
	public static int rowCount(String table, String raceName) {
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT COUNT(weapon_id) FROM "+table+" WHERE weapon_race LIKE '%"+raceName+"%';");
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
	//Inserta el resultado de la batalla en la BD
public static void InsertarBattle(int warrior_id,int weapon_id,int opponent_id,int opponent_weapon_id, int injures_caused, int injures_suffered, int battle_points) {
		
		try {
			PreparedStatement pstm=SQLCore.Connection().prepareStatement("INSERT INTO battles"
					+ "(battle_id, player_id, warrior_id, weapon_id, opponent_id, opponent_weapon_id, injures_caused,injures_suffered,battle_points) values(0,1,?,?,?,?,?,?,?)"	);
			pstm.setInt(1, warrior_id);
			pstm.setInt(2, weapon_id);
			pstm.setInt(3, opponent_id);
			pstm.setInt(4, opponent_weapon_id);
			pstm.setInt(5, injures_caused);
			pstm.setInt(6, injures_suffered);
			pstm.setInt(7, battle_points);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Devuelve los 10 primeros top Jugadores
	public static String[][] getRanking(){
		String[][] topPlayers = new String[10][9];
		int contador=0;
		try {
			Statement stmnt = SQLCore.Connection().createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT battle_id, player_id, warrior_id, weapon_id, opponent_id, opponent_weapon_id, injures_caused,injures_suffered,battle_points FROM battles ORDER BY battle_points DESC LIMIT 10;");
			while (rs.next()) {
					topPlayers[contador][0]=Integer.toString(rs.getInt(1));
					topPlayers[contador][1]=Integer.toString(rs.getInt(2));
					topPlayers[contador][2]=Integer.toString(rs.getInt(3));
					topPlayers[contador][3]=Integer.toString(rs.getInt(4));
					topPlayers[contador][4]=Integer.toString(rs.getInt(5));
					topPlayers[contador][5]=Integer.toString(rs.getInt(6));
					topPlayers[contador][6]=Integer.toString(rs.getInt(7));
					topPlayers[contador][7]=Integer.toString(rs.getInt(8));
					topPlayers[contador][8]=Integer.toString(rs.getInt(9));
				contador++;
			}
			
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido ejecutar la consulta");
			return null;
		}
		//System.out.println(weapons[4].getWeaponName()+ "MorenoMaricon");
		return topPlayers;
	}
	
}

