package database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DATABASE_NAME = "database";
    private static final String DATABASE_DRIVER = "jdbc:sqlite:";
    private static final String DATABASE_EXTENSION = ".db";
    private static final String FULL_DATABASE_DRIVER = DATABASE_DRIVER + "./" + DATABASE_NAME + DATABASE_EXTENSION;

    protected Connection c;

    public boolean databaseExists() {
        return new File("./" + DATABASE_NAME + DATABASE_EXTENSION).exists();
    }

    public void createDatabase() {
        System.out.println("Creating database");
        createTables();
    }

    public ResultSet makeDatabaseQuery(String query) throws SQLException {
        c = DriverManager.getConnection(FULL_DATABASE_DRIVER);
        Statement s = c.createStatement();
        s.setQueryTimeout(30);
        ResultSet rs = s.executeQuery(query);
        return rs;
    }

    public List<String[]> getAllItems() throws SQLException {
        List<String[]> allPlans = new ArrayList<>();
        c = DriverManager.getConnection(FULL_DATABASE_DRIVER);
        Statement s = c.createStatement();
        s.setQueryTimeout(30);
        String query = "SELECT * FROM item";
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            String[] aux = new String[2];
            for (int i = 0; i < 2; i++) {
                System.out.println("On loop adding "+rs.getString(i+1));
                aux[i] = rs.getString(i+1);
            }
            allPlans.add(aux);
        }
        rs.close();
        c.close();
        return allPlans;
    }
    public List<String[]> getAllPayments() throws SQLException {
        List<String[]> allPlans = new ArrayList<>();
        c = DriverManager.getConnection(FULL_DATABASE_DRIVER);
        Statement s = c.createStatement();
        s.setQueryTimeout(30);
        String query = "SELECT * FROM payment";
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            String[] aux = new String[4];
            for (int i = 0; i < 3; i++) {
                aux[i] = rs.getString(i+1);
            }
            allPlans.add(aux);
        }
        rs.close();
        c.close();
        return allPlans;
    }
    public List<String[]> getAllIncomes() throws SQLException {
        List<String[]> allPlans = new ArrayList<>();
        c = DriverManager.getConnection(FULL_DATABASE_DRIVER);
        Statement s = c.createStatement();
        s.setQueryTimeout(30);
        String query = "SELECT * FROM income";
        ResultSet rs = s.executeQuery(query);
        while (rs.next()) {
            String[] aux = new String[4];
            for (int i = 0; i < 3; i++) {
                aux[i] = rs.getString(i+1);
            }
            allPlans.add(aux);
        }
        rs.close();
        c.close();
        return allPlans;
    }

    public void makeDatabaseUpdate(String query) throws SQLException {
        System.out.println("Updating database");
        c = DriverManager.getConnection(FULL_DATABASE_DRIVER);
        Statement s = c.createStatement();
        s.setQueryTimeout(30);
        s.executeUpdate(query);
        c.close();
    }

    public void closeConnection() throws SQLException {
        if(c != null)
            c.close();
    }

    protected void createTables() {
        try {
            String items = "CREATE TABLE item("
                    + "name STRING, "
                    + "quantity INTEGER, "
                    + "PRIMARY KEY(name)"
                    + ")";
            String payments = "CREATE TABLE payment("
                    + "cause STRING, "
                    + "date DATE, "
                    + "quantity INTEGER, "
                    + "PRIMARY KEY(cause,date)"
                    + ")";
            String incomes = "CREATE TABLE income("
                    + "cause STRING, "
                    + "date DATE, "
                    + "quantity INTEGER, "
                    + "PRIMARY KEY(cause,date)"
                    + ")";

            String[] queries = {
                    items,
                    payments,
                    incomes
            };


            for(String q : queries)
                makeDatabaseUpdate(q);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
