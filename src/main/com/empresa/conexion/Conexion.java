package com.empresa.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    // Esta será la instancia única del Singleton
    private static class ConexionHolder {
        private static final Conexion INSTANCE = new Conexion();
    }

    // Hacemos el constructor privado para evitar intentos de crear instancias extra
    private Conexion() {}

    // Este metodo devolverá la instancia
    public static Conexion getInstance() {
        return ConexionHolder.INSTANCE;
    }

    private BasicDataSource dataSource;

    private DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (this) {
                if (dataSource == null) {
                    dataSource = new BasicDataSource();
                    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    dataSource.setUsername("root");
                    dataSource.setPassword("123456");
                    dataSource.setUrl("jdbc:mysql://localhost:3306/gestion_nominas?useTimezone=true&serverTimezone=UTC");
                    dataSource.setInitialSize(20);
                    dataSource.setMaxIdle(15);
                    dataSource.setMaxTotal(20);
                    dataSource.setMaxWaitMillis(5000);
                }
            }
        }
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}