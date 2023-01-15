package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.InstituicaoFinanceira;
import br.com.fiap.fintech.singleton.ConnectionClass;

public class InstituicaoFinanceiraDAO {
    
      private Connection conexao;
    
      public void insert(InstituicaoFinanceira instituicao) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
    
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_INST_FIN(CD_INST,NM_INST) VALUES (?, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setInt(1, instituicao.getNumeroInstituicao());
          stmt.setString(2, instituicao.getNomeInstituicao());
          stmt.executeUpdate();
        } catch (SQLException e) {  // Tratando exceções
          e.printStackTrace();
        } finally {
          try {
            stmt.close();
            conexao.close();
          } catch (SQLException e) {  // Tratando exceções
            e.printStackTrace();
          }
        }
      }
      
      
      public List<InstituicaoFinanceira> getAll() {  // Método "GET ALL" solicitado no trabalho
    	     List<InstituicaoFinanceira> lista = new ArrayList<InstituicaoFinanceira>();
    	     PreparedStatement stmt = null;
    	     ResultSet rs = null;
    	    
    	    try {
    	      conexao = ConnectionClass.obterConexao();
    	      stmt = conexao.prepareStatement("SELECT * FROM T_INST_FIN");
    	      rs = stmt.executeQuery();
    	  
    	    // Percorre todos os registros encontrados
    	    
    	    while (rs.next()) {
    	        int numeroInstituicao = rs.getInt("CD_INST");
    	        String nomeInstituicao = rs.getString("NM_INST");
    	           	        
    	        // Instanciando objeto "instituição" para armazenar informações coletadas do banco de dados
    	        
    	        InstituicaoFinanceira instituicao = new InstituicaoFinanceira(nomeInstituicao, numeroInstituicao);
    	        
    	        // Adiciona a "categoria" na lista
    	        
    	        lista.add(instituicao);
    	      }
    	    } catch (SQLException e) {
    	      e.printStackTrace();
    	    }finally {
    	      try {
    	        stmt.close();
    	        rs.close();
    	        conexao.close();
    	      } catch (SQLException e) {
    	        e.printStackTrace();
    	      }
    	    }
    	    return lista;
      }
}