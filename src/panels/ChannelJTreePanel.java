package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class ChannelJTreePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;

    private ChannelTreeHolderPanel channelTreeHolderPanel;
    private ChannelJTreeButtonInfoPanel channelTreeButtonPanel;

    public ChannelJTreePanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        setLayout(new GridLayout(0,2));
        setBorder(BorderFactory.createTitledBorder("Channels"));
        channelTreeHolderPanel = new ChannelTreeHolderPanel(chatClient);
        channelTreeButtonPanel = new ChannelJTreeButtonInfoPanel(chatClient);
        add(channelTreeHolderPanel);
        add(channelTreeButtonPanel);
    }

    /*
     * Returns ChannelTreeHolderPanel	 *
     */
    public ChannelTreeHolderPanel getChannelTreeHolderPanel() {
        return channelTreeHolderPanel;
    }

}
