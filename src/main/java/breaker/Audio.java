package breaker;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio{
	public static void b2b(){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("b2b.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
		System.out.println("Error with playing sound.");
		e.printStackTrace();
		}
	}

	public static void b2p() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("b2p.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
		System.out.println("Error with playing sound.");
		e.printStackTrace();
		}
	}

	public static void win() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("win.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
		System.out.println("Error with playing sound.");
		e.printStackTrace();
		}
	}
	public static void lost() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("lost.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
		System.out.println("Error with playing sound.");
		e.printStackTrace();
		}
	}
	public static void levelComplete() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("levelcomplete.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
		System.out.println("Error with playing sound.");
		e.printStackTrace();
		}
	}
	public static void powerup() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("powerup.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
		System.out.println("Error with playing sound.");
		e.printStackTrace();
		}
	}
}
