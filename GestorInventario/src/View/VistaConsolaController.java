/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Inventario;
import Model.Consola;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Rober
 */
public class VistaConsolaController {

    @FXML
    private TableView tablaConsolas;
    @FXML
    private TableColumn consolaColumn;
    @FXML
    private TableColumn idColumn;

    @FXML
    private Label nombre;
    @FXML
    private Label marca;
    @FXML
    private Label codigoBarras;
    @FXML
    private Label precio;
    @FXML
    private Label generacion;
    @FXML
    private Label stock;
    @FXML
    private Label fechaAlta;
    @FXML
    private Label fechaUltimaAct;

    // Referencia a la clase principal
    private Inventario inventario;

    //El constructor es llamado ANTES del método initialize
    public VistaConsolaController() {
    }

    //Inicializa la clase controller y es llamado justo después de cargar el archivo FXML
    @FXML
    private void initialize() {

        //Inicializo la tabla con las dos primera columnas
        String nombre = "nombre";
        String id = "id";
        consolaColumn.setCellValueFactory(new PropertyValueFactory(nombre));
        //consolaColumn.setCellValueFactory(new PropertyValueFactory<>(nombre));
        idColumn.setCellValueFactory(new PropertyValueFactory<>(id));

        //Borro los detalles de la persona
        mostrarDetallesConsola(null);

        //Escucho cambios en la selección de la tabla y muestro los detalles en caso de cambio
        tablaConsolas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarDetallesConsola((Consola) newValue));
    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setLibretaConsolas(Inventario inventario) {

        this.inventario = inventario;

        //Añado la lista obervable a la tabla
        tablaConsolas.setItems(inventario.getDatosConsola());
    }

    //Muestra los detalles de la persona seleccionada
    private void mostrarDetallesConsola(Consola consola) {

        if (consola != null) {
            //Relleno los labels desde el objeto persona
            nombre.setText(consola.getNombre());
            marca.setText(consola.getMarca());
            codigoBarras.setText(consola.getCodigoBarras());
            precio.setText(String.valueOf(consola.getPrecio()));
            generacion.setText(consola.getGeneracion());
            stock.setText(String.valueOf(consola.getStock()));
            fechaAlta.setText(consola.getFechaAlta());
            fechaUltimaAct.setText(consola.getFechaUltimaActualizacion());
        } else {
            //Persona es null, vacío todos los labels.
            nombre.setText("");
            marca.setText("");
            codigoBarras.setText("");
            precio.setText("");
            generacion.setText("");
            stock.setText("");
            fechaAlta.setText("");
            fechaUltimaAct.setText("");
        }
    }

    //Borro la persona seleccionada cuando el usuario hace clic en el botón de Borrar
    @FXML
    private void borrarConsola() throws SQLException {
        //Capturo el indice seleccionado y borro su item asociado de la tabla
        int indiceSeleccionado = tablaConsolas.getSelectionModel().getSelectedIndex();
        if (indiceSeleccionado >= 0) {
            //Borro item
            Consola aux = new Consola();
            aux = (Consola) tablaConsolas.getSelectionModel().getSelectedItem();
            System.out.println("Nombre borrada = " + aux.getNombre());
            tablaConsolas.getItems().remove(indiceSeleccionado);
            Inventario.conexionSql.borrar(aux.getNombre());
        } else {
            //Muestro alerta
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Atención");
            alerta.setHeaderText("Consola no seleccionada");
            alerta.setContentText("Por favor, selecciona una consola de la tabla");
            alerta.showAndWait();
        }
    }

    //Muestro el diálogo editar persona cuando el usuario hace clic en el botón de Crear
    @FXML
    private void crearConsola() {
        Consola temporal = new Consola();
        boolean guardarClicked = inventario.muestraEditarConsola(temporal);
        if (guardarClicked) {
            try {
                inventario.getDatosConsola().add(temporal);
                Inventario.conexionSql.putConsola(inventario.getDatosConsola());
            } catch (SQLException ex) {
                Logger.getLogger(VistaConsolaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(VistaConsolaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Muestro el diálogo editar consola cuando el usuario hace clic en el botón de Editar
    @FXML
    private void editarConsola() {
        Consola seleccionada = (Consola) tablaConsolas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            boolean guardarClicked = inventario.muestraEditarConsola(seleccionada);
            if (guardarClicked) {
                mostrarDetallesConsola(seleccionada);
            }

        } else {
            //Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Consola no seleccionada");
            alerta.setContentText("Por favor, selecciona una consola");
            alerta.showAndWait();
        }
    }

    public void generarEtiqueta() {
        Consola seleccionada = (Consola) tablaConsolas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            boolean guardarClicked = inventario.muestraImprimirEtiqueta(seleccionada);
            if (guardarClicked) {
                mostrarDetallesConsola(seleccionada);
            }

        } else {
            //Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Consola no seleccionada");
            alerta.setContentText("Por favor, selecciona una consola");
            alerta.showAndWait();
        }
    }
}
