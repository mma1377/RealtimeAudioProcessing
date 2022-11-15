package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;


public class AudioProcessor {

    AudioProcessor(){
        AudioFormat audioFormat = new AudioFormat(8000, 8, 1, true, true);
        try {
            TargetDataLine dataLine = AudioSystem.getTargetDataLine(audioFormat);
            dataLine.open();
            dataLine.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

}
