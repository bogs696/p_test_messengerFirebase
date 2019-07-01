package com.example.loginfirebase.addcontact.imvp;

public interface IAddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();

}
