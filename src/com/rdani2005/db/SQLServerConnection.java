package com.rdani2005.db;

import java.sql.SQLException;
import java.sql.DriverManager;

public class SQLServerConnection extends BDConfigs {

    /**
     * Crear una nueva conexion a una base de datos SQL Server en local
     *
     * @param bd seleccionar la base de datos a usar
     * @param user seleccionar el usuario, en el cual se accederá a la base de
     * datos
     * @param password insertar la contraseña de acceso a la base de datos
     */
    public SQLServerConnection(String bd, String user, String password) {

        super(user, password);
        /**
         * TODO: optimizará a forma Remota
         */ 
        setUrl("jdbc:sqlserver://localhost:1433;"
                + "database=" + bd + ";"
                + "user=" + user + ";"
                + "password=" + password + ";"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeOut=30;");

        try {

            cn = DriverManager.getConnection(getUrl());

        } catch (SQLException e) {
            System.out.println("Error en la conexion a SQL Server: " + e);
        }
    }

}
