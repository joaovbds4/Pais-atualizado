<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pais</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Visualizar País # ${pais.id}</h3>
		<div class="row">
			<div class="col-md-12">
				<p>
					<strong>${pais.nome}</strong>
				</p>
			</div>
			<div class="col-md-12">
				<p>
					<strong>${pais.populacao}</strong>
				</p>
			</div>
			<div class="col-md-12">
				<p>
					<strong>${pais.area}</strong>
				</p>
			</div>
		</div>
		<hr>
		<div id="actions" class="row">
			<a href="ListarPais.jsp" class="btn btn-default">Voltar</a>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>