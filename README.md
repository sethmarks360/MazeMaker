# MazeMaker
//Programmer: Seth M. Marks
//Section Leader: Jesse D. Bartels
//Instructor: Lester I. McCann
//Class: Main class Program6

//Necessary imports
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MazeMaker 
{
	//The Main Method
	public static void main(String[] args) throws IOException 
	{	
		//Gather data from the user. The user will enter the amount of rows and columns they want the maze to be
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Please enter the amount of rows you'd like this maze to be: ");
		int rows = keyboard.nextInt();
		
		System.out.print("Please enter the amount of columns you'd like this maze to be: ");
		int columns = keyboard.nextInt();
		
		keyboard.close();
		
		//Print out the numbers that the user entered
		System.out.println("You entered: " + rows + " rows and " + columns + " columns.");
		
		//Print out the numbers that are going to represent the actual maze
		System.out.println("This is viewed as a " + (rows * 2 + 1) + " by " + (columns * 2 + 1) + " maze.");
		
		
		MazeHolder theMaze = new MazeHolder(rows, columns); //constructs the maze using the MazeHolder class
		
		Stack theStack = new Stack(); //initialize a new stack 
		
		Point firstPoint = theMaze.firstCut();
		
		theStack.push(firstPoint); //add the end point of firstCut() to the top of the stack
		
		while(theStack.isNotEmpty()) //loop continues as long as stack has at least one object on it
		{
			
			ArrayList<String> ways = new ArrayList<String>(); //initialize ArrayList of possible directions
			String direction = "";
			
			if(theStack.peek().getX() - 1 != -1 && theMaze.get(theStack.peek().getX() - 1, theStack.peek().getY()) != ' ') //if able to go up, add up as an option
				ways.add("Up");
			if(theStack.peek().getX() + 1 != theMaze.rows() && theMaze.get(theStack.peek().getX() + 1, theStack.peek().getY()) != ' ') //if able to go down, add up as an option
				ways.add("Down");
			if(theStack.peek().getY() - 1 != -1 && theMaze.get(theStack.peek().getX(), theStack.peek().getY() - 1) != ' ') //if able to go left, add it as an option
				ways.add("Left");
			if(theStack.peek().getY() + 1 != theMaze.columns() && theMaze.get(theStack.peek().getX(), theStack.peek().getY() + 1) != ' ') //if able to go right, add it as an option
				ways.add("Right");
			
			if(ways.size() > 0) //as long as there is at least one possible direction to go
			{
				Random generator = new Random(); // make a new random number generator
				direction = ways.get(generator.nextInt(ways.size())); //find random index of ArrayList that is the size of number of possible directions
				
				//if statements which control where the maze will be cleared
				if(direction.equals("Up"))
				{
					Point point = new Point(); 
					point.setLocation(theStack.peek().getX() - 1, theStack.peek().getY());
					theMaze.delete(theStack.peek().getX()* 2, theStack.peek().getY()* 2 + 1);
					theMaze.delete(theStack.peek().getX()* 2 - 1, theStack.peek().getY()* 2 + 1);
					theStack.push(point);
					ways.clear();
				}
				
				else if(direction.equals("Down"))
				{
					Point point = new Point(); 
					point.setLocation(theStack.peek().getX() + 1, theStack.peek().getY());
					theMaze.delete(theStack.peek().getX()* 2 + 2, theStack.peek().getY()* 2 + 1);
					theMaze.delete(theStack.peek().getX()* 2 + 3, theStack.peek().getY()* 2 + 1);
					theStack.push(point);
					ways.clear();
				}
				else if(direction.equals("Left"))
				{
					Point point = new Point(); 
					point.setLocation(theStack.peek().getX(), theStack.peek().getY() - 1);
					theMaze.delete(theStack.peek().getX()* 2 + 1, theStack.peek().getY()* 2);
					theMaze.delete(theStack.peek().getX()* 2 + 1, theStack.peek().getY()* 2 - 1);
					theStack.push(point);
					ways.clear();
				}
				else if(direction.equals("Right"))
				{
					Point point = new Point(); 
					point.setLocation(theStack.peek().getX(), theStack.peek().getY() + 1);
					theMaze.delete(theStack.peek().getX()* 2 + 1, theStack.peek().getY()* 2 + 2);
					theMaze.delete(theStack.peek().getX()* 2 + 1, theStack.peek().getY()* 2 + 3);
					theStack.push(point);	
					ways.clear();
				}
			}
				
				else
				{
					theStack.pop(); //if there is no available direction to go, remove the top location from the stack
				}
			
		}
		
		Random maker = new Random(); //another random number generator
		
		int n = 0; //n, a variable to control the while loops
		
		while (n == 0) //loop will continue until an appropriate spot is found to make an entrance
		{
			int rowToDelete = maker.nextInt(theMaze.rows());
			
			if (theMaze.get(rowToDelete, 0) != '@' && rowToDelete != 0)
			{
				theMaze.delete(rowToDelete * 2 + 1, 0);
				n = 1; //make n equal to 1 to escape the while loop
			}
		}
		
		n = 0; //set n back to 0, so another while loop can start
		
		while (n == 0) //loop will continue until an appropriate spot is found to make an exit
		{
			int place = maker.nextInt(theMaze.rows()); //find a row to make the maze exit
			
			if(theMaze.get(place, theMaze.columns() - 1) != '@' && place != 0)
			{
				theMaze.delete(place * 2 + 1, theMaze.columns() * 2);
				n = 1; //set while condition to a different number to terminate the loop
			}
		}
		//use the method that displays the maze as a String
		System.out.println(theMaze.toString());
		
		
	}

}

