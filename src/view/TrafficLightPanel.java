package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class TrafficLightPanel extends JPanel {
	JPanel panel = new JPanel();
	JScrollPane jsp;
	BorderLayout bl = new BorderLayout();
	JButton btn;
	GridLayout gl = new GridLayout(5,2);
	


	public TrafficLightPanel() {
		btn = new CustomJButton("Return");
		
		JSlider slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(2);
		panel.setLayout(gl);
		
		panel.add(new JLabel("1"));
		panel.add(slider);
		panel.add(new JLabel("2"));
		panel.add(slider);
		this.add(btn);
		jsp = new JScrollPane(panel);
		panel.setBackground(Color.WHITE);
		jsp.setPreferredSize(new Dimension(500,700));
		
		this.add(jsp);
		
	}

}
