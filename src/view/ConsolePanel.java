package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.WorldController;

public class ConsolePanel extends JPanel implements ActionListener{
	private JButton startButton,stopButton;
	private WorldController wController;
	private CarSimView mainFrame;
	private Font font = new Font("Tahoma",Font.BOLD ,20);
	
	public ConsolePanel(WorldController wController, CarSimView mainFrame){
		this.mainFrame = mainFrame;
		this.wController = wController;
		this.setPreferredSize(new Dimension(200,800));

		this.setBackground(Color.WHITE);
		
		stopButton = new CustomJButton("STOP");
		stopButton.addActionListener(this);
		startButton = new CustomJButton("Start");
		startButton.addActionListener(this);
		
		JLabel lbl = new JLabel("Controller Panel");
		lbl.setFont(font);
		JLabel lbl2 = new JLabel("Adjust Traffic Ligts");
		this.add(lbl);
		this.add(stopButton);
		this.add(startButton);
		this.add(lbl2);
		
		mainFrame.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stopButton) {
			this.wController.pause();
		} else if (e.getSource() == startButton) {
			this.wController.start();
		}
	}
}
