package br.com.fiap.fintech.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.CategoriaGasto;
import br.com.fiap.fintech.bean.ContaCorrente;
import br.com.fiap.fintech.bean.ContaInvestimento;
import br.com.fiap.fintech.bean.Gastos;
import br.com.fiap.fintech.bean.InstituicaoFinanceira;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Recebimentos;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.CategoriaGastoDAO;
import br.com.fiap.fintech.dao.ContaCorrenteDAO;
import br.com.fiap.fintech.dao.ContaInvestimentoDAO;
import br.com.fiap.fintech.dao.GastosDAO;
import br.com.fiap.fintech.dao.InstituicaoFinanceiraDAO;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.dao.RecebimentosDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;

public class TestesClassesDAO {

	public static void main(String[] args) {
		
	
		
		
		
		
		RecebimentosDAO recebimentosDAO = new RecebimentosDAO();		      
		      
		List<Recebimentos> listaRecebimentos = recebimentosDAO.getAll(3);
		
		System.out.println("********** Imprimindo consulta tabela recebimentos **********");
		      
		for (Recebimentos item : listaRecebimentos) {
			System.out.println("Código do recebimentos: " + item.getCodigoRecebimento() + "  Usuário: " + item.getUser().getId()
			+ "  Categoria do gasto: " + "  Valor: " + item.getValor() + "  Data: " + item.getData().getTime()
			+ "  Descrição: " + item.getDescricao() + "  Instituição Financeira: " + item.getInstituicao().getNumeroInstituicao()
			+ "  Conta: " + item.getConta().getNumeroConta());
	    }
		
		System.out.println("*********************************************************");
		
		
		GastosDAO gastosDao = new GastosDAO();		      
	      
		List<Gastos> listaGastos = gastosDao.getAll(3);
		
		System.out.println("********** Imprimindo consulta tabela gastos **********");
		      
		for (Gastos item : listaGastos) {
			System.out.println("Código do gasto: " + item.getCodigoGasto() + "  Usuário: " + item.getUser().getId()
			+ "  Categoria do gasto: " + item.getCategoria().getCodigoCategoria() + "  Valor: " + item.getValor() + "  Data: " + item.getData().getTime()
			+ "  Descrição: " + item.getDescricao() + "  Instituição Financeira: " + item.getInstituicao().getNumeroInstituicao()
			+ "  Conta: " + item.getConta().getNumeroConta());
	    }
		
		System.out.println("*********************************************************");
		
		
		
	Usuario user = new Usuario();
	user.setId(65);
	GastosDAO gastosDAO = new GastosDAO();
	System.out.println(gastosDAO.totalGastos(user));
	
	System.out.println(gastosDAO.totalAlimentacao(user));
	
	
	
	
		
}	
	
}
