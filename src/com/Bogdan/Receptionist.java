package com.Bogdan;

public class Receptionist {
    private SQL sql = new SQL();

    public String getStatus(String nr){
        return sql.getStatusFull("Select Status from roomstatus join room on roomstatus.StatusID = room.StatusID where RoomID = " + nr+";");
    }



}
