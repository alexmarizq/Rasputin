package Model;

import Util.FechaActual;
import java.util.Calendar;
import javafx.scene.image.Image;

public class Consola {

    private String nombre;
    private String marca;
    private String codigoBarras;
    private double precio;
    private String generacion;
    private int stock;
    private String fechaAlta; //fechaLanzamiento
    private String fechaUltimaAct;
    private Image imagen;

    public Consola() {
        this.nombre = null;
        this.marca = null;
        this.codigoBarras = null;
        this.precio = 0;
        this.generacion = null;
        this.stock = 0;
        this.fechaAlta = FechaActual.getFecha();
        this.fechaUltimaAct = FechaActual.getFecha();
    }

    public Consola(String nombre, String marca, String codigoBarras, double precio, String generacion, int stock) {
        this.nombre = nombre;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
        this.generacion = generacion;
        this.stock = stock;
        this.fechaAlta = FechaActual.getFecha();
        this.fechaUltimaAct = FechaActual.getFecha();
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public double getPrecio() {
        return precio;
    }

    public String getGeneracion() {
        return generacion;
    }

    public int getStock() {
        return stock;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public String getFechaUltimaActualizacion() {
        return fechaUltimaAct;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaUltimaAct(String fechaUltimaAct) {
        this.fechaUltimaAct = fechaUltimaAct;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
