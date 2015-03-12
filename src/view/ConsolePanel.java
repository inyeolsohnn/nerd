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
import javax.swing.JSlider;

import control.WorldController;

public class ConsolePanel extends JPanel implements ActionListener{
	private JButton startButton,stopButton, returnButton, trafficlightButton;
	private JSlider carSpawnSlider = new JSlider(0,10,3);
	
	private WorldController wController;
	private CarSimView mainFrame;
	private Font font = new Font("Tahoma",Font.BOLD ,20);
	

	public ConsolePanel(WorldController wController, CarSimView mainFrame){
		this.mainFrame = mainFrame;
		this.wController = wController;
		
		this.setPreferredSize(new Dimension(200,800));
		this.setBackground(Color.WHITE);
		
		stopButton = new CustomJButton("Stop");
		stopButton.addActionListener(this);
		startButton = new CustomJButton("Start");
		startButton.addActionListener(this);
		returnButton = new CustomJButton("Return To Main Menu");
		returnButton.addActionListener(this);
		trafficlightButton = new CustomJButton("Adjust Traffic Lights");
		trafficlightButton.addActionListener(this);
		
		JLabel lbl = new JLabel("Controller Panel");
		lbl.setFont(font);
		JLabel lbl2 = new JLabel("Adjust Traffic Ligts");
		

		carSpawnSlider.setPaintTicks(true);
		carSpawnSlider.setPaintLabels(true);
		carSpawnSlider.setMinorTickSpacing(2);
		carSpawnSlider.setBackground(Color.WHITE);
		this.add(lbl);
		this.add(stopButton);
		this.add(startButton);
		this.add(returnButton);
		this.add(lbl2);
		this.add(trafficlightButton);
		this.add(new JLabel("Car Spawn Rate"));
		this.add(carSpawnSlider);
		
		
		mainFrame.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stopButton) {
			this.wController.pause();
		} else if (e.getSource() == startButton) {
			this.wController.start();
		}else if(e.getSource() == returnButton){
			this.wController.pause();
			System.out.println("Main Menu");
			mainFrame.mainMenu();
		}else if(e.getSource() == trafficlightButton){	
			System.out.println("Traffic Light Menu");
			mainFrame.TrafficPanel();
		}
	}
}
