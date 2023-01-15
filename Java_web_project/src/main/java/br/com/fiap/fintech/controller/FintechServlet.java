package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.fintech.bean.CategoriaGasto;
import br.com.fiap.fintech.bean.ContaCorrente;
import br.com.fiap.fintech.bean.Gastos;
import br.com.fiap.fintech.bean.InstituicaoFinanceira;
import br.com.fiap.fintech.bean.Recebimentos;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.CategoriaGastoDAO;
import br.com.fiap.fintech.dao.ContaCorrenteDAO;
import br.com.fiap.fintech.dao.GastosDAO;
import br.com.fiap.fintech.dao.InstituicaoFinanceiraDAO;
import br.com.fiap.fintech.dao.RecebimentosDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;

@WebServlet("/fintech")
public class FintechServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static GastosDAO gastoDAO;
	private CategoriaGastoDAO categoriaGastoDAO;
	private RecebimentosDAO recebimentoDAO;
	private static ContaCorrenteDAO contaCorrenteDAO;
	private InstituicaoFinanceiraDAO instituicaoDAO;
	private static UsuarioDAO usuarioDAO;
	private static RecebimentosDAO recebimentosDAO;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		gastoDAO = new GastosDAO();
		recebimentosDAO = new RecebimentosDAO();
		categoriaGastoDAO = new CategoriaGastoDAO();
		recebimentoDAO = new RecebimentosDAO();
		contaCorrenteDAO = new ContaCorrenteDAO();
		instituicaoDAO = new InstituicaoFinanceiraDAO();
		usuarioDAO = new UsuarioDAO();
		
	}
	
	
	// Início doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "abrir-index":
			abrirIndex(request, response);
			break;
		case "abrir-analise":
			abrirAnalise(request, response);
			break;
		case "listarGastos":
			listarGastos(request, response);	
			break;
		case "listarReceitas":
			listarReceitas(request, response);	
			break;
		case "listarContas":
			listarContas(request, response);	
			break;
		case "abrir-form-edicao-gastos":
			abrirFormEdicaoGastos(request, response);
			break;
		case "abrir-form-edicao-receitas":
			abrirFormEdicaoReceitas(request, response);
			break;
		case "abrir-form-edicao-contas":
			abrirFormEdicaoContas(request, response);
			break;
		case "abrir-form-edicao-usuario":
			abrirFormEdicaoUsuario(request, response);
			break;
		case "abrir-form-cadastro-gasto":
			abrirFormCadastroGasto(request, response);
			break;
		case "abrir-form-cadastro-receita":
			abrirFormCadastroReceita(request, response);
			break;
		case "abrir-form-cadastro-conta":
			abrirFormCadastroConta(request, response);
			break;
		case "abrir-form-cadastro-primeira-conta":
			abrirFormCadastroPrimeiraConta(request, response);
			break;
		case "abrir-form-cadastro-usuario":
			abrirFormCadastroUsuario(request, response);
			break;
		case "sair":
			sair(request, response);
			break;
		}	
	}
	
	
	// Métodos utilizados pelo "doGet" relacionados à ANÁLISE
	
	private void abrirAnalise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		nomeUsuario(request);
		saldoTotal(request);
		totalGastos(request);
		totalRecebimentos(request);
		percentualAlimentacao(request);
		percentualSaude(request);
		percentualEducacao(request);
		percentualTransporte(request);
		percentualMoradia(request);
		percentualLazer(request);
		percentualOutros(request);
		request.getRequestDispatcher("analise.jsp").forward(request, response);
	}	
		
	static void saldoTotal(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");  		                                                                                     
		Double saldo = contaCorrenteDAO.saldoTotal(user);
		DecimalFormat df = new DecimalFormat("#,##0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String s = df.format(saldo); 
		request.setAttribute("saldoTotal", s);
	}
	
	static void totalGastos(HttpServletRequest request) {			
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");			                                                                                     
			Double total = gastoDAO.totalGastos(user);
			DecimalFormat df = new DecimalFormat("#,##0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			String s = df.format(total); 
			request.setAttribute("totalGastos", s);
		}
	
	static void percentualAlimentacao(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");		
		Double totalGastos = gastoDAO.totalGastos(user);	
		Double totalAlimentacao = gastoDAO.totalAlimentacao(user);		
		Double percentual = ((totalAlimentacao * 100) / totalGastos);		
		request.setAttribute("percentualAlimentacao", Math.ceil(percentual));
	}
	
	static void percentualSaude(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");		
		Double totalGastos = gastoDAO.totalGastos(user);	
		Double totalSaude = gastoDAO.totalSaude(user);		
		Double percentual = ((totalSaude * 100) / totalGastos);		
		request.setAttribute("percentualSaude", Math.ceil(percentual));
	}
	
	static void percentualEducacao(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");		
		Double totalGastos = gastoDAO.totalGastos(user);	
		Double totalEducacao = gastoDAO.totalEducacao(user);		
		Double percentual = ((totalEducacao * 100) / totalGastos);		
		request.setAttribute("percentualEducacao", Math.ceil(percentual));
	}
	
	static void percentualTransporte(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");		
		Double totalGastos = gastoDAO.totalGastos(user);	
		Double totalTransporte = gastoDAO.totalTransporte(user);		
		Double percentual = ((totalTransporte * 100) / totalGastos);		
		request.setAttribute("percentualTransporte", Math.ceil(percentual));
	}
	
	static void percentualMoradia(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");		
		Double totalGastos = gastoDAO.totalGastos(user);	
		Double totalMoradia = gastoDAO.totalMoradia(user);		
		Double percentual = ((totalMoradia * 100) / totalGastos);		
		request.setAttribute("percentualMoradia", Math.ceil(percentual));
	}
	
	static void totalRecebimentos(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user"); 		                                                                                     
		Double total = recebimentosDAO.totalRecebimentos(user);
		DecimalFormat df = new DecimalFormat("#,##0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String s = df.format(total); 
		request.setAttribute("totalRecebimentos", s);
	}
	
	static void percentualLazer(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");		
		Double totalGastos = gastoDAO.totalGastos(user);	
		Double totalLazer = gastoDAO.totalLazer(user);		
		Double percentual = ((totalLazer * 100) / totalGastos);		
		request.setAttribute("percentualLazer", Math.ceil(percentual));
	}
	
	static void percentualOutros(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");		
		Double totalGastos = gastoDAO.totalGastos(user);	
		Double totalOutros = gastoDAO.totalOutros(user);		
		Double percentual = ((totalOutros * 100) / totalGastos);		
		request.setAttribute("percentualOutros", Math.ceil(percentual));
	}
	
	
	// Métodos utilizados pelo "doGet" relacionados à várias classes
	
	protected static void nomeUsuario(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		Usuario user1 = usuarioDAO.validarUsuario(user);
		
		request.setAttribute("nomeUsuario", user1.getNome());
	}	

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<CategoriaGasto> lista = categoriaGastoDAO.getAll();
		request.setAttribute("categorias", lista);
	}
	
	protected static void carregarOpcoesContas(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		
		List<ContaCorrente> lista = contaCorrenteDAO.getAll(user.getId());
		request.setAttribute("contas", lista);
	}
	
	private void carregarOpcoesInstituicoes(HttpServletRequest request) {
		List<InstituicaoFinanceira> lista = instituicaoDAO.getAll();
		request.setAttribute("instituicoes", lista);
	}
	
	
	// Métodos utilizados pelo "doGet" relacionados ao INDEX
	
	protected static void abrirIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		saldoTotal(request);
		carregarOpcoesContas(request);
		nomeUsuario(request);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}	
	

	// Métodos utilizados pelo "doGet" relacionados a classe GASTOS
	
	private void abrirFormEdicaoGastos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Gastos gasto1 = gastoDAO.buscar(id);
		request.setAttribute("gasto", gasto1);
		carregarOpcoesCategoria(request);
		nomeUsuario(request);
		request.getRequestDispatcher("edicao-gastos.jsp").forward(request, response);
	}	
	
	private void abrirFormCadastroGasto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		
		boolean teste = false;
				
		for(ContaCorrente conta : contaCorrenteDAO.getAll(user.getId())) {	
			teste = true;
		}
		
		if(teste) {
		
		carregarOpcoesCategoria(request);
		carregarOpcoesContas(request);
		carregarOpcoesInstituicoes(request);
		nomeUsuario(request);
		request.getRequestDispatcher("cadastro-gastos.jsp").forward(request, response);
		
		} else {
			abrirFormCadastroPrimeiraConta(request, response);
		}		
		
	}
	
	
	private void listarGastos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user"); 
		
		List<Gastos> lista = gastoDAO.getAll(user.getId());
		request.setAttribute("gasto", lista);
		nomeUsuario(request);
		request.getRequestDispatcher("consulta-gastos.jsp").forward(request, response);
	}  
		
	
	// Métodos utilizados pelo "doGet" relacionados à classe RECEITAS
	
	private void abrirFormCadastroReceita(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		
		boolean teste = false;
				
		for(ContaCorrente conta : contaCorrenteDAO.getAll(user.getId())) {	
			teste = true;
		}
		
		if(teste) {
		
		carregarOpcoesInstituicoes(request);
		carregarOpcoesContas(request);
		nomeUsuario(request);
		request.getRequestDispatcher("cadastro-receitas.jsp").forward(request, response);
		
		} else {
			abrirFormCadastroPrimeiraConta(request, response);
		}
	}	
				
	
	private void listarReceitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user"); 
		
		List<Recebimentos> lista = recebimentoDAO.getAll(user.getId());
		request.setAttribute("recebimento", lista);
		nomeUsuario(request);
		request.getRequestDispatcher("consulta-receitas.jsp").forward(request, response);
	}  
	
	private void abrirFormEdicaoReceitas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		nomeUsuario(request);
		int id = Integer.parseInt(request.getParameter("codigo"));
		Recebimentos recebimento1 = recebimentoDAO.buscar(id);
		request.setAttribute("recebimento", recebimento1);
		request.getRequestDispatcher("edicao-receitas.jsp").forward(request, response);
	}
	
	
	// Métodos utilizados pelo "doGet" relacionados à classe CONTA CORRENTE
	
	private void carregarOpcoesInstituicao(HttpServletRequest request) {
		List<InstituicaoFinanceira> lista = instituicaoDAO.getAll();
		request.setAttribute("instituicoes", lista);
	}
	
	private void abrirFormCadastroConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		nomeUsuario(request);
		carregarOpcoesInstituicao(request);
		request.getRequestDispatcher("cadastro-contacorrente.jsp").forward(request, response);
	}
	
	private void abrirFormCadastroPrimeiraConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		nomeUsuario(request);
		carregarOpcoesInstituicao(request);
		request.getRequestDispatcher("cadastro-primeira-conta.jsp").forward(request, response);
	}
	
	private void listarContas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user");
		
		List<ContaCorrente> lista = contaCorrenteDAO.getAll(user.getId());
		request.setAttribute("conta", lista);
		nomeUsuario(request);
		request.getRequestDispatcher("consulta-contascorrentes.jsp").forward(request, response);
	}  
	
	private void abrirFormEdicaoContas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int conta = Integer.parseInt(request.getParameter("conta"));
		int instituicao = Integer.parseInt(request.getParameter("instituicao"));
		ContaCorrente conta1 = contaCorrenteDAO.buscar(conta, instituicao);
		request.setAttribute("conta", conta1);
		carregarOpcoesCategoria(request);
		nomeUsuario(request);
		request.getRequestDispatcher("edicao-contascorrentes.jsp").forward(request, response);
	}
	
	
	// Métodos utilizados pelo "doGet" relacionados ao CADASTRO USUARIO
	
	private void abrirFormCadastroUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.getRequestDispatcher("cadastro-usuario.jsp").forward(request, response);
	}
	
	private void abrirFormEdicaoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario user = (Usuario) session.getAttribute("user"); 		
	                                                                                
		request.setAttribute("usuario", user);
		request.getRequestDispatcher("edicao-usuario.jsp").forward(request, response);
	}
		
		
		
	// Início doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "realizarLogin":
			realizarLogin(request, response);
			break;
		case "cadastrarGasto":
			cadastrarGasto(request, response);
			break;
		case "cadastrarReceita":
			cadastrarReceita(request, response);
			break;
		case "cadastrarConta":
			cadastrarConta(request, response);
			break;
		case "cadastrarPrimeiraConta":
			cadastrarPrimeiraConta(request, response);
			break;
		case "cadastrarUsuario":
			cadastrarUsuario(request, response);
			break;
		case "editarGasto":
			editarGasto(request,response);
			break;
		case "editarReceita":
			editarReceita(request,response);
			break;
		case "editarConta":
			editarConta(request,response);
			break;
		case "editarUsuario":
			editarUsuario(request,response);
			break;
		case "excluirGasto":
			excluirGasto(request, response);
			break;
		case "excluirReceita":
			excluirReceita(request, response);
			break;
		case "excluirConta":
			excluirConta(request, response);
			break;
		}
	}
	
	protected void realizarLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		Usuario usuario1 = new Usuario();
		usuario1 = usuarioDAO.validarUsuario(usuario);
		
		if (usuario1 != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", usuario1);
			abrirIndex(request, response);
			
							
		} else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
	}
	
		
	// Métodos utilizados pelo "doPost" relacionados à classe GASTOS
	
	private void editarGasto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigoGasto = Integer.parseInt(request.getParameter("codigo"));
			double valor = Double.parseDouble(request.getParameter("valor").replace("," , "."));
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			int codigoInstituicao = Integer.parseInt(request.getParameter("instituicao"));
			int numeroConta = Integer.parseInt(request.getParameter("conta"));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");                       
			
			InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
			instituicao.setNumeroInstituicao(codigoInstituicao);
			
			ContaCorrente conta = new ContaCorrente();
			conta.setNumeroConta(numeroConta);
			
			CategoriaGasto categoria = new CategoriaGasto();
			categoria.setCodigoCategoria(codigoCategoria);	
			
			ContaCorrente conta1 = new ContaCorrente();
			ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
			conta1 = contaDAO.buscar(numeroConta, codigoInstituicao);
			
			Gastos gasto1 = new Gastos();
			gasto1 = gastoDAO.buscar(codigoGasto);
			
			conta1.incluirRecebimento(gasto1.getValor());
			conta1.incluirGasto(valor);
			
			contaDAO.atualizar(conta1);
			
			Gastos gasto = new Gastos(codigoGasto, user, categoria, valor, data, descricao, instituicao, conta);
			gastoDAO.atualizar(gasto);

			request.setAttribute("msg", "Gasto atualizado!");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, confirme os dados");
		}
		listarGastos(request,response);
	}

	private void cadastrarGasto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double valor = Double.parseDouble(request.getParameter("valor").replace("," , "."));
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			int codigoInstituicao = Integer.parseInt(request.getParameter("instituicao"));
			int numeroConta = Integer.parseInt(request.getParameter("conta"));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");                          
			
			InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
			instituicao.setNumeroInstituicao(codigoInstituicao);
			
			ContaCorrente conta = new ContaCorrente();
			conta.setNumeroConta(numeroConta);
						
			CategoriaGasto categoria = new CategoriaGasto();
			categoria.setCodigoCategoria(codigoCategoria);	
			
			ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
			ContaCorrente conta1 = contaDAO.buscar(numeroConta, codigoInstituicao);
			
			conta1.incluirGasto(valor);
			
			contaDAO.atualizar(conta1);			
			
			Gastos gasto = new Gastos(0, user, categoria, valor, data, descricao, instituicao, conta);
			gastoDAO.insert(gasto);
			
							
			request.setAttribute("msg", "Gasto cadastrado!");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Dados inválidos!");
		}
		 abrirFormCadastroGasto(request, response);
	}
		
	private void excluirGasto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigoExcluir"));
					
		try {
			
			ContaCorrente conta = new ContaCorrente();
			ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
			
			Gastos gasto = new Gastos();
			gasto = gastoDAO.buscar(codigo);
			conta = contaDAO.buscar(gasto.getConta().getNumeroConta(), gasto.getInstituicao().getNumeroInstituicao());
			
			conta.incluirRecebimento(gasto.getValor());
			
			contaDAO.atualizar(conta);
						
			gastoDAO.remover(codigo);
			
			request.setAttribute("msg", "Gasto removido!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao excluir");
		}
		listarGastos(request,response);
	}
	
	
	// Métodos utilizados pelo "doPost" relacionados à classe RECEITAS
	
	private void cadastrarReceita(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double valor = Double.parseDouble(request.getParameter("valor").replace("," , "."));
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			int codigoInstituicao = Integer.parseInt(request.getParameter("instituicao"));
			int numeroConta = Integer.parseInt(request.getParameter("conta"));
						
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");                       
			
			InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
			instituicao.setNumeroInstituicao(codigoInstituicao);
			
			ContaCorrente conta = new ContaCorrente();
			conta.setNumeroConta(numeroConta);
			
			ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
			ContaCorrente conta1 = contaDAO.buscar(numeroConta, codigoInstituicao);
			
			conta1.incluirRecebimento(valor);
			
			contaDAO.atualizar(conta1);	
									
			Recebimentos recebimento = new Recebimentos(0, user, valor, data, descricao, instituicao, conta);
			recebimentoDAO.insert(recebimento);

			request.setAttribute("msg", "Receita cadastrada!");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Dados inválidos!");
		}
		 abrirFormCadastroReceita(request, response);
	}
	
	private void editarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigoRecebimento = Integer.parseInt(request.getParameter("codigo"));
			double valor = Double.parseDouble(request.getParameter("valor").replace("," , "."));
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			int codigoInstituicao = Integer.parseInt(request.getParameter("instituicao"));
			int numeroConta = Integer.parseInt(request.getParameter("conta"));
						
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");                   
			
			InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
			instituicao.setNumeroInstituicao(codigoInstituicao);
			
			ContaCorrente conta = new ContaCorrente();
			conta.setNumeroConta(numeroConta);
			
			ContaCorrente conta1 = new ContaCorrente();
			ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
			conta1 = contaDAO.buscar(numeroConta, codigoInstituicao);
			
			Recebimentos recebimento1 = new Recebimentos();
			recebimento1 = recebimentoDAO.buscar(codigoRecebimento);
			
			conta1.incluirGasto(recebimento1.getValor());
			conta1.incluirRecebimento(valor);
			
			contaDAO.atualizar(conta1);
			
			Recebimentos recebimento = new Recebimentos(codigoRecebimento, user, valor, data, descricao, instituicao, conta);
			recebimentoDAO.atualizar(recebimento);

			request.setAttribute("msg", "Receita atualizado!");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, confirme os dados");
		}
		listarReceitas(request, response);
	}
	
	private void excluirReceita(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigoExcluir"));
		
		try {
			
			ContaCorrente conta = new ContaCorrente();
			ContaCorrenteDAO contaDAO = new ContaCorrenteDAO();
			
			Recebimentos recebimento = new Recebimentos();
			recebimento = recebimentoDAO.buscar(codigo);
			conta = contaDAO.buscar(recebimento.getConta().getNumeroConta(), recebimento.getInstituicao().getNumeroInstituicao());
			
			conta.incluirGasto(recebimento.getValor());
			
			contaDAO.atualizar(conta);
						
			gastoDAO.remover(codigo);
			
			recebimentoDAO.remover(codigo);
			
			request.setAttribute("msg", "Receita removida!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao excluir");
		}
		listarReceitas(request,response);
	}
	
	// Métodos utilizados pelo "doPost" relacionados à classe CONTA CORRENTE
	
	private void cadastrarConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double saldo = Double.parseDouble(request.getParameter("saldo").replace("," , "."));
			String descricao = request.getParameter("descricao");
			int codigoInstituicao = Integer.parseInt(request.getParameter("instituicao"));
			int conta1 = Integer.parseInt(request.getParameter("contaCorrente1"));
			int agencia1 = Integer.parseInt(request.getParameter("numeroAgencia1"));
						
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");                   
			
			InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
			instituicao.setNumeroInstituicao(codigoInstituicao);
			
			ContaCorrente contaCorrente1 = new ContaCorrente(agencia1, conta1, instituicao, user, descricao, saldo);
			
			contaCorrenteDAO.insert(contaCorrente1);

			request.setAttribute("msg", "Conta cadastrada!");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, confira os dados");
		}
		listarContas(request, response);
	}
	
	private void cadastrarPrimeiraConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double saldo = Double.parseDouble(request.getParameter("saldo").replace("," , "."));
			String descricao = request.getParameter("descricao");
			int codigoInstituicao = Integer.parseInt(request.getParameter("instituicao"));
			int conta1 = Integer.parseInt(request.getParameter("contaCorrente1"));
			int agencia1 = Integer.parseInt(request.getParameter("numeroAgencia1"));
						
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");			
						
			InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
			instituicao.setNumeroInstituicao(codigoInstituicao);
			
			ContaCorrente contaCorrente1 = new ContaCorrente(agencia1, conta1, instituicao, user, descricao, saldo);
			
			contaCorrenteDAO.insert(contaCorrente1);

			request.setAttribute("msg", "Conta cadastrada!");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, confira os dados");
		}
		abrirIndex(request, response);
	}
	
	private void excluirConta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int contaExcluir = Integer.parseInt(request.getParameter("contaExcluir"));
		int instituicaoExcluir = Integer.parseInt(request.getParameter("instituicaoExcluir"));
		try {
			contaCorrenteDAO.remover(contaExcluir, instituicaoExcluir);
			request.setAttribute("msg", "Conta removida!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao excluir");
		}
		listarContas(request,response);
	}
	
	private void editarConta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int numeroConta = Integer.parseInt(request.getParameter("numeroConta"));
			int numeroInstituicao = Integer.parseInt(request.getParameter("numeroInstituicao"));
			String descricao = request.getParameter("descricao");
			double saldo = Double.parseDouble(request.getParameter("saldo").replace("," , "."));
			int agencia = Integer.parseInt(request.getParameter("agencia"));
									
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("user");                                     
			
			InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
			instituicao.setNumeroInstituicao(numeroInstituicao);
						
			ContaCorrente conta = new ContaCorrente(agencia, numeroConta, instituicao, user, descricao, saldo);
			contaCorrenteDAO.atualizar(conta);

			request.setAttribute("msg", "Conta atualizada!");
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, confirme os dados");
			}
			listarContas(request, response);
		}
	
	
		// Métodos utilizados pelo "doPost" relacionados à classe USUÁRIO
	
		private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			boolean verify = false;
			try {
				String nome = request.getParameter("nome");
				String sobrenome = request.getParameter("sobrenome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String confirmarSenha = request.getParameter("confirmarSenha");
				int genero = Integer.parseInt(request.getParameter("genero"));
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar data = Calendar.getInstance();
				data.setTime(format.parse(request.getParameter("nascimento")));
				
				if(senha.equalsIgnoreCase(confirmarSenha)) {
					
				verify = true;
												
				Usuario user = new Usuario(0, nome, sobrenome, genero, data, email, "senha");
				user.setSenha(senha);
				
					if(usuarioDAO.verificaEmail(user)) {
						
					
					usuarioDAO.insert(user);					
			
					request.setAttribute("msg", "Cadastro realizado!");
					
					Usuario user1 = usuarioDAO.validarUsuario(user);				
					HttpSession session = request.getSession();
					session.setAttribute("user", user1);
					
					} else {
						request.setAttribute("erro", "E-mail já cadastrado!");
						verify = false;
						
					}
				
				} else {
					request.setAttribute("erro", "Senha diferente!");
					
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Dados inválidos!");
			}
			
			if(verify) {
			
			abrirFormCadastroPrimeiraConta(request, response);
			
			} else {
				abrirFormCadastroUsuario(request,response);
			}
		}
		
		
		
		private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				
				String nome = request.getParameter("nome");
				String sobrenome = request.getParameter("sobrenome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String confirmarSenha = request.getParameter("confirmarSenha");
				int genero = Integer.parseInt(request.getParameter("genero"));
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Calendar data = Calendar.getInstance();
				data.setTime(format.parse(request.getParameter("nascimento")));
				
				if(senha.equalsIgnoreCase(confirmarSenha)) {
				
				HttpSession session = request.getSession();
				Usuario user1 = (Usuario) session.getAttribute("user"); 
												
				Usuario user = new Usuario(user1.getId(), nome, sobrenome, genero, data, email, "senha");        
				user.setSenha(senha);
				usuarioDAO.atualizar(user);					
				session.setAttribute("user", user);				
		
				request.setAttribute("msg", "Cadastro alterado!");
				
				} else {
					request.setAttribute("erro", "Senha diferente!");
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("erro", "Por favor, confirme os dados");
			}
			abrirFormEdicaoUsuario(request,response);
		}
		
		
		protected void sair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session= request.getSession();
			session.invalidate();
			request.getRequestDispatcher("Login.jsp").forward(request, response);

			}

}


