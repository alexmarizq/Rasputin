/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JButton;

/**
 * FXML Controller class
 *
 * @author Rober
 */
public class VistaEtiquetasController{

    @FXML
    private TextField numeroCopias;
    
    private Stage escenarioEdicion;
    
    @FXML
    private void initialize() {
    }  

    @FXML
    private void cancelar() {
        escenarioEdicion.close();
    }
    
    @FXML
    private void imprimir(){
        System.out.println(numeroCopias.getText());
    }
}
