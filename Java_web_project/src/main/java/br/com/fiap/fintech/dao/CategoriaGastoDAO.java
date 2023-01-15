package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.CategoriaGasto;
import br.com.fiap.fintech.singleton.ConnectionClass;

public class CategoriaGastoDAO {
    
      private Connection conexao;
    
      public void insert(CategoriaGasto categoria) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
    
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_CATEGORIAS(CD_CATEGORIA,DS_CATEGORIA) VALUES (SQ_CD_CATEGORIAS.NEXTVAL, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, categoria.getCategoria());
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
      
      
      public List<CategoriaGasto> getAll() {  // Método "GET ALL" solicitado no trabalho
    	    List<CategoriaGasto> lista = new ArrayList<CategoriaGasto>();
    	    PreparedStatement stmt = null;
    	    ResultSet rs = null;
    	    
    	    try {
    	      conexao = ConnectionClass.obterConexao();
    	      stmt = conexao.prepareStatement("SELECT * FROM T_CATEGORIAS");
    	      rs = stmt.executeQuery();
    	  
    	    // Percorre todos os registros encontrados
    	    
    	    while (rs.next()) {
    	        int codigoCategoria = rs.getInt("CD_CATEGORIA");
    	        String categoria1 = rs.getString("DS_CATEGORIA");
    	           	        
    	        // Instanciando objeto "categoria" para armazenar informações coletadas do banco de dados
    	        
    	        CategoriaGasto categoria = new CategoriaGasto(codigoCategoria, categoria1);
    	        
    	        // Adiciona a "categoria" na lista
    	        
    	        lista.add(categoria);
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