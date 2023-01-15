package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.CategoriaGasto;
import br.com.fiap.fintech.bean.ContaCorrente;
import br.com.fiap.fintech.bean.Gastos;
import br.com.fiap.fintech.bean.InstituicaoFinanceira;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.singleton.ConnectionClass;

public class UsuarioDAO {
	
	  private Connection conexao;
	    
      public void insert(Usuario user) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
       
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_USUARIO (CD_USUARIO, T_GENERO_CD_GENERO, NM_NOME, NM_SOBRENOME, DS_EMAIL, CD_SENHA, DT_DATA_NASC) VALUES (SQ_CD_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setInt(1, user.getGenero());
          stmt.setString(2, user.getNome());
          stmt.setString(3, user.getSobrenome());
          stmt.setString(4, user.getEmail());
          stmt.setString(5, user.getSenha());          
          java.sql.Date nascimento = new java.sql.Date(user.getDataNascimento().getTimeInMillis());
          stmt.setDate(6, nascimento);         
          stmt.executeUpdate();
        } catch (SQLException e) { 
          e.printStackTrace();
        } finally {
  			try {
  				stmt.close();
  				conexao.close();
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}
  		}
      }
      
            
      // Método atualizar
       
  
      public void atualizar(Usuario user) {
  		  PreparedStatement stmt = null;

  		try {
  			conexao = ConnectionClass.obterConexao();
  			String sql = "UPDATE T_USUARIO SET T_GENERO_CD_GENERO = ?, NM_NOME = ?, NM_SOBRENOME = ?, DS_EMAIL = ?, CD_SENHA = ?, DT_DATA_NASC = ?  WHERE CD_USUARIO = ?";
  			stmt = conexao.prepareStatement(sql);
  			stmt.setInt(1, user.getGenero());
  			stmt.setString(2, user.getNome());
  			stmt.setString(3, user.getSobrenome());
  			stmt.setString(4, user.getEmail());
  			stmt.setString(5, user.getSenha());
  			java.sql.Date nascimento = new java.sql.Date(user.getDataNascimento().getTimeInMillis());
  			stmt.setDate(6, nascimento); 
  			stmt.setInt(7, user.getId());
  			stmt.executeUpdate();
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				stmt.close();
  				conexao.close();
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}
  		}

    }
  
    
  	// Método buscar por código
  	
  	
  	public Usuario buscar(int codigo) {
  		
  		Usuario user = null;
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
  		
  		try {
  			conexao = ConnectionClass.obterConexao();
  			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE CD_USUARIO = ?");
  			stmt.setInt(1, codigo);
  			rs = stmt.executeQuery();

  			if (rs.next()){
  				int codigoUsuario = rs.getInt("CD_USUARIO");
    	        int genero = rs.getInt("T_GENERO_CD_GENERO");
    	        String nome = rs.getString("NM_NOME");
    	        String sobrenome = rs.getString("NM_SOBRENOME");
    	        String email = rs.getString("DS_EMAIL");
    	        String senha = rs.getString("CD_SENHA");
    	        java.sql.Date data = rs.getDate("DT_DATA_NASC");
    	        Calendar nascimento = Calendar.getInstance();
    	        nascimento.setTimeInMillis(data.getTime());
    	         				
    	        user = new Usuario(codigoUsuario, nome, sobrenome, genero, nascimento, email, senha);
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
  		return user;
  	}
  	
  	
  	public Usuario validarUsuario(Usuario user) {
  		Usuario user1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionClass.obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL = ? AND CD_SENHA = ?");
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigoUsuario = rs.getInt("CD_USUARIO");
    	        int genero = rs.getInt("T_GENERO_CD_GENERO");
    	        String nome = rs.getString("NM_NOME");
    	        String sobrenome = rs.getString("NM_SOBRENOME");
    	        String email = rs.getString("DS_EMAIL");
    	        String senha = rs.getString("CD_SENHA");
    	        java.sql.Date data = rs.getDate("DT_DATA_NASC");
    	        Calendar nascimento = Calendar.getInstance();
    	        nascimento.setTimeInMillis(data.getTime());
    	         				
    	        user1 = new Usuario(codigoUsuario, nome, sobrenome, genero, nascimento, email, senha);    	       
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
		 return user1;
	}
  	
  	
  	
  	public boolean verificaEmail(Usuario user) {
  		Usuario user1 = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionClass.obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL = ?");
			stmt.setString(1, user.getEmail());
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigoUsuario = rs.getInt("CD_USUARIO");
    	        int genero = rs.getInt("T_GENERO_CD_GENERO");
    	        String nome = rs.getString("NM_NOME");
    	        String sobrenome = rs.getString("NM_SOBRENOME");
    	        String email = rs.getString("DS_EMAIL");
    	        String senha = rs.getString("CD_SENHA");
    	        java.sql.Date data = rs.getDate("DT_DATA_NASC");
    	        Calendar nascimento = Calendar.getInstance();
    	        nascimento.setTimeInMillis(data.getTime());
    	         				
    	        user1 = new Usuario(codigoUsuario, nome, sobrenome, genero, nascimento, email, senha); 
    	        
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
		 if(user1 != null) {
			 return false;
		 } else {
			 return true;
		 }
	}

       

}	