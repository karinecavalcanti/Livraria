/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.ItemV;
import Negocio.Pessoa;
import Negocio.Venda;
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
public class DaoVenda implements DaoBasico{
 
   DaoItemV dIV; 
   public DaoVenda() {
       String inst = "CREATE TABLE IF NOT EXISTS Venda"
                          + " (Numero INT NOT NULL"
                          + ", Matricula INT NOT NULL"
                          + ", Inscricao INT NOT NULL"
                          + ", Data VARCHAR(10) NOT NULL"
                          + ", ValPago FLOAT NOT NULL"
                          + ", Pago TINYINT(1) NOT NULL"
                          + ", PRIMARY KEY (Numero)"
                          + ", KEY Matricula (Matricula)"
                          + ", KEY Inscricao (Inscricao)" 
                          + ", CONSTRAINT MatriculaV FOREIGN KEY (Matricula) REFERENCES Pessoa (Identifica)"
                          + ", CONSTRAINT InscricaoV FOREIGN KEY (Inscricao) REFERENCES Pessoa (Identifica)"
                          + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
          try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();
                dIV = new DaoItemV();
            }
            DaoConexaoAB.getInstancia().setCon(con);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Venda v = (Venda) o;
        String inst = "Insert into Venda";
            inst += "(Numero, Matricula, Inscricao, Data, ValPago, Pago)";
            inst += " values (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, v.getNumero());  
                pS.setInt(2, v.getCliente().getIdentifica());
                pS.setInt(3, v.getFuncionario().getIdentifica());
                pS.setString(4, v.getData());
                pS.setFloat(5, v.getValPago());
                pS.setBoolean(6, v.isPago());
                pS.execute();
               for (int ind = 0; ind < v.getTamanho(); ind++)
                   dIV.incluir(v.getItem(ind));               
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
    public boolean alterar(Object o) {
        boolean result = true;
        Venda v = (Venda) o;
        String inst = "Update Venda set Matricula = ?, Inscricao = ?, Data =?,";
               inst += " ValPago = ?, Pago = ? where Numero = ? ";   
         try{
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)){
                pS.setInt(1, v.getCliente().getIdentifica());
                pS.setInt(2, v.getFuncionario().getIdentifica());
                pS.setString(3, v.getData());
                pS.setFloat(4, v.getValPago());
                pS.setBoolean(5, v.isPago());
                pS.setInt(6, v.getNumero());
                pS.execute();
                for (int ind = 0; ind < v.getTamanho(); ind++)
                   dIV.alterar(v.getItem(ind));
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
        Venda v = (Venda) o;
        String inst = "Delete from Venda where Numero = ? "; 
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                   for (int ind = 0; ind < v.getTamanho(); ind++)
                       dIV.excluir(v.getItem(ind));    
                   pS.setInt(1, v.getNumero());
                   pS.execute();           
            }
            DaoConexaoAB.getInstancia().setCon(con);
        } 
        catch (SQLException e){
            result = false;
            throw new RuntimeException(e);
        }       
        return (result);
    }

    @Override
    public Object busca(int numero, int nada) {
        String inst = "Select * from Venda where numero = ?";
        Venda v = null;
        ResultSet rS;
        
        try {
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
               pS.setInt(1, numero);
               rS = pS.executeQuery();
               DaoConexaoAB.getInstancia().setCon(con);    
               if (rS.next()) {
                  v = new Venda(); 
                  v.setNumero(rS.getInt("Numero"));
                  DaoPessoa dP = new DaoPessoa(); 
                  Pessoa p = (Pessoa) dP.busca(rS.getInt("Matricula"), 0);
                  v.setCliente(p);
                  p = (Pessoa) dP.busca(rS.getInt("Inscricao"), 0);
                  v.setFuncionario(p);
                  v.setData(rS.getString("Data"));
                  v.setValPago(rS.getFloat("ValPago"));
                  v.setPago(rS.getBoolean("Pago"));
                  List<Object> listaI;
                  DaoItemV dI = new DaoItemV();
                  listaI = dI.carregaItens(numero);
                  for (Object o : listaI) {
                      ItemV i  = (ItemV) o;    
                      v.setItem(i);
                  }
               }
            }
            DaoConexaoAB.getInstancia().setCon(con);
        }   
        catch (SQLException e) {
                 throw new RuntimeException(e.getMessage());            
        }
        return(v);    
    }

    @Override
    public List<Object> carrega() {
        String inst = "Select * from Venda order by data desc";
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
                        o = busca(rS.getInt("Numero"), 1);
                        lista.add(o);
                    }
                pS.close();
            }             
        } catch(SQLException e){
                throw new RuntimeException(e.getMessage());
        }
        return lista;    
    }
    
    public Object ultimaVenda() {
      String inst  = "Select Max(Numero) as Ultima from Venda";
      ResultSet rS;
      Object o = null;
      
      try {
            try (PreparedStatement pS =
                        DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
                 rS = pS.executeQuery(inst);
                 DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());
                 if (rS.next()) 
                   o =  busca(rS.getInt("Ultima"), 0);      
                pS.close();
            }
      }     
      catch(SQLException e){
          throw new RuntimeException(e.getMessage());
      }
      return (o);  
   }
    
} 
 