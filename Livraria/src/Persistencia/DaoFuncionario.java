/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author aluno
 */
public class DaoFuncionario implements DaoBasico {
  
    public DaoFuncionario() {
       String inst = "CREATE TABLE IF NOT EXISTS Funcionario"
                   + " (Identifica INT NOT NULL"
                   + ", CartTrab VARCHAR(10) NOT NULL"
                   + ", Cpf VARCHAR(18) NOT NULL"
                   + ", Salario FLOAT NOT NULL"
                   + ", Sexo VARCHAR(18) NOT NULL"
                   + ", Rg VARCHAR(18) NOT NULL"
                   + ", Cep VARCHAR(18) NOT NULL"
                   + ", PRIMARY KEY (Identifica)"
                   + ", KEY Codigo (Identifica)" 
                   + ", CONSTRAINT IdentificaV FOREIGN KEY (Identifica) REFERENCES "
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
      Funcionario v = (Funcionario) o;
      String inst="Insert into Funcionario ";
        inst += "(Identifica, CartTrab, Cpf, Salario, Sexo, Rg, Cep) values(?, ?, ?, ?, ?, ?, ?)";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, v.getIdentifica());
              pS.setString(2, v.getCartTrab());
              pS.setString(3, v.getCpf());
              pS.setFloat(4, v.getSalario());
              pS.setString(5, v.getSexo());
              pS.setString(6, v.getRg());
              pS.setString(7, v.getCep());
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
      Funcionario v = (Funcionario) o;
      String inst = "Update Funcionario set CartTrab = ?, Cpf = ?, Salario = ?, Sexo = ?, Rg = ?, Cep = ?";
      inst += " where Identifica = ?";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setString(1, v.getCartTrab());
              pS.setString(2, v.getCpf());
              pS.setFloat(3, v.getSalario());  
              pS.setString(4, v.getSexo());
              pS.setString(5, v.getRg());
              pS.setString(6, v.getCep());
              pS.setInt(7, v.getIdentifica());
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
      Funcionario v = (Funcionario) o;
      String inst="Delete from Funcionario where Identifica = ?";
      try{
         Connection con = DaoConexaoAB.getInstancia().getCon();
         try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, v.getIdentifica());
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
        String inst="Select * from Funcionario where Identifica = ?";
        Funcionario f = null ;
        ResultSet rS;
        try {  
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, identifica);
                rS = pS.executeQuery();
                DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());     
                if (rS.next()) {
                   f =  new Funcionario();  
                   f.setIdentifica(rS.getInt("Identifica"));
                   f.setCartTrab(rS.getString("CartTrab"));
                   f.setCpf(rS.getString("Cpf"));
                   f.setSalario(rS.getFloat("Salario"));
                   f.setSexo(rS.getString("Sexo"));
                   f.setRg(rS.getString("Rg"));
                   f.setCep(rS.getString("Cep"));
                }
           }  
         } catch (SQLException e) {
             throw new RuntimeException(e.getMessage()); 
           }
       return(f);    
    }
    
    @Override
     public List<Object> carrega() {
       String inst  = "Select * from Funcionario";
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
