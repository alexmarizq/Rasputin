package View;

import Model.Consola;
import Util.FechaActual;
import Util.UtilidadDeFechas;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarConsolaController {

    //TextField para los campos
    @FXML
    private TextField nombre;
    @FXML
    private TextField marca;
    @FXML
    private TextField codigoBarras;
    @FXML
    private TextField precio;
    @FXML
    private TextField generacion;
    @FXML
    private TextField stock;
    @FXML
    private TextField fechaAlta;
    @FXML
    private TextField fechaUltimaAct;
    //@FXML
    //private TextField imagen;
    private Stage escenarioEdicion; //Escenario de edición
    private Consola consola; // Referencia a la clase persona
    private boolean guardarClicked = false;

    //Inicializa la clase controller y es llamado justo DESPUÉS de cargar el archivo FXML
    @FXML
    private void initialize() {
    }
    //Establece el escenario de edición
    public void setEscenarioEdicion(Stage escenarioEdicion) {
        this.escenarioEdicion = escenarioEdicion;
    }

    //Establece la persona a editar
    public void setConsola(Consola consola) {
        this.consola = consola;

        nombre.setText(consola.getNombre());
        marca.setText(consola.getMarca());
        codigoBarras.setText(consola.getCodigoBarras());
        precio.setText(String.valueOf(consola.getPrecio()));
        generacion.setText(consola.getGeneracion());
        stock.setText(String.valueOf(consola.getStock()));
        fechaAlta.setText(consola.getFechaAlta());
        fechaUltimaAct.setText(consola.getFechaUltimaActualizacion());
        //getImagen()??
        //fechaAlta.setPromptText("dd/mm/yyyy");
        //fechaUltimaAct.setPromptText("dd/mm/yyyy");
    }

    //Devuelve true si se ha pulsado Guardar, si no devuelve false
    public boolean isGuardarClicked() {
        return guardarClicked;
    }

    //LLamado cuando se pulsa Guardar
    @FXML
    private void guardar() {

        if (datosValidos()) {
            //Asigno datos a propiedades de consola
            consola.setNombre(nombre.getText());
            consola.setMarca(marca.getText());
            consola.setCodigoBarras(codigoBarras.getText());
            consola.setPrecio(Double.valueOf(precio.getText()));
            consola.setGeneracion(generacion.getText());
            consola.setStock(Integer.valueOf(stock.getText()));
            consola.setFechaAlta(fechaAlta.getText());
            consola.setFechaUltimaAct(FechaActual.getFecha());

            guardarClicked = true; //Cambio valor booleano
            escenarioEdicion.close(); //Cierro el escenario de edición

        }
    }

    //LLamado cuando se pulsa Cancelar
    @FXML
    private void cancelar() {
        escenarioEdicion.close();
    }

    //Validación de datos
    private boolean datosValidos() {

        //Inicializo string para mensajes
        String mensajeError = "";

        //Compruebo los campos
        if (nombre.getText() == null || nombre.getText().length() == 0) {
            mensajeError += "Nombre no válido.\n";
        }
        if (marca.getText() == null || marca.getText().length() == 0) {
            mensajeError += "Marca no válida.\n";
        }
        if (codigoBarras.getText() == null || codigoBarras.getText().length() != 9) {
            mensajeError += "Código de Barras no válido.\n";
        }

        if (precio.getText() == null || precio.getText().length() == 0) {
            mensajeError += "Precio no válido.\n";
        } else {
            //Convierto el código postal a float
            try {
                Float.valueOf(precio.getText());
            } catch (NumberFormatException e) {
                mensajeError += "Precio no válido (debe ser un float).\n";
            }
        }

        if (generacion.getText() == null || generacion.getText().length() == 0) {
            mensajeError += "Generación no válida.\n";
        }

        if (stock.getText() == null || stock.getText().length() == 0) {
            mensajeError += "Stock no válido.\n";
        } else {
            //Convierto el código postal a float
            try {
                Float.valueOf(stock.getText());
            } catch (NumberFormatException e) {
                mensajeError += "Stock no válido (debe ser un float).\n";
            }
        }

        if (fechaAlta.getText() == null || fechaAlta.getText().length() == 0) {
            mensajeError += "Fecha de nacimiento no válida.\n";
        } else {
            if (!UtilidadDeFechas.fechaValida(fechaAlta.getText())) {
                mensajeError += "Fecha de alta no válida (debe tener formato dd/mm/yyyy).\n";
            }
        }

        //intentar que se actualice automaticamente
        if (fechaUltimaAct.getText() == null || fechaUltimaAct.getText().length() == 0) {
            mensajeError += "Fecha de nacimiento no válida.\n";
        } else {
            if (!UtilidadDeFechas.fechaValida(fechaUltimaAct.getText())) {
                mensajeError += "Fecha de alta no válida (debe tener formato dd/mm/yyyy).\n";
            }
        }
        
        //Si no hay errores devuelvo true, si no, una alerta con los errores y false
        if (mensajeError.length() == 0) {
            return true;
        } else {
            //Muestro alerta y devuelvo false
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Datos no válidos");
            alerta.setContentText("Por favor, corrige los errores");
            alerta.showAndWait();
            return false;
        }

    }

}
