//Name - Mustafa Majeed
//Tennis ball Class
import javax.swing.ImageIcon; //do all the imports
import java.awt.*;
import java.awt.event.*;

public class PlayerBall extends Rectangle {

  public int yVelocity = 3; //variable declaration
  public int xVelocity = 2;
  public static final int BALL_DIAMETER = 20;
  private Image tennisBall;

  public PlayerBall(int x, int y) { //constructor class
    super(x, y, BALL_DIAMETER, BALL_DIAMETER);
    ImageIcon icon = new ImageIcon("tennisImage.png"); //we make tennis ball variable equal to the tennis ball image from the folder
    tennisBall = icon.getImage();
  }

  public void move() {
    y = y + yVelocity; //the tennis ball movement is controlled by the velocities
    x = x + xVelocity;
  }

  public void draw(Graphics g) {
    g.setColor(Color.white);
    g.drawImage(tennisBall, x, y, BALL_DIAMETER, BALL_DIAMETER, null); //we draw the image which is now our tennis ball in game
  }

}