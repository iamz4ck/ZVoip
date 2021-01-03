package panels;

import utilities.ChatClient;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class ConnectedUsersJListPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JList<String> usersConnectedJList;
    private DefaultListModel<String> defaultListModel;
    private JScrollPane listScrollPane;
    private ChatClient chatClient;
    ArrayList<String> usersInJList;

    public ConnectedUsersJListPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        usersInJList = new ArrayList<String>();
        Border border = BorderFactory.createTitledBorder("Users Connected:");
        setBorder(border);
        setLayout(new GridLayout());
        usersConnectedJList = new JList<String>();
        defaultListModel = new DefaultListModel<String>();
        usersConnectedJList.setModel(defaultListModel);
        listScrollPane = new JScrollPane(usersConnectedJList);
        this.add(listScrollPane);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 240);
    }



    public void addUserToConnectedUserJList(String usernameToAdd) {
        if(!defaultListModel.contains(usernameToAdd) && usernameToAdd.length() > 1) {
            defaultListModel.addElement(usernameToAdd);
            usersInJList.add(usernameToAdd);
            chatClient.getDisplay().getJTreePanel().getChannelTreeHolderPanel().addUsersToLobbyTreeNode(usersInJList);
        }
    }

    /**
     * Clears all lists containing
     * connected users representation.
     */
    public void clearLists() {
        clearConnectedUserList();
        clearUsersInJListArray();
    }

    /**
     * Clears connected users
     * JList.
     */
    public void clearUsersInJListArray() {
        usersInJList.removeAll(usersInJList);
    }

    /**
     * Clears connected users in
     * channel list.
     */
    public void clearConnectedUserList() {
        defaultListModel.clear();
    }

}
