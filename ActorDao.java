package org.Banxico.Proyecto1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Banxico.Proyecto1.entity.Actor;

public class ActorDao {

	public void save(Actor actor) { //metodo para hacer INSERT
		
		String query = "INSERT INTO actor("
				+ "fist_name, last_name)"
				+ "values(?, ?)"; //se mandan a llamar a los statements que estan mas abajo en el codigo...
		
		Connection connection = ConnectionUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query); //statements se solicictan arriba en el codigo en los values...
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Actor> getAll() { //Metodo para hacer un Select
		
		String query = "SELECT * FROM actor";
		
		Connection connection = ConnectionUtil.getConnection();
		
		List<Actor> actores = new ArrayList<>();
		Actor actor = null;
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(query);
			
			while (rs.next()) {
				actor = new Actor();
				actor.setActorId(rs.getInt("actor_id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				
				
				actores.add(actor);
			}
			
			rs.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actores;
		
	}
	
	public void update(Actor actor) { //Metodo para hacer un Update con una referencia de id
		
		String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
		Connection connection = ConnectionUtil.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setInt(3, actor.getActorId());
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void delete(Integer actor_id) { //Metodo para hacer un Delete con una referencia de actor_id
		
		String query = "DELETE FROM actor WHERE actor_id = ?";
		
		Connection connection = ConnectionUtil.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, actor_id);
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
