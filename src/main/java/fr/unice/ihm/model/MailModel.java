package fr.unice.ihm.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class MailModel {
    private StringProperty sender;
    private StringProperty senderAddress;
    private StringProperty mailObject;
    private StringProperty mailBody;

    public MailModel(String sender, String senderAddress, String mailObject, String mailBody) {
        this.sender = new SimpleStringProperty(sender);
        this.senderAddress = new SimpleStringProperty(senderAddress);
        this.mailObject = new SimpleStringProperty(mailObject);
        this.mailBody = new SimpleStringProperty(mailBody);
    }

    public String buildMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("De ");
        sb.append(sender.getValue());
        sb.append("\nE-mail: ");
        sb.append(senderAddress.getValue());
        sb.append("\nObjet: ");
        sb.append(mailObject.getValue());
        sb.append("\nMail:\n");
        sb.append(mailBody.getValue());
        return sb.toString();
    }
}
