package com.example.loginfirebase;


public class Settings {
    private static Settings instance;

    private int youColorMessage;
    private int interlocutorColorMessage;

    public int getYouColorMessage() {
        return youColorMessage;
    }

    public void setYouColorMessage(int youColorMessage) {
        this.youColorMessage = youColorMessage;
    }

    public int getInterlocutorColorMessage() {
        return interlocutorColorMessage;
    }

    public void setInterlocutorColorMessage(int interlocutorColorMessage) {
        this.interlocutorColorMessage = interlocutorColorMessage;
    }

    private Settings(){
        youColorMessage = R.attr.colorPrimary;
        interlocutorColorMessage = R.attr.colorAccent;
    }

    public static Settings getInstance(){
        if(instance==null){
            instance = new Settings();
        }

        return instance;
    }

}
