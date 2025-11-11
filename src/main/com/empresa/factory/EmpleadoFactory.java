package com.empresa.factory;

import com.empresa.exceptions.DatosNoCorrectosException;
import com.empresa.model.Empleado;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoFactory {
    public static Empleado crearDesdeResultSet(ResultSet rs) throws SQLException, DatosNoCorrectosException {
        return new Empleado(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("dni"),
                rs.getString("sexo").charAt(0),
                rs.getInt("categoria"),
                rs.getInt("anyos")
        );
    }
}
