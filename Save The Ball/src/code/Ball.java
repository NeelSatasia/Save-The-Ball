package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle {
	
	int ballType;
	Color ballColor;
	int ballColorTransparency;
	int changeColorTransparency;
	
	int ballHorizontalVelocity;
	int ballVerticalVelocity;
	
	public Ball(int x, int y, int w, int h, Color color) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.ballColor = color;
		this.ballType = 1;
		this.ballColorTransparency = 255;
		this.changeColorTransparency = 0;
		
		this.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
		this.ballVerticalVelocity = 4;
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(this.ballColor.getRed(), this.ballColor.getGreen(), this.ballColor.getBlue(), this.ballColorTransparency));
		g.fillArc(this.x, this.y, this.width, this.height, 0, 360);
	}
	
	public void changeBallType(int b) {
		switch(b) {
			case 1:
				this.ballColor = Color.BLACK;
				this.ballType = 1;
				break;
			case 2:
				this.ballColor = new Color(0, 0, 139);
				this.ballType = 2;
				break;
			case 3:
				this.ballColor = new Color(0, 100, 0);
				this.ballType = 3;
				break;
			case 4:
				this.ballColor = new Color(0, 139, 139);
				this.ballType = 4;
				break;
			case 5:
				this.ballColor = new Color(0, 255, 127);
				this.ballType = 5;
				break;
			case 6:
				this.ballColor = new Color(47, 79, 79);
				this.ballType = 6;
				break;
			case 7:
				this.ballColor = new Color(72, 61, 139);
				this.ballType = 7;
				break;
			case 8:
				this.ballColor = new Color(65, 105, 225);
				this.ballType = 8;
				break;
			case 9:
				this.ballColor = new Color(184, 134, 11);
				this.ballType = 9;
				break;
			case 10:
				this.ballColor = new Color(178, 34, 34);
				this.ballType = 10;
				break;
			case 11:
				this.ballColor = new Color(255, 140, 0);
				this.ballType = 11;
				break;
			case 12:
				this.ballColor = new Color(255, 20, 147);
				this.ballType = 12;
				break;
			case 13:
				this.ballColor = new Color(233, 150, 122);
				this.ballType = 13;
				break;
			case 14:
				this.ballColor = new Color(139, 69, 19);
				this.ballType = 14;
				break;
			case 15:
				this.ballColor = new Color(220, 20, 60);
				this.ballType = 15;
				break;
		}
	}
}
