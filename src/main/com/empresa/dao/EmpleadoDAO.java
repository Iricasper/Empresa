package com.empresa.dao;

import com.empresa.conexion.Conexion;
import com.empresa.factory.EmpleadoFactory;
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
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // Obtener la lista de empleados
    public List<Empleado> obtenerEmpleados() throws SQLException {
        ResultSet resultSet;
        List<Empleado> listaEmpleados = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;

        try (Connection connection = Conexion.getInstance().getConnection()) {
            sql = "SELECT * FROM empleados";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listaEmpleados.add(EmpleadoFactory.crearDesdeResultSet(resultSet));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEmpleados;
    }

    public Map<String, Object> obtenerSueldoEmpleado(String dniBuscado) throws SQLException {
        ResultSet resultSet;
        Map<String, Object> sueldoMap = new HashMap<>();
        String dni;
        String nombre;
        int sueldo;

        String sql;
        estadoOperacion = false;

        if (dniBuscado.equals("")) {
            System.err.println("No se ha introducido ningun valor");
            sueldoMap.put("error", "No se ha introducido ningun valor");
        } else if (dniBuscado.length() != 9) {
            System.err.println("El DNI debe tener 9 caracteres");
            sueldoMap.put("error", "El DNI debe tener 9 caracteres");
        } else {
            try (Connection connection = Conexion.getInstance().getConnection()) {
                sql = "SELECT empleados.dni, empleados.nombre, nominas.sueldo FROM empleados join nominas ON empleados.dni = nominas.dni WHERE empleados.dni LIKE UPPER(?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, dniBuscado);

                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    dni = resultSet.getString("dni");
                    nombre = resultSet.getString("nombre");
                    sueldo = resultSet.getInt("sueldo");
                    sueldoMap.put("dni", dni);
                    sueldoMap.put("nombre", nombre);
                    sueldoMap.put("sueldo", sueldo);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return sueldoMap;
    }

    public Map<Integer, Empleado> obtenerEmpleados(String campo, String valor) throws SQLException {
        ResultSet resultSet;
        String sql;
        Map<Integer, Empleado> empleadosMap = new HashMap<>();
        estadoOperacion = false;
        try (Connection connection = Conexion.getInstance().getConnection()) {
            sql = "SELECT * FROM empleados WHERE ? LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, campo);
            statement.setString(2, valor);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Empleado e = new Empleado();
                e.id = resultSet.getInt("id");
                e.dni = resultSet.getString("dni");
                e.nombre = resultSet.getString("nombre");
                e.sexo = resultSet.getString("sexo").charAt(0);
                e.setCategoria(resultSet.getInt("categoria"));
                e.anyos = resultSet.getInt("anyos");
                empleadosMap.put(e.getId(), e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return empleadosMap;
    }

}
