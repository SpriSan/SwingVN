package screens.components;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class SoundManager {

    public static void PlaySound(InputStream name)
    {
        try (AudioInputStream audio = AudioSystem.getAudioInputStream(name)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException |LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
