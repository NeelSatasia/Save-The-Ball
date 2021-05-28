package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class StartingPanel extends JPanel {
	
	GridBagConstraints gbc;
	
	JLabel gameTitleLabel = new JLabel("Save The Ball");
	
	JButton playButton = new JButton("Play");
	JButton storeButton = new JButton("Store");
	JButton settingsButton = new JButton("Settings");
	
	public StartingPanel() {
		new JPanel();
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(40, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		add(gameTitleLabel);
		gameTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		gbc.gridy = 1;
		
		add(playButton, gbc);
		customizeButton(playButton, true, Color.BLACK, Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		playButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridy = 2;
		
		add(storeButton, gbc);
		customizeButton(storeButton, true, Color.BLACK, Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		storeButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gbc.gridy = 3;
		add(settingsButton, gbc);
		customizeButton(settingsButton, true, Color.BLACK, Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		settingsButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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
