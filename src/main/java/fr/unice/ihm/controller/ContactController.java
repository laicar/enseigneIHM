package fr.unice.ihm.controller;

import fr.unice.ihm.model.MailModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class ContactController {

    @FXML
    private ChoiceBox<String> civilite;

    @FXML
    private TextField prenom;

    @FXML
    private TextField nom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField objetMail;

    @FXML
    private TextArea corpsMail;

    @FXML
    private Button envoyer;

    @FXML
    private Button annuler;

    MailModel mailModel;

    @FXML
    void cancelMailAction(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void sendMailAction(ActionEvent event) {
        mailModel = new MailModel(buildMailSender(), adresse.getText(), objetMail.getText(), corpsMail.getText());
        //sendMail();
        showMail();
        closeWindow();
    }

    private void sendMail() {
        String to = "carole.lai-tong@etu.unice.fr";
        String from = adresse.getText();

        // Assuming you are sending adresse from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        //properties.setProperty("mail.smtp.host", host);
        properties.setProperty("smtp.gmail.com", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(objetMail.getText());
        message.setText(mailModel.buildMessage());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    private void showMail() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mail.fxml"));

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("E-mail reçu");
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MailResultController controller = loader.getController();
        controller.initialize();
        controller.setMail(mailModel);
        stage.show();
        // Hide this current window (if this is what you want)
        //((Node)(event.getSource())).getScene().getWindow().hide();
    }

    private String buildMailSender() {
        StringBuilder sb = new StringBuilder();
        sb.append(civilite.getSelectionModel().getSelectedItem().toString());
        sb.append(" ");
        sb.append(prenom.getText());
        sb.append(" ");
        sb.append(nom.getText());
        return sb.toString();
    }

    @FXML
    void initialize() {
        assert civilite != null : "fx:id=\"civilite\" was not injected: check your FXML file 'contact.fxml'.";
        assert prenom != null : "fx:id=\"prenom\" was not injected: check your FXML file 'contact.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'contact.fxml'.";
        assert adresse != null : "fx:id=\"adresse\" was not injected: check your FXML file 'contact.fxml'.";
        assert objetMail != null : "fx:id=\"objetMail\" was not injected: check your FXML file 'contact.fxml'.";
        assert corpsMail != null : "fx:id=\"corpsMail\" was not injected: check your FXML file 'contact.fxml'.";
        assert envoyer != null : "fx:id=\"envoyer\" was not injected: check your FXML file 'contact.fxml'.";
        assert annuler != null : "fx:id=\"annuler\" was not injected: check your FXML file 'contact.fxml'.";
        civilite.setItems(FXCollections.observableArrayList("Mlle","Mme","Mr"));
        civilite.getSelectionModel().selectFirst();
    }
}
