import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackGroundMusic implements Runnable {
    @Override
    public void run() {
        AudioInputStream stream = null;
        try {
            stream = AudioSystem.getAudioInputStream(new File("resources/Dreams.wav"));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert clip != null;
            clip.open(stream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
