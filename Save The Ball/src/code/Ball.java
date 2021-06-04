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
	
	public Ball(int x, int y, int w, int h, int ballType, Color color) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.ballType = ballType;
		this.ballColor = color;
		this.ballColorTransparency = 255;
		this.changeColorTransparency = 0;
		
		this.ballHorizontalVelocity = (int)(Math.random() * 11) - 5;
		this.ballVerticalVelocity = 4;
	}
	
	public void draw(Graphics g) {
		switch(this.ballType) {
			case 1:
				g.setColor(new Color(this.ballColor.getRed(), this.ballColor.getGreen(), this.ballColor.getBlue(), this.ballColorTransparency));
				g.fillArc(this.x, this.y, this.width, this.height, 0, 360);
				break;
		}
	}

	public void changeBallColor() {
		int randColorChance = (int)(Math.random() * 9);
		
		switch(randColorChance) {
			case 0:
				this.ballColor = Color.BLACK;
				break;
			case 1:
				this.ballColor = Color.BLUE;
				break;
			case 2:
				this.ballColor = Color.GREEN;
				break;
			case 3:
				this.ballColor = Color.ORANGE;
				break;
			case 4:
				this.ballColor = new Color(230, 230, 0); //yellow
				break;
			case 5:
				this.ballColor = new Color(204, 102, 0); //brown
				break;
			case 6:
				this.ballColor = new Color(115, 0, 230); //purple
				break;
			case 7:
				this.ballColor = new Color(255, 26, 140); //pink
				break;
		}
	}
}
