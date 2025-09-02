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
public class Fornecedor extends Pessoa{
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String relatorio() {
    	String resp = super.relatorio();
    	
    	resp += "Sendo o  Cnpj  n° " + cnpj;
    	return resp;
    }
                
}
