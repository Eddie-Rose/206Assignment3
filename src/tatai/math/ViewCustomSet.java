package tatai.math;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

	private final File dir = new File("./CustomQuestionSet"); 




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

		if (!(dir.exists())) {
			dir.mkdir();
		}
		
		String[] txtFileList = dir.list();
		if (txtFileList != null) {
			for (int i = 0; i < txtFileList.length ; i++) {
				int pos = txtFileList[i].lastIndexOf(".");
				if (pos > 0) {
					txtFileList[i] = txtFileList[i].substring(0, pos);
				}
			}
		}
	
		list = new JList(txtFileList);
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

		
		
		viewCustomSetFrame.addWindowListener(new WindowAdapter()
		{

			// If window is closing, must set frame field to null or else when prompted to 
			//Open "SignUp" again it won't allow it.
			@Override
			public void windowClosing(WindowEvent e)
			{

				viewCustomSetFrame = null;
			}
		});

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
