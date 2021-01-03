package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class SettingsRMSLevelDisplayPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ClientNoiseGateSliderPanel clientNoiseGateSliderPanel;
    private ChatClient chatClient;
    private Font largeFont;

    public SettingsRMSLevelDisplayPanel(ChatClient chatClient, ClientNoiseGateSliderPanel clientNoiseGateSliderPanel) {
        this.chatClient = chatClient;
        this.clientNoiseGateSliderPanel = clientNoiseGateSliderPanel;
        largeFont = new Font("TimesRoman", Font.BOLD, 24);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.clearRect(0, 0, getWidth(), getHeight());
        Font previousFont = graphics.getFont();
        graphics.setFont(largeFont);
        Color previousColor = graphics.getColor();
        graphics.drawString("Noise Gate Setting Level: " + clientNoiseGateSliderPanel.getNoiseGateLevel(), 80, 18);
        graphics.setFont(previousFont);
        graphics.drawString("Microphone Sound Level: " + getRMSOfOutgoingAudio(), 10, 38);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(170, 28, getRMSOfOutgoingAudio() + 1, 10);
        graphics.setColor(previousColor);
        graphics.dispose();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 150);
    }

    public int getRMSOfOutgoingAudio() {
        if(chatClient.getMicrophoneCapture() != null) {
            return chatClient.getMicrophoneCapture().getRMSLevel();
        }
        return 0;
    }

    public int getRMSOfIncomingAudio() {
        return (int) chatClient.getServerReader().getCurrentIncomingRMSLevel();
    }

}

