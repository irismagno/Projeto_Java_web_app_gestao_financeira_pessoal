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

public class GastosDAO {
    
      private Connection conexao;
    
      public void insert(Gastos gasto) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
    
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_GAST(CD_GAST, T_USUARIO_CD_USUARIO, T_CATEGORIAS_CD_CATEGORIA, VL_VALOR, DT_DATA, DS_DESCRICAO, T_CNT_C_T_INST_FIN_CD_INST, T_CNT_C_NR_CONTA) VALUES (SQ_CD_GAST.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setDouble(1, gasto.getUser().getId());
          stmt.setDouble(2, gasto.getCategoria().getCodigoCategoria());
          stmt.setDouble(3, gasto.getValor());          
          java.sql.Date data = new java.sql.Date(gasto.getData().getTimeInMillis());
          stmt.setDate(4, data);
          stmt.setString(5, gasto.getDescricao());
          stmt.setDouble(6, gasto.getInstituicao().getNumeroInstituicao());
          stmt.setDouble(7,gasto.getConta().getNumeroConta());     
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
      
      
      public List<Gastos> getAll(int id) {  // Método "GET ALL" solicitado no trabalho
    	    List<Gastos> lista = new ArrayList<Gastos>();
    	    PreparedStatement stmt = null;
    	    ResultSet rs = null;
    	    
    	    try {
    	      conexao = ConnectionClass.obterConexao();
    	      stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_USUARIO_CD_USUARIO = ? ORDER BY DT_DATA DESC");   
    	      stmt.setInt(1, id);
    	      rs = stmt.executeQuery();
    	  
    	    //Percorre todos os registros encontrados
    	    
    	    while (rs.next()) {
    	        int codigoGasto = rs.getInt("CD_GAST");
    	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
    	        int codigoCategoria = rs.getInt("T_CATEGORIAS_CD_CATEGORIA");
    	        double valor = rs.getDouble("VL_VALOR");
    	        java.sql.Date data1 = rs.getDate("DT_DATA");
    	        Calendar data = Calendar.getInstance();
    	        data.setTimeInMillis(data1.getTime());
    	        String descricao = rs.getString("DS_DESCRICAO");
    	        int codigoInstituicao = rs.getInt("T_CNT_C_T_INST_FIN_CD_INST");
    	        int numeroConta = rs.getInt("T_CNT_C_NR_CONTA");
    	        
    	        // Instanciando user, categoria, instituicao e conta para viabilizar instanciação do "gasto"
    	        
    	        Usuario user = new Usuario();
    	        user.setId(codigoUsuario);
    	        
    	        CategoriaGasto categoria = new CategoriaGasto();
    	        categoria.setCodigoCategoria(codigoCategoria);
    	        
    	        InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
    	        instituicao.setNumeroInstituicao(codigoInstituicao);
    	        
    	        ContaCorrente conta = new ContaCorrente();
    	        conta.setNumeroConta(numeroConta);    	        
    	        
    	        //Instanciando objeto "gasto" para armazenar informações coletadas do banco de dados
    	        
    	        Gastos gasto = new Gastos(codigoGasto, user, categoria, valor, data, descricao, instituicao, conta);
    	        
    	        //Adiciona o "gasto" na lista
    	        
    	        lista.add(gasto);
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
       
  
      public void atualizar(Gastos gasto) {
  		  PreparedStatement stmt = null;

  		try {
  			conexao = ConnectionClass.obterConexao();
  			String sql = "UPDATE T_GAST SET T_CATEGORIAS_CD_CATEGORIA = ?, VL_VALOR = ?, DT_DATA = ?, DS_DESCRICAO = ?, T_CNT_C_T_INST_FIN_CD_INST = ?, T_CNT_C_NR_CONTA = ? WHERE CD_GAST = ?";
  			stmt = conexao.prepareStatement(sql);
  			stmt.setInt(1, gasto.getCategoria().getCodigoCategoria());
  			stmt.setDouble(2, gasto.getValor());
  			java.sql.Date data = new java.sql.Date(gasto.getData().getTimeInMillis());
  			stmt.setDate(3, data);
  			stmt.setString(4, gasto.getDescricao());
  			stmt.setInt(5, gasto.getInstituicao().getNumeroInstituicao());
  			stmt.setInt(6, gasto.getConta().getNumeroConta());
  			stmt.setInt(7, gasto.getCodigoGasto());
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
  				String sql = "DELETE FROM T_GAST WHERE CD_GAST = ?";
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
  	
  	
  	public Gastos buscar(int codigo) {
  		
  		Gastos gasto = null;
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
  		
  		try {
  			conexao = ConnectionClass.obterConexao();
  			stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE CD_GAST = ?");
  			stmt.setInt(1, codigo);
  			rs = stmt.executeQuery();

  			if (rs.next()){
  				int codigoGasto = rs.getInt("CD_GAST");
    	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
    	        int codigoCategoria = rs.getInt("T_CATEGORIAS_CD_CATEGORIA");
    	        double valor = rs.getDouble("VL_VALOR");
    	        java.sql.Date data1 = rs.getDate("DT_DATA");
    	        Calendar data = Calendar.getInstance();
    	        data.setTimeInMillis(data1.getTime());
    	        String descricao = rs.getString("DS_DESCRICAO");
    	        int codigoInstituicao = rs.getInt("T_CNT_C_T_INST_FIN_CD_INST");
    	        int numeroConta = rs.getInt("T_CNT_C_NR_CONTA");
  				
    	        Usuario user = new Usuario();
    	        user.setId(codigoUsuario);
    	        
    	        CategoriaGasto categoria = new CategoriaGasto();
    	        categoria.setCodigoCategoria(codigoCategoria);
    	        
    	        InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
    	        instituicao.setNumeroInstituicao(codigoInstituicao);
    	        
    	        ContaCorrente conta = new ContaCorrente();
    	        conta.setNumeroConta(numeroConta);	        
    	        
    	        
  				gasto = new Gastos(codigoGasto, user, categoria, valor, data, descricao, instituicao, conta);
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
  		return gasto;
  	}
  	
  	
  	public Double totalGastos(Usuario user) {
  		
  		Double totalGastos = 0.00;
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
  		
  		try {
  			conexao = ConnectionClass.obterConexao();
  			stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_USUARIO_CD_USUARIO = ?");
  			stmt.setInt(1, user.getId());      			     			
  			rs = stmt.executeQuery();
  			
  			Double total = 0.0;
  			
  			while (rs.next()){
  		       
  				total += rs.getDouble("VL_VALOR");
  				
  				totalGastos = total;
  			
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
  		return totalGastos;
  	}
  	
  	
	public Double totalAlimentacao(Usuario user) {
  		
  		Double totalAlimentacao = 0.00;
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
  		
  		try {
  			conexao = ConnectionClass.obterConexao();
  			stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 1 AND T_USUARIO_CD_USUARIO = ?");
  			stmt.setInt(1, user.getId());      			     			
  			rs = stmt.executeQuery();
  			
  			Double total = 0.0;
  			
  			while (rs.next()){
  		       
  				total += rs.getDouble("VL_VALOR");
  				
  				totalAlimentacao = total;
  			
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
  		return totalAlimentacao;
  	}
	
	
	public Double totalSaude(Usuario user) {
	  		
	  		Double totalSaude = 0.00;
	  		PreparedStatement stmt = null;
	  		ResultSet rs = null;
	  		
	  		try {
	  			conexao = ConnectionClass.obterConexao();
	  			stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 4 AND T_USUARIO_CD_USUARIO = ?");
	  			stmt.setInt(1, user.getId());      			     			
	  			rs = stmt.executeQuery();
	  			
	  			Double total = 0.0;
	  			
	  			while (rs.next()){
	  		       
	  				total += rs.getDouble("VL_VALOR");
	  				
	  				totalSaude = total;
	  			
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
	  		return totalSaude;
	  	}
	       
    
	public Double totalEducacao(Usuario user) {
			
			Double totalEducacao = 0.00;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = ConnectionClass.obterConexao();
				stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 5 AND T_USUARIO_CD_USUARIO = ?");
				stmt.setInt(1, user.getId());      			     			
				rs = stmt.executeQuery();
				
				Double total = 0.0;
				
				while (rs.next()){
			       
					total += rs.getDouble("VL_VALOR");
					
					totalEducacao = total;
				
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
			return totalEducacao;
		}

	public Double totalTransporte(Usuario user) {
		
		Double totalTransporte = 0.00;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionClass.obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 3 AND T_USUARIO_CD_USUARIO = ?");
			stmt.setInt(1, user.getId());      			     			
			rs = stmt.executeQuery();
			
			Double total = 0.0;
			
			while (rs.next()){
		       
				total += rs.getDouble("VL_VALOR");
				
				totalTransporte = total;
			
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
		return totalTransporte;
	}
	
	
	public Double totalMoradia(Usuario user) {
			
			Double totalMoradia = 0.00;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = ConnectionClass.obterConexao();
				stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 7 AND T_USUARIO_CD_USUARIO = ?");
				stmt.setInt(1, user.getId());      			     			
				rs = stmt.executeQuery();
				
				Double total = 0.0;
				
				while (rs.next()){
			       
					total += rs.getDouble("VL_VALOR");
					
					totalMoradia = total;
				
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
			return totalMoradia;
		}

	public Double totalLazer(Usuario user) {
		
		Double totalLazer = 0.00;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionClass.obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 2 AND T_USUARIO_CD_USUARIO = ?");
			stmt.setInt(1, user.getId());      			     			
			rs = stmt.executeQuery();
			
			Double total = 0.0;
			
			while (rs.next()){
		       
				total += rs.getDouble("VL_VALOR");
				
				totalLazer = total;
			
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
		return totalLazer;
	}

	public Double totalOutros(Usuario user) {
			
			Double totalOutros = 0.00;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = ConnectionClass.obterConexao();
				stmt = conexao.prepareStatement("SELECT * FROM T_GAST WHERE T_CATEGORIAS_CD_CATEGORIA = 6 AND T_USUARIO_CD_USUARIO = ?");
				stmt.setInt(1, user.getId());      			     			
				rs = stmt.executeQuery();
				
				Double total = 0.0;
				
				while (rs.next()){
			       
					total += rs.getDouble("VL_VALOR");
					
					totalOutros = total;
				
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
			return totalOutros;
		}
	
      
}