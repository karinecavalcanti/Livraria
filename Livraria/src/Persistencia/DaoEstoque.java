/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;


import Negocio.Estoque;
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
public class DaoEstoque implements DaoBasico {
    
    public DaoEstoque() {
       String inst = "CREATE TABLE IF NOT EXISTS Estoque"
                   + " (Codigo INT NOT NULL"
                   + ", Quantidade INT NOT NULL"
                   + ", QuantMin INT NOT NULL"
                   + ", PRIMARY KEY (Codigo)"
                   + ", KEY Codigo (Codigo)" 
                   + ", CONSTRAINT CodigoE FOREIGN KEY (Codigo) REFERENCES "
                   + "Produto (Codigo)"
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
      Estoque e = (Estoque) o;
      String inst="Insert into Estoque";
        inst += "(Codigo, Quantidade, QuantMin) values(?, ?, ?)";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, e.getCodigo());
              pS.setInt(2, e.getQuantidade());
              pS.setInt(3, e.getQuantMin());
              pS.execute();
          }
         DaoConexaoAB.getInstancia().setCon(con);
      } 
      catch (SQLException eX) {
         result = false; 
         throw new RuntimeException(eX.getMessage());
      }
      return(result);
    }
    
    @Override
    public boolean alterar(Object o){
      boolean result = true;  
      Estoque e = (Estoque) o;
      String inst = "Update Estoque set Quantidade = ?, QuantMin = ?";
      inst += " where Codigo = ?";
      
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, e.getQuantidade());
              pS.setInt(2, e.getQuantMin());
              pS.setInt(3, e.getCodigo());
              pS.execute();
          }
         DaoConexaoAB.getInstancia().setCon(con);
      } catch (SQLException eX) {
         result = false;  
         throw new RuntimeException(eX.getMessage());
      }
      return(result);
    }
    @Override
    public boolean excluir(Object o){
      boolean result = true;  
      Estoque e = (Estoque) o;
      String inst="Delete from Estoque where Codigo = ?";
      try{
         Connection con = DaoConexaoAB.getInstancia().getCon();
         try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, e.getCodigo());
              pS.execute();
         }
         DaoConexaoAB.getInstancia().setCon(con);
      } catch (SQLException eX) {
          result = false;
          throw new RuntimeException(eX.getMessage());
      }
      return(result);
    } 

    @Override
    public Object busca(int Codigo, int nada){
        String inst="Select * from Estoque where Codigo = ?";
        Estoque e = null ;
        ResultSet rS;
        try {  
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, Codigo);
                rS = pS.executeQuery();
                DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());     
                if (rS.next()) {
                   e = new Estoque();  
                   e.setQuantidade(rS.getInt("Quantidade"));
                   e.setQuantMin(rS.getInt("QuantMin"));
               }
           }  
         } catch (SQLException eX) {
             throw new RuntimeException(eX.getMessage()); 
           }
       return(e);    
    }
    
    @Override
     public List<Object> carrega(){
       String inst="select * from Estoque order by QuantMin desc";
       List<Object> lista = new ArrayList<>();
       ResultSet rS; 
       Object o;
      
       try{
           try (PreparedStatement pS = 
                    DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
              rS = pS.executeQuery(inst);
              DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());
              if (rS != null)
                while (rS.next()){
                  o = busca(rS.getInt("Codigo"), 0);
                  lista.add(o);
                }
              pS.close();
           }
       } catch(SQLException eX) {
           throw new RuntimeException(eX.getMessage()); 
       }
       return(lista); 
     }   
      
}
