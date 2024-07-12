package org.Banxico.Proyecto1.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Banxico.Proyecto1.dao.ActorDao;
import org.Banxico.Proyecto1.entity.Actor;



public class ActorController extends HttpServlet {

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		
		listAll(request, response);
		delete(request, response, 1);
		save(request, response);
		update(request, response);
		
		
		
		
	}	
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		
		listAll(request, response);
		
	}

	
	private void listAll(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Actor> actores = ActorDao.getAll();
		request.setAttribute("actorsList", actores);
		
	}
	
	private void delete(HttpServletRequest request,
			HttpServletResponse response,
			Integer id) {
		
		ActorDao.delete(id);
		
	}
	
	private void save(HttpServletRequest request,
			HttpServletResponse response) {
		
		Actor actor = new Actor();
		actor.setFirstName(request.getParameter("firstName"));
		actor.setLastName(request.getParameter("lastName"));
		
		ActorDao.save(actor);
		
	}
	
	
	private void update(HttpServletRequest request,
			HttpServletResponse response) {
		
		Actor actor = new Actor();
		actor.setFirstName(request.getParameter("firstName"));
		actor.setLastName(request.getParameter("lastName"));
		
		ActorDao.update(actor);
		
	}
	
}
