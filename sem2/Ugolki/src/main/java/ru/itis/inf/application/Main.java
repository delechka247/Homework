package ru.itis.inf.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.itis.inf.controllers.MainController;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "/fxml/Main.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Ugolki");
        primaryStage.setResizable(false);

        Scene scene = primaryStage.getScene();
        MainController controller = fxmlLoader.getController();
        //scene.setOnKeyPressed(controller.keyEventEventHandler);
        primaryStage.show();
    }

}
