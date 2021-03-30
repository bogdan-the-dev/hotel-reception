package com.Bogdan;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.security.spec.ECField;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SQL {
    private String UserName;
    private String Password;
    private Connection connection;

    public SQL(String userName, String Password) {
        this.Password = Password;
        this.UserName = userName;
        this.connect();
    }

    public SQL(){
        this("root", "Bogdan2000");
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/hotel";
        try {
            this.connection = DriverManager.getConnection(url, this.UserName, this.Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generateCreateLogInStatement(String UserName, String Password, int level) {
        return "INSERT INTO login ( `Username` ,`Password`, `CredentialLevel`) VALUES (" + '\u0022' + UserName + '\u0022' + " ," + '\u0022' + Password + '\u0022' + " ," + level + ");";
    }

    public void executeStatement(String Statement) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(Statement);
            System.out.println("Statement executed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRoomType(String query){
        int type = 0;
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                type = result.getInt("RoomType");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return type;
    }

    public boolean verifyRoom(String query, java.util.Date cIn, java.util.Date cOut){

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = result.getDate("CheckInDate");
                Date d2 = result.getDate("CheckOutDate");
                if(!format.format(d2).equals(format.format(cIn)) || d2.before(cIn))
                    return false;
                else if(cOut.after(d1) || !format.format(d1).equals(format.format(cIn)))
                    return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public int getRoomPrice(String query){
        int price = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                price = result.getInt("Price");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return price;
    }

    public String createLogInStatement(String userName, String password) {
        return "select * from login where UserName = " + '\u0022' + userName + '\u0022' + " and Password = " + '\u0022' + password + '\u0022' + ";";
    }

    public int login(String Statement) {
        int level = -1;
        String buffer;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(Statement);
            while (result.next())
                level = result.getInt("CredentialLevel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return level;
    }

    public int getID(String Statement){
        int id = -1;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(Statement);
            while (result.next())
                id = result.getInt("userID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getBookings(String query){
        String text="";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                text += "id="+ result.getInt("bookingID") +"; room=" + result.getInt("RoomID");
                Date date = result.getDate("CheckInDate");
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                text += "; checkin=" + format.format(date);
                date = result.getDate("CheckOutDate");
                text += "; checkout=" + format.format(date);
                text += "; price=" + result.getInt("Price");
                text += "\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    public void getCustomerInfo(String firstName, String lastName, String phone, String email, String address, int socialSecurityNumber, String creditCard, int id){
        String tmp = "select * from customerinfo where id = " + id + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(tmp);
            if(result.next()) {
                firstName = result.getString("FirstName");
                lastName = result.getString("LastName");
                phone = result.getString("PhoneNumber");
                email = result.getString("email");
                address = result.getString("Address");
                socialSecurityNumber = result.getInt("SocialSecurityNumber");
                creditCard = result.getString("CreditCard");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setCustomerInfo(String firstName, String lastName, String phone, String email, String address, String socialSecurityNumber, String creditCard, int id){
        String Statement = "insert into customerinfo values(" + id + ", " + '\u0022' + firstName + '\u0022' + ", " + '\u0022' + lastName + '\u0022' + ", " + '\u0022' +  phone +
                '\u0022' + ", " + '\u0022' + email + '\u0022' + ", " + '\u0022' + address + '\u0022' + ", " + '\u0022' + socialSecurityNumber + '\u0022' + ", " + '\u0022' + creditCard + '\u0022' + ");";
        try{
            Statement statement = connection.createStatement();
            statement.execute(Statement);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean verifyAvailableCustomerUsername(String username){
        boolean status = true;
        try {
            String verification = "select * from login where  UserName = " + '\u0022' + username + '\u0022' + ";";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(verification);
            if(result.next())
                status = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public int getRoomStatus(String statement){
        int status = -1;
        try{
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(statement);
            if(result.next())
                status = result.getInt("StatusID");
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public int getClientIDForBooking(String query){
        int id = -1;
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            if(result.next()){
                id = result.getInt("CustomerID");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }


    public Date getDate(String query, String date){
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            if(result.next()){
                return result.getDate(date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean isCheckedIn(String query){
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            if(result.next()){
                return result.getBoolean("CheckedIn");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public int getRoomPriceJoin(String query){
        int price = 0;
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            if(result.next()){
                price = result.getInt("Price");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return price;
    }

    public int getSize(String query){
        int nr = 0;
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            while(result.next()){
                nr ++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return nr;
    }

    public int getNR(String query, String field){
        int nr = 0;
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            if(result.next()){
                nr = result.getInt(field);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return nr;
    }
    public int getSum(String query, String field){
        int nr = 0;
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            while (result.next()){
                nr += result.getInt(field);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return nr;
    }

    public String getRoomNr(String query, String field){
        String s = "";
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            while (result.next()){
                s += + result.getInt(field)+ ", ";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public ArrayList<Integer> getNumbers(String query, String field){
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            while (result.next()){
                arrayList.add(result.getInt(field));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public String getStatusFull(String query){
        String s="";
      try {
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(query);
            while (result.next()){
                s = result.getString("Status");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

}
