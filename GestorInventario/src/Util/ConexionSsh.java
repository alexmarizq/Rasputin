/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.Connection;

/**
 *
 * @author Rober
 */
public class ConexionSsh {
    private static Session sesion = null;
    private static Connection conexion = null;
    String hostSsh = "104.248.240.20";
    String usuarioSsh = "root";
    int puertoSsh = 22; /* en el caso de CEU, usa el 990 */
    String keySsh = "la-ruta-a-tu-archivo-de-par-de-claves";
    String hostRemoto = "127.0.0.1";
    int puertoLocal = 8740; // cualquier puerto vacío para redireccionar
    int puertoRemoto = 3306;
    public int puertoAsignado; //para la conexión sql
    
    public ConexionSsh() throws JSchException{
        
        java.util.Properties config = new java.util.Properties();
        JSch jsch = new JSch();
        
        //Inicio sesión ssh
        sesion = jsch.getSession(usuarioSsh, hostSsh, puertoSsh);
        jsch.addIdentity(keySsh);
        config.put("StrictHostKeyChecking", "no");
        config.put("ConnectionAttempts", "3");
        sesion.setConfig(config);
        sesion.connect();
        System.out.println("SSH conectado");
        
        //Redirección de puerto
        puertoAsignado = sesion.setPortForwardingL(puertoLocal, hostRemoto, puertoRemoto);
        System.out.println("Puerto asignado: "+ puertoAsignado);   
        
    }
    
    public static void cerrar() {
        
        if (sesion != null && sesion.isConnected()) {
            sesion.disconnect();
            System.out.println("SSH desonectado");
        }
    }
}
