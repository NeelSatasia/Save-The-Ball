package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class StorePanel extends JPanel {
	
	GridBagConstraints gbc;
	
	JScrollPane storeSp;
	
	JLabel ballsLabel = new JLabel("Balls");
	JScrollPane ballsStoreSp;
	BallsStorePanel ballsStorePanel;
	
	JButton backButton = new JButton("Go Back");
	
	public StorePanel() {
		new JPanel();
		setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 10, 0);
		
		add(backButton, gbc);
		customizeButton(backButton, true, Color.RED, Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridy = 1;
		
		add(ballsLabel, gbc);
		ballsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		ballsStoreSp = new JScrollPane();
		ballsStorePanel = new BallsStorePanel();
		ballsStoreSp.add(ballsStorePanel);
		ballsStoreSp.setPreferredSize(new Dimension(160, 200));
		ballsStoreSp.setViewportView(ballsStorePanel);
		ballsStoreSp.validate();
		ballsStoreSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ballsStoreSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		gbc.gridy = 2;
		
		add(ballsStoreSp, gbc);
	}
	
	public void customizeButton(JButton button, boolean enable, Color buttonColor, Color textColor, Border border) {
		button.setEnabled(enable);
		button.setBorder(border);
		button.setBackground(buttonColor);
		if(enable) {
			button.setForeground(textColor);
		}
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
	}
}
