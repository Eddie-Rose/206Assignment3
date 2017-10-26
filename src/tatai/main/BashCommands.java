package tatai.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

/**
 * Class which contains all the necessary Bash/Linux commands.
 * Instantiated using the singleton design pattern.
 * 
 * Author Edwin Roesli and Harpreet Singh
 *
 */
public class BashCommands {

	private BashCommands() {}


	//Initially set the field to null 
	private static BashCommands instance = null; 


	//singleton part of the class
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

			//creates the command and executes it
			String command = "cd MaoriNumbers ; aplay foo.wav";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitStatus = process.waitFor();

			//if it passes or fails, print out the error/ success statement
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

			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();

			command = "cd User ; echo -e \" "+"$(date +%D) $(date +%T) "+username+ " " +name+" "+score+"/10\" >> stats.txt ; sort -k5 -nro stats.txt stats.txt";

			pb = new ProcessBuilder("bash", "-c", command);

			process = pb.start();

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


	/**
	 * Checks if the "User" folder exits, if not create it and the anonymous folder
	 *User folder stores all the users info such as log in details and scores
	 */

	public void makeUserFolder() {
		try {
			String command = "if [ ! -d User ]; then mkdir -p User; cd User ; > stats.txt ; mkdir -p anonymous ; cd anonymous ; > stats.txt ; fi ";
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
			command = "cd User ; mkdir " + username;
			pb = new ProcessBuilder("bash", "-c", command);

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

				//Shows a prompt that the username is invalid
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
	 * Deletes the users folder and  details
	 * 
	 * @param username: users username
	 */
	public void deleteUser(String username) {

		try {

			//Command for removing the users folder and contents 
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


	/**
	 * 
	 * Attempts to create a new set of custom questions, store the file in the "CustomSet" directory
	 * and stores the custom questions in a text file
	 * 
	 * @param name: The name of the new Set
	 * @return int: The state of return, if 0, then it was successful, else an error occured
	 */
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

	/**
	 * Tries to delete the Set's txt file containing all the custom Question data
	 * 
	 * @param name Name of the Set to be deleted
	 */
	public void deleteSet (String name) {


		try {	


			//Command and process to delete the Set

			String command = "cd CustomQuestionSet ; rm " + name + ".txt ";
			ProcessBuilder pb = new ProcessBuilder("bash","-c", command);

			Process process = pb.start();
			process.waitFor();



		} catch (IOException | InterruptedException e) {
			e.printStackTrace();

		}
	}


	/**
	 * Appends the custom text file with the new equation and answer
	 * 
	 * @param txtFile Name of the custom Set text file
	 * @param equation The inputed equation in String form
	 * @param answer	The inputed answer to the equation in String form
	 */
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


	/**
	 * 
	 * Reads the Custom text file and stores the question and answers into a HashMap<String, Integer>
	 * If an error occurs during this process return null and delete this file
	 * 
	 * @param name Name of the custom Set
	 * @return The Map which stores all the answers and Questions of the custom Set
	 */
	public Map<String, Integer> getSetQuestions(String name) {

		Map<String, Integer> qMap = new HashMap<String, Integer>();
		String line;
		String question = "";
		int answer;
		int questionNumber = 1;
		boolean questionLine = true;


		try {


			//Sets up the process builder and starts it
			String command = "cd CustomQuestionSet ; cat " + name + ".txt ;";
			ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

			Process process = pb.start();

			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			int exitStatus = process.waitFor();

			if (exitStatus == 0) {


				// While there is still something to read, loop through it and check its contents
				while ((line = stdout.readLine()) != null)  {

					//If it it's the final number breakout of the loop
					if (questionNumber == 11) {
						break;
					}
					
					//If its a question line read the question line and set the next line to be an answer
					else if (questionLine) {

						question = line;
						questionLine = false;

					}
					
					//Must be a answer line, parse the string to an Int, 
					//if it throws a number format exception, return null 
					else {

						answer = Integer.parseInt(line);
						qMap.put(question, answer);

						questionNumber++;
						questionLine = true;

						

					}




				}
				
				//If the content of the file does not have 10 questions, create random questions 
				if (questionNumber != 11) {
					while (questionNumber != 11) {
						int value1;
						int value2;
						int operationValue = (int)(Math.random() * 4);


						//operation Value 0 = addition 
						if (operationValue == 0) {

							value1 = (int)( 1 + (Math.random() * 98));
							value2 = (int)(1 + (Math.random() * (99 - value1)));
							answer = value1 + value2;

							question = value1 + " + " + value2;
							qMap.put(question, answer);





						}
						
						
						//Operation Value 1 = subtract
						else if (operationValue == 1) {

							value1 = (int)(50 + (Math.random() * 49));
							value2 = (int)(1 + (Math.random() * (value1) - 1));
							answer = value1 - value2;

							question = value1 + " - " + value2;
							qMap.put(question, answer);


							System.out.println(question);
							System.out.println(answer);

						}
						
						
						//Operation Value 2 = multiplication
						else if (operationValue == 2) {
							
							
							//Generates a random number between 1 and 11
							value1 = (int)( 1 + (Math.random() * 11));
							
							//If value == 11, limit value 2 to 1 - 9 
							if (value1 == 11) {
								value2 = (int)( 1 + (Math.random() * 9));
							}
							
							//If value == 10, limit value 2 to 1 - 9  
							else if (value1 == 10) {
								value2 = (int)( 1 + (Math.random() * 9));

							}
							
							//If value == 9, limit value 2 to 1 - 11 
							else if (value1 == 9) {
								value2 = (int)( 1 + (Math.random() * 11));

							}

							else {
								value2 = (int)( 1 + (Math.random() * 12));

							}

							answer = value1 * value2;
							question = value1 + " X " + value2;


							qMap.put(question, answer);
							System.out.println(question);
							System.out.println(answer);


						}
						
						
						//Operation Value 3 = division 
						else if (operationValue == 3) {
							
							//Generates a number between 1 - 99
							value1 = (int)( 1 + (Math.random() * 99));
							
							//Stores value1's factors into an arrayList
							ArrayList<Integer> factors = new ArrayList<>();
							factors = Number.getFactors(value1);


							//Randomly generate a number from that array list
							int size = factors.size();
							int index = (int)((Math.random() * size));

							value2 = factors.get(index);
							answer = value1 / value2;
							question= value1 + " / " + value2;

							qMap.put(question, answer);
							



						}


						questionNumber++;
					}
				}


				//else we extract the std error and output it 	
			} else {

				while ((line = stderr.readLine()) != null) {
					System.err.println(line);

				}
			}



		} catch (IOException | InterruptedException | NumberFormatException e) {

			return null;

		}



		return qMap;
	}



}

















