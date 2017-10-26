package tatai.math;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import tatai.main.BashCommands;
import tatai.main.Level;


/**
 * 
 * This is the first Frame the user will see when 
 * they choose the custom option in the math menu
 * 
 * From here the user can choose to add, delete or play their 
 * custom Set.
 * 
 * @author Edwin Roesli and Harpreet Singh
 *
 */
public class ViewCustomSet {
	
	//
	private static JFrame viewCustomSetFrame = null;
	private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	
	private DefaultListModel<String> model;
	private JList<String> list;
	private JLabel lblMainLabel;
	private JButton btnDelete;
	private JButton btnPlay;
	private JButton btnAdd;
	
	
	//Create a new directory to store all the custom questions if it does
	//not exist
	private final File dir = new File("./CustomQuestionSet"); 



	//Private constructor to be called within the class
	private ViewCustomSet() {


		createViewCustomSetFrame();

	}


	@SuppressWarnings("unused")
	private static ViewCustomSet instance;



	//This static method is what is called outside of this class to ensure 
	//That only a single instance of this class is called
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
	
	/**
	 * Main method in this class
	 * This method sets all the components in the frame 
	 *  
	 */
	private void createViewCustomSetFrame() {
		
		
		//Set all the main components first and set where they are set at.
		viewCustomSetFrame = new JFrame();
		viewCustomSetFrame.setSize(800, 500);
		viewCustomSetFrame.setLocation(dim.width/2-viewCustomSetFrame.getSize().width/2, dim.height/2-viewCustomSetFrame.getSize().height/2);
		viewCustomSetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewCustomSetFrame.setLayout(null);
		viewCustomSetFrame.setResizable(false);

		
		//Set the main label of the Frame to show what this class is about.
		lblMainLabel = new JLabel("Custom question sets!");
		lblMainLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 35));
		lblMainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainLabel.setBounds(20, 37, 800, 33);
		viewCustomSetFrame.getContentPane().add(lblMainLabel);
		
		//Initialise a JScrollPane to have a place to set the Table containing all the sets.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 114, 646, 283);
		viewCustomSetFrame.getContentPane().add(scrollPane);
		
		
		//If the directory that stores the Custom set does not 
		//exist, create it 
		if (!(dir.exists())) {
			dir.mkdir();
		}
		
		//Set the new Default list model to store all the custom sets 
		//that is stored in the directory.
		model = new DefaultListModel<String>();
		
		//Stores the txtFiles found in the directory into a String array first
		String[] txtFileList = dir.list();
		if (txtFileList != null) {
			
			//Loops through the directory and lists the custom sets in the model list 
			for (int i = 0; i < txtFileList.length ; i++) {
				int pos = txtFileList[i].lastIndexOf(".");
				if (pos > 0) {
					txtFileList[i] = txtFileList[i].substring(0, pos);
				}
				//add the txt file name into the default model
				model.addElement(txtFileList[i]);
			}
		}
		
		//store the model into a JList
		list = new JList<String>();
		list.setModel(model);
		
		//add the JList into the scroll pane
		scrollPane.setViewportView(list);
		
		//Set the header of the scrollPane to be "Custom Sets: "
		JLabel lblCustomSets = new JLabel("Custom Sets:");
		scrollPane.setColumnHeaderView(lblCustomSets);

		
		//Sets the delete, play and add button into the frame and 
		//Set its listeners.
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(550, 420, 150, 50);
		btnDelete.addActionListener(new DeleteListener());
		viewCustomSetFrame.getContentPane().add(btnDelete);


		btnPlay = new JButton("Play");
		btnPlay.setBounds(100, 420, 150, 50);
		btnPlay.addActionListener(new PlayListener());
		viewCustomSetFrame.getContentPane().add(btnPlay);


		btnAdd = new JButton("add");
		btnAdd.setBounds(330, 420, 150, 50);
		viewCustomSetFrame.getContentPane().add(btnAdd);
		btnAdd.addActionListener(new AddCustomSetListener());

		
		//Listen to when the window is closed, and set the viewCustomFrame 
		//field to null so it can be instantiated again.
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
	
	
	//refresh method to refresh the whole model to take into account 
	//the change that happened
	public void refresh() {
		
		
		//Set the default model 
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		//Set the String array for the text file to be stored in 
		String[] txtFileList = dir.list();
		if (txtFileList != null) {
			
			//Loops through the whole directory so trim the file name (remove the .txt part)
			//and add it to the model list
			for (int i = 0; i < txtFileList.length ; i++) {
				int pos = txtFileList[i].lastIndexOf(".");
				if (pos > 0) {
					txtFileList[i] = txtFileList[i].substring(0, pos);
				}
				
				model.addElement(txtFileList[i]);
			}
		}
		
		
		//set the model
		list.setModel(model);
		
	}
	
	//Set the add listener to dispose of this frame and create the
	//CustomSetCreate class
	private class AddCustomSetListener implements ActionListener {
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CustomSetCreation.getInstance();
			viewCustomSetFrame.dispose();
			viewCustomSetFrame = null;

		}


	}
	
	//Set the delete listener to delete the chosen set,
	//if no set is chosen return with a messagePane
	private class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (list.getSelectedValue() == null) {
				
				JOptionPane.showMessageDialog(null, "No Set has been selected");
				
			}
			else {
				
				String name = (String) list.getSelectedValue();
				BashCommands commands = BashCommands.getInstance();
				commands.deleteSet(name);
				refresh();
				
				
			}
		
		}
		
	}
	
	//Set the play listener to play the chosen set 
	//if no set has been chosen, return with a message pane.
	private class PlayListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(list.getSelectedValue() == null) {
				
				JOptionPane.showMessageDialog(null, "No Set has been selected");
				
			}
			else {
				
				String name = (String) list.getSelectedValue();
				
				//Initialise the hashMap and invoke the Bash command's getInstance() 
				//method to read the txt file and store the questions and answers into a 
				//hash map
				
				Map<String, Integer> answerMap = new HashMap<String, Integer>();
				BashCommands commands = BashCommands.getInstance();
				answerMap = commands.getSetQuestions(name);
				
				//if the map is null delete the set as it is faulty
				if (answerMap == null) {
					commands.deleteSet(name);
					
				}
				//else it returns as it should and initialise the custom level frame
				else {
					
					
					@SuppressWarnings("unused")
					Level custom = new CustomMath(name, 0, 0, answerMap);
				}
				
				
				
			}
			
		}
		
		
	}
	



}
