package com.greenshake;

import org.flywaydb.core.Flyway;

import java.sql.*;
import java.util.List;

public class DevMigrations {
    private static final String url = "jdbc:h2:./test";
    public static void main(String[] args){
        Flyway flyway = Flyway
                .configure()
                .dataSource(url,null,null)
                .load();
        flyway.migrate();
    }
}
