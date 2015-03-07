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

public class CarSimView extends JFrame  {

	private Container mainContainer;
	private WorldController wControl;
	private JButton stopButton, startButton;
	private MainMenu mainMenu;
	private SimulationPanel simPanel;

	public CarSimView(String title, WorldController wControl) {
		super(title);
		mainContainer = this.getContentPane();
		this.wControl=wControl;
		simPanel= new SimulationPanel(this.wControl, this);
		mainMenu= new MainMenu(this.wControl, this);
		
		
		
		this.setSize(1280, 960);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setVisible(true);

		mainContainer.removeAll();
		mainContainer.add(mainMenu);
	}
	
	

	public void mainMenu() {
		// TODO Auto-generated method stub
		//change panel  main menu
		mainContainer.removeAll();
		mainContainer.add(mainMenu);
		mainContainer.revalidate();
		mainContainer.repaint();
		
	}



	public void simulationView() {
		// TODO Auto-generated method stub
		mainContainer.removeAll();
		mainContainer.add(simPanel);
		mainContainer.revalidate();
		mainContainer.repaint();
		
	}
}
