package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.singleton.ConnectionClass;

public class ObjetivoDAO {
    
      private Connection conexao;
    
      public void insert(Objetivo objetivo) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
    
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_OBJETIVOS(CD_OBJ, T_USUARIO_CD_USUARIO, NM_OBJETIVO, VL_VALOR, DS_DESCRICAO, DT_REALIZACAO) VALUES (SQ_CD_OBJ.NEXTVAL, ?, ?, ?, ?, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setDouble(1, objetivo.getUser().getId());
          stmt.setString(2, objetivo.getNomeObjetivo());
          stmt.setDouble(3, objetivo.getValor()); 
          stmt.setString(4, objetivo.getDescricao());
          java.sql.Date data = new java.sql.Date(objetivo.getDataRealizacao().getTimeInMillis());
          stmt.setDate(5, data);
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
      
      
      public List<Objetivo> getAll() {  // Método "GET ALL" solicitado no trabalho
    	    List<Objetivo> lista = new ArrayList<Objetivo>();
    	    PreparedStatement stmt = null;
    	    ResultSet rs = null;
    	    
    	    try {
    	      conexao = ConnectionClass.obterConexao();
    	      stmt = conexao.prepareStatement("SELECT * FROM T_OBJETIVOS");
    	      rs = stmt.executeQuery();
    	  
    	    //Percorre todos os registros encontrados
    	    
    	    while (rs.next()) {
    	        int codigoObjetivo = rs.getInt("CD_OBJ");
    	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
    	        String nomeObjetivo = rs.getString("NM_OBJETIVO");
    	        double valor = rs.getDouble("VL_VALOR");
    	        String descricao = rs.getString("DS_DESCRICAO");
    	        java.sql.Date data1 = rs.getDate("DT_REALIZACAO");
    	        Calendar data = Calendar.getInstance();
    	        data.setTimeInMillis(data1.getTime());
    	        
    	        
    	            	        
    	        // Instanciando um usuário para viabilizar instanciação do "objetivo"
    	        
    	        Usuario user = new Usuario();
    	        user.setId(codigoUsuario);
    	        
    	        //Instanciando "objetivo" para armazenar informações coletadas do banco de dados
    	        
    	        Objetivo objetivo = new Objetivo(codigoObjetivo, user, nomeObjetivo, valor, descricao, data);
    	        
    	        //Adiciona o "objetivo" na lista
    	        
    	        lista.add(objetivo);
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