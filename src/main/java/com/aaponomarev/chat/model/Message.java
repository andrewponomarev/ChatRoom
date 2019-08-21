package com.aaponomarev.chat.model;

/**
 * WebSocket message model
 */
public class Message {

    private MessageType type;

    private String username;

    private String msg;

    private Integer onlineCount;

    public Message() {
    }

    public Message(String userName, String msg) {
        this.username = userName;
        this.msg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }
}
