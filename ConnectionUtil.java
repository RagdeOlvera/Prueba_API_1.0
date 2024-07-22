package org.Banxico.Proyecto1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Clase utilitaria para gestionar la conexion a la BDD 
 */

public class ConnectionUtil {

	/*ESTA SECCION SE USA PARA LA CONEXION A LA BASE DE DATOS*/
	
	private static final String URL = "jdbc:sqlite:C:/Users/B19908/Documents/SQLite/sakila.db";  // 'xyxyx' Direccion url de la base de datos, ejemplo /127.0.0.1/sakila
/*	private static final String USER = "dbo_SAKILA"; // Usuario de la bdd
	private static final String PASSWORD = "DGTI-SDSGO-Y11"; // Contraseña de la bdd /// Posible error debido a encriptacion*/
	
	public static void main(String[] args) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from actor;"); // esta linea es una prueba para mandar a llamar a una tabla de la bdd.
		
		while(rs.next()) {
			System.out.println(rs.getString("first_name") + " , " + rs.getString("last_name"));
		}
	}

	static {
		try {
			Class.forName("org.sqlite.JDBC"); //driver para el control, en este caso es para mysql, buscar las diferentes por medio de maven repository para mysql es :com.mysql.cj.jdbc.Driver
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(URL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error al conectarse a la BDD");
		}
		
		
	}
	
}
