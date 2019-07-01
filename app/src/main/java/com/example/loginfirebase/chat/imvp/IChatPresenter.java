package com.example.loginfirebase.chat.imvp;

import com.example.loginfirebase.chat.ChatMessage;

public interface IChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String msg);
    void acceptMessage(ChatMessage msg);


}
