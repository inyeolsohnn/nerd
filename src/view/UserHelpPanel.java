package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private JLabel helpTitle, aimTitle, trafficTitle, collsionTitle, addTrafficLightLabel, collisionLabel;
	private ImageIcon helpTitle_img, aim_img, addTrafficLight_img, collision_img;
	private JTextArea introTextArea, trafficTextArea, collsionTextArea;
	private Font titleFont = new Font("Tahoma", Font.BOLD, 20);

	public UserHelpPanel(WorldController wController, CarSimView mainFrame) {
		this.wController = wController;
		this.mainFrame = mainFrame;

		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));

		usrHelpPanel = new JPanel();
		btnHolderPanel = new JPanel();

		introTextArea = new JTextArea(2, 50);
		introTextArea.setLineWrap(true);
		introTextArea.setEditable(false);
		trafficTextArea = new JTextArea(2, 50);
		trafficTextArea.setLineWrap(true);
		trafficTextArea.setEditable(false);
		collsionTextArea = new JTextArea(2, 50);
		collsionTextArea.setLineWrap(true);
		collsionTextArea.setEditable(false);
		
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
	
		
		trafficTitle = new JLabel("Traffic Light Information:");
		collsionTitle = new JLabel("Collision Information:");
		trafficTitle.setFont(titleFont);
		collsionTitle.setFont(titleFont);
		addTrafficLightLabel = new JLabel();
		addTrafficLight_img= new ImageIcon("src" + File.separator + "gfx"
					+ File.separator + "addTrafficLightHelp.png");
		addTrafficLightLabel.setIcon(addTrafficLight_img);
		
		collisionLabel = new JLabel();
		collision_img = new ImageIcon("src" + File.separator + "gfx"
					+ File.separator + "collisonHelp.png");
		collisionLabel.setIcon(collision_img);
		
		this.add(helpTitle);

		jscrollPane = new JScrollPane(usrHelpPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jscrollPane.setPreferredSize(new Dimension(700, 400));
		jscrollPane.setBackground(Color.WHITE);
		jscrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.BLACK));

		introTextArea
				.setText("The aim of this software is to allow you to..");
		trafficTextArea.setText("The image above illustrates the process in how to add traffic lights to the road. ");
		collsionTextArea.setText("The image above illustrates an example collsion, this is an indication that the way your trafficlights are set up are not good.");
		usrHelpPanel.add(aimTitle);
		usrHelpPanel.add(introTextArea);
		usrHelpPanel.add(trafficTitle);
		usrHelpPanel.add(addTrafficLightLabel);
		usrHelpPanel.add(trafficTextArea);
		usrHelpPanel.add(collsionTitle);
		usrHelpPanel.add(collisionLabel);
		usrHelpPanel.add(collsionTextArea);
		usrHelpPanel.setBackground(Color.WHITE);
		usrHelpPanel.setPreferredSize(new Dimension(700, 1200));
		btnHolderPanel.setPreferredSize(new Dimension(1000, 150));

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