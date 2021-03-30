package com.Bogdan;

import java.util.ArrayList;

public class Repairman {
    private SQL sql = new SQL();

    public ArrayList<Integer> getRoomsToRepair(){
        return sql.getNumbers("select RoomID from room where StatusID = 4;", "RoomID");
    }

    public String[] getArr(ArrayList<Integer> arrayList){
        int size = arrayList.size();
        String[] values = new String[size];
        for(int i = 0; i < size; i++)
            values[i]=arrayList.get(i).toString();
        return values;
    }

    public void finishRepair(String room){
        sql.executeStatement("update room set StatusID = 5 where RoomID = '" + room + "';");
    }
}
