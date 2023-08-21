package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/exo_compteBancaire";
        String user = "postgres";
        String password = "0000";

        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            if(connection != null){
                System.out.println("La connexion est ok");
            }else {
                System.out.println("connexion echoué");
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}