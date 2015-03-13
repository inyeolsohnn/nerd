package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import control.WorldController;
import model.TrafficLight;

public class TrafficLightPanel extends JPanel {
	JPanel panel = new JPanel();
	JScrollPane jsp;
	BorderLayout bl = new BorderLayout();
	JButton btn;
	static final int INTERVAL_MIN = 0;
	static final int INTERVAL_MAX = 20;
	ArrayList<TrafficLight> lights;
	WorldController wController;
	CarSimView mainFrame;
	String TrifficLightID;
	
	public TrafficLightPanel(WorldController wController, CarSimView mainFrame) {
		this.wController = wController;
		this.mainFrame = mainFrame;
		btn = new CustomJButton("Return");
		this.add(btn);
		lights = wController.getLights();
		System.out.println(lights.size());
	
		int trafficLightSize = lights.size();
		GridLayout gl = new GridLayout(trafficLightSize+1,3,2,3);
		panel.setLayout(gl);
		
		
		for( int i = 0; i < trafficLightSize+1; i++){
			
			if(i==0){
				JLabel lbl = new JLabel("");
				JLabel lbl2 = new JLabel("greenInterval");
				JLabel lbl3 = new JLabel("redInterval");
				panel.add(lbl);
				panel.add(lbl2);
				panel.add(lbl3);
			}
			else{
				TrifficLightID=""+lights.get(i-1).getId(); //THIS IS NEW
				JLabel lbl = new JLabel(TrifficLightID);
				panel.add(lbl);
				JSlider greenSlider = new JSlider(JSlider.HORIZONTAL,
						INTERVAL_MIN, INTERVAL_MAX, (int)lights.get(i-1).getGreen()); //THIS IS NEW
				
				
				greenSlider.setName(TrifficLightID+"green");
				greenSlider.setPaintTicks(true);
				greenSlider.setPaintLabels(true);
				greenSlider.setMinorTickSpacing(2);
				greenSlider.addChangeListener(new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {
						
						JSlider source = (JSlider)e.getSource();
						// TODO Auto-generated method stub
						//arrays.get(currentTrafficlight-1).setGreenInterval(source.getValue());
						System.out.println(TrifficLightID+"greenInterval:"+source.getValue());
						
					}
					
				});

				JSlider redSlider = new JSlider(JSlider.HORIZONTAL,
						INTERVAL_MIN, INTERVAL_MAX, (int)lights.get(i-1).getRed()); //THIS IS NEW
				
				redSlider.setName(TrifficLightID+"red");
				redSlider.setPaintTicks(true);
				redSlider.setPaintLabels(true);
				redSlider.setMinorTickSpacing(2);
				redSlider.addChangeListener(new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {
						
						JSlider source = (JSlider)e.getSource();
						// TODO Auto-generated method stub
						//arrays.get(currentTrafficlight-1).setGreenInterval(source.getValue());
						System.out.println(TrifficLightID+"redInterval:"+source.getValue());
						
					}
					
				});
				
				
				panel.add(greenSlider);
				panel.add(redSlider);
				
			}
		
			
		}
		
		/*
		JSlider slider1 = new JSlider();
		JSlider slider2 = new JSlider();
		JSlider slider3 = new JSlider();
		JSlider slider4 = new JSlider();
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setMinorTickSpacing(2);*/
		panel.setLayout(gl);
		
		
		jsp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(500,700));
		jsp.setPreferredSize(new Dimension(1000,700));
		
		this.add(jsp);
		
	}

}