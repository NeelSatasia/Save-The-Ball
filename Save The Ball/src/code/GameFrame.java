package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
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
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame {
	
	StartingPanel startingPanel;
	
	JPanel playPagePanel;
	GridBagConstraints playPagePanelgbc;
	
	JPanel mainPlayPanel;
	
	JPanel headerPlayPanel;
	
	JPanel subPlayPanel;
	GridBagConstraints subPlayPanelgbc;
	
	PlayGamePanel playGamePanel;
	
	StorePanel storePanel;
	
	KeyListener controlKeys;
	MouseAdapter mouseControl;
	
	int previousXPosition;
	
	Point pointer;
	
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
		subPlayPanelgbc = new GridBagConstraints();
		playGamePanel = new PlayGamePanel(this.getWidth(), this.getHeight());
		storePanel = new StorePanel();
		
		add(startingPanel);
		
		playPagePanel.setLayout(new GridBagLayout());
		playPagePanel.setBackground(new Color(230, 230, 250));
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
		playPagePanel.add(startingPanel.inverseMovementButton, playPagePanelgbc);
		
		mainPlayPanel.setLayout(new BoxLayout(mainPlayPanel, BoxLayout.Y_AXIS));
		mainPlayPanel.setBackground(Color.WHITE);
		
		headerPlayPanel.setLayout(new BoxLayout(headerPlayPanel, BoxLayout.X_AXIS));
		headerPlayPanel.setOpaque(false);
		
		headerPlayPanel.add(playGamePanel.backButton);
		headerPlayPanel.add(Box.createHorizontalGlue());
		
		mainPlayPanel.add(headerPlayPanel);
		
		mainPlayPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		mainPlayPanel.add(playGamePanel.scoreLabel);
		playGamePanel.scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPlayPanel.add(subPlayPanel);
		subPlayPanel.setLayout(new GridBagLayout());
		subPlayPanel.setBackground(Color.WHITE);
		subPlayPanelgbc.anchor = GridBagConstraints.CENTER;
		subPlayPanelgbc.fill = GridBagConstraints.CENTER;
		
		subPlayPanel.add(playGamePanel, subPlayPanelgbc);
		
		startingPanel.playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(startingPanel);
				add(playPagePanel);
				
				startingPanel.classicButton.setText("Classic - " + playGamePanel.classicModeHighScore);
				startingPanel.duoBallsButton.setText("Duo Balls - " + playGamePanel.duoBallsHighScore);
				startingPanel.ballRainButton.setText("Ball Rain - " + playGamePanel.ballRainHighScore);
				startingPanel.colorBallRainButton.setText("Color Ball Rain - " + playGamePanel.colorBallRainHighScore);
				startingPanel.barUpAndDownButton.setText("Bar Up & Down - " + playGamePanel.barUpAndDownHighScore);
				startingPanel.inverseMovementButton.setText("Inverse Movement - " + playGamePanel.inverseMovementModeHighScore);
				
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
		
		startingPanel.inverseMovementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.inverseMovementMode = true;
				
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
						if(playGamePanel.inverseMovementMode) {
							playGamePanel.bar.barHorizontalVelocity = 5;
						} else {
							playGamePanel.bar.barHorizontalVelocity = -5;
						}
						
						playGamePanel.actionPerformed(null);
					} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if(playGamePanel.inverseMovementMode) {
							playGamePanel.bar.barHorizontalVelocity = -5;
						} else {
							playGamePanel.bar.barHorizontalVelocity = 5;
						}
						
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
			public void mouseClicked(MouseEvent e) {
				playGamePanel.pauseGame();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(playGamePanel.isPlayingGame && playGamePanel.gamePaused == false) {
					if(playGamePanel.inverseMovementMode) {
						if(e.getX() < previousXPosition) {
							playGamePanel.bar.setLocation(playGamePanel.bar.x + 4, playGamePanel.bar.y);
						} else if(e.getX() > previousXPosition) {
							playGamePanel.bar.setLocation(playGamePanel.bar.x - 4, playGamePanel.bar.y);
						}
						
						previousXPosition = e.getX();
					} else {
						pointer = MouseInfo.getPointerInfo().getLocation();
						SwingUtilities.convertPointFromScreen(pointer, playGamePanel);
						
						if(pointer.x > playGamePanel.bar.x - 40 && pointer.x < playGamePanel.bar.x + playGamePanel.bar.width + 40) {
							playGamePanel.bar.setLocation(pointer.x - (playGamePanel.bar.width/2), playGamePanel.bar.y);
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
				playGamePanel.inverseMovementMode = false;
				
				playGamePanel.timer.stop();
				
				remove(mainPlayPanel);
				add(startingPanel);
				
				repaint();
				revalidate();
			}
		});
	}
}
