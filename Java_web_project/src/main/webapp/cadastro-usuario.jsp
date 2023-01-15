<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-BR">
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Cadastro usuário</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="css/style_login.css">
		<link rel="stylesheet" href="css/style_dashboard.css">
	</head>
	<body class="cor-fundo">
		<div class="row">
			<div class="col">
				<header>					
					<p class="text-center subtitulo">Vamos começar!</p>					
					<p class="text-center">Precisaremos de alguns dados pessoais:</p>
					<div class="text-center">
						<c:if test="${not empty msg }">
							<div class="alert alert-success">${msg}</div>
						</c:if>
						<c:if test="${not empty erro }">
							<div class="alert alert-danger">${erro}</div>
						</c:if>
					</div>
				</header>
				<main class= "centraliza">				
				<form class="row g-3" action="fintech" method="post">
				<input type="hidden" value="cadastrarUsuario" name="acao">
				  <div class="col-md-6">
				    <label for="nome" class="form-label fw-bold secundarias-card">Nome</label>
				    <input name="nome" type="text" class="form-control" id="nome" placeholder="Nome">
				  </div>
				  <div class="col-md-6">
				    <label for="sobrenome" class="form-label fw-bold secundarias-card">Sobrenome</label>
				    <input name="sobrenome" type="text" class="form-control" id="sobrenome" placeholder="Sobrenome">
				  </div>				  
				  <div class="col-12">
				    <label for="email" class="form-label fw-bold secundarias-card">E-mail</label>
				    <input name="email" type="email" class="form-control" id="email" placeholder="nome@email.com">
				  </div>
				  <div class="col-md-6">
				    <label for="senha" class="form-label fw-bold secundarias-card">Senha</label>
				    <input name="senha" type="password" class="form-control" id="senha" placeholder="********">
				  </div>
				  <div class="col-md-6">
				  <label for="corfirmarSenha" class="form-label fw-bold secundarias-card">Confirmar senha</label>
				    <input name="confirmarSenha" type="password" class="form-control" id="confirmarSenha" placeholder="********">  
				  </div>
				  <div class="col-md-6">
				    <label for="genero" class="form-label fw-bold secundarias-card">Gênero</label>
				    <select name="genero" id="genero" class="form-select">
				      <option selected>Selecione</option>
				      <option value="1">Masculino</option>
				      <option value="2">Feminino</option>
				    </select>
				  </div>
				  <div class="col-md-6">
				    <label for="nascimento" class="form-label fw-bold secundarias-card">Data de nascimento</label>
				    <input name="nascimento" type="text" class="form-control" id="nascimento" placeholder="DD/MM/YYY">				  
				  </div>				  
				  <div class="col-12">
				    <div class="form-check">
				      <input class="form-check-input" type="checkbox" id="mentar-conectado">
				      <label for="manter-conectado"><a href="errorPageNotFounded.jsp" class="fonte-checkbox form-check-label posiciona">Concordo com os termos de uso</a></label>				      
				    </div>
				 </div>	
				 <div class="text-center pt-1">
				 	<input type="submit" value="Cadastrar" class="btn btn-primary botao mt-1">					
				</div>			 
				</form>				
				<br>
				</main>
				<footer class="text-center">					
				<a href="Login.jsp" class="fonte-checkbox">Fazer login</a>			  
			</footer>
		</div>	
	</body>
</html>