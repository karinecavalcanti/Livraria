/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Util.Diversos;

/**
 *
 * @author 
 */
public class Produto {
   private int codigo;
   private String descricao;
   private String editora;
   private String grupo;
   private float precoUnit;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public float getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(float precoUnit) {
        this.precoUnit = precoUnit;
    }

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String relatorio() {
		   String resp = "";
		   
		   resp += "O produto " + descricao + " com o código " + codigo + '\n';
		   resp += "Pertencente ao grupo " + grupo + " com o preço unitário de ";
		   resp +=  Diversos.doisDigitos(1).format(precoUnit) + '\n';
		   return resp;
	   }
  
}
