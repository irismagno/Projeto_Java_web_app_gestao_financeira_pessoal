<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-BR">
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Alterar cadastro</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="css/style_login.css">
		<link rel="stylesheet" href="css/style_dashboard.css">
	</head>
	<body class="cor-fundo">
		<div class="row">
			<div class="col">
				<header>					
					<p class="text-center subtitulo">Alteração cadastral</p>					
					<p class="text-center">informe os dados:</p>
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
				<input type="hidden" value="editarUsuario" name="acao">
				  <div class="col-md-6">
				    <label for="nome" class="form-label fw-bold secundarias-card">Nome</label>
				    <input name="nome" type="text" class="form-control" id="nome" value="${usuario.nome}">
				  </div>
				  <div class="col-md-6">
				    <label for="sobrenome" class="form-label fw-bold secundarias-card">Sobrenome</label>
				    <input name="sobrenome" type="text" class="form-control" id="sobrenome" value="${usuario.sobrenome}">
				  </div>				  
				  <div class="col-12">
				    <label for="email" class="form-label fw-bold secundarias-card">E-mail</label>
				    <input name="email" type="email" class="form-control" id="email" value="${usuario.email}">
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
				    <input name="nascimento" type="text" class="form-control" id="nascimento" value='<fmt:formatDate value="${usuario.dataNascimento.time}" pattern="dd/MM/yyyy"/>'>				  
				  </div>				  
				 <div class="row text-center">
			     	<div class="col">	
				 	<input type="submit" value="Salvar" class="btn btn-primary mt-4 mx-3">
					<a href="fintech?acao=abrir-index" class="btn btn-danger mt-4 mx-3">Voltar</a>
					</div> 	
				</div> 
				</form>				
				<br>
			</main>				
		</div>	
	</body>
</html>