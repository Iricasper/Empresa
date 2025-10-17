package com.empresa.dao;

import com.empresa.conexion.Conexion;
import com.empresa.model.Empleado;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // Obtener la lista de empleados
    public List<Empleado> obtenerEmpleados() throws SQLException, IOException {
        ResultSet resultSet = null;
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

    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

}
