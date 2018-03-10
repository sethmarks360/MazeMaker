import java.awt.Point;
import java.util.Random;

public class MazeHolder
{
	//Instance variables necessary to Maze construction
	private char[][] theMaze;
	private int rows;
	private int columns;
	
	Random generator = new Random();
	//Constructor which creates the Maze using the user's command line arguments
	public MazeHolder(int rows, int columns)
	{
		this.rows = rows; //set's object's rows to be the one entered in parameter
		this.columns = columns; //set's object's columns to be the one entered in parameter
		
		theMaze = new char[rows*2 + 1][columns*2 + 1]; //allocate new array of 2*paramter + 1 size
		
		for (int r = 0; r < rows*2 + 1; r++)
		{
			for (int c = 0; c < columns*2 + 1; c++)
			{
				theMaze[r][c] = '@'; //all characters are '@'s
			}
		}
	}
	//METHOD: firstCut()
	//Purpose: cut a random group of characters out of the Maze and push the end point
	//Returns: the end point of the random cut
	//Parameters: none
	public Point firstCut()
	{
		int x = generator.nextInt(this.rows); //Random row
		int y = generator.nextInt(this.columns); //Random column

		theMaze[x*2 + 1][y*2 + 1] = ' '; //Delete modified row and column index
		
		Point p = new Point (x, y); //End point to return
		
		return p;
	}
	//METHOD: get()
	//Purpose: retrieve the charcater at a given point in the maze
	//Returns: the desired character, either '#' or ' '
	//Parameters: row and column number
	public char get(double row, double column)
	{
		int r = (int) (row*2 + 1); 
		int c = (int) (column*2 + 1); 
		return theMaze[r][c];
	}
	//METHOD: delete()
	//Purpose: delete any character in the maze
	//Returns: nothing, but modifies object
	//Parameters: row and column number to delete
	public void delete(double r, double c)
	{
		theMaze[(int) r][(int) c] = ' ';
	}
	//METHOD: toString()
	//Purpose: turn the Maze into a String object so it can be displayed
	//Returns: a String that is the representation of the Maze
	//Parameters: none
	public String toString()
	{
		String mazeString = "";
		
		for (int r = 0; r < this.rows*2 + 1; r++)
		{
			for (int c = 0; c < this.columns*2 + 1; c++)
			{
				mazeString = mazeString + theMaze[r][c];
			}
			
			mazeString += "\n"; //add a new line after each line to give Maze a block shape
		}
		
		return mazeString;
	}
	
	//This method returns the current number of rows held by the object
	public int rows()
	{
		return this.rows;
	}
	//This method returns the current number of columns held by the object
	public int columns()
	{
		return this.columns;
	}
//End of Class	
}