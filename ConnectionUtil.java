package org.Banxico.Proyecto1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexion a la BDD 
 */

public class ConnectionUtil {

	private static final String URL = "jdbc:sql://servappruebas9:3346/bpm_htp";  // 'xyxyx' Direccion url de la base de datos, ejemplo /127.0.0.1/sakila
	private static final String USER = "dbo_bpm_htp"; // Usuario de la bdd
	private static final String PASSWORD = "BECAFC5DF0AA0D045B4009056B1BDF65D538614D1FBD81770C5288274F7AD645"; // Contraseña de la bdd /*Posible error debido a encriptacion*/
	
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error al conectarse a la BDD");
		}
		
		
	}
	
}
