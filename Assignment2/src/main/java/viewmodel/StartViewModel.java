package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ModelManager;
import model.User;

public class StartViewModel {
    private final ModelManager modelManager;
    private final SimpleStringProperty name;
    public StartViewModel(ModelManager instance){
        this.modelManager = instance;
        this.name = new SimpleStringProperty("");
    }
    public void bindName(StringProperty property){
        property.bindBidirectional(name);
    }
    public boolean addUser(User user){
        return modelManager.(user);
    }
    public void reset(){
        name.set("");
    }
}
