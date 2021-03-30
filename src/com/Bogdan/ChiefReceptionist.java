package com.Bogdan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChiefReceptionist extends Receptionist{
    private SQL sql = new SQL();
    public ChiefReceptionist() {
    }

    public int getRevenue(Date date1, Date date2, String room){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int total = 0;
        String query = "select bookings.Price from bookings where RoomID=" + room + " and CheckInDate>= '" + format.format(date1) + "' and CheckOutDate<='" + format.format(date2) +  "' and Paid=1;";
        total = sql.getSum(query, "Price");
        return total;
    }

}
