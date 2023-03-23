package edu.bip.client;

import edu.bip.client.controller.ApplicationController;
import edu.bip.client.controller.EditBookController;
import edu.bip.client.controller.EditPublisherController;
import edu.bip.client.entity.BookEntity;
import edu.bip.client.entity.PublishingEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
    launch();
}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Application.class.getResource("view/main.fxml"));
        VBox mainApp = (VBox) loader.load();
        Scene scene = new Scene(mainApp);
        stage.setTitle("джун)");
        stage.setScene(scene);

        ApplicationController controller = loader.getController();
        stage.show();
    }

    public static boolean showPersonEditDialog(BookEntity bookObj, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/editBook.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Book Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditBookController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(bookObj, id);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showPublisherEditDialog(PublishingEntity publishingObj, int id) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("view/editPublisher.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Publisher Editor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditPublisherController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLabels(publishingObj, id);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}