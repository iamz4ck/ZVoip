package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class MicrophoneSettingsPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;
    private ClientNoiseGateSliderPanel clientNoiseGateSliderPanel;


    public MicrophoneSettingsPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 230);
    }

    public void init() {
        clientNoiseGateSliderPanel = new ClientNoiseGateSliderPanel(chatClient);
        this.setLayout(new BorderLayout());
        this.add(clientNoiseGateSliderPanel, BorderLayout.CENTER);
    }

    /**
     * Returns ChatClient
     * @return ChatClient
     */
    public ChatClient getChatClient() {
        return chatClient;
    }

    /**
     * Returns client noise gate panel holding
     * JSlider noise gate
     * @return
     */
    public ClientNoiseGateSliderPanel getClientNoiseGateSliderPanel() {
        return clientNoiseGateSliderPanel;
    }

}
