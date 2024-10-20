package com.example.bubtforun;

public class UserModel {
    private String username;
    private String userNumber;
    private String userEmail;
    private String userPassword;
    private String userID;
    private String userBio;
    private String userProfile;
    private String userCover;

    public UserModel() {
    }

    public UserModel(String username, String userNumber, String userEmail, String userPassword, String userID, String userBio, String userProfile, String userCover) {
        this.username = username;
        this.userNumber = userNumber;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userID = userID;
        this.userBio = userBio;
        this.userProfile = userProfile;
        this.userCover = userCover;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserCover() {
        return userCover;
    }

    public void setUserCover(String userCover) {
        this.userCover = userCover;
    }

}
