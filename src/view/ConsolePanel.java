package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import view.DynamicChart;
import control.WorldController;

public class ConsolePanel extends JPanel implements ActionListener {

	private WorldController wController;
	private CarSimView mainFrame;
	private Font font = new Font("Tahoma", Font.BOLD, 20);
	private JButton startButton, stopButton, returnButton, trafficlightButton,
			helpButton;
	private JPanel bottomButtonPanel, topButtonPanel;
	private JSlider carSpawnSlider = new JSlider(0, 10, 3);
	private BorderLayout borderLayout = new BorderLayout();
	DynamicChart demo;
	public ConsolePanel(WorldController wController, CarSimView mainFrame) {
		this.mainFrame = mainFrame;
		this.wController = wController;
		this.setPreferredSize(new Dimension(200, 800));
		this.setBackground(Color.WHITE);
		this.setLayout(borderLayout);

		topButtonPanel = new JPanel();
		bottomButtonPanel = new JPanel();
		demo = new DynamicChart("", this.wController);
		this.add(topButtonPanel, BorderLayout.NORTH);
		//topButtonPanel
		this.add(bottomButtonPanel, BorderLayout.SOUTH);
		
		// JButton setup!
		stopButton = new CustomJButton("Stop");
		stopButton.addActionListener(this);
		startButton = new CustomJButton("Start");
		startButton.addActionListener(this);
		returnButton = new CustomJButton("Return To Main Menu");
		returnButton.addActionListener(this);
		trafficlightButton = new CustomJButton("Adjust Traffic Lights");
		trafficlightButton.addActionListener(this);
		helpButton = new CustomJButton("Help?");
		helpButton.addActionListener(this);

		JLabel controlTitleLabel = new JLabel("Control Panel");
		controlTitleLabel.setFont(font);
		JLabel trafficTitleLabel = new JLabel("Adjust Traffic Ligts");

		carSpawnSlider.setPaintTicks(true);
		carSpawnSlider.setPaintLabels(true);
		carSpawnSlider.setMinorTickSpacing(2);

		topButtonPanel.add(controlTitleLabel);
		topButtonPanel.add(stopButton);
		topButtonPanel.add(startButton);
		topButtonPanel.add(trafficTitleLabel);
		topButtonPanel.add(trafficlightButton);
		topButtonPanel.add(new JLabel("Car Spawn Rate"));
		topButtonPanel.add(carSpawnSlider);
		topButtonPanel.add(demo.content);
		bottomButtonPanel.add(returnButton);
		bottomButtonPanel.add(helpButton);

		topButtonPanel.setPreferredSize(new Dimension(200, 700));
		bottomButtonPanel.setPreferredSize(new Dimension(200, 80));

		mainFrame.add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stopButton) {
			this.wController.pause();
		} else if (e.getSource() == startButton) {
			this.wController.start();
		} else if (e.getSource() == returnButton) {
			this.wController.pause();
			System.out.println("Main Menu");
			mainFrame.mainMenu();
		} else if (e.getSource() == trafficlightButton) {
			System.out.println("Traffic Light Menu");
			this.wController.pause();
			mainFrame.TrafficPanel();
		} else if (e.getSource() == helpButton) {
			this.wController.pause();
			mainFrame.HelpPanel();
		}
	}

	public DynamicChart getDynamicChart() {
		// TODO Auto-generated method stub
		return this.demo;
	}
}
