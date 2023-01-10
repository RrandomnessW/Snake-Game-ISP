/*
Ryan Wang
Mrs. Krasteva
January 9, 2020
This program will be able to play the snake game.
The menu has 5 options. Play game, instructions, difficulty selection, show highscores, and quit.
Play game will bring you to a screen where you must enter your name. The name cannot have any spaces.
Then you are able to play the game as a snake eating apples. If you hit the edges or your self, you lose the game.
Your score is then stored into a file.
The instructions options show the instructions.
The difficulty selection will determine how fast the snake moves and which colour and board is going to be displayed.
High scores will show you the top 10 scores in the game.
Pressing any other key will result in quitting the menu and closing th eprogram.

Sources used:
Ben Zeng - got help for receiving input on moving the snake. Helped me with the thread
https://stackoverflow.com/questions/20251762/sorting-an-int-array-from-highest-to-lowest - used to know how to sort an int array from greatest to least.

*/
import java.awt.*;
import hsa.Console;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import javax.swing.JOptionPane;
import java.util.*;


/*
    Global Variable Dictionary

    type            name of var     explanation
   |------------------------------------------------------------------------|
   |Console         c               Used to display a console window.       |
   |------------------------------------------------------------------------|
   |char            mInput          Used to store the menu option.          |
   |------------------------------------------------------------------------|
   |String          name            Used to store the user's name.          |
   |------------------------------------------------------------------------|
   |char            direction       Used to store the direction of the snake|
   |------------------------------------------------------------------------|
   |int             score           Used to keep track of the user's score. |
   |------------------------------------------------------------------------|
   |int[][]         location        Used to represent the board and store   |
   |                                the snake's positions.                  |
   |------------------------------------------------------------------------|
   |char            difficulty      Used to keep track of the difficulty.   |
   |------------------------------------------------------------------------|
   |char            boardColor      Used to keep track of the board colour. |
   |------------------------------------------------------------------------|
   |String          tailComms       Used to keep track of where the tail    |
   |                                will go.                                |
   |------------------------------------------------------------------------|


*/


public class HungrySnake
{
    Console c = new Console (); //- used to display console
    char mInput; //- used in mainMenu() to get the proper menu option
    String name; //- used in enterName() to get the user inputted name
    char direction; //- used in newGame() to give the snake a direction
    int score; //- used to keep track of score
    int[] [] location; //- used to keep track of where the snake is
    char difficulty = '2'; //- keeps track of difficulty
    char boardColor = 'g'; //- keeps track of the board colour
    String tailComms;

    public void splashScreen ()  //display a splash screen for two seconds
    {
	int n1 = 100;
	int n2 = 130;
	int p1 = 200;
	int p2 = 230;
	for (int i = 0 ; i < 50 ; i++)
	{
	    c.setColor (Color.CYAN);
	    c.fillRect (0, 0, 640, 500);
	    c.setColor (new Color (100, 255, 100));
	    c.setFont (new Font ("Courier", Font.BOLD, 80));
	    if (i < 12)
	    {
		n1 += 4;
		n2 -= 4;
		p1 += 4;
		p2 -= 4;
		c.drawString ("H", 100, n1);
		c.drawString ("U", 150, n2);
		c.drawString ("N", 200, n1);
		c.drawString ("G", 250, n2);
		c.drawString ("R", 300, n1);
		c.drawString ("Y", 350, n2);
		c.drawString ("S", 100, p1);
		c.drawString ("N", 150, p2);
		c.drawString ("A", 200, p1);
		c.drawString ("K", 250, p2);
		c.drawString ("E", 300, p1);
		c.setColor (new Color (50, 50, 50));
		c.setFont (new Font ("Courier", Font.BOLD, 50));
		c.drawString ("Snake!", 100, 300);
	    }
	    else if (i < 25)
	    {
		n1 -= 4;
		n2 += 4;
		p1 -= 4;
		p2 += 4;
		c.drawString ("H", 100, n1);
		c.drawString ("U", 150, n2);
		c.drawString ("N", 200, n1);
		c.drawString ("G", 250, n2);
		c.drawString ("R", 300, n1);
		c.drawString ("Y", 350, n2);
		c.drawString ("S", 100, p1);
		c.drawString ("N", 150, p2);
		c.drawString ("A", 200, p1);
		c.drawString ("K", 250, p2);
		c.drawString ("E", 300, p1);
		c.setColor (new Color (50, 50, 50));
		c.setFont (new Font ("Courier", Font.BOLD, 50));
		c.drawString ("Snake!", 100, 350);
	    }
	    else if (i < 37)
	    {
		n1 += 4;
		n2 -= 4;
		p1 += 4;
		p2 -= 4;
		c.drawString ("H", 100, n1);
		c.drawString ("U", 150, n2);
		c.drawString ("N", 200, n1);
		c.drawString ("G", 250, n2);
		c.drawString ("R", 300, n1);
		c.drawString ("Y", 350, n2);
		c.drawString ("S", 100, p1);
		c.drawString ("N", 150, p2);
		c.drawString ("A", 200, p1);
		c.drawString ("K", 250, p2);
		c.drawString ("E", 300, p1);
		c.setColor (new Color (50, 50, 50));
		c.setFont (new Font ("Courier", Font.BOLD, 50));
		c.drawString ("Snake!", 100, 400);
	    }
	    else if (i < 50)
	    {
		n1 -= 4;
		n2 += 4;
		p1 -= 4;
		p2 += 4;
		c.drawString ("H", 100, n1);
		c.drawString ("U", 150, n2);
		c.drawString ("N", 200, n1);
		c.drawString ("G", 250, n2);
		c.drawString ("R", 300, n1);
		c.drawString ("Y", 350, n2);
		c.drawString ("S", 100, p1);
		c.drawString ("N", 150, p2);
		c.drawString ("A", 200, p1);
		c.drawString ("K", 250, p2);
		c.drawString ("E", 300, p1);
		c.setColor (new Color (50, 50, 50));
		c.setFont (new Font ("Courier", Font.BOLD, 50));
		c.drawString ("Snake!", 100, 450);
	    }
	    try
	    {
		Thread.sleep (30);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public void title ()  //clears screen and displays title
    {
	c.clear ();
	c.print (' ', 34);
	c.setTextColor(Color.black);
	c.println ("Hungry Snake");
	c.println ();
    }


    public void mainMenu ()  //displays the menu and gets menu inputs
    {
	
	c.setColor (Color.CYAN);
	c.setTextBackgroundColor (Color.cyan);
	c.fillRect (0, 0, 640, 500);
	title ();
	c.println ("Enter in the number of the menu option that you want.");
	c.println ();
	c.println ("1. New Game");
	c.println ("2. Instrucions");
	c.println ("3. Difficulty Select");
	c.println ("4. Show Highscores");
	c.println ("5. Quit");
	while (true)
	{
	    mInput = c.getChar ();
	    if (mInput >= 49 && mInput <= 53)
	    {
		break;
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "Please enter a number between 1 and 5 for the menu option.", "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}

    }


    public void instructions ()  //displays instructions
    {
	title ();
	c.println ("Use the keys WASD to move the snake. Eat food to grow! You lose if the snake \nhits the wall or your snake self.");
	c.println ("Go into instructions to change difficulty and board colour. Changing difficulty \nchanges speed of snake and amount of points you get.");
	c.println("You get 1 point on easy, 2 points ");
	c.println();
	c.println ("Press any key to continue.");
	pauseProgram ();
    }


    /*
	Global Variable Dictionary

	type            name of var     explanation
       |------------------------------------------------------------------------|
       |String          diff            Used to receive and store the difficulty|
       |------------------------------------------------------------------------|
       |String          bc              Used to get and store the board colour. |
       |------------------------------------------------------------------------|
    */
    public void levelSelection ()  //allow level selection and colour selection
    {
	String diff = "";
	String bc = "";
	title ();
	c.println ();
	c.println ("1. Easy      2. Normal       3. Hard");
	c.println ();
	c.println ("a. Red       b. Blue     c. Yellow");
	c.println ("d. Orange    e. Green    f. Purple   g. Black");
	c.println ();

	while (true)
	{
	    c.print ("Enter in the number that corresponds to difficulty option: ");
	    difficulty = c.getChar (); //nineth line
	    c.println (difficulty);
	    if (difficulty >= 49 && difficulty <= 51)
	    { //49-51
		break;
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "Please enter a number between 1 and 3 to get the difficulty.", "ERROR", JOptionPane.ERROR_MESSAGE);
		c.setCursor (9, 1);
		c.println ();
		c.setCursor (9, 1);
	    }
	}

	if (difficulty == '1')
	{
	    diff = "Easy";
	}
	else if (difficulty == '2')
	{
	    diff = "Normal";
	}
	else if (difficulty == '3')
	{
	    diff = "Hard";
	}


	while (true)
	{
	    c.print ("Enter in the letter that corresponds to the colour option: ");
	    boardColor = c.getChar ();
	    c.println (boardColor);
	    if (boardColor >= 97 && boardColor <= 103)
	    { //bug
		break;
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "Please enter a letter from a to g to get the color option.", "ERROR", JOptionPane.ERROR_MESSAGE);
		c.setCursor (10, 1);
		c.println ();
		c.setCursor (10, 1);
	    }
	}

	if (boardColor == 'a')
	{
	    bc = "Red";
	}
	else if (boardColor == 'b')
	{
	    bc = "Blue";
	}
	else if (boardColor == 'c')
	{
	    bc = "Yellow";
	}
	else if (boardColor == 'd')
	{
	    bc = "Orange";
	}
	else if (boardColor == 'e')
	{
	    bc = "Green";
	}
	else if (boardColor == 'f')
	{
	    bc = "Purple";
	}
	else if (boardColor == 'g')
	{
	    bc = "Black";
	}

	c.println ("The difficulty is:" + diff);
	c.println ("The colour is:" + bc);
	c.println ();
	c.println ("Press any other key to go back to the main menu.");
	pauseProgram ();

    }


    /*
	Global Variable Dictionary

	type            name of var     explanation
       |------------------------------------------------------------------------|
       |String          fileName        Used to store the file name: HighScores.|
       |------------------------------------------------------------------------|
       |String[][]      sco             Used to store scores and names.         |
       |------------------------------------------------------------------------|
       |String          line            Used to store the lines of the file.    |
       |------------------------------------------------------------------------|
       |BufferedReader[][]   input      Used to be able to read the file.       |
       |------------------------------------------------------------------------|
       |File            myText          Used to open file and read from it.     |
       |------------------------------------------------------------------------|
       |char            tmp             Used to see if file needs to be cleared.|
       |------------------------------------------------------------------------|
       |PrintWriter     output          Used to write to file.                  |
       |------------------------------------------------------------------------|
    */
    public void showHighscores ()  //shows ten highscores
    {
	c.setColor (Color.CYAN);
	c.setTextBackgroundColor (Color.cyan);
	c.fillRect (0, 0, 640, 500);
	title ();
	String fileName = "HighScores";
	String[] [] sco = new String [10] [2];
	String line;
	BufferedReader input;
	File myText;
	char tmp;
	PrintWriter output;
	try
	{

	    myText = new File (fileName);

	    input = new BufferedReader (new FileReader (fileName));
	    line = input.readLine ();
	    int i = 0;
	    c.println ("High Scores");
	    c.print ("Name", 50);
	    c.println ("Scores");
	    c.println ();
	    while (line != null && i < 10)
	    {
		sco [i] = line.split (" ");
		c.print (sco [i] [1], 50);
		c.println (sco [i] [0]);
		i++;
		line = input.readLine ();
	    }

	}


	catch (Exception e)
	{
	}
	c.println ();
	c.println ("Press c to clear the highscore file.");
	c.println ();
	c.println ("Press any key to continue...");
	while (true)
	{
	    tmp = c.getChar ();
	    if (tmp == 'c')
	    {
		try
		{
		    output = new PrintWriter (new FileWriter (fileName));
		    output.close ();
		}
		catch (Exception e)
		{
		}

	    }
	    else
	    {
		break;
	    }
	}

	//pauseProgram ();
    }


    public void goodbye ()  //displays a farewell message
    {
	c.clear ();
	c.println ("latered. See you later!");
	c.println ("press any key to leave the game");
	pauseProgram ();
	c.close ();
    }


    /*
	Global Variable Dictionary

	type            name of var     explanation
       |------------------------------------------------------------------------|
       |char            cn              Used to store inputted characters.      |
       |------------------------------------------------------------------------|
    */
    public void enterName ()  //asks for the user's name
    {
	title (); //2 lines
	name = "";
	char cn;
	c.println ("Press enter key when you have finished entering your name. Press backspace ");
	c.println ("to remove a character.");

	while (true)
	{
	    c.setCursor (5, 1);
	    c.println ();
	    c.println ();
	    c.println ();
	    c.setCursor (6, 1);
	    c.println ("Name: " + name);
	    c.setCursor (5, 1);
	    c.println ("Enter character: "); //4
	    cn = c.getChar ();
	    c.print (cn);

	    if (cn == 8 && name.length () > 0)
	    { //backspace
		name = name.substring (0, name.length () - 1);
	    }
	    else if (cn == 10)
	    { //enter
		if(name.length()==0)
		{
		    JOptionPane.showMessageDialog (null, "Please have at least one character in your name.", "Enter a Name", JOptionPane.ERROR_MESSAGE);
		}else{
		    break;
		}
	    }
	    else if (cn == 32)
	    {
		JOptionPane.showMessageDialog (null, "Please do not enter the space as your character.", "No Spaces", JOptionPane.ERROR_MESSAGE);
	    }
	    else
	    {
		name += cn;
	    }
	    if (name.length () > 20)
	    {
		JOptionPane.showMessageDialog (null, "Your name exceeds the character limit", "Name Too Long", JOptionPane.ERROR_MESSAGE);
	    }
	}
    }


    /*
	Global Variable Dictionary

	type            name of var     explanation
       |------------------------------------------------------------------------|
       |boolean         loseGame        Used to see if the game has been lost.  |
       |------------------------------------------------------------------------|
       |boolean         foodExist       Used to see if food already exists.     |
       |------------------------------------------------------------------------|
       |int[]           head            Used to store the location of the head. |
       |------------------------------------------------------------------------|
       |int[]           tail            Used to store the location of the tail. |
       |------------------------------------------------------------------------|
       |int[]           appCoord        Used to store coordinates of the apple. |
       |------------------------------------------------------------------------|
       |char            preT            Used to store the previous tail command.|
       |------------------------------------------------------------------------|
    */
    public void newGame ()  //initiates a new game
    {
	direction = 'd';
	location = new int [25] [25];
	tailComms = "DDD";
	score = 0;
	boolean loseGame = false;
	boolean foodExist = false; //- used to keep track of the existence of food
	int[] head = new int [2];
	int[] tail = new int [2];
	head [0] = 12; //y position (index)
	head [1] = 5; //x position (index)
	tail [0] = 12; //y position (index)
	tail [1] = 2; //x position (index)
	int[] appCoord = new int [2]; //x,y
	appCoord [0] = (int) (Math.random () * 24);
	appCoord [1] = (int) (Math.random () * 24);
	char preT; //previous tail command
	for (int i = 2 ; i < 6 ; i++)
	{
	    location [12] [i] = 1; //1 means that there the snake is at this position
	}

	while (!loseGame)
	{
	    c.setColor (Color.WHITE);
	    c.fillRect (0, 0, 640, 500);
	    if (boardColor == 'g')
	    {
		displayBoard (difficulty);
	    }
	    else
	    {
		displayBoard (difficulty, boardColor);
	    }

	    c.setColor (new Color (0, 120, 0));
	    for (int i = 0 ; i < 25 ; i++)
	    {
		for (int j = 0 ; j < 25 ; j++)
		{
		    if (location [i] [j] == 1)
		    {
			c.fillRect (j * 20, i * 20, 20, 20);
		    }
		}
	    }
	    c.setColor (Color.black);
	    c.setFont (new Font ("SansSerif", Font.BOLD, 40));
	    c.drawString ("Score:", 510, 170);
	    c.setFont (new Font ("SansSerif", Font.BOLD, 100));
	    //display score
	    if (score < 10)
	    {
		c.drawString (score + "", 540, 250);
	    }
	    else if (score < 100)
	    {
		c.drawString (score + "", 520, 250);
	    }
	    else if (score < 999)
	    {
		c.drawString (score + "", 500, 250);
	    }

	    appCoord = showFood (appCoord, foodExist);
	    location [appCoord [0]] [appCoord [1]] = 2; //2 means there is an apple here
	    c.setColor (new Color (180, 0, 0));
	    c.fillRect (appCoord [1] * 20 + 5, appCoord [0] * 20 + 5, 10, 10);
	    foodExist = true;

	    moveSnake ();
	    //move snake's position
	    //remove the number at tail.
	    preT = tailComms.charAt (0);
	    if ((tailComms.charAt (0) + "").equalsIgnoreCase ("w"))
	    {
		location [tail [0]] [tail [1]] = 0;
		tail [0] -= 1;
	    }
	    else if ((tailComms.charAt (0) + "").equalsIgnoreCase ("a"))
	    {
		location [tail [0]] [tail [1]] = 0;
		tail [1] -= 1;
	    }
	    else if ((tailComms.charAt (0) + "").equalsIgnoreCase ("s"))
	    {
		location [tail [0]] [tail [1]] = 0;
		tail [0] += 1;
	    }
	    else if ((tailComms.charAt (0) + "").equalsIgnoreCase ("d"))
	    {
		location [tail [0]] [tail [1]] = 0;
		tail [1] += 1;
	    }
	    tailComms = tailComms.substring (1, tailComms.length ());
	    //change tail
	    if (isHit (head, tail) == true)
	    {
		loseGame = true;
		break;
	    }
	    //start at the snake's head, depending on the direction, put 1 in the array adjacent to head position
	    if ((direction + "").equalsIgnoreCase ("w"))
	    {
		location [head [0] - 1] [head [1]] = 1;
		head [0] -= 1;
	    }
	    else if ((direction + "").equalsIgnoreCase ("a"))
	    {
		location [head [0]] [head [1] - 1] = 1;
		head [1] -= 1;
	    }
	    else if ((direction + "").equalsIgnoreCase ("s"))
	    {
		location [head [0] + 1] [head [1]] = 1;
		head [0] += 1;
	    }
	    else if ((direction + "").equalsIgnoreCase ("d"))
	    {
		location [head [0]] [head [1] + 1] = 1;
		head [1] += 1;
	    }
	    //change head
	    if (head [0] == appCoord [0] && head [1] == appCoord [1])
	    { //eats apple
		if (difficulty == '1')
		{ //easy
		    score++;
		}
		else if (difficulty == '2')
		{ //normal
		    score += 2;
		}
		else
		{ //hard
		    score += 4;
		}

		if ((preT + "").equalsIgnoreCase ("w"))
		{
		    tail [0] += 1;
		    location [tail [0]] [tail [1]] = 1;
		}
		else if ((preT + "").equalsIgnoreCase ("a"))
		{
		    tail [1] += 1;
		    location [tail [0]] [tail [1]] = 1;
		}
		else if ((preT + "").equalsIgnoreCase ("s"))
		{
		    tail [0] -= 1;
		    location [tail [0]] [tail [1]] = 1;
		}
		else if ((preT + "").equalsIgnoreCase ("d"))
		{
		    tail [1] -= 1;
		    location [tail [0]] [tail [1]] = 1;
		}
		tailComms = preT + tailComms;
		foodExist = false;
	    }
	}



	if (loseGame)
	{
	    loseGame ();
	}


	else if (difficulty == '1' && score == 625 || difficulty == '2' && score == 1250 || difficulty == '3' && score == 2500)
	{
	    winGame ();
	}
	storeScore ();
    }


    public void displayBoard (int d)  //displays the default coloured board depending on difficulty
    {
	if (d == '1')
	{
	    c.setColor (Color.WHITE);
	    c.fillRect (0, 0, 640, 500);
	    for (int i = 0 ; i < 13 ; i++)
	    {
		if (i % 2 == 0)
		{
		    c.setColor (new Color (70, 70, 70));
		    c.fillRect (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
		else
		{
		    c.setColor (Color.white);
		    c.fillRect (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
	    }
	}


	else if (d == '2')
	{
	    c.setColor (Color.WHITE);
	    c.fillRect (0, 0, 640, 500);
	    for (int i = 0 ; i < 25 ; i++)
	    {
		for (int j = 0 ; j < 25 ; j++)
		{
		    if ((j + i) % 2 == 0)
		    {
			c.setColor (new Color (70, 70, 70));
			c.fillRect (i * 20, j * 20, 20, 20);
		    }
		}
	    }
	}


	else if (d == '3')
	{
	    c.setColor (Color.WHITE);
	    c.fillRect (0, 0, 640, 500);
	    for (int i = 0 ; i < 13 ; i++)
	    {
		if (i % 2 == 0)
		{
		    c.setColor (new Color (70, 70, 70));
		    c.fillOval (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
		else
		{
		    c.setColor (Color.white);
		    c.fillOval (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
	    }
	}
    }


    /*
	Global Variable Dictionary

	type            name of var     explanation
       |------------------------------------------------------------------------|
       |Color           bCol            Keeps track of the board colour and     |
       |                                design.                                 |
       |------------------------------------------------------------------------|
    */
    public void displayBoard (int d, char col)  //sets color and board design
    { //needs to be finished
	Color bCol = new Color (0, 0, 0);
	if (col == 'a') //red
	{
	    bCol = new Color (255, 50, 50);
	}


	else if (col == 'b') //blue
	{
	    bCol = new Color (50, 50, 255);
	}


	else if (col == 'c') //yellow
	{
	    bCol = new Color (255, 240, 70);
	}


	else if (col == 'd') //orange
	{
	    bCol = new Color (255, 165, 50);
	}


	else if (col == 'e') //green
	{
	    bCol = new Color (50, 255, 50);
	}


	else if (col == 'f') //purple
	{
	    bCol = new Color (240, 50, 240);
	}


	if (d == '1')
	{
	    c.setColor (Color.WHITE);
	    c.fillRect (0, 0, 640, 500);
	    for (int i = 0 ; i < 13 ; i++)
	    {
		if (i % 2 == 0)
		{
		    c.setColor (bCol);
		    c.fillRect (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
		else
		{
		    c.setColor (Color.white);
		    c.fillRect (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
	    }
	}


	else if (d == '2')
	{
	    c.setColor (Color.white);
	    c.fillRect (0, 0, 640, 500);
	    for (int i = 0 ; i < 25 ; i++)
	    {
		for (int j = 0 ; j < 25 ; j++)
		{
		    if ((j + i) % 2 == 0)
		    {
			c.setColor (bCol);
			c.fillRect (i * 20, j * 20, 20, 20);
		    }
		}
	    }
	}


	else if (d == '3')
	{
	    c.setColor (Color.WHITE);
	    c.fillRect (0, 0, 640, 500);
	    for (int i = 0 ; i < 13 ; i++)
	    {
		if (i % 2 == 0)
		{
		    c.setColor (bCol);
		    c.fillOval (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
		else
		{
		    c.setColor (Color.white);
		    c.fillOval (i * 20, i * 20, 500 - i * 40, 500 - i * 40);
		}
	    }
	}
    }


    /*
	Global Variable Dictionary

	type                    name of var     explanation
       |----------------------------------------------------------------------------------------|
       |HungrySnakeDirection    hsd             Declares the HungrySnakeDirection class.        |
       |----------------------------------------------------------------------------------------|
       |char                    nDirection      Used to keep track of the previous              |
       |                                        direction.                                      |
       |----------------------------------------------------------------------------------------|

    */
    public void moveSnake ()  //determines snake direction and amount of time given for a direction input
    { //if the direction is opposite, snake will not move in the opposite direction
	//changes direction and actually doesn't move the snake
	HungrySnakeDirection hsd;
	char nDirection = direction;
	hsd = new HungrySnakeDirection (c);
	hsd.start ();
	try
	{
	    if (difficulty == '1')
	    {
		Thread.sleep (150);
	    }
	    else if (difficulty == '2')
	    {
		Thread.sleep (100);
	    }
	    else if (difficulty == '3')
	    {
		Thread.sleep (50);
	    }

	    nDirection = hsd.giveDirection ();
	}


	catch (Exception e)
	{

	}
	hsd.stop ();
	if ((direction + "").equalsIgnoreCase ("w"))
	{ //checks to see if the character entered is the opposite character
	    if ((nDirection + "").equalsIgnoreCase ("s"))
	    { //opposite
	    }
	    else if ((nDirection + "").equalsIgnoreCase ("a") || (nDirection + "").equalsIgnoreCase ("d"))
	    { //set to direction
		direction = nDirection;
	    }
	}


	else if ((direction + "").equalsIgnoreCase ("a"))
	{
	    if ((nDirection + "").equalsIgnoreCase ("d"))
	    { //opposite
	    }
	    else if ((nDirection + "").equalsIgnoreCase ("w") || (nDirection + "").equalsIgnoreCase ("s"))
	    { //set to direction
		direction = nDirection;
	    }
	}


	else if ((direction + "").equalsIgnoreCase ("s"))
	{
	    if ((nDirection + "").equalsIgnoreCase ("w"))
	    { //opposite
	    }
	    else if ((nDirection + "").equalsIgnoreCase ("a") || (nDirection + "").equalsIgnoreCase ("d"))
	    { //set to direction
		direction = nDirection;
	    }
	}


	else if ((direction + "").equalsIgnoreCase ("d"))
	{
	    if ((nDirection + "").equalsIgnoreCase ("a"))
	    { //opposite
	    }
	    else if ((nDirection + "").equalsIgnoreCase ("w") || (nDirection + "").equalsIgnoreCase ("s"))
	    { //set to direction
		direction = nDirection;
	    }
	}


	tailComms += direction;


    }


    /*
	Global Variable Dictionary

	type            name of var     explanation
       |------------------------------------------------------------------------|
       |int[]           aRet            Used to store coordinates of the food.  |
       |------------------------------------------------------------------------|
    */
    public int[] showFood (int[] ar, boolean fe)  //determines a random locaation for the food
    { //gives back two numbers, x and y coord of the board
	int[] aRet = ar;
	if (fe == false)
	{
	    int x;
	    int y;
	    while (true)
	    {
		y = (int) (Math.random () * 24);
		x = (int) (Math.random () * 24);
		if (location [y] [x] == 0)
		{
		    aRet [0] = y;
		    aRet [1] = x;
		    break;
		}
	    }
	}
	return aRet;
    }


    /*
	Global Variable Dictionary

	type            name of var     explanation
       |------------------------------------------------------------------------|
       |boolean         isIt            Used to see if the snake has hit the    |
       |                                wall or itself                          |
       |------------------------------------------------------------------------|
    */
    public boolean isHit (int[] he, int[] ta)  //uses head, and tail locations
	//checks weather the snake is hit
    {
	boolean isIt = false;
	try
	{
	    if ((direction + "").equalsIgnoreCase ("w"))
	    {
		if (location [he [0] - 1] [he [1]] == 1)
		{ //&& (he[0]-1)!=ta[0] && he[1]!=ta[1]){
		    isIt = true;
		}
	    }
	    else if ((direction + "").equalsIgnoreCase ("a"))
	    {
		if (location [he [0]] [he [1] - 1] == 1)
		{ // && he [0]!=ta[0] && (he [1] - 1)!=ta[1]){
		    isIt = true;
		}
	    }
	    else if ((direction + "").equalsIgnoreCase ("s"))
	    {
		if (location [he [0] + 1] [he [1]] == 1)
		{ // && (he [0] + 1)!=ta[0] && he [1]!=ta[1]){
		    isIt = true;
		}
	    }
	    else if ((direction + "").equalsIgnoreCase ("d"))
	    {
		if (location [he [0]] [he [1] + 1] == 1)
		{ // && he [0]!=ta[0] && (he [1] + 1)!=ta[1]){
		    isIt = true;
		}
	    }
	}


	catch (ArrayIndexOutOfBoundsException exception)
	{
	    isIt = true;
	}


	return isIt;
    }


    public void loseGame ()  //displays a lose game screen
    {
	c.setColor (Color.CYAN);
	c.setTextBackgroundColor (Color.cyan);
	c.fillRect (0, 0, 640, 500);
	c.println ("You lose!!");
	c.println ("Oh no this is so sad!!");
	c.println (name + ", Your score is " + score);
	c.println ("Press any key to continue...");
	pauseProgram ();
	pauseProgram ();
	//String ccc = c.readLine ();
    }


    /*
    Global Variable Dictionary

    type            name of var     explanation
    |------------------------------------------------------------------------|
    |PrintWriter    output          Used to write to the file.              |
    |------------------------------------------------------------------------|
    |String         fileName        Used for storing the file name          |
    |------------------------------------------------------------------------|
    |String[][]     sco             Used for storing file info.             |
    |------------------------------------------------------------------------|
    |int[]          iSco            Used for storing file scores.           |
    |------------------------------------------------------------------------|
    |String         line            Used for storing lines of the file      |
    |------------------------------------------------------------------------|
    |String[]       tmp             Used for storing previous file info     |
    |------------------------------------------------------------------------|
    |BufferedReader input           Used for reading the file.              |
    |------------------------------------------------------------------------|
    |File           myText          Used for seeing if the file exists.     |
    |------------------------------------------------------------------------|
    |int            tx              Used for keeping track of the num of loops|
    |------------------------------------------------------------------------|

    */
    public void storeScore ()  //stores scores
    {
	PrintWriter output;
	String fileName = "HighScores";
	String[] [] sco = new String [10] [2];
	int[] iSco = new int [10];
	String line;
	String[] tmp = new String [2];
	BufferedReader input;
	File myText;
	int tX;
	try
	{

	    myText = new File (fileName);
	    if (!myText.exists ())
	    {
		output = new PrintWriter (new FileWriter (fileName));
		output.println (score + " " + name);
		output.close ();
	    }
	    else
	    {
		input = new BufferedReader (new FileReader (fileName));
		line = input.readLine ();
		int i = 0;
		while (line != null && i < 10)
		{
		    sco [i] = line.split (" ");
		    iSco [i] = Integer.parseInt (sco [i] [0]);
		    i++;
		    line = input.readLine ();
		}
		if (i == 10)
		{ //this means there are 10 top scores already
		    tX = 0;
		    Arrays.sort (iSco);
		    reverse (iSco);
		    for (int j = 0 ; j < 10 ; j++)
		    {
			for (int k = tX ; k < 10 ; k++)
			{
			    if (iSco [j] == Integer.parseInt (sco [k] [0]))
			    {
				tmp = sco [j];
				sco [j] = sco [k];
				sco [k] = sco [j];
				tX = 1;
				break;
			    }
			}
		    }
		    if (iSco [9] < score)
		    {
			iSco [9] = score;
			sco [9] [0] = score + "";
			sco [9] [1] = name;
		    }
		    Arrays.sort (iSco);
		    reverse (iSco);
		    for (int j = 0 ; j < 10 ; j++)
		    {
			for (int k = tX ; k < 10 ; k++)
			{
			    if (iSco [j] == Integer.parseInt (sco [k] [0]))
			    {
				tmp = sco [j];
				sco [j] = sco [k];
				sco [k] = tmp;
				tX = 1;
				break;
			    }
			}
		    }
		    output = new PrintWriter (new FileWriter (fileName));
		    i = 0;
		    line = sco [i] [0] + " " + sco [i] [1];
		    for (int p = 0 ; p < 10 ; p++)
		    {
			line = sco [p] [0] + " " + sco [p] [1];
			output.println (line);

		    }
		    output.close ();
		}
		else
		{ //there are less than 10 top scores
		    sco [i] [0] = "" + score;
		    sco [i] [1] = name;
		    iSco [i] = score;
		    tX = 0;
		    Arrays.sort (iSco);
		    reverse (iSco); //sorted from largest to least
		    for (int j = 0 ; j <= i ; j++)
		    {
			for (int k = tX ; k <= i ; k++)
			{
			    if (iSco [j] == Integer.parseInt (sco [k] [0]))
			    {
				tmp = sco [j];
				sco [j] = sco [k];
				sco [k] = tmp;
				tX++;
				break;
			    }
			}
		    }
		    output = new PrintWriter (new FileWriter (fileName));
		    i = 0;
		    line = sco [i] [0] + " " + sco [i] [1];
		    while (sco [i] [1] != null && line != null)
		    {
			output.println (line);
			if (i <= 9)
			{
			    i++;
			    line = sco [i] [0] + " " + sco [i] [1];
			}
			else
			{
			    line = null;
			}

		    }
		    output.close ();

		}
	    }
	}


	catch (Exception e)
	{
	}
    }


    public void reverse (int[] array)  //reverses an array to greatest to least
    {
	//reverse method obtained from:
	//https://stackoverflow.com/questions/20251762/sorting-an-int-array-from-highest-to-lowest
	if (array == null)
	{
	    return;
	}
	int i = 0;
	int j = array.length - 1;
	int tmp;
	while (j > i)
	{
	    tmp = array [j];
	    array [j] = array [i];
	    array [i] = tmp;
	    j--;
	    i++;
	}
    }


    public void winGame ()  //displays a wingame screen
    {
	c.setColor (Color.RED);
	c.fillRect (0, 0, 640, 500);
	c.println ("Yo DID YOU JUST WIN???!?!?!?!!??!?!?!?");
	c.println ("CONGRATULATIONS, YOU'VE WON!!!");
	c.println ("Your score is " + score);
	c.println ("Press any key to continue...");
    }


    public void pauseProgram ()  //requires a character input before continuing
    {
	c.getChar ();
    }


    public static void main (String[] args)  //code is executed here
    {
	HungrySnake hs = new HungrySnake ();
	hs.splashScreen ();
	while (true)
	{
	    hs.mainMenu ();
	    if (hs.mInput == '1')
	    {
		hs.enterName ();
		hs.newGame ();
	    }
	    else if (hs.mInput == '2')
	    {
		hs.instructions ();
	    }
	    else if (hs.mInput == '3')
	    {
		hs.levelSelection ();
	    }
	    else if (hs.mInput == '4')
	    {
		hs.showHighscores ();
	    }
	    else if (hs.mInput == '5')
	    {
		hs.goodbye ();
		break;
	    }
	}


	//hs.newGame ();


    }
} // HungrySnake class
