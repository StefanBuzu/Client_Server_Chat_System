package model;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        users = new ArrayList<User>();
    }
    public int getSize(){
        return users.size();
    }
    public User getUser(User username){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) == username){
                return users.get(i);
            }
        } return null;
    }
    public void addUser(User user){
        users.add(user);
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public void setUserList(ArrayList<User> userList)
    {
        this.users = userList;
    }
    public void removeUser(User user)
    {
        users.remove(user);
    }
}