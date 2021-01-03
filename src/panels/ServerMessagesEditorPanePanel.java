package panels;

import panes.ServerMessagesEditorPane;
import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class ServerMessagesEditorPanePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ChatClient chatClient;
    private ServerMessagesEditorPane serverMessagesEditorPane;
    private JScrollPane serverMsgsScrollPane;

    public ServerMessagesEditorPanePanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(340, 230);
    }

    public void init() {
        serverMessagesEditorPane = new ServerMessagesEditorPane(chatClient);
        serverMsgsScrollPane = new JScrollPane(serverMessagesEditorPane);
        setLayout(new BorderLayout());
        add(serverMsgsScrollPane, BorderLayout.CENTER);
    }

    public ServerMessagesEditorPane getServerMessagesEditorPane() {
        return serverMessagesEditorPane;
    }

}