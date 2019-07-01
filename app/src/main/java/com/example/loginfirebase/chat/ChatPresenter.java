package com.example.loginfirebase.chat;

import com.example.loginfirebase.lib.User;
import com.example.loginfirebase.chat.imvp.IChatActivity;
import com.example.loginfirebase.chat.imvp.IChatPresenter;
import com.example.loginfirebase.chat.imvp.IChatRepository;

public class ChatPresenter implements IChatPresenter {
    IChatActivity chatView;
    IChatRepository chatRepository;

    public ChatPresenter(IChatActivity chatView){
        this.chatView = chatView;

        chatRepository = new ChatRepository(this);

    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onResume() {
        chatRepository.subscribeForChatUpates();
        chatRepository.changeUserConnectionStatus(User.ONLINE);
    }

    @Override
    public void onPause() {
        chatRepository.unSubscribeForChatUpates();
        chatRepository.changeUserConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onDestroy() {
        chatRepository.destroyChatListener();
        chatView = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        this.chatRepository.setReceiver(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatRepository.sendMessage(msg);
    }


    @Override
    public void acceptMessage(ChatMessage msg){

        chatView.onMessageReceived(msg);
    }

}
