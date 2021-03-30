package com.Bogdan;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Customer {

    private SQL sql = new SQL();
    private Connection connection;

    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String socialSecurityNumber;
    private String creditCard;

    private String userName;
    private String password;
    private int totalAmount;
    private int level;

    public void initialize(String userName, String password, int id){
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.level = level;
    }


/*
    public void login(){
        String statement = sql.createLogInStatement(userName, password);
        this.id = sql.login(statement);
        if(this.id == -1)
            System.out.println("Invalid login credentials!!\nTry again!");
        else{
            sql.getCustomerInfo(firstName, lastName, phone, email, address, socialSecurityNumber, creditCard, id);
        }
    }
*/
    public void createCustomerAccount(String userName, String Password){
        String statement = sql.generateCreateLogInStatement(userName, Password, 5);
        sql.executeStatement(statement);
    }

    public void setInfo(String firstName, String lastName, String phone, String email, String address, String socialSecurityNumber, String creditCard){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.socialSecurityNumber = socialSecurityNumber;
        this.creditCard = creditCard;
    }


    public String getBookings(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
        String day = tday.format(today);

        String query = "select BookingID, RoomID, CheckInDate, CheckOutDate, Price from bookings where CustomerID = " + id + " and CheckInDate >= " + "'"+day+"' and CheckedIn = 0 and CheckedOut = 0;";
        return sql.getBookings(query);
    }

    public int getID(){
        return this.id;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setId(int id){
        this.id = id;
    }

    public String checkInQ(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
        String day = tday.format(today);
       return  "select RoomID from bookings where CheckInDate = '" + day + "' and CustomerID = " + id + " and CheckedIn=0;";
    }

    public String checkOutQ(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
        String day = tday.format(today);
        return  "select RoomID from bookings where CheckOutDate = '" + day + "' and CustomerID = " + id + " and CheckedOut=0;";
    }

    public void setIDFromPhone(String phone){
        id = sql.getNR("select login.userID from login join customerinfo on login.userID = customerinfo.ID where customerinfo.PhoneNumber= '" + phone + "';", "userID");

    }

}
