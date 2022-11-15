package audio;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/** A container for an audio signal backed by a double buffer so as to allow floating point calculation
 * for signal processing and avoid saturation effects. Samples are 16 bit wide in this implementation. */
public class AudioSignal {

    private double[] sampleBuffer; // floating point representation of audio samples
    private double dBlevel; // current signal level

    /**
     * Construct an AudioSignal that may contain up to "frameSize" samples.
     * @param frameSize the number of samples in one audio frame */
    public AudioSignal(int frameSize) {
        sampleBuffer = new double[frameSize];
    }

    /** Sets the content of this signal from another signal.
     * @param other other.length must not be lower than the length of this signal */
    public void setFrom(AudioSignal other) throws Exception {
        if(other.sampleBuffer.length > sampleBuffer.length){
            throw new Exception("Length of other signal should be lower than the lenght of this signal");
        }
        else {
            int i = 0;
            for(; i < other.sampleBuffer.length; i++){
                sampleBuffer[i] = other.sampleBuffer[i];
            }
            for(; i < sampleBuffer.length; i++){
                sampleBuffer[i] = 0;
            }
        }
    }

    /** Fills the buffer content from the given input. Byte's are converted on the fly to double's.
     * @return false if at end of stream */
    public boolean recordFrom(TargetDataLine audioInput) {
        byte[] byteBuffer = new byte[sampleBuffer.length * 2]; // 16 bit samples
        if (audioInput.read(byteBuffer, 0, byteBuffer.length) == -1)
            return false;
        for (int i = 0; i < sampleBuffer.length; i++) {
            sampleBuffer[i] = ((byteBuffer[2 * i] << 8) + byteBuffer[2 * i + 1]) / 32768.0; // big endian
        }
        //dBlevel =
        // ... TODO : dBlevel = update signal level in dB here ...
        return true;
    }

    /** Plays the buffer content to the given output.
     * @return false if at end of stream */
    public boolean playTo(SourceDataLine audioOutput) {
        return false;
    }

    // your job: add getters and setters ...
    double getSample(int i) throws Exception {
        if(i >= sampleBuffer.length || sampleBuffer.length < 0)
            throw new Exception("The index is out of bound");
        return sampleBuffer[i];
    }

    void setSample(int i, double value) throws Exception{
        if(i >= sampleBuffer.length || sampleBuffer.length < 0)
            throw new Exception("The index is out of bound");
        sampleBuffer[i] = value;
    }

    double getdBLevel(){
        return dBlevel;
    }

    int getFrameSize(){
        return sampleBuffer.length;
    }

    //Can be implemented much later:
    // Complex[] computeFFT()
}
