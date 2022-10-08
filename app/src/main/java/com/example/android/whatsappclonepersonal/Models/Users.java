package com.example.android.whatsappclonepersonal.Models;

public class Users {
    String profilePic,userName,mail,userId,lastMessage,passWord,status;


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Users(String profilePic, String userName, String mail, String userId, String lastMessage, String passWord) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.mail = mail;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.passWord = passWord;


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId(String key) {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Users(){}
    public Users( String userName, String mail,  String passWord) {

        this.userName = userName;
        this.mail = mail;
        this.passWord = passWord;
    }

    public String getUserId() {
        return null;
    }
}
