package com.example.handyman;

public class storingdata {
    String username,mobileno,password,cpassword;
    public storingdata() {
    }

    public storingdata(String username, String mobileno, String password, String cpassword) {
        this.username = username;
        this.mobileno = mobileno;
        this.password = password;
        this.cpassword = cpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
}
