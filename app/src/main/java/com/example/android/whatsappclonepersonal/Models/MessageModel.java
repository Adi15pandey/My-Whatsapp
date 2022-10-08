package com.example.android.whatsappclonepersonal.Models;

public class MessageModel {
    String Uid,message,messageID;
    Long timestamp;


    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public MessageModel(String uid, String message, Long timestamp , String messageID) {
        this.Uid = uid;
        this.message = message;
        this.timestamp = timestamp;
        this.messageID= messageID;
    }

    public MessageModel(String uid, String message) {
        this.Uid = uid;
        this.message = message;
    }
    public MessageModel(){}

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
       this.Uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
