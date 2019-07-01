package com.example.loginfirebase.login.imvp;

public interface ILoginPresenter {
    public void validateLogin(String email, String password);
    public void validateRegister(String email, String password, String passwordRepeat);
}
