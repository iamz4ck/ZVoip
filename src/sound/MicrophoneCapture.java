package sound;

import utilities.ChatClient;

import javax.sound.sampled.*;
import java.io.IOException;

public class MicrophoneCapture extends Thread {

    private TargetDataLine microphone;
    private AudioFormat audioFormat;

    //Streams
    private AudioInputStream audioInputStream;

    /////////////////////
    private double currentRMSLevel;
    private SoundUtilities soundUtilities;
    private ChatClient chatClient;
    private boolean isRunning = false, isRecording = false;;



    public MicrophoneCapture(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    @Override
    public void run() {
        isRunning = true;
        while(isRunning && !chatClient.getSocket().isClosed()) {
            if(isRecording) {
                int length = 0;
                byte[] tempBufferArray = new byte[microphone.getBufferSize()];
                try {
                    length = audioInputStream.read(tempBufferArray, 0, tempBufferArray.length);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                currentRMSLevel = soundUtilities.getRMS(tempBufferArray);
                if(currentRMSLevel >= chatClient.getClientMicrophoneNoiseGateLevel()) {
                    chatClient.getServerWriter().sendAudioBytesToServer(tempBufferArray);
                    sleepCapture(0);
                }
            }
        }
    }

    public void init() {
        soundUtilities = new SoundUtilities();
        audioFormat = soundUtilities.getAudioFormat();
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
        try {
            microphone = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            microphone.open();
            microphone.start();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        audioInputStream = new AudioInputStream(microphone);
    }

    /**
     * Starts or stops recording
     * can be resumed and (not complete shutdown).
     * @param isRecording
     */
    public void setIsRecording(boolean isRecording) {
        this.isRecording = isRecording;
    }

    /**
     * Called when shutting down microphone
     * capture for graceful thread shutdown.
     */
    public void endMicrophoneCapture() {
        if(microphone != null) {
            microphone.close();
        }
        microphone = null;
        audioFormat = null;
        soundUtilities = null;
        isRunning = false;
    }

    public int getRMSLevel() {
        return (int) currentRMSLevel;
    }

    /**
     * Puts thread to sleep for timing issues.
     * @param amountToAddToNormal
     */
    public void sleepCapture(int amountToAddToNormal) {
        try {
            Thread.sleep(120 + amountToAddToNormal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns boolean dependent of
     * status of recording microphone.
     * @return
     */
    public boolean getIsRecording() {
        return this.isRecording;
    }



}

