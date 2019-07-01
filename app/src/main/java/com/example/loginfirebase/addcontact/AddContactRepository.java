package com.example.loginfirebase.addcontact;

import com.example.loginfirebase.addcontact.imvp.IAddContactPresenter;
import com.example.loginfirebase.lib.FirebaseHelper;
import com.example.loginfirebase.lib.User;
import com.example.loginfirebase.addcontact.imvp.IAddContactRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class AddContactRepository implements IAddContactRepository {

    private IAddContactPresenter addContactPresenter;
    public AddContactRepository(IAddContactPresenter addContactPresenter){
        this.addContactPresenter=addContactPresenter;
    }
    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");

        FirebaseHelper helper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user != null) {
                    boolean online = user.isOnline();
                    FirebaseHelper helper = FirebaseHelper.getInstance();

                    DatabaseReference userContactsReference = helper.getMyContactsReference();
                    userContactsReference.child(key).setValue(online);

                    String currentUserEmailKey = helper.getAuthUserEmail();
                    currentUserEmailKey = currentUserEmailKey.replace(".","_");
                    DatabaseReference reverseUserContactsReference = helper.getContactsReference(email);
                    reverseUserContactsReference.child(currentUserEmailKey).setValue(true);
                } else {
                    addContactPresenter.success(true);
                }
                addContactPresenter.success(false);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {}
        });
    }
}
