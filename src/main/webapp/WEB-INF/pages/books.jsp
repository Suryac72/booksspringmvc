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
<%@ page import = " java.util.* " %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page import="com.entity.*"%>
<%@ page import="java.util.List"%>

<%@ page import="java.util.Base64"%>
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

.for{
	display: flex;
	flex-direction: row;
	justify-content: center;
}
.text-right button {
	position: fixed;
	right: 10rem;
}

#table {
	margin-top: 3rem;
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
			<h3>Books List</h3>

			<div class="text-right">
				<form style="display: flex" action="addBook.jsp" method="post">
					<button type="submit" class="btn btn-primary btn-sm">Add a
						Book</button>
				</form>
			</div>
		</div>

		<table id="table" class="table table-bordered">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Book Code</th>
					<th scope="col">Book Name</th>
					<th scope="col">Author</th>
					<th scope="col">Date Added</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${book}" var="p">
					<tr>

						<th scope="row"></th>
						<td><c:out value="${p.getId()}" /></td>
						<td><c:out value="${p.getName()}" /></td>
						<td><c:out value="${p.getAuthor()}" /></td>
						<td><c:out value="${p.getDate()}" /></td>
						<td>
					     <c:set var="id" value="${p.getId()}" scope="session" />
					     <c:set var="name" value="${p.getName()}" scope="session" />
					     <c:set var="auth" value="${p.getAuthor()}" scope="session" />
							<div class="for">
								<form action="updateBook" method="post"style="margin-right:1rem;">
									<button type=submit class="btn btn-outline-primary btn-sm">Update</button>
								</form>
								<form action="deleteBook?book_id=${sessionScope.id}" method="post">
									<button type=submit class="btn btn-outline-danger btn-sm">Delete</button>
								</form>
							</div>

						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- <footer>Copyright @2022 Nagarro</footer> -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>
