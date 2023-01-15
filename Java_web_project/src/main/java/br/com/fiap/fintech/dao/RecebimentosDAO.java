package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.fiap.fintech.bean.ContaCorrente;
import br.com.fiap.fintech.bean.InstituicaoFinanceira;
import br.com.fiap.fintech.bean.Recebimentos;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.singleton.ConnectionClass;

public class RecebimentosDAO {
    
      private Connection conexao;
    
      public void insert(Recebimentos recebimento) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
    
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_RECEB(CD_REC, T_USUARIO_CD_USUARIO, VL_VALOR, DT_DATA, DS_DESCRICAO, T_CNT_C_T_INST_FIN_CD_INST, T_CNT_C_NR_CONTA) VALUES (SQ_CD_REC.NEXTVAL, ?, ?, ?, ?, ?, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setDouble(1, recebimento.getUser().getId());
          stmt.setDouble(2, recebimento.getValor());          
          java.sql.Date data = new java.sql.Date(recebimento.getData().getTimeInMillis());
          stmt.setDate(3, data);
          stmt.setString(4, recebimento.getDescricao());
          stmt.setDouble(5, recebimento.getInstituicao().getNumeroInstituicao());
          stmt.setDouble(6, recebimento.getConta().getNumeroConta());     
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
      
      
      public List<Recebimentos> getAll(int id) {  // Método "GET ALL" solicitado no trabalho
    	    List<Recebimentos> lista = new ArrayList<Recebimentos>();
    	    PreparedStatement stmt = null;
    	    ResultSet rs = null;
    	    
    	    try {
    	      conexao = ConnectionClass.obterConexao();
    	      stmt = conexao.prepareStatement("SELECT * FROM T_RECEB WHERE T_USUARIO_CD_USUARIO = ? ORDER BY DT_DATA DESC");
    	      stmt.setInt(1, id);
    	      rs = stmt.executeQuery();
    	  
    	    //Percorre todos os registros encontrados
    	    
    	    while (rs.next()) {
    	        int codigoRecebimento = rs.getInt("CD_REC");
    	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
    	        double valor = rs.getDouble("VL_VALOR");
    	        java.sql.Date data1 = rs.getDate("DT_DATA");
    	        Calendar data = Calendar.getInstance();
    	        data.setTimeInMillis(data1.getTime());
    	        String descricao = rs.getString("DS_DESCRICAO");
    	        int codigoInstituicao = rs.getInt("T_CNT_C_T_INST_FIN_CD_INST");
    	        int numeroConta = rs.getInt("T_CNT_C_NR_CONTA");
    	        
    	        // Instanciando user, instituicao e conta para viabilizar instanciação do "recebimento"
    	        
    	        Usuario user = new Usuario();
    	        user.setId(codigoUsuario);
    	           	        	        
    	        InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
    	        instituicao.setNumeroInstituicao(codigoInstituicao);
    	        
    	        ContaCorrente conta = new ContaCorrente();
    	        conta.setNumeroConta(numeroConta);    	        
    	        
    	        //Instanciando objeto "gasto" para armazenar informações coletadas do banco de dados
    	        
    	        Recebimentos recebimento = new Recebimentos(codigoRecebimento, user, valor, data, descricao, instituicao, conta);
    	        
    	        //Adiciona o "gasto" na lista
    	        
    	        lista.add(recebimento);
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
      
      
      // Método atualizar
      
      
      public void atualizar(Recebimentos recebimento) {
  		  PreparedStatement stmt = null;

  		try {
  			conexao = ConnectionClass.obterConexao();
  			String sql = "UPDATE T_RECEB SET VL_VALOR = ?, DT_DATA = ?, DS_DESCRICAO = ?, T_CNT_C_T_INST_FIN_CD_INST = ?, T_CNT_C_NR_CONTA = ? WHERE CD_REC = ?";
  			stmt = conexao.prepareStatement(sql);
  			stmt.setDouble(1, recebimento.getValor());
  			java.sql.Date data = new java.sql.Date(recebimento.getData().getTimeInMillis());
  			stmt.setDate(2, data);
  			stmt.setString(3, recebimento.getDescricao());
  			stmt.setInt(4, recebimento.getInstituicao().getNumeroInstituicao());
  			stmt.setInt(5, recebimento.getConta().getNumeroConta());
  			stmt.setInt(6, recebimento.getCodigoRecebimento());
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
  	
 
  	public void remover(int codigo) {
  			PreparedStatement stmt = null;

  			try {
  				conexao = ConnectionClass.obterConexao();
  				String sql = "DELETE FROM T_RECEB WHERE CD_REC = ?";
  				stmt = conexao.prepareStatement(sql);
  				stmt.setInt(1, codigo);
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
  	
  	
  	public Recebimentos buscar(int codigo) {
  		
  		Recebimentos recebimento = null;
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
  		
  		try {
  			conexao = ConnectionClass.obterConexao();
  			stmt = conexao.prepareStatement("SELECT * FROM T_RECEB WHERE CD_REC = ?");
  			stmt.setInt(1, codigo);
  			rs = stmt.executeQuery();

  			if (rs.next()){
  				int codigoRecebimento = rs.getInt("CD_REC");
    	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
    	        double valor = rs.getDouble("VL_VALOR");
    	        java.sql.Date data1 = rs.getDate("DT_DATA");
    	        Calendar data = Calendar.getInstance();
    	        data.setTimeInMillis(data1.getTime());
    	        String descricao = rs.getString("DS_DESCRICAO");
    	        int codigoInstituicao = rs.getInt("T_CNT_C_T_INST_FIN_CD_INST");
    	        int numeroConta = rs.getInt("T_CNT_C_NR_CONTA");
  				
    	        Usuario user = new Usuario();
    	        user.setId(codigoUsuario);
    	        
    	        InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
    	        instituicao.setNumeroInstituicao(codigoInstituicao);
    	        
    	        ContaCorrente conta = new ContaCorrente();
    	        conta.setNumeroConta(numeroConta);	        
    	        
    	        
  				recebimento = new Recebimentos(codigoRecebimento, user, valor, data, descricao, instituicao, conta);
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
  		return recebimento;
  	}
  	
  	
public Double totalRecebimentos(Usuario user) {
  		
  		Double totalRecebimentos = 0.00;
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
  		
  		try {
  			conexao = ConnectionClass.obterConexao();
  			stmt = conexao.prepareStatement("SELECT * FROM T_RECEB WHERE T_USUARIO_CD_USUARIO = ?");
  			stmt.setInt(1, user.getId());      			     			
  			rs = stmt.executeQuery();
  			
  			Double total = 0.0;
  			
  			while (rs.next()){
  		       
  				total += rs.getDouble("VL_VALOR");
  				
  				totalRecebimentos = total;
  			
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
  		return totalRecebimentos;
  	}
       
}

