package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class PlayGamePanel extends JPanel implements ActionListener {
	
	Timer timer = new Timer(5, this);
	
	Ball ball;
	Ball ball2;
	
	ArrayList<Ball> balls;
	int ballSpawnTimeCounter = 50;
	
	Bar bar;
	Bar bar2;
	
	int screenMaxWidth;
	int screenMaxHeight;
	
	int score;
	JLabel scoreLabel = new JLabel("", SwingConstants.CENTER);
	
	int totalCoins = 1000;
	JLabel totalCoinsLabel = new JLabel("Coins: " + totalCoins, SwingConstants.CENTER);
	
	boolean classicMode = false;
	int classicHighScore = 0;
	
	boolean duoBallsMode = false;
	int duoBallsHighScore = 0;
	
	boolean ballRainMode = false;
	int ballRainHighScore = 0;
	
	boolean duoBarsMode = false;
	int duoBarsHighScore = 0;
	
	boolean inverseMovementMode = false;
	int inverseMovementHighScore = 0;
	
	boolean invisibleBarMode = false;
	int invisibleBarHighScore = 0;
	
	JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
	
	boolean gamePaused = false;
	JLabel resumeLabel = new JLabel("Click To Resume", SwingConstants.CENTER);
	
	boolean isPlayingGame = false;
	boolean isInPlayGamePanel = false;
	
	JButton backButton = new JButton("Go Back");
	JButton tryAgainButton = new JButton("Try Again");
	
	int topBorderHeight = 0;
	
	public PlayGamePanel(int w, int h) {
		UIManager.put("Button.disabledText", Color.BLACK);
		
		screenMaxWidth = w - 40;
		screenMaxHeight = h - 100;
		
		if(screenMaxWidth > 300) {
			screenMaxWidth = 260;
		}
		
		if(screenMaxHeight > 450) {
			screenMaxHeight = 350;
		}
		
		new JPanel();
		setLayout(null);
		setOpaque(true);
		setPreferredSize(new Dimension(screenMaxWidth, screenMaxHeight));
		setBackground(new Color(242, 242, 242));
		
		ball = new Ball((screenMaxWidth/2) - 10, 0, 15, 15, new Color(47, 79, 79));
		
		bar = new Bar((screenMaxWidth/2) - 20, screenMaxHeight - 90);
		bar2 = new Bar((screenMaxWidth/2) - 20, 30);
		
		ball2 = new Ball((screenMaxWidth/2) - 10, bar.y - 20, ball.width, ball.height, ball.ballColor);
		
		balls = new ArrayList<Ball>();
		
		totalCoinsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		totalCoinsLabel.setForeground(Color.BLACK);
		
		add(scoreLabel);
		scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		gameOverLabel.setBounds(0, 50, screenMaxWidth, 35);
		gameOverLabel.setFont(new Font("Ink Free", Font.BOLD, 30));
		gameOverLabel.setForeground(Color.BLACK);
		
		resumeLabel.setBounds(0, 50, screenMaxWidth, 25);
		resumeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		resumeLabel.setForeground(Color.BLACK);
		
		customizeButton(backButton, true, Color.RED, Color.WHITE, BorderFactory.createEmptyBorder(2, 5, 2, 5));
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		tryAgainButton.setBounds((screenMaxWidth/2) - 50, gameOverLabel.getY() + gameOverLabel.getHeight() + 15, 100, 30);
		customizeButton(tryAgainButton, true, Color.BLACK, Color.WHITE, null);
		tryAgainButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tryAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(gameOverLabel);
				remove(tryAgainButton);
				
				startGame();
				
				revalidate();
				repaint();
			}
		});
		
		timer.setInitialDelay(1000);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		bar.setBounds(bar.x + bar.barHorizontalVelocity, bar.y, bar.width, bar.height);
		bar.draw(g);
		
		if(classicMode || duoBallsMode || inverseMovementMode || invisibleBarMode) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, screenMaxWidth, topBorderHeight);
			
			if(invisibleBarMode && isPlayingGame) {
				bar.barColor = new Color(bar.barColor.getRed(), bar.barColor.getGreen(), bar.barColor.getBlue(), 0);
			}
			
			if(((ball.y >= screenMaxHeight) || (duoBallsMode && ball2.y >= screenMaxHeight)) && isPlayingGame) {
				gameOver();
			} else {
				ball.draw(g);
				
				if(score >= 100) {
					if(ball.ballColorTransparency == 255) {
						ball.changeColorTransparency = -5;
					} else if(ball.ballColorTransparency == 0) {
						ball.changeColorTransparency = 5;
					}
					
					ball.ballColorTransparency += ball.changeColorTransparency;
				}
				
				if(duoBallsMode) {
					ball2.draw(g);
					
					if(score >= 100) {
						if(ball2.ballColorTransparency == 255) {
							ball2.changeColorTransparency = -5;
						} else if(ball2.ballColorTransparency == 0) {
							ball2.changeColorTransparency = 5;
						}
						
						ball2.ballColorTransparency += ball2.changeColorTransparency;
					}
				}
			}
		} else if(duoBarsMode) {
			bar2.setBounds(bar2.x + bar.barHorizontalVelocity, bar2.y, bar.width, bar.height);
			bar2.draw(g);
			
			if(ball.y < -(ball.height) || ball.y >= screenMaxHeight){
				gameOver();
			} else {
				ball.draw(g);
			}
		}
		
		if(ballRainMode) {
			for(int i = 0; i < balls.size(); i++) {
				balls.get(i).draw(g);
			}
			
			if(ballSpawnTimeCounter == 0) {
				balls.add(new Ball((int)(Math.random() * (screenMaxWidth - 20)), -25, 15, 15, Color.BLACK));
				
				balls.get(balls.size() - 1).ballVerticalVelocity = 5;
				
				if(score >= 20) {
					int randNum = (int)(Math.random() * 11);
					
					if(randNum <= 7) {
						balls.get(balls.size() - 1).ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
					} else {
						balls.get(balls.size() - 1).ballHorizontalVelocity = 0;
					}
				} else {
					balls.get(balls.size() - 1).ballHorizontalVelocity = 0;
				}
				
				if(balls.size() > 1) {
					balls.get(balls.size() - 1).ballVerticalVelocity = balls.get(balls.size() - 2).ballVerticalVelocity;
				}
				
				if(score >= 100) {
					ballSpawnTimeCounter = 40;
				} else {
					ballSpawnTimeCounter = 50;
				}
			} else {
				ballSpawnTimeCounter--;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isPlayingGame && gamePaused == false) {
			
			//classic mode or duoBallsMode or duoBarsMode or inverseMovementMode
			
			if(classicMode || duoBallsMode || duoBarsMode || inverseMovementMode || invisibleBarMode) {
				if(bar.y == screenMaxHeight - 90 && ball.y + ball.height <= bar.y && ball.y + ball.height + ball.ballVerticalVelocity >= bar.y) {
					if(ball.x + ball.width + (ball.width/2) >= bar.x && ball.x - (ball.width/2) <= bar.x + bar.width) {
						ball.setLocation(ball.x, bar.y - ball.height);
						ball.ballVerticalVelocity *= -1;
						
						score++;
						totalCoins++;
						scoreLabel.setText("Score: " + score + "  Coins: " + totalCoins);
						
						if(score == 30) {
							ball.ballVerticalVelocity = 6;
							bar.width = 35;
							topBorderHeight = 10;
						} else if(score == 75) {
							ball.ballVerticalVelocity = 7;
							bar.width = 30;
							topBorderHeight = 20;
						} else if(score == 130) {
							ball.ballVerticalVelocity = 8;
							bar.width = 25;
						}
					} else {
						ball.setLocation(ball.x, ball.y + ball.ballVerticalVelocity);
					}
				} else if(duoBarsMode && ball.x + ball.width + 10 >= bar.x && ball.x - 10 <= bar.x + bar.width && ball.y >= bar2.y + bar2.height && ball.y + ball.ballVerticalVelocity <= bar2.y + bar2.height) {
					ball.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
					ball.ballVerticalVelocity *= -1;
					
					//bar.setLocation(bar.x, screenMaxHeight - 90);
					
					score++;
					totalCoins++;
					scoreLabel.setText("Score: " + score + "  Coins: " + totalCoins);
				} else if((classicMode || duoBallsMode || inverseMovementMode || invisibleBarMode) && ball.y + ball.ballVerticalVelocity <= topBorderHeight) {
					ball.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
					ball.ballVerticalVelocity *= -1;
					
				} else {
					ball.setLocation(ball.x, ball.y + ball.ballVerticalVelocity);
				}
				
				if(ball.ballHorizontalVelocity > 0 && ball.x + ball.width + ball.ballHorizontalVelocity >= screenMaxWidth) {
					ball.ballHorizontalVelocity *= -1;
					ball.setLocation(screenMaxWidth - ball.width, ball.y);
				} else if(ball.ballHorizontalVelocity < 0 && ball.x + ball.ballHorizontalVelocity <= 0) {
					ball.ballHorizontalVelocity *= -1;
					ball.setLocation(0, ball.y);
				} else {
					ball.setLocation(ball.x + ball.ballHorizontalVelocity, ball.y);
				}
			}
			
			//duoBallsMode (Ball 2)
			
			if(duoBallsMode) {
				if(ball2.y + ball2.height <= bar.y && ball2.y + ball2.height + ball2.ballVerticalVelocity >= bar.y) {
					if(ball2.x + ball2.width + (ball2.width/2) >= bar.x && ball2.x - (ball2.width/2) <= bar.x + bar.width) {
						ball2.setLocation(ball2.x, bar.y - ball2.height);
						ball2.ballVerticalVelocity *= -1;
						
						score++;
						totalCoins++;
						scoreLabel.setText("Score: " + score + "  Coins: " + totalCoins);
						
						if(score == 30) {
							ball2.ballVerticalVelocity = 6;
						} else if(score == 75) {
							ball2.ballVerticalVelocity = 7;
						} else if(score == 130) {
							ball2.ballVerticalVelocity = 8;
						}
					} else {
						ball2.setLocation(ball2.x, ball2.y + ball2.ballVerticalVelocity);
					}
				} else if(ball2.y + ball2.ballVerticalVelocity <= topBorderHeight) {
					ball2.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
					ball2.ballVerticalVelocity *= -1;
					
				} else {
					ball2.setLocation(ball2.x, ball2.y + ball2.ballVerticalVelocity);
				}
				
				if(ball2.ballHorizontalVelocity > 0 && ball2.x + ball2.width + ball2.ballHorizontalVelocity >= screenMaxWidth) {
					ball2.ballHorizontalVelocity *= -1;
					ball2.setLocation(screenMaxWidth - ball2.width, ball2.y);
				} else if(ball2.ballHorizontalVelocity < 0 && ball2.x + ball2.ballHorizontalVelocity <= 0) {
					ball2.ballHorizontalVelocity *= -1;
					ball2.setLocation(0, ball2.y);
				} else {
					ball2.setLocation(ball2.x + ball2.ballHorizontalVelocity, ball2.y);
				}
			}
			
			//(color) ball rain mode
			
			if(ballRainMode) {
				int i = 0;
				while(i < balls.size()) {
					if(score == 100) {
						balls.get(i).ballVerticalVelocity = 6;
						bar.width = 25;
					} else if(score == 150) {
						balls.get(i).ballVerticalVelocity = 7;
						bar.width = 20;
					}
					
					if(balls.get(i).x + balls.get(i).width + (balls.get(i).width/2) > bar.x && balls.get(i).x - (balls.get(i).width/2) <= bar.x + bar.width && balls.get(i).y + balls.get(i).height == bar.y) {
						if(ballRainMode) {
							balls.remove(i);
							
							score++;
							totalCoins++;
							scoreLabel.setText("Score: " + score + "  Coins: " + totalCoins);
						}
					} else if(balls.get(i).y > screenMaxHeight - 10) {
						if(ballRainMode) {
							balls.clear();
							gameOver();
						}
					} else {
						if(balls.get(i).ballHorizontalVelocity > 0 && balls.get(i).x + balls.get(i).width + balls.get(i).ballHorizontalVelocity >= screenMaxWidth) {
							balls.get(i).ballHorizontalVelocity *= -1;
							balls.get(i).setLocation(screenMaxWidth - balls.get(i).width, balls.get(i).y);
						} else if(balls.get(i).ballHorizontalVelocity < 0 && balls.get(i).x + balls.get(i).ballHorizontalVelocity <= 0) {
							balls.get(i).ballHorizontalVelocity *= -1;
							balls.get(i).setLocation(0, balls.get(i).y);
						} else {
							balls.get(i).setLocation(balls.get(i).x + balls.get(i).ballHorizontalVelocity, balls.get(i).y);
						}
						
						if(balls.get(i).x + balls.get(i).width + (balls.get(i).width/2) > bar.x && balls.get(i).x - (balls.get(i).width/2) <= bar.x + bar.width) {
							if(balls.get(i).y + balls.get(i).height + balls.get(i).ballVerticalVelocity >= bar.y && balls.get(i).y + balls.get(i).height <= bar.y + bar.height) {
								balls.get(i).setLocation(balls.get(i).x, bar.y - balls.get(i).height);
							} else {
								balls.get(i).setLocation(balls.get(i).x, balls.get(i).y += balls.get(i).ballVerticalVelocity);
							}
						} else {
							balls.get(i).setLocation(balls.get(i).x, balls.get(i).y += balls.get(i).ballVerticalVelocity);
						}
						
						i++;
					}
				}
			}
			
			
		}
		
		repaint();
	}
	
	public void startGame() {
		gamePaused = false;
		isPlayingGame = true;
		
		score = 0;
		scoreLabel.setText("Score: " + score + "  Coins: " + totalCoins);
		scoreLabel.setForeground(Color.BLACK);
		
		bar.setLocation((screenMaxWidth/2) - (bar.width/2), screenMaxHeight - 90);
		bar.barHorizontalVelocity = 0;
		bar.width = 40;
		bar.barColor = new Color(bar.barColor.getRed(), bar.barColor.getGreen(), bar.barColor.getBlue(), 255);
		
		if(classicMode || duoBallsMode || duoBarsMode || inverseMovementMode || invisibleBarMode) {
			if(duoBarsMode) {
				ball.setLocation(bar.x + (bar.width/2) - (ball.width), 40);
				bar2.setLocation((screenMaxWidth/2) - (bar.width/2), 30);
				bar2.width = bar.width;
				bar2.barColor = new Color(bar.barColor.getRed(), bar.barColor.getGreen(), bar.barColor.getBlue(), 255);
			} else {
				ball.setLocation(bar.x + (bar.width/2) - (ball.width), 2);
			}
			
			ball.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
			ball.ballColorTransparency = 255;
			
			if(classicMode) {
				ball.ballVerticalVelocity = 5;
			} else {
				ball.ballVerticalVelocity = 4;
			}
		}
		
		if(duoBallsMode) {
			ball2.setLocation(bar.x + (bar.width/2) - (ball2.width), bar.y - ball2.height);
			ball2.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
			ball2.ballVerticalVelocity = 4;
			ball2.ballColorTransparency = 255;
		}
		
		if(duoBarsMode) {
			setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
		} else {
			setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		}
		
		topBorderHeight = 0;
		
		timer.setInitialDelay(1000);
		timer.restart();
		
		repaint();
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
	
	public void gameOver() {
		timer.stop();
		add(gameOverLabel);
		isPlayingGame = false;
		add(tryAgainButton);
		
		if(classicMode && score > classicHighScore) {
			classicHighScore = score;
			} else if(duoBallsMode && score > duoBallsHighScore) {
			duoBallsHighScore = score;
		} else if(ballRainMode && score > ballRainHighScore) {
			ballRainHighScore = score;
		} else if(duoBarsMode && score > duoBarsHighScore) {
			duoBarsHighScore = score;
		} else if(inverseMovementMode && score > inverseMovementHighScore) {
			inverseMovementHighScore = score;
		} else if(invisibleBarMode && score > invisibleBarHighScore) {
			invisibleBarHighScore = score;
		}
	}

	public void pauseGame() {
		if(isPlayingGame) {
			if(timer.isRunning()) {							//game paused
				timer.stop();
				gamePaused = true;
				add(resumeLabel);
				repaint();
			} else {
				gamePaused = false;
				timer.setInitialDelay(0);
				timer.start();								//game unpaused
				remove(resumeLabel);
			}
		}
	}
}

