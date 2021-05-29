package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle {
	
	int ballType;
	Color ballColor;
	
	int ballX;
	int ballY;
	
	int ballHorizontalVelocity;
	int ballVerticalVelocity;
	
	public Ball(int x, int y, int w, int h, int ballType, Color color) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.ballType = ballType;
		this.ballColor = color;
		
		this.ballX = this.x;
		this.ballY = this.y;
		
		ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
		ballVerticalVelocity = 4;
	}
	
	public void draw(Graphics g) {
		
		switch(ballType) {
			case 1:
				g.setColor(this.ballColor);
				g.fillArc(this.x, this.y, this.width, this.height, 0, 360);
				break;
		}
	}
}
