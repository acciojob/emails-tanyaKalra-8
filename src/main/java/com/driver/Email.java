package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        if(this.password.equals(oldPassword)){
            if(newPassword.length()>=8 && upperCase(newPassword) && lowerCase(newPassword) &&
            digit(newPassword) && specialChar(newPassword)){
                this.setPassword(newPassword);
            }
        }
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
    }

    private boolean digit(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c>='1' && c<='9'){
                return true;
            }
        }
        return false;
    }

    private boolean specialChar(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c>='A' && c<='Z' || c>='a' && c<='z' || c>='1' && c<='9'){

            }
            else{
                return true;
            }
        }
        return false;
    }

    private boolean lowerCase(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c>='a' && c<='z'){
                return true;
            }
        }
        return false;
    }

    private boolean upperCase(String newPassword) {
        for(int i=0;i<newPassword.length();i++){
            char c = newPassword.charAt(i);
            if(c>='A' && c<='Z'){
                return true;
            }
        }
        return false;
    }
}
