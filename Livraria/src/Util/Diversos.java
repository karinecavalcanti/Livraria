
package Util;

import java.awt.FontMetrics;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;


public class Diversos {
   
  public static String leDados(String mensagem, String titulo) {
      String dado="";
      do
         dado=JOptionPane.showInputDialog(null,mensagem,titulo,JOptionPane.QUESTION_MESSAGE);
      while(dado==null||dado.isEmpty()); 
      dado=dado.replace(',','.');
      return(dado);
  }
  
  public static void mostrarDados(String resposta, String titulo, boolean icone) {
     if (icone)
         JOptionPane.showMessageDialog(null,resposta,titulo,JOptionPane.INFORMATION_MESSAGE);
     else
        JOptionPane.showMessageDialog(null, resposta,titulo,JOptionPane.ERROR_MESSAGE);			
  }
  
  public static boolean testaNum(String dado,String titulo) {
       boolean teste;	
       try {
             teste=true;
             Double.parseDouble(dado); 
       }
       catch (NumberFormatException erro) {
           mostrarDados("Houve erro na conversão\n" +
           "Digite apenas caracteres numéricos " + erro.getMessage(), titulo, false);
            teste = false;
       }//Fim catch
      return(teste);
  }//Fim do testa num

  public static boolean confirmar (String mensagem, String titulo) {
      return(JOptionPane.showConfirmDialog(null,mensagem, titulo, 
                                               JOptionPane.YES_NO_OPTION) == 0 );
  }
 
  public static boolean intervalo(double n, double n1, double n2, String titulo) {
      if (n<=0) {
         mostrarDados("Favor digitar valores superiores a zero", titulo, false);
          return(false);
      } else if (n1 != n2 && (n < n1 || n > n2)) {
                     mostrarDados("Valores fora do intervalo de " + n1 + " e " + n2, titulo, false);
                      return(false);
                 } else 
                     return(true);
  }
  
  public static String carNumerico(String texto, char car) {
        if ((car >= '0' && car <= '9') || car == ',' || car == 8) {
             if (car != 8)
                 texto += String.valueOf(car);
             else if (!texto.isEmpty())
                         texto = texto.substring(0,texto.length()-1);
        }
        return (texto);
  }
  
  public static String preparaDados(String data, int num) {
	  char car ;
	  String dado = "";
		for(int posi = 0; posi < data.length() ; posi++) {
				car = data.charAt(posi);
			    if ((car >= '0' && car <= '9') && (posi < num)) 
		           dado += car;
		}
		if(data.length() < num)
		     data = dado;
		else
		 data = data.substring(0, dado.length());
		return (data);	 
  }
  
  public static boolean testaData(String data, String titulo) {
	  boolean teste = true;
	  String resp = "", Smes[] = 
		  {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", 
			  "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	  int dia = Integer.parseInt(data.substring(0, 2));
	  int mes = Integer.parseInt(data.substring(2, 4));
	  int ano = Integer.parseInt(data.substring(4, data.length()));
	  if (dia < 1) {
		  teste = false;
		  resp = "Dia inválido"; 
	  }	
	  else if (ano < 1)  {
		           teste = false;
		           resp = "Ano inválido"; 
	          } 
	          else {
	                   if (mes > 0  && mes <= 12)
	                       resp =  "Mês de " + Smes[mes - 1]  + '\n';
	                   switch (mes) {
	                         case 2 : if (ano % 4 == 0 ) {
	                        	               if ( dia > 29) { /*
	                                                                (((year % 4 == 0) && 
                                                                    !(year % 100 == 0))
                                                                  || (year % 400 == 0))
	                                                */    	               
	  	                                            teste = false;
	   	                                            resp += "Ano bissesto, " + 
	  	                                                          ano + '\n' ;
	                        	               }    
	                                       }    
	                                       else if (dia > 28) {
	                                                  teste = false;
	                                                  resp += "Ano não bissesto, " + ano + '\n' ;
	                                              } 
	                                              break;
	                        case 4 : 
	    	                      case 6 :
	    	                            case 9 :
	    	                                  case 11 : if (dia > 30) {
	    	                                                      teste = false;
	    	                                                      resp += "Ano " +  ano + '\n' ;
	    	                                                 }
	    	                                                 break; 
	                        case 1 :
	    	                      case 3 : 
	    	                            case 5 :
	    	                                  case 7 :
	    	                                        case 8 :
	    	                                              case 10 :
	    	                                                    case 12 : if (dia > 31) {
	    	                                                                       teste = false;
	    	             	    	                        	                   resp += "Ano " + 
	    	             	    	                                               ano + '\n' ;
	                             	                                           }   
	    	                    	                                           break;
	    	                default : teste = false;
	    		                          resp += "Mês inválido\n";
	                   } 		    	  	       
	                   if (mes > 0 && mes < 13) 
		                   resp += "Dia " + dia + ", inválido";    
	  }	
	  if (!teste)
		     mostrarDados(resp, titulo, false);   
	  return teste;	  
  }
  
  public static boolean checaCpf (String cpf) {
	  if (cpf.isEmpty() || cpf.length() != 11 || cpf == "00000000000" || 
		  cpf == "11111111111" || cpf == "22222222222" || 
		  cpf == "33333333333" || cpf == "44444444444" ||
	      cpf == "55555555555" || cpf == "66666666666" || 
	      cpf == "77777777777" || cpf == "88888888888" || 
	      cpf == "99999999999" || cpf == "01234567890")
	            return false;
	  else {
	          int num , soma = 0 , resto;
	          for (int i = 0; i < 9; i++) { 
	        	    num  = Character.getNumericValue(cpf.charAt(i));
	        	    soma += num * (10 - i); 
	          } 
	          resto = 11 - (soma % 11);
	          if (resto == 10 || resto == 11)
	           	         resto = 0;   
	          num = Character.getNumericValue(cpf.charAt(9));
	          System.out.println("1° digito verificador " + resto + " " + num);
	          if (resto != num) 
	           	  return false;
	          else {
	                  soma = 0;
	                  for (int i = 0; i < 10; i++) {
	        	           num  = Character.getNumericValue(cpf.charAt(i));
	           	          soma += num * (11 - i); 
	           	      } 	  
	                  resto = 11 - (soma % 11);
	                  if (resto == 10 || resto == 11)
	           	         resto = 0;
	                 num = Character.getNumericValue(cpf.charAt(10));
	                 System.out.println("2° digito verificador " + resto + " " + num);
	                 if (resto != num) 
	        	         return false;
	          }
	  }        
	  return true;     
  }
	 
  public static boolean checaCnpj (String cnpj) {
	    // Elimina CNPJs invalidos conhecidos
	    if (cnpj.isEmpty() || cnpj.length() != 14 ||
	    	cnpj == "00000000000000" || cnpj == "11111111111111" || 
	        cnpj == "22222222222222" || cnpj == "33333333333333" || 
	        cnpj == "44444444444444" || cnpj == "55555555555555" || 
	        cnpj == "66666666666666" || cnpj == "77777777777777" || 
	        cnpj == "88888888888888" || cnpj == "99999999999999" ||
	        cnpj == "01234567890000") 
	    		    	return false;
	    else {    
	             int num ,posi ,resto, soma, tam;
	             String subCnpj, digitos; 
	             tam = cnpj.length() - 2;
	             subCnpj = cnpj.substring(0,tam);
	             digitos = cnpj.substring(tam);
	             soma = 0;
	             posi = tam - 7;
	             for (int i = tam; i >= 1; i--) {
	            	 num  = Character.getNumericValue(subCnpj.charAt(tam - i));
	                 soma += num * posi--;
	                 if (posi < 2)
	                     posi = 9;
	              }
	             resto = soma % 11 < 2 ? 0 : 11 - soma % 11;
	             num = Character.getNumericValue(digitos.charAt(0));
		         System.out.println("1° digito verificador " + resto + " " + num);
	             if (resto != num)
	                return false;
	             else {
	                    tam++; 
	                    subCnpj = cnpj.substring(0,tam);
	                    soma = 0;
	                    posi = tam - 7;
	                    for (int i = tam; i >= 1; i--) {
	                    	num  = Character.getNumericValue(subCnpj.charAt(tam - i));
	                    	soma += num * posi--;
	                        if (posi < 2)
	                            posi = 9;
	                    }
	                    resto = soma % 11 < 2 ? 0 : 11 - soma % 11;
	                    num = Character.getNumericValue(digitos.charAt(1));
		                 System.out.println("2° digito verificador " + resto + " " + num);
	                    if (resto != num)
	              	          return false;
	             }
	    }      
	    return true;
  }
	  	 
  public static DefaultFormatterFactory FormatoMascara(String titulo, int op) {
      MaskFormatter mascara = null;
     
      try {
          switch(op) {
              case 0 : mascara = new MaskFormatter("##/##/####");
                            break;
              case 1 : mascara = new MaskFormatter("##:##");
                            break;
              case 2 : mascara = new MaskFormatter("(##) - #### - ####"); 
                            break;
              case 3 : mascara = new MaskFormatter("(##) - #### - #####"); 
                            break;
              case 4 : mascara = new MaskFormatter("###.###.### - ##");
                            break;
              case 5 : mascara = new MaskFormatter("##.###.###/#### - ##");             
          }
         // mascara.setPlaceholderCharacter('0');
       // if (op < 0)
        	//mascara.setValidCharacters ("0123456789");
      }
      catch (ParseException e) {
          mostrarDados("Erro na formatação " + e.getMessage(), titulo, 
          		                                                                                  false);
      }
      DefaultFormatterFactory formato = 
      		new DefaultFormatterFactory(mascara, mascara);
      return (formato);
 }

  public static NumberFormat doisDigitos(int tipo) {
      NumberFormat doisDigitos = null;
      doisDigitos = (tipo == 0) ? NumberFormat.getNumberInstance()  : //número
                            (tipo == 1) ? NumberFormat.getCurrencyInstance() : //valor
                            (tipo == 2) ? NumberFormat.getPercentInstance() : /*percentual*/ null;
       doisDigitos.setMinimumFractionDigits(2);
       doisDigitos.setMaximumFractionDigits(2);
       return doisDigitos;  
  } 
  
  public static DecimalFormat senDigitos()  {
	  DecimalFormat sem =  new DecimalFormat("#00");
	  return sem;
  }
  
  public static String converterValor(String numero) {
      numero = numero.replace(".", "");
      numero = numero.replace(',', '.');
      numero = numero.replace("R$", "");
      numero = numero.replace("%", "");
      return numero;
   }  

   
 /*  public static void centralizarTitulo(JFrmCargo frameC , int opFrame, String titulo) {
	      FontMetrics fMC = frameC.getFontMetrics(frameC.getFont());
          int sCC = (frameC.getWidth() / 2 - fMC.stringWidth(titulo.trim()) / 2) / 
         fMC.stringWidth(" ");
         frameC.setTitle(String.format("%" + (sCC + 20 ) + "s", "") + titulo.trim()); */
	   /*   switch (opFrame) {
	        case 1 :  //JFrmFuncionario frameF = (JFrmFuncionario) o;
	                  FontMetrics fM = frameF.getFontMetrics(frameF.getFont());
                      int sC = (frameF.getWidth() / 2 - fM.stringWidth(titulo.trim()) / 2) / 
                                fM.stringWidth(" ");
                      frameF.setTitle(String.format("%" + (sC + 50 ) + "s", "") + titulo.trim());
	                  break;
            case 2 :  //JFrmCargo frameC = (JFrmCargo) o;
                      FontMetrics fMC = frameC.getFontMetrics(frameC.getFont());
                      int sCC = (frameC.getWidth() / 2 - fMC.stringWidth(titulo.trim()) / 2) / 
                      fMC.stringWidth(" ");
                      frameC.setTitle(String.format("%" + (sCC + 20 ) + "s", "") + titulo.trim());
                      break;
            case 3 :  JFrmProjeto frameP = (JFrmProjeto) o;
                      FontMetrics fMP = frameP.getFontMetrics(frameP.getFont());
                      int sCP = (frameP.getWidth() / 2 - fMP.stringWidth(titulo.trim()) / 2) / 
                                 fMP.stringWidth(" ");
                      frameP.setTitle(String.format("%" + (sCP + 20 ) + "s", "") + titulo.trim());
                      break;
            case 4 :  JFrmAloca frameA = (JFrmAloca) o;
                      FontMetrics fMA = frameA.getFontMetrics(frameA.getFont());
                      int sCA = (frameA.getWidth() / 2 - fMA.stringWidth(titulo.trim()) / 2) / 
                                 fMA.stringWidth(" ");
                      frameA.setTitle(String.format("%" + (sCA) + "s", "") + titulo.trim());
	     } */
		   
 // }
 }
