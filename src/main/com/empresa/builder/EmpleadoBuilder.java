package com.empresa.builder;

import com.empresa.exceptions.DatosNoCorrectosException;
import com.empresa.model.Empleado;

public class EmpleadoBuilder implements Builder {
    private int id;
    private String dni;
    private String nombre;
    private char sexo;
    private int categoria = 1;
    private int anyos = 0;

    @Override
    public EmpleadoBuilder id(int id) {
        this.id = id;
        return this;
    }

    @Override
    public EmpleadoBuilder dni(String dni) {
        this.dni = dni;
        return this;
    }

    @Override
    public EmpleadoBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public EmpleadoBuilder sexo(char sexo) {
        this.sexo = sexo;
        return this;
    }

    @Override
    public EmpleadoBuilder categoria(int categoria) {
        if (categoria >= 1 && categoria <= 10) {
            this.categoria = categoria;
        } else throw new DatosNoCorrectosException("Categoría inválida");
        return this;
    }

    @Override
    public EmpleadoBuilder anyos(int anyos) {
        if  (anyos >= 0) {
            this.anyos = anyos;
        } else throw new DatosNoCorrectosException("Años incorrectos");
        return this;
    }

    public Empleado build() throws DatosNoCorrectosException {
        return new Empleado(id, dni, nombre, sexo, categoria, anyos);
    }
}
