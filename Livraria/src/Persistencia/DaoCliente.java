/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.Cliente;
import Negocio.Pessoa;

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
public class DaoCliente implements DaoBasico {
    
    public DaoCliente() {
       String inst = "CREATE TABLE IF NOT EXISTS Cliente"
                   + " (Identifica INT NOT NULL"
                   + ", Cpf VARCHAR(25) NOT NULL"
                   + ", Sexo VARCHAR(10) NOT NULL"
                   + ", Rg VARCHAR(18) NOT NULL"
                   + ", Cep VARCHAR(15) NOT NULL"
                   + ", PRIMARY KEY (Identifica)"
                   + ", KEY Codigo (Identifica)" 
                   + ", CONSTRAINT IdentificaC FOREIGN KEY (Identifica) REFERENCES "
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
      Cliente c = (Cliente) o;
      String inst="Insert into Cliente";
        inst += "(Identifica, Cpf, Sexo, Rg, Cep) values(?, ?, ?, ?, ?)";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, c.getIdentifica());
              pS.setString(2, c.getCpf());
              pS.setString(3, c.getSexo());
              pS.setString(4, c.getRg());
              pS.setString(5, c.getCep());
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
      Cliente c = (Cliente) o;
      String inst = "Update Cliente set Cpf = ?, Sexo = ?, Rg = ?, Cep = ? ";
      inst += " where Identifica = ?";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setString(1, c.getCpf());
              pS.setString(2, c.getSexo());
              pS.setString(3, c.getRg());
              pS.setString(4, c.getCep());
              pS.setInt(5, c.getIdentifica());
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
      Cliente c = (Cliente) o;
      String inst="Delete from Cliente where Identifica = ?";
      try{
         Connection con = DaoConexaoAB.getInstancia().getCon();
         try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, c.getIdentifica());
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
        String inst="Select * from Cliente where Identifica = ?";
        Cliente c = null ;
        ResultSet rS;
        try {  
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, identifica);
                rS = pS.executeQuery();
                DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());     
                if (rS.next()) {
                   c = new Cliente();  
                   c.setIdentifica(rS.getInt("Identifica"));
                   c.setCpf(rS.getString("Cpf"));
                   c.setSexo(rS.getString("Sexo"));
                   c.setRg(rS.getString("Rg"));
                   c.setCep(rS.getString("Cep"));
                }
           }  
         } catch (SQLException e) {
             throw new RuntimeException(e.getMessage()); 
           }
       return(c);    
    }
    
    @Override
     public List<Object> carrega() {
       String inst  = "Select * from Cliente";
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
