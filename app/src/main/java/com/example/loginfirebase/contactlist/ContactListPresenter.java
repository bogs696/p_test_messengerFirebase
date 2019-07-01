package com.example.loginfirebase.contactlist;

import com.example.loginfirebase.contactlist.imvp.*;
import com.example.loginfirebase.lib.User;

public class ContactListPresenter implements IContactListPresenter {
    IContactListView contactListView;
    IContactListRepository contactListRepository;

    public ContactListPresenter(IContactListView contactListView){
        this.contactListView = contactListView;
        contactListRepository = new ContactListRepository(this);
    }

    @Override
    public void signOff() {
        contactListRepository.changeUserConnectionStatus(User.OFFLINE);
        contactListRepository.destroyContactListListener();
        contactListRepository.unSubscribeForContactListUpdates();
        contactListRepository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return contactListRepository.getCurrentEmail();
    }

    @Override
    public void removeContact(String email) {
        contactListRepository.removeContact(email);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onResume() {
        contactListRepository.changeUserConnectionStatus(User.ONLINE);
        contactListRepository.subscribeForContactListUpdates();
    }

    @Override
    public void onPause() {
        contactListRepository.changeUserConnectionStatus(User.OFFLINE);
        contactListRepository.unSubscribeForContactListUpdates();
    }

    @Override
    public void onDestroy() {
        contactListRepository.destroyContactListListener();
        contactListView = null;
    }

    @Override
    public void onContactAdded(User user) {
        if (contactListView != null) {
            contactListView.onContactAdded(user);
        }
    }
    @Override
    public void onContactChanged(User user) {
        if (contactListView != null) {
            contactListView.onContactChanged(user);
        }
    }
    @Override
    public void onContactRemoved(User user) {
        if (contactListView != null) {
            contactListView.onContactRemoved(user);
        }
    }
}
