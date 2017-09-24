package tatai;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Advanc {

	JButton btnBegin = new JButton("Start");
	JLabel lblNewLabel = new JLabel();
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Advanc window = new Advanc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Advanc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdvancedLevel = new JLabel("Advanced Level");
		lblAdvancedLevel.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblAdvancedLevel.setBounds(128, 40, 192, 18);
		frame.getContentPane().add(lblAdvancedLevel);
		
		JTextArea txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setEnabled(false);
		txtrWelcomeToThe.setEditable(false);
		txtrWelcomeToThe.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtrWelcomeToThe.setText("Welcome to the advanced level,\nNumbers asked are from 10 to 99,\nPress \"Start\" to begin.");
		txtrWelcomeToThe.setBounds(103, 94, 264, 69);
		frame.getContentPane().add(txtrWelcomeToThe);
		
		
		btnBegin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (btnBegin.getText().equals("Start")) {
					btnBegin.setVisible(false);
					txtrWelcomeToThe.setVisible(false);
					AdvancedTest worker = new AdvancedTest();
					worker.execute();
				}
			}
		});
		btnBegin.setBounds(158, 190, 117, 25);
		frame.getContentPane().add(btnBegin);
		
		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(205, 120, 70, 15);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public class AdvancedTest extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			
			
			Random r = new Random();
			for (int i = 0; i < 10; i++) {

				
				int testNumber = r.nextInt(89) + 10;
				lblNewLabel.setText("" + testNumber);
				lblNewLabel.setVisible(true);
				
				btnBegin.setText("record");
				btnBegin.setVisible(true);

			}
			
			
			return null;
		}
		
		
	}
}
















