import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.Box;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.util.Scanner;
import java.util.Hashtable;

public class Game extends Frame
{
	public String charname = "";
	public char gender;
	public int pp = 50;
	public int morality = 50;
	public int legacy = 0;
	public int temperament = 50;
	
	public int historySwitch = 0;
	public int sAlliance = 0;
	public int fEducation = 0;
	public int fMedicine = 0;
	public int fMilitary = 0;
	public int extraFunds = 0;
	public int ending = 0;

	public String input = "";
	public String history = "";

/************************************WINDOW CONSTRUCTION************************************/
	private String title;

	Label label;
	JFrame window, historyWindow, statsWindow;
	Container con, hcon, scon;
	Color myGray = Color.decode("#e2e2e2");

	JPanel titleNamePanel, blurbPanel, historyBlurbPanel, startButtonPanel, historyButtonPanel, statsPanel, statsButtonPanel, nameFieldPanel, choiceAPanel, choiceBPanel, choiceCPanel;
	JLabel titleNameLabel;
	JButton startButton, historyButton, statsButton, choiceA, choiceB, choiceC;
	JTextArea blurbText, historyText, statsText;
	JTextField nameField;
	JScrollPane historyPane, statsPane;

	StartHandler startHandler = new StartHandler(); 
	ChoiceHandler choiceHandler = new ChoiceHandler();
	HistoryHandler historyHandler = new HistoryHandler();
	StatsHandler statsHandler = new StatsHandler();

	Font titleFont = new Font("Times New Roman", Font.PLAIN, 40);
	Font buttonFont = new Font("Times New Roman", Font.PLAIN, 20);
	Font blurbFont = new Font("Times New Roman", Font.PLAIN, 20);

	int windowWidth = 800;
	int windowHeight = 590;

	int titleWidth = windowWidth/2;
	int titleHeight = windowHeight/10;
	int titleX = windowWidth/4;
	int titleY = 100;

	int blurbWidth = windowWidth-3;
	int blurbHeight = 425;
	int blurbX = 0;
	int blurbY = 10;

	int buttonAWidth = windowWidth;
	int buttonAHeight = 45;
	int buttonAX = 0;
	int buttonAY = 450; //keep adding +50 to add more choices//

	public Game(String title)
	{
		this.title = title;

		window = new JFrame();
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.white);
		window.setLayout(null);

		con = window.getContentPane();


		//** MAIN TITLE TEXTBOX DETAILS **\\
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, titleY, 600, titleHeight); //(x-coor, y-coor, width, height)//
		titleNamePanel.setBackground(Color.white);

		//** MAIN TITLE TEXT DETAILS **\\
		titleNameLabel = new JLabel(title);
		titleNameLabel.setForeground(Color.black);
		titleNameLabel.setFont(titleFont);

		//** START BUTTON TEXTBOX DETAILS **\\
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(buttonAX, buttonAY, buttonAWidth, buttonAHeight);
		startButtonPanel.setBackground(Color.white);
		startButton = new JButton("START");
		startButton.addActionListener(startHandler);
		startButton.setBackground(Color.gray); //textbox color//
		startButton.setForeground(Color.black); //text color//
		startButton.setFont(buttonFont);

		//** HISTORY BUTTON TEXTBOX DETAILS **\\
		historyButtonPanel = new JPanel();
		historyButtonPanel.setBounds(0, buttonAY+70, 100, 45);
		historyButtonPanel.setBackground(Color.white);
		historyButton = new JButton("History");
		historyButton.addActionListener(historyHandler);
		historyButton.setBackground(Color.gray); //textbox color//
		historyButton.setForeground(Color.black); //text color//
		historyButton.setFont(buttonFont);

		//** STATS BUTTON TEXTBOX DETAILS **\\
		statsButtonPanel = new JPanel();
		statsButtonPanel.setBounds(windowWidth-105, buttonAY+70, 100, 45);
		statsButtonPanel.setBackground(Color.white);
		statsButton = new JButton("Stats");
		statsButton.addActionListener(statsHandler);
		statsButton.setBackground(Color.gray); //textbox color//
		statsButton.setForeground(Color.black); //text color//
		statsButton.setFont(buttonFont);

		titleNamePanel.add(titleNameLabel);
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		startButtonPanel.add(startButton);

		window.setVisible(true);

	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void clearAll()
	{
		choiceA.setVisible(false);
		choiceB.setVisible(false);
		choiceC.setVisible(false);	
		choiceAPanel.setVisible(false);	
		choiceBPanel.setVisible(false);
		choiceCPanel.setVisible(false);
		blurbPanel.setVisible(false);
		blurbText.setVisible(false);			
	}

	public void seeOne()
	{
		blurbPanel.setVisible(true);
		blurbText.setVisible(true);	
		choiceA.setVisible(true);	
		choiceAPanel.setVisible(true);		
	}

	public void seeTwo()
	{
		blurbPanel.setVisible(true);
		blurbText.setVisible(true);	
		choiceA.setVisible(true);	
		choiceAPanel.setVisible(true);	
		choiceB.setVisible(true);	
		choiceBPanel.setVisible(true);	
	}

	public void seeAll()
	{
		choiceA.setVisible(true);	
		choiceB.setVisible(true);
		choiceC.setVisible(true);	
		choiceAPanel.setVisible(true);	
		choiceBPanel.setVisible(true);
		choiceCPanel.setVisible(true);	
		blurbPanel.setVisible(true);
		blurbText.setVisible(true);	
	}

	public void compileHistory(String historyContent)
	{
		String historyFinal = "THE STORY SO FAR:\n\n";
		historyFinal += historyContent;

		historyWindow = new JFrame();
		historyWindow.setSize(windowWidth, blurbHeight+50);
		historyWindow.setDefaultCloseOperation(historyWindow.DISPOSE_ON_CLOSE);
		historyWindow.getContentPane().setBackground(Color.white);
		historyWindow.setLocationRelativeTo(null);

		hcon = historyWindow.getContentPane();

		//** BLURB TEXTBOX PANEL DETAILS **\\
		historyBlurbPanel = new JPanel();
		historyBlurbPanel.setLayout(new BoxLayout(historyBlurbPanel, BoxLayout.PAGE_AXIS));
		historyBlurbPanel.setBounds(blurbX+10, blurbY+15, blurbWidth-10, blurbHeight);
		historyBlurbPanel.setBackground(Color.white);

		//** BLURB TEXT DETAILS **\\
		historyText = new JTextArea(historyFinal);
		historyPane = new JScrollPane(historyText);
		historyPane.setBounds(blurbX+15, blurbY+15, blurbWidth-30, blurbHeight-40);
		historyPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		historyText.setBounds(blurbX+15, blurbY+15, blurbWidth-30, blurbHeight-60);
		historyText.setBackground(Color.white);
		historyText.setForeground(Color.black);
		historyText.setFont(blurbFont);
		historyText.setLineWrap(true);
		historyText.setWrapStyleWord(true);

		historyBlurbPanel.add(historyPane);
		hcon.add(historyBlurbPanel);

		historyWindow.setVisible(true);

	}
	public void displayStats()
	{
		statsWindow = new JFrame();
		statsWindow.setSize(275, 310);
		statsWindow.setDefaultCloseOperation(statsWindow.DISPOSE_ON_CLOSE);
		statsWindow.getContentPane().setBackground(Color.white);
		statsWindow.setLocationRelativeTo(null);

		scon = statsWindow.getContentPane();

		//** BLURB TEXTBOX PANEL DETAILS **\\
		statsPanel = new JPanel();
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.PAGE_AXIS));
		statsPanel.setBounds(blurbX+10, blurbY+15, blurbWidth-8, blurbHeight);
		statsPanel.setBackground(Color.white);

		//** BLURB TEXT DETAILS **\\
		statsText = new JTextArea(getStats());
		statsPane = new JScrollPane(statsText);
		statsPane.setBounds(blurbX+15, blurbY+15, blurbWidth-28, blurbHeight-40);
		statsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		statsText.setBounds(blurbX+15, blurbY+15, blurbWidth-28, blurbHeight-60);
		statsText.setBackground(Color.white);
		statsText.setForeground(Color.black);
		statsText.setFont(blurbFont);
		statsText.setLineWrap(true);
		statsText.setWrapStyleWord(true);

		statsPanel.add(statsPane);
		scon.add(statsPanel);

		statsWindow.setVisible(true);
	}			
	public String getStats()
	{
		String statsText = "\nPLAYER STATS:\n\n";
		statsText += "Name: " + charname + "\n";
		statsText += "Gender: " + getGender(gender, 'X') + "\n\n";
		statsText += "Temperament: " + temperament + " (" + getTemperament(temperament) + ")" + "\n";
		statsText += "Morality: " + morality + " (" + getMorals(morality) + ")" + "\n";
		statsText += "Public perception: " + pp + " ("+ getPP(pp) + ")" + "\n";
		statsText += "Legacy: " + legacy + " (" + getLegacy(legacy) + ")" + "\n";
		return statsText;
	}		
	public static String getPP(int n)
	{
		String finalS = "";
		if (n >= 0 && n <= 30)
		{
			finalS = "Hated";
		}
		else if (n >= 31 && n <= 69)
		{
			finalS = "Mixed";
		}
		else
		{
			finalS = "Loved";
		}
		return finalS;
	}	
	public static String getMorals(int n)
	{
		String finalM = "";
		if (n >= 0 && n <= 40)
		{
			finalM = "Evil";
		}
		else if (n >= 41 && n <= 59)
		{
			finalM = "Neutral";
		}
		else
		{
			finalM = "Good";
		}
		return finalM;
	}	
	public static String getLegacy(int n)
	{
		String finalL = "";
		if (n >= 0 && n <= 25)
		{
			finalL = "No impact";
		}
		else if (n >= 26 && n <= 50)
		{
			finalL = "Slight impact";
		}
		else if (n >= 51 && n <= 75)
		{
			finalL = "Noticeable impact";
		}
		else
		{
			finalL = "Profound impact";
		}
		return finalL;
	}	
	public static String getTemperament(int n)
	{
		String finalT = "";
		if (n >= 0 && n <= 34)
		{
			finalT = "Negative";
		}
		else if (n >= 35 && n <= 66)
		{
			finalT = "Apathetic";
		}
		else
		{
			finalT = "Positive";
		}
		return finalT;
	}	
	public void newPage(String blurbContent, int numberChoices, String choiceAText, String choiceAAction, String choiceBText, String choiceBAction, String choiceCText, String choiceCAction)
	{
		history += blurbContent + "\n\n\n";
		titleNameLabel.setVisible(false);
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		startButton.setVisible(false);

		//** BLURB TEXTBOX PANEL DETAILS **\\
		blurbPanel = new JPanel();
		blurbPanel.setBounds(blurbX+10, blurbY+15, blurbWidth-10, blurbHeight-10);
		blurbPanel.setBackground(Color.white);

		//** BLURB TEXT DETAILS **\\
		blurbText = new JTextArea(blurbContent);
		blurbText.setBounds(blurbX+10, blurbY+15, blurbWidth-10, blurbHeight-10);
		blurbText.setBackground(Color.white);
		blurbText.setForeground(Color.black);
		blurbText.setFont(blurbFont);
		blurbText.setLineWrap(true);
		blurbText.setWrapStyleWord(true);

		if (numberChoices >= 1)
		{
			addButton1(choiceAText);
			choiceA.setActionCommand(choiceAAction);

			if (numberChoices >= 2)
			{
				addButton2(choiceBText);
				choiceB.setActionCommand(choiceBAction);

				if (numberChoices == 3)
				{
					addButton3(choiceCText);
					choiceC.setActionCommand(choiceCAction);
				}
			}
		}
		//**NAME SETTER**\\
		else if (numberChoices == 0)
		{
			nameFieldPanel = new JPanel();
			nameFieldPanel.setBounds(100, titleY+150, 600, titleHeight); //(x-coor, y-coor, width, height)//
			nameFieldPanel.setBackground(Color.white);
			nameField = new JTextField("", 12);
			nameField.setBackground(myGray);
			nameField.setForeground(Color.black);

			addButton1(choiceAText);
			choiceA.setActionCommand(choiceAAction);
			nameFieldPanel.add(nameField);
			con.add(nameFieldPanel);
		}

		else{}
	}
	public void addButton1(String choiceAText)
	{
		//** CHOICE BUTTON DETAILS**\\
		choiceAPanel = new JPanel();
		choiceAPanel.setBounds(buttonAX, buttonAY, buttonAWidth, buttonAHeight);
		choiceAPanel.setBackground(Color.white);
		choiceA = new JButton(choiceAText);
		choiceA.addActionListener(choiceHandler);
		choiceA.setBackground(Color.gray); //textbox color//
		choiceA.setForeground(Color.black); //text color//
		choiceA.setFont(buttonFont);

		//**CONSTRUCTING STORYPAGE**\\
		blurbPanel.add(blurbText);
		con.add(blurbPanel);
		con.add(choiceAPanel);
		choiceAPanel.add(choiceA);
		if (historySwitch == 1)
		{
			historyButtonPanel.add(historyButton);
			con.add(historyButtonPanel);
			statsButtonPanel.add(statsButton);
			con.add(statsButtonPanel);
		}
	}
	public void addButton2(String choiceBText)
	{
		choiceBPanel = new JPanel();
		choiceBPanel.setBounds(buttonAX, buttonAY+35, buttonAWidth, buttonAHeight);
		choiceBPanel.setBackground(Color.white);
		choiceB = new JButton(choiceBText);
		choiceB.addActionListener(choiceHandler);
		choiceB.setBackground(Color.gray);
		choiceB.setForeground(Color.black); 
		choiceB.setFont(buttonFont);
		con.add(choiceBPanel);
		choiceBPanel.add(choiceB);
	}
	public void addButton3(String choiceCText)
	{
		choiceCPanel = new JPanel();
		choiceCPanel.setBounds(buttonAX, buttonAY+70, buttonAWidth, buttonAHeight);
		choiceCPanel.setBackground(Color.white);
		choiceC = new JButton(choiceCText);
		choiceC.addActionListener(choiceHandler);
		choiceC.setBackground(Color.gray);
		choiceC.setForeground(Color.black);
		choiceC.setFont(buttonFont);
		con.add(choiceCPanel);
		choiceCPanel.add(choiceC);
	}
/************************************CHARACTER CUSTOMIZATION************************************/
	public static char setGender(char chargender)
	{
		if (chargender == 'm' || chargender == 'M')
		{
			chargender = 'M';
		}
		else if (chargender == 'f' || chargender == 'F')
		{
			chargender = 'F';
		}
		else
		{
			chargender = 'T';
		}
		return chargender;
	}

	public static String getSubPronoun(char g, char type)
	{
		String pronoun = "xxx";

		if (g == 'M')
		{
			if (type == 'x')
			{
				pronoun = "he";
			}
			else
			{
				pronoun = "He";
			}
		}

		else if (g == 'F')
		{
			if (type == 'x')
			{
				pronoun = "she";
			}
			else
			{
				pronoun = "She";
			}
		}

		else 
		{
			if (type == 'x')
			{
				pronoun = "they";
			}
			else
			{
				pronoun = "They";
			}
		}
		return pronoun;
	}

	public static String getGender(char g, char type)
	{
		String gender = "";

		if (g == 'M')
		{
			gender = "Male";
		}
		else if (g == 'F')
		{
			gender = "Female";
		}
		else
		{
			gender = "Non-binary";
		}
		return gender;
	}

	public static String getTitle(char g, char type)
	{
		String pronoun = "xxx";

		if (g == 'M')
		{
			if (type == 'x')
			{
				pronoun = "prince";
			}
			else if (type == 'y')
			{
				pronoun = "king";
			}
			else
			{
				pronoun = "my prince";
			}
		}
		else if (g == 'F')
		{
			if (type == 'x')
			{
				pronoun = "princess";
			}
			else if (type == 'y')
			{
				pronoun = "queen";
			}
			else
			{
				pronoun = "my princess";
			}
		}
		else
		{
			if (type == 'x')
			{
				pronoun = "ruler";
			}
			else if (type == 'y')
			{
				pronoun = "monarch";
			}
			else
			{
				pronoun = "your Highness";
			}  
		}
		return pronoun;
	}

/************************************ MAIN ************************************/
	
	public static void main(String[] args) 
	{
		Scanner kb = new Scanner(System.in);
		Game adventure = new Game("Title???");
	}
	public class HistoryHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			compileHistory(history);
		}
	}
	public class StatsHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			displayStats();
		}
	}
	public class StartHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			historySwitch = 1;
			input = "You are born.\n\nIt is your mother, touched and turned pale by death and moonlight, who names you:";
			newPage(input, 0, "Continue.", "nameAction", "", "", "", "");
		}
	}
	public class ChoiceHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String choice = e.getActionCommand();

			if (choice.equals("nameAction"))
			{
				choiceA.setVisible(false);
				choiceAPanel.setVisible(false);	
				blurbPanel.setVisible(false);
				blurbText.setVisible(false);
				nameFieldPanel.setVisible(false);
				charname = nameField.getText();	
				input = "Your father arrives in time to be the one who signs your name into royal records. They say he was covered in grime and blood, unwashed ";
				input += "from a four-day ride. He had been returning from peace talks with a neighboring kingdom when his caravan was attacked by bandits, but ";
				input += "witnesses tell of his ruthlessness in the skirmish, how he rode on afterwards, determined to return to his wife and future child's side.";
				input += "\n\nAs he scribes your name into history, a single drop of blood trickles from his soiled clothes and stains the parchment.";
				input += "\n\nThe next morning, a crier stands on the steps of the palace, heralding the arrival of the healthy...";
				newPage(input, 3, "...prince.", "choice1Action", "...princess.", "choice2Action", "...heir.", "choice3Action");		
				seeAll();	
			}
			else if (choice.equals("choice1Action"))
			{
				clearAll();
				input = "They will tell stories of this day: the arrival of the royal heir " + charname + ", born under a blood moon. They say the crier was not ";
				input += "the only bringer of news that night, that a priest of Apollo shouted himself hoarse in front of the temple, warning of ";
				input += "the blood that you are prophesied to spill. \n\n";
				input += "Prophecies are seldom wrong. From the beginning, yours holds true.\n\n";
				input += "The first life you claim is your mother's. ";
				input += "The news arrives with daybreak, a cruel counterpoint to the warm, dawning sun. That following day is beautiful, and you ";
				input += "hear the mixed whispers they trade in the streets: that the unnatural weather may already be your doing, or that the gods ";
				input += "smile down upon the destruction you have begun to wreak. Both possibilities frighten them.\n\n";
				input += "Soon, you will be forced to address this.";
				newPage(input, 2, "It is better they fear you.", "choice4Action", "You will make them love you.", "choice5Action", "", "");		
				seeTwo();
				gender = setGender('m');					
			}
			else if (choice.equals("choice2Action"))
			{
				clearAll();	
				input = "They will tell stories of this day: the arrival of the royal heir " + charname + ", born under a blood moon. They say the crier was not ";
				input += "the only bringer of news that night, that a priest of Apollo shouted himself hoarse in front of the temple, warning of ";
				input += "the blood that you are prophesied to spill. \n\n";
				input += "Prophecies are seldom wrong. From the beginning, yours holds true.\n\n";
				input += "The first life you claim is your mother's. ";
				input += "The news arrives with daybreak, a cruel counterpoint to the warm, dawning sun. That following day is beautiful, and you ";
				input += "hear the mixed whispers they trade in the streets: that the unnatural weather may already be your doing, or that the gods may ";
				input += "smile down upon the destruction you have begun to wreak. Both possibilities frighten them.\n\n";
				input += "Soon, you will be forced to address this.";
				newPage(input, 2, "It is better they fear you.", "choice4Action", "You will make them love you.", "choice5Action", "", "");
				seeTwo();	
				gender = setGender('f');	
			}
			else if (choice.equals("choice3Action"))
			{
				clearAll();	
				input = "They will tell stories of this day: the arrival of the royal heir " + charname + ", born under a blood moon. They say the crier was not ";
				input += "the only bringer of news that night, that a priest of Apollo shouted himself hoarse in front of the temple, warning of ";
				input += "the blood that you are prophesied to spill. \n\n";
				input += "Prophecies are never wrong. From the beginning, yours holds true.\n\n";
				input += "The first life you claim is your mother's.\n\n";
				input += "The news arrives with daybreak, a cruel counterpoint to the warm, dawning sun. That following day is beautiful, and you ";
				input += "hear the mixed whispers they trade in the streets: that the unnatural weather may already be your doing, or that the gods ";
				input += "smile down upon the destruction you have begun to wreak. Both possibilities frighten them.\n\n";
				input += "Soon, you will be forced to address this.";
				newPage(input, 2, "It is better they fear you.", "choice4Action", "You will make them love you.", "choice5Action", "", "");
				seeTwo();
				gender = setGender('t');			
			}
			else if (choice.equals("choice4Action"))
			{
				clearAll();
				input = "The people lament the Queen's name in elegies while they whisper yours in cautionary tales. From the beginning, you are the ";
				input += "malevolent tyrant who snatches children away in the dead of night, the dark monster who slumbers beneath their beds, the wicked ";
				if (gender == 'F')
				{
					input += "sorceress";
				}
				else if (gender == 'M')
				{
					input += "sorcerer";
				}
				else
				{
					input += "mage";
				}
				input += " who curses entire generations of families.\n\n";
				input += "This, you think, is bearable. Let them fear you.";
				pp = pp - 10;
				newPage(input, 2, "It will keep them safe.", "choice6Action", "It will keep them obedient.", "choice7Action", "", "");
				seeTwo();	
			}
					else if (choice.equals("choice6Action"))
					{
						clearAll();
						input = "If they fear you, they will learn to keep away from you. And if they keep away from you, then they cannot be harmed by you. \n\n";
						input += "This is the only comfort you can draw from your prophecy.";
						pp = pp - 5;
						morality += 5;
						temperament = temperament - 10;				
						newPage(input, 1, "Continue", "choice10Action", "", "", "", "");
						seeOne();
					}
					else if (choice.equals("choice7Action"))
					{
						clearAll();
						input = "If they fear you, they will more easily obey you. And so long as they obey you, perhaps you would not harm them.\n\n";
						input += "Countless rulers before have proven the effectiveness of fear. You will use this to your advantage.";
						pp = pp + 5;
						morality -= 5;
						temperament = temperament - 5;
						newPage(input, 1, "Continue", "choice10Action", "", "", "", "");
						seeOne();	
					}
			else if (choice.equals("choice5Action"))
			{
				clearAll();
				input = "You will change their hearts, you decide. It would not be the first time that a ruler would grow to be loved by the ";
				input += "people despite their deeds. It is not impossible.\n\n";
				input += "The distinction that remains to be made is how you will do so. Rulers have been loved out of fear yet; few have been ";
				input += "loved truly.";
				pp = pp + 10;
				newPage(input, 2, "You will earn their love.", "choice8Action", "You will force them to love you.", "choice9Action", "", "");
				seeTwo();
			}
					else if (choice.equals("choice8Action"))
					{
						clearAll();
						input = "You will earn their love, you decide. It may be difficult, but not impossible, and you are not one to shirk from ";
						input += "a challenge. You also know that it will be worth it, in the end. You mother, one of those few who were truly loved, would ";
						input += "be proud of you. It will be your way of honoring her legacy.";
						temperament += 5;
						morality += 5;
						pp += 5;
							newPage(input, 1, "Continue", "choice10Action", "", "", "", "");
						seeOne();	
					}
					else if (choice.equals("choice9Action"))
					{
						clearAll();
						input = "You will give them no choice but to love you, even if it is borne out of fear.\n\n";
						input += "Some may come to do it sincerely, you think, but you know better that most will not. But in this way they will at least respect ";
						input += "you: a respect that you know you deserve. You are, after all, their would-be " + getTitle(gender, 'x') + ".";
						newPage(input, 1, "Continue", "choice10Action", "", "", "", "");
						seeOne();	
					}
			else if (choice.equals("choice10Action"))
			{
				clearAll();	
				input = "The years crawl by.\n\nYour father grows cold in the wake of your mother's death, spending more and more time behind the heavy doors of his ";
				input += "chambers. His advisors look increasingly worried each day, and even your tutors whisper amongst each other when they do not think you can ";
				input += "hear. But you do.\n\n";
				input += "You see the way some of them glance towards you. At one point, you hear them wondering if you may be able to talk some sense into your father.\n\n";
				input += "\"No!\" the maid whispers harshly. \"Have you not heard of the prophecy? " + getSubPronoun(gender, 'X') + " would ruin us all!\"\n\n";
				input += "They startle when you rise from your studies and approach them. At thirteen years young, you are short and slight for your age, a stature that inspires ";
				input += "derision from your people. In fact, some of them here, you realize as you stop in front of the small gathering of servants, have called you names before. ";
				input += "Now, however, all eyes are riveted on you, and none of them dare to speak.";
				newPage(input, 3, "You offer to help talk to your father.", "choice11Action", "You walk past them, deciding to stay out of it.", "choice12Action", "You scorn them for whispering behind your father's back.", "choice13Action");
				seeAll();
			}
					else if (choice.equals("choice11Action"))
					{
						clearAll();	
						input = "\"What matters need attending to?\" you ask in the voice that you have been taught to use: calm, authoritative. You also soften yourself slightly, taking pity ";
						input += "on the few that look like they may run away at any moment. \"I can try to talk to my father.\"\n\n";
						input += "It's a few more seconds before someone speaks - one of the advisors. \"There is the issue of foreign diplomacy, " + getTitle(gender, 'X') + ",\" she murmurs, eyes downcast. ";
						input += "\"Our neighbor kingdom Selese has come under the threat of woodland invaders and has sent multiple requests to reaffirm our alliance, but His Highness has not sent ambassadors ";
						input += "for the second year in the row. There is talk of the Selesian queen severing political ties if we do not help.\"\n\n";
						input += "You consider this for a moment. Selese is a fledgeling kingdom compared to the others that surround it, but they have grown auspiciously over the years. Your mother was close friends ";
						input += "with the queen, but her death, combined with your father's isolation, has strained relations. It is likely that Selese will be able to repel the invaders on their own and prove their ";
						input += "mettle, but you may be able to convince your father to promise aid in the case that they cannot.";
						pp += 5;
						legacy += 10;
						temperament += 5;
						morality += 5;
						newPage(input, 2, "You will convince your father to help.", "choice14Action", "You will alert your father of the issue, but nothing more.", "choice15Action", "", "");
						seeTwo();
					}
							else if (choice.equals("choice14Action"))
							{
								clearAll();	
								input = "\"I see,\" you say. The decision is clear: Selese is a valuable ally, and the kingdom must prove its reliability in times of need. \"I will speak to my father and urge him to reaffirm our political ";
								input += "ties with Selese.\"\n\n";
								input += "The servants look pleasantly surprised, but one steps forward to clasp your hand. \"Thank you, " + getTitle(gender, 'X') + "!\" He kisses your hand in a show of gratitude.\n\n";
								input += "Speaking with your father is a tricky matter, but you do have better luck than the others, it seems. Hours later, you reemerge from his chambers with an order to send diplomats ";
								input += "to Selese as soon as possible.\n\n";
								input += "The palace seems to regard you more warmly afterwards. A few servants even approach and express their gratitude for your intervention.";
								pp += 5;
								temperament += 5;
								morality += 5;
								sAlliance = 1;
								newPage(input, 1, "Continue.", "choice18Action", "", "", "", "");
								seeOne();
							}
							else if (choice.equals("choice15Action"))
							{
								clearAll();	
								input = "\"I see,\" you say. The urgency is clear: Selese is a valuable ally, and the kingdom must prove its reliability in times of need. \"I will speak to my father and bring the matter to his attention, ";
								input += "although I cannot guarantee how he will respond.\"\n\n";
								input += "The servants look pleasantly surprised, but the woman whohad spoken nods. \"That may just be enough, " + getTitle(gender, 'X') + ". Thank you.\"\n\n";
								input += "Speaking with your father is a tricky matter, but you do have better luck than the others, it seems, for he does look into your eyes. You are unsure if you get your message across, but hours later, ";
								input += "you reemerge from his chambers feeling satisfied that you have at least alerted him.\n\n";
								input += "The palace seems to regard you more openly afterwards. They still seem uncertain about Selese, but not as fearful as before. You hope your father makes the right decision.";
								pp += 5;
								legacy -= 5;
								morality += 5;
								newPage(input, 1, "Continue.", "choice18Action", "", "", "", "");
								seeOne();
							}
					else if (choice.equals("choice12Action"))
					{
						clearAll();	
						input = "You remain standing in place for a few seconds, meeting each of their eyes and daring them to say a word. You have to tilt your chin upwards ";
						input += "to look at them, but you feel powerful, knowing that you have rendered them silent. They may talk all they'd like, but if they cannot say it to ";
						input += "your face, they are not worth your time.\n\n";
						input += "You walk past them without another word, headed to your room. You think you can even hear them struggling to keep their breathing quiet.\n\n";
						input += "In the following days, the whole palace seems to avoid you - but this is no different from how they have treated you before.";
						input += "They hardly deserve your help as the royal " + getTitle(gender, 'x') + " if they cannot even treat you as a person.\n\n";
						input += "Shortly, you hear rumors of the neighboring kingdom Selese facing threat of invaders, and that they have reached out to your father for a reaffirmation of the alliance between your kingdoms. You wonder how your father will act.";
						pp -= 5;
						morality -= 5;
						temperament -=5;
						newPage(input, 1, "Continue.", "choice18Action", "", "", "", "");
						seeOne();
					}
					else if (choice.equals("choice13Action"))
					{
						clearAll();	
						input = "You remain standing place for a few seconds, meeting each of their eyes impassively. You have to tilt your chin upwards in order to do so, but you feel powerful, ";
						input += "knowing that you have cowed them into silence.\n\n";
						input += "\"My late mother,\" you begin, using the voice you have been taught and perfected, \"did not give all of you a home in this palace and a job to feed your families ";
						input += "with, only for you to stand around and gossip, especially about her own family.\"\n\n";
						input += "\"I- " + getTitle(gender, 'X') + ", we were only discussing-\"\n\n";
						input += "\"My father's incompetence? My shortcomings? Yes, they sounded like very urgent matters.\" You cut off the servant who had spoken, eyeing him coldly. \"How can I be sure that you are not all plotting mutiny in the shadow of my ";
						input += "father's grief?\"\n\n";
						input += "\"We weren't, " + getTitle(gender, 'X') + ", I swear it!\" The servant looks horrified, and the others now look equally discomfited. Good, you think.\n\n";
						pp -= 5;
						temperament -= 10;
						newPage(input, 2, "You decide to make an example out of him.", "choice16Action", "You tell them to get back to work.", "choice17Action", "", "");
						seeTwo();
					}
							else if (choice.equals("choice16Action"))
							{
								clearAll();	
								input = "You have barely finished issuing the command to take him away when he shouts \"No!\" and surges forward. Surprised, you stumble back, but the others hold him back. ";
								input += "You watch him struggle and plead while they murmur for him to be quiet. Someone else steps forward and addresses you: \"Please, " + getTitle(gender, 'X') + ", he did ";
								input += "not mean any harm.\"\n\n";
								input += "\"With his words, possibly,\" you answer levelly. \"But he has now attempted to assault me. Take him to a holding cell.\"\n\n";
								input += "For several long seconds, no one says a word. The man is quietly sobbing into a woman's shoulder. You are unmoved. \"If he does not go, then you will all join him,\" you inform the crowd, turning";
								input += "around. You have wasted enough time here. You do not need to turn to know that they finally obey you. The man's feverish pleas are evidence enough.\n\n";
								input += "For the next few days, the whole palace seems to be trying to avoid you. Those who speak with you do so with a little more caution than usual. You hear rumors of the neighboring kingdom Selese facing threat of invaders, ";
								input += "and that they have reached out to your father for a reaffirmation of the alliance between your kingdoms. You wonder how your father will act.";
								pp -= 5;
								temperament -= 5;
								morality -= 5;
								legacy += 5;
								newPage(input, 1, "Continue.", "choice18Action", "", "", "", "");
								seeOne();
							}
							else if (choice.equals("choice17Action"))
							{
								clearAll();	
								input = "\"The next man, woman, or child I catch whispering behind my back,\" you say, glancing around the group of your supposedly most loyal subjects, \"will be thrown into a holding cell for a week and have their position ";
								input += "revoked. That is all.\"\n\n";
								input += "You turn around and walk away without further word; you have wasted enough time here. You hope that they learn from your lesson of mercy.\n\n";
								input += "For the next few days, the whole palace seems to be trying to avoid you. Those who speak with you do so with a little more caution than usual. You hear rumors of the neighboring kingdom Selese facing threat of invaders, ";
								input += "and that they have reached out to your father for a reaffirmation of the alliance between your kingdoms. You wonder how your father will act.";
								temperament += 5;
								newPage(input, 1, "Continue.", "choice18Action", "", "", "", "");
								seeOne();
							}
			else if (choice.equals("choice18Action"))
			{
				clearAll();	
				input = "Matters are quiet for a time.\n\n";
				if (sAlliance == 1)
				{
					input += "The queen of Selese formally reaffirms the alliance between your kingdoms a week later. It seems that people have heard of your involvement, and your name is part of the celebrations that follow. ";
					legacy += 10;
					pp += 5;
				}
				else
				{
					input += "The queen of Selese formally dissolves the alliance between your kingdoms a week later. The people receive this news with unease, given the threat of the invaders, but it is distant yet. ";
					pp -= 5;
				}
				input += "Your father puts forth a matching statement, but he seems unaffected by it - only wary, weakened by his grief. Rumors of his health swirl around the kingdom. If he dies...";
				newPage(input, 2, "You are ready and willing to take his place.", "choice19Action", "You dread having to take his place.", "choice20Action", "", "");
				seeTwo();
			}
					else if (choice.equals("choice19Action"))
					{
						clearAll();	
						input = "You know that you are still young, but you are nonetheless prepared to ensure the stability of the kingdom. Your prophecy hangs over your shoulders as heavy as the corpse of an albatross, ";
						input += "but you would not shirk your duty to the people out of fear.";
						morality += 10;
						newPage(input, 1, "Continue.", "choice21Action", "", "", "", "");
						seeOne();
					}
					else if (choice.equals("choice20Action"))
					{
						clearAll();	
						input = "Perhaps it is only your youth, but you cannot possibly imagine taking his place, nor do you have any interest in doing so. You know that it will fall to you eventually, but you can't ";
						input += "help but wish you had been born someone else - without this title, without this prophecy.";
						morality -= 10;
						temperament -= 5;
						newPage(input, 1, "Continue.", "choice21Action", "", "", "", "");
						seeOne();
					}
			else if (choice.equals("choice21Action"))
			{
				clearAll();
				input = "As if an omen, an illness overtakes your father on your nineteenth nameday. He is bedridden, and when the illness does not ebb after even a week, it is clear that someone must make decisions in his stead. Now that you ";
				input += "nearly of age, you cannot simply remain silent through royal affairs.\n\nSoon, you are approached by advisors, who request you to review your father's unfinalized plans for the incoming year. It is clear that they are ";
				input += "not expecting your father to survive this bout of illness - and neither do the doctors.";
				newPage(input, 2, "You cooperate with the advisors.", "choice22Action", "You make it clear that you have no interest in royal affairs.", "choice23Action", "", "");		
				seeTwo();				
			}
					else if (choice.equals("choice22Action"))
					{
						clearAll();	
						input = "You have sat in with councils before and observed the proceedings, but this is different. The reality of it is daunting, but you steel yourself and nod, following them into your father's study.\n\n";
						input += "You have never been privy to royal documents before, but here they are now, laid bare before you. There are almost too many papers to read, but your father's advisors - and soon-to-be yours - ";
						input += "explain each one patiently.\n\n";
						input += "The most concerning issue is the crown funds. Presently, the kingdom is at a financial peak, flourishing from the investments that your mother previously made in various trading companies. Currently, ";
						input += "the crown can afford to dedicate a portion of the funds to improving an aspect of the kingdom.";
						temperament += 10;
						newPage(input, 3, "Invest more funding in the military.", "choice24Action", "Invest more funding in the education system.", "choice25Action", "Invest more funding in medicine and research.", "choice26Action");
						seeAll();
					}
					else if (choice.equals("choice23Action"))
					{
						clearAll();	
						input = "You make your displeasure clear to the advisors, but it appears that no one else will be able to make these decisions. Reluctantly, you follow them to your father's study.\n\n";
						input += "You have never been privy to royal documents before, but here they are now, laid bare before you. There are almost too many papers to read, but your father's advisors - and soon-to-be yours - ";
						input += "explain each one, albeit with some stiffness. It is clear that your attitude does not bode well with them, but what can they do? You are their superior.\n\n";
						input += "The most concerning issue is the crown funds. Presently, the kingdom is at a financial peak, flourishing from the investments that your mother previously made in various trading companies. Currently, ";
						input += "the crown can afford to dedicate a portion of the funds to improving an aspect of the kingdom.";
						temperament += 10;
						pp -=5;
						newPage(input, 3, "Invest more funding in the military.", "choice24Action", "Invest more funding in the education system.", "choice25Action", "Invest more funding in medicine and research.", "choice26Action");
						seeAll();
					}
			else if (choice.equals("choice24Action"))
			{
				clearAll();	
				input = "The news of the invaders plaguing Selese's borders are cause for concern. The kingdom's safety precedes all else; you direct extra funds to the military.\n\n";
				input += "The advisors seem skeptical, but otherwise approve of this decision.\n\n";
				input += "A projection of crown spending indicates that there is still coin to spare. One of the advisors, in charge of gauging public sentiments, brings up a petition that has been making ";
				input += "its rounds throughout the kingdom. There is a courtyard in the middle of the city, the site of many festivals. The people would like to erect a monument for the gods, to appease and ";
				input += "gain their favor. You can't help but wonder if this is a reaction to the possibility of you, the cursed heir, soon ascending to the throne.";
				fMilitary = 1;
				newPage(input, 3, "Build a monument to the gods.", "choice27Action", "Build a statue to commemorate your mother instead.", "choice28Action", "Save the funds.", "choice29Action");
				seeAll();
			}
			else if (choice.equals("choice25Action"))
			{
				clearAll();	
				input = "You believe it is vital that the upcoming generation grows to be intelligent and sensible. You direct extra funds to improving local schools and building more where necessary.\n\n";
				input += "The advisors seem wary, but ultimately approve of this decision.\n\n";
				input += "A projection of crown spending indicates that there is still coin to spare. One of the advisors, in charge of gauging public sentiments, brings up a petition that has been making ";
				input += "its rounds throughout the kingdom. There is a courtyard in the middle of the city, the site of many festivals. The people would like to erect a monument for the gods, to appease and ";
				input += "gain their favor. You can't help but wonder if this is a reaction to the possibility of you, the cursed heir, soon ascending to the throne.";
				pp +=5;
				fEducation = 1;
				newPage(input, 3, "Build a monument to the gods.", "choice27Action", "Build a statue to commemorate your mother instead.", "choice28Action", "Save the funds.", "choice29Action");
				seeAll();
			}
			else if (choice.equals("choice26Action"))
			{
				clearAll();	
				input = "With the promise of winter comes the promises of all sorts of illnesses; you decide it is best to invest more in medicine and further research.\n\n";
				input += "The advisors seem to approve of this decision.\n\n";
				input += "A projection of crown spending indicates that there is still coin to spare. One of the advisors, in charge of gauging public sentiments, brings up a petition that has been making ";
				input += "its rounds throughout the kingdom. There is a courtyard in the middle of the city, the site of many festivals. The people would like to erect a monument for the gods, to appease and ";
				input += "gain their favor. You can't help but wonder if this is a reaction to the possibility of you, the cursed heir, soon ascending to the throne.";
				fMedicine = 1;
				pp += 5;
				newPage(input, 3, "Build a monument to the gods.", "choice27Action", "Build a statue to commemorate your mother instead.", "choice28Action", "Save the funds.", "choice29Action");
				seeAll();
			}
			else if (choice.equals("choice27Action"))
			{
				clearAll();	
				input = "You approve the petition and allot a certain amount of funds to clear the courtyard and build a monument. Perhaps this is also your own faint prayer to them, that they may ";
				input += "be kind on you and your prophecy.\n\n";
				input += "The rest of the matters are small, mainly citizens who have requested a royal audience for their grievances. You sit through perhaps more than twenty of them, spending what's ";
				input += "left of your afternoon and most of your evening in the throne room. You don't know how your father or mother suffered through this nearly every day.\n\n";
				input += "You think you address perhaps twenty or so complaints.";
				legacy += 5;
				pp += 5;
				newPage(input, 3, "You sincerely try to help them.", "choice30Action", "You work through them as quickly as possible.", "choice31Action", "", "");
				seeAll();
			}
			else if (choice.equals("choice28Action"))
			{
				clearAll();	
				input = "You turn away from the petition and glance at the portrait of your mother hanging on the wall thoughtfully. What have the gods ever done for you, except damn you with your  ";
				input += "prophecy? And it was not the gods who raised this kingdom from ruins and brought it to prosperity - it was generations of your family that did so.\n\n";
				input += "You issue a directive to build a statue of your mother instead. The advisors look doubtful, but none of them speak up against it.\n\n";
				input += "The rest of the matters are small, mainly citizens who have requested a royal audience for their grievances. You sit through perhaps more than twenty of them, spending what's ";
				input += "left of your afternoon and most of your evening in the throne room. You don't know how your father or mother suffered through this nearly every day.\n\n";
				input += "You think you address perhaps twenty or so complaints.";
				legacy += 10;
				temperament -= 10;
				pp -= 5;
				newPage(input, 2, "You sincerely try to help them.", "choice30Action", "You work through them as quickly as possible.", "choice31Action", "", "");
				seeTwo();
			}
			else if (choice.equals("choice29Action"))
			{
				clearAll();	
				input = "You turn away the petition. \"We may need the funds some other time,\" you murmur decisively, and your advisors vocalize their approval for the first time.";
				input += "The rest of the matters are small, mainly citizens who have requested a royal audience for their grievances. You sit through perhaps more than twenty of them, spending what's ";
				input += "left of your afternoon and most of your evening in the throne room. You don't know how your father or mother suffered through this nearly every day.\n\n";
				input += "You think you address perhaps twenty or so complaints.";
				pp -= 10;
				extraFunds = 1;
				newPage(input, 2, "You sincerely try to help them.", "choice30Action", "You work through them as quickly as possible.", "choice31Action", "", "");
				seeTwo();
			}
			else if (choice.equals("choice30Action"))
			{
				clearAll();	
				input = "You do your best to help the people. It is exhausting, but it pays off - your advisors commend you on a job well done after you finish.\n\n";
				input += "Being able to retire to your chambers at the end of the day is a great relief.";
				legacy += 10;
				pp += 5;
				morality += 10;
				newPage(input, 1, "Continue.", "choice32Action", "", "", "", "");
				seeOne();
			}
			else if (choice.equals("choice31Action"))
			{
				clearAll();	
				input = "The complaints are bothersome, and many times you wonder how these people can complain over such trivial things. There are certainly others who are worse off than them; ";
				input += "their ungratefulness irritates you. You sit and grit through them nonetheless. Your impatience must be evident by the end of the evening, as people in the halls ";
				input += "scurry out your way to your chambers for some sorely-needed rest.";
				pp -= 5;
				morality -= 5;
				temperament -= 10;
				newPage(input, 1, "Continue.", "choice32Action", "", "", "", "");
				seeOne();
			}
			else if (choice.equals("choice32Action"))
			{
				clearAll();	
				if (extraFunds == 0)
				{
					input = "Your father passes away on a beautiful May afternoon, two days before the projected completion date of the courtyard. The construction, at least, gives the people something ";
					input += "to busy themselves with, so that they are not so fixated on the oddity of your reaction: ";
				}
				else
				{
					input = "Your father passes away on a beautiful May afternoon. You are aware of how odd the people may find your reaction: ";
				}
				if (temperament >= 60)
				{
					input += "you are eager to ascend to the throne.";
				}
				else
				{
					input += "you are resigned at your father's death and the responsibilities it brings with it.";
				}
				input += "\n\nTruthfully, you have never felt close to your father. You supposed that this makes his death easier to bear, but not the questioning stares that linger on you throughout the funeral.\n\n";
				input += "You are crowned less than a day later. ";
				if (pp >= 70)
				{
					input += "The ceremony is grand. You initially believe that it will be solemn, given how close it is to the funeral, but on the contrary the people seem livelier. Perhaps they are eager to forget ";
					input += "the tragedy. Perhaps they sincerely like you.";
				}
				else
				{
					input += "The ceremony is quiet, witnessed by only royal officers and a smattering of civilians. You do not blame them for not attending - the funeral was barely a fortnight ago, after all. Though ";
					input += "it is also possible that they simply did not care for your coronation.";
				}
				if (sAlliance == 0)
				{
					newPage(input, 1, "Continue.", "choice33Action", "", "", "", "");
				}
				else
				{
					newPage(input, 1, "Continue.", "choice34Action", "", "", "", "");
				}
				seeOne();
			}
			else if (choice.equals("choice33Action"))
			{
				clearAll();	
				input = "You do not have time to settle into your new title. Soon, word arrives from the north: Selese's defenses have crumbled, and the invaders have begun a systematic takeover ";
				input += "of the kingdom. It is unclear what their motives are yet: they may simply be seeking to plunder and torch the kingdom to the ground, or they may seek to rule it themselves. ";
				input += "If it is the latter, your kingdom will be significantly affected, as many trading routes run through Selese.\n\n";
				input += "You instinctively want to send aid, but your advisors warn that it may be too late and thus a waste of the kingdom's resources.";
				newPage(input, 2, "Send aid.", "choice35Action", "Fortify your own kingdom.", "choice36Action", "", "");
				seeTwo();
			}
					else if (choice.equals("choice35Action"))
					{
						clearAll();	
						input = "\"We must still try to help them,\" you insist to your advisors. You send a portion of your troops to Selese.\n\n";
						input += "Unfortunately, your advisors were right. A report arrives a week later, stating that the kingdom had been fortified against outsiders, and majority of the Selesian guards had been killed or ";
						input += "forced to fight for the invaders. Your troops attempt to storm the walls, but the enemy has the advantage. A scant few of your troops survive and come limping back.\n\n";
						input += "The people see, and they are terrified. You fear that this is your prophecy coming true.\n\n";
						input += "Along with the report comes talk of the invaders looking for their next conquest. Since you sent your troops to Selese, you do not have nearly as many to defend your own walls with. ";
						input += "Conscription is generally unfavored by the masses, but you may have no choice.";
						pp += 5;
						morality += 5;
						legacy += 10;
						newPage(input, 2, "Conscript your citizens.", "choice37Action", "Train your remaining troops as best as you can.", "choice38Action", "", "");
						seeTwo();
					}
							else if (choice.equals("choice37Action"))
							{
								clearAll();	
								input = "As you expect, the conscription is met with an outcry - most with accusation, that if you had not sent out troops to begin with, there would have been enough people left to ";
								input += "defend the kingdom. \"Unfortunately,\" you tell them firmly, \"that is not the case.\" You give them an ultimatum: fight for their lives, or die - either at the hands of the invaders,";
								input += "or by your sword afterwards.\n\n";
								input += "Assuming you live through this.";
								pp -= 10;
								temperament -= 10;
								legacy += 10;
								newPage(input, 1, "You prepare for the invasion.", "ENDING1", "", "", "", "");
								seeOne();
							}
							else if (choice.equals("choice38Action"))
							{
								clearAll();	
								input = "\"You expect us to roll over and die at the ends of their sword?\" your army commander demands.\n\n";
								input += "\"I expect you to protect this kingdom to the best of your ability,\" you answer coldly, unmoved by his anger. \"That is your job, not these innocent people's.\"\n\n";
								input += "\"When the invaders ream through our gates,\" he snarls, \"they will not care who is innocent.\"\n\n";
								input += "His words are an ugly promise, but you have no time to dwell on them. There is work to be done.";
								pp += 5;
								morality += 5;
								legacy += 25;
								newPage(input, 1, "You prepare for the invasion.", "ENDING1", "", "", "", "");
								seeOne();
							}
					else if (choice.equals("choice36Action"))
					{
						clearAll();	
						input = "\"Selese is lost.\" You pluck the sigil from the war table and set it aside. \"We must defend our own.\" Your advisors are relieved by your decision and ";
						input += "offer their counsel. Luckily, you have enough funds to supply your troops on the wall with more ammunition and weapons. ";
						if (fMilitary == 1)
						{
							input += "The investments you made into the military earlier have also paid off; when you make your rounds, you are satisfied with the discipline that you see. ";
						}
						if (temperament >= 65)
						{
							input += "\n\nThe people seem to believe that you are capable of protecting the kingdom. ";
						}
						else
						{
							input += "\n\nThe people do not seem certain that you can protect the kingdom. ";
						}
						input += "However they may feel, you know you will soon be put to the test.";
						pp += 5;
						morality += 10;
						legacy += 5;
						newPage(input, 1, "You prepare for the invasion.", "ENDING2", "", "", "", "");
						seeOne();
					}
			else if (choice.equals("choice34Action"))
			{
				clearAll();	
				input = "The new title feels foreign, but there is thankfully a period of calm when you can regather your wits. Not long after news of your coronation spreads, however, you receive congratulations from Selese - with a marriage offer concerning their two heirs who have recently come of age.\n\n";
				input += "A marriage, your advisors comment, would be beneficial to both kingdoms. Selese seems to have warded off the invaders thanks to your father's aid, but should it happen again, you would be bound by alliegance to defend them - and they would be bound in turn ";
				input += "to defend your kingdom.";
				newPage(input, 3, "Marry the future king.", "choice39Action", "Marry the future queen.", "choice40Action", "Refuse the marriage offer.", "choice41Action");
				seeAll();
			}
					else if (choice.equals("choice39Action"))
					{
						clearAll();	
						input = "You decide to take the future Selesian king's hand in marriage. ";
						if (gender == 'M')
						{
							input += "Your advisors question the issue of continuing your bloodline, but you brush them off. It is none of their business - and now that you are " + getTitle(gender, 'y') + ", ";
							input += "you can change the system, if you'd like.";
						}
						input += "\n\nNews of your acceptance quickly spreads. ";
						if (pp >= 65)
						{
							input += "The people are overjoyed for you, and there are soon questions about the wedding.";
						}
						else
						{
							input += "You hear murmurs of pity for your future spouse and what curses you may inflict him with. You are quick to put down those rumors, however.";
						}
						legacy += 25;
						temperament += 10;
						newPage(input, 1, "Your wedding day soon arrives.", "ENDING3", "", "", "", "");
						seeOne();
					}
					else if (choice.equals("choice40Action"))
					{
						clearAll();	
						input = "You decide to take the future Selesian queen's hand in marriage. ";
						if (gender == 'F')
						{
							input += "Your advisors question the issue of continuing your bloodline, but you brush them off. It is none of their business - and now that you are " + getTitle(gender, 'y') + ", ";
							input += "you can change the system, if you'd like.";
						}
						input += "\n\nNews of your acceptance quickly spreads. ";
						if (pp >= 65)
						{
							input += "The people are overjoyed for you, and there are soon questions about the wedding.";
						}
						else
						{
							input += "You hear murmurs of pity for your future spouse and what curses you may inflict her with. You are quick to put down those rumors, however.";
						}
						legacy += 25;
						temperament += 10;
						newPage(input, 1, "Your wedding day soon arrives.", "ENDING3", "", "", "", "");
						seeOne();
					}
					else if (choice.equals("choice41Action"))
					{
						clearAll();	
						input = "You decide to put off marriage for now. You have hardly stepped into your new role, after all; you want to see what changes you can bring about before you decide to unite your ";
						input += "kingdom with another. Your advisors attempt to persuade you, but you are firm in your decision.\n\n";
						input += "Selese does not seem to take offense; they still seem graciously grateful for the assistance that your father had lent them during the attempted invasion.\n\n";
						input += "Perhaps in a few years you may reconsider.";
						newPage(input, 1, "Continue.", "ENDING4", "", "", "", "");
						seeOne();
					}
			else if (choice.equals("ENDING1"))
			{
				clearAll();	
				ending = 1;
				if (fMilitary == 1)
				{
					input = "Despite your planning, the invasion still takes the kingdom by surprise. Luckily, you are able to fend off the initial strike; your soldiers are tactical and unyielding, managing to quickly recover from the surprise and repel the enemy forces from the wall.";
				}
				else
				{
					input = "The invasion takes the kingdom by surprise. Your soldiers fumble to fend off the initial strike, but luckily recover quickly enough to repel the forces from the wall. ";
				}
				input += "\n\nFrom there, the struggle only turns uglier. The enemy lays siege to your kingdom for weeks, but luckily your kingdom is well-stocked with supplies. ";
				if (pp <= 35)
				{
					input += "Morale is low, however, both in your troops and in your civilians. Within a week, there is rampant looting and ransacking amongst your own people as they scramble to secure their own safety. ";
				}
				else
				{
					input += "Fortunately, morale remains decent, ensuring that your troops are ready to fight for you and that your people cooperate more easily with fortifying the city. ";
				}
				input += "Yet this does not prove to be enough. On the dawn of the second month, enemy forces finally break through your walls. Your troops, weakened in force, are unable to fend them off this time. ";
				input += "It does not take long for the invaders to storm the palace, apprehend you, and bring you out to the courtyard where they announce that you will be made an example of. The gleam of executioner's ax is indication enough of what they plan to do.\n\n";
				input += "You are slain in front of your people.";
				newPage(input, 1, "Your legacy is all the remains of you.", "LastPage", "", "", "", "");
				seeOne();
			}
			else if (choice.equals("ENDING2"))
			{
				clearAll();	
				ending = 2;
				if (fMilitary == 1)
				{
					input = "Despite your planning, the invasion still takes the kingdom by surprise. Luckily, you are able to fend off the initial strike; your soldiers are tactical and unyielding, managing to quickly recover from the surprise and repel the enemy forces from the wall.";
				}
				else
				{
					input = "The invasion takes the kingdom by surprise. Your soldiers fumble to fend off the initial strike, but luckily recover quickly enough to repel the forces from the wall. ";
				}
				input += "\n\nFrom there, the struggle only turns uglier. The enemy lays siege to your kingdom for weeks, but luckily your kingdom is well-stocked with supplies. ";
				if (pp <= 35)
				{
					input += "Morale is low, however, both in your troops and in your civilians. Within a week, there is rampant looting and ransacking amongst your own people as they scramble to secure their own safety. ";
				}
				else
				{
					input += "Fortunately, morale remains decent, ensuring that your troops are ready to fight for you and that your people cooperate more easily with fortifying the city. ";
				}
				input += "On the dawn of the second month, you set out a final coordinated attack against the invaders with the last of your troops. You can only pray that they succeed.\n\n";
				input += "They do.\n\n";
				input += "By the next day, the kingdom gates are open once more, and there are widespread efforts to clean up the city.";
				newPage(input, 1, "Your legacy has begun to set in stone.", "LastPage", "", "", "", "");
				seeOne();
			}
			else if (choice.equals("ENDING3"))
			{
				clearAll();	
				ending = 3;
				input = "Your wedding is a glorious affair. Selese is more than willing to help fund the fanfare, and it isn't long before the fated coronation held in the courtyard of your kingdom. Selesians ";
				input += "attend in droves, clearly excited for their beloved ruler's wedding, ";
				if (pp <= 35)
				{
					input += "although most of your people seem only to attend by obligation. ";
				}
				else
				{
					input += "as do your people. ";
				}
				input += "\n\nOnce joined, you and your spouse rule your kingdoms side by side for many long years. ";
				if (temperament >= 65 && morality >= 65)
				{
					input += "Over time, you even come to sincerely love each other. ";
					temperament += 10;
				}
				else
				{
					input += "You don't feel as though you ever truly fall in love, but it matters little. ";
					morality -= 5;
				}
				input += "You manage to bring prosperity to both kingdoms. Your prophecy seems so far away now.";
				newPage(input, 1, "Your legacy has begun to set in stone.", "LastPage", "", "", "", "");
				seeOne();
			}
			else if (choice.equals("ENDING4"))
			{
				clearAll();	
				ending = 4;
				input = "You rule for many long years, alone but never truly lonely. Your ambition quickly reveals itself once you set to work on implementing new policies. ";
				if (morality <= 35)
				{
					input += "Your people scarcely approve of them, but this matters little to you - what could they possibly know of ruling a kingdom?\n\n";
				}
				else
				{
					input += "Your changes are met with overall approval, and you soon garner the trust of your people.\n\n";
				}
				input += "You cultivate friendships with other kingdoms, quickly establishing a vast network of dependable alliances, should you need them someday. Meanwhile, your own ";
				input += "kingdom becomes renowned for how it has flourished.\n\n";
				input += "On many days you find yourself strolling through the kingdom and admiring all that you have helped build. You often stop by ";
				if (extraFunds == 1)
				{
					input += "the courtyard and look up at the sky, wondering how you will be remembered.";
				}
				else
				{
					input += "the monument in the courtyard, look up at the stone reminder, and wonder how you will be remembered.";
				}
				newPage(input, 1, "Your legacy has begun to set in stone.", "LastPage", "", "", "", "");
				seeOne();
			}
			else if (choice.equals("LastPage"))
			{
				clearAll();	
				input = "History will know you as the " + getTitle(gender, 'y') + " " + charname + " who ";
				if (ending == 1)
				{
					input += "led their kingdom to ruin. Your tale becomes the subject of many ";
					if (temperament <= 35)
					{
						input += "cautionary tales to scare children at night. ";
					}
					else
					{
						input += "songs and ballads that lament the final siege. ";
					}
					input += "\n\nYour people, what few of them have survived, reflect on your ";
					if (pp <= 35)
					{
						input += "callousness towards your subjects. They wonder if this was a product of your prophecy - or if the prophecy was a product of your nature, predetermined at birth.\n\n";
					}
					else
					{
						input += "unfortunate end. They grieve for you, and they wonder what you could have accomplished in your time, had you been given more.\n\n";
					}
					if (legacy >= 50)
					{
						input += "Despite your defeat, your legacy will endure for centuries to come.";
					}
					else
					{
						input += "However, like the end of your kingdom, your legacy soon fades out, until you are resigned to the effacing waves of time.";
					}
				}
				if (ending == 2)
				{
					input += "led their kingdom to victory. Your tale becomes the subject of many ";
					if (temperament <= 35)
					{
						input += "lectures on bravery and hardheadedness. ";
					}
					else
					{
						input += "songs and ballads that lionize the final siege. ";
					}
					input += "\n\nYour people tend to reflect on your ";
					if (pp <= 35)
					{
						input += "callousness towards them. They wonder if this is a product of your prophecy - or if the prophecy was a product of your nature, predetermined at birth.\n\n";
					}
					else if (pp <= 60)
					{
						input += "fairness as a ruler. They seem relieved that the prophecy did not resign them to a cruel monarch.\n\n";
					}
					else
					{
						input += "kind, amiable nature. They view themselves as lucky to have you as their " + getTitle(gender, 'y') + ", for you continue to rule justly.\n\n";
					}
					input += "Yet it is inevitable that you pass away; ";
					if (legacy >= 50)
					{
						input += "however, you can rest in peace, knowing that your legacy will endure the centuries to come.";
					}
					else
					{
						input += "unfortunately, as with so many others, your legacy soon fades out, until you are resigned to the effacing waves of time.";
					}
				}
				if (ending == 3)
				{
					input += "led their kingdom to peace. Your tale becomes the subject of many ";
					if (temperament >= 65 && morality >= 65)
					{
						input += "epic ballads, lauding both your accomplishments and the love you found with your spouse. ";
					}
					else
					{
						input += "fond tales that inspire fondness and nostalgia. ";
					}
					input += "\n\nYour people often reflect on your ";
					if (pp <= 35)
					{
						input += "callousness towards them. They wonder if this is a product of your prophecy - or if the prophecy was a product of your nature, predetermined at birth. They wonder if this puts a strain on your relationship.\n\n";
					}
					else if (pp <= 60)
					{
						input += "fairness as a ruler. They seem relieved that the prophecy did not resign them to a cruel monarch, though some believe that your marriage helped rein you in.\n\n";
					}
					else
					{
						input += "kind, amiable nature. They view themselves as lucky to have you as their " + getTitle(gender, 'y') + ", for you continue to rule justly with your spouse.\n\n";
					}
					input += "Yet it is inevitable that you pass away, clutching your spouse's hand tightly and looking up at their kind, smiling eyes; ";
					if (legacy >= 50)
					{
						input += "however, you can rest in peace, knowing that your legacy will endure the centuries to come.";
					}
					else
					{
						input += "unfortunately, as with so many others, your legacy soon fades out, until you are resigned to the effacing waves of time.";
					}
				}
				if (ending == 4)
				{
					input += "led their kingdom to prosperity. Your tale becomes the subject of many ";
					if (temperament >= 65)
					{
						input += "epic ballads, lauding the many feats you accomplished single-handedly.\n\n";
					}
					else
					{
						input += "success stories, as well as inspiration to those who feel lost in their lives.\n\n";
					}
					input += "Your people often reflect on your ";
					if (pp <= 35)
					{
						input += "callousness towards them, however. They wonder if this is a product of your prophecy - or if the prophecy was a product of your nature, predetermined at birth. They wonder if you are ever lonely behind the walls of your palace.\n\n";
					}
					else if (pp <= 60)
					{
						input += "fairness as a ruler. They seem relieved that the prophecy did not resign them to a cruel monarch, though some believe that a marriage might ensure that does not happen.\n\n";
					}
					else
					{
						input += "kind, amiable nature. They view themselves as lucky to have you as their " + getTitle(gender, 'y') + ", for you continue to rule justly.\n\n";
					}
					input += "Yet it is inevitable that you pass away, surrounded by the advisors who have supported you through the years; ";
					if (legacy >= 50)
					{
						input += "however, you can rest in peace, knowing that your legacy will endure the centuries to come.";
					}
					else
					{
						input += "unfortunately, as with so many others, your legacy soon fades out, until you are resigned to the effacing waves of time.";
					}
				}
				newPage(input, 1, "END", "LastPage", "", "", "", "");
				seeOne();
			}
			else
			{
				System.exit(0);		
			}
		}
	}
}