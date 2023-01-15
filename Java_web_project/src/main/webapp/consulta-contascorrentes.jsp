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
<title>Consultar contas</title>
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
				
				<h4 class="mt-2 titulo-card">Minhas contas:</h4>
				<c:if test="${not empty msg }">
						<div class="alert alert-success">${msg}</div>
					</c:if>
					<c:if test="${not empty erro }">
						<div class="alert alert-danger">${erro}</div>
					</c:if>
				<table class="table table-striped secundarias-card">
			  		<tr>
						<th class="fw-bold">Descrição</th>
						<th class="fw-bold">Número</th>
						<th class="fw-bold">Saldo</th>
						<th>    </th>
					</tr>
			 		 <c:forEach items="${conta}" var="c">
					  <tr>
						<td class="fw-bold">${c.descricao}</td>
						<td class="fw-bold">${c.numeroConta}</td>
						<td class="fw-bold">R$ ${c.saldo}</td>
					    <td>					  
							<div class="btn-group dropstart">
							  <button type="button" class="btn btn-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
							    Mais
							  </button>
							  <ul class="dropdown-menu cor-offcanvas ps-2 pe-2">
							    <c:url value="fintech" var="link">
									<c:param name="acao" value="abrir-form-edicao-contas"/>
									<c:param name="conta" value="${c.numeroConta}"/>
									<c:param name="instituicao" value="${c.instituicao.numeroInstituicao}"/>
								</c:url>
							    <a href="${link}" class="btn btn-primary align-middle float-end">Editar</a>
						        <form action="fintech" method="post">
						      		<input type="hidden" name="acao" value="excluirConta">
						      		<input type="hidden" name="contaExcluir" value="${c.numeroConta}">
						      		<input type="hidden" name="instituicaoExcluir" value="${c.instituicao.numeroInstituicao}">
							        <button type="submit" class="btn btn-danger">Excluir</button>
					            </form>		      				 
							  </ul>
							</div>																																		
						   </td>
					   </tr>
					 </c:forEach>    
				</table>
			</div>	
	    </div>
	   </div>
	</main>
	<footer class="container-fluid rodape d-flex justify-content-center">
		  <div class="row">
		   	<div class="col text-center" >
		   	    <a href="fintech?acao=abrir-form-cadastro-conta" class="btn btn-primary botoesrodape mt-3"><i class="bi bi-cash-coin"></i>Nova</a>
		   		<a href="fintech?acao=abrir-index" class="btn btn-primary botoesrodape mt-3"><i class="bi bi-house-door"></i>Home</a>
		   	</div>
		  </div>
	</footer>
</body>
</html>