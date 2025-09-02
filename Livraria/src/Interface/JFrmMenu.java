package Interface;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class JFrmMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
				try {
					JFrmMenu frame = new JFrmMenu();
					frame.setVisible(true);
					frame.chama(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
		//	}
		//});
	}

	/**
	 * Create the frame.
	 */
	public JFrmMenu() {
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setLocationRelativeTo(null);		
	    setExtendedState(MAXIMIZED_BOTH);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
   public void chama(JFrmMenu a) {
	   a.dispose();
	   JFrmVenda v = new JFrmVenda();
       v.setVisible(true);   
       v.setExtendedState(MAXIMIZED_BOTH);
       v.coordenadas(getHeight() , getWidth() - 18);
      // v.temporizador();
       v.relogio();
   }
}
