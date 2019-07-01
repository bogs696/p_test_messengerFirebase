package com.example.loginfirebase.addcontact.imvp;

public interface IAddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void success(boolean error);
}
