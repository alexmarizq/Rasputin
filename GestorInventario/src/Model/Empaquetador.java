package Model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "consolas") //Define el nombre del elemento raíz XML
public class Empaquetador {
    
    
    private List<Consola> consolas;
    
    @XmlElement(name = "persona") //Opcional para el elemento especificado
    public List<Consola> getConsolas(){
        return consolas;
    }
    
    public void setConsolas(List<Consola> consolas){
        this.consolas = consolas;
    }
    
}
