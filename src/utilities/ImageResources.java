package utilities;

import javax.swing.*;
import java.net.URL;

public class ImageResources {

    private Icon closeWindowIcon;
    private Icon frameMainIcon;
    private Icon microphoneMutedIcon;
    private Icon microphoneUnMutedIcon;
    private Icon minimizeFrameIcon;
    private Icon sendFileIcon;
    private Icon settingsIcon;
    private Icon speakerMutedIcon;
    private Icon speakerUnMutedIcon;
    private Icon usersConnectedIcon;
    private Icon messageHomeIcon;
    private Icon tinyKeyboardIcon;
    private Icon tinySpeakerIcon;
    private Icon tinyMicrophoneIcon;

    private URL basicSmileEmoteURL;
    private URL basicStonedSmileEmoteURL;
    private URL mlgDealWithItEmoteURL;
    private URL msgSentIconURL;

    public ImageResources() {
        init();
    }

    public void init() {
        URL closeWindowIconURL = this.getClass().getResource("/buttonIcons/closeWindowIcon.png");
        URL frameMainIconURL = this.getClass().getResource("/buttonIcons/mainIcon.png");
        URL microphoneMutedIconURL = this.getClass().getResource("/buttonIcons/microphoneMuted.png");
        URL microphoneUnMutedURL = this.getClass().getResource("/buttonIcons/microphoneNotMuted.png");
        URL minimizeWindowIconURL = this.getClass().getResource("/buttonIcons/minimizeWindowIcon.png");
        URL sendFileIconURL = this.getClass().getResource("/buttonIcons/sendFile.png");
        URL settingsIconURL = this.getClass().getResource("/buttonIcons/settings.png");
        URL speakerMutedIconURL = this.getClass().getResource("/buttonIcons/speakerMuted.png");
        URL speakerUnMutedIconURL = this.getClass().getResource("/buttonIcons/speakerNotMuted.png");
        URL usersConnectedIconURL = this.getClass().getResource("/buttonIcons/usersConnectedIcon.png");
        URL messageHomeIconURL = this.getClass().getResource("/buttonIcons/messageHome.png");
        URL tinyMicrophoneIconURL = this.getClass().getResource("/buttonIcons/tinyMicrophoneIcon.png");
        URL tinyKeyboardIconURL = this.getClass().getResource("/buttonIcons/tinyKeyboardIcon.png");
        URL tinySpeakerIconURL = this.getClass().getResource("/buttonIcons/tinySpeakerIcon.png");
        closeWindowIcon = new ImageIcon(closeWindowIconURL);
        frameMainIcon = new ImageIcon(frameMainIconURL);
        microphoneMutedIcon = new ImageIcon(microphoneMutedIconURL);
        microphoneUnMutedIcon = new ImageIcon(microphoneUnMutedURL);
        minimizeFrameIcon = new ImageIcon(minimizeWindowIconURL);
        sendFileIcon = new ImageIcon(sendFileIconURL);
        settingsIcon = new ImageIcon(settingsIconURL);
        speakerMutedIcon = new ImageIcon(speakerMutedIconURL);
        speakerUnMutedIcon = new ImageIcon(speakerUnMutedIconURL);
        usersConnectedIcon = new ImageIcon(usersConnectedIconURL);
        messageHomeIcon = new ImageIcon(messageHomeIconURL);
        tinyKeyboardIcon = new ImageIcon(tinyKeyboardIconURL);
        tinySpeakerIcon = new ImageIcon(tinySpeakerIconURL);
        tinyMicrophoneIcon = new ImageIcon(tinyMicrophoneIconURL);
        //end button icons

        //html emotes url//
        basicSmileEmoteURL = this.getClass().getResource("/emoteImages/basicSmileEmote.png");
        basicStonedSmileEmoteURL = this.getClass().getResource("/emoteImages/basicStonedSmileEmote.png");
        mlgDealWithItEmoteURL = this.getClass().getResource("/emoteImages/mlgDealWithItEmote.png");
        msgSentIconURL = this.getClass().getResource("/emoteImages/msgSentIcon.png");

        System.out.print("URL == " + closeWindowIconURL.toString());
    }

    public String getMsgSentIconSourcePath() {
        return msgSentIconURL.toString();
    }

    public Icon getCloseWindowIcon() {
        return closeWindowIcon;
    }

    public Icon getFrameMainIcon() {
        return frameMainIcon;
    }

    public Icon getMicrophoneMutedIcon() {
        return microphoneMutedIcon;
    }

    public Icon getMicrophoneUnMutedIcon() {
        return microphoneUnMutedIcon;
    }

    public Icon getMinimizeFrameIcon() {
        return minimizeFrameIcon;
    }

    public Icon getSendFileIcon() {
        return sendFileIcon;
    }

    public Icon getSettingsIcon() {
        return settingsIcon;
    }

    public Icon getSpeakerMutedIcon() {
        return speakerMutedIcon;
    }

    public Icon getSpeakerUnMutedIcon() {
        return speakerUnMutedIcon;
    }

    public Icon getUsersConnectedIcon() {
        return usersConnectedIcon;
    }

    public Icon getMessageHomeIcon() {
        return messageHomeIcon;
    }

    public String getBasicSmileEmoteSourcePath() {
        return basicSmileEmoteURL.toString();
    }

    public String getBasicStonedSmileEmotePath() {
        return basicStonedSmileEmoteURL.toString();
    }

    public String getMLGDealWithItEmotePath() {
        return mlgDealWithItEmoteURL.toString();
    }

    public Icon getTinyKeyboardIcon() {
        return tinyKeyboardIcon;
    }

    public Icon getTinySpeakerIcon() {
        return tinySpeakerIcon;
    }

    public Icon getTinyMicrophoneIcon() {
        return tinyMicrophoneIcon;
    }



}

