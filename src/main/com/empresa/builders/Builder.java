package com.empresa.builders;

public interface Builder {
    Object id(int id);
    Object dni(String dni);
    Object nombre(String nombre);
    Object sexo(char sexo);
    Object categoria(int categoria);
    Object anyos(int anyos);
}
