/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class DaoFornecedor implements DaoBasico {
    
     public DaoFornecedor() {
       String inst = "CREATE TABLE IF NOT EXISTS Fornecedor"
                   + " (Identifica INT NOT NULL"
                   + ", Cnpj VARCHAR(25) NOT NULL"
                   + ", PRIMARY KEY (Identifica)"
                   + ", KEY Codigo (Identifica)" 
                   + ", CONSTRAINT IdentificaF FOREIGN KEY (Identifica) REFERENCES "
                   + "Pessoa (Identifica)"
                   + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
       try {
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();
               
          }
          DaoConexaoAB.getInstancia().setCon(con);
       } 
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
       } 
    }
    
   @Override
    public boolean incluir(Object o){
      boolean result = true;  
      Fornecedor f = (Fornecedor) o;
      String inst="Insert into Fornecedor";
        inst += "(Identifica, Cnpj) values(?, ?)";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, f.getIdentifica());
              pS.setString(2, f.getCnpj());
              pS.execute();
          }
         DaoConexaoAB.getInstancia().setCon(con);
      } catch (SQLException e) {
         result = false; 
         throw new RuntimeException(e.getMessage());
      }
      return(result);
    }
    
    @Override
    public boolean alterar(Object o){
      boolean result = true;  
      Fornecedor f = (Fornecedor) o;
      String inst = "Update Fornecedor set Cnpj = ?";
      inst += " where Identifica = ?";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setString(1, f.getCnpj());
              pS.setInt(2, f.getIdentifica());
              pS.execute();
          }
         DaoConexaoAB.getInstancia().setCon(con);
      } catch (SQLException e) {
         result = false;  
         throw new RuntimeException(e.getMessage());
      }
      return(result);
    }
   
    @Override
    public boolean excluir(Object o){
      boolean result = true;  
      Fornecedor f = (Fornecedor) o;
      String inst="Delete from Fornecedor where Identifica = ?";
      try{
         Connection con = DaoConexaoAB.getInstancia().getCon();
         try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, f.getIdentifica());
              pS.execute();
         }
         DaoConexaoAB.getInstancia().setCon(con);
      } catch (SQLException e) {
          result = false;
          throw new RuntimeException(e.getMessage());
      }
      return(result);
    }  
     
    @Override
    public Object busca(int identifica, int nada){
        String inst="Select * from Fornecedor where Identifica = ?";
        Fornecedor f = null ;
        ResultSet rS;
        try {  
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, identifica);
                rS = pS.executeQuery();
                DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());     
                if (rS.next()) {
                   f = new Fornecedor();  
                   f.setIdentifica(rS.getInt("Identifica"));
                   f.setCnpj(rS.getString("Cnpj"));
                }
           }  
         } catch (SQLException e) {
             throw new RuntimeException(e.getMessage()); 
           }
       return(f);    
    }
    
    @Override
     public List<Object> carrega() {
       String inst  = "Select * from Fornecedor order by Cnpj";
       List<Object> lista = new ArrayList<>();
       ResultSet rS; 
       Object o;
       try{
           try (PreparedStatement pS = 
                    DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
              rS = pS.executeQuery(inst);
              DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());
              if (rS != null)
                while (rS.next()) {
                  o = busca(rS.getInt("Identifica"), 0);
                  lista.add(o);
                }               
              pS.close();
           }
       } catch(SQLException e) {
           throw new RuntimeException(e); 
         }
       return(lista); 
     } 
}
