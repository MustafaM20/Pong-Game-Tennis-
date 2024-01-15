//Name - Mustafa Majeed
//Paddle 1 - Method
import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;

public class Playable1 extends Rectangle {

  //declare all our variables
  public int yVelocity;
  public final int SPEED = 15; //movement speed of paddle
  public static final int SQUARE_LENGTH = 60; //dimensions of rectangle
  public static final int SQUARE_WIDTH = 120; //The player models are kept skinny to stop glitches from happening between the paddle and ball
  private Image tennisPlayer1; //image variable

  //constructor which creates our paddle
  public Playable1(int x, int y) {
    super(x, y, SQUARE_LENGTH, SQUARE_WIDTH);
    ImageIcon icon = new ImageIcon("player1.png"); //we add an image which is in our code folder
    tennisPlayer1 = icon.getImage(); //we make the variable tennisPlayer1 equal to this image
    yVelocity = 0; //giving null values to our velocity
  }

  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_W) { //if w is clicked you go upwards
      setYDirection(SPEED * -1); //essentially make our velocity negative, which then subtracts for y -coord to make us go up
      move();
    }

    if (e.getKeyCode() == KeyEvent.VK_S) { //if s is clicked you go downwards
      setYDirection(SPEED); //we just add the speed(velocity) to the already established y coord
      move();
    }
  }

  public void keyReleased(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_W) { //we stop the paddle when the key is released
      setYDirection(0);
      move();
    }

    if (e.getKeyCode() == KeyEvent.VK_S) { //stop the paddle when key is released
      setYDirection(0);
      move();
    }
  }

  //called from GamePanel when any keyboard input is detected

  //called whenever the movement of the paddle changes in the y-direction (up/down)
  public void setYDirection(int yDirection) {
    yVelocity = yDirection;
  }

  public void move() { //the paddle is moved
    y = y + yVelocity;
  }

  public void draw(Graphics g) {
    g.setColor(Color.white); //we draw the tennis player image and give it the same values as a normal paddle
    g.drawImage(tennisPlayer1, x, y, SQUARE_LENGTH, SQUARE_WIDTH, null);

  }

}