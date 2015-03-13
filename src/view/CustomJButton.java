package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonModel;
import javax.swing.JButton;

/*
 * This class extends the the java JButton which allows for it to be 
 * customized and have a better appereance.
 */
public class CustomJButton extends JButton {

	private GradientPaint gradientPaint; // provides a way to fill a Shape with
	// a linear color gradient pattern
	private GradientPaint gradientPaint2;

	private final Color buttonColour1 = new Color(255, 255, 255);
	private final Color buttonColour2 = new Color(100, 100, 100);
	private final Color buttonColour3 = new Color(255, 0, 0);

	public CustomJButton(String text) {
		super(text);
		setFont(new Font("Tahoma", Font.BOLD, 12)); // Font propperties
		setForeground(Color.WHITE); // Colour of text
		setFocusable(false);
		setContentAreaFilled(false);
	}

	// By Overidding the paint method, the way the button
	// looks has been changed
	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g.create();
		/*
		 * Turn the simple graphic into Graphics2D which is an extenstion of
		 * graphic that provides more sophicasted control over shapes, and
		 * allows for the graphics to be cleaner through rendering
		 */

		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int height = getHeight(); // Height of the screen
		int width = getWidth(); // Width of the screen
		ButtonModel buttonModel = getModel();// The JButton Model

		// The button looks like this when nothing is happening
		gradientPaint = new GradientPaint(0, 0, buttonColour1, 0, height / 2,
				buttonColour2, true);

		graphics2d.setPaint(gradientPaint);

		if (!buttonModel.isPressed()) {
			gradientPaint2 = new GradientPaint(0, 0, new Color(0, 0, 0), 0,
					height - 1, new Color(0, 0, 0));
		} else {// When the button is pressed change colour
			gradientPaint = new GradientPaint(0, 0, buttonColour1, 0,
					height / 2, buttonColour3, true);
			graphics2d.setPaint(gradientPaint);
		}
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0,
				width - 1, height - 1, 10, 10);

		Shape shape = graphics2d.getClip();
		graphics2d.clip(r2d);
		graphics2d.fillRect(0, 0, width, height);
		graphics2d.setClip(shape);
		graphics2d.setPaint(gradientPaint2);
		graphics2d.drawRoundRect(0, 0, width - 1, height - 1, 10, 10); // A
																		// rectangle
																		// with
																		// rounded
																		// corners
		graphics2d.dispose(); // Realese the graphics2d object because we
								// need the graphics g component not graphics2d
		super.paintComponent(g);

	}
}
