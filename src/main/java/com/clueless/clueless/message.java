package com.clueless.clueless;

public class message {
    String destination;
    String message;

    public message(String dest, String msg){
        destination = dest;
        message = msg;
    }
    public String getMsg(){
        return message;
    }
    public String getDest(){
        return destination;
    }
}
