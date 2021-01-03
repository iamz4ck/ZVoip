package network;

import com.google.gson.Gson;
import sound.AudioWriter;
import utilities.ChatClient;

import java.io.DataInputStream;
import java.io.IOException;

public class ServerReader extends Thread {

    private ChatClient chatClient;
    private DataInputStream dataInputStream;
    private boolean isRunning = false;
    private double incomingRMSLevel = 0;
    private Gson gson = new Gson();

    private boolean isSoundMuted = false;

    private State currentState;
    private enum State {
        AUDIO_BYTES, STRING_BYTES
    }

    public ServerReader(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }


    public void run() {
        isRunning = true;
        while(isRunning) {

            int length = 0;

            try{
                length = dataInputStream.readInt();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(length < 0) {
                length = Math.abs(length);
                byte[] incomingBytes = new byte[length];
                try {
                    dataInputStream.readFully(incomingBytes, 0, incomingBytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String incomingJSONString = new String(incomingBytes);
                MessagePacket messagePacket = gson.fromJson(incomingJSONString, MessagePacket.class);
                messagePacket.buildPacket();
                if(messagePacket.packetType.equalsIgnoreCase("SIMPLE_MESSAGE")) {
                    appendToServerMessages(chatClient.getUsername(), messagePacket.simpleMessage);
                }
                if(messagePacket.packetType.equalsIgnoreCase("UTILITY_MESSAGE")) {
                    System.out.println("utility msg received " + messagePacket.jsonData.toString());
                    if(messagePacket.simpleMessage.equalsIgnoreCase("REQUESTED_USERS")) {
                        System.out.println("utility msg: " + messagePacket.utilityMessage.length());
                        if(messagePacket.utilityMessage.length() == 2) {
                            chatClient.getServerWriter().sendUsersConnectedRequest();
                        }
                        if(!messagePacket.utilityMessage.equalsIgnoreCase("{}"))
                            //appendToServerMessages("DEV", messagePacket.utilityMessage);
                            addRequestedUserToAllLists(messagePacket.username);
                    }
                }
            }


		/*	int packetLength = getDataInputStreamInt();
			if(packetLength > 0) {
				setCurrentState(State.AUDIO_BYTES);
			}
			if(packetLength < 0) {
				setCurrentState(State.STRING_BYTES);
			}
			switch (getCurrentState()) {
			case AUDIO_BYTES:

				byte[] audioBuffer = new byte[packetLength];
				audioBuffer = getAudioBytesFromPacket(audioBuffer);
				addIncomingByteArrayToAudioWriter(audioBuffer);

				break;

			case STRING_BYTES:

				int absolutePacketLength = Math.abs(packetLength);
				byte[] stringBuffer = new byte[absolutePacketLength];
				stringBuffer = getStringBytesFromPacket(stringBuffer);
				String incomingPacketStringValue = new String(stringBuffer);
				String packetStringProtocol = getPacketStringProtocol(incomingPacketStringValue);
				String senderUsername = getPacketSenderUsername(incomingPacketStringValue);
				if(packetStringProtocol.contains(NetworkProtocol.MSG)) {
					appendToServerMessages(senderUsername, getPacketStringMessageContents(incomingPacketStringValue));
				}
				if(packetStringProtocol.contains(NetworkProtocol.REQUEST_CONNECTED_USERS_LIST)) {
					addRequestedUserToAllLists(senderUsername);
				}
				if(packetStringProtocol.contains(NetworkProtocol.CLIENT_DISCONNECTION)) {
					annouceSeperateClientDisconnection(senderUsername);
				}

				break;
			} */
        }
    }

    /**
     * Appends client disconnection
     * notification
     * @param usernameDisconnecting
     */
    public void annouceSeperateClientDisconnection(String usernameDisconnecting) {
        appendToServerMessages(usernameDisconnecting, "dissconnected from channel/server...");
        chatClient.getDisplay().getChatCenterPanel().getConnectedUsersPanel().clearAllUsersLists();
        chatClient.getServerWriter().sendUsersConnectedRequest();
    }

    /**
     * Adds username to all channel
     * lists
     * @param usernameToAdd
     */
    public void addRequestedUserToAllLists(String usernameToAdd) {
        chatClient.getDisplay().getChatCenterPanel().getConnectedUsersPanel().addUserToConnectedUserJList(usernameToAdd);
    }

    /**
     * Gets a string representing
     * all message contents in a
     * string packet not including
     * username or protocol
     * @param incomingPacketString
     * @return String packet message contents
     */
    public String getPacketStringMessageContents(String incomingPacketString) {
        return  incomingPacketString.substring(incomingPacketString.indexOf("%") + 1, incomingPacketString.length());
    }

    /**
     * Gets the protocol of incoming
     * string packet
     * @param incomingPacketString
     * @return String protocol
     */
    public String getPacketStringProtocol(String incomingPacketString) {
        return  incomingPacketString.substring(0, 3);
    }

    /**
     * Gets packet sender username
     * @param incomingPacketString
     * @return String packet sender username
     */
    public String getPacketSenderUsername(String incomingPacketString) {
        return incomingPacketString.substring(3, incomingPacketString.length());
    }

    /**
     * Adds byte[] to audiowriter
     * write byte[] arraylist
     * @param audioBuffer
     */
    public void addIncomingByteArrayToAudioWriter(byte[] audioBuffer) {
        getAudioWriter().addBytesToWriteArray(audioBuffer);
    }

    /**
     * Initializes objects needed to run().
     */
    public void init() {
        try {
            dataInputStream = new DataInputStream(chatClient.getSocket().getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Appendes strings to server message
     * area. (Using HTML kit)
     * @param username
     * @param msgContents
     */
    public void appendToServerMessages(String username, String msgContents) {
        String appendableString = username + ": " + msgContents;
        chatClient.getDisplay().getChatCenterPanel().getChatMessagePanel().getServerMessageEditorPane().appendHtml(appendableString, false);
    }

    /**
     * Returns current ServerReader state.
     * AUDIO_BYTES, STRING_BYTES
     * @return State
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Sets State for ServerReader.
     * AUDIO_BYTES, STRING_BYTES
     * @param state
     */
    public void setCurrentState(State state) {
        currentState = state;
    }

    /**
     * Sets isRunning boolean that determines
     * if thread should keep running.
     * @param isRunning
     */
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Returns byte[] of audio sent from
     * Server.
     * @param buffer
     * @return byte[]
     */
    public byte[] getAudioBytesFromPacket(byte[] buffer) {
        try {
            dataInputStream.readFully(buffer, 0, buffer.length);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Returns byte[] of string sent
     * from Server.
     * @param buffer
     * @return
     */
    public byte[] getStringBytesFromPacket(byte[] buffer) {
        try {
            dataInputStream.readFully(buffer, 0, buffer.length);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Returns integer sent from server
     * @return int
     */
    public int getDataInputStreamInt() {
        int length = 0;
        try {
            length = dataInputStream.readInt();
        } catch (IOException e) {
            isRunning = false;
            e.printStackTrace();
        }
        return length;
    }

    /**
     * Returns double representing
     * current RMS sound level.
     * @return double
     */
    public double getCurrentIncomingRMSLevel() {
        return incomingRMSLevel;
    }

    /**
     * Returns instance of AudioWriter
     * class.
     * @return AudioWriter
     */
    public AudioWriter getAudioWriter() {
        return chatClient.getAudioWriter();
    }

    /**
     * Sets if we should mute speakers
     * (does not stop thread)
     * @param isSoundMuted
     */
    public void setIsSoundMuted(boolean isSoundMuted) {
        this.isSoundMuted = isSoundMuted;
    }

    /**
     * Returns false if sound is not
     * muted
     * @return boolean
     */
    public boolean getIsSoundMuted() {
        return isSoundMuted;
    }

}

