/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.Cliente;
import Negocio.Fornecedor;
import Negocio.Pessoa;
import Negocio.Funcionario;
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
public class DaoPessoa implements DaoBasico {
    
    private DaoBasico dC, dFo, dF;
    public DaoPessoa() {
       String inst = "CREATE TABLE IF NOT EXISTS Pessoa"
                   + " (Identifica INT NOT NULL"
                   + ", Nome VARCHAR(45) NOT NULL"
                   + ", Endereco VARCHAR(25) NOT NULL"
                   + ", Definicao VARCHAR(15) NOT NULL"
                   + ", Email VARCHAR(20) NOT NULL"
                   + ", Celular VARCHAR(20) NOT NULL"
                   + ", PRIMARY KEY (Identifica)"
                   + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
       try {
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.execute();
                this.dC = new DaoCliente();
                this.dFo = new DaoFornecedor();
                this.dF = new DaoFuncionario();
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
      Pessoa p = (Pessoa) o;
      String inst = "Insert into Pessoa ";
             inst += "(Identifica, Nome, Endereco, Definicao, ";
             inst += "Email, Celular) ";
             inst += "values(?, ?, ?, ?, ?, ?)";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setInt(1, p.getIdentifica());
              pS.setString(2, p.getNome());
              pS.setString(3, p.getEndereco());
              pS.setString(4, p.getDefinicao() == 'C' ? "Cliente" : 
                      p.getDefinicao() == 'O' ? "Fornecedor" : "Funcionario");
              pS.setString(5, p.getEmail());
              pS.setString(6 , p.getCelular());  
              pS.execute();
              switch (p.getDefinicao()) {
                  case 'C' : dC.incluir(o);
                             break;
                  case 'F' : dF.incluir(o);
                             break;
                  case 'O' : dFo.incluir(o);
              }
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
      Pessoa p = (Pessoa) o;
      String inst = "Update Pessoa set Nome = ?, Endereco = ?, Definicao = ?,";
             inst += " Email = ?,  Celular = ? where Identifica = ?";
      try{
          Connection con = DaoConexaoAB.getInstancia().getCon();
          try (PreparedStatement pS = con.prepareStatement(inst)) {
              pS.setString(1, p.getNome());
              pS.setString(2, p.getEndereco());
              pS.setString(3, p.getDefinicao() == 'C' ? "Cliente" : 
                      p.getDefinicao() == 'O' ? "Fornecedor" : "Funcionario");
              pS.setString(4, p.getEmail());
              pS.setString(5, p.getCelular());
              pS.setInt(6, p.getIdentifica());
              Pessoa p_aux = (Pessoa) busca(p.getIdentifica(), 0);
              //busca na tabela 
              if(p.getDefinicao() == p_aux.getDefinicao())
                switch (p.getDefinicao()) {
                   case 'C' : dC.alterar(o);
                              break;
                   case 'F' : dF.alterar(o);
                              break;
                   case 'O' : dFo.alterar(o);
                }
              else {
                   switch (p_aux.getDefinicao()) {
                      case 'C' : dC.excluir(p_aux);
                                 break;
                      case 'F' : dF.excluir(p_aux);
                                 break;
                      case 'O' : dFo.excluir(p_aux);
                   }
                   switch (p.getDefinicao()) {
                      case 'C' : dC.incluir(o);
                                 break;
                      case 'F' : dF.incluir(o);
                                 break;
                      case 'O' : dFo.incluir(o);
                   }  
              }
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
      Pessoa p = (Pessoa) o;
      String inst="Delete from Pessoa where Identifica = ?";
      try{
         Connection con = DaoConexaoAB.getInstancia().getCon();
         try (PreparedStatement pS = con.prepareStatement(inst)) {
              switch (p.getDefinicao()) {
                  case 'C' : dC.excluir(o);
                             break;
                  case 'F' : dF.excluir(o);
                             break;
                  case 'O' : dFo.excluir(o);
              }
              pS.setInt(1, p.getIdentifica());
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
    public Object busca(int identifica, int nada) {
        String inst="Select * from Pessoa where Identifica = ?";
        Pessoa p = null ;
        ResultSet rS;
        try {  
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setInt(1, identifica);
                rS = pS.executeQuery();
                DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());     
                if (rS.next()) {
                	if(rS.getString("Definicao").equalsIgnoreCase("Cliente")) {
                        Cliente c = (Cliente) dC.busca(identifica, 0);
                         p = c;
                         p.setDefinicao('C');   
                   }
                   else  if(rS.getString("Definicao").equalsIgnoreCase("Funcionario")) { 
                                  Funcionario f = (Funcionario) dF.busca(identifica, 0);    
                                  p = f;
                                  p.setDefinicao('F');
                             }           
                            else {
                           	         Fornecedor o = (Fornecedor) dFo.busca(identifica, 0);
                                        p = o;
                                        p.setDefinicao('O');
                                     }  
                   p.setIdentifica(rS.getInt("Identifica"));
                   p.setNome(rS.getString("Nome"));
                   p.setEndereco(rS.getString("Endereco"));
                   p.setEmail(rS.getString("Email"));
                   p.setCelular(rS.getString("Celular"));
                }
           }  
         } catch (SQLException e) {
             throw new RuntimeException(e.getMessage()); 
           }
       return(p);    
    }
    
    @Override
     public List<Object> carrega() {
       String inst  = "Select * from Pessoa order by Nome";
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
    
    public Object buscaCpf(String cpf, int nada){
        String inst="Select * from Cliente where Cpf Like ?";
        Cliente c = null ;
        Pessoa p = null;
        ResultSet rS;
        try {  
            Connection con = DaoConexaoAB.getInstancia().getCon();
            try (PreparedStatement pS = con.prepareStatement(inst)) {
                pS.setString(1, cpf);
                rS = pS.executeQuery();
                DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());     
                if (rS.next()) {
                   c = new Cliente(); 
                   c.setIdentifica(rS.getInt("Identifica"));
                   c.setCpf(rS.getString("Cpf"));
                   p = (Pessoa) busca(c.getIdentifica(), 0);
                   //p.setNome();
                }
           }  
         } catch (SQLException e) {
             throw new RuntimeException(e.getMessage()); 
           }
       return(p);    
    }
}