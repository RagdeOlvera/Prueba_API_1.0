<%@page import="org.Banxico.Proyecto1.entity.Actor"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Actores</title>
	<style>
		body {
			font-family: Arial, sans-serif;
			margin: 0;
			padding: 0;
			background-color: #f4f4f4;
		}
		.container {
			width: 80%;
			margin: auto;
			overflow: hidden;
		}
		header {
			background: #35424a;
			color: #ffffff;
			padding-top: 30px;
			min-height: 70px;
			border-bottom: #e8491d 3px solid;
		}
		header a {
			color: #ffffff;
			text-decoration: none;
			text-transform: uppercase;
			font-size: 16px;
		}
		header ul {
			padding: 0;
			list-style: none;
		}
		header li {
			display: inline;
			padding: 0 20px 0 20px;
		}
		h3 {
			color: #35424a;
			text-align: center;
			margin-top: 30px;
		}
		form, table {
			background: #ffffff;
			padding: 20px;
			margin-top: 30px;
			box-shadow: 0px 0px 10px 0px #000000;
		}
		table {
			width: 100%;
			border-collapse: collapse;
		}
		table, th, td {
			border: 1px solid #ddd;
		}
		th, td {
			padding: 10px;
			text-align: center;
		}
		th {
			background-color: #f2f2f2;
		}
		button, input[type="submit"] {
			background-color: #35424a;
			color: white;
			padding: 10px 15px;
			border: none;
			cursor: pointer;
			margin-top: 10px;
		}
		button:hover, input[type="submit"]:hover {
			background-color: #e8491d;
		}
		a {
			color: #35424a;
			text-decoration: none;
		}
		a:hover {
			color: #e8491d;
		}
	</style>
</head>
<body>
	<header>
		<div class="container">
			<h1>Gestión de Actores</h1>
		</div>
	</header>
	<div class="container">
		<div> <%-- Sección Método Save (Create/Insert) --%>
			
			<h3>Alta de Actores</h3>
			
			<%
			Actor loadedActor = (Actor)request.getAttribute("actor");
			String url = "actores?tipoOperacion=3";
			
			if (loadedActor == null){
				url = "actores?tipoOperacion=4";
				loadedActor = new Actor();
				loadedActor.setFirstName("");
				loadedActor.setLastName("");
				loadedActor.setActorId(0);
			}
			%>
			
			<form action="<%=url%>" method="post">
				<table>
					<tr>
						<td>Nombre:</td>
						<td>
							<input 
							type="hidden"
							name="id"
						 	value="<%= loadedActor.getActorId() %>" />
							
							<input 
							type="text"
							name="firstName"
							id="firstName"
						 	value="<%= loadedActor.getFirstName() %>" 
							required />
						</td>
					</tr>
					<tr>
						<td>Apellido:</td>
						<td>
							<input 
							type="text"
							name="lastName"
							id="lastName"
							value="<%= loadedActor.getLastName() %>"
							required />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit">Guardar</button>
						</td>
					</tr>
				</table>
			</form>
		</div>	
	
		<div> <%-- Sección Método ListAll (Select *) --%>
			
			<h3>Relación de Actores</h3>
			<table>	
				<thead>
				<tr> 
					<th>Id</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th colspan="2">Acciones</th>
				</tr>
				</thead>
				<tbody>
			
				<% // Código Java 'Scriptlets'
				List<Actor> actores = (List<Actor>)request.getAttribute("actorsList");
				if (actores != null ){
					for (Actor actor : actores) {
				%>

					<tr> 
						<td><%= actor.getActorId() %></td>
						<td><%= actor.getFirstName() %></td>
						<td><%= actor.getLastName() %></td>
						<td>
							<a href="actores?tipoOperacion=1&id=<%= actor.getActorId() %>">
								Eliminar
							</a>
						</td>
						<td>
							<a href="actores?tipoOperacion=2&id=<%= actor.getActorId() %>">
								Actualizar
							</a>
						</td>
					</tr>
				<%
						}
					} else { %>
						<tr>
							<td colspan="5"><h3>No hay registros que mostrar</h3></td>
						</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
