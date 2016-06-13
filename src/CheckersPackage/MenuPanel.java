package CheckersPackage;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	public MenuPanel() {
		super();
		JLabel testLabel = new JLabel();
		Font titleFont = new Font("Verdana", Font.BOLD, 40);
		testLabel.setFont(titleFont);
		testLabel.setText("Checkers!");
		this.add(testLabel);
		//testLabel.setBorder(BorderFactory.createEtchedBorder());
		
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
