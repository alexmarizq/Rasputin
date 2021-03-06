package Util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rober
 */
public class ConexionSsh {

    private static Session sesion = null;
    private static Connection conexion = null;
    String hostSsh = "104.248.240.20";
    String usuarioSsh = "root";
    int puertoSsh = 22;
    String keySsh = "src\\util\\id_rsa.pem";
    String hostRemoto = "127.0.0.1";
    int puertoLocal = 8740; 
    int puertoRemoto = 3306;
    public int puertoAsignado = 22; 

    public ConexionSsh() {

        try {
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
        } catch (JSchException ex) {
            System.out.println(ex);
            System.out.println("Error conectando con KRAA");
        }

    }

    public static void cerrar() {

        if (sesion != null && sesion.isConnected()) {
            sesion.disconnect();
            System.out.println("SSH desonectado");
        }
    }
}
