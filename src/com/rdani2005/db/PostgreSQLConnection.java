package com.rdani2005.db;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection extends BDConfigs {

    /**
     * Crear una nueva conexion a una base de datos PostgreSQL
     *
     * @param bd seleccionar la base de datos a usar
     * @param user seleccionar el usuario, en el cual se accederá a la base de
     * datos
     * @param password insertar la contraseña de acceso a la base de datos
     */
    public PostgreSQLConnection(String user, String password, String bd) {
        super(user, password);

        /**
         * TODO: optimizará a forma Remota
         */
        setUrl("jdbc:postgresql://localhost:5432/" + bd);

        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(getUrl(), user, password);
            System.out.println("Conectada la base de datos!");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexion a base de datos PSQL: " + e);
        }
    }

}
