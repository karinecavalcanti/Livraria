/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Util.Diversos;

/**
 *
 * @author aluno
 */
public class Funcionario extends Pessoa{
  private String cartTrab;
  private String cpf;
  private String sexo;
  private String rg;
  private String cep;
  
  private float salario;

  public String getCartTrab() {
      return cartTrab;
  }

  public void setCartTrab(String cartTrab) {
      this.cartTrab = cartTrab;
  }

  public String getCpf() {
      return cpf;
  }

  public void setCpf(String cpf) {
      this.cpf = cpf;
  }

  public float getSalario() {
     return salario;
  }

  public void setSalario(float salario){
     this.salario = salario;
  }

	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String relatorio() {
	  	String resp = super.relatorio();
	  	
	  	resp += "Sendo o cpf  de n° " + cpf + '\n';
	  	resp += "Com a carteira de trabalho de identificação " + cartTrab;
	  	resp += " e o salário de referência de " + Diversos.doisDigitos(1).format(salario);
	  	return resp;
	  }

}
