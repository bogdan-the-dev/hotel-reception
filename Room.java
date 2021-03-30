package com.Bogdan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {
    private SQL sql = new SQL();

    private int roomID;
    private int roomNumber;
    private String customerName;
    private int customerID;
    private double price;
    private Date checkInDate;
    private Date chekOutDate;
    private int status;
    private int type;

    public Room(int id, Date d1, Date d2){
        this.roomID = id;
        this.checkInDate = d1;
        this.chekOutDate = d2;
    }

    public Room(int id){
        this.roomID = id;
        setRoomNumber();
    }

    public Room(int roomID, double price){
        this(roomID, null, 0, price, false, 0, null, null);
    }

    public Room(int roomID, String customerName, int customerID, double price, boolean roomFor2, int indexForStatus, Date checkIn, Date checkOut){
        this.roomID = roomID;
        this.customerName = customerName;
        this.customerID = customerID;
        this.price = price;
        this.checkInDate = checkIn;
        this.chekOutDate = checkOut;
    }

    public void setRoomNumber(){
        this.roomNumber = roomID;
    }

    public void setType(){
        String query = "select RoomType from room where RoomID = " + roomID;
        this.type=sql.getRoomType(query);
    }

    public void setStatusForDates(Date d1, Date d2){
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        String query = "select CheckoutDate, CheckInDate from bookings where RoomID = " + roomID;
        if(sql.verifyRoom(query, d1, d2))
            this.status = 5;
    }


    public int getRoomID() {
        return roomID;
    }

    public int getType(){
        return this.type;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void initializeStatus(){
        status = sql.getNR("select StatusID from room where RoomID = " + roomID + ";", "StatusID");
    }

    public void dumpStatus(){
        sql.executeStatement("update room set StatusID=" + status + " where RoomID = " + roomID + ";");
    }

    public  String getStatusChar(){
        String statement = "select * from room where RoomNumber = " + roomNumber;
        int status = sql.getRoomStatus(statement);
        if(status == 1)
            return "Unoccupied";
        else if(status == 2)
            return "Occupied";
        else if(status == 3)
            return "In cleaning";
        else if(status == 4)
            return "In repair";
        else if(status == 5)
            return "Available for booking";
        return null;
    }
}
