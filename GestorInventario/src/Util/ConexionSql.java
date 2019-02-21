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
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Rober
 */
public class ConexionSql {
    private Connection conexion ;
    int cont = 20;

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

    public List getConsolas() throws SQLException{
        
        Statement stmnt = conexion.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM consolas");

        List<Consola> consolas = new ArrayList<>();
        while (rs.next()) {
            int id = Integer.valueOf(rs.getString("id"));
            String nombre = rs.getString("nombre");
            String marca = rs.getString("marca");
            String codigoBarras = rs.getString("barcode");
            Double precio = rs.getDouble("precio");
            String generacion = rs.getString("generacion");
            int stock = rs.getInt("stock");
            String fechaAlta = rs.getString("fecha_alta");
            String fechaUltimaAct = rs.getString("fecha_mod");
            
            Consola consola = new Consola(id, nombre, marca, codigoBarras, precio, generacion, stock, fechaAlta, fechaUltimaAct);
            consolas.add(consola);
        }
        rs.close();
        stmnt.close();
        return consolas;

    }
    
    public void putConsola(List<Consola> consolas) throws SQLException, Exception{
        
        //Borro todas
        Statement stmnt = conexion.createStatement();
        stmnt.executeUpdate("DELETE FROM consolas");
        stmnt.close();
        //Preparo el statement
        String query = "INSERT INTO consolas (id,nombre,marca,descripcion,precio,stock,fecha_alta,fecha_mod,barcode,slug,activo,imagen,home,generacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(query);
        //Añado cada persona
        for (Consola consola : consolas){
            //EDITAR ORDEN
            System.out.println("Contador " + cont);
            ps.setString(1, String.valueOf(cont));
            ps.setString(2, consola.getNombre());
            ps.setString(3, consola.getMarca());
            ps.setString(4, consola.getDescripcion());
            System.out.println(consola.getPrecio());
            ps.setDouble(5, consola.getPrecio());
            ps.setString(9, consola.getCodigoBarras());
            ps.setString(6, String.valueOf(consola.getStock()));            
            ps.setString(14, consola.getGeneracion());     
            ps.setString(7, consola.getFechaAlta());
            ps.setString(8, consola.getFechaUltimaActualizacion());
            ps.setString(10,null);
            ps.setString(11,null);
            ps.setString(12,null);
            ps.setString(13,null);
            System.out.println("Insertando datos...");
            ps.execute();
            cont++;
            System.out.println("Añadida " + consola.getNombre());
        }
        ps.close();
        System.out.println("Datos guardados en Base de datos");
    }
    
    public void borrar(String nombre) throws SQLException{
        Statement stmnt = conexion.createStatement();
        stmnt.executeUpdate("DELETE FROM consolas WHERE nombre = '" + nombre + "'");
        stmnt.close(); 
    }
    
    public void modificar(Consola consola){
        try {
            String query = "UPDATE consolas SET nombre = '" + consola.getNombre() + "',"
                    + " marca = '" + consola.getMarca() + "', descripcion = '" + consola.getDescripcion() + "',"
                    + " precio = " + consola.getPrecio() +", stock = " + consola.getStock() + ""
                    + ", fecha_alta = '" + consola.getFechaAlta() + "', fecha_mod = '" + consola.getFechaUltimaActualizacion() +"'"
                    + ", barcode = '" + consola.getCodigoBarras() + "', generacion = '" + consola.getGeneracion() + "'"
                    + " WHERE id = " + consola.getId();
            System.out.println(query);
            Statement ps = conexion.prepareStatement(query);
            ps.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
