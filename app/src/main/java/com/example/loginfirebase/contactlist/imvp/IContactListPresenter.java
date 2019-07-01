package com.example.loginfirebase.contactlist.imvp;

import com.example.loginfirebase.lib.User;

public interface IContactListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
