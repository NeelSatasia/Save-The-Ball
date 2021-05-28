package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle {
	
	int ballType;
	
	public Ball(int x, int y, int ballType) {
		this.x = x;
		this.y = y;
		this.width = 20;
		this.height = 20;
		this.ballType = ballType;
	}
	
	public void draw(Graphics g) {
		
		switch(ballType) {
			case 1:
				g.setColor(Color.RED);
				g.fillArc(this.x, this.y, this.width, this.height, 0, 360);
				break;
		}
	}
}
