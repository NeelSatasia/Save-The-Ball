package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameFrame extends JFrame {
	
	JPanel mainPanel = new JPanel();
	
	JPanel topPanel = new JPanel();
	
	JPanel playPanel1 = new JPanel();
	GridBagConstraints gbc = new GridBagConstraints();
	
	PlayGamePanel newPlayPanel;
	
	JButton backButton = new JButton("Back");
	
	KeyListener controlKeys;
	
	public GameFrame() {
		new JFrame();
		setSize(300, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Game");
		
		add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);
		
		mainPanel.add(backButton);
		customizeButton(backButton, true, Color.BLACK, Color.WHITE, null);
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		newPlayPanel = new PlayGamePanel(this.getWidth(), this.getHeight());
		
		playPanel1.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		mainPanel.add(playPanel1);
		playPanel1.setBackground(Color.WHITE);
		
		playPanel1.add(newPlayPanel, gbc);
		
		newPlayPanel.repaint();
		newPlayPanel.revalidate();
		
		controlKeys = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					newPlayPanel.barHorizontalVelocity = -5;
					newPlayPanel.actionPerformed(null);
				} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					newPlayPanel.barHorizontalVelocity = 5;
					newPlayPanel.actionPerformed(null);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					newPlayPanel.barHorizontalVelocity = 0;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		};
		
		addKeyListener(controlKeys);
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
