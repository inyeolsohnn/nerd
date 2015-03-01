package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Car;
import model.Road;
import model.TrafficLight;
import control.WorldController;

public class CarSimView extends JFrame implements ActionListener {

	private Container mainContainer;
	private WorldController wController;
	private JButton stopButton, startButton;
	private int trafficlightnums;
	public CarSimView(String title , int trafficlightnums) {
		super(title);
		mainContainer = this.getContentPane();
		FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(experimentLayout);
		this.trafficlightnums = trafficlightnums;
		ArrayList <TrafficLight> lights = new ArrayList<TrafficLight>();
		for(int i=0;i<trafficlightnums;i++){
			
		}
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

			//setSize(800, 600);
			setPreferredSize(new Dimension(1000,800));
			setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
			this.setPreferredSize(new Dimension(1100,800));
			this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
			this.setBackground(Color.WHITE);

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
			ArrayList<Road> roads = control.getRoads();
			ArrayList<Car> cars = control.getCars();
			for(int i = 0;i<trafficlightnums;i++){
				paintTrafficLight();
			}
	

		}

		private void paintTrafficLight() {
			// TODO Auto-generated method stub
			
		}

	}
}
