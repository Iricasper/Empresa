package com.empresa.builder;

public interface Builder {
    Object dni(String dni);
    Object nombre(String nombre);
    Object sexo(char sexo);
    Object categoria(int categoria);
    Object anyos(int anyos);
}
