package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Car;
import model.CarPark;
import model.Road;
import model.TrafficLight;
import control.TrafficLightController;
import control.WorldController;

class SimulationPanel extends JPanel implements MouseListener {

	private WorldController control;
	private TrafficLightController tlc;
	private CarSimView mainFrame;
	private JButton stopButton, startButton, returnButton; // not used
	private BorderLayout borderLayout;
	private Integer selectedPark = null;

	public SimulationPanel(WorldController control, TrafficLightController tlc,
			CarSimView mainFrame) {

		this.control = control;
		this.tlc = tlc;
		this.mainFrame = mainFrame;
		this.setPreferredSize(new Dimension(980, 670));
		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
		this.setBackground(Color.WHITE);
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		this.addMouseListener(this);

	}

	protected void paintComponent(Graphics g) {

		ArrayList<Road> roads = control.getRoads();
		System.out.println("Painting roads");
		for (int i = 0; i < roads.size(); i++) {
			roads.get(i).paint(g);

		}
		for (int i = 0; i < roads.size(); i++) {
			roads.get(i).paintBorders(g);
		}

		ArrayList<TrafficLight> lights = control.getLights();
		for (int i = 0; i < lights.size(); i++) {
			lights.get(i).paint(g);
		}
		ArrayList<Car> cars = new ArrayList<Car>(control.getCars().values());
		for (int i = 0; i < cars.size(); i++) {
			cars.get(i).paint(g);

		}
		if (mainFrame.getAddingLight()) {
			ArrayList<CarPark> parks = this.control.getParks();
			for (int i = 0; i < parks.size(); i++) {
				parks.get(i).paint(g);
			}
		}
	}

	// Can this be remove there not used?

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// check if traffic light exists where it is clicked

		if (!this.mainFrame.getAddingLight()) {
			int selected = control.findLight(e.getPoint());
			if (selected != 0)
				mainFrame.TrafficPanel(selected);
			// create new trafficlight panel
			// change color
			// add to main panel
		} else if (this.mainFrame.getAddingLight()) {
			if (selectedPark == null) {
				selectedPark = control.findPark(e.getPoint());
				if (selectedPark != null) {
					JOptionPane.showMessageDialog(null, "park id : "
							+ selectedPark + " has been selected");
				}
			}

			else {
				tlc.addNewLight(selectedPark, e.getPoint());
				selectedPark = null;
				this.mainFrame.setAddingLight(false);
				this.repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
