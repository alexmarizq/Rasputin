/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Consola;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Rober
 */
public class VistaEstadisticasController {
    @FXML
    private BarChart<String, Integer> graficoBarras;

    @FXML
    private CategoryAxis ejeX;
    
    @FXML
    private NumberAxis ejeY;

    private ObservableList<String> nombreMeses = FXCollections.observableArrayList();

    //Se invoca justo después de que se ha cargado el archivo FXML
    @FXML
    private void initialize() {
        
        //Array de nombre de meses
        String[] meses = {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
        
        //Los convierto a lista obervable
        nombreMeses.addAll(Arrays.asList(meses));

        //Asigno los nombres de meses a categorías
        ejeX.setCategories(nombreMeses);
        
        //Etiquetas de los ejes
        ejeX.setLabel("Mes de nacimiento");
        ejeY.setLabel("Número de personas");
    }

    //Set mes de cada persona para el eje Y
    public void setDatosConsola(List<Consola> consolas) {
        
        //Array con cantidad de personas por mes de nacimiento
        int[] numMes = new int[12];
        for (Consola c : consolas) {
            int mes = Integer.valueOf(c.getAnoSalida()) - 1;
            numMes[mes]++;
        }

        //Genero la serie
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Mes de nacimiento");
        for (int i = 0; i < numMes.length; i++) {
            series.getData().add(new XYChart.Data<>(nombreMeses.get(i), numMes[i]));
        }

        //Añado la serie al gráfico
        graficoBarras.getData().add(series);
        
    }
}
