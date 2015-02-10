import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class UserFrame extends JPanel {

	public UserFrame(){
		createUserFrame(); //creates the simple view
		createJSliders(); //Adds JSliders to page. 
	}
	

	public void createUserFrame(){
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(
                3, 3, 3, 3, Color.BLACK));
		this.add(new JLabel("USER CONTROLS HERE"));
		this.setPreferredSize(new Dimension(350,850));
	}
	
	//Potenial creation of JSliders for user to increase and decrease. 
	private void createJSliders() {
		JSlider trafficSlider = new JSlider(JSlider.VERTICAL);
		trafficSlider.setVisible(true);
		trafficSlider.setBackground(Color.WHITE);
		trafficSlider.setPaintTicks(true);
		trafficSlider.setMajorTickSpacing(10);
		this.add(trafficSlider);
		
	}
	
	
}
