import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;


public class CDUFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CDUFrame() {
		initialize();
	}
	
	private void initialize() {
		setTitle("CDU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);		
	}

}
