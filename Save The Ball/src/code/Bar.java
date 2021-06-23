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
		this.barType = 1;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.barColor);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public void changeBarType(int b) {
		switch(b) {
			case 1:
				this.barColor = Color.BLACK;
				this.barType = 1;
				break;
			case 2:
				this.barColor = new Color(0, 0, 139);
				this.barType = 2;
				break;
			case 3:
				this.barColor = new Color(0, 100, 0);
				this.barType = 3;
				break;
			case 4:
				this.barColor = new Color(0, 139, 139);
				this.barType = 4;
				break;
			case 5:
				this.barColor = new Color(0, 255, 127);
				this.barType = 5;
				break;
			case 6:
				this.barColor = new Color(47, 79, 79);
				this.barType = 6;
				break;
			case 7:
				this.barColor = new Color(72, 61, 139);
				this.barType = 7;
				break;
			case 8:
				this.barColor = new Color(65, 105, 225);
				this.barType = 8;
				break;
			case 9:
				this.barColor = new Color(184, 134, 11);
				this.barType = 9;
				break;
			case 10:
				this.barColor = new Color(178, 34, 34);
				this.barType = 10;
				break;
			case 11:
				this.barColor = new Color(255, 140, 0);
				this.barType = 11;
				break;
			case 12:
				this.barColor = new Color(255, 20, 147);
				this.barType = 12;
				break;
			case 13:
				this.barColor = new Color(233, 150, 122);
				this.barType = 13;
				break;
			case 14:
				this.barColor = new Color(139, 69, 19);
				this.barType = 14;
				break;
			case 15:
				this.barColor = new Color(220, 20, 60);
				this.barType = 15;
				break;
		}
	}
}
