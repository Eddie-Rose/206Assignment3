package tatai.math;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewCustomSet {

	private static JFrame viewCustomSetFrame = null;
	
	private JButton btnPlay;
	private JButton btnDelete;
	
	
	
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
		
		
		
	}
	
	
}
