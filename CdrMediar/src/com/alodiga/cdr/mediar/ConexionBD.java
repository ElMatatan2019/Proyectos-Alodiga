package com.alodiga.cdr.mediar;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    
    Connection conn = null;
    /**
     * 
     * @return ConnecBd
     */
    /** Se realiza la coneccion con la base de datos a traves de libreria MYsq
     * @return l*/
    public static Connection ConnecBd(String conexion, String user , String password) throws SQLException ,ClassNotFoundException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(conexion, user, password);
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new SQLException();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new ClassNotFoundException();
        }
    }      
}

