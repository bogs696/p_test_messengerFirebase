package com.example.loginfirebase.login;

import android.text.TextUtils;

import com.example.loginfirebase.R;
import com.example.loginfirebase.login.imvp.ILoginModel;
import com.example.loginfirebase.login.imvp.ILoginPresenter;
import com.example.loginfirebase.login.imvp.IView;

public class LoginPresenter  implements ILoginPresenter, ILoginModel.OnLoginFinishedListener {
    IView loginView;
    ILoginModel loginModel;
    public LoginPresenter(IView loginView){
        this.loginView=loginView;
        loginModel=new LoginModelFirebase();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(TextUtils.isEmpty(email) ||!isEmailValid(email)){
            loginView.setUserNameError(R.string.error_invalid_email);
            return;
        }
        if(TextUtils.isEmpty(password)||!isPasswordValid(password)){
            loginView.setPasswordError(R.string.error_invalid_password);
            return;
        }
        loginModel.login(email, password, this);
    }
    @Override
    public void validateRegister(String email, String password, String passwordRepeat){
        if(TextUtils.isEmpty(email) ||!isEmailValid(email)){
            loginView.setUserNameError(R.string.error_invalid_email);
            return;
        }
        if(TextUtils.isEmpty(password)||!isPasswordValid(password)){
            loginView.setPasswordError(R.string.error_invalid_password);
            return;
        }
        if(TextUtils.isEmpty(passwordRepeat)||!password.equals(passwordRepeat)){
            loginView.setPasswordError(R.string.error_invalid_password);
            return;
        }
        loginModel.register(email,password,this);

    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public void onCanceled() {
        loginView.notSuccess();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError(R.string.error_invalid_password);
    }

    @Override
    public void onSuccess() {
        loginView.successAction();
    }
}
