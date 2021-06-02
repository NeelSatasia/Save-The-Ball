package code;

import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StorePanel extends JPanel {
	
	JButton backButton = new JButton("Back");
	
	public StorePanel() {
		new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
