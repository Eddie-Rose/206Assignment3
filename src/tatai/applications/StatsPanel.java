package tatai.applications;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tatai.main.BashCommands;
import tatai.main.MainGUI;
import tatai.main.Resizable;

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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;

public class StatsPanel extends JFrame {

	private JPanel contentPane;
	private BufferedReader input;
	private static String path;
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JTable table;
	DefaultTableModel tableModel;
	JButton btnLeaderboard;
	JButton btnPersonal;
	JButton btnClear;
	private int frameWidth = 1100;
	private int frameHeight = 700;

	/**
	 * Create the frame.
	 */
	public StatsPanel() {
		String username = MainGUI.getUsername();
		path = "./User/"+username+"/stats.txt";
		
		setTitle("Statistics");
		setVisible(true);
//		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStatisitcs = new JLabel("Statistics");
		lblStatisitcs.setFont(new Font("DejaVu Sans", Font.BOLD, 50));
		lblStatisitcs.setBounds(352, 55, 287, 36);
		contentPane.add(lblStatisitcs);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BashCommands commands = BashCommands.getInstance();
				commands.clearStats();
				tableModel.setRowCount(0);
				//display();
				
			}
		});
		btnClear.setBounds(887, 397, 156, 87);
		contentPane.add(btnClear);
		
		JButton btnMainMenu = new JButton("<html>Main Menu</html>");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnMainMenu.setBounds(887, 511, 156, 87);
		contentPane.add(btnMainMenu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 157, 804, 441);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		String columns[] = {"Rank","Date", "Time", "Name","Level", "Score"};
		
		 
		    // specify number of columns
		    tableModel = new DefaultTableModel(0,6){
		        public boolean isCellEditable(int row, int column)
		        {
		          return false;
		        }
		      };
		    tableModel.setColumnIdentifiers(columns);
			table.setModel(tableModel);
			
			btnPersonal = new JButton("Personal");
			btnPersonal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableModel.setRowCount(0);
					path = "./User/"+username+"/stats.txt";
					display();
					btnPersonal.setEnabled(false);
					btnLeaderboard.setEnabled(true);
				}
			});
			btnPersonal.setEnabled(false);
			btnPersonal.setBounds(887, 153, 156, 87);
			contentPane.add(btnPersonal);
			
			btnLeaderboard = new JButton("Leaderboard");
			btnLeaderboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableModel.setRowCount(0);
					path = "./User/stats.txt";
					display();
					btnLeaderboard.setEnabled(false);
					btnClear.setEnabled(false);
					btnPersonal.setEnabled(true);
				}
			});
			btnLeaderboard.setBounds(887, 268, 156, 87);
			contentPane.add(btnLeaderboard);
		display();
		
		Resizable[] resizableComp = new Resizable[6];
		
		resizableComp[0] = new Resizable(lblStatisitcs, frameWidth, frameHeight);
		resizableComp[1] = new Resizable(btnClear, frameWidth, frameHeight);
		resizableComp[2] = new Resizable(btnMainMenu, frameWidth, frameHeight);
		resizableComp[3] = new Resizable(scrollPane_1, frameWidth, frameHeight);
		resizableComp[4] = new Resizable(btnPersonal, frameWidth, frameHeight);
		resizableComp[5] = new Resizable(btnLeaderboard, frameWidth, frameHeight);
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				
				for(Resizable comp : resizableComp) {
					comp.Resize(c.getWidth(), c.getHeight());
				}
			}
		});
		
	}
	
	public void display() {
		try
        {
			String line;
			String[] row = new String[] {};
			int i = 1;
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
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
          // do nothing if file doesn't exist
        }
	}
	
	public static String getHighScore() {
		 String highScore = "0";
		 String username = MainGUI.getUsername();
		try
        {
			String line;
			FileReader fr = new FileReader("./User/"+username+"/stats.txt");
			BufferedReader br = new BufferedReader(fr);
			if((line = br.readLine()) != null) {
				highScore = line.split(" ")[5];
			}
            br.close();
            return highScore;
        }
        catch(Exception e)
        {       
           // Do nothing if file does not exist
        }
		return highScore;
		
	}
}
