// Small Triangle
// Jason Michael, Uriah Newkirk, Idorenyin Inyang - GEEN165 - 4/17/15
import java.awt.Color;
public class SmallTriangle extends Shapes{
	public static int x, y; // necessary integers for representing the coordinate where they'll be aligned
	
	public SmallTriangle(int a, int b){
		super("Small Triangle"); // Passing the shape 'Small Triangle' through the parent class constructor
		x = a; // determines x-coordinate positioning
		y = b; // determines y-coordinate positioning
	}
	
	public void draw(java.awt.Graphics g, int x, int y){ // this method sets the color and the position of the original shape
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 60, 60);
		g.setColor(Color.YELLOW);
		int a = x + 20, b = x + 40, c = x + 40;
		int[] x1 = {a, b, c};
		a = y + 30;
		b = y + 20;
		c = y + 40;
	    int[] y1 = {a, b, c};
	    g.fillPolygon(x1, y1, 3);	
	}
	
	public void update(java.awt.Graphics g, int x, int y){ // this method sets the color and the position of the shape after being dragged to a similar shape
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 60, 60);
		g.setColor(Color.YELLOW);
		int a = x + 20, b = x + 40, c = x + 40;
		int[] x1 = {a, b, c};
		a = y + 30;
		b = y + 20;
		c = y + 40;
	    int[] y1 = {a, b, c};
	    g.fillPolygon(x1, y1, 3);
	}
}
