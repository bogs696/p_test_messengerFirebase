package com.example.loginfirebase.contactlist.imvp;

import com.example.loginfirebase.lib.User;

public interface IOnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
