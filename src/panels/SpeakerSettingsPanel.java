package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeakerSettingsPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;
    private SpeakerSettingsSliderPanel speakerSettingsSliderPanel;
    private SpeakerSettingsRMSLevelDisplay speakerSettingRMSLevelDisplay;

    private JButton applyButton;


    public SpeakerSettingsPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        speakerSettingsSliderPanel = new SpeakerSettingsSliderPanel(chatClient);
        speakerSettingRMSLevelDisplay = new SpeakerSettingsRMSLevelDisplay(chatClient, speakerSettingsSliderPanel);
        applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chatClient.getServerReader().getAudioWriter().setVolumeOnLine(speakerSettingsSliderPanel.getVolumeSliderValue());
            }
        });
        setLayout(new GridLayout(3, 0));
        add(speakerSettingsSliderPanel);
        add(speakerSettingRMSLevelDisplay);
        add(applyButton);
    }

    public SpeakerSettingsSliderPanel getSpeakerSettingsSliderPanel() {
        return speakerSettingsSliderPanel;
    }



}

