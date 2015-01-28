package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import model.Car;
import model.Road;
import control.WorldController;

public class CarSimView extends JFrame implements ActionListener {

	private Container mainContainer;
	private WorldController wController;
	private JButton stopButton, startButton;

	public CarSimView(String title) {
		super(title);
		mainContainer = this.getContentPane();
		FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(experimentLayout);
		stopButton = new JButton("STOP");
		stopButton.addActionListener(this);
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		this.add(stopButton);
		this.add(startButton);
	

	}

	public void setController(WorldController controller) {
		this.wController = controller;
		mainContainer.add(new simulationPanel(controller));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stopButton) {
			this.wController.pause();
		} else if (e.getSource() == startButton) {
			this.wController.start();
		}
	}

	class simulationPanel extends JPanel implements MouseListener {

		private WorldController control;

		public simulationPanel(WorldController control) {

			this.control = control;
			setSize(800, 600);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

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

		protected void paintComponent(Graphics g) {
			HashMap<Integer, Road> roads = control.getRoads();
			HashMap<Integer, Car> cars = control.getCars();

			for (Integer roadKey : roads.keySet()) {
				Road currentRoad = roads.get(roadKey);
				System.out.println("drawing road: " + currentRoad.getId());
			}
			for (Integer carKey : cars.keySet()) {
				Car currentCar = cars.get(carKey);
				System.out.println("drawing car: " + currentCar.getId());
			}

		}

	}
}
