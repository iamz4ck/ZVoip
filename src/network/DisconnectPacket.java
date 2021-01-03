package network;

import com.google.gson.JsonObject;

public class DisconnectPacket {

    private JsonObject jsonData;

    private String loginState;
    private String packetType;
    private String username;


    public DisconnectPacket(String loginState, String packetType, String username) {
        this.loginState = loginState;
        this.packetType = packetType;
        this.username = username;
        buildPacket();
        System.out.println(jsonData.toString());
    }

    public void buildPacket() {
        jsonData = new JsonObject();
        jsonData.addProperty("loginState", this.loginState);
        jsonData.addProperty("packetType", this.packetType);
        jsonData.addProperty("username", username);
    }
}
