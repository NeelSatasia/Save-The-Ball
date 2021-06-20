package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bar extends Rectangle {
	
	Color barColor;
	int barHorizontalVelocity;
	int barType;
	
	public Bar(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
		this.barColor = color;
		this.barHorizontalVelocity = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.barColor);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public void changeBarType(int b) {
		switch(b) {
			case 1:
				this.barColor = Color.BLACK;
				break;
			case 2:
				this.barColor = new Color(0, 0, 139);
				break;
			case 3:
				this.barColor = new Color(0, 100, 0);
				break;
			case 4:
				this.barColor = new Color(0, 139, 139);
				break;
			case 5:
				this.barColor = new Color(0, 255, 127);
				break;
			case 6:
				this.barColor = new Color(47, 79, 79);
				break;
			case 7:
				this.barColor = new Color(72, 61, 139);
				break;
			case 8:
				this.barColor = new Color(65, 105, 225);
				break;
			case 9:
				this.barColor = new Color(184, 134, 11);
				break;
			case 10:
				this.barColor = new Color(178, 34, 34);
				break;
			case 11:
				this.barColor = new Color(255, 140, 0);
				break;
			case 12:
				this.barColor = new Color(255, 20, 147);
				break;
			case 13:
				this.barColor = new Color(233, 150, 122);
				break;
			case 14:
				this.barColor = new Color(139, 69, 19);
				break;
			case 15:
				this.barColor = new Color(220, 20, 60);
				break;
		}
	}
	
	public void changeBarColor() {
		int randColorChance = (int)(Math.random() * 8);
		
		switch(randColorChance) {
			case 0:
				this.barColor = Color.BLACK;
				break;
			case 1:
				this.barColor = Color.BLUE;
				break;
			case 2:
				this.barColor = Color.GREEN;
				break;
			case 3:
				this.barColor = Color.ORANGE;
				break;
			case 4:
				this.barColor = new Color(204, 102, 0); //brown
				break;
			case 5:
				this.barColor = new Color(115, 0, 230); //purple
				break;
			case 6:
				this.barColor = new Color(255, 26, 140); //pink
				break;
		}
	}
}
