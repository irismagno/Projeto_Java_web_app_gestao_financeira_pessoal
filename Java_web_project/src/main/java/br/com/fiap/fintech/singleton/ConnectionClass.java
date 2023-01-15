package br.com.fiap.fintech.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {  // Classe respons�vel pela conex�o com o banco de dados

  public static Connection obterConexao() {
    Connection conexao = null;
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM94900", "130889");
      
    } catch (Exception e) {  // Tratando excess�es
      e.printStackTrace();
    }
    return conexao;
  }

}