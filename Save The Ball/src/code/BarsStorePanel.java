package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BarsStorePanel extends JPanel {
	
	int barTypeEquipped;
	
	JButton[] buyBars;
	ArrayList<Integer> barsBought;
	
	public BarsStorePanel() {
		new JPanel();
		setLayout(null);
		setBackground(new Color(242, 242, 242));
		setPreferredSize(new Dimension(140, 250));
		
		barTypeEquipped = 1;
		
		buyBars = new JButton[15];
		barsBought = new ArrayList<Integer>();
		
		barsBought.add(barTypeEquipped);
		
		int x = 7;
		int y = 30;
		int currentColCounter = 0;
		for(int i = 0; i < buyBars.length; i++) {
			buyBars[i] = new JButton("50");
			customizeButton(buyBars[i], true, Color.BLACK, Color.WHITE, null);
			add(buyBars[i]);
			buyBars[i].setBounds(x, y, 36, 20);
			
			if(i + 1 == barTypeEquipped && barsBought.contains(i + 1)) {
				buyBars[i].setText("Using");
				buyBars[i].setBackground(Color.GREEN);
				buyBars[i].setEnabled(false);
			}
			
			currentColCounter++;
			
			if(currentColCounter == 3) {
				currentColCounter = 0;
				x = 7;
				y += 45;
			} else {
				x += 65;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(5, 15, 40, 5);
		
		g.setColor(new Color(0, 0, 139));
		g.fillRect(70, 15, 40, 5);
		
		g.setColor(new Color(0, 100, 0));
		g.fillRect(135, 15, 40, 5);
		
		g.setColor(new Color(0, 139, 139));
		g.fillRect(5, 60,  40, 5);
		
		g.setColor(new Color(0, 255, 127));
		g.fillRect(70, 60, 40, 5);
		
		g.setColor(new Color(47, 79, 79));
		g.fillRect(135, 60, 40, 5);
		
		g.setColor(new Color(72, 61, 139));
		g.fillRect(5, 105, 40, 5);
		
		g.setColor(new Color(65, 105, 225));
		g.fillRect(70, 105, 40, 5);
		
		g.setColor(new Color(184, 134, 11));
		g.fillRect(135, 105, 40, 5);
		
		g.setColor(new Color(178, 34, 34));
		g.fillRect(5, 150, 40, 5);
		
		g.setColor(new Color(255, 140, 0));
		g.fillRect(70, 150, 40, 5);
		
		g.setColor(new Color(255, 20, 147));
		g.fillRect(135, 150, 40, 5);
		
		g.setColor(new Color(233, 150, 122));
		g.fillRect(5, 195, 40, 5);
		
		g.setColor(new Color(139, 69, 19));
		g.fillRect(70, 195, 40, 5);
		
		g.setColor(new Color(220, 20, 60));
		g.fillRect(135, 195, 40, 5);
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
