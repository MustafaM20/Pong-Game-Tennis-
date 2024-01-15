//Name - Mustafa Majeed
//Game Panel Class
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

  // dimensions of window
  public static final int GAME_WIDTH = 1600;
  public static final int GAME_HEIGHT = 800;
  public static final int SPAWNPOINT_X = 60;
  public static final int SPAWNPOINT_Y = 400;

  //initialize all our variables
  public Thread gameThread;
  public Image image;
  public Graphics graphics;
  public PlayerBall ball;
  public Playable1 square;
  public Playable2 square2;
  public int p1score = 0;
  public int p2score = 0;
  public Score score;

  public GamePanel() {
    ball = new PlayerBall(GAME_WIDTH / 2, GAME_HEIGHT / 2); // create a ball, which spawns in the middle of the court
    square = new Playable1(SPAWNPOINT_X - 30, SPAWNPOINT_Y); //creates first paddle
    square2 = new Playable2(SPAWNPOINT_X + 1440, SPAWNPOINT_Y); //creates second paddle
    score = new Score(p1score, p2score); //creates our score object

    this.setFocusable(true); // make everything in this class appear on the screen
    this.addKeyListener(this); // start listening for keyboard input

    this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

    gameThread = new Thread(this);
    gameThread.start();
  }

  public void paint(Graphics g) {
    // draw images OFF the screen, then simply move the image on screen as needed.
    image = createImage(GAME_WIDTH, GAME_HEIGHT); // draw off screen
    graphics = image.getGraphics();
    draw(graphics); // update the positions of everything on the screen
    g.drawImage(image, 0, 0, this); // move the image on the screen

  }

  // call the draw methods in each class to update positions as things move
  public void draw(Graphics g) {
    ball.draw(g); //update the positions of all the moving objects
    square.draw(g);
    square2.draw(g);
    score.player1score = p1score; //update the score of both players before drawing scoreboard
    score.player2score = p2score;
    score.draw(g); //draw the scoreboard and court designs
  }

  public void move() {
    ball.move(); //the move method makes everything move smoothly and to the right co-ordinates
    square.move();
    square2.move();
  }

  public void checkCollision() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
    //does all the collisoins between the ball and paddle and paddle and walls

    if (ball.y <= 0 || ball.y >= (800 - ball.BALL_DIAMETER)) { //if the ball hits the walls this will happen
      ball.yVelocity = -ball.yVelocity; //the ball will go in the exact opposite y value
      Sound.playBounceAudio(); //a sound method will be played
    }

    if (ball.getBounds().intersects(square.getBounds())) { //if the ball hits the 1st paddle. we use intersects which essentially creates the ball into a rectangle which then is compared
      ball.xVelocity = -ball.xVelocity; //the ball will go in opposite direction

      if (ball.xVelocity < 12) { //the ball will speed up if its not hit its maximum speed yet
        ball.xVelocity += 2;
      }

      if (ball.yVelocity < 20) { //the ball will travel in the y direction faster
        ball.yVelocity += 4;
      }

      Sound.playBounceAudio(); //a hit sound will be played

    }

    if (ball.getBounds().intersects(square2.getBounds())) { //essentially the same thing but for the other paddle
      ball.xVelocity = -ball.xVelocity;

      if (ball.xVelocity > -12) {
        ball.xVelocity -= 2;
      }

      if (ball.yVelocity < 20) {
        ball.yVelocity += 4;
      }

      Sound.playBounceAudio();
    }

    if (square.y <= 0) { //all these just stop the paddles from going over the edges
      square.y = 0;
    }

    if (square.y >= (GAME_HEIGHT - 120)) {
      square.y = GAME_HEIGHT - 120;
    }

    if (square2.y <= 0) {
      square2.y = 0;
    }

    if (square2.y >= (GAME_HEIGHT - 120)) {
      square2.y = GAME_HEIGHT - 120;
    }

  }

  public void resetBall() { //this resets the ball after someone scores
    ball.x = 300;
    ball.y = 250;
    ball.xVelocity = 2;
    ball.yVelocity = 3;
  }

  public void pointScoring() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException { //our point scoring method
    if (ball.x <= 0) { //if player 2 scores
      p2score += 1; //we add 1 point
      Sound.playScoreSound(); //we play the cheer sound effect 
      Thread.sleep(1000); //we let the code not run for 1 second the user is ready again
      resetBall(); //we then reset the ball speed and position
    }
    if (ball.x > 1600) { //we repeat the same thing again
      Sound.playScoreSound();
      p1score += 1;
      Thread.sleep(1000);
      resetBall();
    }
  }

  public void run() {
    // the CPU runs our game code too quickly - we need to slow it down! The
    // following lines of code "force" the computer to get stuck in a loop for short
    // intervals between calling other methods to update the screen.
    long lastTime = System.nanoTime();
    double amountOfTicks = 60;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long now;

    while (score.winCondition == false) {
      now = System.nanoTime();
      delta = delta + (now - lastTime) / ns;
      lastTime = now;

      // only move objects around and update screen if enough time has passed
      if (delta >= 1) {
        move(); //we run all the methods
        try {
          checkCollision(); //because we are using sound effects, we have to throw all these declarations just incase the files aren't found
        } catch (LineUnavailableException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        try {
          pointScoring();
        } catch (LineUnavailableException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        repaint();

        delta--;
      }
    }
  }

  //this is how we control the panels. we have a keypressed method which works when a key is pressed
  public void keyPressed(KeyEvent e) {
    square.keyPressed(e);
    square2.keyPressed(e);
  }

  //then we have a key released method which works when the key is released.
  public void keyReleased(KeyEvent e) {
    square.keyReleased(e);
    square2.keyReleased(e);
  }

  public void keyTyped(KeyEvent e) {

  }
}