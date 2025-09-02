/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;


import Negocio.Produto;

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
public class DaoProduto implements DaoBasico {
    
    private DaoBasico dE, dL;              
    public DaoProduto() {
    String inst = "CREATE TABLE IF NOT EXISTS Produto"
                          + " (Codigo INT NOT NULL"
                          + ", Descricao VARCHAR(25) NOT NULL"
                          + ", Grupo VARCHAR(15) NOT NULL"
                          + ", PrecoU FLOAT NOT NULL"
                          + ", Editora VARCHAR (20) NOT NULL"
                          + ", PRIMARY KEY (Codigo)"
                          + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
    try {
        Connection con = DaoConexaoAB.getInstancia().getCon();
        try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();
                this.dE = new DaoEstoque();
        }
        DaoConexaoAB.getInstancia().setCon(con);
    } 
    catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
    } 
  }
 
  @Override
  public boolean incluir(Object o) {
      boolean result = true;  
      Produto p = (Produto) o;
      String inst="Insert into Produto";
             inst += "(Codigo, Descricao, grupo, PrecoU, Editora)";
             inst += "values(?, ?, ?, ?, ?)";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, p.getCodigo());
              pS.setString(2, p.getDescricao());
              pS.setString(3, p.getGrupo());
              pS.setFloat(4, p.getPrecoUnit());
              pS.setString(5, p.getEditora());
              pS.execute();
              dE.incluir(p);
          }
         DaoConexaoAB.getInstancia().setCon(con);
      } catch (SQLException e) {
           result = false;  
           throw new RuntimeException(e);
      }
      return(result);
  }
    
  @Override
  public boolean alterar(Object o) {
      boolean result = true;  
      Produto p = (Produto) o;
      String inst="Update Produto set Descricao = ?, Grupo = ?, PrecoU = ?, Editora = ?"; 
      inst += " where Codigo = ?";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setString(1, p.getDescricao());
              pS.setString(2, p.getGrupo());
              pS.setFloat(3, p.getPrecoUnit());
              pS.setString(4, p.getEditora());
              pS.setInt(5, p.getCodigo());
              pS.execute();
              dE.alterar(p);
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
      Produto p = (Produto) o;
      String inst="Delete from Produto where Codigo = ?";
      try{
         Connection con = DaoConexaoAB.getInstancia().getCon();
         try (PreparedStatement pS = con.prepareStatement(inst)) {
              dE.excluir(p);
              pS.setInt(1, p.getCodigo());
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
  public Object busca(int codigo, int nada) {
        String inst="Select * from Produto where Codigo = ?";
        Produto p = null;
        ResultSet rS;
        
        try{
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
               pS.setInt(1, codigo);
               rS = pS.executeQuery();
               DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());
               if (rS.next()) {
                   Object o = dE.busca(codigo, nada);
                   p = (Produto) o;
                   p.setCodigo(rS.getInt("Codigo"));
                   p.setDescricao(rS.getString("Descricao"));
                   p.setGrupo(rS.getString("Grupo"));
                   p.setPrecoUnit(rS.getFloat("PrecoU"));
                   p.setEditora(rS.getString("Editora"));
               }
            }  
        } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());            
         }
       return(p);    
  }
    
  @Override
  public List<Object>carrega() {
       String inst="Select * from Produto order by Descricao";
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
       } catch(SQLException e) {
           throw new RuntimeException(e.getMessage());
           
       }
       return(lista); 
  }
  
}
