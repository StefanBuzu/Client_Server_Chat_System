package viewmodel;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import model.Message;
import model.ModelManager;
import model.User;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatViewModel implements PropertyChangeListener {
    private final ModelManager modelManager;
    private final SimpleListProperty<User> users;
    private final SimpleListProperty<Message> messages;
    private final SimpleStringProperty message;
    private final PropertyChangeSupport support;

    public ChatViewModel(ModelManager instance){
        this.modelManager = instance;
        this.message = new SimpleStringProperty("");
        this.users = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.messages = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.support = new PropertyChangeSupport(this);

        this.modelManager.add
    }

    public boolean addMessage(User user, String text){
        Message message = new Message(user,text);
        return modelManager.add
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
