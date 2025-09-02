/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import java.util.ArrayList;
import java.util.List;

import Util.Diversos;

/**
 *
 * @author aluno
 */
public class Venda {
   private int numero;
   private Pessoa cliente;
   private Pessoa funcionario;
   private String data;
   private float valPago;
   private boolean pago;
   private final List<ItemV>item;

  public Venda() {
      item = new ArrayList<>();
  }
  
  public int getNumero() {
        return numero;
  }

   public void setNumero(int numero) {
        this.numero = numero;
   }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Pessoa getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Pessoa funcionario) {
        this.funcionario = funcionario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public ItemV getItem(int posi) {
        return item.get(posi);
    }

    public void setItem(ItemV item) {
        this.item.add(item);
    }
   
    public int getTamanho() {
        return item.size();
    } 
    public float getValPago() {
		return valPago;
	}

	public void setValPago(float valPago) {
		this.valPago = valPago;
	}

	public float total() {
        float tot = 0;
        
       for (ItemV it : item) {
           tot += it.valorItem();
       }        
        return tot;
    }
    public String relatorio() {
    	String  nF = "";
    	final int col = 28;
    	int nIt = 0; 
    	Cliente c = (Cliente) cliente;
    	nF += "                                                           Cupom Fiscal\n";
    	nF += " Sistema de vendas                                                          Cnpj  99.999.999/0001-99\n";
    	nF += "                                                                                         IE 12.345.67\n";
    	nF +=  " Data " +  data + "                      Cpf  do cliente " + c.getCpf()  + "                   ";
    	nF += "Número da venda " + numero +'\n' ;
    	nF += "   Item         Código                   Produto                                 Qtda     Valor Unitário     ";
    	nF  +="Valor total\n";
    	  nF += "\n__________________________________________________________________";
          nF += "_____________________________________\n";
    	for (int ind = 0, tam = 0; ind < getTamanho(); ind++) {  		  
    		   nF +=  "    "  + Diversos.senDigitos().format(getItem(ind).getItem());
    		   tam = String.valueOf(getItem(ind).getProduto().getCodigo()).length();
    		   nF +=  (tam > 3 ? "           "  : "             " );  
    	       nF += getItem(ind).getProduto().getCodigo();
               nF += "               "; 
               tam = getItem(ind).getProduto().getDescricao().length(); 
               nF += getItem(ind).getProduto().getDescricao() ;
              // if (tam > 10)
               nF += "\n          	   	                                                 ";
              // else
              //	   for(int p = 0; p <  col - tam - 2 ; p++) 
    		  //	         nF += "  " ; 
               nIt +=  getItem(ind).getQuantidade();
    		  nF += Diversos.senDigitos().format(getItem(ind).getQuantidade()) + "         " ;  
    		   if  (getItem(ind).getProduto().getPrecoUnit() < 10) 
    		         nF += "R$ 0" +
    		         Diversos.doisDigitos(0).format(getItem(ind).getProduto().getPrecoUnit());
    		   else	   
    		          nF +=  Diversos.doisDigitos(1).format(getItem(ind).getProduto().getPrecoUnit());
    		   nF += "          " ;
    		   if (getItem(ind).valorItem() < 10)
    			   nF += "R$   " + Diversos.doisDigitos(0).format(getItem(ind).valorItem()) + '\n';
    		   else
    			   nF +=  Diversos.doisDigitos(1).format(getItem(ind).valorItem()) + '\n';    		 
    	 }	   
         nF += "__________________________________________________________________";
         nF += "_____________________________________\n\n";
         nF +=  "                                                                                           Quant. de Itens              ";
     	nF  +=  "           "  + Diversos.senDigitos().format(nIt) + '\n';
    	nF += "                                                                                           Valor total de itens          " ; 
    	nF +=  Diversos.doisDigitos(1).format(total()) + '\n';
    	nF += "                                                                                           Valor pago                      ";                      
       nF += 	Diversos.doisDigitos(1).format(valPago) + '\n';
       nF +=  "                                                                                           Valor recebido               " ;
       float val = valPago - total();    	   
       if  (val < 10)  
	         nF += "R$  0" + Diversos.doisDigitos(0).format(val);
	   else	   
	        nF +=  Diversos.doisDigitos(1).format(val);
       nF += '\n';
       nF +=  "                         Valor pago em cartão de" + (pago ? " débito ": 
    	   " crédito ou dinheiro"); 
    	return nF;
    } // int tam = "                      
}
