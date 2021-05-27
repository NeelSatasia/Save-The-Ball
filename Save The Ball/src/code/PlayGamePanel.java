package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

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
	
	JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
	
	public PlayGamePanel(int w, int h) {
		UIManager.put("Button.disabledText", Color.BLACK);
		
		screenMaxWidth = w - 50;
		screenMaxHeight = h - 90;
		
		new JPanel();
		setLayout(null);
		setOpaque(false);
		setPreferredSize(new Dimension(screenMaxWidth, screenMaxHeight));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		ball = new Ball((screenMaxWidth/2) - 10, 2);
		ballX = (int) ball.x;
		ballY = (int) ball.y;
		
		bar = new Bar((screenMaxWidth/2) - 20, screenMaxHeight - 90);
		
		add(scoreLabel);
		scoreLabel.setBounds(0, bar.y + ((screenMaxHeight - bar.y)/2) + 10, screenMaxWidth, 30);
		scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		gameOverLabel.setBounds((screenMaxWidth/2) - 75, 50, 150, 35);
		gameOverLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		gameOverLabel.setForeground(Color.BLACK);
		gameOverLabel.setBackground(new Color(255, 128, 128));
		gameOverLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		gameOverLabel.setOpaque(true);
		
		repaint();
		
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ball.setLocation(ballX, ballY);
		bar.setLocation((int) (bar.x + barHorizontalVelocity), (int) bar.y);
		
		ball.draw(g);
		bar.draw(g);
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, bar.y + ((this.getHeight() - bar.y)/2), this.getWidth(), this.getHeight() - bar.y + ((this.getHeight() - bar.y)/2));
		
		if(ball.y >= bar.y + ((this.getHeight() - bar.y)/2)) {
			timer.stop();
			add(gameOverLabel);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(ballY + ball.height <= bar.y && ballY + ball.height + ballVerticalVelocity >= bar.y) {
			if(ballX + ball.width + 10 >= bar.x && ballX - 10 <= bar.x + bar.width) {
				ballY = (int) (bar.y - ball.height);
				ballVerticalVelocity *= -1;
				
				score++;
				scoreLabel.setText("Score: " + score);
				scoreLabel.setLocation((this.getWidth()/2) - (scoreLabel.getWidth()/2), bar.y + ((this.getHeight() - bar.y)/2) + 10);
			} else {
				ballY += ballVerticalVelocity;
			}
		} else if(ballY + ballVerticalVelocity <= 0) {
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
		
		repaint();
	}
}

