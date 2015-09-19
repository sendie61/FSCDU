import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JSpinner;


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
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setBounds(182, 237, 89, 23);
		frame.getContentPane().add(btnApply);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 444, 226);
		frame.getContentPane().add(tabbedPane);
		
		JPanel tabLooks = new JPanel();
		tabbedPane.addTab("Looks", null, tabLooks, null);
		
		JPanel tabPos = new JPanel();
		tabbedPane.addTab("Position", null, tabPos, null);
		tabPos.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CDU window");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(10, 11, 70, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		tabPos.add(lblNewLabel);
		
		JLabel lblTop = new JLabel("Top:");
		lblTop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTop.setBounds(90, 11, 46, 14);
		tabPos.add(lblTop);
		
		JLabel lblLeft = new JLabel("Left:");
		lblLeft.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLeft.setBounds(90, 36, 46, 14);
		tabPos.add(lblLeft);
		
		JSpinner nCDUTop = new JSpinner();
		nCDUTop.setBounds(146, 8, 58, 20);
		tabPos.add(nCDUTop);
		
		JSpinner nCDULeft = new JSpinner();
		nCDULeft.setBounds(146, 33, 58, 20);
		tabPos.add(nCDULeft);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeight.setBounds(232, 11, 46, 14);
		tabPos.add(lblHeight);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWidth.setBounds(232, 36, 46, 14);
		tabPos.add(lblWidth);
		
		JSpinner nCDUHeight = new JSpinner();
		nCDUHeight.setBounds(288, 8, 58, 20);
		tabPos.add(nCDUHeight);
		
		JSpinner nCDUWidth = new JSpinner();
		nCDUWidth.setBounds(288, 33, 58, 20);
		tabPos.add(nCDUWidth);
		
		JPanel tabNetwork = new JPanel();
		tabbedPane.addTab("Network", null, tabNetwork, null);
		
		CDUWindow = new CDUFrame();
		CDUWindow.setVisible(true);
	}
}
