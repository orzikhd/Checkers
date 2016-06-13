package CheckersPackage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
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
	
	public MenuPanel(CheckersModel masterModel, CheckersPanel masterPanel) {
		super();
		
		this.masterModel = masterModel;
		this.masterPanel = masterPanel;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel titleLabel = new JLabel();
		Font titleFont = new Font("Verdana", Font.BOLD, 40);
		titleLabel.setFont(titleFont);
		titleLabel.setText("Checkers!");
		this.add(titleLabel);
		
		/*
			JPanel panel = new JPanel();
			panel.add(new JButton(new AbstractAction("name of button") {
			    public void actionPerformed(ActionEvent e) {
			        //do stuff here
			    }
			}));
		 */
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
