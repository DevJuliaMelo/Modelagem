package org.example.conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instance;
    private static final String url = "jdbc:mysql://localhost:3306/modelagem";
    private static final String user = "root";
    private static final String password = "123456";
    private Connection conn;

    private ConexaoBD() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConexaoBD getInstance() {
        if (instance == null) {
            instance = new ConexaoBD();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
