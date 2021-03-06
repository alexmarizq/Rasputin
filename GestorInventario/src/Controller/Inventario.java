/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Consola;
import Model.Empaquetador;
import Util.ConexionSql;
import Util.ConexionSsh;
import View.EditarConsolaController;
import View.VistaConsolaController;
import View.VistaEstadisticasController;
import View.VistaEtiquetasController;
import View.VistaPrincipalController;
import com.jcraft.jsch.JSchException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class Inventario extends Application {

    private ObservableList datosConsola = FXCollections.observableArrayList();
    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaConsola, vistaEtiquetas, vistaEstadisticas;
    private Parent editarConsola;
    public static ConexionSql conexionSql;

    //Datos de ejemplo
    public Inventario() {
//        datosConsola.add(new Consola(1, "PS4", "Sony", "123456789", 29, "Primera", 10));
    }

    //Método para devolver los datos como lista observable de personas
    public ObservableList getDatosConsola() {
        return datosConsola;
    }

    @Override
    public void start(Stage escenarioPrincipal) throws SQLException, JSchException {
    
        //Debo hacerlo para que luego me funcione en l carga de escenas
        this.escenarioPrincipal = escenarioPrincipal;

        //Establezco el título
        this.escenarioPrincipal.setTitle("Libreta de direcciones");

        //Establezco el icono de aplicación
        this.escenarioPrincipal.getIcons().add(new Image("file:img/libretaDirecciones.png"));

        //Establezco conexión con la base de datos local
        //conexionSql = new ConexionSql("jdbc:mysql://104.248.240.20:3306/kraa?useSSL=false", "root", "Root1234#");

        //Establezco la conexión SSH
        ConexionSsh conexionSsh = new ConexionSsh();
        
        //Establezco conexión con la base de datos remota
        conexionSql = new ConexionSql("jdbc:mysql://127.0.0.1:" + conexionSsh.puertoAsignado + "/kraa", "root", "Root1234#");
        
        //Inicializo el layout principal
        initLayoutPrincipal();

        //Muestro la vista persona
        muestraVistaPersona();

    }

    public void initLayoutPrincipal() throws SQLException {

        //Cargo el layout principal a partir de la vista VistaPrincipal.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = Inventario.class.getResource("../view/VistaPrincipal.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargo la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);

        //Doy al controlador acceso a la aplicación principal
        VistaPrincipalController controller = loader.getController();
        controller.setInventario(this);

        //Muestro la escena
        escenarioPrincipal.show();

        //Intento cargar el último archivo abierto
        File archivo = getRutaArchivoPersonas();
        if (archivo != null) {
            cargaConsolas(archivo);
        }

        //Cargo personas de la base de datos (borrando las anteriores)
        datosConsola.clear();
        List listaConsolas = conexionSql.getConsolas();
        System.out.println(listaConsolas.size());
        for (int i = 0; i < listaConsolas.size(); i++) {
            datosConsola.add(listaConsolas.get(i));
        }
    }

    public void muestraVistaPersona() {

        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = Inventario.class.getResource("../view/VistaConsola.fxml");
        loader.setLocation(location);
        try {
            vistaConsola = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Añado la vista al centro del layoutPrincipal
        layoutPrincipal.setCenter(vistaConsola);

        //Doy acceso al controlador VistaPersonaCOntroller a LibretaDirecciones
        VistaConsolaController controller = loader.getController();
        controller.setLibretaConsolas(this);

    }

    //Invoco el método getPrimaryStage para que devuelva mi escenario pñrincipal
    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }

    //Vista editarConsola
    public boolean muestraEditarConsola(Consola consola) {

        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = Inventario.class.getResource("../view/EditarConsola.fxml");
        loader.setLocation(location);
        try {
            editarConsola = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioEdicion = new Stage();
        escenarioEdicion.setTitle("Editar Consola");
        escenarioEdicion.initModality(Modality.WINDOW_MODAL);
        escenarioEdicion.initOwner(escenarioPrincipal);
        Scene escena = new Scene(editarConsola);
        escenarioEdicion.setScene(escena);

        //Asigno el escenario de edición y la persona seleccionada al controlador
        EditarConsolaController controller = loader.getController();
        controller.setEscenarioEdicion(escenarioEdicion);
        controller.setConsola(consola);

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioEdicion.showAndWait();

        //devuelvo el botón pulsado
        return controller.isGuardarClicked();

    }
    
    public boolean muestraImprimirEtiqueta(Consola consola) {

        //Cargo la vista persona a partir de VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = Inventario.class.getResource("../view/VistaEtiquetas.fxml");
        loader.setLocation(location);
        try {
            vistaEtiquetas = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioImpresion = new Stage();
        escenarioImpresion.setTitle("Imprimir etiquetas");
        escenarioImpresion.initModality(Modality.WINDOW_MODAL);
        escenarioImpresion.initOwner(escenarioPrincipal);
        Scene escena = new Scene(vistaEtiquetas);
        escenarioImpresion.setScene(escena);

        //Asigno el escenario de edición y la persona seleccionada al controlador
        VistaEtiquetasController controller = loader.getController();
        controller.setEscenarioEdicion(escenarioImpresion);
        controller.setConsola(consola);

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioImpresion.showAndWait();

        //devuelvo el botón pulsado
        return controller.isImprimirClicked();

    }

    //Método main
    public static void main(String[] args) {
        launch(args);
    }

    //Obtengo la ruta del archivo de la preferencias de usuario en Java
    public File getRutaArchivoPersonas() {

        Preferences prefs = Preferences.userNodeForPackage(Inventario.class);
        String rutaArchivo = prefs.get("rutaArchivo", null);
        System.out.println(rutaArchivo);
        if (rutaArchivo != null) {
            return new File(rutaArchivo);
        } else {
            return null;
        }
    }

    //Guardo la ruta del archivo en las preferencias de usuario en Java
    public void setRutaArchivoConsolas(File archivo) {

        Preferences prefs = Preferences.userNodeForPackage(Inventario.class);
        if (archivo != null) {
            //Añado la ruta a las preferencias
            prefs.put("rutaArchivo", archivo.getPath());
            //Actualizo el título del escenario a partir del archivo
            escenarioPrincipal.setTitle("Libreta de direcciones - " + archivo.getName());
        } else {
            //Elimino la ruta de las preferencias
            prefs.remove("rutaArchivo");
            //Actualizo el título del escenario quitando el nombre del archivo
            escenarioPrincipal.setTitle("Libreta de direcciones");
        }

    }

    //Cargo personas de un fichero
    public void cargaConsolas(File archivo) {

        try {
            //Contexto
            JAXBContext context = JAXBContext.newInstance(Empaquetador.class);
            Unmarshaller um = context.createUnmarshaller();

            //Leo XML del archivo y hago unmarshall
            Empaquetador empaquetador = (Empaquetador) um.unmarshal(archivo);

            //Borro los anteriores
            datosConsola.clear();
            datosConsola.addAll(empaquetador.getConsolas());

            //Guardo la ruta del archivo al registro de preferencias
            setRutaArchivoConsolas(archivo);

        } catch (Exception e) {
            //Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se pueden cargar datos de la ruta " + archivo.getPath());
            alerta.setContentText(e.toString());
            alerta.showAndWait();

        }

    }

    //Guardo personas en un fichero
        public void guardaConsolas(File archivo) throws SQLException {

        try {
            
            try {
                //Contexto
                JAXBContext context = JAXBContext.newInstance(Empaquetador.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                
                //Empaqueto los datos de las personas
                Empaquetador empaquetador = new Empaquetador();
                empaquetador.setConsolas(datosConsola);
                
                //Marshall y guardo XML a archivo
                m.marshal(empaquetador, archivo);
                
                //Guardo la ruta delk archivo en el registro
                setRutaArchivoConsolas(archivo);
                
            } catch (Exception e) { // catches ANY exception
                //Muestro alerta
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("No se puede guardar en el archivo " + archivo.getPath());
                alerta.setContentText(e.toString());
                alerta.showAndWait();
            }
            
            //Guardar en la base de datos
            conexionSql.putConsola(datosConsola);
        } catch (Exception ex) {             Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearGrafico() {
        
        //Cargo la vista estadísticas
        FXMLLoader loader = new FXMLLoader();
        URL location = Inventario.class.getResource("/view/VistaEstadisticas.fxml");
        loader.setLocation(location);
        try {
            vistaEstadisticas = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Inicializo un nuevo escenario y asigno el principal
        Stage escenarioEstadisticas = new Stage();        
        escenarioEstadisticas.setTitle("Estadísticas");
        escenarioEstadisticas.initModality(Modality.WINDOW_MODAL);
        escenarioEstadisticas.initOwner(escenarioPrincipal);
        
        //Cargo la escena que contiene ese layout de estadisticas
        Scene escena = new Scene(vistaEstadisticas);
        escenarioEstadisticas.setScene(escena);
                    
        //Asigno el controlador
        VistaEstadisticasController controller = loader.getController();
        controller.setDatosConsola(datosConsola);

        //Muestro el escenario
        escenarioEstadisticas.show();
        
    }

}
