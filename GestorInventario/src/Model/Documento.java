/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Rober
 */
public class Documento implements Serializable{
    private String path;
    private byte [] archivo;
    private String nombre;
    
    public Documento(String path, String nombre, byte[] archivo){
        this.path = path;
        this.archivo = archivo;
        this.nombre = nombre;
    }
    
    public byte [] getBytes(){
        return archivo;
    }
    
    public String getPath(){
        return path;
    }
    
    public String getNombre(){
        return nombre;
    }
}
