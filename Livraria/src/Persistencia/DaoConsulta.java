/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aluno
 */
public class DaoConsulta {
   
   public DefaultTableModel apresentaDados (ResultSet rS) { 
       DefaultTableModel modelo = new DefaultTableModel();
       String coluna;
       try{
            ResultSetMetaData rSMD = rS.getMetaData();
            for (int i=1; i<=rSMD.getColumnCount(); i++) {
              coluna = rSMD.getColumnLabel(i).toUpperCase();
              modelo.addColumn(coluna);
           }
           while(rS.next()) {
               Object[] objeto = new Object[rSMD.getColumnCount()];
               for (int i=0; i<rSMD.getColumnCount();i++)
                     objeto[i] = rS.getObject(i+1);
               modelo.addRow(objeto);
          }      
       }
       catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
    }
    return(modelo);   
  }
  
   public DefaultTableModel retornaItems (int numero) {
        String  inst = "Select Item, Item.Codigo as Código, Descricao as Produto, ";
                     inst += "Quantidade, PrecoU as [Valor Unitário], ";
                     inst += "(Quantidade * PrecoU) as [Valor Item] ";
                     inst += "From Item, Produto Where Item.Codigo = Produto.Codigo ";
                     inst += " And Item.Numero =  ? Order By Item";
       
         ResultSet rS;  
         DefaultTableModel modelo = null; 
         try {
           try (PreparedStatement pS = 
                    DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
                 pS.setInt(1, numero);
                 rS = pS.executeQuery();
                 modelo = rS != null ? apresentaDados(rS): null;
                DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());              
           } 
       }
       catch(SQLException e) {
           throw new RuntimeException(e.getMessage());
    }
    return(modelo);
 }    
  
  public DefaultTableModel retornaLista(int op, String texto) {
        //  boolean c, String relatorio) {
    String inst = "";
    ResultSet rS;
   
    DefaultTableModel modelo = null; 
    switch (op) {
        case 0 : inst  = "Select Matricula, Codigo as Código , Nome as Funcionário,";
                 inst += " Descricao as Projeto,  replace(cast(Salario as"; 
                 inst += " DECIMAL(7,2)),'.',',') as\"Salário Base\"" ;
                 inst += ", replace(cast(SalarioR as"; 
                 inst += " DECIMAL(7,2)),'.',',') as\"Salário Real\"";
                 inst += " from Tabela order by Nome" ;
                 break;
        case 1 : inst  = "Select a.Matricula, f.Nome, p.Codigo as Código, ";
                       // inst += "case when Financiado then i.Duracao else n.Tipo end, ";
                 inst += "p.Descricao as Descrição, a.Financiado from Aloca a ";
                 inst += "inner join Funcionario f on a.Matricula = f.Matricula ";
                 inst += "inner join Projeto p on a.Codigo = p.Codigo ";
                 inst += "where exists (Select * from Financiado i ";
                 inst += "where a.Matricula = i.Matricula And ";
                 inst += "a.Codigo = i.Codigo) Or exists(Select * from  ";
                 inst += "NFinanciado n inner join Aloca a on a.Matricula = n.Matricula And ";
                 inst += "a.Codigo = n.Codigo) order by f.Nome";
                 break;
        case 2 : inst  = "Select *, replace(cast(Salario as "; 
                 inst += "DECIMAL(7,2)),'.',',') as Salário from Funcionario f ";
                 inst += "where not exists (Select * from Aloca a, Projeto p ";
                 inst += "where a.Matricula = f.Matricula and a.Codigo = p.Codigo)"; 
                 break;
        case 3 : inst  = "Select Nome, Matricula, Descricao as Cargo, ";
                 inst += "replace(cast(Salario as DECIMAL(7,2)),'.',',') as Salário, ";
                 inst += "Ativo from Funcionario f inner join Cargo c ";              
                 inst += "on f.Numero = c.Numero order by nome";
                 break;
        case 4 : inst  = "Select * from Projeto order by descricao";
                 break;
        case 5 : inst  = "Select p.Descricao as Projeto, count(f.Matricula) as ";
                 inst += "Total from Aloca a ";
                 inst += "inner join Funcionario f on a.Matricula = f.Matricula ";
                 inst += "inner join Projeto p on a.Codigo = p.Codigo ";
                 inst += "group by p.Descricao order by count(f.Matricula) desc";
                 break;
        case 6 : inst  = "Select Nome, Matricula, Descricao as Cargo, ";
                 inst += "replace(cast(Salario as DECIMAL(7,2)),'.',',') as Salário, ";
                 inst += "Ativo from Funcionario f ";
                 inst += "inner join Cargo c on f.Numero = c.Numero "; 
                 inst += "where nome Like ? order by Nome";                   
    }
    
   // System.out.println(inst);
    try {
          try (PreparedStatement pS = 
                    DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
              if (op == 6)
                        pS.setString(1, '%' + texto + '%');
              rS = pS.executeQuery();
              modelo = rS != null ? apresentaDados(rS): null;
              DaoConexaoAB.getInstancia().setCon(DaoConexaoAB.getInstancia().getCon());              
          }        
    }
    catch(SQLException e) {
           throw new RuntimeException(e.getMessage());
    }
    return(modelo);
 }  
} 

