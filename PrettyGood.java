// Pretty Good Game Project
// Jason Michael, Uriah Newkirk, Idorenyin Inyang - GEEN165 - 4/17/15
import java.util.*; 
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;

public class PrettyGood extends JFrame implements ActionListener{
	Container pane;
	JPanel panel;
	Timer timer;   // Does the time, pretty obvious
	Timer checker;  // checks the board every 15 milliseconds to see if the board has changed
	JButton button = new JButton("Start");
	JLabel timeLabel = new JLabel("Time Remaining");
	JLabel timeLeft = new JLabel("60");
	Random rand = new Random();
	ArrayList<String> pieces;
	Shapes[][] pile = new Shapes[2][2];  // Pile board
	Shapes[][] board = new Shapes[5][5];  // The actual game board
	JLabel[][] pileLabels = new JLabel[2][2];  // The labels to represent the pile
	JLabel[][] boardLabels = new JLabel[5][5]; // The labels to represent the board
	JLabel status = new JLabel(""); // Will tell the status of the player's wins/losses
	MouseListener mouse;  // MouseListener object
	
	public PrettyGood(){
		pane = getContentPane();
		pane.setLayout(null);
		pane.setPreferredSize(new Dimension(340, 480)); // Setting the dimensions of the application frame
		
		timeLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		pane.add(timeLabel);
		timeLabel.setBounds(53, 10, 115, 20);
		
		timeLeft.setFont(new Font("Dialog", Font.PLAIN, 20));
		pane.add(timeLeft);
		timeLeft.setBounds(98, 50, 50, 30);
		
		button.addActionListener(this);
		pane.add(button);
		button.setBounds(45, 100, 125, 25);		
		
		pane.add(status);
		
		mouse = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent jc = (JComponent)e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);  
                
                // Basically, allowing to drag and drop components via copying
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}       
        };
      
        setTitle("Pretty Good");  // Setting the title of the application window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               // Windows closes when red x is pressed
	    pack();                                                       // Auto window size
	    setResizable(false);                                          // Window is not resizable
	    setVisible(true);                                            // Window is visible              
	} 
	
	boolean started = false; // Whether or not to start the game
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == button && !started){
			start();  // When the button is clicked and it is not started, the timer will start
		}else if(event.getSource() == timer && started){
			updateTime(); // restart time
		}else if(event.getSource() == checker){
			check(); //check every 50 milliseconds on the program 
		}else if(event.getSource() == button && started){ //stop the time
			timer.stop();
			checker.stop();
			button.setText("Start");
			timeLeft.setText("60");
			currentTime = 60;
			started = false;
			
			for(int i = 0; i < 2; i++){
				for(int j = 0; j < 2; j++){
					Container parent = pileLabels[i][j].getParent();
					parent.remove(pileLabels[i][j]);
					parent.validate();
				}
			}
			
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					Container parent = boardLabels[i][j].getParent();
					parent.remove(boardLabels[i][j]);
					parent.validate();
				}
			}
		}
	} 
	
	public void drawBoard(){ // method for the layout of the board
		pieces = new java.util.ArrayList<String>();
		int x, y = 160;
		for(int i = 0; i < 5; i++){
			x = 15;
			for(int j = 0; j < 5; j++){
				int num = rand.nextInt(6) + 1; // This will determine the random shapes on the board
				switch(num){
				case 1:
					board[i][j] = new Square(x, y);
					pieces.add(board[i][j].getShape()); break;
				case 2:
					board[i][j] = new Triangle(x, y);
					pieces.add(board[i][j].getShape()); break;
				case 3:
					board[i][j] = new Plus(x, y);
					pieces.add(board[i][j].getShape()); break;
				case 4:
					board[i][j] = new SmallTriangle(x, y);
					pieces.add(board[i][j].getShape()); break;
				case 5:
					board[i][j] = new LShape(x, y);
					pieces.add(board[i][j].getShape()); break;
				case 6:
					board[i][j] = new Circle(x, y);
					pieces.add(board[i][j].getShape()); break;
				}
				boardLabels[i][j] = new JLabel();
				boardLabels[i][j].setBounds(x, y, 60, 60);
				boardLabels[i][j].setIcon(new ImageIcon(board[i][j].getShape()));
				boardLabels[i][j].addMouseListener(mouse);
    		    boardLabels[i][j].setTransferHandler(new TransferHandler("icon"));
				pane.add(boardLabels[i][j]);
				x = x + 65; // x-coordinate positioning
			}
			y = y + 65;  // y-coordinate positioning
		}	
		java.util.Collections.shuffle(pieces); // Shuffling 'dem pieces
		
		y = 10;
		int index = 0;
		for(int i = 0; i < 2; i++){
			x = 210;
			for(int j = 0; j < 2; j++){
				pileLabels[i][j] = new JLabel();
				pileLabels[i][j].setBounds(x, y, 60, 60);	
				pileLabels[i][j].setIcon(new ImageIcon(pieces.get(index)+" Piece "+index)); // labels are set as icons
				pileLabels[i][j].addMouseListener(mouse); // MouseListener implemented on the piece JLabels
    		    pileLabels[i][j].setTransferHandler(new TransferHandler("icon")); 
				pane.add(pileLabels[i][j]); // added on the GUI
				index++;
				x = x + 65; // re-positioning x-coordinate
			}
			y = y + 65; // re-positioning y-coordinate
		}
		
		for(int i = 0; i < 4; i++){
			pieces.remove(0);
		}
	}
	
	public boolean first; // boolean variable that will confirm everything on the board is true
	public boolean[][] added; // boolean array that determines on the array of the pieces that were added	
	public String[][] old; // String array that determines which pieces are old
	
	public void check(){
		if(!first){
			boolean all = true;
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					if (added[i][j] == false){
						all = false;
					}
				}
			}
			if(all){ // when all the pieces are true, you win
				win();
			}
		}
			
		if(first){ // when this is true...
			added = new boolean[5][5];
			old = new String[5][5];
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					old[i][j] = ((ImageIcon)boardLabels[i][j].getIcon()).toString(); //placing icons with the String array
				}
			}
			first = false; // now this is false...so...
		}else{ //now pieces can be identified on the board
			String s = "";
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					s = ((ImageIcon)boardLabels[i][j].getIcon()).toString();					
					if(!old[i][j].equals(s)){
						if(s.contains(old[i][j])){
							old[i][j] = s;
							added[i][j] = true;
							if(pieces.size() > 0){
								String pilePiece = "";
								int index = 0;
								for(int a = 0; a < 2; a++){
									for(int b = 0; b < 2; b++){
										pilePiece = ((ImageIcon)pileLabels[a][b].getIcon()).toString();
										if(s.equals(pilePiece)){
											pileLabels[a][b].setIcon(new ImageIcon(pieces.get(0)+" Piece "+index));
											pane.add(pileLabels[a][b]);
											pieces.remove(0);	
										}
										index++;
									}									
								}
							}
							
			            }else{
			                boardLabels[i][j].setIcon(new ImageIcon(old[i][j]));
			                pane.add(boardLabels[i][j]);
			            }
			         }	
				}
			}
		}	
		paintBoard();
	}
	
	int currentTime = 60;
	public void updateTime(){
		timeLeft.setText(String.valueOf(currentTime--)); // decrement time
		if(currentTime <= -1){
			timer.stop(); // stop the timer
			checker.stop(); // stop checking the board
			button.setText("Start"); // change text of button to "Start" to play again
			currentTime = 60; // initialize the time back to 60
			started = false;
			
			for(int i = 0; i < 2; i++){
				for(int j = 0; j < 2; j++){
					Container parent = pileLabels[i][j].getParent();
					parent.remove(pileLabels[i][j]);
					parent.validate();
				}
			}
			
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					Container parent = boardLabels[i][j].getParent();
					parent.remove(boardLabels[i][j]);
					parent.validate();
				}
			}
			status.setBounds(72, 128, 100, 25);
			status.setForeground(java.awt.Color.RED);
			status.setText("GAME OVER");
			button.setText("Play Again");
		}		
	}
	
	public void start(){  // Method for the game to start
		status.setText("");
		timeLeft.setText("60");
		button.setText("Reset"); // set the button to "Reset" in case player wants to reset the game
		drawBoard(); // place shape objects on the board
		first = true;
		timer = new Timer(1000, this);
		timer.start(); // timer is activated
		checker = new Timer(50, this);
		checker.start();  // and so is the checker
		started = true;
	}
		
	public void paintBoard(){
		Graphics g = pane.getGraphics();		
		int x, y = 160;
		for(int i = 0; i < 5; i++){
			x = 15;
			for(int j = 0; j < 5; j++){
				if(added[i][j]){
					board[i][j].update(g, x, y); // painting the game board with the shapes necessary
				}else{
					board[i][j].draw(g, x, y); // otherwise, just put in pieces
				}
				x = x + 65; // x-coordinate positioning 
			}
			y = y + 65; // y-coordinate positioning
		}
		
		Shapes[][] s = new Shapes[2][2];  // 2Darray of Shape objects
		String piece = "";
		y = 10;
		for(int i = 0; i < 2; i++){
			x = 210;
			for(int j = 0; j < 2; j++){	 // Setting specific pieces from the string to the Shape object array
				piece = ((ImageIcon)pileLabels[i][j].getIcon()).toString();
				int length = piece.length();
				piece = piece.substring(0, length - 2);
				switch(piece){
				case "Triangle Piece":
					s[i][j] = new Triangle(x, y); 
					s[i][j].update(g, x, y); break;
				case "Square Piece":
					s[i][j] = new Square(x, y);
					s[i][j].update(g, x, y); break;
				case "Plus Piece":
					s[i][j] = new Plus(x, y);
					s[i][j].update(g, x, y); break;
				case "Small Triangle Piece":
					s[i][j] = new SmallTriangle(x, y);
					s[i][j].update(g, x, y); break;
				case "LShape Piece":
					s[i][j] = new LShape(x, y); 
					s[i][j].update(g, x, y); break;
				case "Circle Piece":
					s[i][j] = new Circle(x, y);
					s[i][j].update(g, x, y); break;
				}		
				x = x + 65; //
			}

			y = y + 65;
		}			
	}
	
	public void win(){ // basically the win method
		timer.stop();  // time stops
		checker.stop(); // checker stops
		button.setText("Start"); // Button text is reset to "Start"
		timeLeft.setText("60"); // Time is placed back at 60
		currentTime = 60; // so is the current time
		started = false; // started is placed back at false, meaning the game is finished
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				Container parent = pileLabels[i][j].getParent();
				parent.remove(pileLabels[i][j]);
				parent.validate();
			}
		}
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				Container parent = boardLabels[i][j].getParent();
				parent.remove(boardLabels[i][j]);
				parent.validate();  // validating all the label components on the board
			}
		}
		
		status.setBounds(82, 128, 100, 25);
		status.setForeground(java.awt.Color.BLUE);
		status.setText("You win!");
		button.setText("Play Again");
	}
	
	public static void main(String[] args){
		PrettyGood pg = new PrettyGood();	// constructor to start the game	
	}
}