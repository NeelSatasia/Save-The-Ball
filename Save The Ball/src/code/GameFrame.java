package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameFrame extends JFrame {
	
	StartingPanel startingPanel;
	
	JPanel mainPlayPanel;
	
	JPanel headerPlayPanel;
	
	JPanel subPlayPanel1;
	GridBagConstraints gbc;
	
	PlayGamePanel subPlayPanel2;
	
	JButton backButton = new JButton("");
	
	KeyListener controlKeys;
	
	public GameFrame() {
		new JFrame();
		setSize(300, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Save The Ball");
		
		startingPanel = new StartingPanel();
		headerPlayPanel = new JPanel();
		mainPlayPanel = new JPanel();
		subPlayPanel1 = new JPanel();
		gbc = new GridBagConstraints();
		subPlayPanel2 = new PlayGamePanel(this.getWidth(), this.getHeight());
		
		add(startingPanel);
		
		mainPlayPanel.setLayout(new BoxLayout(mainPlayPanel, BoxLayout.Y_AXIS));
		mainPlayPanel.setBackground(Color.WHITE);
		
		subPlayPanel2 = new PlayGamePanel(this.getWidth(), this.getHeight());
		
		subPlayPanel2.add(backButton);
		backButton.setBounds(2, 1, 32, 32);
		customizeButton(backButton, true, Color.WHITE, Color.WHITE, null);
		ImageIcon backArrowIcon = new ImageIcon("C:\\Users\\Vipul\\git\\Save-The-Ball\\Save The Ball\\src\\BackArrow.png");
		backButton.setIcon(backArrowIcon);
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		backButton.setOpaque(false);
		
		subPlayPanel1.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		mainPlayPanel.add(subPlayPanel1);
		subPlayPanel1.setBackground(Color.WHITE);
		
		subPlayPanel1.add(subPlayPanel2, gbc);
		
		startingPanel.playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(startingPanel);
				add(mainPlayPanel);
				
				subPlayPanel2.startGame();
				
				repaint();
				revalidate();
			}
			
		});
		
		startingPanel.repaint();
		startingPanel.revalidate();
		
		repaint();
		invalidate();
		
		subPlayPanel2.repaint();
		subPlayPanel2.revalidate();
		
		controlKeys = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(subPlayPanel2.gameStarted && subPlayPanel2.gameOver == false && subPlayPanel2.gamePaused == false) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						subPlayPanel2.barHorizontalVelocity = -5;
						subPlayPanel2.actionPerformed(null);
					} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						subPlayPanel2.barHorizontalVelocity = 5;
						subPlayPanel2.actionPerformed(null);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					subPlayPanel2.barHorizontalVelocity = 0;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		};
		
		addKeyListener(controlKeys);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subPlayPanel2.remove(subPlayPanel2.gameOverLabel);
				subPlayPanel2.remove(subPlayPanel2.restartButton);
				
				subPlayPanel2.gameOver = true;
				subPlayPanel2.gameStarted = false;
				subPlayPanel2.gamePaused = false;
				subPlayPanel2.timer.stop();
				
				remove(mainPlayPanel);
				add(startingPanel);
				
				repaint();
				revalidate();
			}
		});
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
