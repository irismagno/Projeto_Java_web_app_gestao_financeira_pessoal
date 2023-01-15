package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.ContaInvestimento;
import br.com.fiap.fintech.bean.InstituicaoFinanceira;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.singleton.ConnectionClass;

public class ContaInvestimentoDAO {
    
      private Connection conexao;
    
      public void insert(ContaInvestimento investimento) {  // Método "INSERT" solicitado no trabalho
        PreparedStatement stmt = null;
    
        try {
          conexao = ConnectionClass.obterConexao();
          String sql = "INSERT INTO T_CNT_INVEST(CD_INVEST, T_USUARIO_CD_USUARIO, T_TIPO_INVEST_CD_TP_INVEST, T_INST_FIN_CD_INST,"
          		+ " DS_DESCRICAO, VL_VALOR, DT_DT_APLICACAO, NR_PRAZO, NR_CONTA, NR_AGENCIA, VL_TAXA  ) VALUES (SQ_CD_INVEST.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
          stmt = conexao.prepareStatement(sql);
          stmt.setDouble(1, investimento.getUser().getId());
          stmt.setDouble(2, investimento.getTipoDeInvestimento());
          stmt.setDouble(3, investimento.getInstituicao().getNumeroInstituicao());          
          stmt.setString(4, investimento.getDescricao());
          stmt.setDouble(5, investimento.getSaldo());
          java.sql.Date dataAplicacao = new java.sql.Date(investimento.getDataAplicacao().getTimeInMillis());
          stmt.setDate(6, dataAplicacao);         
          stmt.setDouble(7, investimento.getPrazo());
          stmt.setDouble(8, investimento.getNumeroConta());
          stmt.setDouble(9, investimento.getNumeroAgencia());
          stmt.setDouble(10, investimento.getTaxaDeJuros());
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
      
      
      public List<ContaInvestimento> getAll() {  // Método "GET ALL" solicitado no trabalho
    	    List<ContaInvestimento> lista = new ArrayList<ContaInvestimento>();
    	    PreparedStatement stmt = null;
    	    ResultSet rs = null;
    	    
    	    try {
    	      conexao = ConnectionClass.obterConexao();
    	      stmt = conexao.prepareStatement("SELECT * FROM T_CNT_INVEST");
    	      rs = stmt.executeQuery();
    	  
    	    //Percorre todos os registros encontrados
    	    
    	    while (rs.next()) {
    	        int codigoInvestimento = rs.getInt("CD_INVEST");
    	        int codigoUsuario = rs.getInt("T_USUARIO_CD_USUARIO");
    	        int codigoTipoInvestimento = rs.getInt("T_TIPO_INVEST_CD_TP_INVEST");
    	        int codigoInstituicao = rs.getInt("T_INST_FIN_CD_INST");
    	        String descricao = rs.getString("DS_DESCRICAO");
    	        double valor = rs.getDouble("VL_VALOR");
    	        java.sql.Date data1 = rs.getDate("DT_DT_APLICACAO");
    	        Calendar data = Calendar.getInstance();
    	        data.setTimeInMillis(data1.getTime());
    	        int prazo = rs.getInt("NR_PRAZO");
    	        int numeroConta = rs.getInt("NR_CONTA");
    	        int numeroAgencia = rs.getInt("NR_AGENCIA");
    	        double taxaJuros = rs.getDouble("VL_TAXA");
    	        
    	        // Instanciando user e instituicao para viabilizar instanciação da "conta investimento"
    	        
    	        Usuario user = new Usuario();
    	        user.setId(codigoUsuario);
    	       
    	        InstituicaoFinanceira instituicao1 = new InstituicaoFinanceira();
    	        instituicao1.setNumeroInstituicao(codigoInstituicao);
    	          	        
    	        //Instanciando objeto "conta investimento" para armazenar informações coletadas do banco de dados
    	        
    	        ContaInvestimento investimento = new ContaInvestimento(codigoInvestimento, user, instituicao1, numeroAgencia, numeroConta,
    	        		codigoTipoInvestimento, taxaJuros, prazo, descricao, data, valor);
    	        
    	        //Adiciona a "conta investimento" na lista
    	        
    	        lista.add(investimento);
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