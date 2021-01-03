package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;

public class ConnectedUsersPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;

    private SoundLevelMiniPanel soundLevelMiniPanel;
    private ConnectedUsersJListPanel connectedUsersJListPanel;


    public ConnectedUsersPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
        addPanelComponents();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 110);
    }


    public void init() {
        connectedUsersJListPanel = new ConnectedUsersJListPanel(chatClient);
        soundLevelMiniPanel = new SoundLevelMiniPanel(chatClient);
    }

    /**
     * Adds all panels to this panel.
     */
    public void addPanelComponents() {
        this.setLayout(new BorderLayout());
        this.add(connectedUsersJListPanel, BorderLayout.NORTH);
        this.add(soundLevelMiniPanel, BorderLayout.CENTER);
    }

    /**
     * Adds users to all lists
     * representing connected users.
     * @param usernameToAdd
     */
    public void addUserToConnectedUserJList(String usernameToAdd) {
        connectedUsersJListPanel.addUserToConnectedUserJList(usernameToAdd);
    }

    /**
     * Clears all lists representing
     * Connected users.
     */
    public void clearAllUsersLists() {
        connectedUsersJListPanel.clearLists();
    }





}

