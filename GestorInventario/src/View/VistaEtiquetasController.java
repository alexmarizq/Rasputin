/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Consola;
import Util.CodigoDeBarras;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rober
 */
public class VistaEtiquetasController
{

    @FXML
    private TextArea numeroCopias;
    
    
    private Stage vistaEtiquetas;
    private Consola consola;
    private boolean imprimirClicked = false;
    
    @FXML
    private void initialize() {
    }  

    @FXML
    private void cancelar() {
        vistaEtiquetas.close();
    }
    
    public void setEscenarioEdicion(Stage vistaEtiquetas) {
        this.vistaEtiquetas = vistaEtiquetas;
    }
    
    public void setConsola(Consola consola){
        this.consola = consola;
    }
    
    public boolean isImprimirClicked() {
        return imprimirClicked;
    }
    
    @FXML
    private void imprimir(){
        CodigoDeBarras.getCode(consola.getCodigoBarras(), Integer.valueOf(numeroCopias.getText()));
        imprimirClicked = true;
        vistaEtiquetas.close();
    }
}
