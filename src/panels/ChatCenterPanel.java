package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class ChatCenterPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private ChatClient chatClient;
    private ConnectedUsersPanel connectedUsersPanel;
    private ChatMessagePanel chatMessagePanel;

    public ChatCenterPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
        addPanelComponents();
    }

    public void init() {
        chatMessagePanel = new ChatMessagePanel(chatClient);
        connectedUsersPanel = new ConnectedUsersPanel(chatClient);
    }

    /**
     * Adds panel commponets to
     * this panel.
     */
    public void addPanelComponents() {
        this.setLayout(new BorderLayout());
        this.add(chatMessagePanel, BorderLayout.WEST);
        this.add(connectedUsersPanel, BorderLayout.EAST);
    }

    /**
     * Returns instance of ChatClient
     * @return ChatClient
     */
    public ChatClient getChatClient() {
        return chatClient;
    }

    /**
     * Returns instance of ConnectedUsersPanel
     * @return ConnectedUsersPanel
     */
    public ConnectedUsersPanel getConnectedUsersPanel() {
        return connectedUsersPanel;
    }

    /**
     * Returns instance of ChatMessagePanel
     * @return ChatMessagePanel
     */
    public ChatMessagePanel getChatMessagePanel() {
        return chatMessagePanel;
    }

}

