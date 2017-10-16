package tatai.applications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tatai.main.BashCommands;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;

public class StatsPanel extends JFrame {

	private JPanel contentPane;
	private BufferedReader input;
	private String path;
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JTable table;
	DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public StatsPanel() {
		path = "./stats.txt";
		
		setTitle("Statistics");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStatisitcs = new JLabel("Statistics");
		lblStatisitcs.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblStatisitcs.setBounds(141, 0, 143, 36);
		contentPane.add(lblStatisitcs);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BashCommands commands = BashCommands.getInstance();
				commands.clearStats();
				tableModel.setRowCount(0);
				//display();
			}
		});
		btnClear.setBounds(356, 93, 80, 40);
		contentPane.add(btnClear);
		
		JButton btnMainMenu = new JButton("<html>Main Menu</html>");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnMainMenu.setBounds(356, 167, 80, 40);
		contentPane.add(btnMainMenu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 83, 304, 179);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);

		
		String columns[] = {"Rank","Date", "Time", "Level", "Score"};
		
		 
		    // specify number of columns
		    tableModel = new DefaultTableModel(0,5); 
		    tableModel.setColumnIdentifiers(columns);
			table.setModel(tableModel);
		display();
		
	}
	
	public void display() {
		try
        {
			String line;
			String[] row = new String[] {};
			int i = 1;
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
//			textArea.read(br, null);   
//            textArea.requestFocus();
            while((line = br.readLine()) != null) 
            {
            	row = line.split(" ");
            	row[0] = ""+i;
            	i++;
               tableModel.addRow(row); 
            }
            br.close();
        }
        catch(Exception e)
        {       
            e.printStackTrace();
        }
	}
	
	public static String getHighScore() {
		 String highScore = "0";
		try
        {
			String line;
			FileReader fr = new FileReader("./stats.txt");
			BufferedReader br = new BufferedReader(fr);
			if((line = br.readLine()) != null) {
				highScore = line.split(" ")[4];
			}
            br.close();
            return highScore;
        }
        catch(Exception e)
        {       
            e.printStackTrace();
        }
		return highScore;
		
	}
}
