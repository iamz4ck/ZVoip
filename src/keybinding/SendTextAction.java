package keybinding;

import panels.ChatMessagePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SendTextAction extends AbstractAction {

    /**
     * Enter Key Binding Action
     */
    private static final long serialVersionUID = 1L;
    private String keyPressed;
    private ChatMessagePanel chatMessagePanel;

    public SendTextAction(String keyPressed, ChatMessagePanel chatMessagePanel) {
        this.keyPressed = keyPressed;
        this.chatMessagePanel = chatMessagePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(keyPressed.equalsIgnoreCase("ENTER")) {
            chatMessagePanel.sendChatAreaStringToServerWriter();
        }
    }
}
