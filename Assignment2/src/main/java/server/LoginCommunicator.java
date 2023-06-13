package server;

import client.Data;
import com.google.gson.Gson;
import model.Message;
import model.User;
import server.logs.FileLog;
import server.model.ServerData;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class LoginCommunicator implements Runnable {
    private final Socket socket;
    private final UDPBroadcaster broadcaster;
    private final Gson gson;
    private final ArrayList<User> users;
    private final ArrayList<Message> messages;
    private final FileLog logs;

    public LoginCommunicator(Socket socket, UDPBroadcaster broadcaster,ArrayList<User> users, ArrayList<Message> messages) {
        this.socket = socket;
        this.broadcaster = broadcaster;
        this.gson = new Gson();
        this.users = users;
        this.messages = messages;
        logs = FileLog.getInstance(new File("C:/Users/crist/IdeaProjects/ViaUniversity/Semester2/Assignment2_ChatSystem/src/main/java/chat/server/logs/Logs.txt"));
    }

    private void communicate() throws IOException {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter output = new PrintWriter(outputStream);

            loop:
            while (true) {
                String request = input.readLine();
                Data data = gson.fromJson(request, Data.class);
                switch (data.getRequest()) {
                    case "connect":
                    {
                        boolean ok = !data.getUser().getUsername().trim().isEmpty();
                        for (User user : users)
                        {
                            if (data.getUser().getUsername().equals(user.getUsername()))
                            {
                                ok = false;
                                break;
                            }
                        }

                        if (ok)
                        {
                            Message message = new Message(data.getUser().getUsername() + " has joined the chat");
                            users.add(data.getUser());
                            messages.add(message);
                            logs.log(message.getMessage());
                        }
                        output.println(ok);
                        output.flush();
                        ServerData serverData = new ServerData("add", users, messages);
                        String json = gson.toJson(serverData);
                        broadcaster.broadcast(json);
                    }
                    case "message":
                    {
                        boolean ok = !data.getMessage().getMessage().trim().isEmpty();
                        System.out.println(data.getUser());
                        messages.add(data.getMessage());
                        logs.log(data.getUser().getUsername() + ": " + data.getMessage().getMessage());
                        output.println(ok);
                        output.flush();
                        ServerData serverData = new ServerData("add", users, messages);
                        String json = gson.toJson(serverData);
                        broadcaster.broadcast(json);
                    }
                    case "exit":
                        Message message = new Message(data.getUser().getUsername() + " has left the chat");
                        messages.add(message);
                        users.remove(data.getUser());
                        logs.log(message.getMessage());
                        ServerData serverData = new ServerData("remove", users, messages);
                        String json = gson.toJson(serverData);
                        broadcaster.broadcast(json);
                        break loop;
                }
            }
        } finally {
            synchronized (broadcaster) {
                socket.close();
            }
        }
    }


    @Override
    public void run()  {
        try
        {
            communicate();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
