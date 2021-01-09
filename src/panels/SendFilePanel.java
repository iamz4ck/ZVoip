package panels;

import utilities.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SendFilePanel extends JPanel {

    ChatClient chatClient;
    String selectedFile = "No file selected.";
    long selectedFileSize = 0;
    JButton browseJButton;
    JButton cancelJButton;
    JButton uploadJButton;
    FileChosenDetailsPanel fileChosenDetailsPanel;
    File fileSelected;

    public SendFilePanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        fileChosenDetailsPanel = new FileChosenDetailsPanel(this);
        browseJButton = new JButton("Browse..");
        browseJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int choice = jFileChooser.showOpenDialog(jFileChooser);
                if(choice != JFileChooser.APPROVE_OPTION) return;
                fileSelected = jFileChooser.getSelectedFile();
                selectedFile = fileSelected.toString();
                selectedFileSize = fileSelected.length();

            }
        });
        uploadJButton = new JButton("upload");
        uploadJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        cancelJButton = new JButton("cancel");
        cancelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JPanel panelHolder = new JPanel();
        panelHolder.add(uploadJButton);
        panelHolder.add(browseJButton);
        panelHolder.add(cancelJButton);
        this.add(panelHolder, BorderLayout.NORTH);
        this.add(fileChosenDetailsPanel, BorderLayout.SOUTH);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getParent().getWidth(), 100);
    }

    @Override
    public void paintComponent(Graphics graphics) {
       /* graphics.clearRect(0, 0, getWidth(), getHeight());

        graphics.drawString(selectedFile, 20, 20);

        graphics.drawString(selectedFileSize, 20, 40);

        graphics.dispose();
        repaint(1000);


        */
    }
}
