package panels;

import utilities.ChatClient;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;

public class ChannelTreeHolderPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;
    private JScrollPane channelScrollPane;

    private JTree channelJTree;
    private DefaultMutableTreeNode channelPlaceHolder;
    private DefaultMutableTreeNode lobbyTreeNode;
    private DefaultMutableTreeNode h1z1TreeNode;

    public ChannelTreeHolderPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(120, 80);
    }

    public void init() {
        channelPlaceHolder = new DefaultMutableTreeNode("Channels");
        lobbyTreeNode = new DefaultMutableTreeNode("Lobby");
        h1z1TreeNode = new DefaultMutableTreeNode("H1Z1");
        channelPlaceHolder.add(lobbyTreeNode);
        channelPlaceHolder.add(h1z1TreeNode);
        channelJTree = new JTree(channelPlaceHolder);
        channelJTree.setBackground(Color.gray);
        channelScrollPane = new JScrollPane();
        channelScrollPane.getViewport().add(channelJTree);
        setLayout(new BorderLayout());
        this.add(channelScrollPane, BorderLayout.CENTER);
    }

    /**
     *
     * @param users
     */
    public void addUsersToLobbyTreeNode(ArrayList<String> users) {
        DefaultTreeModel model = (DefaultTreeModel) channelJTree.getModel();
        lobbyTreeNode.removeAllChildren();
        for(String username: users) {
            DefaultMutableTreeNode newNodeToAdd = new DefaultMutableTreeNode(username);
            model.insertNodeInto(newNodeToAdd, lobbyTreeNode, lobbyTreeNode.getChildCount());
        }
        model.reload();
    }
}

