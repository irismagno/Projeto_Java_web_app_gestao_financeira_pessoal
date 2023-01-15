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
	<title>Análise</title>
 </head>
	<body class="cor-fundo-corpo">
		<header class="container-fluid">
		  <div class="row cor-fundo-cabecalho">
		   	<div class="col">
		   		<button class="btn p-0 float-start" type="submit"><img src="img/foto_usuario.jpg" alt="Foto usuário" class="rounded-circle rounded foto-usuario float-start"></button>
		   		<button class="btn estilo-sino mt-3 me-1 p-1 float-end" type="submit"><i class="bi bi-bell tamanho-sino"></i></button>
		   		<h1 class="nome-rv"><i class="fa fa-user nome-usuario">${nomeUsuario}</i></h1>	
			</div>			
		  </div>		  
		</header>
		<main class="container-fluid centraliza">
		   <div class="row">
		   	    <div class="col">
			   		<div class="card estilo-card cor-flutuante mb-0">
				      <div class="card-body">				     
					  <div>
					    <div class="col-xs-12">
					      <h4 class="text-center titulo-card fw-bold">Análise de gastos:</h4>
					    </div>						  
						<div>
						<p class="m-0 p-0 secundarias-card">Alimentação</p>
						<div class="progress cor-flutuante mb-3 mt-0" style="height: 25px">
						  <div class="progress-bar" role="progressbar" aria-label="Example with label" style="width: ${percentualAlimentacao}%" aria-valuenow="${percentualAlimentacao}" aria-valuemin="0" aria-valuemax="100">${percentualAlimentacao} %</div>
						</div>
						<p class="m-0 p-0 secundarias-card">Saúde</p>
						<div class="progress cor-flutuante mb-3 mt-0" style="height: 25px">
						  <div class="progress-bar" role="progressbar" aria-label="Default example" style="width: ${percentualSaude}%" aria-valuenow="${percentualSaude}" aria-valuemin="0" aria-valuemax="100">${percentualSaude} %</div>
						</div>
						<p class="m-0 p-0 secundarias-card">Educação</p>
						<div class="progress cor-flutuante mb-3 mt-0" style="height: 25px">
						  <div class="progress-bar" role="progressbar" aria-label="Default example" style="width: ${percentualEducacao}%" aria-valuenow="${percentualEducacao}" aria-valuemin="0" aria-valuemax="100">${percentualEducacao} %</div>
						</div>
						<p class="m-0 p-0 secundarias-card">Transporte</p>
						<div class="progress cor-flutuante mb-3 mt-0" style="height: 25px">
						  <div class="progress-bar" role="progressbar" aria-label="Default example" style="width: ${percentualTransporte}%" aria-valuenow="${percentualTransporte}" aria-valuemin="0" aria-valuemax="100">${percentualTransporte} %</div>
						</div>
						<p class="m-0 p-0 secundarias-card">Moradia</p>
						<div class="progress cor-flutuante mb-3 mt-0" style="height: 25px">
						  <div class="progress-bar" role="progressbar" aria-label="Default example" style="width: ${percentualMoradia}%" aria-valuenow="${percentualMoradia}" aria-valuemin="0" aria-valuemax="100">${percentualMoradia} %</div>
						</div>
						<p class="m-0 p-0 secundarias-card">Lazer</p>
						<div class="progress cor-flutuante mb-3 mt-0" style="height: 25px">
						  <div class="progress-bar" role="progressbar" aria-label="Default example" style="width: ${percentualLazer}%" aria-valuenow="${percentualLazer}" aria-valuemin="0" aria-valuemax="100">${percentualLazer} %</div>
						</div>
						<p class="m-0 p-0 secundarias-card">Outros</p>
						<div class="progress cor-flutuante mb-3 mt-0" style="height: 25px">
						  <div class="progress-bar" role="progressbar" aria-label="Default example" style="width: ${percentualOutros}%" aria-valuenow="${percentualOutros}" aria-valuemin="0" aria-valuemax="100">${percentualOutros} %</div>
						</div>
						</div>				  		      
					 </div>
			    </div>
		   	</div>
		   	<hr class="zera-borda-hr">  	
		   	<div class="col">
		   		<div class="card estilo-card cor-flutuante p-2 mt-0">
			      <div class="card-body">			      
			      <h4 class="fw-bold secundarias-card">Total de receitas: --------- R$ ${totalRecebimentos}</h4>
			      <h4 class="fw-bold secundarias-card">Total de despesas: -------- R$ ${totalGastos}</h4>
			      <h4 class="fw-bold secundarias-card">Saldo final: ---------------- R$ ${saldoTotal}</h4>
			      </div>
			    </div>
		   	</div>		      
		</main>
		<footer class="container-fluid rodape d-flex justify-content-center">
		  <div class="row">
		   	<div class="col text-center" >
		   		<a href="fintech?acao=abrir-index" class="btn btn-primary botoesrodape mt-3"><i class="bi bi-house-door"></i>Home</a>
		  	</div>			
		</footer>
	</body>
</html>