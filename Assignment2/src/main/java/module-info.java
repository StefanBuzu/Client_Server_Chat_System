module com.example.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;

    opens model to com.google.gson;
    opens client to com.google.gson;
    opens server to com.google.gson;
    opens com.example.assignment2 to javafx.fxml;
    exports client;
    exports model;
    exports server;
//    exports view;
//    exports viewmodel;
}