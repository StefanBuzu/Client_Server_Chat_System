package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.User;
import viewmodel.StartViewModel;

import javax.swing.plaf.synth.Region;
import java.awt.*;

public class StartViewController {
    @FXML public TextField userText;
    @FXML public Button submitButton;

    private ViewHandler viewHandler;
    private StartViewModel startViewModel;
    private Region root;

    @FXML public void onEnterName()
    {
        boolean ok = false;
        if (!userText.getText().trim().isEmpty())
        {
            ok = startViewModel.addUser(new User(userText.getText()));
        }

        if (ok)
        {
            viewHandler.openView(ViewHandler.CHAT);
            helloViewModel.reset();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty name or name already taken");
            alert.showAndWait();
            helloViewModel.reset();
        }
    }

    public void init(ViewHandler viewHandler, HelloViewModel helloViewModel, javafx.scene.layout.Region root)
    {
        this.viewHandler = viewHandler;
        this.helloViewModel = helloViewModel;
        this.root = root;

        this.helloViewModel.bindName(nameInput.textProperty());
    }

    public javafx.scene.layout.Region getRoot()
    {
        return root;
    }
}
