import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;

//
// CDUFrame represents the actual content of the FMC module
//
public class CDUFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CDUFrame() {
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		setUndecorated(true);  // No title bar nor border
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);		
	}

}
