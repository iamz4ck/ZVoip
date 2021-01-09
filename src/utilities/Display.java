package utilities;

import panels.*;

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
    private SendFilePanel sendFilePanel;

    String currentPanelShowing = "CENTER_PANEL";


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

    public String getCurrentPanelShowingState() {
        return this.currentPanelShowing;
    }

    public void setCurrentPanelShowingState(String panelShowing) {
        this.currentPanelShowing = panelShowing;
    }

    public void addSelectedPanel(JPanel jPanel, String panelName) {
        String previousPanelShowing = this.currentPanelShowing;
        System.out.println("Current panel showing 1: " + previousPanelShowing);
        if(previousPanelShowing.equalsIgnoreCase(GUIProtocol.SETTINGS_PANEL_STATE)) {
            frame.getContentPane().remove(settingsTabbedPanel);
            frame.add(jPanel, BorderLayout.CENTER);
            frame.pack();
            frame.validate();
            System.out.println("1");
            this.currentPanelShowing = panelName;
            return;
        }
        if(previousPanelShowing.equalsIgnoreCase(GUIProtocol.CHAT_CENTER_PANEL_STATE)) {
            frame.getContentPane().remove(chatCenterPanel);
            frame.add(jPanel, BorderLayout.CENTER);
            frame.pack();
            frame.validate();
            System.out.println("2");
            this.currentPanelShowing = panelName;
            return;
        }
        if(previousPanelShowing.equalsIgnoreCase(GUIProtocol.SEND_FILE_PANEL_STATE)) {
            frame.getContentPane().remove(sendFilePanel);
            frame.add(jPanel, BorderLayout.CENTER);
            frame.pack();
            frame.validate();
            System.out.println("3");
            this.currentPanelShowing = panelName;
            return;

        }
        System.out.println("Current Panel showing2 : " + currentPanelShowing);
        /*
         frame.getContentPane().remove(chatCenterPanel);
        frame.add(settingsTabbedPanel, BorderLayout.CENTER);
        frame.pack();
        frame.validate();
         */
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
        sendFilePanel = new SendFilePanel(chatClient);
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
        //showCenterPanel();
        addSelectedPanel(getChatCenterPanel(), "CENTER_PANEL");
        showChannelTreePanel();
    }


    public SendFilePanel getSendFilePanel() {
        return this.sendFilePanel;
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

