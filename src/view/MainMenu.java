package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.WorldController;

public class MainMenu extends JPanel implements ActionListener {
	JButton tButton, roundButton, interButton, fullSimulation;
	WorldController wControl;
	CarSimView mainFrame;
	private JPanel buttonJPanel;
	private ImageIcon title_img;

	public MainMenu(WorldController wControl, CarSimView mainFrame) {
		this.wControl = wControl;
		this.mainFrame = mainFrame;
		buttonJPanel = new JPanel();
		
		tButton = new CustomJButton("T Junction");
		tButton.addActionListener(this);
		roundButton = new CustomJButton("Roundabout");
		roundButton.addActionListener(this);
		interButton = new CustomJButton("Intersection");
		interButton.addActionListener(this);
		fullSimulation = new CustomJButton("Full Simulation");
		fullSimulation.addActionListener(this);
		
		title_img = new ImageIcon("src" + File.separator + "gfx"
				+ File.separator + "title_gfx.gif");
		JLabel titleSimulationTitle = new JLabel();
		titleSimulationTitle.setIcon(title_img);
		this.add(titleSimulationTitle);

		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3,
				Color.BLACK));
		buttonJPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.BLACK));
		buttonJPanel.setBackground(Color.WHITE);
		buttonJPanel.setPreferredSize(new Dimension(400,200));
		this.add(buttonJPanel);

		buttonJPanel.add(tButton);
		buttonJPanel.add(roundButton);
		buttonJPanel.add(interButton);
		buttonJPanel.add(fullSimulation);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(tButton)) { // T Junction choosen by user

			mainFrame.simulationView();
			wControl.setTJunction();
		} else if (e.getSource().equals(roundButton)) { // Roundabout example
														// chosen by user
			mainFrame.simulationView();
			wControl.setRoundAbout();
		} else if (e.getSource().equals(interButton)) { // Intersection chooen
														// by user
			mainFrame.simulationView();
			// If there views set up for intersecton
		} else if (e.getSource().equals(fullSimulation)) { // Full simulation
															// chosen by user.

		}
	}
}
