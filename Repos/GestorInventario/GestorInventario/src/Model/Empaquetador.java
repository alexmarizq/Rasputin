package Model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personas") //Define el nombre del elemento raíz XML
public class Empaquetador {
    
    
    private List<Persona> personas;
    
    @XmlElement(name = "persona") //Opcional para el elemento especificado
    public List<Persona> getPersonas(){
        return personas;
    }
    
    public void setPersonas(List<Persona> personas){
        this.personas = personas;
    }
    
}
