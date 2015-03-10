package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.WorldController;

public class MainMenu extends JPanel implements ActionListener {
	JButton tButton, roundButton, interButton, fullSimulation;
	WorldController wControl;
	CarSimView mainFrame;

	public MainMenu(WorldController wControl, CarSimView mainFrame) {
		this.wControl = wControl;
		this.mainFrame = mainFrame;
		tButton = new JButton("T Junction");
		roundButton = new JButton("Roundabout");
		interButton = new JButton("Intersection");
		fullSimulation = new JButton("Full Simulation");
		tButton.addActionListener(this);
		this.add(tButton);
		this.add(roundButton);
		this.add(interButton);
		this.add(fullSimulation);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(tButton)) {
			
			mainFrame.simulationView();
			wControl.setTJunction();
		}
	}
}
