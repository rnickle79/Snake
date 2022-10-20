import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class BackGroundMusic implements Runnable {
    @Override
    public void run() {
        try {
            URL musicURL = getClass().getResource("Dreams.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(musicURL);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
