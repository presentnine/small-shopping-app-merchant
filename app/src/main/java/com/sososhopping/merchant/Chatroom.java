package com.sososhopping.merchant;

public class Chatroom {
    String customerName = "";
    String chatroomId = "";

    public Chatroom() {
    }

    public Chatroom(String customerName, String chatroomId) {
        this.customerName = customerName;
        this.chatroomId = chatroomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }
}
