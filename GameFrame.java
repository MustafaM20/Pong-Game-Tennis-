//Name - Mustafa Majeed
//Game Frame and Setup class
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

  GamePanel panel;
  Color tennisCourt = new Color(0,102,0);//make a custom color which matches tennis court

  public GameFrame(){
    panel = new GamePanel(); //run GamePanel constructor
    this.add(panel);
    this.setTitle("Pong Game (Tennis Version)!"); //set title for frame
    this.setResizable(false); //frame can't change size
    this.setBackground(tennisCourt); //set the backgorund to the color
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button will stop program execution
    this.pack();//makes components fit in window - don't need to set JFrame size, as it will adjust accordingly
    this.setVisible(true); //makes window visible to user
    this.setLocationRelativeTo(null);//set window in middle of screen
  }
  
}