package CheckersPackage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5870916876473839398L;
	private CheckersModel masterModel;
	private CheckersPanel masterPanel;
	private JLabel message;
	
	public MenuPanel(CheckersModel masterModel, CheckersPanel masterPanel) {
		super();
		
		this.masterModel = masterModel;
		this.masterPanel = masterPanel;
		this.message = new JLabel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Font titleFont = new Font("Verdana", Font.BOLD, 40);
		Font messageFont = new Font("Verdana", Font.BOLD, 30);
		
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(titleFont);
		titleLabel.setText("Checkers!");
		this.add(titleLabel);
		
		this.message.setFont(messageFont);
		
		JButton clearBoard = new JButton("Clear Board");
		clearBoard.setActionCommand("clear");
		clearBoard.addActionListener(this);
		this.add(clearBoard);
		
		JButton resetBoard = new JButton("Reset Board");
		resetBoard.setActionCommand("reset");
		resetBoard.addActionListener(this);
		this.add(resetBoard);
		//testLabel.setBorder(BorderFactory.createEtchedBorder());
		
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
		if (command.equals("reset")) {
			this.masterModel.emptyBoard();
			this.masterModel.setUpBoard();
			this.masterPanel.requestUpdate();
		} else if (command.equals("clear")) {
			this.masterModel.emptyBoard();
			this.masterPanel.requestUpdate();
		} else {
			System.out.println("Sad Trombone");
		}
	}
}
