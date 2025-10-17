package com.empresa.model;

import com.empresa.exceptions.DatosNoCorrectosException;

public class Empleado {
    public String nombre;
    public String dni;
    public char sexo;
    private int categoria;
    public int anyos;

    public Empleado() {
    }

    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        if (categoria >= 1 && categoria <= 10 && anyos >= 0) {
            this.categoria = categoria;
            this.anyos = anyos;
        } else {
            throw new DatosNoCorrectosException("Datos no correctos");
        }
    }

    public Empleado(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = 1;
        this.anyos = 0;
    }

    public void setCategoria(int categoriaNueva) {
        if (categoriaNueva >= 1 && categoriaNueva <= 10) {
            categoria = categoriaNueva;
        }
    }

    public int getCategoria() {
        return categoria;
    }

    public void incrAnyo() {
        anyos++;
    }

    public void imprime() {
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Sexo: " + sexo);
        System.out.println("Antigüedad: " + anyos);
        System.out.println("Categoría: " + getCategoria());
    }
}
