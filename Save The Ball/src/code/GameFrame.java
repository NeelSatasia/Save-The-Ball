package code;

import java.awt.Color;
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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GameFrame extends JFrame {
	
	StartingPanel startingPanel;
	
	JPanel playPagePanel;
	GridBagConstraints playPagePanelgbc;
	
	JPanel mainPlayPanel;
	GridBagConstraints mainPlayPanelgbc;
	
	PlayGamePanel playGamePanel;
	
	StorePanel storePanel;
	
	KeyListener controlKeys;
	MouseAdapter mouseControl;
	
	int previousXPosition;
	
	Point pointer;
	
	public GameFrame() {
		new JFrame();
		setSize(300, 450);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("DaBall");
		
		UIManager.put("Button.disabledText", Color.BLACK);
		
		startingPanel = new StartingPanel();
		playPagePanel = new JPanel();
		playPagePanelgbc = new GridBagConstraints();
		mainPlayPanel = new JPanel();
		mainPlayPanelgbc = new GridBagConstraints();
		playGamePanel = new PlayGamePanel(this.getWidth(), this.getHeight());
		storePanel = new StorePanel();
		
		add(startingPanel);
		
		playPagePanel.setLayout(new GridBagLayout());
		playPagePanel.setBackground(new Color(102, 205, 170));
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
		
		mainPlayPanel.setLayout(new GridBagLayout());
		mainPlayPanel.setBackground(Color.WHITE);
		
		playPagePanelgbc.anchor = GridBagConstraints.CENTER;
		playPagePanelgbc.fill = GridBagConstraints.CENTER;
		
		mainPlayPanel.add(playGamePanel.backButton, mainPlayPanelgbc);
		
		mainPlayPanelgbc.insets = new Insets(5, 0, 5, 0);
		mainPlayPanelgbc.gridy = 1;
		
		mainPlayPanel.add(playGamePanel.scoreLabel, mainPlayPanelgbc);
		
		mainPlayPanelgbc.insets = new Insets(0, 0, 0, 0);
		mainPlayPanelgbc.gridy = 2;
		
		mainPlayPanel.add(playGamePanel, mainPlayPanelgbc);
		
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
		
		storePanel.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(storePanel);
				add(startingPanel);
				
				repaint();
				revalidate();
			}
			
		});
		
		if(storePanel.ballsStorePanel.ballsBought.size() == 1) {
			playGamePanel.ball.ballType = storePanel.ballsStorePanel.ballsBought.get(0);
			playGamePanel.ball2.ballType = playGamePanel.ball.ballType;
			playGamePanel.ball.changeBallType(playGamePanel.ball.ballType);
			playGamePanel.ball2.changeBallType(playGamePanel.ball2.ballType);
		} else {
			
		}
		
		for(int i = 0; i < storePanel.ballsStorePanel.buyBalls.length; i++) {
			if((storePanel.ballsStorePanel.ballsBought.contains(i + 1) && storePanel.ballsStorePanel.ballTypeEquipped != playGamePanel.ball.ballType) || (storePanel.ballsStorePanel.ballsBought.contains(i + 1) == false && playGamePanel.totalCoins >= Integer.parseInt(storePanel.ballsStorePanel.buyBalls[i].getText()))) {
				storePanel.ballsStorePanel.buyBalls[i].setEnabled(true);
				storePanel.ballsStorePanel.buyBalls[i].setBackground(Color.BLACK);
				storePanel.ballsStorePanel.buyBalls[i].setForeground(Color.WHITE);
			} else if(i + 1 != storePanel.ballsStorePanel.ballTypeEquipped) {
				storePanel.ballsStorePanel.buyBalls[i].setEnabled(false);
				storePanel.ballsStorePanel.buyBalls[i].setBackground(new Color(204, 204, 204));
			}
			
			int i2 = i;
			storePanel.ballsStorePanel.buyBalls[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(storePanel.ballsStorePanel.ballsBought.contains(i2 + 1) == false) {
						playGamePanel.totalCoins -= Integer.parseInt(storePanel.ballsStorePanel.buyBalls[i2].getText());
						storePanel.ballsStorePanel.ballsBought.add(i2 + 1);
						storePanel.ballsStorePanel.buyBalls[i2].setText("Use");
						
						for(int j = 0; j < storePanel.ballsStorePanel.buyBalls.length; j++) {
							if(storePanel.ballsStorePanel.ballsBought.contains(j + 1) == false && playGamePanel.totalCoins < Integer.parseInt(storePanel.ballsStorePanel.buyBalls[j].getText())) {
								storePanel.ballsStorePanel.buyBalls[j].setEnabled(false);
								storePanel.ballsStorePanel.buyBalls[j].setBackground(new Color(204, 204, 204));
							}
						}
					} else {
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setEnabled(true);
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setBackground(Color.BLACK);
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setForeground(Color.WHITE);
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setText("Use");
						storePanel.ballsStorePanel.ballTypeEquipped = i2 + 1;
						playGamePanel.ball.ballType = i2 + 1;
						playGamePanel.ball2.ballType = i2 + 1;
						playGamePanel.ball.changeBallType(playGamePanel.ball.ballType);
						playGamePanel.ball2.changeBallType(playGamePanel.ball2.ballType);
						storePanel.ballsStorePanel.buyBalls[i2].setEnabled(false);
						storePanel.ballsStorePanel.buyBalls[i2].setBackground(Color.GREEN);
						storePanel.ballsStorePanel.buyBalls[i2].setText("Using");
					}
				}
			});
		}
		
		for(int i = 0; i < storePanel.barsStorePanel.buyBars.length; i++) {
			if((storePanel.barsStorePanel.barsBought.contains(i + 1) && storePanel.barsStorePanel.barTypeEquipped != playGamePanel.ball.ballType) || (storePanel.barsStorePanel.barsBought.contains(i + 1) == false && playGamePanel.totalCoins >= Integer.parseInt(storePanel.barsStorePanel.buyBars[i].getText()))) {
				storePanel.barsStorePanel.buyBars[i].setEnabled(true);
				storePanel.barsStorePanel.buyBars[i].setBackground(Color.BLACK);
				storePanel.barsStorePanel.buyBars[i].setForeground(Color.WHITE);
			} else if(i + 1 != storePanel.barsStorePanel.barTypeEquipped) {
				storePanel.barsStorePanel.buyBars[i].setEnabled(false);
				storePanel.barsStorePanel.buyBars[i].setBackground(new Color(204, 204, 204));
			}
			
			int i2 = i;
			storePanel.barsStorePanel.buyBars[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(storePanel.ballsStorePanel.ballsBought.contains(i2 + 1) == false) {
						playGamePanel.totalCoins -= Integer.parseInt(storePanel.ballsStorePanel.buyBalls[i2].getText());
						storePanel.ballsStorePanel.ballsBought.add(i2 + 1);
						storePanel.ballsStorePanel.buyBalls[i2].setText("Use");
						
						for(int j = 0; j < storePanel.ballsStorePanel.buyBalls.length; j++) {
							if(storePanel.ballsStorePanel.ballsBought.contains(j + 1) == false && playGamePanel.totalCoins < Integer.parseInt(storePanel.ballsStorePanel.buyBalls[j].getText())) {
								storePanel.ballsStorePanel.buyBalls[j].setEnabled(false);
								storePanel.ballsStorePanel.buyBalls[j].setBackground(new Color(204, 204, 204));
							}
						}
					} else {
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setEnabled(true);
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setBackground(Color.BLACK);
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setForeground(Color.WHITE);
						storePanel.ballsStorePanel.buyBalls[storePanel.ballsStorePanel.ballTypeEquipped - 1].setText("Use");
						storePanel.ballsStorePanel.ballTypeEquipped = i2 + 1;
						playGamePanel.ball.ballType = i2 + 1;
						playGamePanel.ball2.ballType = i2 + 1;
						playGamePanel.ball.changeBallType(playGamePanel.ball.ballType);
						storePanel.ballsStorePanel.buyBalls[i2].setEnabled(false);
						storePanel.ballsStorePanel.buyBalls[i2].setBackground(Color.GREEN);
						storePanel.ballsStorePanel.buyBalls[i2].setText("Using");
					}
				}
			});
		}
		
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
							if(playGamePanel.bar.x + playGamePanel.bar.width + 4 >= playGamePanel.getWidth()) {
								playGamePanel.bar.setLocation(playGamePanel.getWidth() - playGamePanel.bar.width, playGamePanel.bar.y);
							} else {
								playGamePanel.bar.setLocation(playGamePanel.bar.x + 4, playGamePanel.bar.y);
							}
						} else if(e.getX() > previousXPosition) {
							if(playGamePanel.bar.x - 4 <= 0) {
								playGamePanel.bar.setLocation(0, playGamePanel.bar.y);
							} else {
								playGamePanel.bar.setLocation(playGamePanel.bar.x - 4, playGamePanel.bar.y);
							}
						}
						
						previousXPosition = e.getX();
					} else {
						pointer = MouseInfo.getPointerInfo().getLocation();
						SwingUtilities.convertPointFromScreen(pointer, playGamePanel);
						
						if(pointer.x > playGamePanel.bar.x - 40 && pointer.x < playGamePanel.bar.x + playGamePanel.bar.width + 40) {
							if(pointer.x - (playGamePanel.bar.width/2) <= 0) {
								playGamePanel.bar.setLocation(0, playGamePanel.bar.y);
							} else if(pointer.x + (playGamePanel.bar.width/2) >= playGamePanel.getWidth()) {
								playGamePanel.bar.setLocation(playGamePanel.getWidth() - playGamePanel.bar.width, playGamePanel.bar.y);
							} else {
								playGamePanel.bar.setLocation(pointer.x - (playGamePanel.bar.width/2), playGamePanel.bar.y);
							}
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
