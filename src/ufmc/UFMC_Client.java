package ufmc;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;


//import java.awt.*;
//import javax.swing.*;

public class UFMC_Client {

	private static IOCPclient clientIOCP;
	private CDUSettings settings;

	static public CDUFrame CDUWindow;

	private JFrame frame;
	private JTextField nTop;
	private JTextField nLeft;
	private JTextField nHeight;
	private JTextField nWidth;
	private JTextField sIOCPServerIp;
	private JTextField nIOCPServerPort;
	private JCheckBox chckbxConnected;

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		// This is all GUI-thread stuff
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

		Thread.sleep(2000); // Give other thread the time to start up.
		// This starts up the TCP thread
		Thread iocpThread = new Thread(clientIOCP);
		iocpThread.start();
		iocpThread.join();
	}

	/**
	 * Create the application. (Constructor)
	 */
	public UFMC_Client() {
		initialize();
		clientIOCP = new IOCPclient();
		clientIOCP.textFrame( CDUWindow);
		clientIOCP.connect(settings.IOCPServerIP, settings.IOCPServerPort);
	}

	/**
	 * Initialize the contents of the main window.
	 */
	private void initialize() {
		settings = new CDUSettings("FSCDU.ini");
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apply();
			}
		});
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

		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeight.setBounds(232, 11, 46, 14);
		tabPos.add(lblHeight);

		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWidth.setBounds(232, 36, 46, 14);
		tabPos.add(lblWidth);

		nTop = new JTextField();
		nTop.setText(settings.CDUTop.toString());
		nTop.setBounds(146, 8, 46, 20);
		tabPos.add(nTop);
		nTop.setColumns(10);

		nLeft = new JTextField();
		nLeft.setText(settings.CDULeft.toString());
		nLeft.setColumns(10);
		nLeft.setBounds(146, 33, 46, 20);
		tabPos.add(nLeft);

		nHeight = new JTextField();
		nHeight.setText(settings.CDUHeight.toString());
		nHeight.setColumns(10);
		nHeight.setBounds(288, 8, 46, 20);
		tabPos.add(nHeight);

		nWidth = new JTextField();
		nWidth.setText(settings.CDUWidth.toString());
		nWidth.setColumns(10);
		nWidth.setBounds(288, 33, 46, 20);
		tabPos.add(nWidth);

		JPanel tabNetwork = new JPanel();
		tabbedPane.addTab("Network", null, tabNetwork, null);
		tabNetwork.setLayout(null);

		JLabel lblIocpServerIp = new JLabel("IOCP Server IP:");
		lblIocpServerIp.setBounds(25, 28, 97, 14);
		tabNetwork.add(lblIocpServerIp);

		JLabel lblIocpServerPort = new JLabel("IOCP Server Port:");
		lblIocpServerPort.setBounds(25, 53, 97, 14);
		tabNetwork.add(lblIocpServerPort);

		sIOCPServerIp = new JTextField();
		sIOCPServerIp.setText(settings.IOCPServerIP);
		sIOCPServerIp.setBounds(132, 25, 86, 20);
		tabNetwork.add(sIOCPServerIp);
		sIOCPServerIp.setColumns(10);

		nIOCPServerPort = new JTextField();
		nIOCPServerPort.setText(settings.IOCPServerPort.toString());
		nIOCPServerPort.setBounds(132, 50, 43, 20);
		tabNetwork.add(nIOCPServerPort);
		nIOCPServerPort.setColumns(10);

		chckbxConnected = new JCheckBox("Connected to IOCP server");
		chckbxConnected.setEnabled(false);
		chckbxConnected.setBounds(25, 74, 163, 23);
		tabNetwork.add(chckbxConnected);

		Rectangle rect = new Rectangle();
		rect.setBounds(settings.CDULeft, settings.CDUTop, settings.CDUWidth, settings.CDUHeight);
		CDUWindow = new CDUFrame(rect);
		CDUWindow.setVisible(true);
	}

	/**
	 * Apply button pressed.
	 */
	private void apply() {
		settings.CDUTop = Integer.parseInt(nTop.getText());
		settings.CDULeft = Integer.parseInt(nLeft.getText());
		settings.CDUHeight = Integer.parseInt(nHeight.getText());
		settings.CDUWidth = Integer.parseInt(nWidth.getText());
		settings.IOCPServerIP = sIOCPServerIp.getText();
		settings.IOCPServerPort = Integer.parseInt(nIOCPServerPort.getText());

		Rectangle rect = new Rectangle();
		rect.setBounds(settings.CDULeft, settings.CDUTop, settings.CDUWidth, settings.CDUHeight);
		CDUWindow.redraw(rect);
		settings.writeInifile();
	}

}
