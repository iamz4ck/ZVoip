package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class ClientNoiseGateSliderPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private MicrophoneSettingsPanel settingsPanel;
    private JSlider noiseGateSlider;
    private SettingsRMSLevelDisplayPanel settingsRMSLevelDisplayPanel;
    private ChatClient chatClient;

    public ClientNoiseGateSliderPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        noiseGateSlider = configureClientVoiceGateSlider();
        settingsRMSLevelDisplayPanel = new SettingsRMSLevelDisplayPanel(chatClient, this);
        this.setLayout(new BorderLayout());
        this.add(noiseGateSlider, BorderLayout.NORTH);
        this.add(settingsRMSLevelDisplayPanel, BorderLayout.CENTER);
    }

    /**
     * Create noise gate slider
     * for client to set values.
     * @return JSlider
     */
    public JSlider configureClientVoiceGateSlider() {
        JSlider jSlider = new JSlider(JSlider.HORIZONTAL);
        return jSlider;
    }

    /**
     * Returns double representation
     * of client noise gate slider setting.
     * @return
     */
    public double getNoiseGateLevel() {
        return noiseGateSlider.getValue();
    }

    /**
     * Returns client microphone
     * settings panel
     * @return MicrophoneSettingsPanel
     */
    public MicrophoneSettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

}

