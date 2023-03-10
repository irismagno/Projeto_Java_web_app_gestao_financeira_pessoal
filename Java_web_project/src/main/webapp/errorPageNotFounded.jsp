<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-BR">
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>P?gina em constru??o</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="css/style_login.css">
		<link rel="stylesheet" href="css/style_dashboard.css">
	</head>
	<body class="cor-fundo">
		<div class="row">
			<div class="col">
				<header>
					<div class="logo text-center">
						<img src="img/cifrao.png" width="73" height="67">
						<h1>P?gina em constru??o...</h1>
						<br>
					</div>
					<p class="text-center subtitulo">Aguarde!</p>
					<br>
					<p class="text-center">Acreditamos nesse projeto e est? funcioalidade Ainda est? em desenvolvimento :)</p>
					<hr class="zera-borda-hr">
				</header>
				<main class= "centraliza">
					<h1></h1>
				</main>
				<footer class="text-center">				
				<c:url value="fintech" var="link">
					<c:param name="acao" value="abrir-index"/>
				</c:url>
				<a href="${link}" class="fonte-checkbox">Enquanto isso, aproveite os nossos outros recursos</a>
			    <form action="fintech" method="post">						
			</footer>
		</div>	
	</body>
</html>