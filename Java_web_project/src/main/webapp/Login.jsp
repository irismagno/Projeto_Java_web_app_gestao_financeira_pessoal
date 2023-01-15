<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-BR">
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Login</title>
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
						<h1>Fintech</h1>
						<c:if test="${not empty msg }">
							<div class="alert alert-success">${msg}</div>
						</c:if>
						<c:if test="${not empty erro }">
							<div class="alert alert-danger">${erro}</div>
						</c:if>
						<br>
					</div>
					<p class="text-center subtitulo">Seja bem-vindo!</p>
					<br>
					<p class="text-center">Acesse sua conta para controlar suas finanças!</p>
				</header>
				<main class= "centraliza">
					<form action="fintech" method="post">
					<input type="hidden" value="realizarLogin" name="acao">
					<div class="mb-1 ps-4 pe-4 pt-3">
					  <label for="email" class="formulario">E-mail:</label>
					  <input name="email" type="text" class="form-control" id="email" placeholder="nome@email.com">
					</div>
					<div class="mb-1 ps-4 pe-4 pt-3">
					  <label for="senha" class="formulario">Senha:</label>
					  <input name="senha" type="password" class="form-control" id="senha" placeholder="********">
					</div>
					<div>
						<input type="checkbox" id="manter-conectado" name="manter-conectado" class="ms-4 posiciona">
						<label for="manter-conectado"><span class="fonte-checkbox">Mantenha-me conectado</span></label>
					</div>
					<div class="text-center pt-3">
						<input type="submit" value="Entrar" class="btn btn-primary botao mt-2">						
					</div>
					</form>	
					<br><br>
				</main>
				<footer class="text-center">				
					<c:url value="fintech" var="link">
						<c:param name="acao" value="abrir-form-cadastro-usuario"/>
					</c:url>
					<a href="${link}" class="fonte-checkbox">Registrar para usar</a>
				    <form action="fintech" method="post">						
			</footer>
		</div>	
	</body>
</html>