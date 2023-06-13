package client;

import model.Message;
import model.User;

public class Data {
    private String request;
    private User user;
    private Message message;

    public Data(String request, Message message, User user) {
        this.request = request;
        this.message = message;
        this.user = user;
    }

    public Data(String request,User user){
        this.request = request;
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public Message getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String toString(){
        return ""+user+" "+request+" "+message;
    }
}
