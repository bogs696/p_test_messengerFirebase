package com.example.loginfirebase.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginfirebase.R;
import com.example.loginfirebase.contactlist.ContactListActivity;
import com.example.loginfirebase.login.imvp.ILoginPresenter;
import com.example.loginfirebase.login.imvp.IRegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    @Bind(R.id.r_email) AutoCompleteTextView mEmailView;
    @Bind(R.id.r_password) EditText mPasswordView;
    @Bind(R.id.r_password_repeat) EditText mPasswordRepeatView;
    @Bind(R.id.r_login_progress) View mProgressView;
    @Bind(R.id.r_login_form) View mLoginFormView;
    private ILoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);

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
    @OnClick(R.id.r_login_button)
     void login(){
        finish();
    }
    @OnClick(R.id.r_new_account_button)
     void attemptRegister(){
        mEmailView.setError(null);
        mPasswordView.setError(null);
        showProgress(true);
        presenter.validateRegister(mEmailView.getText().toString(), mPasswordView.getText().toString(),mPasswordRepeatView.getText().toString());
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
        Toast.makeText(RegisterActivity.this,"По данному логину уже существует пользователь",Toast.LENGTH_SHORT).show();
    }
}
