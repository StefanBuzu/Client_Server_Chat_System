package client;


import model.Message;
import model.User;

import java.beans.PropertyChangeListener;
import java.io.Closeable;
import java.io.IOException;

public interface LoginClient {
    boolean connect(User user) throws IOException;
    boolean message(User user, Message message) throws IOException;
    void close(User username) throws IOException;
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
