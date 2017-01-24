// Triangle
// Jason Michael, Uriah Newkirk, Idorenyin Inyang - GEEN165 - 4/17/15
import java.awt.Color;
public class Triangle extends Shapes{
	public static int x, y; // necessary integers for representing the coordinate where they'll be aligned
	
	public Triangle(int a, int b){
		super("Triangle"); // Passing the shape 'Triangle' through the parent class constructor
		x = a; // determines x-coordinate positioning
		y = b; // determines y-coordinate positioning
	}
	
	public void draw(java.awt.Graphics g, int x, int y){ // this method sets the color and the position of the original shape
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 60, 60);
		g.setColor(Color.YELLOW);
		int a = x + 10, b = x + 30, c = x + 50;
		int[] x1 = {a, b, c};
		a = y + 10;
		b = y + 50;
		c = y + 10;
	    int[] y1 = {a, b, c};
	    g.fillPolygon(x1, y1, 3);	
	}
	
	public void update(java.awt.Graphics g, int x, int y){ 
		// this method sets the color and the position of the shape after being dragged to a similar shape
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 60, 60);
		g.setColor(Color.YELLOW);
		int a = x + 10, b = x + 30, c = x + 50;
		int[] x1 = {a, b, c};
		a = y + 10;
		b = y + 50;
		c = y + 10;
	    int[] y1 = {a, b, c};
	    g.fillPolygon(x1, y1, 3);		
	}
}
