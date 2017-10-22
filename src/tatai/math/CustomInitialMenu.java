package tatai.math;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tatai.main.Level;

public class CustomInitialMenu {


	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private static JFrame customFrame = null;

	public static void CustomInitialMenuFrame() {


		if (customFrame != null) {
			customFrame.setVisible(true);
			return;
		}

		customFrame = new JFrame();
		customFrame.setLayout(null);
		customFrame.setResizable(false);
		customFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		customFrame.setSize(500, 700);
		customFrame.setLocation(dim.width/2-customFrame.getSize().width/2, dim.height/2-customFrame.getSize().height/2);


		JLabel lblCustomMenu = new JLabel("Custom Maths Menu");
		lblCustomMenu.setBounds(50, 0, 500, 100);
		lblCustomMenu.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		customFrame.getContentPane().add(lblCustomMenu);



		JButton btnCustomLevel = new JButton("Play Set");



		JButton createSet = new JButton("Create Set");
		createSet.setBounds(100, 300, 300, 200);
		customFrame.getContentPane().add(createSet);
		createSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				customFrame.dispose();
				CustomSetCreation.getInstance();
				


			}
		});


		customFrame.setVisible(true);




	}
}
