package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin extends Rectangle {
	
	public Coin(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 10;
	}
	
	public void draw(Graphics g) {
		g.fillArc(this.x, this.y, this.width, this.height, 0, 360);
		
		//g.setColor(Color.WHITE);
		//g.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		//g.drawString("C", this.x + 2, this.y + 2);
	}
}
