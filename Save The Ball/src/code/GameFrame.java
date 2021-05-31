package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GameFrame extends JFrame {
	
	StartingPanel startingPanel;
	
	JPanel playPagePanel;
	GridBagConstraints playPagePanelgbc;
	
	JPanel mainPlayPanel;
	
	JPanel headerPlayPanel;
	
	JPanel subPlayPanel1;
	GridBagConstraints subPlayPanel1gbc;
	
	PlayGamePanel subPlayPanel2;
	
	KeyListener controlKeys;
	MouseAdapter mouseControl;
	
	boolean isDraggable = false;
	
	public GameFrame() {
		new JFrame();
		setSize(300, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Save The Ball");
		
		startingPanel = new StartingPanel();
		playPagePanel = new JPanel();
		playPagePanelgbc = new GridBagConstraints();
		headerPlayPanel = new JPanel();
		mainPlayPanel = new JPanel();
		subPlayPanel1 = new JPanel();
		subPlayPanel1gbc = new GridBagConstraints();
		subPlayPanel2 = new PlayGamePanel(this.getWidth(), this.getHeight());
		
		add(startingPanel);
		
		playPagePanel.setLayout(new GridBagLayout());
		playPagePanelgbc.anchor = GridBagConstraints.CENTER;
		playPagePanelgbc.fill = GridBagConstraints.CENTER;
		playPagePanelgbc.insets = new Insets(40, 0, 0, 0);
		
		playPagePanel.add(startingPanel.modesLabel, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 1;
		playPagePanel.add(startingPanel.classicButton, playPagePanelgbc);
		
		playPagePanelgbc.insets = new Insets(10, 0, 0, 0);
		playPagePanelgbc.gridy = 2;
		playPagePanel.add(startingPanel.duoBallsButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 3;
		playPagePanel.add(startingPanel.ballRainButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 4;
		playPagePanel.add(startingPanel.colorBallRainButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 5;
		playPagePanel.add(startingPanel.switchBarButton, playPagePanelgbc);
		
		playPagePanelgbc.insets = new Insets(40, 0, 0, 0);
		playPagePanelgbc.gridy = 6;
		playPagePanel.add(startingPanel.backButton, playPagePanelgbc);
		
		mainPlayPanel.setLayout(new BoxLayout(mainPlayPanel, BoxLayout.Y_AXIS));
		mainPlayPanel.setBackground(Color.WHITE);
		
		headerPlayPanel.setLayout(new BoxLayout(headerPlayPanel, BoxLayout.X_AXIS));
		headerPlayPanel.setOpaque(false);
		
		headerPlayPanel.add(subPlayPanel2.backButton);
		
		headerPlayPanel.add(Box.createHorizontalGlue());
		
		headerPlayPanel.add(subPlayPanel2.pauseButton);
		
		mainPlayPanel.add(headerPlayPanel);
		
		subPlayPanel1.setLayout(new GridBagLayout());
		subPlayPanel1gbc.anchor = GridBagConstraints.CENTER;
		subPlayPanel1gbc.fill = GridBagConstraints.BOTH;
		mainPlayPanel.add(subPlayPanel1);
		subPlayPanel1.setBackground(Color.WHITE);
		
		subPlayPanel1.add(subPlayPanel2, subPlayPanel1gbc);
		
		startingPanel.playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(startingPanel);
				add(playPagePanel);
				
				repaint();
				revalidate();
			}
			
		});
		
		startingPanel.repaint();
		startingPanel.revalidate();
		
		startingPanel.classicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				subPlayPanel2.classicMode = true;
				
				subPlayPanel2.startGame();
				
				repaint();
				revalidate();
			}
			
		});
		
		startingPanel.duoBallsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				subPlayPanel2.duoBallsMode = true;
				
				subPlayPanel2.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.ballRainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				subPlayPanel2.ballRainMode = true;
				
				subPlayPanel2.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.colorBallRainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				subPlayPanel2.colorBallRainMode = true;
				
				subPlayPanel2.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(startingPanel);
				
				repaint();
				revalidate();
			}
		});
		
		repaint();
		invalidate();
		
		controlKeys = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(subPlayPanel2.isPlayingGame && subPlayPanel2.gamePaused == false) {
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
				
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					subPlayPanel2.pauseGame();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		};
		
		addKeyListener(controlKeys);
		
		mouseControl = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(subPlayPanel2.isPlayingGame && subPlayPanel2.gamePaused == false) {
					if(e.getX() > subPlayPanel2.bar.x && e.getX() < subPlayPanel2.bar.x + subPlayPanel2.bar.width + 40) {
						subPlayPanel2.bar.x = e.getX() - subPlayPanel2.bar.width;
						isDraggable = true;
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				isDraggable = false;
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(isDraggable) {
					subPlayPanel2.bar.x = e.getX() - subPlayPanel2.bar.width;
				}
			}
		};
		
		addMouseListener(mouseControl);
		addMouseMotionListener(mouseControl);
		
		subPlayPanel2.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subPlayPanel2.remove(subPlayPanel2.gameOverLabel);
				subPlayPanel2.remove(subPlayPanel2.tryAgainButton);
				
				subPlayPanel2.isPlayingGame = false;
				subPlayPanel2.gamePaused = false;
				
				if(subPlayPanel2.ballRainMode || subPlayPanel2.colorBallRainMode) {
					subPlayPanel2.balls.clear();
				}
				
				subPlayPanel2.classicMode = false;
				subPlayPanel2.duoBallsMode = false;
				subPlayPanel2.ballRainMode = false;
				subPlayPanel2.colorBallRainMode = false;
				subPlayPanel2.switchBarMode = false;
				
				subPlayPanel2.timer.stop();
				
				remove(mainPlayPanel);
				add(startingPanel);
				
				repaint();
				revalidate();
			}
		});
	}
}
