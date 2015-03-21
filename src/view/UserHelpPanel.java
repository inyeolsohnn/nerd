package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.WorldController;

public class UserHelpPanel extends JPanel implements ActionListener{
	WorldController wController;
	CarSimView mainFrame;
	private JScrollPane jscrollPane;
	private JPanel usrHelpPanel, btnHolderPanel;
	private JButton rtrnButton;
	private JLabel helpTitle, aimTitle, trafficTitle;
	private ImageIcon helpTitle_img, aim_img, traffic_img;
	private JTextArea introTextArea, trafficTextArea;

	public UserHelpPanel(WorldController wController, CarSimView mainFrame) {
		this.wController = wController;
		this.mainFrame = mainFrame;

		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));

		usrHelpPanel = new JPanel();
		btnHolderPanel = new JPanel();

		introTextArea = new JTextArea(5, 40);
		introTextArea.setLineWrap(true);
		introTextArea.setEditable(false);
		trafficTextArea = new JTextArea(5, 40);
		trafficTextArea.setLineWrap(true);
		trafficTextArea.setEditable(false);
		rtrnButton = new CustomJButton("Return");
		rtrnButton.addActionListener(this);

		helpTitle = new JLabel();
		helpTitle_img = new ImageIcon("src" + File.separator + "gfx"
				+ File.separator + "helpTitle_gfx.gif");
		helpTitle.setIcon(helpTitle_img);
		aimTitle = new JLabel();
		aim_img = new ImageIcon("src" + File.separator + "gfx" + File.separator
				+ "aim_gfx.gif");
		aimTitle.setIcon(aim_img);
		trafficTitle = new JLabel();
		traffic_img = new ImageIcon("src" + File.separator + "gfx"
				+ File.separator + "trafficLight_gfx.gif");
		trafficTitle.setIcon(traffic_img);

		this.add(helpTitle);

		jscrollPane = new JScrollPane(usrHelpPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscrollPane.setPreferredSize(new Dimension(500, 400));
		jscrollPane.setBackground(Color.WHITE);
		jscrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.BLACK));

		introTextArea
				.setText("HELLO BLAH B;AH THSAFHASK nFHASGASF to use this t use that hbascaly im doing this and you ened to do that also because i said so okay");
		trafficTextArea.setText("talking about traffic lights");

		usrHelpPanel.add(aimTitle);
		usrHelpPanel.add(introTextArea);
		usrHelpPanel.add(trafficTitle);
		usrHelpPanel.add(trafficTextArea);
		usrHelpPanel.setBackground(Color.WHITE);
		usrHelpPanel.setPreferredSize(new Dimension(500, 400));
		btnHolderPanel.setPreferredSize(new Dimension(1000, 200));

		this.add(jscrollPane);
		this.add(btnHolderPanel);
		btnHolderPanel.add(rtrnButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == rtrnButton) { // T Junction choosen by user
			this.wController.pause();
			mainFrame.simulationView(); //Need to confirm where user goes back too
		}

	}
}