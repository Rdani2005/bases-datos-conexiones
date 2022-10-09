package com.rdani2005.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;

public class BDConfigs {

    /**
     * Metodo para retornar la url a la base de datos
     *
     * @return url, la cual es la url de la base de datos
     */
    public String getUrl() {
        return url;
    }

    /**
     * Metodo para cambiar la url de la base de datos
     *
     * @param url funciona como la nueva url de la base de datos
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Crear una conexion segura a la base de datos de selección
     *
     * @param user usuario de la base de datos
     * @param password contraseña del usuario, usado para permitir una conexion
     */
    public BDConfigs(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Funcion que retorna un valor booleano, indicando si la sentencia fue
     * correcta o incorrecta
     *
     * @param query sentencia a realizar: DELETE UPDATE DROP
     * @return result > 1
     */
    public boolean Consulta(String query) {
        int result;
        try {
            statement = cn.createStatement();
            result = this.statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return (result > 1);
    }

    /**
     * Obtiene una lista con datos de una conexion a una base de datos remota
     *
     * @return list El cual es un una lista que contiene los datos solicitados
     * @param query string que contiene la sentencia SQL a buscar
     * @param datos int que contiene la cantidad de datos que queremos obtener
     * Ejemplo de ejecución de datos
     *
     * SQLServerConnection cn = new SQLServerConnection("@database","@usuario",
     * "@password"); ArrayList<ArrayList<String>> lista =
     * cn.getResults("@sentencia_SQL", @datos_esperados);
     *
     * for (ArrayList<String> items : lista) { for (String item : items) {
     * System.out.print(item); System.out.print(" "); }
     *
     * System.out.println(""); }
     *
     * cn.CloseConnection();
     */
    public ArrayList<ArrayList<String>> getResults(String query, int datos) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        ResultSet rs;
        // Realizar consulta
        try {
            statement = cn.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error en la consulta a la base de datos: " + e);
            return null;
        }
        // Agregar datos a la lista
        try {

            while (rs.next()) {
                ArrayList<String> items = new ArrayList<>();
                for (int i = 1; i <= datos; i++) {
                    items.add(rs.getString(i));
                }
                list.add(items);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return list;
    }

    /**
     * Metodo para cerrar la conexion a la base de datos
     */
    public void CloseConnection() {
        try {
            cn.close();
            System.out.println("Cerrada la conexion a la base de datos");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private String url;
    private String user;
    private String password;
    private Statement statement;
    Connection cn;

}
