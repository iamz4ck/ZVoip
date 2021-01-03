package utilities;

import network.ServerReader;
import network.ServerWriter;
import sound.AudioWriter;
import sound.MicrophoneCapture;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {

    private ImageResources imageResources;
    private Display display;
    private String username;
    private ServerWriter serverWriter;
    private ServerReader serverReader;
    private MicrophoneCapture microphoneCapture;
    private Socket socket;
    private ExecutorService executorService;

    private String ip = "localhost";//"71.120.157.144";
    private int port = 5550;

    private boolean isMicrophoneRecording = false;

    private AudioWriter audioWriter;

    public ChatClient() {
        Object result = JOptionPane.showInputDialog(null, "Enter a username:");
        username = (String) result.toString().trim();
        init();
    }

    /**
     * Initializes all needed threads and
     * classes.
     */
    public void init() {
        imageResources = new ImageResources();
        display = new Display(this);
        executorService = Executors.newCachedThreadPool();
        try {
            socket = new Socket(ip, port);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        serverWriter = new ServerWriter(this);
        serverReader = new ServerReader(this);
        microphoneCapture = new MicrophoneCapture(this);
        audioWriter = new AudioWriter(this);
        executorService.execute(serverWriter);
        executorService.execute(serverReader);
        executorService.execute(microphoneCapture);
        executorService.execute(audioWriter);
    }


    /**
     * Starts or resumes recording on
     * current microphone
     */
    public void startRecordingMicrophone() {
        microphoneCapture.setIsRecording(true);
    }

    /**
     * Stops all recording on current microphone
     */
    public void stopRecordingMicrophone() {
        microphoneCapture.setIsRecording(false);
    }

    /**
     * Returns ImageResource class
     * @return
     */
    public ImageResources getImageResources() {
        return imageResources;
    }

    /**
     * Returns clients socket in use.
     * @return Socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Returns AudioWriter class
     * @return AudioWriter
     */
    public AudioWriter getAudioWriter() {
        return audioWriter;
    }

    /**
     * Returns ServerWriter class
     * @return ServerWriter
     */
    public ServerWriter getServerWriter() {
        return serverWriter;
    }

    /**
     * Returns ServerReader class
     * @return
     */
    public ServerReader getServerReader() {
        return serverReader;
    }

    /**
     * Returns Display class
     * that manages gui.
     * @return
     */
    public Display getDisplay() {
        return display;
    }

    /**
     * Returns clients set username.
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns boolean that confirms
     * state of mic recording.
     * @return
     */
    public boolean getIsMicrophoneRecording() {
        return isMicrophoneRecording;
    }

    /**
     * Returns MicrophoneCapture class
     * @return MicrophoneCapture
     */
    public MicrophoneCapture getMicrophoneCapture() {
        return microphoneCapture;
    }

    /**
     * Attempts to shutdown all threads
     * currently in ExecutorService
     */
    public void shutDownExecutorService() {
        executorService.shutdownNow();
    }

    /**
     * Returns double representation of
     * current microphone noise level.
     * @return
     */
    public double getClientMicrophoneNoiseGateLevel() {
        return getDisplay().getSettingsTabbedPanel().getMicrophoneSettingsPanel().getClientNoiseGateSliderPanel().getNoiseGateLevel();
    }



}

