package com.example.hotel;

import com.example.hotel.controller.PersonasLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel");

        initRootLayout();

    }

    public void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonasLayoutVista.fxml"));
            rootLayout = (AnchorPane) loader.load();

            PersonasLayoutController controller = loader.getController();
            controller.setMainApp(this);

            Scene escena = new Scene(rootLayout);
            primaryStage.setScene(escena);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}