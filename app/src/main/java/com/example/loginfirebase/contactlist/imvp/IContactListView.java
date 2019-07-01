package com.example.loginfirebase.contactlist.imvp;
import com.example.loginfirebase.lib.User;
public interface IContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
