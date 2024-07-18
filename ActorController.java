package org.Banxico.Proyecto1.controller;

import java.io.IOException;
import java.util.List;



import org.Banxico.Proyecto1.dao.ActorDao;
import org.Banxico.Proyecto1.entity.Actor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/actores")

public class ActorController extends HttpServlet {

	public static int ELIMINAR_ACTOR = 1;
	public static int CARGA_ACTOR = 2; 
	public static int ACTUALIZAR_ACTOR = 3;
	public static int GUARDAR_ACTOR = 4;
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		Integer tipoOperacion = 0;
		Integer id = 0;

		if (request.getParameter("tipoOperacion") != null) {
			tipoOperacion = Integer.parseInt(request.getParameter("tipoOperacion"));
		}
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		
		if (tipoOperacion == ELIMINAR_ACTOR) {
	
			delete(request, response, id);
			
		} else if(tipoOperacion ==  CARGA_ACTOR){
			
			loadActor(request, response, id); //carga los datos del actor actual en el formulario para modificar
		}

		listAll(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
		
	}	
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		Integer tipoOperacion = 0;

		if (request.getParameter("tipoOperacion") != null) {	//valida el tipo de operacion que recibe 
			tipoOperacion = Integer.parseInt(request.getParameter("tipoOperacion"));
		}
		
		save(request, response, tipoOperacion);
		listAll(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/*CRUD*/
	
	private void listAll(HttpServletRequest request, //revisado funcionamiento correcto
			HttpServletResponse response) {
		
		List<Actor> actores = ActorDao.getAll();
		request.setAttribute("actorsList", actores);
		
	}
	
	private void delete(HttpServletRequest request, //revisado Funcionamiento correcto
			HttpServletResponse response,
			Integer id) {
		
		ActorDao.delete(id);
		
	}
	
	private void save(HttpServletRequest request,
			HttpServletResponse response,
			Integer tipoOperacion) {
		
		Actor actor = new Actor();
		actor.setFirstName(request.getParameter("firstName"));
		actor.setLastName(request.getParameter("lastName"));
		
		if(tipoOperacion == GUARDAR_ACTOR) {
			ActorDao.save(actor);
		}else if (tipoOperacion == ACTUALIZAR_ACTOR) {
			actor.setActorId(Integer.parseInt(request.getParameter("id")));
			ActorDao.update(actor);
		}
		
	}
	
	/*
	private void update(HttpServletRequest request,
			HttpServletResponse response) {
		
		Actor actor = new Actor();
		actor.setFirstName(request.getParameter("firstName"));
		actor.setLastName(request.getParameter("lastName"));
		
		ActorDao.update(actor);
		
	}*/
	
	private void loadActor(HttpServletRequest request,
			HttpServletResponse response, 
			Integer id) {
		
		request.setAttribute("actor", ActorDao.findById(id));
	}
}
