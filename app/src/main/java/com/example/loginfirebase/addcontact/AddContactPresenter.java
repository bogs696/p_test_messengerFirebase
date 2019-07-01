package com.example.loginfirebase.addcontact;

import com.example.loginfirebase.addcontact.imvp.IAddContactPresenter;
import com.example.loginfirebase.addcontact.imvp.IAddContactRepository;
import com.example.loginfirebase.addcontact.imvp.IAddContactView;

public class AddContactPresenter implements IAddContactPresenter {
    IAddContactView addContactView;
    IAddContactRepository addContactRepository;
    public AddContactPresenter(IAddContactView addContactView) {
        this.addContactView = addContactView;
        this.addContactRepository = new AddContactRepository(this);
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onDestroy() {
        addContactView = null;
    }

    @Override
    public void addContact(String email) {
        addContactView.hideInput();
        addContactView.showProgress();
        this.addContactRepository.addContact(email);
    }


    @Override
    public void success(boolean error){
        addContactView.hideProgress();
        addContactView.showInput();

        if (error) {
            addContactView.contactNotAdded();
        } else {
            addContactView.contactAdded();
        }
    }
}
