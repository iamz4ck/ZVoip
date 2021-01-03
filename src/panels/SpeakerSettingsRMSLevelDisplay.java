package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class SpeakerSettingsRMSLevelDisplay extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;
    private Font largeFont;
    private SpeakerSettingsSliderPanel sliderPanel;

    public SpeakerSettingsRMSLevelDisplay(ChatClient chatClient, SpeakerSettingsSliderPanel sliderPanel) {
        this.chatClient = chatClient;
        this.sliderPanel = sliderPanel;
        largeFont = new Font("TimesRoman", Font.BOLD, 24);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 150);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.clearRect(0, 0, getWidth(), getHeight());
        graphics.setFont(largeFont);
        graphics.drawString("Current setting: " + sliderPanel.getVolumeSliderValue(), 150, 16);
        graphics.drawString("Current incoming audio level:" + getRMSOfIncomingAudio(), 10, 48);
        graphics.dispose();
        repaint();
    }

    /**
     * Returns int representation of
     * incoming audio level casted from
     * a double
     * @return integer
     */
    public int getRMSOfIncomingAudio() {
        return (int) chatClient.getServerReader().getCurrentIncomingRMSLevel();
    }

}

