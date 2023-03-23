package edu.bip.client.controller;

import edu.bip.client.entity.PublishingEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static edu.bip.client.controller.ApplicationController.publishersData;
import static edu.bip.client.controller.ApplicationController.updatePublisher;

public class EditPublisherController {

    @FXML
    private TextField publisherCity_field;
    @FXML
    private TextField publisherPublisher_field;

    private Stage editPublisherStage;
    private PublishingEntity publishing;
    private int publishingID;
    private boolean okClicked = false;
    private boolean redacting = false;

    public void setDialogStage (Stage dialogStage) {this.editPublisherStage = dialogStage;}

    @FXML
    private void handleCancel() {editPublisherStage.close();}

    @FXML
    private void handleOk() throws IOException {
        publishing.setPublisher(publisherPublisher_field.getText());
        publishing.setCity(publisherCity_field.getText());

        okClicked = true;
        editPublisherStage.close();
        publishersData.set(publishingID, publishing);

        if (redacting = true) {
        updatePublisher(publishing);}
    }

    public void setLabels(PublishingEntity publishingIn, int publisher_id) {
        this.publishing = publishingIn;
        this.publishingID = publisher_id;

        publisherPublisher_field.setText(publishing.getPublisher());
        publisherPublisher_field.setText(publishing.getCity());
    }

    public boolean isOkClicked(){return okClicked;}
    public boolean redacting(){return redacting;}
}
