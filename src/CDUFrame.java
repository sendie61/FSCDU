import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

//
// CDUFrame represents the actual content of the  FMC module 
//
public class CDUFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel[] line;
	private boolean isLargeFont[] = { true, false, true, false, true, false,
			true, false, true, false, true, false, true, true };
	private Font largeFont;
	private Font smallFont;
	
	/**
	 * Create the frame.
	 */
	public CDUFrame(Rectangle position) {
		initialize(position);
	}

	private void initialize(Rectangle position) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(position);
		setUndecorated(true); // No title bar nor border
		contentPane = new JPanel();
		contentPane.setForeground(new Color(50, 205, 50));
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblEmptyLabel = new JLabel("");
		contentPane.add(lblEmptyLabel);

		File large_font_file = new File("src\\AEROWINXFMCLARGE.TTF");
		File small_font_file = new File("src\\AEROWINXFMCSMALL.TTF");
		try {
			largeFont = Font.createFont(Font.TRUETYPE_FONT, large_font_file);
			smallFont = Font.createFont(Font.TRUETYPE_FONT, small_font_file);
		} catch (FontFormatException | IOException ex) {
			return;
		}
		int i = 0;
		line = new JLabel[20];
		while (i < 14) {
			line[i] = new JLabel("0123456789012345678901234");
			line[i].setHorizontalAlignment(SwingConstants.CENTER);
			if (isLargeFont[i]) {
				line[i].setFont(largeFont.deriveFont(position.width / 16f));
			} else {
				line[i].setFont(smallFont.deriveFont(position.width / 16f));
			}
			line[i].setForeground(new Color(0, 204, 0));
			contentPane.add(line[i]);
			i++;
		}
		JLabel lblEmptyLabel2 = new JLabel("");
		contentPane.add(lblEmptyLabel2);
	}
	
	public void redraw(Rectangle rect) {
		int i = 0;
		while (i < 14) {
			if (isLargeFont[i]) {
				line[i].setFont(largeFont.deriveFont(rect.width / 16f));
			} else {
				line[i].setFont(smallFont.deriveFont(rect.width / 16f));
			}
			i++;
		}
		this.setBounds(rect);
	}
}
