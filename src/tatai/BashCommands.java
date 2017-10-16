package tatai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

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
				String command = "echo -e \" "+"$(date +%D) $(date +%T) "+name+" "+score+"/10\" >> stats.txt";
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

		
		
		public int makeUserDir(String username, String fullName, String password) {
			
			try {
				
				String command = "if [ ! -d User ]; then mkdir -p User; fi";
				ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

				Process process = pb.start();
				process.waitFor();

				
				
				
				
				command = "cd User ; mkdir " + username;
				pb = new ProcessBuilder("bash", "-c", command);

				Process process0 = pb.start();
				int exitStatus = process0.waitFor();

				if (exitStatus == 1) {
					JOptionPane.showMessageDialog(null, "Username taken, please create a new one");
					return 0;
				}
				
				command = "cd User ; cd " + username + " ; echo \"" + fullName + "\" >> userinfo.txt ; echo \"" + password + "\" >> userinfo.txt"; 
				pb = new ProcessBuilder("bash", "-c", command);

				Process process1 = pb.start();
			


				exitStatus = process1.waitFor();

				if (exitStatus == 0) {
					JOptionPane.showMessageDialog(null, "New user created, please logIn to record personal data");
					return 1;
				} else {
					JOptionPane.showMessageDialog(null, "Error, please try again");
					return 0;
				}

			} catch (Exception f) {
				f.printStackTrace();
			}
			return 0;
		}
			
		
		
		public String verifyLogin (String username, String password) {
			
			try {
				
				Path currentRelativePath = Paths.get("");
				String s = currentRelativePath.toAbsolutePath().toString();
				
				s = s + "/User/" + username;
				File file = new File(s);
				
				if (!file.exists()) {
					
					JOptionPane.showMessageDialog(null, "Invalid Username");
					return null;
					
				}
				
				

				else {
					FileReader fr = new FileReader(s + "/userinfo.txt");
					BufferedReader br = new BufferedReader(fr);

					String userFullName = br.readLine();
					String userPassword = br.readLine();

					if (!(userPassword.equals(password))) {

						JOptionPane.showMessageDialog(null, "Invalid password");
						return null;

					}
					
					else {
						
						return userFullName;
						
					}
				}

				
				
				
				
			} catch (Exception f) {
				f.printStackTrace();
			}
			
			
			
			return null;
			
			
			
			
			
		}
		
		
		
		public void deleteUser(String username) {
			
			try {
				String command = "cd User ; rm -rf " + username;
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

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		