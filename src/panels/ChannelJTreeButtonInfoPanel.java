package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChannelJTreeButtonInfoPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;

    private JButton joinButton;
    private JButton leaveButton;

    public ChannelJTreeButtonInfoPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        initButtons();
        addButtons();
    }

    /**
     * Adds buttons to panel using
     * GridLayout
     */
    public void addButtons() {
        setLayout(new GridLayout(2, 0));
        add(joinButton);
        add(leaveButton);
    }

    /**
     * Initializes buttons to be
     * added.
     */
    public void initButtons() {
        joinButton = new JButton("Join");
        leaveButton = new JButton("Leave");
        joinButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        leaveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

}

