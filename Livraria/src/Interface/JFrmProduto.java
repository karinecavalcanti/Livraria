package Interface;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Estoque;
import Negocio.Produto;
import Util.Diversos;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.ComponentOrientation;

import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JFrmProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JTextField jTxtCodigo;
	private JTextField jTxtDesc;
	private JTextField jTxtPrecoU;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private final ButtonGroup ButtonGroup = new ButtonGroup();
    private JTextArea jTxtARelatorio;
    private JButton jBtnRelatorio;
    private final String titulo;
    private ControleBasico  cP;  
	private BasicComboBoxRenderer.UIResource uIResource;
	private JComboBox<String> jCmbGrupo;
	private JSpinner jSpnQuant;
	private JSpinner jSpnQuantMin;
	private JLabel lblEditora;
	private JTextField jTxtEdit;
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmProduto frame = new JFrmProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
			      }
	      }
    	});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public JFrmProduto() { 
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				carregaLista();
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtDesc.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar produtos com estoques";
		this.cP = new ControleGeral(2);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		centralizarTitulo();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 766, 522);
		jContentPane = new JPanel();
		jContentPane.setForeground(new Color(30, 144, 255));
		jContentPane.setBackground(new Color(51, 153, 204));
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(new Color(51, 204, 255));
		jPnlBotoes.setBounds(33, 245, 625, 65);
		jPnlBotoes.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jPnlBotoes);
		jPnlBotoes.setLayout(null);
		
		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarDados('I');			
			}
		});
		jBtnIncluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 23, 84, 25);
		jPnlBotoes.add(jBtnIncluir);
		
		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(415, 23, 84, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnSair = new JButton("Sair");
		jBtnSair.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja finalisar", titulo))
					dispose(); 
			}
		});
		jBtnSair.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnSair.setBounds(509, 23, 90, 25);
		jPnlBotoes.add(jBtnSair);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(106, 23, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					if (!verificaChaveV())
						cadastrarDados('E');
				else
					Diversos.mostrarDados
					("Não pode remover\n O produto " + jTxtDesc.getText() +
					" pois está sendo manipulado pela venda" , titulo, false);
			}
		});
		jBtnExcluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(200, 23, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		jBtnRelatorio = new JButton("Relat\u00F3rio");
		jBtnRelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  relatorioGeral();	
			}
		});
		jBtnRelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRelatorio.setBounds(294, 23, 111, 25);
		jPnlBotoes.add(jBtnRelatorio);
		
		JLabel jLblCodigo = new JLabel("C\u00F3digo");
		jLblCodigo.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodigo.setBounds(23, 28, 94, 15);
		jLblCodigo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblCodigo);
		
		jTxtCodigo = new JTextField();
		jTxtCodigo.setBackground(new Color(255, 255, 255));
		jTxtCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  busca();
			}
			
		});
		jTxtCodigo.setBounds(122, 23, 81, 25);
		jTxtCodigo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCodigo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtCodigo);
		jTxtCodigo.setColumns(10);
		
		JLabel jLblDesc = new JLabel("Descri\u00E7\u00E3o");
		jLblDesc.setBounds(33, 75, 85, 15);
		jLblDesc.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
		jContentPane.add(jLblDesc);
		
		jTxtDesc = new JTextField();
		jTxtDesc.setBackground(new Color(255, 255, 255));
		jTxtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtDesc.setText(jTxtDesc.getText().toUpperCase());
			}
		});
		jTxtDesc.setBounds(124, 70, 520, 25);
		jTxtDesc.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtDesc.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtDesc.setColumns(10);
		jTxtDesc.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTxtDesc);
		
		JScrollPane jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBackground(new Color(51, 204, 255));
		jScrlPRelatorio.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), 
        		"Relatório", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
		jScrlPRelatorio.setBounds(23, 321, 640, 105);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jTxtARelatorio.setBackground(new Color(255, 255, 255));
		jTxtARelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		uIResource = new BasicComboBoxRenderer.UIResource();
		uIResource.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel jLblPrecoU = new JLabel("Pre\u00E7o unit\u00E1rio R$");
		jLblPrecoU.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblPrecoU.setHorizontalAlignment(SwingConstants.CENTER);
		jLblPrecoU.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblPrecoU.setBounds(34, 116, 140, 21);
		jContentPane.add(jLblPrecoU);
		
		jTxtPrecoU = new JTextField();
		jTxtPrecoU.setBackground(new Color(255, 255, 255));
		jTxtPrecoU.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtPrecoU.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtPrecoU.setColumns(10);
		jTxtPrecoU.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtPrecoU.setBounds(199, 114, 81, 25);
		jContentPane.add(jTxtPrecoU);
		
		 
		 JPanel jPnlEstoque = new JPanel();
		 jPnlEstoque.setLayout(null);
		 jPnlEstoque.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		 jPnlEstoque.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Estoque", TitledBorder.CENTER, TitledBorder.TOP,
		 				new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
		 jPnlEstoque.setBackground(new Color(51, 204, 255));
		 jPnlEstoque.setBounds(33, 154, 407, 65);
		 jContentPane.add(jPnlEstoque);
		 
		 JLabel jLblQuant = new JLabel("Quantidade");
		 jLblQuant.setHorizontalTextPosition(SwingConstants.CENTER);
		 jLblQuant.setHorizontalAlignment(SwingConstants.CENTER);
		 jLblQuant.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jLblQuant.setBounds(16, 26, 95, 21);
		 jPnlEstoque.add(jLblQuant);
		 
		 jSpnQuant = new JSpinner();
		 jSpnQuant.setForeground(Color.BLACK);
		 jSpnQuant.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jSpnQuant.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 jSpnQuant.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jSpnQuant.setBounds(115, 25, 56, 24);
		 JComponent editorD = jSpnQuant.getEditor();
		 JSpinner.DefaultEditor spinnerEditorD = (JSpinner.DefaultEditor)editorD;
	     spinnerEditorD.getTextField().setHorizontalAlignment(JTextField.CENTER);
		 spinnerEditorD.getTextField().setBackground(Color.CYAN);
		 jPnlEstoque.add(jSpnQuant);
		 
		 JLabel lblQuantidadeMnima = new JLabel("Quantidade m\u00EDnima");
		 lblQuantidadeMnima.setHorizontalTextPosition(SwingConstants.CENTER);
		 lblQuantidadeMnima.setHorizontalAlignment(SwingConstants.CENTER);
		 lblQuantidadeMnima.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblQuantidadeMnima.setBounds(175, 27, 163, 21);
		 jPnlEstoque.add(lblQuantidadeMnima);
		 
		 jSpnQuantMin = new JSpinner();
		 jSpnQuantMin.setForeground(Color.BLACK);
		 jSpnQuantMin.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jSpnQuantMin.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 jSpnQuantMin.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jSpnQuantMin.setBounds(341, 25, 42, 24);
		 JComponent editorDM = jSpnQuantMin.getEditor();
		 JSpinner.DefaultEditor spinnerEditorDM = (JSpinner.DefaultEditor) editorDM;
	     spinnerEditorDM.getTextField().setHorizontalAlignment(JTextField.CENTER);
		 spinnerEditorDM.getTextField().setBackground(Color.CYAN);
		 jPnlEstoque.add(jSpnQuantMin);
		 
		 JLabel jLblGrupo = new JLabel("Grupo");
		 jLblGrupo.setHorizontalAlignment(SwingConstants.CENTER);
		 jLblGrupo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jLblGrupo.setBounds(236, 23, 61, 25);
		 jContentPane.add(jLblGrupo);
		 
		 jCmbGrupo = new JComboBox<String>();
		 jCmbGrupo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jCmbGrupo.setBorder(new LineBorder(Color.BLACK, 2, true));
		 jCmbGrupo.setBackground(new Color(255, 255, 255));
		 jCmbGrupo.setBounds(300, 23, 239, 25);
		 jCmbGrupo.setRenderer(uIResource);
		 jContentPane.add(jCmbGrupo);
		 
		 lblEditora = new JLabel("Editora");
		 lblEditora.setHorizontalAlignment(SwingConstants.RIGHT);
		 lblEditora.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 lblEditora.setBounds(316, 120, 85, 15);
		 jContentPane.add(lblEditora);
		 
		 jTxtEdit = new JTextField();
		 jTxtEdit.setHorizontalAlignment(SwingConstants.CENTER);
		 jTxtEdit.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		 jTxtEdit.setColumns(10);
		 jTxtEdit.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		 jTxtEdit.setBackground(new Color(255, 255, 255));
		 jTxtEdit.setBounds(414, 117, 81, 25);
		 jContentPane.add(jTxtEdit);
		
		 setResizable(false);
		 setLocationRelativeTo(null); //centraliza o formulário
		 
		 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
             UIManager.put("OptionPane.messageForeground", Color.BLUE);
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmProduto.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	private void limpar() {
		 JTextField txt[] = {jTxtCodigo, jTxtDesc, jTxtPrecoU, jTxtEdit};
	     for (JTextField t : txt)
	            t.setText("");
	     jCmbGrupo.setSelectedIndex(-1);
	     ButtonGroup.clearSelection();
	     JSpinner spn[] = { jSpnQuant,  jSpnQuantMin};
	     for (JSpinner s : spn)
	    	    s.setValue(1);
	     jTxtARelatorio.setText("");
	     jTxtCodigo.setEditable(true);
	     JButton jBtn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton btn : jBtn) 
	            btn.setEnabled(false);  //desabilita os botões  
	     jTxtDesc.requestFocusInWindow();
	}
	
	private void relatorioGeral() {
		 jTxtARelatorio.setText(cP.relatorio(2));
    }
	
	private void centralizarTitulo() {
	    FontMetrics fM = getFontMetrics(getFont());
        int sC = (getWidth() / 2 - fM.stringWidth(titulo.trim()) / 2) / fM.stringWidth(" ");
        setTitle(String.format("%" + (sC + 99 ) + "s", "") + titulo.trim());
    }
	
	private void carregaLista() {
		String grupo[] = {"Terror", "Romance", "Literatura", "Comédia", "Drama", "Suspense"};
        for(String g : grupo)
        	jCmbGrupo.addItem(g);
        }     
	
	private void carregaObjetos(Produto p) {
	    Estoque e = (Estoque) p;
	    jSpnQuant.setValue(e.getQuantidade());
	    jSpnQuantMin.setValue(e.getQuantMin());
	   jTxtCodigo.setText(String.valueOf(p.getCodigo()));
       jTxtDesc.setText(p.getDescricao());
       jCmbGrupo.setSelectedItem(p.getGrupo());  
       jTxtEdit.setText(p.getEditora());
      jTxtPrecoU.setText(Diversos.doisDigitos(0).format(p.getPrecoUnit()));   
	}
		
	private void cadastrarDados(char opcao) {
	         String resp = "";
	         if(jTxtCodigo.getText().isEmpty() || jTxtDesc.getText().isEmpty() || 
	        		 jCmbGrupo.getSelectedIndex() < 0 || 	 jTxtPrecoU.getText().isEmpty()) 
	              resp = "Favor digitar os dados";
	         else {
	                   Estoque e = new Estoque();
	                   e.setQuantidade((int) jSpnQuant.getValue());
	                   e.setQuantMin((int) jSpnQuantMin.getValue());
	        	      Produto p = e;
	                  p.setCodigo(Integer.parseInt(jTxtCodigo.getText()));
	                  p.setDescricao(jTxtDesc.getText());
	                  p.setGrupo((String)jCmbGrupo.getSelectedItem());
	                  p.setPrecoUnit(Float.parseFloat
	            		  (Diversos.converterValor(jTxtPrecoU.getText())));
	                  p.setEditora(jTxtEdit.getText());
	                  if (!cP.setManipular(p, opcao)) {  // ==  false
	                       resp = "Problemas ao " + (opcao == 'A' ? "atualizar" : opcao == 'E' ?  
	                   	   "remover" : "inserir") + " os dados do produto " + p.getDescricao();
	                      
	                       jBtnAlterar.setEnabled(false);
	                       jBtnExcluir.setEnabled(false);
	                       jBtnIncluir.setEnabled(false);
	                   }   
	                   else {
	            	             resp = "O produto " + p.getDescricao() + 
	                        	        	  " pertencente  ao grupo " + p.getGrupo(); 
	                            switch(opcao) {
	                                  case 'A' :  resp += "\nFoi atualizado(a) ";
	                                                       break;
	                                  case 'E' :  resp  +=  "\nFoi removido(a) ";
	                                                      limpar();
	                                                      jBtnAlterar.setEnabled(false);
	                                                      jBtnExcluir.setEnabled(false);
	                                                      break;
	                                  case 'I' :  resp +=  "\nFoi inserido(a) ";
	                                                     jBtnAlterar.setEnabled(true);
	                                                     jBtnExcluir.setEnabled(true);
	                                                    jBtnIncluir.setEnabled(false);                  
	                           }
	                          resp += " com sucesso";                
	                    }                  
	              }
	             Diversos.mostrarDados(resp, titulo, (resp.charAt(0) != 'F' && resp.charAt(0) != 'P'));
	    }
	 
	private void busca() {
		int codigo;
        Produto p;
        if (!Diversos.testaNum(jTxtCodigo.getText(), titulo))
            jTxtCodigo.setText(""); // converter texto para numero
        else if (!Diversos.intervalo
        		   (Integer.parseInt(jTxtCodigo.getText()), 0 ,0 , titulo))
                       jTxtCodigo.setText(""); //testar se é maior que zero
               else {
                       codigo = Integer.parseInt(jTxtCodigo.getText());
                       Object o = cP.getBusca(codigo, 0);
                       if (o == null) {
                            jBtnIncluir.setEnabled(true);
                           Diversos.mostrarDados
                                ("Produto " + codigo + " inexistente", titulo, true);
                       }    
                       else {
                    	         p = (Produto) o;
                                carregaObjetos(p); 
                                jBtnAlterar.setEnabled(true);
                                jBtnExcluir.setEnabled(true);
                      } 
                     jTxtCodigo.setEditable(false);
            }
	} //Fim do busca
	
	private boolean verificaChaveV() {
		ControleGeral cG = new ControleGeral(0); 
	    String parametro[] = {jTxtCodigo.getText(), "codigo", "ItemV"};
		 return (cG.rotinas(1, parametro));
	  }
	
	
	
}
