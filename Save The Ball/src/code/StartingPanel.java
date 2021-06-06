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
	
	JLabel gameTitleLabel = new JLabel("Save The Ball", SwingConstants.CENTER);
	
	JButton playButton = new JButton("Play");
	JLabel modesLabel = new JLabel("Modes", SwingConstants.CENTER);
	JButton classicButton = new JButton("Classic");
	JButton duoBallsButton = new JButton("Duo Balls");
	JButton ballRainButton = new JButton("Ball Rain");
	JButton colorBallRainButton = new JButton("Color Ball Rain");
	JButton barUpAndDownButton = new JButton("Bar Up & Down");
	JButton inverseMovementButton = new JButton("Inverse Movement");
	
	JButton storeButton = new JButton("Store");
	JButton settingsButton = new JButton("Settings");
	JButton backButton = new JButton("Go Back");
	
	public StartingPanel() {
		new JPanel();
		setLayout(new GridBagLayout());
		setBackground(new Color(64, 224, 208));
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		add(gameTitleLabel);
		gameTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		gameTitleLabel.setForeground(Color.BLUE);
		
		gbc.gridy++;
		
		add(playButton, gbc);
		customizeButton(playButton, true, new Color(65, 105, 225), Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		playButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridy++;
		
		add(storeButton, gbc);
		customizeButton(storeButton, true, new Color(65, 105, 225), Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		storeButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gbc.gridy++;
		
		add(settingsButton, gbc);
		customizeButton(settingsButton, true, new Color(65, 105, 225), Color.WHITE, BorderFactory.createEmptyBorder(2, 10, 2, 10));
		settingsButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		modesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		customizeButton(classicButton, true, new Color(34, 139, 34), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		classicButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		customizeButton(duoBallsButton, true, new Color(65, 105, 225), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		duoBallsButton.setFont(classicButton.getFont());
		
		customizeButton(ballRainButton, true, new Color(0, 139, 139), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		ballRainButton.setFont(classicButton.getFont());
		
		customizeButton(colorBallRainButton, true, new Color(184, 134, 11), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		colorBallRainButton.setFont(classicButton.getFont());
		
		customizeButton(barUpAndDownButton, true, new Color(220, 20, 60), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		barUpAndDownButton.setFont(classicButton.getFont());
		
		customizeButton(inverseMovementButton, true, new Color(47, 79, 79), Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		inverseMovementButton.setFont(classicButton.getFont());
		
		customizeButton(backButton, true, Color.RED, Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
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
