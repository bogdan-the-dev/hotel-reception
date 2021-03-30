package com.Bogdan;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        //GUI mainWindow = new GUI("Hotel Cișmigiu - Main Window", new Dimension(400, 200), true, null);
        //mainWindow.setAsMainWindow();
        //mainWindow.initializeGUI();

        GUIModel gModel = new GUIModel();
        GUIView gView = new GUIView(gModel);
        GUIController gController = new GUIController(gModel, gView);
        gController.initializeRoom();
        gController.setMainMenu();


    }
}
