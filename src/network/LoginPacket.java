package network;

import com.google.gson.JsonObject;

public class LoginPacket {

    private JsonObject jsonData;

    private String loginState;
    private String packetType;
    private String username;
    private String password;
    private String dateTime;


    public LoginPacket(String loginState, String packetType, String username, String password, String dateTime) {
        this.loginState = loginState;
        this.packetType = packetType;
        this.username = username;
        this.password = password;
        this.dateTime = dateTime;
        buildPacket();
        System.out.println(jsonData.toString());
    }

    public void buildPacket() {
        jsonData  = new JsonObject();
        jsonData.addProperty("loginState", this.loginState);
        jsonData.addProperty("packetType", this.packetType);
        jsonData.addProperty("username", this.username);
        jsonData.addProperty("password", this.password);
        jsonData.addProperty("dateTime", this.dateTime);
    }

    public String getJSONString() {
        return jsonData.toString();
    }


}
