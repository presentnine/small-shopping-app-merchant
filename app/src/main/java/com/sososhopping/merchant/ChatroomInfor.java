package com.sososhopping.merchant;

public class ChatroomInfor {
    public String customerName = "";
    public String storeName;
    public String chatroomId = "";
    public String lastMessage;
    public Object lastMessageTimestamp;
    public Object leaveChatroomTimestamp;

    public ChatroomInfor() {
    }

    public ChatroomInfor(String customerName, String storeName, String chatroomId, String lastMessage, Object lastMessageTimestamp, Object leaveChatroomTimestamp) {
        this.customerName = customerName;
        this.storeName = storeName;
        this.chatroomId = chatroomId;
        this.lastMessage = lastMessage;
        this.lastMessageTimestamp = lastMessageTimestamp;
        this.leaveChatroomTimestamp = leaveChatroomTimestamp;
    }
}
