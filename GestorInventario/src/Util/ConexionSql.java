/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Consola;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Rober
 */
public class ConexionSql {
    private Connection conexion ;

    public ConexionSql(String dbURL, String usuario, String password) throws SQLException {
        
        conexion = DriverManager.getConnection(dbURL, usuario, password);
        System.out.println("Base de datos conectada");
        
    }

    public void cerrar() throws SQLException {
        if (conexion != null) {
            conexion.close();
            System.out.println("Base de datos desconectada");
        }
    }

    public List getPersonas() throws SQLException{
        
        Statement stmnt = conexion.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM personas");

        List<Consola> personas = new ArrayList<>();
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String direccion = rs.getString("direccion");
            String ciudad = rs.getString("ciudad");
            int codigoPostal = rs.getInt("codigoPostal");
            java.sql.Date fechaDeNacimiento = rs.getDate("fechaDeNacimiento");
            //Persona persona = new Persona(nombre, apellidos, direccion, ciudad, codigoPostal, fechaDeNacimiento.toLocalDate());
            //personas.add(persona);
        }
        rs.close();
        stmnt.close();
        return personas;

    }
    
    public void putConsola(List<Consola> consolas) throws SQLException{
        
        
        //Borro todas
        Statement stmnt = conexion.createStatement();
        stmnt.executeUpdate("DELETE FROM personas");
        stmnt.close();
        //Preparo el statement
        String query = "INSERT INTO personas (nombre,marca,descripcion,precio,stock,codigBarras,imagen,generacion) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(query);
        //Añado cada persona
        for (Consola consola : consolas){
            //EDITAR ORDEN
            ps.setString(1, consola.getNombre());
            ps.setString(2, consola.getMarca());
            ps.setString(3, consola.getCodigoBarras());
            ps.setString(4, String.valueOf(consola.getPrecio()));
            ps.setString(5, consola.getGeneracion());
            ps.setString(6, String.valueOf(consola.getStock()));
            ps.setDate(7, java.sql.Date.valueOf(consola.getFechaAlta()));
            ps.setDate(8, java.sql.Date.valueOf(consola.getFechaUltimaActualizacion()));
            ps.execute();
        }
        ps.close();
        System.out.println("Datos guardados en Base de datos");
       
        
    }
}
