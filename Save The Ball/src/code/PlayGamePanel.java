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
	int barHorizontalVelocity = 0;
	
	int screenMaxWidth;
	int screenMaxHeight;
	
	int score = 0;
	JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
	
	boolean classicMode = false;
	int classicModeHighScore = 0;
	
	boolean duoBallsMode = false;
	int multipleBallsHighScore = 0;
	
	boolean ballRainMode = false;
	int ballRainHighScore = 0;
	
	boolean colorBallRainMode = false;
	int colorBallRainHighScore = 0;
	
	boolean switchBarMode = false;
	int switchBarHighScore = 0;
	
	JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
	
	JButton pauseButton = new JButton("Pause");
	
	boolean gamePaused = false;
	boolean isPlayingGame = false;
	
	JButton backButton = new JButton("Back");
	JButton tryAgainButton = new JButton("Try Again");
	
	int topBorderHeight = 0;
	
	public PlayGamePanel(int w, int h) {
		UIManager.put("Button.disabledText", Color.BLACK);
		
		screenMaxWidth = w - 50;
		screenMaxHeight = h - 90;
		
		new JPanel();
		setLayout(null);
		setOpaque(false);
		setPreferredSize(new Dimension(screenMaxWidth, screenMaxHeight));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		ball = new Ball((screenMaxWidth/2) - 10, 45, 20, 20, 1, Color.RED);
		
		bar = new Bar((screenMaxWidth/2) - 20, screenMaxHeight - 90);
		
		ball2 = new Ball((screenMaxWidth/2) - 20, bar.y - 20, 20, 20, 1, Color.RED);
		
		balls = new ArrayList<Ball>();
		
		add(scoreLabel);
		scoreLabel.setBounds(0, bar.y + ((screenMaxHeight - bar.y)/2) + 10, screenMaxWidth, 30);
		scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		gameOverLabel.setBounds(0, 50, screenMaxWidth, 35);
		gameOverLabel.setFont(new Font("Ink Free", Font.BOLD, 30));
		gameOverLabel.setForeground(Color.BLACK);
		
		customizeButton(backButton, true, Color.RED, Color.WHITE, BorderFactory.createEmptyBorder(2, 5, 2, 5));
		backButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		customizeButton(pauseButton, true, Color.BLACK, Color.WHITE, BorderFactory.createEmptyBorder(2,  5, 2, 5));
		pauseButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pauseGame();
			}
		});
		
		tryAgainButton.setBounds((screenMaxWidth/2) - 50, gameOverLabel.getY() + gameOverLabel.getHeight() + 15, 100, 30);
		customizeButton(tryAgainButton, true, Color.BLACK, Color.WHITE, null);
		tryAgainButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		tryAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(gameOverLabel);
				remove(tryAgainButton);
				
				startGame();
			}
		});
		
		//timer.setInitialDelay(1000);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		bar.setBounds((int) (bar.x + barHorizontalVelocity), (int) bar.y, bar.width, bar.height);
		bar.draw(g);
		
		if(classicMode || duoBallsMode) {
			ball.setLocation(ball.ballX, ball.ballY);
		}
		
		if(duoBallsMode) {
			ball2.setLocation(ball2.ballX, ball2.ballY);
		}
		
		g.setColor(new Color(204, 204, 204));
		g.fillRect(0, bar.y + ((screenMaxHeight - bar.y)/2), screenMaxWidth, screenMaxHeight - bar.y + ((screenMaxHeight - bar.y)/2));
		
		if(classicMode || duoBallsMode) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, screenMaxWidth, topBorderHeight);
			
			if((ball.y >= bar.y + ((screenMaxHeight - bar.y)/2) || ball2.y >= bar.y + (screenMaxHeight - bar.y)/2) && isPlayingGame) {
				gameOver();
			} else {
				ball.draw(g);
				
				if(duoBallsMode) {
					ball2.draw(g);
				}
			}
		}
		
		if(ballRainMode || colorBallRainMode) {
			for(int i = 0; i < balls.size(); i++) {
				balls.get(i).draw(g);
			}
			
			if(ballSpawnTimeCounter == 0) {
				balls.add(new Ball((int)(Math.random() * (screenMaxWidth - 20)), -25, 15, 15, 1, Color.BLACK));
				
				if(score >= 45) {
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
					
					if(colorBallRainMode) {
						balls.get(balls.size() - 1).changeBallColor();
					}
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
		
		if(isPlayingGame == false && gamePaused == false) {
			if(classicMode && score > classicModeHighScore) {
				classicModeHighScore = score;
 			} else if(duoBallsMode && score > multipleBallsHighScore) {
				multipleBallsHighScore = score;
			} else if(ballRainMode && score > ballRainHighScore) {
				ballRainHighScore = score;
			} else if(colorBallRainMode && score > colorBallRainHighScore) {
				colorBallRainHighScore = score;
			} else if(switchBarMode && score > switchBarHighScore) {
				switchBarHighScore = score;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isPlayingGame && gamePaused == false) {
			
			//classic mode
			
			if(classicMode || duoBallsMode) {
				if(ball.ballY + ball.height <= bar.y && ball.ballY + ball.height + ball.ballVerticalVelocity >= bar.y) {
					if(ball.ballX + ball.width + 10 >= bar.x && ball.ballX - 10 <= bar.x + bar.width) {
						ball.ballY = (int) (bar.y - ball.height);
						ball.ballVerticalVelocity *= -1;
						
						score++;
						scoreLabel.setText("Score: " + score);
						scoreLabel.setLocation((screenMaxWidth/2) - (scoreLabel.getWidth()/2), bar.y + ((screenMaxHeight - bar.y)/2) + 10);
						
						if(score == 50) {
							ball.ballVerticalVelocity = 6;
							bar.width = 30;
						} else if(score == 100) {
							ball.ballVerticalVelocity = 7;
							bar.width = 25;
						} else if(score == 150) {
							ball.ballVerticalVelocity = 8;
							bar.width = 20;
						}
						
						if(score > 50) {
							int randNum = (int)(Math.random() * 11);
							
							if(randNum > 5) {
								switch(randNum) {
									case 6:
										topBorderHeight = 20;
										break;
									case 7:
										topBorderHeight = 30;
										break;
									case 8:
										topBorderHeight = 40;
										break;
								}
							} else if(topBorderHeight > 0) {
								topBorderHeight = 0;
							}
						}
					} else {
						ball.ballY += ball.ballVerticalVelocity;
					}
				} else if(ball.ballY + ball.ballVerticalVelocity <= topBorderHeight) {
					ball.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
					ball.ballVerticalVelocity *= -1;
					
				} else {
					ball.ballY += ball.ballVerticalVelocity;
				}
				
				if(ball.ballHorizontalVelocity > 0 && ball.ballX + ball.width + ball.ballHorizontalVelocity >= screenMaxWidth) {
					ball.ballHorizontalVelocity *= -1;
					ball.ballX = screenMaxWidth - (int) ball.width;
				} else if(ball.ballHorizontalVelocity < 0 && ball.ballX + ball.ballHorizontalVelocity <= 0) {
					ball.ballHorizontalVelocity *= -1;
					ball.ballX = 0;
				} else {
					ball.ballX += ball.ballHorizontalVelocity;
				}
			}
			
			//multipleBallsMode (Ball 2)
			
			if(duoBallsMode) {
				if(ball2.ballY + ball2.height <= bar.y && ball2.ballY + ball2.height + ball2.ballVerticalVelocity >= bar.y) {
					if(ball2.ballX + ball2.width + 10 >= bar.x && ball2.ballX - 10 <= bar.x + bar.width) {
						ball2.ballY = (int) (bar.y - ball2.height);
						ball2.ballVerticalVelocity *= -1;
						
						score++;
						scoreLabel.setText("Score: " + score);
						
						if(score == 50) {
							ball.ballVerticalVelocity = 6;
							bar.width = 30;
						} else if(score == 100) {
							ball.ballVerticalVelocity = 7;
							bar.width = 25;
						} else if(score == 150) {
							ball.ballVerticalVelocity = 8;
							bar.width = 20;
						}
					} else {
						ball2.ballY += ball2.ballVerticalVelocity;
					}
				} else if(ball2.ballY + ball2.ballVerticalVelocity <= topBorderHeight) {
					ball2.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
					ball2.ballVerticalVelocity *= -1;
					
				} else {
					ball2.ballY += ball2.ballVerticalVelocity;
				}
				
				if(ball2.ballHorizontalVelocity > 0 && ball2.ballX + ball2.width + ball2.ballHorizontalVelocity >= screenMaxWidth) {
					ball2.ballHorizontalVelocity *= -1;
					ball2.ballX = screenMaxWidth - (int) ball2.width;
				} else if(ball2.ballHorizontalVelocity < 0 && ball2.ballX + ball2.ballHorizontalVelocity <= 0) {
					ball2.ballHorizontalVelocity *= -1;
					ball2.ballX = 0;
				} else {
					ball2.ballX += ball2.ballHorizontalVelocity;
				}
			}
			
			//(color) ball rain mode
			
			if(ballRainMode || colorBallRainMode) {
				int i = 0;
				while(i < balls.size()) {
					if(score == 50) {
						balls.get(i).ballVerticalVelocity = 5;
						bar.width = 30;
					} else if(score == 100) {
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
							scoreLabel.setText("Score: " + score);
						} else if(colorBallRainMode) {
							if(bar.barColor.getRGB() == balls.get(i).ballColor.getRGB()) {
								balls.remove(i);
								
								score++;
								scoreLabel.setText("Score: " + score);
								
								bar.changeBarColor();
							} else {
								balls.clear();
								gameOver();
							}
						}
					} else if(balls.get(i).y > screenMaxHeight - 10) {
						if(ballRainMode) {
							balls.clear();
							gameOver();
						} else if(colorBallRainMode) {
							if(balls.get(i).ballColor.getRGB() == bar.barColor.getRGB()) {
								balls.clear();
								gameOver();
							} else {
								balls.remove(i);
								
								score++;
								scoreLabel.setText("Score: " + score);
								
								if(score >= 5) {
									bar.changeBarColor();
								}
							}
						}
					} else {
						if(balls.get(i).ballHorizontalVelocity > 0 && balls.get(i).ballX + balls.get(i).width + balls.get(i).ballHorizontalVelocity >= screenMaxWidth) {
							balls.get(i).ballHorizontalVelocity *= -1;
							balls.get(i).ballX = screenMaxWidth - (int) balls.get(i).width;
						} else if(balls.get(i).ballHorizontalVelocity < 0 && balls.get(i).ballX + balls.get(i).ballHorizontalVelocity <= 0) {
							balls.get(i).ballHorizontalVelocity *= -1;
							balls.get(i).ballX = 0;
						} else {
							balls.get(i).ballX += balls.get(i).ballHorizontalVelocity;
						}
						
						if(balls.get(i).x + balls.get(i).width + (balls.get(i).width/2) > bar.x && balls.get(i).x - (balls.get(i).width/2) <= bar.x + bar.width) {
							if(balls.get(i).y + balls.get(i).height + balls.get(i).ballVerticalVelocity >= bar.y && balls.get(i).y + balls.get(i).height <= bar.y + bar.height) {
								balls.get(i).setLocation(balls.get(i).ballX, bar.y - balls.get(i).height);
							} else {
								balls.get(i).setLocation(balls.get(i).ballX, balls.get(i).y += balls.get(i).ballVerticalVelocity);
							}
						} else {
							balls.get(i).setLocation(balls.get(i).ballX, balls.get(i).y += balls.get(i).ballVerticalVelocity);
						}
						
						i++;
					}
				}
			}
		}
		
		repaint();
	}
	
	public void startGame() {
		pauseButton.setEnabled(true);
		
		score = 0;
		scoreLabel.setText("Score: " + score);
		
		bar.setLocation((screenMaxWidth/2) - (bar.width/2), bar.y);
		
		if(classicMode || duoBallsMode) {
			ball.setLocation((screenMaxWidth)/2 - (ball.width/2), 2);
			ball.ballX = ball.x;
			ball.ballY = ball.y;
			ball.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
			
			if(classicMode) {
				ball.ballVerticalVelocity = 5;
			} else {
				ball.ballVerticalVelocity = 4;
			}
		}
		
		if(duoBallsMode) {
			ball.setLocation((screenMaxWidth)/2 - (ball2.width/2), bar.y - ball2.height);
			ball2.ballX = ball.x;
			ball2.ballY = ball.y;
			ball2.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
			ball2.ballVerticalVelocity = 4;
		}
		
		if(colorBallRainMode) {
			bar.changeBarColor();
		} else {
			bar.barColor = Color.BLACK;
		}
		
		barHorizontalVelocity = 0;
		bar.width = 40;
		
		pauseButton.setBackground(Color.BLACK);
		
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
		pauseButton.setBackground(new Color(128, 128, 128));
	}

	public void pauseGame() {
		if(isPlayingGame) {
			if(timer.isRunning()) {							//game paused
				timer.stop();
				pauseButton.setText("Paused");
				gamePaused = true;
			} else {
				gamePaused = false;
				timer.start();								//game unpaused
				pauseButton.setText("Pause");
			}
		}
	}
}

