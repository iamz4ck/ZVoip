package panes;

import network.NetworkProtocol;
import utilities.ChatClient;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.IOException;

public class ServerMessagesEditorPane extends JEditorPane {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private HTMLEditorKit htmlEditorKit;
    private HTMLDocument htmlDocument;
    private ChatClient chatClient;

    //Emote sources
    private String basicSmileEmoteSRC;
    private String basicStonedSmileEmoteSRC;
    private String mlgDealWithItEmoteSRC;
    private String msgRecievedSRC;
    //



    //html tags
    private String htmlTag = "<html>";
    private String bodyTag = "<body>";
    private String endHtmlTag = "</html>";
    private String endBodyTag = "</body>";
    private String italicFontTag = "<i>";
    private String italicFontEndTag = "</i>";
    private String imgTag = "<img src =";
    private String endImgTag = "></img>";
    private String colorFontTagRed = "<font color= \"red\">";
    private String colorFontTagGray = "<font color= \"gray\">";
    private String endColorFontTag = "</font>";
    //

    private String msgRecievedTag;

    public ServerMessagesEditorPane(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
        htmlEditorKit = new HTMLEditorKit();
        htmlDocument = new HTMLDocument();
        this.setEditorKit(htmlEditorKit);
        this.setDocument(htmlDocument);
    }



    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    public void init() {
        basicSmileEmoteSRC = chatClient.getImageResources().getBasicSmileEmoteSourcePath();
        basicStonedSmileEmoteSRC = chatClient.getImageResources().getBasicStonedSmileEmotePath();
        mlgDealWithItEmoteSRC = chatClient.getImageResources().getMLGDealWithItEmotePath();
        msgRecievedSRC = chatClient.getImageResources().getMsgSentIconSourcePath();
        msgRecievedTag = imgTag + "'" + msgRecievedSRC + "'" + endImgTag;
        setEditable(false);
    }

    /**
     * Uses HTML Editor Kit to append messages
     * Recieved from the server
     * @param string
     * @param isWhisper
     */
    public void appendHtml(String string, boolean isWhisper) {
        if(string.length() >= 3) {
            if(string.substring(0, 3).contains(NetworkProtocol.CONNECTION_SUCCESFUL)) {
                int connectedUserNameLength = Integer.parseInt(string.substring(3, 4));
                String connectedUserName = string.substring(4, 4 + connectedUserNameLength);
                String msgContents = string.substring(4 + connectedUserNameLength + 1, string.length());
                string = colorFontTagRed + connectedUserName + " " + msgContents + endColorFontTag;
            }
        }
        String basicSmileCheck = NetworkProtocol.basicSmileIdentifier.replaceAll("\\\\", "");
        if(string.contains(basicSmileCheck)) {
            String basicSmileEmoteHTML = "<img src=" + "'" + basicSmileEmoteSRC + "'" +"></img>";
            string = string.replaceAll(NetworkProtocol.basicSmileIdentifier, basicSmileEmoteHTML);
        }
        if(string.contains(NetworkProtocol.basicStonedSmileIdentifier)) {
            String basicStonedSmileEmoteHTML = imgTag + "'" + basicStonedSmileEmoteSRC + "'" + endImgTag;
            string = string.replaceAll(NetworkProtocol.basicStonedSmileIdentifier, basicStonedSmileEmoteHTML);
        }
        if(string.contains(NetworkProtocol.mlgDealWithItSmileIdentifier)) {
            String mlgDealWithItEmoteHTML = imgTag + "'" + mlgDealWithItEmoteSRC + "'" + endImgTag;
            string = string.replaceAll(NetworkProtocol.mlgDealWithItSmileIdentifier, mlgDealWithItEmoteHTML);
        }
        string = string.replaceAll("\\\\", "");
        if(string.length() > 1) {
            string = msgRecievedTag + string;
        }
        if(isWhisper) {
            string = italicFontTag + colorFontTagGray + string + endColorFontTag + italicFontEndTag;
        }
        try {
            htmlEditorKit.insertHTML(htmlDocument, htmlDocument.getLength(), string, 0, 0, null);
            setCaretPosition(htmlDocument.getLength());
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Returns String representation of
     * a new line
     * @return String
     */
    public String getNewLine() {
        return System.getProperty("line.separator");
    }

}

