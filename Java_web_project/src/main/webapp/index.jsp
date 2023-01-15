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
	<title>Home</title>
 </head>
	<body class="cor-fundo-corpo">
		<header class="container-fluid">
		  <div class="row cor-fundo-cabecalho">
		   	<div class="col">
		   		<button class="btn p-0" type="submit"><img src="img/foto_usuario.jpg" alt="Foto usuário" class="rounded-circle rounded float-start foto-usuario"></button>
		   		<button class="btn estilo-sino float-end mt-3 me-1 p-1" type="submit"><i class="bi bi-bell tamanho-sino"></i></button>
		   		<h1 class="nome-rv"><i class="fa fa-user nome-usuario">Olá ${nomeUsuario}!</i></h1>	
			</div>
		  </div>		  
		</header>
		<main class="container-fluid centraliza">
		   <div class="row">
		   	    <div class="col">
			   		<div class="card estilo-card cor-flutuante text-center">
				      <div class="card-body">
				        <h2 class="float-start titulo-card fw-bold">Saldo geral:</h2>
				        <p>				        
						   <button class="btn p-0 config-olho bi bi-eye " type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"></button>
						</p>
						<div class="collapse.show" id="collapseExample">
						  <div class="card-body">						    
						   <p class="float-start fw-bold secundarias-card">R$ ${saldoTotal}</p><br>
				        <hr class="zera-borda-hr">
				        <hr>
				        <h2 class="float-start fw-bold secundarias-card">Minhas contas:</h2>
				        <br>				
				        <table class="table float-start fw-bold secundarias-card">
					  		<c:forEach items="${contas}" var="c">
							  <tr>
								<td class="float-start bi bi-cash-coin p-1"> ${c.descricao}</td>
								<td class="float-end">R$ ${c.saldo}</td>
							 </tr>
							</c:forEach>	
						</table>						    
						  </div>
					  </div>				   				        
				        <br><br>
				        <a href="fintech?acao=listarContas" class="btn btn-primary botoes-card">Gerenciar contas</a>
					  </div>
			    </div>
		   	</div>
		   	<hr class="zera-borda-hr">		   	
		   	<div class="col">
		   		<div class="card estilo-card cor-flutuante text-center">
			      <div class="card-body">
			      	<button class="btn p-0 config-olho" type="submit"><i class="bi bi-eye"></i></button>
			        <h2 class="float-start fw-bold secundarias-card">Meus investimentos:</h2>
			        <br><br>
			        <p class="card-text float-start"><i class="bi bi-cash-coin p-2"></i>XP Investimentos</p>
			        <p class="card-text float-end secundarias-card">R$ 0,00</p>
			        <br><br>
			        <a href="errorPageNotFounded.jsp" class="btn btn-primary botoes-card">Gerenciar investimentos</a>
			      </div>
			    </div>
		   	</div>
		   	<hr class="zera-borda-hr">
		   	<div class="col">
		   		<div class="card estilo-card cor-flutuante text-center">
			      <div class="card-body p-2">
			      	<h2 class="float-start fw-bold secundarias-card p-0">Meus objetivos:</h2>
			      	<br><br>	        
			        <p><a href="errorPageNotFounded.jsp" class="text-card-left float-start">Cadastre um objetivo financeiro</a></p>
			        <a href="errorPageNotFounded.jsp"><i class="bi bi-box-arrow-in-right float-end icon-seta"></i><a/>
			      </div>
			    </div>
		    </div>
		</main>
		<footer class="container-fluid rodape d-flex justify-content-center">
		  <div class="row">
		   	<div class="col text-center" >
		   		<a href="#" class="btn btn-primary botoesrodape" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions"><i class="bi bi-list"></i>Menu</a>
		   		<a href="fintech?acao=abrir-form-cadastro-receita" class="btn btn-primary botoesrodape"><i class="bi bi-plus-square"></i>Receita</a>
		   		<a href="fintech?acao=abrir-form-cadastro-gasto" class="btn btn-primary botoesrodape"><i class="bi bi-dash-square"></i>Gasto</a>
		   		<a href="fintech?acao=abrir-analise" class="btn btn-primary botoesrodape"><i class="bi bi-bar-chart-line"></i>Análise</a>
		  	</div>
			<div>
				<div class="offcanvas offcanvas-start cor-offcanvas" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
					<div class="offcanvas-header cor-offcanvas">
						<h4 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">Menu:</h4>
						<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
					</div>
					<div class="offcanvas-body">
						<p><a href="fintech?acao=listarReceitas" class="text-card-left">Minhas receitas</a></p>
						<p><a href="fintech?acao=listarGastos" class="text-card-left">Meus gastos</a></p>
						<p><a href="errorPageNotFounded.jsp" class="text-card-left">Meus investimentos</a></p>
						<p><a href="errorPageNotFounded.jsp" class="text-card-left">Objetivos financeiros</a></p>
						<p><a href="fintech?acao=abrir-form-edicao-usuario" class="text-card-left">Dados cadastrais</a></p>
						<p><a href="fintech?acao=sair" class="text-card-left">Sair</a></p>
					</div>
				</div>
			</div>
		</footer>
	</body>
</html>