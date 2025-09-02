package Interface;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Cliente;
import Negocio.Estoque;
import Negocio.Funcionario;
import Negocio.ItemV;
import Negocio.Pessoa;
import Negocio.Produto;
import Negocio.Venda;
import Util.Diversos;

import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;

import java.awt.ComponentOrientation;

import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JFrmVenda extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JMenuBar jMnBPrincipal;
	private JToolBar jTolBInfo;
    private JLabel jLblAutor;
    private JLabel jLblData;
    private JLabel jLblHora; 
    private final String titulo;
    private JSeparator jSepAutor;
    private JSeparator jSepHora;
    private JSeparator jSepSair;
    private JMenu jMnCadastrar;
    private JTable jTblListarItens;
    private JScrollPane jScrPListarItens;
    private JFormattedTextField jFtdTxtCpf;
    private JPanel jPnlValor;
    private JLabel jLblValor;
    private JTextField jTxtValor;
    private JLabel jLblNumero;
    private JLabel jLblItem;
    private JLabel jLblValorP;
    private JLabel jLblValorR;
    private JPanel jPnlProduto;
    private JPanel jPnlControle;
    private JLabel jLblFun;
    private JComboBox<String> jCmbProduto;
    private JComboBox<String> jCmbFuncionario;
    private JCheckBox jChkSim;
    private JLabel jLblValItem;
    private JSpinner jSpnQuant;
    private JLabel jLblCodigo;
    private JLabel jLblCliente;
    private JLabel jLblValUnit;
    private JLabel jLbIFuncionario;
    private JLabel jLblIdentifica;
    private JLabel jLblNome;
    private JLabel jLblVisivel;
    private JLabel jLblQuantItens;
    private JTextArea jTxtANotaFiscal; 
    private JButton jBtnCancelar;
    private JButton jBtnIniciar;
   /* private JButton jBtnNovoItem;
    private JButton jBtnFinalizar;*/
    private ControleBasico cP, cPe, cV; 
    private List<Object> listaF, listaI, listaP, listaPe ; 
    private BasicComboBoxRenderer.UIResource uIResource;
    private JMenu jMnInstrucao;
    private JSeparator separator;
    private JMenuItem jMnIAviso; 
  
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	  	//EventQueue.invokeLater(new Runnable() {
	  		//public void run() {
		        try {
					JFrmVenda frame = new JFrmVenda();
					frame.setVisible(true);
					//frame.relogio();
				} catch (Exception e) {
					e.printStackTrace();
				}
	     // }
    	//});
	}

	/**
	 * 
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public JFrmVenda() {
		
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				jLblAutor.setText("  Autor : Karine e Flávio    ");
		        jLblData.setText(" Data : " +
		        DateFormat.getDateInstance().format(new Date())+ "                        ");
		        prepara_iniciar(true);
		        if ( jCmbFuncionario.getSelectedIndex() < 0) 
		             Diversos.mostrarDados("Favor selecionar funcionario(a)", titulo, false);	             
			}			
			@Override
			public void windowActivated(WindowEvent arg0) {
				 if ( jLblNome.getText().isEmpty()) {
				       		    carregaLista();
				       		   limpar();
				       		  limparItem();
				 }
			}
		});
		titulo = "Cadastrar vendas";
		 this.cPe = new ControleGeral(1);
		 this.cP = new ControleGeral(2);
	     this.cV = new ControleGeral(3);
		uIResource = new BasicComboBoxRenderer.UIResource();
		uIResource.setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1360, 795);
		jContentPane = new JPanel();
		jContentPane.setMaximumSize(new Dimension(32772, 32767));
		jContentPane.setBackground(new Color(0, 204, 204));
		jContentPane.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(null);
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);
				
		jTolBInfo = new JToolBar();
		jTolBInfo.setBounds(0, -16, 0, 16);
		jTolBInfo.setBackground(Color.CYAN);
		jTolBInfo.setRollover(true);
		jTolBInfo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jTolBInfo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTolBInfo);
		
		jLblAutor = new JLabel("");
		jTolBInfo.add(jLblAutor);
		jLblAutor.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		
		jSepAutor = new JSeparator();
		jSepAutor.setBorder(null);
		jSepAutor.setOrientation(SwingConstants.VERTICAL);
		jSepAutor.setForeground(Color.BLACK);
		jTolBInfo.add(jSepAutor);
		
		jLblData = new JLabel("");
		jLblData.setVerticalAlignment(SwingConstants.TOP);
		jLblData.setHorizontalTextPosition(SwingConstants.LEFT);
		jLblData.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTolBInfo.add(jLblData);
		
		jSepHora = new JSeparator();
		jSepHora.setOrientation(SwingConstants.VERTICAL);
		jSepHora.setForeground(Color.BLACK);
		jTolBInfo.add(jSepHora);
		
		jLblHora = new JLabel("");
		jLblHora.setHorizontalAlignment(SwingConstants.LEFT);
		jLblHora.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTolBInfo.add(jLblHora);
		
		jMnBPrincipal = new JMenuBar();
		jMnBPrincipal.setBackground(Color.WHITE);
		jMnBPrincipal.setForeground(Color.BLACK);
		jMnBPrincipal.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 11));
		jContentPane.add(jMnBPrincipal);
		//jMnBPrincipal.setBounds(0, 0, getHeight() - 22, getWidth() - 16);
		//jMnBPrincipal.setBounds(0, 0, 1360, 24);
		
		JMenu jMnArquivo = new JMenu("Arquivo");
		jMnArquivo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnArquivo.setHorizontalAlignment(SwingConstants.CENTER);
		jMnBPrincipal.add(jMnArquivo);
		
		JMenuItem jMnISair = new JMenuItem("Sair");
		jMnISair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		jMnISair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja finalizar,\na manipulação das vendas", titulo))
					System.exit(0);
			}
		});
		
		jSepSair = new JSeparator();
		jSepSair.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnArquivo.add(jSepSair);
		jMnISair.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnISair.setHorizontalAlignment(SwingConstants.CENTER);
		jMnArquivo.add(jMnISair);
		
		jMnCadastrar = new JMenu("Cadastrar");
		jMnCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		jMnCadastrar.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnBPrincipal.add(jMnCadastrar);
		
		JSeparator jSepCadastrar = new JSeparator();
		jSepCadastrar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(jSepCadastrar);
		
		JMenuItem jMnIPessoa = new JMenuItem("Pessoa");
		jMnIPessoa.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrmPessoa frame = new JFrmPessoa();
                frame.setVisible(true);
			}
		});
		jMnIPessoa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		jMnIPessoa.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnIPessoa);	
		
		
		JMenuItem jMnIProduto = new JMenuItem("Produto");
		jMnIProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrmProduto frame = new JFrmProduto();
	             frame.setVisible(true);
			}
		});
		jMnIProduto.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		jMnIProduto.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnIProduto);
		
		JSeparator JsepVenda = new JSeparator();
		JsepVenda.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnCadastrar.add(JsepVenda);
		
		JMenuItem jMnIVenda = new JMenuItem("Venda");
		jMnIVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		jMnIVenda.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		jMnIVenda.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnCadastrar.add(jMnIVenda);
		
		jMnInstrucao = new JMenu("Instru\u00E7\u00E3o");
		jMnInstrucao.setHorizontalAlignment(SwingConstants.CENTER);
		jMnInstrucao.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnBPrincipal.add(jMnInstrucao);
		
		separator = new JSeparator();
		separator.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jMnInstrucao.add(separator);
		
		jMnIAviso = new JMenuItem("Aviso");
		jMnIAviso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Diversos.mostrarDados
		    	 ("Instruções\nFavor selecionar um produto\nE informar a quantidade\n" +
		          "Para confirmar o item clicar em Valor a pagar\n" +
		    			 "E para adicionar o próximo item, adicionar a quantidade escrevendo\n"+
		    	   "Para finalizar retirar o foco do valor pago\nOu selecionar débito", titulo, true);
			}
		});
		jMnIAviso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		jMnIAviso.setHorizontalAlignment(SwingConstants.CENTER);
		jMnIAviso.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		jMnInstrucao.add(jMnIAviso);
		
		jPnlValor = new JPanel();
		jPnlValor.setBackground(new Color(153, 204, 204));
		jPnlValor.setLayout(null);
		jPnlValor.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jPnlValor.setBounds(10, 524, 532, 185);
		jContentPane.add(jPnlValor);
		
		jTxtValor = new JTextField();
		jTxtValor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!jTxtValor.getText().isEmpty()) 
					finalizar();
			}
		});
		
		jTxtValor.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtValor.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jTxtValor.setForeground(Color.BLUE);
		jTxtValor.setBackground(new Color(153, 204, 204));
		jTxtValor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
        		"Valor pago de R$", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		jTxtValor.setBounds(282, 11, 202, 58);
		jPnlValor.add(jTxtValor);
		jTxtValor.setColumns(10);
		
		jLblValorP = new JLabel("");
		jLblValorP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				  if ((int)jSpnQuant.getValue() > 0) {
				     if (Diversos.confirmar("Confirma item", titulo)) 
					    item();
				  }
				  else
					  Diversos.mostrarDados("Favor informar a quantidade", titulo, false);
			}
		});
		jLblValorP.setBounds(17, 11, 219, 60);
		jPnlValor.add(jLblValorP);
		jLblValorP.setHorizontalAlignment(SwingConstants.CENTER);
		jLblValorP.setForeground(Color.BLUE);
		jLblValorP.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblValorP.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
		        		"Valor a pagar de R$", TitledBorder.CENTER, TitledBorder.TOP,
						new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		
		jLblValorR = new JLabel("");
		jLblValorR.setBounds(17, 82, 225, 60);
		jPnlValor.add(jLblValorR);
		jLblValorR.setHorizontalAlignment(SwingConstants.CENTER);
		jLblValorR.setForeground(Color.BLUE);
		jLblValorR.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblValorR.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
        		"Valor a receber de R$", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		
		JPanel jPnlDebito = new JPanel();
		jPnlDebito.setBounds(401, 82, 93, 60);
		jPnlValor.add(jPnlDebito);
		jPnlDebito.setLayout(null);
		jPnlDebito.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"Débito" , TitledBorder.CENTER, TitledBorder.TOP,
							new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		jPnlDebito.setBackground(new Color(153, 204, 204));
		
		jChkSim = new JCheckBox("Sim");
		jChkSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jChkSim.isSelected()) {
					 jTxtValor.setText( jLblValorP.getText());
					 finalizar();
				}		 
			}
		});
		jChkSim.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jChkSim.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jChkSim.setBackground(new Color(153, 204, 204));
		jChkSim.setBounds(18, 22, 59, 25);
		jPnlDebito.add(jChkSim);
		
		jLblQuantItens = new JLabel("");
		jLblQuantItens.setHorizontalAlignment(SwingConstants.CENTER);
		jLblQuantItens.setForeground(Color.BLUE);
		jLblQuantItens.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblQuantItens.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
		        		"Quant. Itens", TitledBorder.CENTER, TitledBorder.TOP,
						new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		jLblQuantItens.setBounds(257, 82, 134, 60);
		jPnlValor.add(jLblQuantItens);
		
		jLblNumero = new JLabel("");
		jLblNumero.setForeground(Color.BLUE);
		jLblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jLblNumero.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblNumero.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
        		"Numero", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));	
		
		jLblNumero.setBounds(38, 51, 128, 60);
		jContentPane.add(jLblNumero);
		
		jLblItem = new JLabel("");
		jLblItem.setForeground(Color.BLUE);
		jLblItem.setHorizontalAlignment(SwingConstants.CENTER);
		jLblItem.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblItem.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
		        		"Item", TitledBorder.CENTER, TitledBorder.TOP,
						new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		jLblItem.setBounds(237, 51, 128, 60);
		jContentPane.add(jLblItem);
		
		jPnlProduto = new JPanel();
		jPnlProduto.setLayout(null);
		jPnlProduto.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
		        		"Produto", TitledBorder.CENTER, TitledBorder.TOP,
						new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		jPnlProduto.setBackground(new Color(153, 204, 204));
		jPnlProduto.setBounds(20, 154, 360, 333);
		jContentPane.add(jPnlProduto);
		
		jCmbProduto = new JComboBox<String>();
		jCmbProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionaChave(0);
			}
		});
		
		jCmbProduto.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jCmbProduto.setBorder(new LineBorder(Color.BLACK, 2, true));
		jCmbProduto.setBackground(Color.WHITE);
		jCmbProduto.setBounds(22, 21, 312, 25);
		jCmbProduto.setRenderer(uIResource);
		jPnlProduto.add(jCmbProduto);
		
		jLblCodigo = new JLabel("Codigo");
		jLblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodigo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblCodigo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblCodigo.setBounds(22, 72, 173, 25);
		jPnlProduto.add(jLblCodigo);
		
		jLblValUnit = new JLabel("Valor unt\u00E1rio R$");
		jLblValUnit.setHorizontalTextPosition(SwingConstants.LEFT);
		jLblValUnit.setHorizontalAlignment(SwingConstants.LEFT);
		jLblValUnit.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblValUnit.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblValUnit.setBounds(22, 230, 311, 25);
		jPnlProduto.add(jLblValUnit);
		
		jLblValItem = new JLabel("Valor item R$ ");
		jLblValItem.setHorizontalAlignment(SwingConstants.LEFT);
		jLblValItem.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblValItem.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblValItem.setBounds(22, 281, 311, 25);
		jPnlProduto.add(jLblValItem);
		
		JPanel jPnlQuant = new JPanel();
		jPnlQuant.setLayout(null);
		jPnlQuant.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
		        		"Quantidade", TitledBorder.CENTER, TitledBorder.TOP,
						new Font("Arial Black", Font.BOLD | Font.ITALIC, 13), null));
		jPnlQuant.setBackground(new Color(153, 204, 204));
		jPnlQuant.setBounds(205, 54, 129, 60);
		jPnlProduto.add(jPnlQuant);
		
		jSpnQuant = new JSpinner();	
		jSpnQuant.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				valorItem(jCmbProduto.getSelectedIndex());
			}
		});
		jSpnQuant.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		jSpnQuant.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jSpnQuant.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jSpnQuant.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jSpnQuant.setBounds(33, 23, 58, 22);
		JComponent editorD = jSpnQuant.getEditor();
		 JSpinner.DefaultEditor spinnerEditorD = (JSpinner.DefaultEditor)editorD;
	     spinnerEditorD.getTextField().setHorizontalAlignment(JTextField.CENTER);
		 spinnerEditorD.getTextField().setBackground(Color.WHITE);
		 spinnerEditorD.getTextField().setForeground(Color.BLUE);
		jPnlQuant.add(jSpnQuant);
		
		JPanel jPnlVenda = new JPanel();
		jPnlVenda.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
        		"Venda", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		jPnlVenda.setBackground(new Color(153, 204, 204));
		jPnlVenda.setBounds(702, 656, 342, 75);
		jContentPane.add(jPnlVenda);
		jPnlVenda.setLayout(null);
		
		jBtnIniciar = new JButton("Iniciar");
		jBtnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciar();
			}
		});
		jBtnIniciar.setBounds(60, 28, 86, 29);
		jBtnIniciar.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jBtnIniciar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnIniciar.setBackground(Color.LIGHT_GRAY);
		jPnlVenda.add(jBtnIniciar);
		
		jBtnCancelar = new JButton("Cancelar");
		jBtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
				prepara_iniciar(false);
			}
		});
		jBtnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jBtnCancelar.setBackground(Color.LIGHT_GRAY);
		jBtnCancelar.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jBtnCancelar.setBounds(207, 28, 106, 29);
		jPnlVenda.add(jBtnCancelar);
		
		
		jPnlControle = new JPanel();
		jPnlControle.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), 
        		"Identificação", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial Black", Font.BOLD | Font.ITALIC, 15), null));
		jPnlControle.setBackground(new Color(153, 204, 204));
		jPnlControle.setBounds(626, 510, 494, 114);
		jContentPane.add(jPnlControle);
		jPnlControle.setLayout(null);
		
		jCmbFuncionario = new JComboBox<String>();
		jCmbFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionaChave(1);
			}
		});
		jCmbFuncionario.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jCmbFuncionario.setBorder(new LineBorder(Color.BLACK, 2, true));
		jCmbFuncionario.setBackground(Color.WHITE);
		jCmbFuncionario.setBounds(155, 28, 170, 25);
		jCmbFuncionario.setRenderer(uIResource);
		jPnlControle.add(jCmbFuncionario);
		
		jLbIFuncionario = new JLabel("");
		jLbIFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		jLbIFuncionario.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLbIFuncionario.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLbIFuncionario.setBounds(351, 28, 133, 25);
		jPnlControle.add(jLbIFuncionario);
		
		jLblFun = new JLabel("Funcion\u00E1rio");
		jLblFun.setHorizontalAlignment(SwingConstants.CENTER);
		jLblFun.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblFun.setBorder(new EmptyBorder(0, 0, 0, 0));
		jLblFun.setBounds(24, 28, 112, 25);
		jPnlControle.add(jLblFun);
		
		JLabel jLblCli = new JLabel("Cliente");
		jLblCli.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCli.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblCli.setBorder(new EmptyBorder(0, 0, 0, 0));
		jLblCli.setBounds(24, 64, 112, 25);
		jPnlControle.add(jLblCli);
		
		jFtdTxtCpf = new JFormattedTextField();
		jFtdTxtCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				pesquisa();
			}
		});
		jFtdTxtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtCpf.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jFtdTxtCpf.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtCpf.setBackground(Color.WHITE);
		jFtdTxtCpf.setBounds(155, 64, 170, 25);
		jPnlControle.add(jFtdTxtCpf);
		jFtdTxtCpf.setFormatterFactory(Diversos.FormatoMascara(titulo, 4));
		
		jLblIdentifica = new JLabel("");
		jLblIdentifica.setHorizontalAlignment(SwingConstants.CENTER);
		jLblIdentifica.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblIdentifica.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblIdentifica.setBounds(353, 63, 131, 25);
		jPnlControle.add(jLblIdentifica);
		
		jLblNome = new JLabel("");
		jLblNome.setHorizontalAlignment(SwingConstants.CENTER);
		jLblNome.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblNome.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblNome.setBounds(155, 28, 170, 25);
		jPnlControle.add(jLblNome);
		
		jLblCliente = new JLabel("");
		jLblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCliente.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		jLblCliente.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblCliente.setBounds(155, 64, 170, 25);
		jPnlControle.add(jLblCliente);
		
		jScrPListarItens = new JScrollPane();
		jScrPListarItens.setBorder(new LineBorder(Color.WHITE, 2));
		jScrPListarItens.setBounds(390, 11, 980, 231);
		jContentPane.add(jScrPListarItens);
		
		jTblListarItens = new JTable();
		jTblListarItens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selecionarItem();  
			}
		});
		jScrPListarItens.setViewportView(jTblListarItens);
		
		JScrollPane jSrclPNotaFiscal = new JScrollPane();
		jSrclPNotaFiscal.setBackground(new Color(51, 204, 204));
		jSrclPNotaFiscal .setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), 
        		"Nota Fiscal", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));	
		jSrclPNotaFiscal.setBounds(390, 272, 980, 195);
		jContentPane.add(jSrclPNotaFiscal);
		
		jTxtANotaFiscal = new JTextArea();
		jTxtANotaFiscal.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jTxtANotaFiscal.setBackground(Color.WHITE);
		jTxtANotaFiscal.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jSrclPNotaFiscal.setViewportView(jTxtANotaFiscal);

	
		setResizable(false);		
		setLocationRelativeTo(null);
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 15)));
            UIManager.put("OptionPane.messageForeground", Color.BLUE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmVenda.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}
	
	public void relogio() {
		   try {
	    	  Thread.sleep(1000);
	    	  while(true)  
	    		   jLblHora.setText
	              ("Hora : " + DateFormat.getTimeInstance().format(new Date().getTime()) +  
	            		  "                                    ");		 
	       } 
	       catch (InterruptedException e){
	         Diversos.mostrarDados("Problema ao acionar a hora " + e.getMessage(), titulo, false);  
	       }
	}
	public void prepara_iniciar(boolean ver) {
		 if (ver) {
			 jCmbFuncionario.setSelectedIndex(-1);
			 jCmbFuncionario.requestFocusInWindow();
			 jCmbFuncionario.setVisible(true);
		     jLblNome.setText("");
		     jLbIFuncionario.setText("");
		 }
		 else {
			    jFtdTxtCpf.setVisible(true);
			    jFtdTxtCpf.setEnabled(true);
			    jCmbFuncionario.setVisible(false);
			    jLblNome.setVisible(false);
	            jLbIFuncionario.setVisible(false);
	            jLblFun.setVisible(false);
	            jFtdTxtCpf.requestFocusInWindow();
	           jBtnIniciar.setEnabled(true);
		 }
	}
	
	public void coordenadas(int x, int y) {
		jMnBPrincipal.setBounds(0, 0, y, 24);
		jTolBInfo.setBounds(0, x, y, 24);		
	}
	
	private void selecionaChave(int posi) {
		 int ind = -1; 
		 try{
		       switch (posi) {
		              case 0 : ind = jCmbProduto.getSelectedIndex();
	                                   if ( ind > -1) {
	                                	   Produto p  = (Produto) listaP.get(ind);
	                                       jLblCodigo.setText("Codigo : " + String.valueOf(p.getCodigo()));
	                                       jLblValUnit.setText("Valor Unitario R$ " + 
		                                            Diversos.doisDigitos(0).format(p.getPrecoUnit()));
		                                        jLblValItem.setText("Valor do Item R$ "  + 
		                                		 Diversos.doisDigitos(0).format(p.getPrecoUnit() *
		                                				  (int)jSpnQuant.getValue()));
		                                      //  jSpnQuant.setEnabled(true);
        	                           }       
	                                   break;
		             case 1 : 
		            	 ind = jCmbFuncionario.getSelectedIndex();
                                       if (ind > -1) {
                                    	   Funcionario f = (Funcionario) listaF.get(ind);
                                           jLblNome.setText(f.getNome());
                                           jLbIFuncionario.setText(String.valueOf(f.getIdentifica())); 
                                           jBtnIniciar.setEnabled(true);
                                      }     
     		   }
		 }   
	     catch(Exception e) {
	            Diversos.mostrarDados("Problemas ao selecionar os dados dos produtos, \n" + 
	            "clientes e ou funcionários "+ e.getMessage(), titulo, false); 
	     }      
    }
	
	private void carregaLista() {
		 try{
	           int[] posi = {jCmbProduto.getSelectedIndex(), jCmbFuncionario.getSelectedIndex()};
	           
	           listaP= cP.lista();  
	           jCmbProduto.removeAllItems();
	           for (Object o : listaP) {
	                Produto p = (Produto) o;
	                jCmbProduto.addItem(p.getDescricao());
	           } 
	           if (posi[0] > -1) 
	              jCmbProduto.setSelectedIndex(posi[0]);
	           else 
	              jCmbProduto.setSelectedIndex(-1);
	           
	         listaPe = cPe.lista();
	         this.listaF = new ArrayList<>();
	         jCmbFuncionario.removeAllItems();
	          for (Object o : listaPe) {
	                Pessoa p = (Pessoa) o; 
	                if (p.getDefinicao() == 'F') {
	                   Funcionario v = (Funcionario) p;
	                   listaF.add(v);
	                   jCmbFuncionario.addItem(v.getNome());
	                }
	            }
	           if (posi[1] > -1) 
	                 jCmbFuncionario.setSelectedIndex(posi[1]);
	            else {
	                     jLbIFuncionario.setText("");
	                   jCmbFuncionario.setSelectedIndex(-1);
	             } 	              
		 }   
	     catch(Exception e) {
	            Diversos.mostrarDados("Problemas ao carregar os dados dos produtos, \n" + 
	            "clientes e ou funcionários"+ e.getMessage(), titulo, false); 
	         }
	 }
	
	
	 public void limpar() {
		 DefaultTableModel model= (DefaultTableModel)jTblListarItens.getModel();
         model.setRowCount(0);
         model.setColumnCount(0);
         JLabel lbl[] = { jLblCliente, jLblIdentifica, jLblNumero, jLblItem, jLblQuantItens, jLblValorP, jLblValorR};
         for (JLabel l : lbl)
               l.setText("");
         JButton btn[] = {jBtnCancelar, jBtnIniciar};
         for (JButton b : btn) {
             b.setEnabled(false);
         }
         jPnlControle.setVisible(true);
         jFtdTxtCpf.setText("");
         jFtdTxtCpf.setVisible(true);
         jFtdTxtCpf.setEnabled(false);
         jSpnQuant.setEnabled(false);  
        jChkSim.setSelected(false);
        limparItem();
        jTxtValor.setText("");
        jTxtValor.requestFocusInWindow();
	 }
	 
	 public void limparItem() {
	      jLblCodigo.setText("");
	     jLblValUnit.setText("Valor untário R$");
	    jLblValItem.setText("Valor item R$ ");
	    jSpnQuant.setValue(1);
	   jSpnQuant.setEnabled(true);
	    
	    JComboBox<?> jCmb[] = {jCmbProduto};
	     for (JComboBox<?> cmb : jCmb) 
		    	cmb.setSelectedIndex(-1);
	    
	 }
	
	 public void iniciar() {
		   jTxtANotaFiscal.setText("");
		   if (!Diversos.confirmar("Cliente possui cadastro", titulo)){
			    jPnlControle.setVisible(false);
			    jLblIdentifica.setText("0");
		   }
		   else
			   prepara_iniciar(false);
	      limparItem();
	      DefaultTableModel modelo = (DefaultTableModel)jTblListarItens.getModel();
	   modelo.setRowCount(0);
	   modelo.setColumnCount(0);
	    jTblListarItens.setFont(new Font("Arial Black", Font.BOLD + Font.ITALIC, 15));
	             ((DefaultTableCellRenderer)  jTblListarItens.getTableHeader().getDefaultRenderer()).
	   setHorizontalAlignment(SwingConstants.CENTER);
	   jTblListarItens.setGridColor(Color.LIGHT_GRAY);
	   jTblListarItens.setBackground(Color.LIGHT_GRAY);
	   jTblListarItens.setForeground(Color.BLACK);
	  ControleGeral cVU = new ControleGeral(4);
      Venda v = (Venda) cVU.buscaUltima();
       this.listaI = new ArrayList<>();
	   jLblNumero.setText(v != null ? String.valueOf(v.getNumero() + 1) : "1");
	   jLblItem.setText("1");
	   jSpnQuant.setValue(1);  
	   jLblQuantItens.setText("0");
	   jLblValorR.setText("0,00");
       jLblValorP.setText("0,00");
       jTxtValor.setText("0,00");
       jBtnIniciar.setEnabled(false);
       jSpnQuant.setEnabled(true);     
	 }
	 private void preencheTabela() {
			DefaultTableModel modelo= (DefaultTableModel)jTblListarItens.getModel();
			modelo.setRowCount(0);
		    modelo.setColumnCount(0);
			DefaultTableCellRenderer valores = new DefaultTableCellRenderer();
	        valores.setHorizontalAlignment(JLabel.CENTER);   
	        jTblListarItens.getTableHeader().setDefaultRenderer(valores);
	        Object[] colunas  = {"Item","Código","Produto","Quantidade",
	          " Valor Unitário" ,"Valor"};
	       for (Object coluna : colunas) 
	           modelo.addColumn(coluna); 
	       jTblListarItens.setModel(modelo); 
	       jLblValorP.setText("0,00");
	       for (int i = 0; i < listaI.size(); i++) { 
	           ItemV iV = (ItemV) listaI.get(i);
	           Object[] linhas = 
	                   new Object[]{Diversos.senDigitos().format(i + 1),
	           iV.getProduto().getCodigo(),iV.getProduto().getDescricao(),
	           Diversos.senDigitos().format(iV.getQuantidade()),
	           Diversos.doisDigitos(0).format(iV.getProduto().getPrecoUnit()),
	           Diversos.doisDigitos(0).format(iV.valorItem())};
	           jTblListarItens.getTableHeader().setDefaultRenderer(valores);
	           modelo.addRow(linhas); 
	           jLblValorP.setText(Diversos.doisDigitos(0).format
	        		   (Float.parseFloat(Diversos.converterValor( jLblValorP.getText()))+ iV.valorItem()));                                                                        
	        }             
	       for (int i = 0; i <  jTblListarItens.getColumnCount(); i++)
	           jTblListarItens.getColumn(jTblListarItens.getModel().getColumnName(i))
	                   .setCellRenderer(valores);  
	       jBtnCancelar.setEnabled(true);	     
	  	}
    
	 private void finalizar() {
         jTblListarItens.setEnabled(true);
         if (listaI.size() > 0) {
             jLblValorR.setText(Diversos.doisDigitos(1).format(
            Float.parseFloat(Diversos.converterValor( jTxtValor.getText())) - 
        	 Float.parseFloat(Diversos.converterValor( jLblValorP.getText()))));
            Venda v = dadosVenda(true);
             for (Object listaIItem : listaI) {
                    ItemV i = (ItemV) listaIItem;
                    i.setVenda(v);
                    Produto p = (Produto)  cP.getBusca(i.getProduto().getCodigo(), 0);
                    Estoque e = (Estoque) p;
                    e.setQuantidade(e.getQuantidade() - i.getQuantidade());
                    if (cP.setManipular(p, 'A')) 
                        v.setItem(i);
                   else 
                       Diversos.mostrarDados("Problemas ao atualizar os dados "
                                               + "do estoque " + e.getQuantidade(), titulo, false);      
              }
              if (cV.setManipular(v, 'I')) {  
                   Diversos.mostrarDados("A venda " +
                             jLblNumero.getText() + " foi inserida com sucesso", titulo, true);
                    jTxtANotaFiscal.setText(v.relatorio());
                   jTxtANotaFiscal.setEditable(false);
                  limpar();
                  limparItem(); 
                  jFtdTxtCpf.setVisible(true);
                  jCmbProduto.setVisible(true);
                 jBtnIniciar.setEnabled(true);
              } 
        }    
   }
	 private void item() {
		 int ind = jCmbProduto.getSelectedIndex();
       	 int num = Integer.parseInt(jLblNumero.getText());
       	Produto p = (Produto) listaP.get(ind);
        Venda v = dadosVenda(false);
	    ItemV i = new ItemV();
        i.setVenda(v); 
        i.setItem(Integer.parseInt(jLblItem.getText()));
        i.setProduto(p);
        i.setQuantidade((int)jSpnQuant.getValue());
        
        listaI.add(i);
         jLblQuantItens.setText(Diversos.senDigitos().format(
                ((int)jSpnQuant.getValue() + Integer.parseInt( jLblQuantItens.getText()))));         
         preencheTabela();
         limparItem();
         jLblNumero.setText(String.valueOf(num));
         jLblItem.setText(String.valueOf(listaI.size() + 1));
        jTblListarItens.setEnabled(true); 	                   	   		
}
	 
	
    private Venda dadosVenda(boolean fim) {
		Venda v = new Venda();
		 v.setNumero(Integer.parseInt(jLblNumero.getText()));
		 if (fim) {
		 Cliente c = null;  
         if (!jLblCliente.getText().isEmpty())  
        	  c = (Cliente) cPe.getBusca(Integer.parseInt(jLblIdentifica.getText()), 0);
         else  
        	  c = (Cliente) cPe.getBusca(0, 0); //Cliente não cadastrado 
         v.setCliente(c);	 
         int posi = jCmbFuncionario.getSelectedIndex();
         if (posi > -1) {
              Funcionario f =  (Funcionario) listaF.get(posi);
              v.setFuncionario(f); 
        } 
       Date hoje = new Date();
       DateFormat dF = DateFormat.getDateInstance();
       v.setData(dF.format(hoje));	
       v.setValPago(Float.parseFloat(Diversos.converterValor(jTxtValor.getText()))); 
       v.setPago(jChkSim.isSelected());
		 }
		return(v);
	}
    
    private void selecionarItem() {
    	int ind = jTblListarItens.getSelectedRow();
    	String resp = "" ;
        if (ind > -1) {
        	 ItemV iV = (ItemV) listaI.get(ind);
        	 resp += "Deseja estornar o  item n° " + iV.getItem() + "  da venda n° " + 
        	     iV.getVenda().getNumero() + '\n';
        	 resp +=  " Solicitada pelo cliente " ;
        	 resp += !jLblCliente.getText().isEmpty() ?
			           jLblCliente.getText() : "não cadastrado(a)";
        	 resp += "  atendido pelo(a) funcionario(a) "+  jLblNome.getText();
        	 resp += " na data " + iV.getVenda().getData() + "\nSendo o produto " ;
        	 resp +=   iV.getProduto().getDescricao() + " com o código " + 
        	                   iV.getProduto().getCodigo() + '\n';
        	 resp +=   " da Editora " + iV.getProduto().getEditora() + '\n';
        	 resp += "A quantidade "  + iV.getQuantidade() + " e o preço unitário de " + 
        	                        Diversos.doisDigitos(1).format(iV.getProduto().getPrecoUnit());
        	 resp += " resultando no valor do item de " +  
        	                        Diversos.doisDigitos(1).format(iV.valorItem());      	 
        	 if (Diversos.confirmar(resp , titulo)) {
        		 jLblValorP.setText(Diversos.doisDigitos(0).format
                                               (Float.parseFloat(Diversos.converterValor
                		                                                       (jLblValorP.getText())) - iV.valorItem()));
        		 jLblQuantItens.setText(Diversos.senDigitos().format(
                         Integer.parseInt( jLblQuantItens.getText()) - iV.getQuantidade()));
        		 
        	    listaI.remove(ind);
        	    limparItem();
                if (listaI.size() > 0) {
                	preencheTabela(); 
                   jLblItem.setText(String.valueOf(listaI.size() + 1));
                }
                else {
                	     limpar();
                	     jBtnIniciar.setEnabled(true);
                	     jPnlControle.setVisible(true);
                } 	     
        	 }  
        }	 
    }
        
	private void valorItem(int posi) {		
		if(posi > -1) {
			  int qt = Integer.parseInt(String.valueOf(jSpnQuant.getValue()));
		      Produto p = (Produto) listaP.get(posi);
		      Estoque e = (Estoque) p;
		      if (e.getQuantidade() > 0)
		      if (qt > e.getQuantidade()) {
		    	    Diversos.mostrarDados("Quantidade pedida é " + qt +
		                    ", sendo superior a quantidade estocada de " +
		                    String.valueOf(e.getQuantidade()), titulo, true); 
		                jSpnQuant.setValue(1);
		            }
		      else
		        jLblValItem.setText("Valor do Item R$ " +
	                  Diversos.doisDigitos(0).format
	                                                (p.getPrecoUnit() * (int)jSpnQuant.getValue())); 
		}  
		
	}
		
	private void pesquisa() {
		  String cpf  = jFtdTxtCpf.getText();
		  ControleGeral cPe = new ControleGeral(1);
        Object o = cPe.buscaCpf(cpf,0);
         if (o == null) {
              Diversos.mostrarDados("Cliente não cadastrado" , titulo, true);
              jPnlControle.setVisible(false);
         }    
         else {   
                  Pessoa p = (Pessoa) o;
                 jLblCliente.setText(p.getNome());
                jLblIdentifica.setText(String.valueOf(p.getIdentifica())); 
               jFtdTxtCpf.setVisible(false);
         }      
	}
}		

