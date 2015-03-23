package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.TrafficLight;
import control.TrafficLightController;
import control.WorldController;

public class TrafficLightPanel extends JPanel implements ActionListener {
	JPanel panel, btnHolderPanel;
	JScrollPane jsp;
	BorderLayout bl = new BorderLayout();
	JButton rtrnButton;
	static final int INTERVAL_MIN = 0;
	static final int INTERVAL_MAX = 20;
	ArrayList<TrafficLight> lights;

	WorldController wController;
	CarSimView mainFrame;
	TrafficLightController tlc;
	String TrifficLightID;
	private ImageIcon traffic_img;

	public TrafficLightPanel(WorldController wc,
			final TrafficLightController tlc, final CarSimView mainFrame, int id) {
		this.wController = wc;

		this.mainFrame = mainFrame;
		this.tlc = tlc;
		panel = new JPanel();
		btnHolderPanel = new JPanel();
		rtrnButton = new CustomJButton("Return");
		rtrnButton.addActionListener(this);
		lights = tlc.getLights();
		System.out.println(lights.size());

		JLabel trafficLightTitle = new JLabel();
		traffic_img = new ImageIcon("src" + File.separator + "gfx"
				+ File.separator + "adjustTrafficLight_gfx.png");
		trafficLightTitle.setIcon(traffic_img);

		int trafficLightSize = lights.size();
		panel.setPreferredSize(new Dimension(1000,64*trafficLightSize));
		
		System.out.println("size" + trafficLightSize);
		for (int i = 0; i < trafficLightSize; i++) {

			 	JPanel container = new JPanel();
			 	container.setPreferredSize(new Dimension(900,58));
				TrifficLightID = "" + lights.get(i).getId(); // THIS IS NEW

				JLabel lbl = new JLabel(TrifficLightID);
				lbl.setForeground(Color.black);
				if (lights.get(i).getId() == id) {
					lbl.setOpaque(true);
					container.setBackground(Color.red);
					lbl.setForeground(Color.blue);
				}
				container.add(lbl);
				JSlider initialSlider = new JSlider(JSlider.HORIZONTAL,
						INTERVAL_MIN, INTERVAL_MAX, (int) lights.get(i)
								.getInit()); // THIS IS NEW

				initialSlider.setName(TrifficLightID);
				initialSlider.setPaintTicks(true);
				initialSlider.setPaintLabels(true);
				initialSlider.setMinorTickSpacing(1);
				initialSlider.setBackground(Color.WHITE);
				initialSlider.setMajorTickSpacing(5);
				initialSlider.addChangeListener(new ChangeListener() {

					private TrafficLightController tControl = tlc;

					@Override
					public void stateChanged(ChangeEvent e) {

						JSlider source = (JSlider) e.getSource();

						// initial slider
						int id = Integer.parseInt(source.getName());
						int value = source.getValue();
						tlc.setInterval("initial", value, id);
						System.out.println(source.getName() + source.getValue());

					}

				});

				JSlider greenSlider = new JSlider(JSlider.HORIZONTAL,
						INTERVAL_MIN, INTERVAL_MAX, (int) lights.get(i)
								.getGreen()); // THIS IS NEW

				greenSlider.setName(TrifficLightID);
				greenSlider.setPaintTicks(true);
				greenSlider.setPaintLabels(true);
				greenSlider.setMinorTickSpacing(1);
				greenSlider.setBackground(Color.WHITE);
				greenSlider.setMajorTickSpacing(5);
				greenSlider.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {

						JSlider source = (JSlider) e.getSource();

						// green slider
						int id = Integer.parseInt(source.getName());
						int value = source.getValue();
						tlc.setInterval("green", value, id);
						System.out.println(source.getName() + source.getValue());

					}

				});

				JSlider redSlider = new JSlider(JSlider.HORIZONTAL,
						INTERVAL_MIN, INTERVAL_MAX, (int) lights.get(i)
								.getRed()); // THIS IS NEW

				redSlider.setName(TrifficLightID);
				redSlider.setPaintTicks(true);
				redSlider.setPaintLabels(true);
				redSlider.setMinorTickSpacing(1);
				redSlider.setBackground(Color.WHITE);
				redSlider.setMajorTickSpacing(5);
			
				redSlider.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {

						JSlider source = (JSlider) e.getSource();
						// red slider
						int id = Integer.parseInt(source.getName());
						int value = source.getValue();
						tlc.setInterval("red", value, id);
						System.out.println(TrifficLightID + "redInterval:"
								+ source.getValue());

					}

				});
				JButton removeBtn = new LightRemoveButton("remove",
						Integer.parseInt(TrifficLightID));
				removeBtn.setPreferredSize(new Dimension(100, 40));
				removeBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						tlc.removeLight(((LightRemoveButton) e.getSource())
								.getId());
						mainFrame.TrafficPanel(0);

					}

				});
				container.add(initialSlider);
				container.add(greenSlider);
				container.add(redSlider);
				container.add(removeBtn);
				panel.add(container);
			

		}

		/*
		 * JSlider slider1 = new JSlider(); JSlider slider2 = new JSlider();
		 * JSlider slider3 = new JSlider(); JSlider slider4 = new JSlider();
		 * slider1.setPaintTicks(true); slider1.setPaintLabels(true);
		 * slider1.setMinorTickSpacing(2);
		 */
		

		jsp = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setBackground(Color.WHITE);
		btnHolderPanel.setPreferredSize(new Dimension(1000, 200));
		jsp.setPreferredSize(new Dimension(1200, 500));
		jsp.setBackground(Color.WHITE);
		this.add(trafficLightTitle);
		this.add(jsp);
		this.add(btnHolderPanel);
		btnHolderPanel.add(rtrnButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == rtrnButton) { // T Junction choosen by user
			this.wController.pause();
			mainFrame.simulationView(); // Need to confirm where user goes back
										// too
		}

	}

}