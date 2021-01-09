package panels;

import javax.swing.*;
import java.awt.*;

public class FileChosenDetailsPanel extends JPanel {

    SendFilePanel sendFilePanel;

    public FileChosenDetailsPanel(SendFilePanel sendFilePanel) {
        this.sendFilePanel = sendFilePanel;
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getParent().getWidth(), 100);
    }

    public String convertFileSize(long fileSizeInKB) {
       if(fileSizeInKB > 1000) {
           return  fileSizeInKB / 1000 + "KBs";
       }
       return fileSizeInKB + "kbs";
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.clearRect(0, 0, getWidth(), getHeight());

        graphics.drawString(sendFilePanel.selectedFile, 20, 20);

        graphics.drawString(convertFileSize(sendFilePanel.selectedFileSize), 20, 40);

        graphics.dispose();
        repaint(1000);



    }
}
