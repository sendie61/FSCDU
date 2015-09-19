import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

//
// CDUFrame represents the actual content of the  FMC module
//
public class CDUFrame extends JFrame {

	private JPanel contentPane;
	private JLabel[] line; 
	private boolean largeFont[]={true,false,true,false,true,false,true,false,true,false,true,false,true,true};
	/**
	 * Create the frame.
	 */
	public CDUFrame() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		setUndecorated(true); // No title bar nor border
		contentPane = new JPanel();
		contentPane.setForeground(new Color(50, 205, 50));
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblEmptyLabel = new JLabel("");
		contentPane.add(lblEmptyLabel);
		
		int i=0;		
		line = new JLabel[20];
		while (i < 14){
			line[i] = new JLabel("0123456789012345678901234");
			line[i].setHorizontalAlignment(SwingConstants.CENTER);
			if (largeFont[i]){
				line[i].setFont(new Font("AEROWINX FMC LARGE", Font.PLAIN, 400/16));
			}
			else{
				line[i].setFont(new Font("AEROWINX FMC SMALL", Font.PLAIN, 400/16));
			}
			line[i].setForeground(new Color(0, 204, 0));
			contentPane.add(line[i]);
			i++;
		}
		JLabel lblEmptyLabel2 = new JLabel("");
		contentPane.add(lblEmptyLabel2);
	}

	public void redraw( Rectangle rect) {
		int i=0;
		while (i < 14){
			if (largeFont[i]){
				line[i].setFont(new Font("AEROWINX FMC LARGE", Font.PLAIN, rect.width/16));
			}
			else{
				line[i].setFont(new Font("AEROWINX FMC SMALL", Font.PLAIN, rect.width/16));
			}
			i++;
		}
		this.setBounds(rect);
	}
}
