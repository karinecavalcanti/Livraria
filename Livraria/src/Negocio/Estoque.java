/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

/**
 *
 * @author aluno
 */
public class Estoque extends Produto {
   private int Quantidade;
   private int QuantMin;

   public int getQuantMin() {
       return QuantMin;
   }

   public void setQuantMin(int QuantMin) {
       this.QuantMin = QuantMin;
   }

   public int getQuantidade() {
       return Quantidade;
   }

   public void setQuantidade(int Quantidade) {
       this.Quantidade = Quantidade;
   }
   public String relatorio() {
	   String resp = super.relatorio();
	   
	   resp += "\nCom a quantidade de " + Quantidade + " e a quantidade mínima de " ;
	   resp += QuantMin;
	   return resp;
   }	  

}
