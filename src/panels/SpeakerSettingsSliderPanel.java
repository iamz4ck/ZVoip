package panels;

import utilities.ChatClient;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpeakerSettingsSliderPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ChatClient chatClient;
    private JSlider volumeSlider;
    private JComboBox<String> avaliablePlaybackDeviceComboBox;

    public SpeakerSettingsSliderPanel(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    public void init() {
        volumeSlider = configureVolumeSlider();
        avaliablePlaybackDeviceComboBox = configureSpeakerComboBox();
        setLayout(new BorderLayout());
        this.add(volumeSlider, BorderLayout.NORTH);
        this.add(avaliablePlaybackDeviceComboBox, BorderLayout.CENTER);

    }

    /**
     * Creates JComboBox listing all
     * audio mixers available.
     * @return
     */
    public JComboBox<String> configureSpeakerComboBox() {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        ArrayList<Line.Info> avaliableLines = new ArrayList<Line.Info>();
        JComboBox<String> comboBox = new JComboBox<String>();
        for(Mixer.Info info: mixers) {
            if(info.getName().contains("Speaker") || info.getName().contains("Headphones")) {
                comboBox.addItem(info.getName());
            }
            Mixer currentMixer = AudioSystem.getMixer(info);
            Line.Info[] lines = currentMixer.getTargetLineInfo();
            for(Line.Info lineInfo: lines) {
                avaliableLines.add(lineInfo);
            }
        }
        return comboBox;
    }

    /**
     * Creates JSlider representing
     * volume on line setting.
     * @return
     */
    public JSlider configureVolumeSlider() {
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, -6, 6, 3);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        return slider;
    }

    /**
     * Returns current JSlider volume
     * setting
     * @return
     */
    public int getVolumeSliderValue() {
        return volumeSlider.getValue();
    }

}

