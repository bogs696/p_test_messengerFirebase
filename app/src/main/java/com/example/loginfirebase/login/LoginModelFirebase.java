package com.example.loginfirebase.login;

import com.example.loginfirebase.lib.FirebaseHelper;
import com.example.loginfirebase.lib.User;
import com.example.loginfirebase.login.imvp.ILoginModel;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginModelFirebase implements ILoginModel {
    private FirebaseHelper helper;
    private DatabaseReference myUserReference;
    private OnLoginFinishedListener listener;
    public LoginModelFirebase(){
        helper = FirebaseHelper.getInstance();
        myUserReference = helper.getMyUserReference();
    }
    @Override
    public void login(String email, String password, final OnLoginFinishedListener listener) {
        this.listener = listener;
        try {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            myUserReference = helper.getMyUserReference();
                            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    initSignIn(snapshot);
                                }
                                @Override
                                public void onCancelled(DatabaseError firebaseError) {
                                    listener.onCanceled();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            listener.onCanceled();
                        }
                    });
        } catch (Exception e) {
            listener.onCanceled();
        }
    }

    @Override
    public void register(final String email, final String password, final OnLoginFinishedListener listener) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        login(email,password,listener);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onCanceled();
                    }
                });
    }

    private void registerNewUser() {
        String email = helper.getAuthUserEmail();
        if (email != null) {
            User currentUser = new User(email, true, null);
            myUserReference.setValue(currentUser);
        }
    }
    private void initSignIn(DataSnapshot snapshot){
        User currentUser = snapshot.getValue(User.class);

        if (currentUser == null) {
            registerNewUser();
        }
        helper.changeUserConnectionStatus(User.ONLINE);
        listener.onSuccess();
    }
}
