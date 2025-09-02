/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;
import Negocio.ItemV;
import Negocio.Produto;
import Negocio.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class DaoItemV implements DaoBasico {
    
    public DaoItemV() {
    String inst = "CREATE TABLE IF NOT EXISTS ItemV"
                          + " (Numero INT NOT NULL"
                          + ", Item INT NOT NULL"
                          + ", Codigo INT NOT NULL"
                          + ", Quantidade INT NOT NULL"
                          + ", PRIMARY KEY (Numero, Item)"
                          + ", KEY Numero (Numero)"
                          + ", KEY Codigo (Codigo)" 
                          + ", CONSTRAINT NumeroIV FOREIGN KEY (Numero) REFERENCES Venda (Numero)"
                          + ", CONSTRAINT CodigoIV FOREIGN KEY (Codigo) REFERENCES Produto (Codigo)"
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
  public boolean incluir (Object o){
       boolean result = true;
       ItemV i = (ItemV) o;
       String inst = "Insert into ItemV (Numero, Item, Codigo, ";
             inst +="Quantidade) values (?, ?, ?, ?)";
       try {
           Connection con = DaoConexaoAB.getInstancia().getCon();
           try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, i.getVenda().getNumero());
                pS.setInt(2, i.getItem());
                pS.setInt(3,i.getProduto().getCodigo());
                pS.setInt(4, i.getQuantidade());
                pS.execute();
           }
           DaoConexaoAB.getInstancia().setCon(con);
       }
       catch (SQLException e){
             result = false;
             throw new RuntimeException(e.getMessage());
       }
       return (result);       
  }
  
  @Override
  public boolean alterar (Object o) {
       boolean result = true;
       ItemV i = (ItemV) o;
       String inst = "Update ItemV  set Codigo = ?, Quantidade =?";
             inst += " where Numero = ? And Item = ?";   
       try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)){
               pS.setInt(1, i.getProduto().getCodigo());
               pS.setInt(2, i.getQuantidade());
               pS.setInt(3, i.getVenda().getNumero());
               pS.setInt(4, i.getItem());
               pS.execute();  
          }
          DaoConexaoAB.getInstancia().setCon(con);
        }
        catch (SQLException e){
             result = false;
             throw new RuntimeException(e.getMessage());
        }
        return (result);       
    } 
    
    @Override
    public boolean excluir(Object o) {
        boolean result = true;
        ItemV i = (ItemV) o;
        String inst = "Delete from Item where Numero = ? And Item = ?"; 
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, i.getVenda().getNumero());
                pS.setInt(2, i.getItem());
                pS.execute();           
            }
            DaoConexaoAB.getInstancia().setCon(con);
        } catch (SQLException e){
            result = false;
            throw new RuntimeException(e.getMessage());
        }       
        return (result);
    }
    
    @Override
    public Object busca(int numero, int item) {
        String inst = "Select * from ItemV where Numero = ? And Item = ?";
        ItemV i = null;
        ResultSet rS;
        try{
             Connection con = DaoConexaoAB.getInstancia().getCon();
             try (PreparedStatement pS = con.prepareStatement(inst)) {
                   pS.setInt(1, numero);
                   pS.setInt(2, item);
                   rS = pS.executeQuery();
                   DaoConexaoAB.getInstancia().setCon(con);    
                   if (rS.next()) {
                      i = new ItemV(); 
                     // DaoVenda dV = new DaoVenda(); 
                     // Venda v = (Venda) dV.busca(rS.getInt("Numero"), 0); 
                      Venda v = new Venda();
                      v.setNumero(numero);
                      i.setVenda(v);
                      i.setItem(rS.getInt("Item"));
                      DaoProduto dP = new DaoProduto(); 
                      Produto p = (Produto) dP.busca(rS.getInt("Codigo"), 0);
                      i.setProduto(p);
                      i.setQuantidade(rS.getInt("Quantidade"));
                   }
             }   
         }
         catch (SQLException e) {
                 throw new RuntimeException(e.getMessage());            
         }
         return(i);    
    }
    
    @Override
    public List<Object> carrega() {
        String inst = "Select * from Numero, ItemV";
        ResultSet rS;
        List <Object> lista = new ArrayList<>();
        Object o;
        
        try {
            try (PreparedStatement pS =
                        DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
                 rS = pS.executeQuery(inst);
                 DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());
                 if (rS != null)
                    while (rS.next()){
                        o = busca(rS.getInt("Numero"), rS.getInt("Item"));
                        lista.add(o);
                    }
                pS.close();
            }             
        } catch(SQLException e){
                throw new RuntimeException(e.getMessage());
        }
        return lista;    
    }
    
    public List<Object> carregaItens(int numero) {
        String inst = "Select * from ItemV where Numero = ? order by Item";
        ResultSet rS;
        List <Object> lista = new ArrayList<>();
        Object o;
        
        
        try {
            try (PreparedStatement pS =
                        DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
                 pS.setInt(1, numero);
                 rS = pS.executeQuery();
                 DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());
                 if (rS != null) 
                     while (rS.next()) {
                        o = busca(rS.getInt("Numero"), rS.getInt("Item"));
                        lista.add(o);
                    }
                pS.close();
            }             
        } catch(SQLException e){
                throw new RuntimeException(e.getMessage());
        }
        return lista;    
    }
}
