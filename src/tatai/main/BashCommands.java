package tatai.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

/**
 * Class which contains all the necessary Bash/Linux commands.
 * Instantiated using the singleton design pattern.
 * 
 * 
 *
 */
public class BashCommands {

	private BashCommands() {}


	//Initially set the field to null 
	private static BashCommands instance = null; 



	public static BashCommands getInstance() {
		if (instance == null) {
			instance = new BashCommands();
		}

		return instance;
	}


	//Executes the Go Script which contains all the HTK files,
	//Reads the std output and returns all the necessary strings.

	public String excecuteGoScript() {

		String saidNumber = "";

		try {


			//Sets up the process builder and starts it
			String command = "cd MaoriNumbers ; ./Go";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitStatus = process.waitFor();

			//if the exit status of the command returns 0 we extract all the neccesasay strings
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


				//else we extract the std error and output it 	
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


	//Calls upon the file which records the voice 
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


	//Removes the voice recording after the next question is asked
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


	/**
	 * After the end of the level session we need to store the score
	 * This bash command stores the score into a text file
	 * 
	 * 
	 * @param score: The score in which the player has gotten when completing the session
	 * @param name: The name of the level they completed
	 */
	public void addStats(int score, String name) {
		try {
			String username = MainGUI.getUsername();
			String command = "cd User/"+username+" ; echo -e \" "+"$(date +%D) $(date +%T) "+username+ " " +name+" "+score+"/10\" >> stats.txt ; sort -k5 -nro stats.txt stats.txt";
			
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


	/**
	 * Clears the stats txt file 
	 */
	public void clearStats() {
		try {
			String username = MainGUI.getUsername();
			String command = "cd User/"+username+" ; > stats.txt";
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
	
	public void makeUserFolder() {
		try {
			String command = "if [ ! -d User ]; then mkdir -p User; cd User ; mkdir -p anonymous ; cd anonymous ; > stats.txt ; fi ";
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


	/**
	 * Creates the username personal directory and stores their personal info
	 * into the directory 
	 * 
	 * @param username: Name of their username and name of the directory we store the 
	 * @param fullName: Their full name is to be stored into a text file 
	 * @param password: Passowrd stored into a text file to be extracted when trying to log in
	 * @return int: 0 if error occurs, 1 if no errors occur
	 */
	public int makeUserDir(String username, String fullName, String password) {

		try {

			//If there is no "User" directory create it  
			String command = "if [ ! -d User ]; then mkdir -p User; fi";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();
			process.waitFor();





			//Tries to create the Username directory under the User directory 
			String command = "cd User ; mkdir " + username;
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process0 = pb.start();
			int exitStatus = process0.waitFor();


			//If exit 1 then there is an existing user with the same username, returns with nothing 
			if (exitStatus == 1) {
				JOptionPane.showMessageDialog(null, "Username taken, please create a new one");
				return 0;
			}

			//When creating the new user, create the info text file with it to store their details such as password and fullname 
			command = "cd User ; cd " + username + " ; echo \"" + fullName + "\" >> userinfo.txt ; echo \"" + password + "\" >> userinfo.txt"; 
			pb = new ProcessBuilder("bash", "-c", command);

			Process process1 = pb.start();



			exitStatus = process1.waitFor();


			//Shows the final message, shows if the process has failed or not
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


	/**
	 * Verifies if the log in is authentic or not
	 *  
	 * @param username: username extracted from the textField
	 * @param password: password extracted form the textfield
	 * @return String of the users full name if verified, else returns false
	 */
	public String verifyLogin (String username, String password) {

		try {

			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();

			s = s + "/User/" + username;
			File file = new File(s);

			//Checks if the username directory exists
			if (!file.exists()) {

				JOptionPane.showMessageDialog(null, "Invalid Username");
				return null;

			}



			else {
				FileReader fr = new FileReader(s + "/userinfo.txt");
				BufferedReader br = new BufferedReader(fr);

				String userFullName = br.readLine();
				String userPassword = br.readLine();

				br.close();


				//checks whether the password in the file is the same as the on extracted in the TextField 
				if (!(userPassword.equals(password))) {

					JOptionPane.showMessageDialog(null, "Invalid password");
					return null;

				}


				//If it is corrcet, outputs the users name
				else {

					return userFullName;

				}
			}





		} catch (Exception f) {
			f.printStackTrace();
		}



		return null;





	}


	/**
	 * Deletes the users details
	 * 
	 * @param username: users username
	 */
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



	public int createNewSet(String name) {


		try {

			//If there is no "CustomSet" directory create it  
			String command = "if [ ! -d CustomQuestionSet ]; then mkdir -p CustomQuestionSet; fi";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();
			process.waitFor();


			//Tries to create the new CustomSetname directory under the CustomSet directory 
			command = "cd CustomQuestionSet ; if [ ! -e " + name +".txt ]; then >>" + name + ".txt ; else exit 1; fi";
			pb = new ProcessBuilder("bash", "-c", command);

			Process process0 = pb.start();
			int exitStatus = process0.waitFor();


			//If exit 1 then there is an existing user with the same CustomSetName, returns with nothing 
			if (exitStatus == 1) {
				JOptionPane.showMessageDialog(null, "Custom Set name taken, please create a new one");
				return 1;
			}



		} catch (IOException | InterruptedException e) {
				e.printStackTrace();

		}
		return 0;


	}

	public void deleteSet (String name) {


		try {	

			String command = "cd CustomQuestionSet ; rm " + name + ".txt ";
			ProcessBuilder pb = new ProcessBuilder("bash","-c", command);

			Process process = pb.start();
			process.waitFor();



		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			
		}
	}
	
	
	public void addEquation (String txtFile, String equation, String answer) {
		
		try {	

			String command = "cd CustomQuestionSet ; echo " + equation + " >> " + txtFile + ".txt ; echo " + answer + " >> " + txtFile + ".txt ;"; 
			ProcessBuilder pb = new ProcessBuilder("bash","-c", command);

			Process process = pb.start();
			process.waitFor();



		} catch (IOException | InterruptedException e) {
			e.printStackTrace();

		}
		
		
	}



}

















