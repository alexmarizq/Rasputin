/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Inventario;
import Model.Documento;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
    private void acercaDe() throws SQLException, IOException {
        String rutaPdf = "src\\PDF\\Manual.pdf";
        byte[] buffer = Files.readAllBytes(Paths.get(rutaPdf));
        String ruta = "";
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setDialogTitle("Selecciona el destino");
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
            if ((jfc.showDialog(jfc, "Seleccionar")) == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Operacion Cancelada");
            } else {
                File archivo = jfc.getSelectedFile();
                ruta = archivo.getAbsolutePath();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            System.out.println(ex);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            System.out.println(ex);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println(ex);
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Files.write(Paths.get(ruta, "Manual.pdf"), buffer);

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
