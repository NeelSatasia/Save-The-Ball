package code;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StorePanel extends JScrollPane {
	
	JScrollPane storeSp;
	JPanel storePanel;
	
	JLabel ballsLabel = new JLabel("Balls");
	
	JButton backButton = new JButton("Back");
	
	public StorePanel() {
		new JScrollPane();
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		/*storePanel = 
		
		new JScrollPane();
		player3PropertiesPanel.setLayout(new BoxLayout(player3PropertiesPanel, BoxLayout.Y_AXIS));
		player3PropertiesSp.add(player3PropertiesPanel);
		player3PropertiesPanel.setPreferredSize(new Dimension(110, 420));
		monopolyDataPanel.add(player3PropertiesSp);
		player3PropertiesSp.setBounds(20, 560, 120, 100);
		player3PropertiesSp.setViewportView(player3PropertiesPanel);
		player3PropertiesSp.validate();
		player3PropertiesSp.getVerticalScrollBar().setUnitIncrement(10);*/
		
		add(ballsLabel);
		ballsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		ballsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
