package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	
	StartingPanel startingPanel;
	
	JPanel playPagePanel;
	GridBagConstraints playPagePanelgbc;
	
	JPanel mainPlayPanel;
	
	JPanel headerPlayPanel;
	
	JPanel subPlayPanel;
	
	PlayGamePanel playGamePanel;
	
	StorePanel storePanel;
	
	KeyListener controlKeys;
	MouseAdapter mouseControl;
	
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
		subPlayPanel = new JPanel();
		playGamePanel = new PlayGamePanel(this.getWidth(), this.getHeight());
		storePanel = new StorePanel();
		
		add(startingPanel);
		
		playPagePanel.setLayout(new GridBagLayout());
		playPagePanelgbc.anchor = GridBagConstraints.CENTER;
		playPagePanelgbc.fill = GridBagConstraints.CENTER;
		
		playPagePanelgbc.insets = new Insets(0, 0, 10, 0);
		playPagePanel.add(startingPanel.backButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 1;
		playPagePanel.add(startingPanel.modesLabel, playPagePanelgbc);
		playPagePanelgbc.insets = new Insets(20, 0, 0, 0);
		
		playPagePanelgbc.gridy = 2;
		playPagePanel.add(startingPanel.classicButton, playPagePanelgbc);
		
		playPagePanelgbc.insets = new Insets(10, 0, 0, 0);
		playPagePanelgbc.gridy = 3;
		playPagePanel.add(startingPanel.duoBallsButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 4;
		playPagePanel.add(startingPanel.ballRainButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 5;
		playPagePanel.add(startingPanel.colorBallRainButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 6;
		playPagePanel.add(startingPanel.barUpAndDownButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 7;
		playPagePanel.add(startingPanel.dodgeBallButton, playPagePanelgbc);
		
		mainPlayPanel.setLayout(new BoxLayout(mainPlayPanel, BoxLayout.Y_AXIS));
		mainPlayPanel.setBackground(Color.WHITE);
		
		headerPlayPanel.setLayout(new BoxLayout(headerPlayPanel, BoxLayout.X_AXIS));
		headerPlayPanel.setOpaque(false);
		
		headerPlayPanel.add(playGamePanel.backButton);
		
		headerPlayPanel.add(Box.createHorizontalGlue());
		
		headerPlayPanel.add(playGamePanel.pauseButton);
		
		mainPlayPanel.add(headerPlayPanel);
		
		mainPlayPanel.add(playGamePanel.scoreLabel);
		playGamePanel.scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPlayPanel.add(subPlayPanel);
		subPlayPanel.setBackground(Color.WHITE);
		
		subPlayPanel.add(playGamePanel);
		playGamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		startingPanel.playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(startingPanel);
				add(playPagePanel);
				
				startingPanel.classicButton.setText("Classic - " + playGamePanel.classicModeHighScore);
				startingPanel.duoBallsButton.setText("Duo Balls - " + playGamePanel.duoBallsHighScore);
				startingPanel.ballRainButton.setText("Ball Rain - " + playGamePanel.ballRainHighScore);
				startingPanel.colorBallRainButton.setText("Color Ball Rain - " + playGamePanel.colorBallRainHighScore);
				startingPanel.barUpAndDownButton.setText("Bar Up & Down - " + playGamePanel.barUpAndDownHighScore);
				startingPanel.dodgeBallButton.setText("Dodge Ball" + playGamePanel.dodgeBallModeHighScore);
				
				repaint();
				revalidate();
			}
			
		});
		
		startingPanel.repaint();
		startingPanel.revalidate();
		
		startingPanel.classicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.classicMode = true;
				
				playGamePanel.startGame();
				
				repaint();
				revalidate();
			}
			
		});
		
		startingPanel.duoBallsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.duoBallsMode = true;
				
				playGamePanel.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.ballRainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.ballRainMode = true;
				
				playGamePanel.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.colorBallRainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.colorBallRainMode = true;
				
				playGamePanel.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.barUpAndDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.barUpAndDownMode = true;
				
				playGamePanel.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.dodgeBallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.dodgeBallMode = true;
				
				playGamePanel.startGame();
				
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
		
		startingPanel.storeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(startingPanel);
				add(storePanel);
				
				repaint();
				revalidate();
			}
		});
		
		repaint();
		invalidate();
		
		controlKeys = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(playGamePanel.isPlayingGame && playGamePanel.gamePaused == false) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						playGamePanel.bar.barHorizontalVelocity = -5;
						playGamePanel.actionPerformed(null);
					} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						playGamePanel.bar.barHorizontalVelocity = 5;
						playGamePanel.actionPerformed(null);
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					playGamePanel.bar.barHorizontalVelocity = 0;
				}
				
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					playGamePanel.pauseGame();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		};
		
		addKeyListener(controlKeys);
		
		mouseControl = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(playGamePanel.isPlayingGame && playGamePanel.gamePaused == false) {
					if(e.getX() > playGamePanel.bar.x && e.getX() < playGamePanel.bar.x + playGamePanel.bar.width + 40) {
						playGamePanel.bar.setLocation(e.getX() - playGamePanel.bar.width, playGamePanel.bar.y);
						
						if(playGamePanel.dodgeBallMode) {
							playGamePanel.ball.setLocation(e.getX() - playGamePanel.ball.width, playGamePanel.ball.y);
						}
					}
				}
			}
		};
		
		addMouseListener(mouseControl);
		addMouseMotionListener(mouseControl);
		
		playGamePanel.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGamePanel.remove(playGamePanel.gameOverLabel);
				playGamePanel.remove(playGamePanel.tryAgainButton);
				
				playGamePanel.isPlayingGame = false;
				playGamePanel.gamePaused = false;
				
				if(playGamePanel.ballRainMode || playGamePanel.colorBallRainMode) {
					playGamePanel.balls.clear();
				}
				
				playGamePanel.classicMode = false;
				playGamePanel.duoBallsMode = false;
				playGamePanel.ballRainMode = false;
				playGamePanel.colorBallRainMode = false;
				playGamePanel.barUpAndDownMode = false;
				playGamePanel.dodgeBallMode = false;
				
				playGamePanel.timer.stop();
				
				remove(mainPlayPanel);
				add(startingPanel);
				
				repaint();
				revalidate();
			}
		});
	}
}
