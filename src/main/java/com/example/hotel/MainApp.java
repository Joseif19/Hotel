package com.example.hotel;

import com.example.hotel.controller.PersonEditDialogController;
import com.example.hotel.controller.LayoutController;
import com.example.hotel.controller.ReservaDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import javafx.scene.control.Alert;
import model.repository.impl.PersonaRepositoryImpl;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private ObservableList<Persona> personData = FXCollections.observableArrayList();
    private PersonaRepositoryImpl repository = new PersonaRepositoryImpl();
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();

    public MainApp() {
    }

    public ObservableList<Persona> getPersonData() {
        return personData;
    }

    public ObservableList<Reserva> getReservaData() {
        return reservaData;
    }

    public void showPersonOverview() throws ExcepcionPersona {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/LayoutVista.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            LayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.setPersonaRepository(repository);
            personData=controller.descargarPersonas();


            Scene escena = new Scene(personOverview);
            primaryStage.setScene(escena);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws ExcepcionPersona {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel");

        showPersonOverview();
    }

    public boolean showPersonEditDialog(Persona persona) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonEditDialogVista.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            if(persona.getDni() != null){
                dialogStage.setTitle("Editar Persona");
            }else{
                dialogStage.setTitle("Añadir Persona");
            }

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


//    public boolean showReservaEditDialog(Reserva reserva) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/ReservaDialogVista.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//
//            Stage dialogStage = new Stage();
//            if(reserva.getIdReserva() != null){
//                dialogStage.setTitle("Editar Reserva");
//            }else{
//                dialogStage.setTitle("Añadir Reserva");
//            }
//
//            dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            ReservaDialogController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setReserva(reserva);
//
//            dialogStage.showAndWait();
//
//            return controller.isOkClicked();
//        } catch (IOException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("No se pudo cargar el diálogo");
//            alert.setContentText("Ha ocurrido un error al cargar el diálogo.");
//
//            alert.showAndWait();
//
//            return false;
//        }
//    }







    public boolean showReservaEditDialog(Reserva reserva) {
        try {
            // Cargar el archivo FXML para la ventana de reserva
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/ReservaDialogVista.fxml"));
            AnchorPane page = loader.load();

            // Crear una nueva ventana para mostrar la vista de reserva
            Stage dialogStage = new Stage();
            // Verificar si el idReserva es mayor que 0 (o el valor predeterminado para el tipo de datos)
            if (reserva.getIdReserva() > 0) {
                dialogStage.setTitle("Editar Reserva");
            } else {
                dialogStage.setTitle("Añadir Reserva");
            }

            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ReservaVO reservaVO = ReservaUtil.toReservaVO(reserva);

// Ahora pasamos la reserva convertida al controlador


            // Obtener el controlador de la ventana y pasarle la reserva
            ReservaDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            // Convert ReservaVO to Reserva
            reserva = ReservaUtil.toReserva(reservaVO);
            controller.setReserva(reserva);
// Establecer la reserva (vacía o existente)

            // Mostrar la ventana y esperar a que se cierre
            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar el diálogo de reserva");
            alert.setContentText("Ha ocurrido un error al intentar abrir la ventana de reserva.");
            alert.showAndWait();

            return false;
        }
    }






    public Stage getPrimaryStage() {return primaryStage;}

    public static void main(String[] args) {launch(args);}
}
