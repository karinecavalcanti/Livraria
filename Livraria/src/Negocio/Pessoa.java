/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author usuario
 */
public abstract class Pessoa {
   private int identifica;
   private String nome;
   private String endereco;
   private char definicao;
   private String celular;
   private String email;
  
   public int getIdentifica() {
       return identifica;
   }

   public void setIdentifica(int identifica) {
       this.identifica = identifica;
   }

   public String getNome() {
       return nome;
   }

   public void setNome(String nome) {
       this.nome = nome;
   }

   public String getEndereco() {
       return endereco;
   }

   public void setEndereco(String endereco) {
       this.endereco = endereco;
   }

   public char getDefinicao() {
       return definicao;
   }

   public void setDefinicao(char definicao) {
       this.definicao = definicao;
   }

	   public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
	       return email;
    }

   public void setEmail(String email) {
       this.email = email;
   }
   public String relatorio(){
		String resp = "";
		
		resp =   nome + " com a identificação " + identifica + '\n';
		resp += "No endereço " + endereco + " sendo " ;
		resp += (definicao == 'C' ? "Cliente" : definicao == 'O' ? "Fornecedor" : "Funcionario" )+ '\n';
		resp += "Com os telefones, o 1° " + celular + '\n';
		resp += "Já o email é " + email + '\n';
		
		return resp;
	} 
   
}
