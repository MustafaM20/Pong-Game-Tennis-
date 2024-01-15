//Name - Mustafa Majeed
//Scorecard and court class
import java.awt.Color; //imports
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Score extends Rectangle {

  public int player1score; //variable declaration
  public int player2score;
  public boolean winCondition;

  Score(int p1score, int p2score) {
    player1score = p1score; //constructor class
    player2score = p2score;
    winCondition = false; //the win Condition is false right now
  }

  // draws the score and line down the middle
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g; //we use 2d graphics to make thicker lines
    g2.setStroke(new BasicStroke(5)); //we increase thickness of lines
    g.setColor(Color.white);

    g.setFont(new Font("Arial", Font.PLAIN, 16)); //we set the font for the score
    g.drawString("Player 1 Controls: W to go Up. S to go down", 100, 770);
    g.drawString("Player 2 Controls: Up Arrow to go Up. Down Arrow to go down.", 1000, 770);
    
    g.setFont(new Font("Arial", Font.BOLD, 25)); 
    g.drawString("First to 5 points wins!", 685, 30);
    
    g2.drawLine(800, 0, 800, 800); //we draw the design of the court
    g2.drawLine(0, 200, 1600, 200);
    g2.drawLine(0, 600, 1600, 600);
    g2.drawLine(400, 200, 400, 600);
    g2.drawLine(1200, 200, 1200, 600);
    
    g.setFont(new Font("Arial", Font.PLAIN, 45)); //we set the font for the score
    g.drawString(String.valueOf(player1score), 400, 50); //we write the score over each paddle
    g.drawString(String.valueOf(player2score), 1200, 50);

    //win screen
    if (player1score == 5) { //if one player has a score of 5
      g.setColor(Color.white); //we make a box that text is written on
      g.fillRect(500, 175, 600, 300);
      g.setColor(Color.black);
      g.drawString("Player 1 Wins!", 650, 400); //the players are told who won
      winCondition = true; //the code finally stops running because win condition is met
    }

    if (player2score == 5) { //same exact concept as the if statement above
      g.setColor(Color.white);
      g.fillRect(500, 175, 600, 300);
      g.setColor(Color.black);
      g.drawString("Player 2 Wins", 650, 400);
      winCondition = true;
    }

  }
}