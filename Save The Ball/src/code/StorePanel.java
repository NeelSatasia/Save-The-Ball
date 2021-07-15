package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class StorePanel extends JScrollPane {
	
	JPanel storePanel;
	GridBagConstraints gbc;
	
	JLabel ballsLabel = new JLabel("Balls");
	JScrollPane ballsStoreSp;
	BallsStorePanel ballsStorePanel;
	
	JLabel barsLabel = new JLabel("Bars");
	JScrollPane barsStoreSp;
	BarsStorePanel barsStorePanel;
	
	JButton backButton = new JButton("Go Back");
	
	int totalCoins;
	JLabel totalCoinsLabel = new JLabel("Coins: " + totalCoins, SwingConstants.CENTER);
	
	public StorePanel() {
		storePanel = new JPanel();
		storePanel.setLayout(new GridBagLayout());
		storePanel.setBackground(Color.WHITE);
		
		new JScrollPane();
		add(storePanel);
		setViewportView(storePanel);
		validate();
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(null);
		
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(15, 0, 10, 0);
		
		storePanel.add(backButton, gbc);
		customizeButton(backButton, true, Color.RED, Color.WHITE, BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		totalCoinsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		totalCoinsLabel.setForeground(Color.BLACK);
		
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.gridy = 1;
		storePanel.add(totalCoinsLabel, gbc);
		
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridy = 2;
		storePanel.add(ballsLabel, gbc);
		ballsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		ballsStoreSp = new JScrollPane();
		ballsStorePanel = new BallsStorePanel();
		ballsStoreSp.add(ballsStorePanel);
		ballsStoreSp.setPreferredSize(new Dimension(160, 200));
		ballsStoreSp.setViewportView(ballsStorePanel);
		ballsStoreSp.validate();
		ballsStoreSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ballsStoreSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ballsStoreSp.setBorder(null);
		
		gbc.insets = new Insets(0, 0, 20, 0);
		gbc.gridy = 3;
		
		storePanel.add(ballsStoreSp, gbc);
		
		barsStoreSp = new JScrollPane();
		barsStorePanel = new BarsStorePanel();
		barsStoreSp.add(barsStorePanel);
		barsStoreSp.setPreferredSize(new Dimension(200, 200));
		barsStoreSp.setViewportView(barsStorePanel);
		barsStoreSp.validate();
		barsStoreSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		barsStoreSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		barsStoreSp.setBorder(null);
		
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridy = 4;
		
		storePanel.add(barsLabel, gbc);
		barsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.gridy = 5;
		
		storePanel.add(barsStoreSp, gbc);
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
