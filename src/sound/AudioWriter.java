package sound;

import utilities.ChatClient;

import javax.sound.sampled.*;
import javax.swing.*;
import java.util.ArrayList;

public class AudioWriter extends Thread {

    private ChatClient chatClient;
    private boolean isRunning = false;
    private ArrayList<byte[]> audioBytesToWriteList;
    private DataLine.Info dataLineInfo;
    private SourceDataLine sourceDataLine;
    private SoundUtilities soundUtilities;
    private FloatControl volumeControl;
    private byte[] emptyArray = new byte[1024];

    public AudioWriter(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    @Override
    public void run() {
        isRunning = true;
        while(isRunning) {
            if(audioBytesToWriteList.size() > 0) {
                byte[] nextDataToWrite = audioBytesToWriteList.get(0);
                sourceDataLine.write(nextDataToWrite, 0, nextDataToWrite.length);
                audioBytesToWriteList.remove(0);
            } else {
                try {
                    setVolumeOnLine(getValueOfSpeakerSliderSetting());
                    sourceDataLine.write(emptyArray, 0, emptyArray.length);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    isRunning = false;
                    e.printStackTrace();
                }
            }
        }
    }

    public void init() {
        soundUtilities = new SoundUtilities();
        audioBytesToWriteList = new ArrayList<byte[]>();
        dataLineInfo = new DataLine.Info(SourceDataLine.class, soundUtilities.getAudioFormat());
        try {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open();
            sourceDataLine.start();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        volumeControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
    }

    public void addBytesToWriteArray(byte[] audioData) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                audioBytesToWriteList.add(audioData);
            }
        });
    }

    /**
     * Returns integer value of speaker JSlider
     * @return integer
     */
    public int getValueOfSpeakerSliderSetting() {
        return chatClient.getDisplay().getSettingsTabbedPanel().getSpeakerSettingsPanel().getSpeakerSettingsSliderPanel().getVolumeSliderValue();
    }

    /**
     * Changes volume of SourceDataLine
     * @param setting
     */
    public void setVolumeOnLine(float setting) {
        volumeControl.setValue(setting);
    }

}

