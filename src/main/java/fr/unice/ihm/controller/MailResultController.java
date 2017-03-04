package fr.unice.ihm.controller;

import fr.unice.ihm.model.MailModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MailResultController {

    @FXML
    private Label mail;

    @FXML
    private Button quitter;

    @FXML
    void onActionQuit(ActionEvent event) {
        Stage stage = (Stage) quitter.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert mail != null : "fx:id=\"mail\" was not injected: check your FXML file 'mail.fxml'.";
        assert quitter != null : "fx:id=\"quitter\" was not injected: check your FXML file 'mail.fxml'.";
    }

    public void setMail(MailModel mailModel) {
        mail.setText(mailModel.buildMessage());
    }
}