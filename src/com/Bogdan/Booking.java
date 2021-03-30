package com.Bogdan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Booking {
    private SQL sql = new SQL();

    private int id;
    private int customerId;
    private int roomId;
    private int roomType;
    private Date checkInDate;
    private Date checkOutDate;
    private int price;
    private int nrDays;
    private boolean checkedIn;
    private boolean checkedOut;
    private boolean isPaid;
    private String phone;

    public void setDate(Date d1, Date d2){
        this.checkInDate = d1;
        this.checkOutDate = d2;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    public void setRoomId(int roomId){
        this.roomId=roomId;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public int getPrice() {
        return price;
    }

    public int getNrDays() {
        return nrDays;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setNrDays(){
        long difference = checkOutDate.getTime() - checkInDate.getTime();
        float daysBetween = (difference / (1000*60*60*24));
        this.nrDays = (int)daysBetween;
    }

    private boolean vfToday(Date d1, Date d2){
        SimpleDateFormat tday = new SimpleDateFormat("dd-MM-yyyy");
        return tday.format(d1).equals(tday.format(d2));
    }

    public boolean verifyDate(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        return ((checkOutDate.after(today) && vfToday(today, checkInDate) && checkInDate.before(checkOutDate)) || (checkOutDate.after(today) && checkInDate.after(today) && checkInDate.before(checkOutDate)));
    }

    public void calculatePrice(){
        String query = "select Price from roomtype where TypeID = " + roomType + ";";
        price = sql.getRoomPrice(query) * nrDays;
    }

    public void book(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String inDate = format.format(checkInDate);
        String outDate = format.format(checkOutDate);
        String query = "insert into bookings (CustomerID, RoomID, CheckInDate, CheckOutDate, Price, CheckedIn, CheckedOut, Paid) values ( '" + customerId + "' , '" + roomId + "','" + inDate + "','" + outDate + "','" + price +"', '0', '0', '0');";
        sql.executeStatement(query);
    }

    public void delete(String id){
        sql.executeStatement("delete from bookings where bookingID = " + id);
    }

    public void updateBookingCheckInStatus(int bookingID){
        sql.executeStatement("update bookings set CheckedIn = 1 where bookingID = " + bookingID);
    }

    public void setPaid(int bookingID){
        sql.executeStatement("update bookings set Paid = 1 where bookingID = " + bookingID);
    }

    public void updateBookingCheckOutStatus(int bookingID){
        sql.executeStatement("update bookings set CheckedOut = 1 where bookingID = " + bookingID);
    }

    public void readPhone(String phone){
        this.phone = phone;
        getIdFromPhone();
    }

    private void getIdFromPhone(){
        id = sql.getNR("select login.userID from login join customerinfo on login.userID = customerinfo.ID where customerinfo.PhoneNumber= '" + phone + "';", "userID");
    }
}
