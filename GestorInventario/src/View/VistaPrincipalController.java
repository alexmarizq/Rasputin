/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Inventario;
import java.io.File;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

/**
 *
 * @author Rober
 */
public class VistaPrincipalController {
    //Referencia a la clase principal
    private Inventario inventario;

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    //Creo una nueva libreta de direcciones en XML vacía
    @FXML
    private void nuevo() {
        inventario.getDatosConsola().clear();
        inventario.setRutaArchivoConsolas(null);
    }

    //Abro un File Chooser para que el usario seleccione una libreta
    @FXML
    private void abrir() {
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showOpenDialog(inventario.getPrimaryStage());

        if (archivo != null) {
            inventario.cargaConsolas(archivo);
        }
    }

    //Guardar
    @FXML
    private void guardar() throws SQLException {
        File archivo = inventario.getRutaArchivoPersonas();
        if (archivo != null) {
            inventario.guardaConsolas(archivo);
        } else {
            guardarComo();
        }
    }

    //Abro un File Chooser para guardar como
    @FXML
    private void guardarComo() throws SQLException {
        
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showSaveDialog(inventario.getPrimaryStage());

        if (archivo != null) {
            //Me aseguro de que tiene la extensión correcta
            if (!archivo.getPath().endsWith(".xml")) {
                archivo = new File(archivo.getPath() + ".xml");
            }
            inventario.guardaConsolas(archivo);
        }
    }

    //Acerca de
    @FXML
    private void acercaDe() {
        //Muestro alerta
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        alerta.setContentText("Autor: Marco Jakob\nWebsite: http://code.makery.ch\nAdaptación 2018: Jairo García Rincón");
        alerta.showAndWait();
    }

    //Salir
    @FXML
    private void salir() {
        System.exit(0);
    }
    
    //Gráfico
    @FXML
    private void grafico() {
      inventario.crearGrafico();
    }
}
