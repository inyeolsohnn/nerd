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
		
		Font font = new Font("Tahoma",Font.BOLD,20);
		iInterval.setFont(font);
		gInterval.setFont(font);
		rInterval.setFont(font);

		int trafficLightSize = lights.size();
		panel.setPreferredSize(new Dimension(920, 64 * trafficLightSize));
	
		System.out.println("size" + trafficLightSize);
		for (int i = 0; i < trafficLightSize; i++) {

			JPanel container = new JPanel();
			
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
			JTextField redTField = new JTextField("" + lights.get(i).getRed(),
					5);
			redList.add(redTField);
			container.setBackground(Color.WHITE);

			/*
			 * JSlider initialSlider = new JSlider(JSlider.HORIZONTAL,
			 * INTERVAL_MIN, INTERVAL_MAX, (int) lights.get(i) .getInit()); //
			 * THIS IS NEW
			 * 
			 * initialSlider.setName(TrifficLightID);
			 * initialSlider.setPaintTicks(true);
			 * initialSlider.setPaintLabels(true);
			 * initialSlider.setMinorTickSpacing(1);
			 * initialSlider.setBackground(Color.WHITE);
			 * initialSlider.setMajorTickSpacing(5);
			 * initialSlider.addChangeListener(new ChangeListener() {
			 * 
			 * private TrafficLightController tControl = tlc;
			 * 
			 * @Override public void stateChanged(ChangeEvent e) {
			 * 
			 * JSlider source = (JSlider) e.getSource();
			 * 
			 * // initial slider int id = Integer.parseInt(source.getName());
			 * int value = source.getValue(); tlc.setInterval("initial", value,
			 * id); System.out.println(source.getName() + source.getValue());
			 * 
			 * }
			 * 
			 * });
			 * 
			 * 
			 * JSlider greenSlider = new JSlider(JSlider.HORIZONTAL,
			 * INTERVAL_MIN, INTERVAL_MAX, (int) lights.get(i).getGreen()); //
			 * THIS IS // NEW
			 * 
			 * greenSlider.setName(TrifficLightID);
			 * greenSlider.setPaintTicks(true);
			 * greenSlider.setPaintLabels(true);
			 * greenSlider.setMinorTickSpacing(1);
			 * greenSlider.setBackground(Color.WHITE);
			 * greenSlider.setMajorTickSpacing(5);
			 * greenSlider.addChangeListener(new ChangeListener() {
			 * 
			 * @Override public void stateChanged(ChangeEvent e) {
			 * 
			 * JSlider source = (JSlider) e.getSource();
			 * 
			 * // green slider int id = Integer.parseInt(source.getName()); int
			 * value = source.getValue(); tlc.setInterval("green", value, id);
			 * System.out.println(source.getName() + source.getValue());
			 * 
			 * }
			 * 
			 * });
			 * 
			 * JSlider redSlider = new JSlider(JSlider.HORIZONTAL, INTERVAL_MIN,
			 * INTERVAL_MAX, (int) lights.get(i).getRed()); // THIS IS NEW
			 * 
			 * redSlider.setName(TrifficLightID); redSlider.setPaintTicks(true);
			 * redSlider.setPaintLabels(true); redSlider.setMinorTickSpacing(1);
			 * redSlider.setBackground(Color.WHITE);
			 * redSlider.setMajorTickSpacing(5);
			 * 
			 * redSlider.addChangeListener(new ChangeListener() {
			 * 
			 * @Override public void stateChanged(ChangeEvent e) {
			 * 
			 * JSlider source = (JSlider) e.getSource(); // red slider int id =
			 * Integer.parseInt(source.getName()); int value =
			 * source.getValue(); tlc.setInterval("red", value, id);
			 * System.out.println(TrifficLightID + "redInterval:" +
			 * source.getValue());
			 * 
			 * }
			 * 
			 * });
			 */
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