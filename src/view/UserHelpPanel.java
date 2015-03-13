package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class UserHelpPanel extends JPanel {

	private JScrollPane jscrollPane;
	private JPanel usrHelpPanel;
	private JButton rtnButton;
	private JLabel helpTitle, informationTitle;
	private Font largeFont, mediumFont;
	private JTextArea helpTextArea = new JTextArea(5,40);

	public UserHelpPanel() {
		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));

		usrHelpPanel = new JPanel();

		helpTextArea.setLineWrap(true);
		helpTextArea.setEditable(false);
		rtnButton = new CustomJButton("Return");
		largeFont = new Font("Tahoma", Font.BOLD, 20);
		mediumFont = new Font("Tahoma", Font.BOLD, 14);
		this.add(rtnButton);
		
		helpTitle = new JLabel("Help Guide:");
		helpTitle.setFont(largeFont);
		informationTitle = new JLabel("Information to help you use the system");
		informationTitle.setFont(mediumFont);

		jscrollPane = new JScrollPane(usrHelpPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscrollPane.setPreferredSize(new Dimension(500, 400));
		jscrollPane.setBackground(Color.WHITE);
		jscrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.BLACK));
		
		helpTextArea.setText("HELLO BLAH B;AH THSAFHASK nFHASGASF to use this t use that hbascaly im doing this and you ened to do that also because i said so okay");
		usrHelpPanel.add(helpTitle);
		usrHelpPanel.add(informationTitle);
		usrHelpPanel.add(helpTextArea);
		usrHelpPanel.setBackground(Color.WHITE);
		usrHelpPanel.setPreferredSize(new Dimension(500, 400));
		
		for(int i = 0; i<4; i++){
		int count = i;	
		JSlider slider = new JSlider();
		slider.setName("Slider"+i);
		
		
		}
		
		this.add(jscrollPane);

	}
}
