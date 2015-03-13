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
	private JButton stopButton, startButton, returnButton; // not used
	private BorderLayout borderLayout;

	public SimulationPanel(WorldController control, CarSimView mainFrame) {

		this.control = control;
		this.mainFrame = mainFrame;
		this.setPreferredSize(new Dimension(980, 740));
		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		this.setBackground(Color.WHITE);
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

	}

	protected void paintComponent(Graphics g) {
		ArrayList<Road> roads = control.getRoads();
		System.out.println("Painting roads");
		for (int i = 0; i < roads.size(); i++) {
			roads.get(i).paint(g);

		}
		ArrayList<Car> cars = new ArrayList<Car>(control.getCars().values());
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).paint(g);

		}
		ArrayList<TrafficLight> lights = control.getLights();
	}

	// Can this be remove there not used?
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(returnButton)) {
			System.out.println("main menu");
			this.control.pause();
			mainFrame.mainMenu();
		} else if (e.getSource().equals(startButton)) {
			this.control.start();
		} else if (e.getSource().equals(stopButton)) {
			this.control.pause();
		}

	}

}
