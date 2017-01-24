// Square
// Jason Michael, Uriah Newkirk, Idorenyin Inyang - GEEN165 - 4/17/15
import java.awt.Color;
public class Square extends Shapes{
	public static int x, y; // necessary integers for representing the coordinate where they'll be aligned 
	
	public Square(int a, int b){
		super("Square"); // Passing the shape 'Square' through the parent class constructor
		x = a; // determines x-coordinate positioning 
		y = b; // determines y-coordinate positioning 
	}
	
	public void draw(java.awt.Graphics g, int x, int y){ // this method sets the color and the position of the original shape
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 60, 60);	
		g.setColor(Color.YELLOW);
		g.fillRect(x + 15, y + 15, 30, 30);
	}
	
	public void update(java.awt.Graphics g, int x, int y){ // this method sets the color and the position of the shape after being dragged to a similar shape
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 60, 60);
		g.setColor(Color.YELLOW);
		g.fillRect(x + 15, y + 15, 30, 30);		
	}
}
