package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import control.WorldController;

public class TabbedView extends JPanel{
	private JPanel TJunctionPanel,RoundaboutPanel,IntersectionPanel,FullPanel;
	WorldController wControl;
	CarSimView mainFrame;
	String a;
	int b;
	public JTabbedPane tabbedPane;
	public Container mainContainer;
	public SimulationPanel simPanel;
	
	public TabbedView(final WorldController wControl, final CarSimView mainFrame, Container mainContainer){
		tabbedPane = new JTabbedPane();
		
		this.wControl = wControl;
		this.mainFrame = mainFrame;
		this.mainContainer = mainContainer;
		simPanel= new SimulationPanel(wControl, mainFrame);
		TJunctionPanel = new JPanel();
		RoundaboutPanel = new JPanel();
		IntersectionPanel = new JPanel();
		FullPanel = new JPanel();
		TJunctionPanel.add(simPanel);
		tabbedPane.addTab("T Junction", null, TJunctionPanel,
                "T Junction");
		tabbedPane.addTab("Roundabout", null, RoundaboutPanel,
                "Roundabout");
		tabbedPane.addTab("Intersection", null, IntersectionPanel,
                "Intersection");
		tabbedPane.addTab("Full Simulation", null, FullPanel,
                "Full Simulation");
		
		tabbedPane.setBackground(Color.BLUE); //Background Colour of TabbedPane buttons 
	
		tabbedPane.setPreferredSize(new Dimension(1000,780));
		tabbedPane.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
						.equals("T Junction")){
				
					System.out.println("T Junction");
				}
				else if(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
						.equals("Roundabout")){
					System.out.println("Roundabout");
					
				}
				else if(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
						.equals("Intersection")){
					System.out.println("Intersection");
					
				}
				else if(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())
						.equals("Full Simulation")){
					System.out.println("Full Simulation");
					
				}
			}
			
		});
		
	}
}
