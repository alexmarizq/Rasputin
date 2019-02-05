package Model;

import Util.AdaptadorDeFechas;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Persona {

    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty direccion;
    private final StringProperty ciudad;
    private final IntegerProperty codigoPostal;
    private final ObjectProperty fechaDeNacimiento;

    public Persona() {
        //this(null, null, null, null, 0, null);
        this(null,null);
    }

    public Persona(String nombre, String apellidos) {
        
        /*public Persona(String nombre, String apellidos, String direccion, String ciudad, int codigoPostal, LocalDate fechaDeNacimiento) {

        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleStringProperty(direccion);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.codigoPostal = new SimpleIntegerProperty(codigoPostal);
        this.fechaDeNacimiento = new SimpleObjectProperty(fechaDeNacimiento);

    }*/

        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.direccion = new SimpleStringProperty("aaa");
        this.ciudad = new SimpleStringProperty("bbb");
        this.codigoPostal = new SimpleIntegerProperty(123456);
        this.fechaDeNacimiento = new SimpleObjectProperty(12);

    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public int getCodigoPostal() {
        return codigoPostal.get();
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public IntegerProperty codigoPostalProperty() {
        return codigoPostal;
    }

    @XmlJavaTypeAdapter(AdaptadorDeFechas.class)
    public LocalDate getFechaDeNacimiento() {
        return (LocalDate) fechaDeNacimiento.get();
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento.set(fechaDeNacimiento);
    }

    public ObjectProperty fechaDeNacimientoProperty() {
        return fechaDeNacimiento;
    }

}
