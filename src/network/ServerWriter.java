package network;

import utilities.ChatClient;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerWriter extends Thread {

    private DataOutputStream dataOutputStream;
    private ChatClient chatClient;

    public ServerWriter(ChatClient chatClient) {
        this.chatClient = chatClient;
        init();
    }

    @Override
    public void run() {
        sendUsernameToServer();

        sendUsersConnectedRequest();
    }

    /**
     * Initializes objects need to write
     * to socket.
     */
    public void init() {
        try {
            dataOutputStream = new DataOutputStream(chatClient.getSocket()
                    .getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Sends String command to server
     * requesting list of connected users.
     */
    public void sendUsersConnectedRequest() {
        //sendStringBytesToServer(NetworkProtocol.REQUEST_CONNECTED_USERS_LIST, "");
        MessagePacket messagePacket = new MessagePacket("LOGGED_IN", "UTILITY_MESSAGE", "DEV", "GENERAL", "REQUESTED_USERS", "CLIENT_USER_REQUEST");
        sendMessagePacketToServer(messagePacket);
    }

    /**
     * Configures String to be sent to server
     * with correct string byte[] protocol
     * @param protocol
     * @param msgContents
     */
    public void sendStringBytesToServer(String protocol, String msgContents) {
        String configuredStringToSend = protocol + " " + "$" + chatClient.getUsername() + "%" + msgContents;
        System.out.println("sending string: " + configuredStringToSend);
        byte[] configuredBytesToSend = configuredStringToSend.getBytes();
        int configuredBytesLength = configuredBytesToSend.length;
        int intToWriteToServer = configuredBytesLength - (configuredBytesLength * 2);
        try {
            dataOutputStream.writeInt(intToWriteToServer);
            dataOutputStream.write(configuredBytesToSend, 0, configuredBytesLength);
            dataOutputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendMessagePacketToServer(MessagePacket messagePacket) {
        messagePacket.buildPacket();
        byte[] convertedJSON = messagePacket.jsonData.toString().getBytes();
        int intToWrite = convertedJSON.length - (convertedJSON.length * 2);
        try {
            dataOutputStream.writeInt(intToWrite);
            dataOutputStream.write(convertedJSON, 0, convertedJSON.length);
            dataOutputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendLoginPacketToServer(LoginPacket loginPacket) {
        byte[] convertedJSON = loginPacket.getJSONString().getBytes();
        int intToWrite = convertedJSON.length - (convertedJSON.length * 2);
        try {
            dataOutputStream.writeInt(intToWrite);
            dataOutputStream.write(convertedJSON, 0, convertedJSON.length);
            dataOutputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    /**
     * Sends byte[] of audio to server.
     * @param
     */
    public void sendAudioBytesToServer(byte[] audioData) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    dataOutputStream.writeInt(audioData.length);
                    dataOutputStream.write(audioData, 0, audioData.length);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Sends set username to Server
     * (Should only be sent once)
     */
    public void sendUsernameToServer() {
        //sendStringBytesToServer(NetworkProtocol.SEND_USERNAME, "");
        LoginPacket loginPacket = new LoginPacket("NEED_AUTH", "NEED_AUTH", chatClient.getUsername(), "welovetosnoke", "4:20pm");
        //sendStringBytesToServer(loginPacket.getJSONString(), "^^^^^^");
        sendLoginPacketToServer(loginPacket);
    }

    /**
     * Sends String to server confirming
     * client disconnection for graceful
     * disconnection server side.
     */
    public void sendDisconnectionCommand() {




	/*	try {
			dataOutputStream.writeInt(-1);
			dataOutputStream.write(NetworkProtocol.CLIENT_DISCONNECTION.getBytes(), 0, NetworkProtocol.CLIENT_DISCONNECTION.getBytes().length);
			dataOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
    }
}

