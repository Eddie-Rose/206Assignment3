package tatai;

/**
 * Class which contains all the necessary Bash/Linux commands.
 * Instantiated using the singleton design pattern.
 * 
 * @author Edwin
 *
 */
public class BashCommands {
		
		private BashCommands() {}
	
	
		private static BashCommands instance = null; 
		
		
		
		public static BashCommands getInstance() {
			if (instance == null) {
				instance = new BashCommands();
			}
			
			return instance;
		}
		
		
}