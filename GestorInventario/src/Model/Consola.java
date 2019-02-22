package Model;

import Util.FechaActual;
import java.util.Calendar;
import javafx.scene.image.Image;

public class Consola {

    private String nombre;
    private int id;
    private String marca;
    private String codigoBarras;
    private double precio;
    private String generacion;
    private String descripcion;
    private int stock;
    private String fechaAlta; //fechaLanzamiento
    private String fechaUltimaAct;
    private Image imagen;
    int cont = 50;

    public Consola() {
        this.id = cont;
        this.nombre = null;
        this.marca = null;
        this.codigoBarras = null;
        this.precio = 0;
        this.generacion = null;
        this.stock = 0;
        this.fechaAlta = FechaActual.getFechaKikote();
        this.fechaUltimaAct = FechaActual.getFechaKikote();
        cont++;
    }

    public Consola(int id, String nombre, String marca, String codigoBarras, double precio, String generacion, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
        this.generacion = generacion;
        this.stock = stock;
        this.fechaAlta = FechaActual.getFechaKikote();
        this.fechaUltimaAct = FechaActual.getFechaKikote();
        this.descripcion = "Caca";
    }
    
    public Consola(int id, String nombre, String marca, String codigoBarras, double precio, String generacion, int stock, String fechaAlta, String fechaUltimaAct) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
        this.generacion = generacion;
        this.stock = stock;
        this.fechaAlta = fechaAlta;
        this.fechaUltimaAct = fechaUltimaAct;
        this.descripcion = "Caca";
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getId(){
        return id;
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
    
    public String getAnoSalida(){
        return fechaAlta.substring(fechaAlta.length()-4);
    }
    
    public String getDescripcion(){
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setId(int id){
        this.id = id;
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
    
    public void setDescripcion (String desc){
        this.descripcion = desc;
    }
}
