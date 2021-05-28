package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	int ballX;
	int ballY;
	int ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
	int ballVerticalVelocity = 5;
	
	Bar bar;
	int barHorizontalVelocity = 0;
	
	int screenMaxWidth;
	int screenMaxHeight;
	
	int score = 0;
	JLabel scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
	
	int classicModeHighScore = 0;
	
	JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
	
	JButton pauseButton = new JButton("Pause");
	
	boolean gamePaused = false;
	boolean gameOver = false;
	boolean gameStarted = false;
	
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
		
		ball = new Ball((screenMaxWidth/2) - 10, 2, 1);
		ballX = (int) ball.x;
		ballY = (int) ball.y;
		
		bar = new Bar((screenMaxWidth/2) - 20, screenMaxHeight - 90);
		
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
		
		timer.setInitialDelay(1000);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ball.setLocation(ballX, ballY);
		bar.setBounds((int) (bar.x + barHorizontalVelocity), (int) bar.y, bar.width, bar.height);
		
		ball.draw(g);
		bar.draw(g);
		
		g.setColor(new Color(204, 204, 204));
		g.fillRect(0, bar.y + ((this.getHeight() - bar.y)/2), this.getWidth(), this.getHeight() - bar.y + ((this.getHeight() - bar.y)/2));
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, screenMaxWidth, topBorderHeight);
		
		if(ball.y >= bar.y + ((this.getHeight() - bar.y)/2) && gameOver == false) {
			timer.stop();
			add(gameOverLabel);
			gameOver = true;
			add(tryAgainButton);
			pauseButton.setBackground(new Color(128, 128, 128));
			
			if(score > classicModeHighScore) {
				classicModeHighScore = score;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameStarted && gameOver == false && gamePaused == false) {
			if(ballY + ball.height <= bar.y && ballY + ball.height + ballVerticalVelocity >= bar.y) {
				if(ballX + ball.width + 10 >= bar.x && ballX - 10 <= bar.x + bar.width) {
					ballY = (int) (bar.y - ball.height);
					ballVerticalVelocity *= -1;
					
					score++;
					scoreLabel.setText("Score: " + score);
					scoreLabel.setLocation((this.getWidth()/2) - (scoreLabel.getWidth()/2), bar.y + ((this.getHeight() - bar.y)/2) + 10);
					
					if(score == 50 || score == 100 || score == 150 || score == 175) {
						ballVerticalVelocity++;
						
						if(bar.width - 10 >= 20) {
							bar.width -= 10;
						}
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
					ballY += ballVerticalVelocity;
				}
			} else if(ballY + ballVerticalVelocity <= topBorderHeight) {
				ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
				ballVerticalVelocity *= -1;
				
			} else {
				ballY += ballVerticalVelocity;
			}
			
			if(ballHorizontalVelocity > 0 && ballX + ball.width + ballHorizontalVelocity >= screenMaxWidth) {
				ballHorizontalVelocity *= -1;
				ballX = screenMaxWidth - (int) ball.width;
			} else if(ballHorizontalVelocity < 0 && ballX + ballHorizontalVelocity <= 0) {
				ballHorizontalVelocity *= -1;
				ballX = 0;
			} else {
				ballX += ballHorizontalVelocity;
			}
		}
		
		repaint();
	}
	
	public void startGame() {
		gameOver = false;
		gamePaused = false;
		gameStarted = true;
		
		pauseButton.setEnabled(true);
		
		score = 0;
		scoreLabel.setText("Score: " + score);
		
		ball.setLocation((screenMaxWidth)/2 - (ball.width/2), 2);
		bar.setLocation((screenMaxWidth/2) - (bar.width/2), bar.y);
		
		ballX = ball.x;
		ballY = ball.y;
		ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
		
		barHorizontalVelocity = 0;
		bar.width = 40;
		
		pauseButton.setBackground(Color.BLACK);
		
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

	public void pauseGame() {
		if(gameOver == false) {
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

