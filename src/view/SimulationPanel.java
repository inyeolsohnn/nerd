package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Car;
import model.Road;
import control.WorldController;

class SimulationPanel extends JPanel implements ActionListener {

	private WorldController control;
	private CarSimView mainFrame;
	private JButton stopButton, startButton, returnButton;

	public SimulationPanel(WorldController control, CarSimView mainFrame) {

		this.control = control;
		this.mainFrame=mainFrame;

		//setSize(800, 600);
		FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(experimentLayout);
		
		stopButton = new JButton("STOP");
		stopButton.addActionListener(this);
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		returnButton= new JButton("Return");
		this.add(stopButton);
		this.add(startButton);
		this.add(returnButton);
		returnButton.addActionListener(this);
		setPreferredSize(new Dimension(1280,800));
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	
		this.setPreferredSize(new Dimension(1100,800));
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		this.setBackground(Color.WHITE);

	}


	
	protected void paintComponent(Graphics g) {
		ArrayList<Road> roads = control.getRoads();
		ArrayList<Car> cars = control.getCars();
	


	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(returnButton)){
			this.control.pause();
			
			System.out.println("main menu");
			mainFrame.mainMenu();
		}else if(e.getSource().equals(startButton)){
			this.control.start();
		}else if(e.getSource().equals(stopButton)){
			this.control.pause();
		}
		
	}



}
