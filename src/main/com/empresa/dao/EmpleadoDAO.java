package com.empresa.dao;

import com.empresa.conexion.Conexion;
import com.empresa.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpleadoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // Obtener la lista de empleados
    public List<Empleado> obtenerEmpleados() throws SQLException {
        ResultSet resultSet;
        List<Empleado> listaEmpleados = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Empleado e = new Empleado();
                e.id = resultSet.getInt("id");
                e.nombre = resultSet.getString("nombre");
                e.dni = resultSet.getString("dni");
                e.sexo = resultSet.getString("sexo").charAt(0);
                e.setCategoria(resultSet.getInt("categoria"));
                e.anyos = resultSet.getInt("anyos");
                listaEmpleados.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEmpleados;
    }

    public Map<String, Object> obtenerSueldoEmpleado(String dniBuscado) throws SQLException {
        ResultSet resultSet;
        Map<String, Object> sueldoMap = new HashMap<>();
//        Empleado empleado = new Empleado();
        String dni;
        String nombre;
        int sueldo;

        String sql;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT empleados.dni, empleados.nombre, nominas.sueldo FROM empleados join nominas ON empleados.dni = nominas.dni WHERE empleados.dni LIKE UPPER(?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, dniBuscado);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                dni =  resultSet.getString("dni");
                nombre = resultSet.getString("nombre");
                sueldo = resultSet.getInt("sueldo");
                sueldoMap.put("dni", dni);
                sueldoMap.put("nombre", nombre);
                sueldoMap.put("sueldo", sueldo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sueldoMap;
    }

    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

}
