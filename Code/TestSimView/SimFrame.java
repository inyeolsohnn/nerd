import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class SimFrame extends JPanel {
	
	public SimFrame(){
	createSimFrame();
	}
	
	public void createSimFrame(){
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createMatteBorder(
                3, 3, 3, 3, Color.black));
		this.add(new JLabel("CAR SIMULATION HERE"));
		this.setSize(new Dimension(900,900));
		this.setPreferredSize(new Dimension(890,850));
		
	}
	
}
