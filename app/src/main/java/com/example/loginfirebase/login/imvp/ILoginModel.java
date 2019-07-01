package com.example.loginfirebase.login.imvp;

public interface ILoginModel {
    interface OnLoginFinishedListener {

        void onCanceled();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);
    void register(String username, String password, OnLoginFinishedListener listener);

}
