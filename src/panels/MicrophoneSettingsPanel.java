package panels;

import sound.MicrophoneCapture;
import utilities.ChatClient;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MicrophoneSettingsPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;
    private ClientNoiseGateSliderPanel clientNoiseGateSliderPanel;
    private JComboBox microphoneListJComboBox;
    private JButton applyJButton;
    private MicrophoneCapture microphoneCapture;
    private ArrayList<Line.Info> avaliableLines;


    public MicrophoneSettingsPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        this.microphoneCapture = chatClient.getMicrophoneCapture();
        init();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 230);
    }

    public void init() {
        clientNoiseGateSliderPanel = new ClientNoiseGateSliderPanel(chatClient);
        microphoneListJComboBox = configureMicrophoneComboBox();
        applyJButton = new JButton("Apply");
        applyJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected JComboItem: " + microphoneListJComboBox.getSelectedIndex());
                System.out.println("corresponding line info: " + avaliableLines.get(microphoneListJComboBox.getSelectedIndex()));
                //microphoneCapture.setupSelectedMicrophoneLine(avaliableLines.get(microphoneListJComboBox.getSelectedIndex()));
                chatClient.getMicrophoneCapture().setupSelectedMicrophoneLine(avaliableLines.get(microphoneListJComboBox.getSelectedIndex()));

            }
        });
        this.setLayout(new BorderLayout());
        JPanel buttonPanelHolder = new JPanel();
        buttonPanelHolder.add(applyJButton);
        buttonPanelHolder.add(microphoneListJComboBox);
        this.add(buttonPanelHolder, BorderLayout.NORTH);
        this.add(clientNoiseGateSliderPanel, BorderLayout.CENTER);

    }



    /**
     * Returns ChatClient
     * @return ChatClient
     */
    public ChatClient getChatClient() {
        return chatClient;
    }

    /**
     * Returns client noise gate panel holding
     * JSlider noise gate
     * @return
     */
    public ClientNoiseGateSliderPanel getClientNoiseGateSliderPanel() {
        return clientNoiseGateSliderPanel;
    }

    /**
     * Creates JComboBox listing all
     * audio mixers available.
     * @return
     */
    public JComboBox<String> configureMicrophoneComboBox() {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        avaliableLines = new ArrayList<Line.Info>();
        JComboBox<String> comboBox = new JComboBox<String>();
        int i = 0;
        for(Mixer.Info info: mixers) {
            if(info.getName().contains("Microphone") || info.getName().contains("Output")) {
                comboBox.addItem(info.getName() + " item selection: " + i++);
            }
            Mixer currentMixer = AudioSystem.getMixer(info);
            Line.Info[] lines = currentMixer.getTargetLineInfo();
            for(Line.Info lineInfo: lines) {
                avaliableLines.add(lineInfo);
            }
        }
        return comboBox;
    }

}
