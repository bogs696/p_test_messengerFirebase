package com.example.loginfirebase.login.imvp;

public interface IView {
    void setUserNameError(int messageResId);
    void setPasswordError(int messageResId);
    void successAction();
    void notSuccess();
}
