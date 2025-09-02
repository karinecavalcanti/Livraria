/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;


import Negocio.Pessoa;
import Negocio.Produto;
import Negocio.Venda;
import Persistencia.DaoBasico;
import Persistencia.DaoConsulta;
import Persistencia.DaoItemV;
import Persistencia.DaoPessoa;
import Persistencia.DaoProduto;
import Persistencia.DaoRotinas;
import Persistencia.DaoVenda;
import Persistencia.DaoFuncionario;

import java.util.List;

/**
 *
 * @author usuario
 */
public class ControleGeral implements ControleBasico{
    private DaoBasico dG;
    private final String Banco = "BdSisVenda";  
    public ControleGeral(int op)  {
    	this.dG = (op == 1) ?  new DaoPessoa()  :	
	            (op == 2) ?  new DaoProduto() :    	
	          	(op == 3) ?  new DaoVenda() : null;            
    		          
    }
  
	@Override
    public boolean setManipular(Object o, char tarefa)  {
        boolean oK = false; 
        if (dG instanceof DaoBasico) 
           switch(tarefa)  {
                        case 'A': oK = (dG.alterar(o));
                                        break;
                        case 'E': oK = (dG.excluir(o));
                                        break;
                        case 'I': oK = (dG.incluir(o));
           }
        return(oK);
    }
    
    @Override
    public Object getBusca(int chave1, int chave2)  {
        Object o = null;
        if (dG instanceof DaoBasico)
           o = dG.busca(chave1, chave2);
        return (o); 
    }
    
    @Override
    public List<Object> lista()  {
        List<Object> lista;
        lista = null;
        if (dG instanceof DaoBasico) 
            lista = dG.carrega();            
        return lista;
    }
    
    public boolean rotinas(int op , String[] parametro) {
    	boolean oK = false;
    	DaoRotinas dR = new DaoRotinas();
    	 oK = (op == 1) ? dR.buscaCE(parametro[0],  parametro[1], parametro[2]) : 
    		       (op == 0) ? dR.RemoverTabela(Banco) :  false;
    	return oK ;
    }
    
    public String relatorio(int op) {
    	String resp = "";
    	 int contaR = 0;
    	 if (dG instanceof DaoBasico) 
    	     for (Object o : dG.carrega()) {
    	            switch (op) {
    	                 case 1   : Pessoa pe = (Pessoa) o;
    	                                     if (!pe.getNome().equalsIgnoreCase("NÃO CADASTRADO"))
    	                                         resp += pe.relatorio();
    	                                     else
    	                                    	 resp += "Não";
    	                                    break;
    	                 case 2  : Produto p = (Produto) o;
    	                                   resp += p.relatorio();
    	                                   break;
    	                 case 3 : Venda v = (Venda) o;
    	                                  resp += v.relatorio(); 
    	                                                           
    	            }     
    	            if (contaR++ < dG.carrega().size() - 1) 
    	            	if (!resp.substring(resp.length() - 3 , resp.length()).equalsIgnoreCase("Não"))
    	    	       	resp +=  "\n__________________________________________________________________\n\n";
    	        }  
    		// resp = dG.relatorio();
    	 return resp;
    } 
    
    public Object listaConsulta(int iD, String texto) {
    	    DaoConsulta dC = new DaoConsulta(); 
            return(dC.retornaLista(iD, texto)); // ,c, relatorio));
    } 
   
    public Object buscaUltima() {
    	DaoVenda dV = new DaoVenda();
    	return (dV.ultimaVenda());
    }
    
    public Object buscaCpf(String cpf, int nada) {
    	DaoPessoa dPe = new DaoPessoa();
    	return (dPe.buscaCpf(cpf, 0));
    }
}
