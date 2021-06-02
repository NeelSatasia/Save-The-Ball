package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bar extends Rectangle {
	
	Color barColor;
	int barHorizontalVelocity;
	
	public Bar(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
		this.barColor = Color.BLACK;
		this.barHorizontalVelocity = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(this.barColor);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public void changeBarColor() {
		int randColorChance = (int)(Math.random() * 9);
		
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
				this.barColor = new Color(230, 230, 0); //yellow
				break;
			case 5:
				this.barColor = new Color(204, 102, 0); //brown
				break;
			case 6:
				this.barColor = new Color(115, 0, 230); //purple
				break;
			case 7:
				this.barColor = new Color(255, 26, 140); //pink
				break;
		}
	}
}
