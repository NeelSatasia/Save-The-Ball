package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StorePanel extends JPanel {
	
	JScrollPane storeSp;
	JPanel ballsPanel1;
	JPanel ballsPanel2;
	
	JLabel ballsLabel = new JLabel("Balls");
	
	JButton backButton = new JButton("Back");
	
	public StorePanel() {
		new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		/*storeSp = new JScrollPane();
		storePanel.setLayout(new BoxLayout(storePanel, BoxLayout.Y_AXIS));
		storeSp.add(storePanel);
		storePanel.setPreferredSize(new Dimension(110, 420));
		add(storeSp);
		storeSp.setBounds(20, 560, 120, 100);
		storeSp.setViewportView(storePanel);
		storeSp.validate();
		storeSp.getVerticalScrollBar().setUnitIncrement(10);
		storeSp.getVerticalScrollBar().setForeground(Color.black);
		storeSp.setBorder(null);*/
		
		add(ballsLabel);
		ballsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ballsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		ballsPanel1 = new JPanel();
		ballsPanel2 = new JPanel();
		//ballsPanel2.setLayout(mgr);
		//storePanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		
		add(ballsPanel1);
		add(ballsPanel2);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
