package Interface;

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
import java.text.NumberFormat;
import java.util.ArrayList;
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
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Cliente;
import Negocio.Fornecedor;
import Negocio.Funcionario;
import Negocio.Pessoa;
import Util.Diversos;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

import java.awt.ComponentOrientation;

import javax.swing.ButtonGroup;

public class JFrmPessoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JTextField jTxtIdentifica;
	private JTextField jTxtNome;
    private JTextArea jTxtARelatorio;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private final String titulo;
    private JButton jBtnRelatorio;
    private BasicComboBoxRenderer.UIResource uIResourceC;
    private JTextField jTxtEnde;
    private JLabel jLblCpf;
    private JLabel jLblCnpj;
    private JFormattedTextField jFtdTxtCpf;
    private JFormattedTextField jFtdTxtCnpj;
    private JLabel jLblSalario;
    private JLabel jLblCarteiraDeTrabalho;
    private JTextField jTxtCart;
    private JTextField jTxtSalario;
    private JFormattedTextField jFtdTxtCelular;
    private JTextField jTxtEmail;
    private JPanel jPnlDefinicao;
    private JRadioButton jRdbCliente;
    private JRadioButton jRdbFuncionario;
    private JRadioButton jRdbFornecedor;
    private ControleBasico cP, cPe;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField jTxtSexo;
    private JTextField jTxtRg;
    private JTextField jTxtCep;
  

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmPessoa frame = new JFrmPessoa();
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
	public JFrmPessoa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtNome.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar pessoas";
		this.cP = new ControleGeral(1);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		//setTitle("                                                                           Cadastrar funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 815, 673);
		jContentPane = new JPanel();
		jContentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				centralizarTitulo();
			}
		});
		jContentPane.setBackground(new Color(0, 153, 204));
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(new Color(0, 204, 255));
		jPnlBotoes.setBounds(33, 409, 624, 57);
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
		jBtnIncluir.setBounds(12, 17, 84, 25);
		jPnlBotoes.add(jBtnIncluir);
		
		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(415, 17, 84, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnRetornar = new JButton("Retornar");
		jBtnRetornar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja retornar", titulo))
					dispose();
			}
		});
		jBtnRetornar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRetornar.setBounds(509, 17, 103, 25);
		jPnlBotoes.add(jBtnRetornar);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(106, 17, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					if (verificaChave() == false  )
						cadastrarDados('E');
				else
					Diversos.mostrarDados
					("Não pode remover\n O(A) " + jTxtNome.getText() +
					" pois está sendo manipulado pela venda" , titulo, false);
			}
		});
		jBtnExcluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(200, 17, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		jBtnRelatorio = new JButton("Relat\u00F3rio");
		jBtnRelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  relatorioGeral();	
			}
		});
		jBtnRelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRelatorio.setBounds(294, 17, 111, 25);
		jPnlBotoes.add(jBtnRelatorio);
		
		JLabel jLblIdentifica = new JLabel("Identifica\u00E7\u00E3o");
		jLblIdentifica.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblIdentifica.setHorizontalAlignment(SwingConstants.CENTER);
		jLblIdentifica.setBounds(10, 28, 107, 15);
		jLblIdentifica.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblIdentifica);
		
		jTxtIdentifica = new JTextField();
		jTxtIdentifica.setBackground(new Color(255, 255, 255));
		jTxtIdentifica.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  pesquisa();
			}
			
		});
		jTxtIdentifica.setBounds(122, 23, 95, 25);
		jTxtIdentifica.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtIdentifica.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtIdentifica.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtIdentifica);
		jTxtIdentifica.setColumns(10);
		
		JLabel jLblNome = new JLabel("Nome");
		jLblNome.setBounds(33, 75, 70, 15);
		jLblNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		jContentPane.add(jLblNome);
		
		jTxtNome = new JTextField();
		jTxtNome.setBackground(new Color(255, 255, 255));
		jTxtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtNome.setText(jTxtNome.getText().toUpperCase());
			}
		});
		jTxtNome.setBounds(123, 70, 532, 25);
		jTxtNome.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtNome.setColumns(10);
		jTxtNome.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTxtNome);
		
		JLabel jLblEmail = new JLabel("Email");
		jLblEmail.setBounds(247, 24, 77, 25);
		jLblEmail.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jLblEmail);
		
		JLabel jlblCelular = new JLabel("Celular");
		jlblCelular.setHorizontalAlignment(SwingConstants.CENTER);
		jlblCelular.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jlblCelular.setBounds(20, 162, 95, 25);
		jContentPane.add(jlblCelular);
		
		JScrollPane jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBackground(new Color(51, 153, 204));
		jScrlPRelatorio.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), 
        		"Relatório", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
		jScrlPRelatorio.setBounds(40, 477, 626, 117);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jTxtARelatorio.setBackground(new Color(0, 204, 255));
		jTxtARelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		uIResourceC = new BasicComboBoxRenderer.UIResource();
		uIResourceC.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel jLblEnde = new JLabel("Endere\u00E7o");
		jLblEnde.setHorizontalAlignment(SwingConstants.RIGHT);
		jLblEnde.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblEnde.setBounds(33, 121, 70, 15);
		jContentPane.add(jLblEnde);
		
		jTxtEnde = new JTextField();
		jTxtEnde.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtEnde.setText(jTxtEnde.getText().toUpperCase());
			}
		});
		jTxtEnde.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtEnde.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtEnde.setColumns(10);
		jTxtEnde.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtEnde.setBackground(new Color(255, 255, 255));
		jTxtEnde.setBounds(124, 116, 532, 25);
		jContentPane.add(jTxtEnde);
		
		jFtdTxtCelular = new JFormattedTextField();
		jFtdTxtCelular.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtCelular.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtCelular.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtCelular.setBackground(new Color(255, 255, 255));
		jFtdTxtCelular.setBounds(122, 162, 139, 25);
		jContentPane.add(jFtdTxtCelular);
		jFtdTxtCelular.setFormatterFactory(Diversos.FormatoMascara(titulo, 2));
		
		jTxtEmail = new JTextField();
		jTxtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtEmail.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtEmail.setColumns(10);
		jTxtEmail.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtEmail.setBackground(new Color(255, 255, 255));
		jTxtEmail.setBounds(336, 23, 319, 25);
		jContentPane.add(jTxtEmail);
		
		jPnlDefinicao = new JPanel();
		jPnlDefinicao.setLayout(null);
		jPnlDefinicao.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		jPnlDefinicao.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Definição", TitledBorder.CENTER, TitledBorder.TOP,
						new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
		jPnlDefinicao.setBackground(new Color(102, 204, 255));
		jPnlDefinicao.setBounds(42, 198, 660, 185);
		jContentPane.add(jPnlDefinicao);
		
		jRdbCliente = new JRadioButton("Cliente");
		buttonGroup.add(jRdbCliente);
		jRdbCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleciona('C');
			}
		});
		jRdbCliente.setForeground(Color.BLACK);
		jRdbCliente.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbCliente.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbCliente.setBackground(new Color(153, 204, 255));
		jRdbCliente.setBounds(16, 29, 112, 23);
		jPnlDefinicao.add(jRdbCliente);
		
		jRdbFuncionario = new JRadioButton("Funcion\u00E1rio");
		jRdbFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleciona('F');
			}
		});
		buttonGroup.add(jRdbFuncionario);
		jRdbFuncionario.setForeground(Color.BLACK);
		jRdbFuncionario.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbFuncionario.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbFuncionario.setBackground(new Color(153, 204, 255));
		jRdbFuncionario.setBounds(16, 65, 112, 23);
		jPnlDefinicao.add(jRdbFuncionario);
		
		jRdbFornecedor = new JRadioButton("Fornecedor");
		buttonGroup.add(jRdbFornecedor);
		jRdbFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleciona('O');
			}
		});
		jRdbFornecedor.setForeground(Color.BLACK);
		jRdbFornecedor.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbFornecedor.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbFornecedor.setBackground(new Color(153, 204, 255));
		jRdbFornecedor.setBounds(14, 100, 114, 23);
		jPnlDefinicao.add(jRdbFornecedor);
		
		jLblCpf = new JLabel("Cpf");
		jLblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCpf.setBounds(175, 28, 63, 25);
		jPnlDefinicao.add(jLblCpf);
		
		jLblCnpj = new JLabel("Cnpj");
		jLblCnpj.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCnpj.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCnpj.setBounds(166, 100, 70, 25);
		jPnlDefinicao.add(jLblCnpj);
		
		jFtdTxtCpf = new JFormattedTextField();
		jFtdTxtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtCpf.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtCpf.setBackground(new Color(255, 255, 255));
		jFtdTxtCpf.setBounds(243, 26, 161, 25);
		jPnlDefinicao.add(jFtdTxtCpf);
		jFtdTxtCpf.setFormatterFactory(Diversos.FormatoMascara(titulo, 4));
		
		jFtdTxtCnpj = new JFormattedTextField();
		jFtdTxtCnpj.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtCnpj.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtCnpj.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtCnpj.setBackground(new Color(255, 255, 255));
		jFtdTxtCnpj.setBounds(244, 101, 161, 25);
		jPnlDefinicao.add(jFtdTxtCnpj);
		jFtdTxtCnpj.setFormatterFactory(Diversos.FormatoMascara(titulo, 5));
		
		jLblSalario = new JLabel("Sal\u00E1rio R$");
		jLblSalario.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jLblSalario.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblSalario.setBounds(414, 30, 106, 21);
		jPnlDefinicao.add(jLblSalario);
		
		jLblCarteiraDeTrabalho = new JLabel("Carteira de trabalho n\u00B0\r\n");
		jLblCarteiraDeTrabalho.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblCarteiraDeTrabalho.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCarteiraDeTrabalho.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCarteiraDeTrabalho.setBounds(132, 65, 169, 21);
		jPnlDefinicao.add(jLblCarteiraDeTrabalho);
		
		jTxtCart = new JTextField();
		jTxtCart.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCart.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCart.setColumns(10);
		jTxtCart.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCart.setBackground(new Color(255, 255, 255));
		jTxtCart.setBounds(309, 63, 95, 25);
		jPnlDefinicao.add(jTxtCart);
		
		jTxtSalario = new JTextField();
		jTxtSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtSalario.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtSalario.setColumns(10);
		jTxtSalario.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtSalario.setBackground(new Color(255, 255, 255));
		jTxtSalario.setBounds(510, 28, 123, 25);
		jPnlDefinicao.add(jTxtSalario);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblSexo.setBounds(414, 65, 106, 21);
		jPnlDefinicao.add(lblSexo);
		
		jTxtSexo = new JTextField();
		jTxtSexo.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtSexo.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtSexo.setColumns(10);
		jTxtSexo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtSexo.setBackground(new Color(255, 255, 255));
		jTxtSexo.setBounds(510, 67, 123, 25);
		jPnlDefinicao.add(jTxtSexo);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRg.setHorizontalAlignment(SwingConstants.CENTER);
		lblRg.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblRg.setBounds(414, 105, 106, 21);
		jPnlDefinicao.add(lblRg);
		
		jTxtRg = new JTextField();
		jTxtRg.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtRg.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtRg.setColumns(10);
		jTxtRg.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtRg.setBackground(new Color(255, 255, 255));
		jTxtRg.setBounds(510, 102, 123, 25);
		jPnlDefinicao.add(jTxtRg);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCep.setHorizontalAlignment(SwingConstants.CENTER);
		lblCep.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		lblCep.setBounds(414, 137, 106, 21);
		jPnlDefinicao.add(lblCep);
		
		jTxtCep = new JTextField();
		jTxtCep.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCep.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCep.setColumns(10);
		jTxtCep.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCep.setBackground(new Color(255, 255, 255));
		jTxtCep.setBounds(510, 137, 123, 25);
		jPnlDefinicao.add(jTxtCep);
		setResizable(false);
		//setSize(900, 500);
		setLocationRelativeTo(null); //centraliza o formulário
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
            UIManager.put("OptionPane.messageForeground", Color.BLUE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmPessoa.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}

	private void limpar() {
		 JTextField txt[] = {jTxtIdentifica, jTxtNome, jTxtEnde, jTxtCart, jTxtEmail, jTxtSalario, jTxtSexo, jTxtRg, jTxtCep};
	     for (JTextField t : txt)
	            t.setText("");
	     JFormattedTextField ftd[] = {jFtdTxtCpf, jFtdTxtCnpj, jFtdTxtCelular};
	     for (JFormattedTextField f : ftd)
	            f.setText("");
	     jTxtARelatorio.setText("");
	     buttonGroup.clearSelection();
	     jFtdTxtCpf.setEditable(false);
         jTxtSalario.setEditable(false);
        jTxtCart.setEditable(false);
        jFtdTxtCnpj.setEditable(false); 
	     jTxtIdentifica.setEditable(true);
	     JButton btn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton b : btn) 
	            b.setEnabled(false);    
	     jTxtNome.requestFocusInWindow();
	}
	
	private void carregaObjetos(Pessoa p) {
		 switch (p.getDefinicao()) {
                 case 'C' : jRdbCliente.setSelected(true);
                                   Cliente c = (Cliente) p;
                                   jFtdTxtCpf.setText(c.getCpf());
                                   jTxtSexo.setText(c.getSexo());
                                   jTxtCep.setText(c.getCep());
                                   jTxtRg.setText(c.getRg());
                                    break;
                 case 'F' : jRdbFuncionario.setSelected(true);
                                    Funcionario f = (Funcionario) p;
                                    jFtdTxtCpf.setText(f.getCpf());
                                    jTxtSexo.setText(f.getSexo());
                                    jTxtCep.setText(f.getCep());
                                    jTxtRg.setText(f.getRg());
                                    jTxtCart.setText(f.getCartTrab());
                                    jTxtSalario.setText(Diversos.doisDigitos(0).format(f.getSalario()));
                                   break; 
                  case 'O' : jRdbFornecedor.setSelected(true);
                                     Fornecedor fo = (Fornecedor) p;
                                     jFtdTxtCnpj.setText(fo.getCnpj());
         } 
		jTxtIdentifica.setText(String.valueOf(p.getIdentifica()));
        jTxtNome.setText(p.getNome());
       jTxtEmail.setText(p.getEmail());
        jTxtEnde.setText(p.getEndereco());
        jFtdTxtCelular.setText(p.getCelular());

   }
  
  private void seleciona(char opcao) {
	  switch (opcao) {
	      case 'C' : jFtdTxtCpf.setEditable(true);
	      					jTxtSexo.setEditable(true);
	      					jTxtRg.setEditable(true);
	      					jTxtCep.setEditable(true);
	                         jTxtSalario.setEditable(false);
	                         jTxtCart.setEditable(false);
	                         jFtdTxtCnpj.setEditable(false);
	                        break;
	      case 'F' : jFtdTxtCpf.setEditable(true);
						    jTxtSexo.setEditable(true);
							 jTxtRg.setEditable(true);
						     jTxtCep.setEditable(true);
                             jTxtSalario.setEditable(true);
                             jTxtCart.setEditable(true);
                            jFtdTxtCnpj.setEditable(false);
                            break;
	      case 'O' : jFtdTxtCpf.setEditable(false);
					      jTxtSexo.setEditable(false);
							jTxtRg.setEditable(false);
							jTxtCep.setEditable(false);
                             jTxtSalario.setEditable(false);
                            jTxtCart.setEditable(false);
                           jFtdTxtCnpj.setEditable(true);                 
	  }
  }
	
  private void cadastrarDados(char opcao) {
         String resp = "";
         if(jTxtIdentifica.getText().isEmpty() || jTxtNome.getText().isEmpty() ||  
        		 jTxtEnde.getText().isEmpty() ||    jTxtEmail.getText().isEmpty() ||
        		(!jRdbCliente.isSelected() && jRdbFornecedor.isSelected() &&
        				jRdbFuncionario.isSelected()))
            resp = "Favor digitar os dados";
         else {
        	      Pessoa p = null;
                  if(jRdbCliente.isSelected()) {
                     Cliente c = new Cliente();
                     c.setCpf(jFtdTxtCpf.getText());
                     c.setSexo(jTxtSexo.getText());
                     c.setRg(jTxtRg.getText());
                     c.setCep(jTxtCep.getText());
                     p = c;
                 } 
                 else if (jRdbFuncionario.isSelected()) {
                              Funcionario f = new Funcionario();
                              f.setCpf(jFtdTxtCpf.getText());
                              f.setSexo(jTxtSexo.getText());
                              f.setRg(jTxtRg.getText());
                              f.setCep(jTxtCep.getText());
                              f.setCartTrab(jTxtCart.getText());
                              f.setSalario(Float.parseFloat(Diversos.converterValor(jTxtSalario.getText())));
                             p = f;
                           } 
                          else if (jRdbFornecedor.isSelected()) {
                                       Fornecedor fo = new Fornecedor();
                                       fo.setCnpj(jFtdTxtCnpj.getText());
                                       p = fo;
                                    }
              p.setIdentifica(Integer.parseInt(jTxtIdentifica.getText()));
              p.setDefinicao(jRdbCliente.isSelected() ? 'C' :  jRdbFuncionario.isSelected() ? 'F' : 'O');
              p.setNome(jTxtNome.getText());
              p.setEndereco(jTxtEnde.getText());
              p.setCelular(jFtdTxtCelular.getText());
              p.setEmail(jTxtEmail.getText());              
              if (!cP.setManipular(p, opcao)) { 
                  resp = "Problemas ao " + (opcao == 'A' ? "atualizar" : opcao == 'E' ?  
                   		 "remover" : " inserir") + " os dados do(a) " + p.getNome();
                  jBtnAlterar.setEnabled(false);
                  jBtnExcluir.setEnabled(false);
                  jBtnIncluir.setEnabled(false);
              }   
              else {
                   resp = "A pessoa " +  p.getNome();
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
	 
	private void pesquisa() {
		int identifica;
        Pessoa p;
        if (!Diversos.testaNum(jTxtIdentifica.getText(), titulo))
            jTxtIdentifica.setText(""); // converter texto para numero
        else  if (!Diversos.intervalo(Integer.parseInt(jTxtIdentifica.getText()), 0 ,0 , titulo))
                       jTxtIdentifica.setText(""); //testar se é maior que zero 
                 else {
                          identifica = Integer.parseInt(jTxtIdentifica.getText());
                         Object o = cP.getBusca(identifica,0);
                         if (o == null) {
                            jBtnIncluir.setEnabled(true);
                          Diversos.mostrarDados("Pessoa " + identifica + " inexistente", titulo, true);
                        }    
                        else {
                                p = (Pessoa) o;
                                carregaObjetos(p); 
                                jBtnAlterar.setEnabled(true);
                               jBtnExcluir.setEnabled(true);
                      } 
                     jTxtIdentifica.setEditable(false);
               }
	}

	private void centralizarTitulo() {
   		 FontMetrics fM = getFontMetrics(getFont());
   		 int sC = (getWidth() / 2 - fM.stringWidth(titulo.trim()) / 2) / 
                    fM.stringWidth(" ");
         setTitle(String.format("%" + (sC + 30 ) + "s", "") + titulo.trim());  
    } 
	
	private void relatorioGeral() {
       String resp = cP.relatorio(1) ;
        jTxtARelatorio.setText(!resp.isEmpty() ? resp : "Inexistência de dados");
    }
	
	private boolean verificaChave() {
		ControleGeral cG = new ControleGeral(0);
		String chave =  jRdbCliente.isSelected() ? "Matricula" : jRdbFuncionario.isSelected() ? "Inscricao" : null;
		
	    String parametro[] = {jTxtIdentifica.getText(), "Matricula", "Venda"};
		 return (cG.rotinas(1, parametro));
	  }
}
