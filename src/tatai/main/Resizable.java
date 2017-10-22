package tatai.main;

import java.awt.Component;

import javax.swing.JButton;

public class Resizable {
	
	private double xRatio;
	private double yRatio;
	private double widthRatio;
	private double heightRatio;
	private Component component;

	public Resizable(Component c, int frameWidth, int frameHeight) {
		component = c;
		xRatio = (double)frameWidth/c.getX();
		yRatio = (double)frameHeight/c.getY();
		widthRatio = (double)frameWidth/c.getWidth();
		heightRatio = (double)frameHeight/c.getHeight();
	}
	
	public static void main(String[] args) {
		JButton btnBegin = new JButton();
		btnBegin.setBounds(158, 190, 117, 25);
		Resizable a = new Resizable(btnBegin, 450, 300);
		System.out.println(a.xRatio);
		System.out.println(a.yRatio);
		System.out.println(a.widthRatio);
		System.out.println(a.heightRatio);
	}
	
	public void Resize(int frameWidth, int frameHeight) {
		component.setBounds((int)(frameWidth/xRatio), (int)(frameHeight/yRatio), (int)(frameWidth/widthRatio), (int)(frameHeight/heightRatio));
	}

}
