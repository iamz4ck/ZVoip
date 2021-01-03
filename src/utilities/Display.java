package utilities;

import panels.ChannelJTreePanel;
import panels.ChatCenterPanel;
import panels.SettingsTabbedPanel;
import panels.UtilityPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Display {

    //
    private JFrame frame;
    private ChatClient chatClient;
    private Point mousePoint;
    //

    //Panels
    private ChatCenterPanel chatCenterPanel;
    private SettingsTabbedPanel settingsTabbedPanel;
    private UtilityPanel utilityPanel;
    private ChannelJTreePanel channelJTreePanel;


    public Display(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    /**
     * Initialies panels and gui
     * in order.
     */
    public void init() {
        initPanels();
        initFrame();
    }

    /**
     * Initializes all main panels
     * in main gui window
     */
    public void initPanels() {
        utilityPanel = new UtilityPanel(chatClient);
        chatCenterPanel = new ChatCenterPanel(chatClient);
        settingsTabbedPanel = new SettingsTabbedPanel(chatClient);
        channelJTreePanel = new ChannelJTreePanel(chatClient);
    }

    /**
     * Initializes gui actual jframe and adds
     * components to jframe
     */
    public void initFrame() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    mousePoint = new Point(mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                Point currentLocation = frame.getLocation();
                frame.setLocation(currentLocation.x + mouseEvent.getX() - mousePoint.x,
                        currentLocation.y + mouseEvent.getY() - mousePoint.y);
            }
        });
        frame.add(utilityPanel, BorderLayout.NORTH);
        showCenterPanel();
        showChannelTreePanel();
    }

    /**
     * Removes ChatCenterPanel and
     * Adds settings panel in the same
     * BorderLayout location.
     */
    public void showSettingsPanel() {
        frame.getContentPane().remove(chatCenterPanel);
        frame.add(settingsTabbedPanel, BorderLayout.CENTER);
        frame.pack();
        frame.validate();
    }

    /**
     * Removes settings panel if visible
     * and puts ChatCenterPanel in Border
     * Layout CENTER
     */
    public void showCenterPanel() {
        frame.remove(settingsTabbedPanel);
        frame.add(chatCenterPanel, BorderLayout.CENTER);
        frame.pack();
        frame.validate();
    }

    /**
     * Adds ChannelJTreePanel to gui
     * BorderLayout
     */
    public void showChannelTreePanel() {
        frame.add(channelJTreePanel, BorderLayout.SOUTH);
        frame.pack();
        frame.validate();
    }

    /**
     * Returns instance of ChatCenterPanel
     * class.
     * @return ChatCenterPanel
     */
    public ChatCenterPanel getChatCenterPanel() {
        return chatCenterPanel;
    }

    /**
     * Returns instance of SettingsTabbedPanel
     * class.
     * @return SettingTabbedPanel
     */
    public SettingsTabbedPanel getSettingsTabbedPanel() {
        return settingsTabbedPanel;
    }

    /**
     * Returns JFrame of gui	 *
     * @return JFrame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Returns instance of ChannelJTreePanel
     * class.
     * @return
     */
    public ChannelJTreePanel getJTreePanel() {
        return channelJTreePanel;
    }

}

