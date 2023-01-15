<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="css/style_dashboard.css">
<link rel="stylesheet" href="css/style_login.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous"></script>
<title>Editar contas</title>
</head>
<body class="cor-fundo-corpo">
	<header class="container-fluid">
	<div class="row cor-fundo-cabecalho">
		<div class="col">
			<button class="btn p-0" type="submit">
				<img src="img/foto_usuario.jpg" alt="Foto usuário"
					class="rounded-circle rounded float-start foto-usuario">
			</button>
			<h1 class="nome-rv">
				<i class="fa fa-user nome-usuario">${nomeUsuario}</i>
			</h1>
		</div>
	</div>
	</header>
	<main class="container-fluid">
	<div class="row">
		<div class="col">
			<div class="card estilo-card cor-flutuante text-center">
				<h2 class="float-start titulo-card mt-2 fw-bold">Editar contas:</h2>
				<div class="card-body centralizaB">
					<c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
					<form action="fintech" method="post">
						<input type="hidden" value="editarConta" name="acao">
						<input type="hidden" value="${conta.numeroConta}" name="numeroConta">
						<input type="hidden" value="${conta.instituicao.numeroInstituicao}" name="numeroInstituicao">
						<input type="hidden" value="${conta.numeroConta}" name="numeroConta">
						<input type="hidden" value="${conta.saldo}" name="saldo">
						<input type="hidden" value="${conta.numeroAgencia}" name="agencia">						
						<div class="form-group mt-1 fw-bold">
							<label for="id-descricao">Descrição</label> <input type="text"
								name="descricao" id="id-descricao" class="form-control" value="${conta.descricao}">
						</div>
					 	<fieldset disabled>					   
							<div class="form-group mt-1 fw-bold">
								<label for="id-instituicao">Instituição financeira</label> <input
									type="text" name="" id="id-instituicao"
									class="form-control" value="${conta.instituicao.numeroInstituicao}">
							</div>
							<div class="form-group mt-1 fw-bold">
								<label for="id-agencia">Agência</label> <input type="text"
									name="" id="id-agencia" class="form-control" value="${conta.numeroAgencia}">
							</div>
							<div class="form-group mt-1 fw-bold">
								<label for="id-conta">Conta corrente</label> <input type="text"
									name="" id="id-conta" class="form-control" value="${conta.numeroConta}">
							</div>
							<div class="form-group mt-1 fw-bold">
								<label for="id-saldo">Saldo</label> <input type="text"
									name="" id="id-saldo" class="form-control" value="R$ ${conta.saldo}">
							</div>
					  	</fieldset> 
						<input type="submit" value="Salvar" class="btn btn-primary mt-2">
						<a href="fintech?acao=listarContas" class="btn btn-danger mt-2">Voltar</a> 
					</form>
				</div>
		  </div>
	 </main>
</body>
</html>