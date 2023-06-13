package client;

import com.google.gson.Gson;
import model.UserList;
import model.Message;
import model.User;
import server.model.ServerData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;

public class LoginClientImplementation implements LoginClient {
    private static final String EXIT_JSON = """
            {"operator":"exit"}
            """;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader input;
    private Gson gson;

    private PropertyChangeSupport support;
    private MessageListener listener;

    public LoginClientImplementation(String host, int port, String groupAddress, int groupPort) throws IOException {
        this.socket = new Socket(host, port);
        this.writer = new PrintWriter(socket.getOutputStream());
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.gson = new Gson();
        this.support = new PropertyChangeSupport(this);

        listener = new MessageListener(this, groupAddress, groupPort);
        Thread thread = new Thread(listener);
        thread.start();
    }


    public boolean connect(User user) throws IOException {
        Data data = new Data("connect", user);
        String json = gson.toJson(data);
        writer.println(json);
        out.flush();
        String resultJson = input.readLine();
        return gson.fromJson(resultJson, boolean.class);
    }

    public boolean message(User user, Message message) throws IOException {
        Data data = new Data("chat", message, user);
        String json = gson.toJson(data);
        out.println(json);
        out.flush();
        String resultJson = input.readLine();
        return gson.fromJson(resultJson, boolean.class);
    }

    public void close(User user) throws IOException {
        Data data = new Data("exit", user);
        String json = gson.toJson(data);
        out.println(json);
        out.flush();
        listener.close();
        socket.close();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void receiveBroadcast(String message) {
        ServerData serverData = gson.fromJson(message, ServerData.class);
        if (serverData.getResponse().equals("add")) {
            support.firePropertyChange("data", null, serverData);
        } else if (serverData.getResponse().equals("remove")) {
            support.firePropertyChange("close", null, serverData);
        }
    }

}
