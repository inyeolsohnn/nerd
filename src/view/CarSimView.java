package view;

import java.awt.BorderLayout;
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

public class CarSimView extends JFrame {

	private Container mainContainer;
	private WorldController wControl;
	private JButton stopButton, startButton;
	private MainMenu mainMenu;
	private SimulationPanel simPanel;
	private ConsolePanel consolePanel;

	public CarSimView(String title, WorldController wControl) {
		super(title);
		mainContainer = this.getContentPane();
		BorderLayout borderLayout = new BorderLayout();
		// FlowLayout experimentLayout = new FlowLayout();
		this.setLayout(borderLayout);
		this.wControl = wControl;
		simPanel = new SimulationPanel(this.wControl, this);
		mainMenu = new MainMenu(this.wControl, this);
		consolePanel = new ConsolePanel(this.wControl, this);

		this.setResizable(true);
		this.setSize(1280, 830);
		mainContainer.setBackground(Color.WHITE);
		consolePanel.setPreferredSize(new Dimension(200, 790));
		mainMenu.setPreferredSize(new Dimension(1000, 790));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		mainContainer.removeAll();
		mainContainer.add(mainMenu, BorderLayout.CENTER);

		// mainContainer.add(consolePanel, BorderLayout.WEST);
		// mainContainer.add(tabbedView.tabbedPane, BorderLayout.EAST);
	}

	public void mainMenu() {
		// TODO Auto-generated method stub
		// change panel main menu
		mainContainer.removeAll();
		mainContainer.add(mainMenu);
		mainContainer.revalidate();
		mainContainer.repaint();

	}

	public void simulationView() {
		// TODO Auto-generated method stub
		mainContainer.removeAll();
		mainContainer.add(simPanel, BorderLayout.CENTER);
		mainContainer.add(consolePanel, BorderLayout.WEST);
		mainContainer.revalidate();
		mainContainer.repaint();

	}
}
