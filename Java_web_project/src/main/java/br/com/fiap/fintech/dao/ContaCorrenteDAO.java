package br.com.fiap.fintech.dao;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.CategoriaGasto;
import br.com.fiap.fintech.bean.ContaCorrente;
import br.com.fiap.fintech.bean.Gastos;
import br.com.fiap.fintech.bean.InstituicaoFinanceira;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.singleton.ConnectionClass;

public class ContaCorrenteDAO {
    
      private Connection conexao;
    
      public void insert(ContaCorrente conta) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
    
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_CNT_C (NR_CONTA, T_INST_FIN_CD_INST, T_USUARIO_CD_USUARIO, VL_SALDO, DS_DESCRICAO, NR_AGENCIA) VALUES (?, ?, ?, ?, ?, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setInt(1, conta.getNumeroConta());
          stmt.setInt(2, conta.getInstituicao().getNumeroInstituicao());
          stmt.setDouble(3, conta.getUser().getId());          
          stmt.setDouble(4, conta.getSaldo());
          stmt.setString(5, conta.getDescricao());
          stmt.setInt(6, conta.getNumeroAgencia());
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
      
      
      public List<ContaCorrente> getAll(int id) {  // Método "GET ALL" solicitado no trabalho
    	    List<ContaCorrente> lista = new ArrayList<ContaCorrente>();
    	    PreparedStatement stmt = null;
    	    ResultSet rs = null;
    	    
    	    try {
    	      conexao = ConnectionClass.obterConexao();
    	      stmt = conexao.prepareStatement("SELECT * FROM T_CNT_C WHERE T_USUARIO_CD_USUARIO = ?");
    	      stmt.setInt(1, id);
    	      rs = stmt.executeQuery();
    	  
    	    //Percorre todos os registros encontrados
    	    
    	    while (rs.next()) {
    	        int numeroConta = rs.getInt("NR_CONTA");
    	        int codigoInstituicao = rs.getInt("T_INST_FIN_CD_INST");
    	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
    	        double saldo = rs.getDouble("VL_SALDO");
    	        String descricao = rs.getString("DS_DESCRICAO");
    	        int numeroAgencia = rs.getInt("NR_AGENCIA");
    	        	        
    	        // Instanciando user e instituicao para viabilizar instanciação da "conta"
    	        
    	        Usuario user = new Usuario();
    	        user.setId(codigoUsuario);
    	       
    	        InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
    	        instituicao.setNumeroInstituicao(codigoInstituicao);
    	          	        
    	        //Instanciando objeto "conta" para armazenar informações coletadas do banco de dados
    	        
    	        ContaCorrente conta = new ContaCorrente(numeroAgencia, numeroConta, instituicao, user, descricao, saldo);
    	        
    	        //Adiciona a "conta" na lista
    	        
    	        lista.add(conta);
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
      
      
      public void atualizar(ContaCorrente conta) {   
  		  PreparedStatement stmt = null;

  		try {
  			conexao = ConnectionClass.obterConexao();
  			String sql = "UPDATE T_CNT_C SET NR_CONTA = ?, T_INST_FIN_CD_INST = ?, T_USUARIO_CD_USUARIO = ?, VL_SALDO = ?, DS_DESCRICAO = ?, NR_AGENCIA = ? WHERE NR_CONTA = ? AND T_INST_FIN_CD_INST = ?";
  			stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, conta.getNumeroConta());
            stmt.setInt(2, conta.getInstituicao().getNumeroInstituicao());
            stmt.setInt(3, conta.getUser().getId());
            stmt.setDouble(4, conta.getSaldo());
            stmt.setString(5, conta.getDescricao());
            stmt.setInt(6, conta.getNumeroAgencia());            
            stmt.setInt(7, conta.getNumeroConta());
            stmt.setInt(8, conta.getInstituicao().getNumeroInstituicao());
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
      
      
   	// Método remover
    	
      
    	public void remover(int conta, int instituicao) {
    			PreparedStatement stmt1 = null;
    			PreparedStatement stmt2 = null;
    			PreparedStatement stmt3 = null;
    			
    			try {
    				conexao = ConnectionClass.obterConexao();
    				String sql1 = "DELETE FROM T_GAST WHERE T_CNT_C_NR_CONTA = ? AND T_CNT_C_T_INST_FIN_CD_INST = ?";
    				stmt1 = conexao.prepareStatement(sql1);
    				stmt1.setInt(1, conta);
    				stmt1.setInt(2, instituicao);
    				stmt1.executeUpdate();
    				stmt1.close();
    			} catch (SQLException e) {
	  				e.printStackTrace();
	  			}
    			
    			try {
    		    			
    				String sql2 = "DELETE FROM T_RECEB WHERE T_CNT_C_NR_CONTA = ? AND T_CNT_C_T_INST_FIN_CD_INST = ?";
    				stmt2 = conexao.prepareStatement(sql2);
    				stmt2.setInt(1, conta);
    				stmt2.setInt(2, instituicao);     				
    				stmt2.executeUpdate();
    				stmt2.close();
    			} catch (SQLException e) {
	  				e.printStackTrace();
	  			}
    			
    			try {
    			
    				String sql3 = "DELETE FROM T_CNT_C WHERE NR_CONTA = ? AND T_INST_FIN_CD_INST = ?";
    				stmt3 = conexao.prepareStatement(sql3);
    				stmt3.setInt(1, conta);
    				stmt3.setInt(2, instituicao);
    				stmt3.executeUpdate();
    				stmt3.close();
    			} catch (SQLException e) {
	  				e.printStackTrace();
	  			}finally {
    	  			try {
    	  				conexao.close();
    	  			} catch (SQLException e) {
    	  				e.printStackTrace();
    	  			}
    	  		}
    		    			
    	}
    	
    	
    	// Método buscar por código
      	
      	
      	public ContaCorrente buscar(int conta, int instituicao) {
      		
      		ContaCorrente conta1 = null;
      		PreparedStatement stmt = null;
      		ResultSet rs = null;
      		
      		try {
      			conexao = ConnectionClass.obterConexao();
      			stmt = conexao.prepareStatement("SELECT * FROM T_CNT_C WHERE NR_CONTA = ? AND T_INST_FIN_CD_INST = ?");
      			stmt.setInt(1, conta);
      			stmt.setInt(2, instituicao);      			
      			rs = stmt.executeQuery();

      			if (rs.next()){
      				int numeroConta = rs.getInt("NR_CONTA");
        	        int numeroInstituicao = rs.getInt("T_INST_FIN_CD_INST");
        	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
        	        double saldo = rs.getDouble("VL_SALDO");
        	        String descricao = rs.getString("DS_DESCRICAO");
        	        int numeroAgencia = rs.getInt("NR_AGENCIA");
      				
        	        Usuario user = new Usuario();
        	        user.setId(codigoUsuario);
        	               	        
        	        InstituicaoFinanceira instituicao1 = new InstituicaoFinanceira();
        	        instituicao1.setNumeroInstituicao(numeroInstituicao);
        	        
        	        ContaCorrente conta2 = new ContaCorrente();
        	        conta2.setNumeroConta(numeroConta);	        
        	        
        	        
      				conta1 = new ContaCorrente(numeroAgencia, numeroConta, instituicao1, user, descricao, saldo);
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
      		return conta1;
      	}
      	
      	
       // Método buscar SALDO TOTAL do usuário
      	
      	
      	public Double saldoTotal(Usuario user) {
      		
      		Double saldoTotal = 0.00;
      		PreparedStatement stmt = null;
      		ResultSet rs = null;
      		
      		try {
      			conexao = ConnectionClass.obterConexao();
      			stmt = conexao.prepareStatement("SELECT * FROM T_CNT_C WHERE T_USUARIO_CD_USUARIO = ?");
      			stmt.setInt(1, user.getId());      			     			
      			rs = stmt.executeQuery();
      			
      			Double total = 0.0;
      			
      			while (rs.next()){
      		       
      				total += rs.getDouble("VL_SALDO");
      				
      				saldoTotal = total;
      			
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
      		return saldoTotal;
      	}
}