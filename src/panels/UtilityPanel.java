package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UtilityPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;

    //Panels
    private DisplayCommandPanel displayCommandPanel;

    //Buttons
    private JButton settingsButton;
    private JButton microphoneButton;
    private JButton speakerButton;
    private JButton sendFileButton;
    private JButton messageHomeButton;
    /////////////////////////////////////

    //Button Icons
    private Icon settingsIcon;
    private Icon microphoneMutedIcon;
    private Icon microphoneUnMutedIcon;
    private Icon speakerMutedIcon;
    private Icon speakerUnMutedIcon;
    private Icon sendFileIcon;
    private Icon messageHomeIcon;

    public UtilityPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        initIcons();
        initButtons();
        setActionListenersToButtons();
        initPanels();
        addComponentsToPanel();
    }

    public void initIcons() {
        settingsIcon = chatClient.getImageResources().getSettingsIcon();
        microphoneMutedIcon = chatClient.getImageResources().getMicrophoneMutedIcon();
        microphoneUnMutedIcon = chatClient.getImageResources().getMicrophoneUnMutedIcon();
        speakerMutedIcon = chatClient.getImageResources().getSpeakerMutedIcon();
        speakerUnMutedIcon = chatClient.getImageResources().getSpeakerUnMutedIcon();
        sendFileIcon = chatClient.getImageResources().getSendFileIcon();
        messageHomeIcon = chatClient.getImageResources().getMessageHomeIcon();
    }

    public void initButtons() {
        settingsButton = new JButton(settingsIcon);
        microphoneButton = new JButton(microphoneUnMutedIcon);
        speakerButton = new JButton(speakerMutedIcon);
        sendFileButton = new JButton(sendFileIcon);
        messageHomeButton = new JButton(messageHomeIcon);
    }

    public void initPanels() {
        displayCommandPanel = new DisplayCommandPanel(chatClient);
    }

    public void addComponentsToPanel() {
        this.add(messageHomeButton);
        this.add(settingsButton);
        this.add(microphoneButton);
        this.add(speakerButton);
        this.add(sendFileButton);
        this.add(displayCommandPanel);
    }

    public void setActionListenersToButtons() {
        settingsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chatClient.getDisplay().showSettingsPanel();
                return;
            }
        });
        settingsButton.setToolTipText("Settings");
        microphoneButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chatClient.getMicrophoneCapture().getIsRecording()) {
                    chatClient.stopRecordingMicrophone();
                    microphoneButton.setIcon(microphoneUnMutedIcon);
                    return;
                }
                if(!chatClient.getMicrophoneCapture().getIsRecording()) {
                    chatClient.startRecordingMicrophone();
                    microphoneButton.setIcon(microphoneMutedIcon);
                    return;
                }
            }
        });
        microphoneButton.setToolTipText("Microphone");
        speakerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!chatClient.getServerReader().getIsSoundMuted()) {
                    chatClient.getServerReader().setIsSoundMuted(true);
                    speakerButton.setIcon(speakerUnMutedIcon);
                    return;
                }
                if(chatClient.getServerReader().getIsSoundMuted()) {
                    chatClient.getServerReader().setIsSoundMuted(false);
                    speakerButton.setIcon(speakerMutedIcon);
                    return;
                }
            }
        });
        speakerButton.setToolTipText("Speaker");
        sendFileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        sendFileButton.setToolTipText("Send file *not working*");
        messageHomeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                chatClient.getDisplay().showCenterPanel();
                return;

            }
        });
        messageHomeButton.setToolTipText("Messaging");
    }
}

