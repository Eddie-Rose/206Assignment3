package tatai.main;

import java.awt.Component;


/**
 * 
 * Resize class which handles of the components resizing
 * 
 * @author Harpreet Singh
 *
 */
public class Resizable {
	
	private double xRatio;
	private double yRatio;
	private double widthRatio;
	private double heightRatio;
	private Component component;
	
	
	/**
	 * Gathers the information of the components 
	 * and stores the relative ratio of the component to the frame
	 * 
	 * @param c The component itself
	 * @param frameWidth The width of the original frame
	 * @param frameHeight The height of the original frame
	 */
	public Resizable(Component c, int frameWidth, int frameHeight) {
		component = c;
		xRatio = (double)frameWidth/c.getX();
		yRatio = (double)frameHeight/c.getY();
		widthRatio = (double)frameWidth/c.getWidth();
		heightRatio = (double)frameHeight/c.getHeight();
	}
	

	/**
	 * Resizes the components based on the new frame width/ height 
	 * 
	 * @param frameWidth new frame width
	 * @param frameHeight new frame height
	 */
	public void Resize(int frameWidth, int frameHeight) {
		component.setBounds((int)(frameWidth/xRatio), (int)(frameHeight/yRatio), (int)(frameWidth/widthRatio), (int)(frameHeight/heightRatio));
	}

}
