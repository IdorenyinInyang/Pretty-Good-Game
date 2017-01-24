// Parent class for shapes
// Jason Michael, Uriah Newkirk, Idorenyin Inyang - GEEN165 - 4/17/15
public abstract class Shapes{  
	private String shapeType = ""; 
	public abstract void draw(java.awt.Graphics g, int x, int y); // abstract method for drawing the initial shapes
	public abstract void update(java.awt.Graphics g, int x, int y); // abstract method for the shapes after being compared
	
	public Shapes(String s){ // constructor to receive the String name of the shape
		shapeType = s;
	}
	
	public String getShape(){ // return the shape type
		return shapeType;
	}
	
	public void pilePiece(boolean a){ // identifying the shape pieces
		if(a){
			shapeType = shapeType+" Piece";
		}
	}
}
