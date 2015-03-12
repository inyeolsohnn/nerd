package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Car;
import model.Road;
import model.TrafficLight;
import control.WorldController;

class SimulationPanel extends JPanel implements ActionListener {

	private WorldController control;
	private CarSimView mainFrame;
	private JButton stopButton, startButton, returnButton;

	public SimulationPanel(WorldController control, CarSimView mainFrame) {

		this.control = control;
		this.mainFrame = mainFrame;

		BorderLayout borderLayout = new BorderLayout();
		// FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(borderLayout);

		// stopButton = new JButton("STOP");
		// stopButton.addActionListener(this);
		// startButton = new JButton("Start");
		// startButton.addActionListener(this);
		// returnButton= new JButton("Return");
		// this.add(stopButton);
		// this.add(startButton);
		// this.add(returnButton);
		// returnButton.addActionListener(this);
		// setPreferredSize(new Dimension(500,500));

		this.setPreferredSize(new Dimension(980, 740));
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		this.setBackground(Color.WHITE);

	}

	protected void paintComponent(Graphics g) {
		ArrayList<Road> roads = control.getRoads();
		System.out.println("Painting roads");
		for (int i = 0; i < roads.size(); i++) {
			roads.get(i).paint(g);

		}
		ArrayList<Car> cars = control.getCars();
		for(int i = 0; i < cars.size(); i++){
			cars.get(i).paint(g);
			
		}
		ArrayList<TrafficLight> lights = control.getLights();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(returnButton)) {
			this.control.pause();

			System.out.println("main menu");
			mainFrame.mainMenu();
		} else if (e.getSource().equals(startButton)) {
			this.control.start();
		} else if (e.getSource().equals(stopButton)) {
			this.control.pause();
		}

	}

}
