package CheckersPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
		Font messageFont = new Font("Verdana", Font.BOLD, 20);
		
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(titleFont);
		titleLabel.setText("Checkers!");
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);		
		
		this.message.setFont(messageFont);
		this.message.setText("Welcome to Checkers!");
		this.message.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buttonWrapper = new JPanel();
		buttonWrapper.setLayout(new BoxLayout(buttonWrapper, BoxLayout.LINE_AXIS));
		
		JButton clearBoard = new JButton("Clear Board");
		clearBoard.setActionCommand("clear");
		clearBoard.addActionListener(this);
		buttonWrapper.add(clearBoard);
		
		JButton resetBoard = new JButton("Reset Board");
		resetBoard.setActionCommand("reset");
		resetBoard.addActionListener(this);
		buttonWrapper.add(resetBoard);
		
		this.add(Box.createVerticalStrut(10));
		this.add(titleLabel);
		this.add(message);
		this.add(Box.createVerticalStrut(300));
		this.add(buttonWrapper);	
		this.add(Box.createVerticalStrut(10));
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.message.setText("curr size: " + this.getWidth() + ", " + this.getHeight());
		//this.message.setText("curr parent size: " + this.getParent().getWidth() + ", " + this.getParent().getHeight());
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
