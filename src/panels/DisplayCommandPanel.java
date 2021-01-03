package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DisplayCommandPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;

    private JButton closeDisplayButton;
    private JButton minimizeDisplayButton;

    private Icon closeDisplayButtonIcon;
    private Icon minimizeDisplayButtonIcon;

    public DisplayCommandPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();

    }

    public void init() {
        initIcons();
        initButtons();
        setButtonActionListeners();
        this.setLayout(new GridLayout(2,0));
        this.add(closeDisplayButton);
        this.add(minimizeDisplayButton);

    }

    public void initIcons() {
        closeDisplayButtonIcon = chatClient.getImageResources().getCloseWindowIcon();
        minimizeDisplayButtonIcon = chatClient.getImageResources().getMinimizeFrameIcon();
    }

    /**
     * Creates JButtons.
     */
    public void initButtons() {
        closeDisplayButton = new JButton(closeDisplayButtonIcon);
        minimizeDisplayButton = new JButton(minimizeDisplayButtonIcon);
    }

    /**
     * Adds action listeners to buttons
     */
    public void setButtonActionListeners() {
        closeDisplayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chatClient.getServerWriter().sendDisconnectionCommand();
                chatClient.getDisplay().getFrame().dispose();
                try {
                    chatClient.getSocket().close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                chatClient.shutDownExecutorService();
                chatClient.getServerReader().setIsRunning(false);
            }
        });
        minimizeDisplayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(chatClient.getDisplay().getFrame().getState() != Frame.ICONIFIED) {
                    chatClient.getDisplay().getFrame().setState(Frame.ICONIFIED);
                }

            }
        });
    }

}

