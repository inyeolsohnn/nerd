package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.TrafficLight;
import control.TrafficLightController;
import control.WorldController;

public class TrafficLightPanel extends JPanel implements ActionListener {
	private JPanel panel, btnHolderPanel;
	private JScrollPane jsp;
	private BorderLayout bl = new BorderLayout();
	private JButton rtrnButton;
	private static final int INTERVAL_MIN = 0;
	private static final int INTERVAL_MAX = 20;
	private ArrayList<TrafficLight> lights;

	private WorldController wController;
	private CarSimView mainFrame;

	private String TrifficLightID;
	private ImageIcon traffic_img;
	private ArrayList<JTextField> greenList, redList, initList;

	public TrafficLightPanel(WorldController wc,
			final TrafficLightController tlc, final CarSimView mainFrame, int id) {
		this.wController = wc;
		this.greenList = new ArrayList<JTextField>();
		this.redList = new ArrayList<JTextField>();
		this.initList = new ArrayList<JTextField>();
		this.mainFrame = mainFrame;

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

		JLabel iInterval = new JLabel("Initial Interval");
		JLabel gInterval = new JLabel("Green Interval");
		JLabel rInterval = new JLabel("Red Interval");

		Font font = new Font("Tahoma", Font.BOLD, 10);
		iInterval.setFont(font);
		gInterval.setFont(font);
		rInterval.setFont(font);

		JPanel a = new JPanel();
		a.setPreferredSize(new Dimension(920, 30));
		a.add(iInterval);
		a.add(gInterval);
		a.add(rInterval);
		a.add(new JLabel("                                                   "));

		panel.add(a);
		int trafficLightSize = lights.size();
		panel.setPreferredSize(new Dimension(920, 64 * trafficLightSize));

		System.out.println("size" + trafficLightSize);
		if (id == 0) {
			for (int i = 0; i < trafficLightSize; i++) {

				JPanel container = new JPanel();
				container.setBackground(Color.WHITE);
				container.setPreferredSize(new Dimension(920, 58));
				TrifficLightID = "" + lights.get(i).getId(); // THIS IS NEW

				JLabel lbl = new JLabel(TrifficLightID);
				lbl.setForeground(Color.black);
				if (lights.get(i).getId() == id) {
					lbl.setOpaque(true);
					container.setBackground(Color.red);
					lbl.setForeground(Color.blue);
				}
				container.add(lbl);
				JTextField initialTField = new JTextField(""
						+ lights.get(i).getInit(), 5);
				initList.add(initialTField);
				JTextField greenTField = new JTextField(""
						+ lights.get(i).getGreen(), 5);
				greenList.add(greenTField);
				JTextField redTField = new JTextField(""
						+ lights.get(i).getRed(), 5);
				redList.add(redTField);

				JButton submitBtn = new LightButton("submit",
						Integer.parseInt(TrifficLightID));
				submitBtn.setPreferredSize(new Dimension(100, 25));
				submitBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int id = ((LightButton) e.getSource()).getId();
						int index = id - 1;
						JTextField init = initList.get(index);

						JTextField red = redList.get(index);

						JTextField green = greenList.get(index);
						try {
							float redInt = Float.parseFloat(red.getText());
							float initInt = Float.parseFloat(init.getText());
							float greenInt = Float.parseFloat(green.getText());
							if (redInt < 0 || initInt < 0 || greenInt < 0)
								throw new Exception();

							tlc.setInterval("red", redInt, id);
							tlc.setInterval("green", greenInt, id);
							tlc.setInterval("initial", initInt, id);
						} catch (Exception exc) {
							JOptionPane
									.showMessageDialog(null,
											"check if the input is valid. Input needs to be a positive float number");
						}

					}

				});
				JButton removeBtn = new LightButton("remove",
						Integer.parseInt(TrifficLightID));
				removeBtn.setPreferredSize(new Dimension(100, 25));
				removeBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						tlc.removeLight(((LightButton) e.getSource()).getId());
						mainFrame.TrafficPanel(0);

					}

				});
				container.add(initialTField);
				container.add(greenTField);
				container.add(redTField);
				container.add(submitBtn);
				container.add(removeBtn);
				panel.add(container);

			}
		} else {
			JPanel container = new JPanel();
			container.setBackground(Color.WHITE);
			container.setPreferredSize(new Dimension(920, 58));
			TrifficLightID = "" + lights.get(id - 1).getId(); // THIS IS NEW

			JLabel lbl = new JLabel(TrifficLightID);
			lbl.setForeground(Color.black);

			container.add(lbl);
			JTextField initialTField = new JTextField(""
					+ lights.get(id - 1).getInit(), 5);
			initList.add(initialTField);
			JTextField greenTField = new JTextField(""
					+ lights.get(id - 1).getGreen(), 5);
			greenList.add(greenTField);
			JTextField redTField = new JTextField(""
					+ lights.get(id - 1).getRed(), 5);
			redList.add(redTField);

			JButton submitBtn = new LightButton("submit",
					Integer.parseInt(TrifficLightID));
			submitBtn.setPreferredSize(new Dimension(100, 25));
			submitBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int id = ((LightButton) e.getSource()).getId();
			
					JTextField init = initList.get(0);

					JTextField red = redList.get(0);

					JTextField green = greenList.get(0);
					try {
						float redInt = Float.parseFloat(red.getText());
						float initInt = Float.parseFloat(init.getText());
						float greenInt = Float.parseFloat(green.getText());
						if (redInt < 0 || initInt < 0 || greenInt < 0)
							throw new Exception();

						tlc.setInterval("red", redInt, id);
						tlc.setInterval("green", greenInt, id);
						tlc.setInterval("initial", initInt, id);
					} catch (Exception exc) {
						JOptionPane
								.showMessageDialog(null,
										"check if the input is valid. Input needs to be a positive float number");
					}

				}

			});
			JButton removeBtn = new LightButton("remove",
					Integer.parseInt(TrifficLightID));
			removeBtn.setPreferredSize(new Dimension(100, 25));
			removeBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tlc.removeLight(((LightButton) e.getSource()).getId());
					mainFrame.TrafficPanel(0);

				}

			});
			container.add(initialTField);
			container.add(greenTField);
			container.add(redTField);
			container.add(submitBtn);
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
		jsp.setPreferredSize(new Dimension(920, 500));
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