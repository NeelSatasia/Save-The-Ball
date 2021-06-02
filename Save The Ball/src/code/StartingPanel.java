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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class StartingPanel extends JPanel {
	
	GridBagConstraints gbc;
	
	JLabel gameTitleLabel = new JLabel("Save The Ball");
	
	JButton playButton = new JButton("Play");
	JLabel modesLabel = new JLabel("Modes", SwingConstants.CENTER);
	JButton classicButton = new JButton("Classic");
	JButton duoBallsButton = new JButton("Duo Balls");
	JButton ballRainButton = new JButton("Ball Rain");
	JButton colorBallRainButton = new JButton("Color Ball Rain");
	JButton switchBarButton = new JButton("Bar Up & Down");
	
	JButton storeButton = new JButton("Store");
	JButton settingsButton = new JButton("Settings");
	JButton backButton = new JButton("Go Back");
	
	public StartingPanel() {
		new JPanel();
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		add(gameTitleLabel);
		gameTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		gbc.gridy++;
		
		add(playButton, gbc);
		customizeButton(playButton, true, Color.BLACK, Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		playButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridy++;
		
		add(storeButton, gbc);
		customizeButton(storeButton, true, Color.BLACK, Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		storeButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gbc.gridy++;
		
		add(settingsButton, gbc);
		customizeButton(settingsButton, true, Color.BLACK, Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		settingsButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		modesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		customizeButton(classicButton, true, new Color(34, 139, 34), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		classicButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		customizeButton(duoBallsButton, true, new Color(65, 105, 225), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		duoBallsButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		customizeButton(ballRainButton, true, new Color(0, 139, 139), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		ballRainButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		customizeButton(colorBallRainButton, true, new Color(0, 128, 128), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		colorBallRainButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		customizeButton(switchBarButton, true, new Color(220, 20, 60), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		switchBarButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		customizeButton(backButton, true, Color.RED, Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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
