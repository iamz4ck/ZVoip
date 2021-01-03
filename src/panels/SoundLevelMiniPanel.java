package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class SoundLevelMiniPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int currentIncomingRMSLevel = 20;
    private int currentOutgoingRMSLevel = 10;

    private ChatClient chatClient;

    public SoundLevelMiniPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Color previousColor = graphics.getColor();
        graphics.clearRect(0, 0, getWidth(), getHeight());


        graphics.drawString("Incoming sound level",  10, 12);
        graphics.drawRect(6, 0, 136, 26);

        graphics.drawString("Microphone sound level", 10, 52);
        graphics.drawRect(6, 40, 136, 26);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(10, 14, getRMSIncomingAudio(), 10);

        graphics.fillRect(10, 54, getRMSOutgoingAudio(), 10);

        graphics.setColor(previousColor);
        graphics.dispose();
        repaint(500);
    }

    public int getRMSIncomingAudio() {
        if(chatClient.getServerReader() != null) {
            return (int) chatClient.getServerReader().getCurrentIncomingRMSLevel();
        } else {
            return 0;
        }
    }

    /**
     * Returns current microphone sound
     * level casted to an integer.
     * @return integer
     */
    public int getRMSOutgoingAudio() {
        if(chatClient.getMicrophoneCapture() != null) {
            return (int) chatClient.getMicrophoneCapture().getRMSLevel();
        } else {
            return 0;
        }
    }

    public int getCurrentIncomingRMSLevel() {
        return currentIncomingRMSLevel;
    }

    public void setCurrentIncomingRMSLevel(int currentIncomingRMSLevel) {
        this.currentIncomingRMSLevel = currentIncomingRMSLevel;
    }

    public int getCurrentOutgoingRMSLevel() {
        return currentOutgoingRMSLevel;
    }

    public void setCurrentOutgoingRMSLevel(int currentOutgoingRMSLevel) {
        this.currentOutgoingRMSLevel = currentOutgoingRMSLevel;
    }

}
