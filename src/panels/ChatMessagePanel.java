package panels;

import keybinding.SendTextAction;
import network.MessagePacket;
import panes.ServerMessagesEditorPane;
import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ChatMessagePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;


    ////////////////////
    private ServerMessagesEditorPanePanel serverMessagesEditorPanePanel;

    //out from client
    private JTextArea clientToServerMsgArea;
    private JScrollPane clientToServerScrollPane;
    ////////////////////

    //buttons
    private JButton sendMsgButton;
    ////////////////////
    //key binding
    private SendTextAction sendTextAction;
    private InputMap inputMap;
    private ActionMap actionMap;

    public ChatMessagePanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        serverMessagesEditorPanePanel = new ServerMessagesEditorPanePanel(chatClient);
        clientToServerMsgArea = new JTextArea(3, 25);
        clientToServerScrollPane = new JScrollPane(clientToServerMsgArea);
        clientToServerScrollPane.setAutoscrolls(true);
        sendMsgButton = new JButton("Send");
        inputMap = clientToServerMsgArea.getInputMap();
        actionMap = clientToServerMsgArea.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
        actionMap.put("ENTER", new SendTextAction("ENTER", this));
        setSendMsgButtonActionListener();
        this.setLayout(new BorderLayout());
        this.add(serverMessagesEditorPanePanel, BorderLayout.NORTH);
        this.add(clientToServerScrollPane, BorderLayout.CENTER);
        this.add(sendMsgButton, BorderLayout.SOUTH);
    }

    public void setSendMsgButtonActionListener() {
        sendMsgButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendChatAreaStringToServerWriter();
            }
        });
    }

    /**
     * Gets text from client chat sending text area
     * and sends it to server.
     */
    public void sendChatAreaStringToServerWriter() {
        String chatAreaString = getTextInClientMsgArea();
        //chatClient.getServerWriter().sendStringBytesToServer(NetworkProtocol.MSG, chatAreaString);
        MessagePacket messagePacket = new MessagePacket("LOGGED_IN", "SIMPLE_MESSAGE", chatClient.getUsername(), "GENERAL/DEFAULT", chatAreaString, "SIMPLE_MESSAGE");
        chatClient.getServerWriter().sendMessagePacketToServer(messagePacket);
        clientToServerMsgArea.setText("");
        //chatClient.getServerWriter().sendUsersConnectedRequest();
    }

    /**
     * Returns instance of client JTextArea
     * meant for sending strings to server.
     * @return
     */
    public JTextArea getClientToServerMsgTextArea() {
        return clientToServerMsgArea;
    }

    /**
     * Returns instance of clients
     * ServerMessagesEditorPane
     * used for incoming server messages.
     * @return ServerMessagesEditorPane
     */
    public ServerMessagesEditorPane getServerMessageEditorPane() {
        return serverMessagesEditorPanePanel.getServerMessagesEditorPane();
    }

    /**
     * Returns String of client's
     * JTextArea used to send
     * messages to server.
     *
     * @return
     */
    public String getTextInClientMsgArea() {
        return clientToServerMsgArea.getText().trim();
    }

}
