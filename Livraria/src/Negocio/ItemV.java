/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

/**
 *
 * @author aluno
 */
public class ItemV {
  private Venda venda;
  private int item;
  private Produto produto;
  private int quantidade;
 
  public Produto getProduto() {
      return produto;
  }

  public void setProduto(Produto produto) {
      this.produto = produto;
  }

  public Venda getVenda() {
      return venda;
  }

  public void setVenda(Venda venda) {
      this.venda = venda;
  }

  public int getItem() {
      return item;
  }

  public void setItem(int item) {
      this.item = item;
  }

  public int getQuantidade() {
      return quantidade;
  }

  public void setQuantidade(int Quantidade) {
      this.quantidade = Quantidade;
  }

  public float valorItem(){
        return quantidade * produto.getPrecoUnit();
  }
}
