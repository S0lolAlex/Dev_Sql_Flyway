package com.greenshake;

import java.sql.*;
import java.util.*;

public class ClientService {
    private final PreparedStatement createSt;
    private final PreparedStatement readSt;
    private final PreparedStatement readAllSt;
    private final PreparedStatement deleteSt;
    private final PreparedStatement updateSt;

    public ClientService(Connection connection) {
        try {
            createSt = connection
                    .prepareStatement("INSERT INTO client (name) VALUES (?)");
            readSt = connection
                    .prepareStatement("SELECT name FROM client WHERE id = (?)");
            updateSt = connection
                    .prepareStatement("UPDATE client SET name = (?) WHERE ID = (?)");
            deleteSt = connection
                    .prepareStatement("DELETE FROM client WHERE ID = (?)");
            readAllSt = connection
                    .prepareStatement("Select * from client");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(String name) throws Exception {
        if(name.length() < 2 || name.length() > 100) {throw new Exception("Wrong length of name");}
        try {
            createSt.setString(1, name);
            createSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getById(long id) {
       String name ;
        try {
            readSt.setLong(1, id);
            ResultSet rs = readSt.executeQuery();
            rs.next();
            name = rs.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    public void setName(long id, String name) {
        try {
            updateSt.setString(1, name);
            updateSt.setLong(2, id);
            updateSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        try {
            deleteSt.setLong(1, id);
            deleteSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listAll() {
        Client client;
        List<Client> list = new ArrayList<>();
        try {
            ResultSet rs = readAllSt.executeQuery();

            while (rs.next()) {
                client = new Client();
                client.setName(rs.getString("name"));
                client.setId(rs.getInt("id"));
                list.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
