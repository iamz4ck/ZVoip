package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class SettingsTabbedPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;
    //
    private JTabbedPane jTabbedPane;
    private MicrophoneSettingsPanel microphoneSettingsPanel;
    private SpeakerSettingsPanel speakerSettingsPanel;
    //
    private Icon tinyKeyboardIcon;
    private Icon tinySpeakerIcon;
    private Icon tinyMicrophoneIcon;
    //

    public SettingsTabbedPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        jTabbedPane = new JTabbedPane();
        microphoneSettingsPanel = new MicrophoneSettingsPanel(chatClient);
        speakerSettingsPanel = new SpeakerSettingsPanel(chatClient);
        tinyKeyboardIcon = chatClient.getImageResources().getTinyKeyboardIcon();
        tinySpeakerIcon = chatClient.getImageResources().getTinySpeakerIcon();
        tinyMicrophoneIcon = chatClient.getImageResources().getTinyMicrophoneIcon();
        jTabbedPane.addTab("Microphone", tinyMicrophoneIcon, microphoneSettingsPanel);
        jTabbedPane.addTab("Speakers", tinySpeakerIcon, speakerSettingsPanel);
        setLayout(new BorderLayout());
        this.add(jTabbedPane, BorderLayout.CENTER);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(350, 210);
    }

    public MicrophoneSettingsPanel getMicrophoneSettingsPanel() {
        return microphoneSettingsPanel;
    }

    public SpeakerSettingsPanel getSpeakerSettingsPanel() {
        return speakerSettingsPanel;
    }
}

