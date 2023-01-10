/*
Ryan Wang
Mrs. Krasteva
December 26, 2019

Asked Ben Zeng for help to get a char within a time limit and continue
the program if user hasn't entered a char within a certain time limit.
*/

import java.awt.*;
import hsa.Console;
import java.lang.*;

public class HungrySnakeDirection extends Thread
{
    Console c;


    public char enter;
    public HungrySnakeDirection (Console con)
    {
	c = con;
    }


    public void data ()
    {
	while(true){
	    enter = c.getChar ();
	    if(enter=='w' || enter=='a' || enter=='s' || enter=='d' || enter=='W' || enter=='A' || enter=='S' || enter=='D'){
		//c.println(enter);
		break;
	    }
	}

    }


    public void run ()
    {
	data ();
    }
    public char giveDirection(){
	return enter;
    }
} // HungrySnakeDirection class
