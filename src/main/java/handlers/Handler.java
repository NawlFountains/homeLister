package handlers;

import database.DatabaseManager;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Handler {
    protected DatabaseManager dbm;
    public Handler(DatabaseManager dbm) {this.dbm = dbm;}

    public void registerNewItem(String name, int quantity) throws SQLException {
        dbm.makeDatabaseUpdate("INSERT INTO item(name, quantity) VALUES ('"+name+"','"+quantity+"')");
    }
    public void deleteItem(String name) throws SQLException {
        System.out.println("About to delete item");
        dbm.makeDatabaseUpdate("DELETE FROM item WHERE name = '"+name+"';");
    }
    public void registerPayment(String cause, int quantity) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        System.out.println("INSERT INTO payment(cause, date, quantity) VALUES ('"+cause+"','"+currentDate+"' ,'"+quantity+"')");
        dbm.makeDatabaseUpdate("INSERT INTO payment(cause, date, quantity) VALUES ('"+cause+"','"+currentDate+"' ,'"+quantity+"')");
        System.out.println("Registered payment");
    }
    public void deletePayment(String cause, Date date) throws SQLException {
        System.out.println("DELETE FROM payment WHERE cause = '"+cause+"' AND date = '"+date+"';");
        dbm.makeDatabaseUpdate("DELETE FROM payment WHERE cause = '"+cause+"' AND date = '"+date+"';");
    }
    public void registerIncome(String cause, int quantity) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        System.out.println("INSERT INTO income(cause, date, quantity) VALUES ('"+cause+"','"+currentDate+"' ,'"+quantity+"')");
        dbm.makeDatabaseUpdate("INSERT INTO income(cause, date, quantity) VALUES ('"+cause+"','"+currentDate+"' ,'"+quantity+"')");
    }
    public void deleteIncome(String cause, Date date) throws SQLException {
        System.out.println("DELETE FROM income WHERE cause = '"+cause+"' AND date = '"+date+"';");
        dbm.makeDatabaseUpdate("DELETE FROM income WHERE cause = '"+cause+"' AND date = '"+date+"';");
    }
}
