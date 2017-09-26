package tatai;

import javax.swing.SwingWorker;

public class GameHandler extends SwingWorker<String, Void> {

	@Override
	protected String doInBackground() throws Exception {
		BashCommands commands = BashCommands.getInstance();
		String maoriNumber = commands.excecuteGoScript();
		return maoriNumber;
	}
	

}
