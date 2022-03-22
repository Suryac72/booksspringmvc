<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.net.URI"%>
<%@ page import="java.net.http.HttpClient"%>
<%@ page import="java.net.http.HttpRequest"%>
<%@ page import="java.net.http.HttpResponse"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="org.hibernate.SessionFactory"%>

<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="com.entity.*"%>
<%@ page import="java.util.List"%>

<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="org.hibernate.cfg.Configuration"%>
<%@ page import="java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.hibernate.query.Query"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="style.css" />
<title>Book Management System</title>
</head>
<style>
@charset "ISO-8859-1";

.head, .subheading {
	display: flex;
	justify-content: center;
	margin-top: 2rem;
}

.text-right button {
	position: fixed;
	right: 10rem;
}

#table {
	margin-top: 3rem;
}

.form {
	display: flex;
	flex-direction: column;
	width: 50%;
	justify-content: center;
}
.field{
	display: flex;
	flex-direction: row;
	justify-content: center;
}
.container{
	display:flex;
	flex-direction:column;
	
	justify-content:center;
}
.field label{
	margin-right: 2rem;
}
.field input{
	margin-left:3rem;
}
</style>
<body>
	<div class="container">
		<div class="head">
			<h3>Book Management System</h3>
		</div>
		<div class="logout" style="display: flex; justify-content: right">
			<form style="display: flex" action="home" method="post">
				<h5 style="margin-right: 2rem">
					Welcome
					<c:out value="${user.getUsername()}" />
				</h5>
				<button type="submit" class="btn btn-primary btn-sm">Logout</button>
			</form>
		</div>

		<div class="subheading">
			<h3>Add Book Details</h3>
		</div>
		<br>
		<form class="form" action="addbooks" method="post"
			style="margin-left: 2rem;">
			<div class="field">
				<label for="title" id="label">Book Code</label> <input type="text"
					required name="id"
					style="margin-bottom: 1rem; margin-left: 1rem;">
			</div>
			<div class="field">
				<label for="title" id="quantity">Book Name</label> <input
					type="text" required name="name"
					style="margin-bottom: 1rem; margin-left: 0.5rem;">
			</div>
			<div class="field">
				<label for="title" id="size">Author</label> <input
				type="text" required name="author"
				style="margin-bottom: 1rem; margin-left: 2.6rem">
			</div>
			<div class="field">
				<label
				for="title" id="">Added On</label> <input type="text" required
				name="output" value="<%= LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) %>"
				style="margin-bottom: 1rem; margin-left: 1.1rem" /><br>
			</div>
			<div class="field">
			 <button class="btn btn-primary btn-sm"
				type="submit" style="margin-left: 17rem;">Add</button>
			</div>
			
		</form>
		<br> <br>

	</div>
</body>
</html>