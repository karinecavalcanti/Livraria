package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class DaoRotinas {
     
	public boolean RemoverTabela(String Banco)  { 
        boolean result = true;
		String inst = "DROP DATABASE IF EXISTS " + Banco;
  
        try {
        	  try (PreparedStatement pS
                     = DaoConexaoAB.getInstancia().getCon().prepareStatement(inst)) {
        		  pS.execute();
            }
        	DaoConexaoAB.setInstancia(null);//getInstancia().setCon(null);
        } catch (SQLException e) {
        	result = false;
            throw new RuntimeException(e.getMessage());
          }
        return (result); 
	}
	
    public boolean buscaCE(String numero, String chave, String tabela) {
          String inst="Select * from " + tabela + " where  " + chave + " = ?";
         ResultSet rS; 
            
         try {  
                Connection con = DaoConexaoAB.getInstancia().getCon();
                try (PreparedStatement pS = con.prepareStatement(inst)) {
                        pS.setInt(1, Integer.parseInt(numero));
                        rS = pS.executeQuery();
                       DaoConexaoAB.getInstancia().setCon(con);                
                }  
           } catch (SQLException e) {
                 throw new RuntimeException(e.getMessage()); 
              }
           return(rS != null ?  false : true);    
    }  
    	   
    public DefaultTableModel apresentaDados (ResultSet rS) { 
   	       DefaultTableModel modelo = new DefaultTableModel();
   	       String coluna;
   	        try{
   	            ResultSetMetaData rSMD = (ResultSetMetaData) rS.getMetaData();
   	            for (int i = 1; i <= rSMD.getColumnCount(); i++) {
    	              coluna = rSMD.getColumnLabel(i).toUpperCase();
    	              modelo.addColumn(coluna);
   	            }
   	            while(rS.next()) {
    	             Object[] objeto = new Object[rSMD.getColumnCount()];
    	             for (int i = 0; i < rSMD.getColumnCount(); i++)
    	                    objeto[i] = rS.getObject(i+1);
    	             modelo.addRow(objeto);
    	        }      
    	   }
    	   catch (SQLException e) {
    	            throw new RuntimeException(e.getMessage());
    	   }
           return(modelo);   
     }
    
  }

