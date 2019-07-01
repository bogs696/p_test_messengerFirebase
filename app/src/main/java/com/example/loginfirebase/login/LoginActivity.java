package com.example.loginfirebase.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginfirebase.R;
import com.example.loginfirebase.contactlist.ContactListActivity;
import com.example.loginfirebase.login.imvp.ILoginPresenter;
import com.example.loginfirebase.login.imvp.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private ILoginPresenter loginPresenter;
    @Bind(R.id.email)  AutoCompleteTextView mEmailView;
    @Bind(R.id.password)  EditText mPasswordView;
    @Bind(R.id.login_progress)  View mProgressView;
    @Bind(R.id.login_form)  View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
    }
    @OnClick(R.id.email_sign_in_button)
     void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        showProgress(true);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        loginPresenter.validateLogin(email,password);
    }

    private void showProgress(final boolean showProgress) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mLoginFormView.setVisibility(showProgress ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    showProgress ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(showProgress ? View.GONE : View.VISIBLE);
                }
            });
            mProgressView.setVisibility(showProgress ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    showProgress ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(showProgress ? View.VISIBLE : View.GONE);
                }
            });

    }
    @OnClick(R.id.register_button)
     void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    @Override
    public void setUserNameError(int messageResId) {
        showProgress(false);
        mEmailView.setError(getString(messageResId));
        mEmailView.requestFocus();
    }

    @Override
    public void setPasswordError(int messageResId) {
        showProgress(false);
        mPasswordView.setError(getString(messageResId));
        mPasswordView.requestFocus();
    }

    @Override
    public void successAction() {
        showProgress(false);
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void notSuccess() {
        showProgress(false);
        Toast.makeText(LoginActivity.this,"Неправильный логин или пароль",Toast.LENGTH_SHORT).show();
    }

}

