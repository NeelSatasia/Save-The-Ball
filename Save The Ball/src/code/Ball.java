package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Ball extends Rectangle {
	
	ImageIcon ballImg;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 20;
		this.height = 20;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillArc(this.x, this.y, this.width, this.height, 0, 360);
	}
}
