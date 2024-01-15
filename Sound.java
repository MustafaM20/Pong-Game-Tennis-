//Name - Mustafa Majeed
//Sound Method
import java.io.File; //do all our imports
import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {
	
	static File ballHitAudio = new File("tennisBallHit.wav"); //declare both of our sound effect files
	static File scoreAudio = new File("gameScoreSound.wav");
	
	public static void playBounceAudio() throws LineUnavailableException, IOException, UnsupportedAudioFileException { //when this method is called it plays the 1st sound file
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(ballHitAudio);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	}
	
	public static void playScoreSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException { //when this method is called it plays the 2nd sound file
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(scoreAudio);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	}
	
	
	
}