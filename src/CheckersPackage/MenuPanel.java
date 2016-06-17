package CheckersPackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
	private JLabel currentPlayer;
	private JLabel debug;
	
	public MenuPanel(CheckersModel masterModel, CheckersPanel masterPanel) {
		super();
		
		this.masterModel = masterModel;
		this.masterPanel = masterPanel;
		this.message = new JLabel();
		this.currentPlayer = new JLabel();
		this.debug = new JLabel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Font titleFont = new Font("Verdana", Font.BOLD, 40);
		Font messageFont = new Font("Verdana", Font.BOLD, 20);
		
		JPanel titleWrapper = new JPanel();
		titleWrapper.setLayout(new BoxLayout(titleWrapper, BoxLayout.LINE_AXIS));
		titleWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleWrapper.setOpaque(false);
		
		JLabel titleLabel = new JLabel();
		titleLabel.setFont(titleFont);
		titleLabel.setText("Checkers!");
		
		titleWrapper.add(Box.createRigidArea(new Dimension(100, 0)));
		titleWrapper.add(titleLabel);
		titleWrapper.add(Box.createRigidArea(new Dimension(100, 0)));
		
		this.message.setFont(messageFont);
		//this.message.setText("Welcome to Checkers!");
		this.message.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.currentPlayer.setFont(messageFont);
		this.currentPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.debug.setFont(messageFont);
		this.debug.setAlignmentX(Component.CENTER_ALIGNMENT);

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
		this.add(titleWrapper);
		this.add(currentPlayer);
		this.add(message);
		this.add(debug);
		this.add(Box.createVerticalStrut(300));
		this.add(buttonWrapper);	
		this.add(Box.createVerticalStrut(10));
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//this.message.setText("curr size: " + this.getWidth() + ", " + this.getHeight());
		//this.debug.setText("curr parent size: " + this.getParent().getWidth() + ", " + this.getParent().getHeight());
		this.message.setText(this.masterPanel.getStatusMessage());
		
		if (this.masterModel.getCurrentPlayer() == CheckersModel.PLAYER1) {
			this.currentPlayer.setText("Player 1's turn");
		} else if (this.masterModel.getCurrentPlayer() == CheckersModel.PLAYER2) {
			this.currentPlayer.setText("Player 2's turn");
		} else {
			this.currentPlayer.setText("Error: Invalid Player");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("reset")) {
			this.masterModel.emptyBoard();
			this.masterModel.setUpBoard();
			this.masterPanel.requestUpdate();
			this.masterPanel.requestReset();
		} else if (command.equals("clear")) {
			this.masterModel.emptyBoard();
			this.masterPanel.requestUpdate();
			this.masterPanel.requestReset();
		} else {
			System.out.println("Sad Trombone");
		}
	}
}
