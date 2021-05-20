package SQLCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCore {
<<<<<<< Updated upstream
	private static String urlDatos = "jdbc:mysql://localhost/proyecto?serverTimezone=UTC";
=======
	private static String urlDatos = "jdbc:mysql://2.138.10.124/proyecto?serverTimezone=UTC";
>>>>>>> Stashed changes
	private static String usuario = "root";
	private static String pass = "Persiana1234";
	public static Connection Connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MYSQL: Driver cargado correctamente");
			Connection conn = DriverManager.getConnection(urlDatos,usuario,pass);
			System.out.println("MYSQL: Conexion creada correctamente");
			return conn;
		}
		catch(ClassNotFoundException e){
			System.out.println("MYSQL: No se ha podido cargar el driver");
			return null;
		}
		catch(SQLException e) {
			System.out.println("MYSQL: No se ha podido crear la conexion");
			return null;
		}
	}
}
