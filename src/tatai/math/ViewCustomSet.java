package tatai.math;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ViewCustomSet {

	private static JFrame viewCustomSetFrame = null;
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JButton btnPlay;
	private JButton btnDelete;
	private JList list;
	private JLabel lblMainLabel;
	private JButton delete;
	private JButton play;
	private JButton add;
	
	
	
	private ViewCustomSet() {


		createViewCustomSetFrame();

	}


	private static ViewCustomSet instance;




	public static void getInstance(){

		if (viewCustomSetFrame == null) {

			instance = new ViewCustomSet();
			return;

		}
		else  {


			viewCustomSetFrame.setVisible(true);
			return;

		}
	}

	private void createViewCustomSetFrame() {
		
		viewCustomSetFrame = new JFrame();
		viewCustomSetFrame.setSize(800, 500);
		viewCustomSetFrame.setLocation(dim.width/2-viewCustomSetFrame.getSize().width/2, dim.height/2-viewCustomSetFrame.getSize().height/2);
		viewCustomSetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewCustomSetFrame.setLayout(null);
		viewCustomSetFrame.setResizable(false);
		
		
		lblMainLabel = new JLabel("Custom question sets!");
		lblMainLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		lblMainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainLabel.setBounds(20, 37, 800, 33);
		viewCustomSetFrame.getContentPane().add(lblMainLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 114, 646, 283);
		viewCustomSetFrame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblCustomSets = new JLabel("Custom Sets:");
		scrollPane.setColumnHeaderView(lblCustomSets);
		
		
		delete = new JButton("Delete");
		delete.setBounds(550, 420, 150, 50);
		viewCustomSetFrame.getContentPane().add(delete);
		
		
		play = new JButton("Play");
		play.setBounds(100, 420, 150, 50);
		viewCustomSetFrame.getContentPane().add(play);
		
		
		add = new JButton("add");
		add.setBounds(330, 420, 150, 50);
		viewCustomSetFrame.getContentPane().add(add);
		add.addActionListener(new AddCustomSetListener());
		
		viewCustomSetFrame.setVisible(true);
		
	}
	
	
	private class AddCustomSetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			CustomSetCreation.getInstance();
			viewCustomSetFrame.dispose();
			viewCustomSetFrame = null;
			
		}
		
		
	}
	
	
	
}
