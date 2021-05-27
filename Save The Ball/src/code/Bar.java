package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bar extends Rectangle { //Bar
	
	public Bar(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 60;
		this.height = 5;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
}
