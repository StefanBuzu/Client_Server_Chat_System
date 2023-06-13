package model;

import java.util.ArrayList;

public class MessageList {
    private ArrayList<Message> messages;

    public MessageList(){
        messages = new ArrayList<Message>();
    }
    public Message getMessage(int i){
        return messages.get(i);
    }
    public ArrayList<Message> getMessages(){
        return messages;
    }
    public int getSize(){
        return messages.size();
    }
    public void addMessage(Message message){
        messages.add(message);
    }
    public void setMessageList(ArrayList<Message> messageList)
    {
        this.messages = messageList;
    }
}
