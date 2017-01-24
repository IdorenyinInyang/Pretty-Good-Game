// Plus
// Jason Michael, Uriah Newkirk, Idorenyin Inyang - GEEN165 - 4/17/15
import java.awt.Color;
public class Plus extends Shapes{
	public static int x, y; // necessary integers for representing the coordinate where they'll be aligned
	
	public Plus(int a, int b){
		super("Plus"); // Passing the shape 'Plus' through the parent class constructor
		x = a; // determines x-coordinate positioning
		y = b; // determines y-coordinate positioning
	}
	
	public void draw(java.awt.Graphics g, int x, int y){ // this method sets the color and the position of the original shape
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 60, 60);	
		g.setColor(Color.YELLOW);
		g.fillRect(x + 24, y + 10, 12, 40);
		g.fillRect(x + 10, y + 24, 40, 12);
	}
	
	public void update(java.awt.Graphics g, int x, int y){ // this method sets the color and the position of the shape after being dragged to a similar shape
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 60, 60);	
		g.setColor(Color.YELLOW);
		g.fillRect(x + 24, y + 10, 12, 40);
		g.fillRect(x + 10, y + 24, 40, 12);
	}
}
