package com.example.hotel;

import com.example.hotel.controller.PersonEditDialogController;
import com.example.hotel.controller.PersonasLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Persona;
import javafx.scene.control.Alert;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    public MainApp() {

    }

    public ObservableList<Persona> getPersonas() {
        return personas;
    }

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
            AnchorPane rootLayout = (AnchorPane) loader.load();

            PersonasLayoutController controller = loader.getController();
            controller.setMainApp(this);

            Scene escena = new Scene(rootLayout);
            primaryStage.setScene(escena);
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Persona persona) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonEditDialogVista.fxml"));

            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Persona");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(persona);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar el diálogo");
            alert.setContentText("Ha ocurrido un error al cargar el diálogo.");

            alert.showAndWait();

            return false;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}