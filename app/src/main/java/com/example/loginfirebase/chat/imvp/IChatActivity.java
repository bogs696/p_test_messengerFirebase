package com.example.loginfirebase.chat.imvp;

import com.example.loginfirebase.chat.ChatMessage;

public interface IChatActivity {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
