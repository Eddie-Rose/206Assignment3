package tatai;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

	public String excecuteGoScript() {

		String saidNumber = "";

		try {
			String command = "cd MaoriNumbers ; ./Go";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitStatus = process.waitFor();
			
			
			if (exitStatus == 0) {
				String line;
				boolean found = false;
				int wordCount = 0;
				while ((line = stdout.readLine()) != null) {
					if(line.equals("sil")) {
						found = !found;
					}
					if(found == true && !line.equals("sil")) {
						if(wordCount > 0) {
							saidNumber = saidNumber + " " + line;
						} else {

							saidNumber = saidNumber + line;

						}
						wordCount++;
					}

				}

			} else {
				String line;
				while ((line = stderr.readLine()) != null) {
					System.err.println(line);
					
				}
			}


		} catch (Exception g) {
			g.printStackTrace();
		}

		return saidNumber;


	}
	
	public void playback() {
		try {
			String command = "cd MaoriNumbers ; aplay foo.wav";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitStatus = process.waitFor();

			if (exitStatus == 0) {
				String line;
				while ((line = stdout.readLine()) != null) {
					System.out.println(line);
				}
			} else {
				String line;
				while ((line = stderr.readLine()) != null) {
					System.err.println(line);
				}
			}

		} catch (Exception f) {
			f.printStackTrace();
		}
	}
	
	public void remove() {
		try {
			String command = "cd MaoriNumbers ; rm foo.wav";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitStatus = process.waitFor();

			if (exitStatus == 0) {
				String line;
				while ((line = stdout.readLine()) != null) {
					System.out.println(line);
				}
			} else {
				String line;
				while ((line = stderr.readLine()) != null) {
					System.err.println(line);
				}
			}

		} catch (Exception f) {
			f.printStackTrace();
		}
	}
		
		public void addStats(int score, String name) {
			try {
				String command = "echo -e \" "+"$(date +%D) $(date +%T) "+name+" "+score+"/10\" >> stats.txt ; sort -k4 -nro stats.txt stats.txt";
				//\"$((`cat stats.txt | wc -l` + 1)))
				ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

				Process process = pb.start();

				BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				int exitStatus = process.waitFor();

				if (exitStatus == 0) {
					String line;
					while ((line = stdout.readLine()) != null) {
						System.out.println(line);
					}
				} else {
					String line;
					while ((line = stderr.readLine()) != null) {
						System.err.println(line);
					}
				}

			} catch (Exception f) {
				f.printStackTrace();
			}
			
	}
		
		public void clearStats() {
			try {
				String command = "> stats.txt";
				ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

				Process process = pb.start();

				BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				int exitStatus = process.waitFor();

				if (exitStatus == 0) {
					String line;
					while ((line = stdout.readLine()) != null) {
						System.out.println(line);
					}
				} else {
					String line;
					while ((line = stderr.readLine()) != null) {
						System.err.println(line);
					}
				}

			} catch (Exception f) {
				f.printStackTrace();
			}
	}
	
		// not needed right now
		public void sortStats() {
			try {
				String command = "sort -k4 -nro stats.txt stats.txt";
				ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

				Process process = pb.start();

				BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				int exitStatus = process.waitFor();

				if (exitStatus == 0) {
					String line;
					while ((line = stdout.readLine()) != null) {
						System.out.println(line);
					}
				} else {
					String line;
					while ((line = stderr.readLine()) != null) {
						System.err.println(line);
					}
				}

			} catch (Exception f) {
				f.printStackTrace();
			}
		}



}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		