import java.awt.EventQueue;

import javax.swing.JFrame;


//import java.awt.*;
//import javax.swing.*;

public class UFMC_Client {

	private JFrame frame;
	private CDUFrame CDUWindow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UFMC_Client window = new UFMC_Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UFMC_Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CDUWindow = new CDUFrame();
		CDUWindow.setVisible(true);
	}

}
