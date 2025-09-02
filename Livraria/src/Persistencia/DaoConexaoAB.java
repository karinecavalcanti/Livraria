/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

/**
 *
 * @author aluno
 */

import java.sql.*;
import Util.Diversos;


public class DaoConexaoAB {
   private static DaoConexaoAB instancia = null;
   private Connection con;  
   private String servidor;
   private String bD;
   private String usuario;
   private String senha; 
   
   private DaoConexaoAB() { //constructor  
      this.con = null;
      this.servidor = "localhost"; //127.0.0.1
      this.bD  = "bdLivraria";
      this.usuario = "root";
      this.senha = "root";//"usuario";
      final String Driver="com.mysql.cj.jdbc.Driver";
      String url;
      url   = "jdbc:mysql://" + servidor + '/' + bD;
      url +="?createDatabaseIfNotExist=true";
      url +="&user="+ usuario + "&password=" + senha;
      try{
            Class.forName(Driver).newInstance();
            con=DriverManager.getConnection(url);
      }
      catch(ClassNotFoundException | IllegalAccessException 
               | InstantiationException | SQLException e) {
               Diversos.mostrarDados( "Erro de conexão " + e.getMessage(), "Sistema", false);            
      }		         
   }  
   	      
   public synchronized static DaoConexaoAB getInstancia() {  
      if (instancia == null) {  
              instancia = new DaoConexaoAB();  
      }  
      return instancia;  
   }
   
  //Executar sem permitir que o processo seja interrompido   
   public static void setInstancia(DaoConexaoAB instancia) {
        DaoConexaoAB.instancia = instancia;
    }
      
   public Connection getCon() {
        if (con == null)   
           getInstancia();  
          
        return con;  	
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}