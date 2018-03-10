//Programmer: Seth M. Marks
//Section Leader: Jesse D. Bartels
//Instructor: Lester I. McCann
//Class: Stack, Program6
//This class is designed to keep track of the last point looked at on the Stack. 
//Points can be added, removed, or the top Point can be inspected

//necessary imports
import java.awt.Point;
import java.util.ArrayList;

public class Stack 
{
	//The Stack will be represented using an ArrayList composed of Point objects
	private ArrayList<Point> stack;
	int height = 0; //Stack starts off with nothing on it 

	//Constructor to make Stack
	public Stack()
	{
		stack = new ArrayList<Point>();
	}
	
	//Reveals the Point that is currently on top of Stack
	public Point peek()
	{
		return stack.get(height-1);
		
	}
	
	//Adds point to the top of the Stack and increments height var by 1
	public void push(Point p)
	{
		stack.add(p);
		height++;
	}
	
	//Removes the point at the top of the Stack and decrements the height var by 1
	public void pop() 
	{
		height--;
		stack.remove(height);
		
	}
	//Returns true if the Stack is not empty, false if it is empty
	public boolean isNotEmpty()
	{
		return stack.size() != 0;
	}
}