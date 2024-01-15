//Name - Mustafa Majeed
//Date - 12/12/2023
//Paddle 2 Class
import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;

public class Playable2 extends Rectangle {
  //variable declarations
  public int yVelocity;
  public final int SPEED = 15; //movement speed of paddle
  public static final int SQUARE_LENGTH = 60; //paddle dimensions
  public static final int SQUARE_WIDTH = 120;
  private Image tennisPlayer2;

  //constructor creates paddle at given location with given dimensions
  public Playable2(int x, int y) {
    super(x, y, SQUARE_LENGTH, SQUARE_WIDTH);
    ImageIcon icon = new ImageIcon("player2.png"); //we use our second image for our folder
    tennisPlayer2 = icon.getImage(); //make the image variable equal to that image
    yVelocity = 0; //give null value
  }

  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_UP) { //exact same of paddle 1 method
      setYDirection(SPEED * -1); //if we click w, it goes up, if we clicks s it goes down
      move();
    }

    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      setYDirection(SPEED);
      move();
    }
  }

  public void keyReleased(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_UP) { //we stop moving when key is released
      setYDirection(0);
      move();
    }

    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      setYDirection(0);
      move();
    }
  }

  public void setYDirection(int yDirection) {
    yVelocity = yDirection;
  }

  public void move() {
    y = y + yVelocity; //movement of paddle is controlled here
  }

  //called frequently from the GamePanel class
  //draws the current location of the ball to the screen
  public void draw(Graphics g) {
    g.setColor(Color.white);
    g.drawImage(tennisPlayer2, x, y, SQUARE_LENGTH, SQUARE_WIDTH, null); //we draw image and that is our second paddle
  }

}