package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import control.WorldController;

public class ConsolePanel extends JPanel implements ActionListener {

	private WorldController wController;
	private CarSimView mainFrame;
	private Font font = new Font("Tahoma", Font.BOLD, 20);
	private JButton startButton, stopButton, returnButton, trafficlightButton,
			helpButton, resetButton;
	private JLabel cntrlPanel_gfx, carSpawnLabel, teamLabel;
	private JPanel bottomButtonPanel, topButtonPanel;
	private JSlider carSpawnSlider = new JSlider(0, 10, 3);
	private BorderLayout borderLayout = new BorderLayout();
	DynamicChart demo;
	private ImageIcon cntrlPanel_img, teamlogo_img;
	
	public ConsolePanel(WorldController wController, CarSimView mainFrame) {
		this.mainFrame = mainFrame;
		this.wController = wController;
		this.setPreferredSize(new Dimension(200, 800));
		
		this.setLayout(borderLayout);

		topButtonPanel = new JPanel();
		bottomButtonPanel = new JPanel();
		demo = new DynamicChart("", this.wController);
		
		Font font = new Font("Tahoma", Font.BOLD, 14);
		carSpawnLabel = new JLabel("Car Spawn Rate");
		carSpawnLabel.setFont(font);
		cntrlPanel_gfx = new JLabel();
		cntrlPanel_img =new ImageIcon("src" + File.separator + "gfx"
				+ File.separator + "cntrl_gfx.gif");
		cntrlPanel_gfx.setIcon(cntrlPanel_img);
		teamLabel = new JLabel();
		teamlogo_img =new ImageIcon("src" + File.separator + "gfx"
				+ File.separator + "teamNERD_img.gif");
		teamLabel.setIcon(teamlogo_img);
		
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
		resetButton = new CustomJButton("RESET");
		resetButton.addActionListener(this);

		JLabel controlTitleLabel = new JLabel("Control Panel");
		controlTitleLabel.setFont(font);
		JLabel trafficTitleLabel = new JLabel("Adjust Traffic Ligts");

		carSpawnSlider.setPaintTicks(true);
		carSpawnSlider.setPaintLabels(true);
		carSpawnSlider.setMinorTickSpacing(2);

		topButtonPanel.add(cntrlPanel_gfx);
		topButtonPanel.add(stopButton);
		topButtonPanel.add(startButton);
		topButtonPanel.add(resetButton);
		topButtonPanel.add(trafficlightButton);
		topButtonPanel.add(carSpawnLabel);
		topButtonPanel.add(carSpawnSlider);
		topButtonPanel.add(demo.content);
		bottomButtonPanel.add(returnButton);
		bottomButtonPanel.add(helpButton);
		bottomButtonPanel.add(teamLabel);
		topButtonPanel.setPreferredSize(new Dimension(200, 500));
		bottomButtonPanel.setPreferredSize(new Dimension(200, 100));

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
			mainFrame.TrafficPanel(0);
		} else if (e.getSource() == helpButton) {
			this.wController.pause();
			mainFrame.HelpPanel();
		}else if(e.getSource() == resetButton){
			//RESET 
		}
	}

	public DynamicChart getDynamicChart() {
		// TODO Auto-generated method stub
		return this.demo;
	}
}
