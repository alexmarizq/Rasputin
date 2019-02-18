package Model;

import Util.AdaptadorDeFechas;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Consola {

    private String nombre;
    private String marca;
    private String codigoBarras;
    private Float precio;
    private String generacion;
    private Float stock;
    private ObjectProperty fechaAlta; //fechaLanzamiento
    private ObjectProperty fechaUltimaAct;
    private Image imagen;

    public Consola(){
        
    }
    public Consola(String nombre, String marca, String codigoBarras, Float precio, String generacion, Float stock, ObjectProperty fechaAlta, ObjectProperty fechaUltimaAct, Image imagen) {
        this.nombre = nombre;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.precio = precio;
        this.generacion = generacion;
        this.stock = stock;
        this.fechaAlta = fechaAlta;
        this.fechaUltimaAct = fechaUltimaAct;
        this.imagen = imagen;
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

    public Float getPrecio() {
        return precio;
    }

    public String getGeneracion() {
        return generacion;
    }

    public Float getStock() {
        return stock;
    }

    @XmlJavaTypeAdapter(AdaptadorDeFechas.class)
    public LocalDate getFechaAlta() {
        return (LocalDate) fechaAlta.get();
    }

    public void setFechaAlta(LocalDate fechaDeNacimiento) {
        this.fechaAlta.set(fechaDeNacimiento);
    }

    public ObjectProperty fechaDeAltaProperty() {
        return fechaAlta;
    }
    
     @XmlJavaTypeAdapter(AdaptadorDeFechas.class)
    public LocalDate getFechaUltimaActualizacion() {
        return (LocalDate) fechaUltimaAct.get();
    }

    public void setFechaUltimaActualizacion(LocalDate fechaDeNacimiento) {
        this.fechaUltimaAct.set(fechaDeNacimiento);
    }

    public ObjectProperty fechaFechaUltimaActualizacion() {
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

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    public void setStock(Float stock) {
        this.stock = stock;
    }

    public void setFechaAlta(ObjectProperty fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaUltimaAct(ObjectProperty fechaUltimaAct) {
        this.fechaUltimaAct = fechaUltimaAct;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    
   
}
