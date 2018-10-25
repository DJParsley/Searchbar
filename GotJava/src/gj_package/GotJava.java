package gj_package;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import java.io.*;
import java.util.regex.*;
import java.util.*;

/**
 * @author Murray, Devan, Cerrell
 *
 */

public class GotJava {

	private String aboutFiller1 = "Created by Devan Parsley";
	private String aboutFiller2 = "Murray Sloan Jr.";
	private String aboutFiller3 = "and Cerrell Brown";
	private String aboutFiller4 = "Go Team GotJava!";
	private String aboutFiller5 = "Let Our Powers Combine! :D";
	private String desktopPath;
	private String mainPath;
	private String textFilePath;
	private File mainFolder;
	private File textFiles;
	private File textDevan;
	private File textMurray;
	private File coolGuyCerrell;
	private File[] allTextFiles;
	
	public GotJava() {
		
		// This is where we will add files and folders for people who first open our app
		initFiles();
		
		// UI Stuff
		initUI();
		JFrame frame = new JFrame("");
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("Admin");
		JMenu menu2 = new JMenu("Help");
        
		// Adds the Admin and Help menus to the menubar, then adds
		// the menubar to the frame.
		JMenuItem item1 = new JMenuItem("Login");
		JMenuItem item2 = new JMenuItem("About");
		JMenuItem item3 = new JMenuItem("Current Files");
		JMenuItem item4 = new JMenuItem("Add File");
		JMenuItem item5 = new JMenuItem("Remove File");
		JMenuItem item6 = new JMenuItem("Update Files");
		menu1.add(item1);
		menu2.add(item2);
		menubar.add(menu1);
		menubar.add(menu2);
		frame.setJMenuBar(menubar);
        
        // Sets the background color and layout style of the panel.
		JPanel panel = new JPanel ( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets( 5, 5, 5, 5 );
		panel.setBackground( Color.WHITE );
		
        // Sets the properties of the title and adds it to the panel.
        JLabel title = new JLabel();
        title.setFont( new Font( "Serif", Font.BOLD, 24 ) );
        title.setText( "GotJava Search Engine" );
        c.gridx = 1;
        c.gridy = 0;
        panel.add( title, c );
		
		// Sets the properties of the searchbox and adds it to the panel.
        JTextField searchBox = new JTextField();
		searchBox.setPreferredSize( new Dimension ( 200, 20 ) );
		c.gridx = 1;
        c.gridy = 2;
		panel.add( searchBox, c );
		
		// Sets the properties of the search button and adds it to the panel.
		JButton search = new JButton( "Search" );
		c.gridx = 2;
		c.gridy = 2;
		panel.add( search, c );
		
		// Creating and populating the combobox.
		String[] comboBox = { "OR", "AND", "Phrase" };
		JComboBox searchChoices = new JComboBox(comboBox);
		searchChoices.setSelectedIndex(0);
		c.gridx = 2;
		c.gridy = 3;
		panel.add(searchChoices, c);
		
		// Label for user prompt.
		JLabel found = new JLabel();
		found.setFont( new Font( "Serif", Font.BOLD, 14 ) );
		found.setText( "Choose Search Option" );
		c.gridx = 1;
		c.gridy = 3;
//		panel.add( found, c );
		
		// Add scroll pane to the panel.
		JScrollPane scrollList = new JScrollPane();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		panel.add(scrollList, c);
	
		// Event handler for search button
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			
				//This is where you would add the actions for the search button
				
				int counter = 0;
				int counter2 = 0;
				int counter3 = 0;
				
				found.setFont( new Font( "Serif", Font.BOLD, 14 ) );
		        found.setText( "You Pressed Search! Something Happened." );
		        
		        // Gets the value of the user's selected choice (OR, AND, "Phrase").
		        int selection = searchChoices.getSelectedIndex();
		        String keyword = searchBox.getText().toLowerCase();
     		    String[] allKeywords = keyword.split("\\s");
     		    int wordcounter = allKeywords.length;
		        // If the user searches for a single term, the OR selection is forced.
		        if (wordcounter == 1) {
		        	
		        	selection = 0;
		        	
		        }
		       
		        switch (selection) {
		        
		        	//Made the or show a message box showing the textfile, filepath, and word entered with how many time it was found
		        	case 0: 
		        		
		        		String typedWordOR = searchBox.getText();
		        		String keywordOR = searchBox.getText().toLowerCase();
		        		String[] allKeywordsOR = keywordOR.split("\\s");
		        		int wordcountOR = 0;
		        		int[] wordcountPerWordOR = new int[((allKeywordsOR.length) * (allTextFiles.length))]; 
		        		int[] wordCheckerOR = new int[((allKeywordsOR.length) * (allTextFiles.length))];
		        		int[] fileCheckerOR = new int[((allKeywordsOR.length) * (allTextFiles.length))];
		        		String[] stringCheckerOR = new String[((allKeywordsOR.length) * (allTextFiles.length))];
		        		while (counter2 < allKeywordsOR.length) {
		        			
		        			while (counter < allTextFiles.length) {
		        				
		        				try {
		        					
		        					BufferedReader r = new BufferedReader(new FileReader(allTextFiles[counter]));    		    
		        					String line;
		        					
		        					try {
		        						
		        						while ((line = r.readLine()) != null) {
		        							
		        							Pattern pattOR = Pattern.compile(allKeywordsOR[counter2]);
		        							Matcher m = pattOR.matcher(line.toLowerCase());
		        							while (m.find()) {
		        								wordcountOR = wordcountOR + 1;
		        								
		        							}
		        							
		        						}
		        						
		        					}
		        					
		        					catch(IOException f) {
		        						
		        						JOptionPane.showMessageDialog(frame, "IOException");
		        						
		        					}
		        					
		        				}
		        				
		        				catch(FileNotFoundException f) {
		        					
		        					JOptionPane.showMessageDialog(frame, "File not found");
		        					
		        				}
		        		   
		        		   
		        				if (wordcountOR > 0) {
		        					
		        					wordCheckerOR[counter3] = 1;
		        					wordcountPerWordOR[counter3] = wordcountOR;
		        					fileCheckerOR[counter3] = counter;
		        					stringCheckerOR[counter3] = allKeywordsOR[counter2];
		        			   
		        				}
		        			 
		        				wordcountOR = 0;   
		        				counter = counter + 1;
		        				counter3 = counter3 + 1;
					
		        			}
		        			
		        			counter2 += 1;
		        			
							if ((counter == allTextFiles.length) && (counter2 == allKeywordsOR.length)) {
								
								counter3 = 0;
								
			                    while (counter3 < (((allKeywordsOR.length) * (allTextFiles.length)))) {
			                    	
									if ((wordCheckerOR[counter3] == 1)) {
										
										
										
										 // Deprecated.
										/* JOptionPane.showMessageDialog(frame, allTextFiles[fileCheckerOR[counter3]].getName() +"\n"+
												allTextFiles[fileCheckerOR[counter3]] + "\n" +
												wordcountPerWordOR[counter3] + " instances of the word " + stringCheckerOR[counter3]);
										*/
									}
									
									counter3 += 1;
									
			                    }
			                    
			                    
			                    // Line 233 Null Pointer Exception. Comment out and it runs fine.
			                    JPanel content = new JPanel();
								JLabel fileCount[] = new JLabel[counter3];
								String fileAndCount[] = new String[counter3];
								for (int i = 0; i < counter3; i++) {
									
									fileAndCount[i] = allTextFiles[fileCheckerOR[i]].getName() + " " + wordcountPerWordOR[i];
									fileCount[i].setText(fileAndCount[i]);
									content.add(fileCount[i]);
									
								}
								
								scrollList.setViewportView(content);
			                    
							}
							
							counter = 0;
							
		        		}
		        		
		        		break;
		        		
		        		//Made the and show a message box showing the 'yep', it will only do that when a textfile has
		        		// all instances of a word
		        	case 1:
					
		        		String typedWordAND = searchBox.getText();
		        		String keywordAND = searchBox.getText().toLowerCase();
						String[] allKeywordsAND = keywordAND.split("\\s");
						int wordcountAND = 0;
						int[] wordcountPerWordAND = new int[((allKeywordsAND.length) * (allTextFiles.length))]; 
						int[] wordCheckerAND = new int[((allKeywordsAND.length) * (allTextFiles.length))];
						int[] fileCheckerAND = new int[((allKeywordsAND.length) * (allTextFiles.length))];
						int[] fileContainsAllWordsAND = new int[allKeywordsAND.length];
						String[] stringCheckerAND = new String[((allKeywordsAND.length) * (allTextFiles.length))];
						while (counter2 < allKeywordsAND.length) {
						
							while (counter < allTextFiles.length) {
							
								try {
								
									BufferedReader r = new BufferedReader(new FileReader(allTextFiles[counter]));    		    
									String line;
								
									try {
									
										while ((line = r.readLine()) != null) {
										
											Pattern pattAND = Pattern.compile(allKeywordsAND[counter2]);
											Matcher m = pattAND.matcher(line.toLowerCase());
										
											while (m.find()) {
											
												wordcountAND += 1;
	        		        	   
											}

										}
									
									}
								
									catch(IOException f) {
									
										JOptionPane.showMessageDialog(frame, "IOException");
									
									}
								
								}
							
								catch(FileNotFoundException f) {
								
									JOptionPane.showMessageDialog(frame, "File not found");
								
								}
	        		   
	        		   
								if (wordcountAND > 0) {
	        			  
									wordCheckerAND[counter3] = 1;
									fileCheckerAND[counter3] = counter;
									stringCheckerAND[counter3] = allKeywordsAND[counter2];
	        			   
								}
	        		   
								if (wordCheckerAND[counter] == 1) {
	        			   
									fileContainsAllWordsAND[counter] = fileContainsAllWordsAND[counter] + 1;
									wordcountPerWordAND[counter] = wordcountPerWordAND[counter] + wordcountAND;
       					
								}
	        			 
								wordcountAND = 0;
								counter = counter + 1;
								counter3 = counter3 + 1;
				
							}
						
							counter2 += 1;
						
							if ((counter == allTextFiles.length) && (counter2 == allKeywordsAND.length)) {
							
								counter = 0;
								
								while (counter < (allTextFiles.length)) {
								
									if (fileContainsAllWordsAND[counter] == allKeywordsAND.length) {
									
										JOptionPane.showMessageDialog(frame, "Yep " + allTextFiles[counter].getName() + " " + wordcountPerWordAND[counter]);
				
                                		}
								
                                		counter += 1;
                                           
								}
							
							}
                                   
							counter = 0;
						
						}
					
						break;
					
		        	case 2:
		        		//added the ability to search through text file and count the number of intances within that word
		        		//in Devan.txt, only works in Phrase, updated to work in all files in textFiles file in GotJava file
		        		String typedWordPhrase = searchBox.getText();
		        		String keywordPhrase = searchBox.getText().toLowerCase();
		        		int wordcountPhrase = 0;
		        		Pattern pattPhrase = Pattern.compile(keywordPhrase);
		        		
		        		while (counter < allTextFiles.length) {
		        			
		        			try {
		        				
		        				BufferedReader r = new BufferedReader(new FileReader(allTextFiles[counter]));    		    
                                String line;
                                            
                                try {
                                      
                                	while ((line = r.readLine()) != null) {
                                                	
                                		Matcher m = pattPhrase.matcher(line.toLowerCase());
                                		
                                		while (m.find()) {
                                			
                                			wordcountPhrase = wordcountPhrase + 1;
                                                        
                                		}
                                                    
                                	}
                                                
                                }
                                
                                catch(IOException f) {
                                            	
                                	JOptionPane.showMessageDialog(frame, "IOException");
                                            	
                                }
                                
		        			}
                       
		        			catch(FileNotFoundException f) {
                                        	
		        				JOptionPane.showMessageDialog(frame, "File not found");
                                        	
		        			}
                                        
		        			counter += 1;
                                        
		        			if (counter == allTextFiles.length) {
                                        	
		        				JOptionPane.showMessageDialog(frame, wordcountPhrase + " instances of the phrase " + typedWordPhrase +" in all files.");
                                            
		        			}
					
		        		}
		        		
		        		break;
		        		
		        }
		        
		        //The if statements would go IF search text (Now in a string with .txt added, lets call it textFinder)
		        //or textFinder is == to list of files then JLabel found would display "file in index" else if not
		        //it would display "file not found"
			}
			
		});
		
		// Sets the properties of the frame, including title, size, default
		// close operation, and default location. Adds the panel to the frame.
		frame.add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setTitle( "GotJava Search Engine" );
		frame.setLocationRelativeTo( null );
		frame.setSize(400, 200);
		frame.setResizable( false );
		frame.setVisible( true );

		//These are the actions for the menu items
		
		//This is all the actions that are taken after clicking login
		
		item1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JFrame loginFrame = new JFrame();
				
				// This is the login frame
				
				loginFrame.setVisible(true);
				loginFrame.setSize(300, 120);
				loginFrame.setResizable(false);
				loginFrame.setLocationRelativeTo( null );
                       
				JPanel loginPanel = new JPanel();
				
				JTextField adminLogin = new JTextField();
				loginPanel.add(adminLogin);
				adminLogin.setBounds(10,35,200,20);
				
				JLabel loginTitle = new JLabel("Admin Login");
				title.setFont( new Font( "Serif", Font.BOLD, 24 ) );
				loginPanel.add(loginTitle);
				loginTitle.setBounds(10,15,200,24);
				
				JLabel loginError = new JLabel("Enter Password!");
				title.setFont( new Font( "Serif", Font.BOLD, 24 ) );
				loginPanel.add(loginError);
				loginError.setBounds(10,55,200,24);
				
				JButton loginButton = new JButton("Login");
				loginPanel.add(loginButton);
				loginButton.setBounds(210,35,70,20);
                        
				loginPanel.setLayout(null);
				loginPanel.setBackground(Color.WHITE);
				loginFrame.add(loginPanel);
							
				//This action listener is for loginButton it will stay open until closed down normally
                //Or you enter the correct password, which is password	
				loginButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
				    	
						//Below when this button is pressed will convert the text in search box to a string
						String password = adminLogin.getText();
				
						//Password will now be put into an if statement to see if the correct password was entered
						if(password.equals("password")) {
					
							//If the password is correct it will build the admin frame
							//Add all the things you want built into the admin page below
					
							JFrame adminFrame = new JFrame("Admin");                                                                                                                 
							adminFrame.setSize(365, 500);
							adminFrame.setResizable(false);
							adminFrame.setLocationRelativeTo( null );
							adminFrame.setBackground( Color.WHITE ); 
							adminFrame.setVisible(true);
                                        
							//Adding AddButton to Admin Frame
							GridLayout ButtonLayout = new GridLayout(10,3);
							adminFrame.setLayout(ButtonLayout);
                                        
							JButton AddButton = new JButton("Add Files"); 
							adminFrame.add(AddButton);
                                        
							//Adds ActionListener to AddButton
							ActionListener AddAction = new ActionListener(){
                                            
								public void actionPerformed(ActionEvent e) {
									
									JFileChooser Adder = new JFileChooser();
                                               
									try {     
                                                    
										Desktop desktop = Desktop.getDesktop();
										desktop.open(textFiles);                                           
										//   File fileAdd = Adder.getSelectedFile();
										//   Files.copy(fileAdd.getPath(),textFiles);
                                              
									}
                                                
									catch (IOException x) {
                                                	
										//} catch (UnsupportedOperationException x) {
										System.err.format("Unable to copy ",textFiles, x);
                                                    
									}
									
								}
								
							};
							
							AddButton.addActionListener(AddAction);
                                        
							//Adds RemoveButton to Admin Frame
							JButton RemoveButton = new JButton("Remove Files");                                    
							adminFrame.add(RemoveButton);
                                        
							//Adds ActionListener to RemoveButton
							/*   ActionListener RemoveAction = new ActionListnener(){
                                            @Override
                                            public void actionPerformed(ActionEvent e){                                               
                                                try{
                                                    Desktop trashWindow = Desktop.getDesktop();
                                                    File trash = new File("C:\\Users");
                                                    trashWindow.open(trash);
                   
                                                    if(trash.delete())
                                                    {
                                                        System.out.println("File is deleted");
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Cant delete file");
                                                    }
                                                }catch (IOException x){
                                                    System.err.format("Something Went Wrong");
                                                } 
                                            }
                                        };
                                        RemoveButton.addActionListener(RemoveAction);
                                        */                                    
                                        

							//Adds UpdateButton to Admin Frame
							JButton UpdateButton = new JButton("Update Files");
							adminFrame.add(UpdateButton);
							//Adds ActionListener to UpdateButton
							//Add Code Here....
						
						
							//Add all the things you want built into the admin page above
                    
							//This will close only the login frame below
					
							loginFrame.dispatchEvent(new WindowEvent(loginFrame, WindowEvent.WINDOW_CLOSING));
					
							}
						
						else {
							
							//This else statement will clear the searchbox and change the text Enter Password.
					
							loginError.setText("Wrong Password, Try Again");
							adminLogin.setText("");

                          	}
						
					}});
				
			}
			
		});
		
		//This is the end for all actions for item1 or file menu items above
		
		//This is the beginning for all actions by clicking about

		item2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFrame aboutFrame = new JFrame("About");
				aboutFrame.setVisible(true);
				aboutFrame.setSize(300, 130);
				aboutFrame.setLocationRelativeTo(null);
				aboutFrame.setResizable(false);

				JPanel aboutPanel = new JPanel() {

				//Having issues getting paint to work

					@Override
					public void paint (Graphics g) {
						
						super.paint(g);                                               
						g.setColor(Color.BLACK);
						g.setFont( new Font("Times New Roman", Font.BOLD, 16));
						g.drawString(aboutFiller1, 40, 20);
						g.setColor(Color.BLACK);
						g.setFont( new Font("Times New Roman", Font.BOLD, 16));
						g.drawString(aboutFiller2, 120, 40);
						g.setColor(Color.BLACK);
						g.setFont( new Font("Times New Roman", Font.BOLD, 16));
						g.drawString(aboutFiller3, 90, 60);
						g.setColor(Color.BLACK);
						g.setFont( new Font("Times New Roman", Font.BOLD, 16));
						g.drawString(aboutFiller4, 95, 80);
						g.setColor(Color.BLACK);
						g.setFont(new Font("Times New Roman", Font.BOLD, 16));
						g.drawString(aboutFiller5, 65, 100);
						
					}
					
				};

				aboutPanel.setLayout(null);
				aboutPanel.setBackground(Color.WHITE);
				aboutFrame.add(aboutPanel);
				
				//This is where you would add the actions for the about section
			}
			
		});
		
		// This is the end of all the actions for the items in menu under about, above
		
		// Stub headers for future indexing functions.
		
//		public void addFile() {
			
			// TODO Add file to index.
//		}
		
//		public void removeFile() {
			
			// TODO Remove file from index.
    /*                    ActionListener RemoveAction = new ActionListnener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                                      
                           try{
                   Desktop trashWindow = Desktop.getDesktop();
                   File trash = new File("C:\\Users");
                   trashWindow.open(trash);
                   
                       if(trash.delete())
                        {
                            System.out.println("File is deleted");
                        }
                        else
                        {
                            System.out.println("Cant delete file");
                        }
                   }catch (IOException x){
                       System.err.format("Something Went Wrong");
                   } 
                        }
                        };
                        RemoveButton.addActionListener(RemoveAction);
  */
//		}
		
//		public void checkFiles() {
		
			// TODO Check if any added files have changed or no longer exist.

//		}

		
        }
	
        public void initFiles() {
		
        	//This string is the most important, this is the path for everyones desktop (At least in Windows)
    		desktopPath = System.getProperty("user.home") + "\\Desktop";
    		//This adds to the dynamic path for desktop to add a GotJava file
    	    mainPath = desktopPath + "\\GotJava"; 
    	    //This adds to the previous path to add a folder named TextFiles
    	    textFilePath = mainPath + "\\TextFiles";
    	    //This creates mainFolder where GotJava is
    		mainFolder = new File(mainPath);
    		//This creates textFiles where TextFiles is
    		textFiles = new File(textFilePath);
    		//This creates a text file called Devan.txt from this point use this to create your guys' text files
    		textDevan = new File(textFilePath, "Devan.txt");
    		
    		//This checks to see if the user has mainFolder or GotJava already on their desktop
    		if (!mainFolder.exists()) {
    			
    			mainFolder.mkdir();
    			
    		}
    		
    		//This checks to see if the user has textFiles or TextFiles already inside of mainFolder or GotJava
    		if (!textFiles.exists()) {	
    			
    			textFiles.mkdir();
    			
    		}
    		
    		//This checks if my textfile Devan.txt is inside of textFiles or TextFiles use this to create your guys' text files
    		//Be sure to make yours different than mine especially the BufferedWriter.
    		if (!textDevan.exists()) {
    			
    			try {
    				
    				BufferedWriter writerDevan = new BufferedWriter(new FileWriter(textDevan));
    				writerDevan.write("Welcome to my text file! My name is Devan Parsley the 'author' of this text file.");
    				writerDevan.newLine();
    				writerDevan.write("Now I will share somethings with you cause I want to, if you don't like it just stop reading and");
    				writerDevan.newLine();
    				writerDevan.write("click the X in the top right corner to close this.");
    				writerDevan.newLine();
    				writerDevan.write("Let's Begin!");
    				writerDevan.newLine();
    				writerDevan.write("I am here today with the goal of becoming a Video Game programmer.");
    				writerDevan.newLine();
    				writerDevan.write("I have played video games all my life and I feel I should give back to others in the industry, ");
    				writerDevan.newLine();
    				writerDevan.write("like those before me have done so for me. I'm not entirely sure who I'll go to but, ");
    				writerDevan.newLine();
    				writerDevan.write("I am hoping for 3 companies which are Ronimo, Bungie, and the specific team behind Sonic Mania.");
    				writerDevan.newLine();
    				writerDevan.write("That is all that I got to say, Thanks for reading!");
    				writerDevan.flush();
    				writerDevan.close();
    				
    			}
    			
    			catch (IOException e) {
    				
    				e.printStackTrace();
    				
    			}
    			
    		}
    		
    		// Cerrell's Text File Add-on
    		coolGuyCerrell = new File(textFilePath, "Cerrell.txt");
    		if (!coolGuyCerrell.exists()){
    			
    			try{
    				
    				BufferedWriter dabWriter = new BufferedWriter(new FileWriter(coolGuyCerrell));
    				dabWriter.write("What's up!? This is Cerrell the Java II student who is working like crazy just to stay afloat."); 
    				dabWriter.newLine();
    				dabWriter.write("As of 4/10/18 I am 29 years of age and expecting my first child in September this year.");
    				dabWriter.newLine();
    				dabWriter.write("I have a old, but reliable Toyota Camry that gets me from A to B without any problems.");
    				dabWriter.newLine();
    				dabWriter.write("However, when I am not doing store runs in the grocery getter, I am on my GSX-R 750 getting from A to B ");
    				dabWriter.newLine();
    				dabWriter.write("in 2.5 seconds lol.");
    				dabWriter.newLine();
    				dabWriter.write("Now you know a tiny bit about me. Wrapping it up! Stay cool and enjoy the rest of your day!");
    				dabWriter.flush();
    				dabWriter.close();
    				
    				}
    			
    			catch(IOException e) {
    				
    				e.printStackTrace();
    				
                }
    			
    		}
    		
    		textMurray = new File(textFilePath, "Murray.txt");
    		if (!textMurray.exists()) {
    			
    			try {
    				
    				BufferedWriter writerMurray = new BufferedWriter(new FileWriter(textMurray));
    				writerMurray.write("This is Murray's text file for the purpose of populating the directory.");
    				writerMurray.newLine();
    				writerMurray.write("I want to work as a video game developer!");
    				writerMurray.newLine();
    				writerMurray.write("My favorite steak is prime rib.");
    				writerMurray.newLine();
    				writerMurray.write("Guitar is an instrument that I play.");
    				writerMurray.newLine();
    				writerMurray.write("See ya!");
    				writerMurray.newLine();
    				writerMurray.flush();
    				writerMurray.close();
    				
    			}
    			
    			catch (IOException e) {
    				
    				e.printStackTrace();
    				
    			}
    			
    		}
                    
    		allTextFiles = textFiles.listFiles();
		
        }
        
        public void initUI() {
        	
        	
        	
        }

		public static void main( String[] args ) throws IOException {
		
            new GotJava();
		
        }
	
}
