package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BallsStorePanel extends JPanel {
	
	int ballTypeEquipped;
	
	JButton[] buyBalls;
	
	public BallsStorePanel() {
		new JPanel();
		setLayout(null);
		setBackground(new Color(242, 242, 242));
		setPreferredSize(new Dimension(140, 250));
		
		ballTypeEquipped = 1;
		
		buyBalls = new JButton[14];
		
		int x = 20;
		int y = 35;
		
		for(int i = 0; i < buyBalls.length; i++) {
			buyBalls[i] = new JButton("50");
			customizeButton(buyBalls[i], true, Color.BLACK, Color.WHITE, null);
			add(buyBalls[i]);
			buyBalls[i].setBounds(x, y, 20, 20);
			
			if(x % 3 == 0) {
				x = 10;
				y += 55;
			} else {
				x += 30;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillArc(15, 15 , 15, 15, 0, 360);
		
		g.setColor(new Color(0, 0, 139));
		g.fillArc(60, 15, 15, 15, 0, 360);
		
		g.setColor(new Color(0, 100, 0));
		g.fillArc(105, 15, 15, 15, 0, 360);
		
		g.setColor(new Color(0, 139, 139));
		g.fillArc(15, 60, 15, 15, 0, 360);
		
		g.setColor(new Color(0, 255, 127));
		g.fillArc(60, 60, 15, 15, 0, 360);
		
		g.setColor(new Color(47, 79, 79));
		g.fillArc(105, 60, 15, 15, 0, 360);
		
		g.setColor(new Color(72, 61, 139));
		g.fillArc(15, 105, 15, 15, 0, 360);
		
		g.setColor(new Color(65, 105, 225));
		g.fillArc(60, 105, 15, 15, 0, 360);
		
		g.setColor(new Color(184, 134, 11));
		g.fillArc(105, 105, 15, 15, 0, 360);
		
		g.setColor(new Color(178, 34, 34));
		g.fillArc(15, 150, 15, 15, 0, 360);
		
		g.setColor(new Color(255, 140, 0));
		g.fillArc(60, 150, 15, 15, 0, 360);
		
		g.setColor(new Color(255, 20, 147));
		g.fillArc(105, 150, 15, 15, 0, 360);
		
		g.setColor(new Color(233, 150, 122));
		g.fillArc(15, 195, 15, 15, 0, 360);
		
		g.setColor(new Color(139, 69, 19));
		g.fillArc(60, 195, 15, 15, 0, 360);
		
		g.setColor(new Color(220, 20, 60));
		g.fillArc(105, 195, 15, 15, 0, 360);
	}
	
	public void customizeButton(JButton button, boolean enable, Color buttonColor, Color textColor, Border border) {
		button.setEnabled(enable);
		button.setBorder(border);
		button.setBackground(buttonColor);
		if(enable) {
			button.setForeground(textColor);
		}
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
	}
}
