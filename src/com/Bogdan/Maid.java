package com.Bogdan;

import javax.swing.*;
import java.util.ArrayList;

public class Maid {
    private SQL sql = new SQL();


    public ArrayList<Integer> getNrRoomsToBeCleaned(){
        return sql.getNumbers("select RoomID from room where StatusID = 1;", "RoomID");
    }

    public ArrayList<Integer> getRoomsInCleaning(){
        return sql.getNumbers("select RoomID from room where StatusID = 3;", "RoomID");
    }

    public String[] getArr(ArrayList<Integer> arrayList){
        int size = arrayList.size();
        String[] values = new String[size];
        for(int i = 0; i < size; i++)
            values[i]=arrayList.get(i).toString();
        return values;
    }

    public void startClean(String room){
        sql.executeStatement("update room set StatusID = 3 where RoomID = '" + room + "';");
    }

    public void finishClean(String room){
        sql.executeStatement("update room set StatusID = 5 where RoomID = '" + room + "';");
    }
}
