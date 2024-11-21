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
import model.repository.PersonaRepository;
import model.repository.impl.PersonaRepositoryImpl;
import model.ExcepcionPersona;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    // Instancia del repositorio
    private PersonaRepository personaRepository = new PersonaRepositoryImpl();

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
        cargarPersonasDesdeBaseDeDatos();  // Cargar personas de la base de datos
    }

    // Método para cargar las personas desde la base de datos
    private void cargarPersonasDesdeBaseDeDatos() {
        try {
            ArrayList<model.PersonaVO> listaPersonas = personaRepository.ObtenerListaPersonas();
            for (model.PersonaVO personaVO : listaPersonas) {
                // Convierte PersonaVO a Persona (si es necesario)
                Persona persona = new Persona(personaVO.getDni(), personaVO.getNombre(), personaVO.getApellidos(), personaVO.getDireccion(), personaVO.getLocalidad(), personaVO.getProvincia());
                personas.add(persona);
            }
        } catch (ExcepcionPersona e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al cargar personas");
            alert.setContentText("No se ha podido cargar las personas desde la base de datos.");
            alert.showAndWait();
        }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonasLayoutVista.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            // Asegúrate de llamar al método setMainApp aquí
            PersonasLayoutController controller = loader.getController();
            controller.setMainApp(this);  // Pasamos la instancia de MainApp al controlador

            Scene escena = new Scene(rootLayout);
            primaryStage.setScene(escena);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Persona persona) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/example/hotel/vista/PersonEditDialogVista.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Crear un nuevo escenario de diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Persona");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Obtener el controlador del diálogo
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);  // Establecer el escenario del diálogo
            controller.setPerson(persona);           // Pasar la persona que se va a editar

            // Mostrar el diálogo y esperar a que el usuario lo cierre
            dialogStage.showAndWait();

            // Devolver si el usuario hizo clic en OK en el diálogo
            return controller.isOkClicked();
        } catch (IOException e) {
            // Manejo de errores si no se puede cargar el diálogo
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
