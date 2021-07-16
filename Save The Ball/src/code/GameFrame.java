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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	File file = new File("PongBall.txt");
	
	public GameFrame() {
		new JFrame();
		setSize(300, 450);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Pong Ball");
		
		UIManager.put("Button.disabledText", Color.BLACK);
		
		startingPanel = new StartingPanel();
		playPagePanel = new JPanel();
		playPagePanelgbc = new GridBagConstraints();
		mainPlayPanel = new JPanel();
		mainPlayPanelgbc = new GridBagConstraints();
		playGamePanel = new PlayGamePanel(this.getWidth(), this.getHeight());
		storePanel = new StorePanel();
		
		loadData();
		
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
		playPagePanel.add(startingPanel.barUpAndDownButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 6;
		playPagePanel.add(startingPanel.inverseMovementButton, playPagePanelgbc);
		
		playPagePanelgbc.gridy = 7;
		playPagePanel.add(startingPanel.invisibleBarButton, playPagePanelgbc);
		
		mainPlayPanel.setLayout(new GridBagLayout());
		mainPlayPanel.setBackground(Color.WHITE);
		
		mainPlayPanelgbc.anchor = GridBagConstraints.CENTER;
		mainPlayPanelgbc.fill = GridBagConstraints.CENTER;
		
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
				
				startingPanel.classicButton.setText("Classic - " + playGamePanel.classicHighScore);
				startingPanel.duoBallsButton.setText("Duo Balls - " + playGamePanel.duoBallsHighScore);
				startingPanel.ballRainButton.setText("Ball Rain - " + playGamePanel.ballRainHighScore);
				startingPanel.barUpAndDownButton.setText("Bar Up & Down - " + playGamePanel.duoBarsHighScore);
				startingPanel.inverseMovementButton.setText("Inverse Movement - " + playGamePanel.inverseMovementHighScore);
				startingPanel.invisibleBarButton.setText("Invisible Bar - " + playGamePanel.invisibleBarHighScore);
				
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
				playGamePanel.isInPlayGamePanel = true;
				
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
				playGamePanel.isInPlayGamePanel = true;
				
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
				playGamePanel.isInPlayGamePanel = true;
				
				playGamePanel.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.barUpAndDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.duoBarsMode = true;
				playGamePanel.isInPlayGamePanel = true;
				
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
				playGamePanel.isInPlayGamePanel = true;
				
				playGamePanel.startGame();
				
				repaint();
				revalidate();
			}
		});
		
		startingPanel.invisibleBarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(playPagePanel);
				add(mainPlayPanel);
				
				playGamePanel.invisibleBarMode = true;
				playGamePanel.isInPlayGamePanel = true;
				
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
				
				storePanel.totalCoins = playGamePanel.totalCoins;
				storePanel.totalCoinsLabel.setText("Coins: " + storePanel.totalCoins);
				
				for(int i = 0; i < storePanel.ballsStorePanel.buyBalls.length; i++) {
					if(storePanel.ballsStorePanel.ballsBought.contains(i + 1)) {
						if(i + 1 == playGamePanel.ball.ballType) {
							storePanel.ballsStorePanel.buyBalls[i].setText("Using");
							storePanel.ballsStorePanel.buyBalls[i].setEnabled(false);
							storePanel.ballsStorePanel.buyBalls[i].setBackground(Color.GREEN);
						} else {
							storePanel.ballsStorePanel.buyBalls[i].setText("Use");
						}
					} else {
						storePanel.ballsStorePanel.buyBalls[i].setText("50");
					}
					
					if((storePanel.ballsStorePanel.ballsBought.contains(i + 1) && i + 1 != playGamePanel.ball.ballType) || (storePanel.ballsStorePanel.ballsBought.contains(i + 1) == false && playGamePanel.totalCoins >= Integer.parseInt(storePanel.ballsStorePanel.buyBalls[i].getText()))) {
						storePanel.ballsStorePanel.buyBalls[i].setEnabled(true);
						storePanel.ballsStorePanel.buyBalls[i].setBackground(Color.BLACK);
						storePanel.ballsStorePanel.buyBalls[i].setForeground(Color.WHITE);
					} else if(i + 1 != playGamePanel.ball.ballType) {
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
									if(storePanel.ballsStorePanel.ballsBought.contains(j + 1) == false && playGamePanel.totalCoins >= Integer.parseInt(storePanel.ballsStorePanel.buyBalls[j].getText())) {
										storePanel.ballsStorePanel.buyBalls[j].setEnabled(true);
										storePanel.ballsStorePanel.buyBalls[j].setBackground(Color.BLACK);
										storePanel.ballsStorePanel.buyBalls[j].setForeground(Color.WHITE);
									}
								}
							} else {
								storePanel.ballsStorePanel.buyBalls[playGamePanel.ball.ballType - 1].setEnabled(true);
								storePanel.ballsStorePanel.buyBalls[playGamePanel.ball.ballType - 1].setBackground(Color.BLACK);
								storePanel.ballsStorePanel.buyBalls[playGamePanel.ball.ballType - 1].setForeground(Color.WHITE);
								storePanel.ballsStorePanel.buyBalls[playGamePanel.ball.ballType - 1].setText("Use");
								playGamePanel.ball.ballType = i2 + 1;
								playGamePanel.ball2.ballType = i2 + 1;
								playGamePanel.ball.changeBallType(playGamePanel.ball.ballType);
								playGamePanel.ball2.changeBallType(playGamePanel.ball2.ballType);
								storePanel.ballsStorePanel.buyBalls[i2].setEnabled(false);
								storePanel.ballsStorePanel.buyBalls[i2].setBackground(Color.GREEN);
								storePanel.ballsStorePanel.buyBalls[i2].setText("Using");
							}
							
							saveData();
						}
					});
				}
				
				for(int i = 0; i < storePanel.barsStorePanel.buyBars.length; i++) {
					if(storePanel.barsStorePanel.barsBought.contains(i + 1)) {
						if(i + 1 == playGamePanel.bar.barType) {
							storePanel.barsStorePanel.buyBars[i].setText("Using");
							storePanel.barsStorePanel.buyBars[i].setEnabled(false);
							storePanel.barsStorePanel.buyBars[i].setBackground(Color.GREEN);
						} else {
							storePanel.barsStorePanel.buyBars[i].setText("Use");
						}
					} else {
						storePanel.barsStorePanel.buyBars[i].setText("75");
					}
					
					if((storePanel.barsStorePanel.barsBought.contains(i + 1) && i + 1 != playGamePanel.bar.barType) || (storePanel.barsStorePanel.barsBought.contains(i + 1) == false && playGamePanel.totalCoins >= Integer.parseInt(storePanel.barsStorePanel.buyBars[i].getText()))) {
						storePanel.barsStorePanel.buyBars[i].setEnabled(true);
						storePanel.barsStorePanel.buyBars[i].setBackground(Color.BLACK);
						storePanel.barsStorePanel.buyBars[i].setForeground(Color.WHITE);
					} else if(i + 1 != playGamePanel.bar.barType) {
						storePanel.barsStorePanel.buyBars[i].setEnabled(false);
						storePanel.barsStorePanel.buyBars[i].setBackground(new Color(204, 204, 204));
					}
					
					int i2 = i;
					storePanel.barsStorePanel.buyBars[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(storePanel.barsStorePanel.barsBought.contains(i2 + 1) == false) {
								playGamePanel.totalCoins -= Integer.parseInt(storePanel.barsStorePanel.buyBars[i2].getText());
								storePanel.barsStorePanel.barsBought.add(i2 + 1);
								storePanel.barsStorePanel.buyBars[i2].setText("Use");
								
								for(int j = 0; j < storePanel.barsStorePanel.buyBars.length; j++) {
									if(storePanel.barsStorePanel.barsBought.contains(j + 1) == false && playGamePanel.totalCoins >= Integer.parseInt(storePanel.barsStorePanel.buyBars[j].getText())) {
										storePanel.barsStorePanel.buyBars[j].setEnabled(true);
										storePanel.barsStorePanel.buyBars[j].setBackground(Color.BLACK);
										storePanel.barsStorePanel.buyBars[j].setForeground(Color.WHITE);
									}
								}
							} else {
								storePanel.barsStorePanel.buyBars[playGamePanel.bar.barType - 1].setEnabled(true);
								storePanel.barsStorePanel.buyBars[playGamePanel.bar.barType - 1].setBackground(Color.BLACK);
								storePanel.barsStorePanel.buyBars[playGamePanel.bar.barType - 1].setForeground(Color.WHITE);
								storePanel.barsStorePanel.buyBars[playGamePanel.bar.barType - 1].setText("Use");
								playGamePanel.bar.barType = i2 + 1;
								playGamePanel.bar.changeBarType(playGamePanel.bar.barType);
								storePanel.barsStorePanel.buyBars[i2].setEnabled(false);
								storePanel.barsStorePanel.buyBars[i2].setBackground(Color.GREEN);
								storePanel.barsStorePanel.buyBars[i2].setText("Using");
							}
							
							saveData();
						}
					});
				}
				
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
							
							if(playGamePanel.duoBarsMode) {
								if(pointer.x - (playGamePanel.bar2.width/2) <= 0) {
									playGamePanel.bar2.setLocation(0, playGamePanel.bar2.y);
								} else if(pointer.x + (playGamePanel.bar2.width/2) >= playGamePanel.getWidth()) {
									playGamePanel.bar2.setLocation(playGamePanel.getWidth() - playGamePanel.bar2.width, playGamePanel.bar2.y);
								} else {
									playGamePanel.bar2.setLocation(pointer.x - (playGamePanel.bar2.width/2), playGamePanel.bar2.y);
								}
							}
						}
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(playGamePanel.isInPlayGamePanel) {
					saveData();
				}
			}
		};
		
		addMouseListener(mouseControl);
		addMouseMotionListener(mouseControl);
		
		playGamePanel.backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playGamePanel.remove(playGamePanel.gameOverLabel);
				playGamePanel.remove(playGamePanel.tryAgainButton);
				
				playGamePanel.isInPlayGamePanel = false;
				playGamePanel.isPlayingGame = false;
				playGamePanel.gamePaused = false;
				
				if(playGamePanel.ballRainMode) {
					playGamePanel.balls.clear();
				}
				
				playGamePanel.classicMode = false;
				playGamePanel.duoBallsMode = false;
				playGamePanel.ballRainMode = false;
				playGamePanel.duoBarsMode = false;
				playGamePanel.inverseMovementMode = false;
				playGamePanel.invisibleBarMode = false;
				
				playGamePanel.timer.stop();
				
				remove(mainPlayPanel);
				add(startingPanel);
				
				repaint();
				revalidate();
			}
		});
	}
	
	public void saveData() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("PongBall.txt"));
			
			writer.write(playGamePanel.ball.ballType + "\n");
			
			writer.append(playGamePanel.bar.barType + "\n");
			
			writer.append(playGamePanel.totalCoins + "\n");
			
			writer.append(playGamePanel.classicHighScore + "\n");
			writer.append(playGamePanel.duoBallsHighScore + "\n");
			writer.append(playGamePanel.ballRainHighScore + "\n");
			writer.append(playGamePanel.duoBarsHighScore + "\n");
			writer.append(playGamePanel.inverseMovementHighScore + "\n");
			writer.append(playGamePanel.invisibleBarHighScore + "\n");
			
			for(int i = 0; i < storePanel.ballsStorePanel.ballsBought.size(); i++) {
				if(i + 1 < storePanel.ballsStorePanel.ballsBought.size()) {
					writer.append(storePanel.ballsStorePanel.ballsBought.get(i) + " ");
				} else {
					writer.append(storePanel.ballsStorePanel.ballsBought.get(i) + "\n");
				}
			}
			
			for(int i = 0; i < storePanel.barsStorePanel.barsBought.size(); i++) {
				if(i + 1 < storePanel.barsStorePanel.barsBought.size()) {
					writer.append(storePanel.barsStorePanel.barsBought.get(i) + " ");
				} else {
					writer.append(storePanel.barsStorePanel.barsBought.get(i) + "");
				}
			}
			
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadData() {
		try {
			if(file.exists()) {
				Scanner reader = new Scanner(new FileReader(file));
				
				ArrayList<String> dataList = new ArrayList<String>();
				while(reader.hasNextLine()) {
					dataList.add(reader.nextLine());
				}
				
				playGamePanel.ball.ballType = Integer.parseInt(dataList.get(0));
				playGamePanel.ball2.ballType = Integer.parseInt(dataList.get(0));
				
				playGamePanel.bar.barType = Integer.parseInt(dataList.get(1));
				playGamePanel.bar2.barType = Integer.parseInt(dataList.get(1));
				
				playGamePanel.totalCoins = Integer.parseInt(dataList.get(2));
				playGamePanel.totalCoinsLabel.setText("Coins: " + playGamePanel.totalCoins);
				
				playGamePanel.classicHighScore = Integer.parseInt(dataList.get(3));
				playGamePanel.duoBallsHighScore = Integer.parseInt(dataList.get(4));
				playGamePanel.ballRainHighScore = Integer.parseInt(dataList.get(5));
				playGamePanel.duoBarsHighScore = Integer.parseInt(dataList.get(6));
				playGamePanel.inverseMovementHighScore = Integer.parseInt(dataList.get(7));
				playGamePanel.invisibleBarHighScore = Integer.parseInt(dataList.get(8));
				
				String[] ballsList = dataList.get(9).split(" ");
				
				for(int i = 1; i < ballsList.length; i++) {
					storePanel.ballsStorePanel.ballsBought.add(Integer.parseInt(ballsList[i]));
				}
				
				String[] barsList = dataList.get(10).split(" ");
				
				for(int i = 1; i < barsList.length; i++) {
					storePanel.barsStorePanel.barsBought.add(Integer.parseInt(barsList[i]));
				}
				
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
