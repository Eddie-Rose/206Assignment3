package tatai;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Beginner extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Beginner frame = new Beginner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Beginner() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLabel = new JLabel("1");
		lblLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 30));
		lblLabel.setBounds(187, 66, 48, 64);
		contentPane.add(lblLabel);
		
		JLabel lblDfasf = new JLabel("Score:");
		lblDfasf.setBounds(27, 214, 70, 15);
		contentPane.add(lblDfasf);
		
		JLabel label = new JLabel("0/10");
		label.setBounds(90, 214, 70, 15);
		contentPane.add(label);
		
		JButton btnRecord = new JButton("record");
		btnRecord.setForeground(Color.BLACK);
		btnRecord.setBackground(Color.WHITE);
		btnRecord.setBounds(145, 110, 117, 25);
		btnRecord.setBorder(null);
		contentPane.add(btnRecord);
	}
}
