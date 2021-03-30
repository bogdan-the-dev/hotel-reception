package com.Bogdan;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class GUIView extends JFrame {
    private JFrame window = new JFrame();
    private GUIModel gModel;

    private JTextField t1;
    private JPasswordField t2;
    private JPasswordField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;
    private JTextField t8;
    private JTextField t9;
    private JTextField t10;
    private JComboBox c1;
    private JDatePickerImpl calendar;
    private JDatePanelImpl calendarPanel;
    private UtilCalendarModel calendarModel;
    private JDatePickerImpl calendar2;
    private JDatePanelImpl calendarPanel2;
    private UtilCalendarModel calendarModel2;
    private JComboBox c2;



    private JPanel masterPane = new JPanel();

    public GUIView(GUIModel gModel){
        this.gModel = gModel;
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void setMainWindow(ActionListener signUp, ActionListener signIn, WindowListener closing, Dimension dimension){
        window.setTitle("Hotel Cișmigiu - Main Window");
        window.setSize(dimension);
        JPanel mainContainer = new JPanel();
        JButton loginButton = new JButton("Sign In");
        JButton createAccount = new JButton("Sign Up");
        loginButton.addActionListener(signIn);

        createAccount.addActionListener(signUp);


        JPanel text = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setText("Welcome to the Hotel Cișmigiu!");
        textArea.setEditable(false);
        text.add(textArea);
        JPanel button = new JPanel();
        button.add(loginButton);
        button.add(createAccount);
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(text, BorderLayout.NORTH);
        mainContainer.add(button, BorderLayout.CENTER);
        window.addWindowListener(closing);
        window.add(mainContainer);
        window.setResizable(false);
        window.setVisible(true);
    }


    public void setSignIn(ActionListener signInListener, ActionListener cancelL, WindowListener closingListener, Dimension dimension){
        //window.validate();
        window.setTitle("Hotel Cișmigiu - Authentication");
        window.setSize(dimension);
        window.addWindowListener(closingListener);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());

        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));
        JPanel pVertL = new JPanel();
        JPanel pVertR = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(20,0)));
        pHoriz.add(pVertR);

        JLabel l1 = new JLabel("Username", JLabel.RIGHT);
        pVertL.add(l1);
        t1 = new JTextField ();
        pVertR.add(t1);

        JLabel l2 = new JLabel("Password", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertL.add(l2);

          t2 = new JPasswordField ();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t2);
        mainContainer.add(pHoriz, BorderLayout.CENTER);

        JButton signIn = new JButton("Sign In");
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(cancelL);
        signIn.addActionListener(signInListener);
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(1,2));
        buttonArea.add(cancel);
        buttonArea.add(signIn);
        mainContainer.add(buttonArea, BorderLayout.SOUTH);
        window.add(mainContainer);
        window.setVisible(true);
    }



    public void setSignUp(ActionListener createAccountL, ActionListener cancelL, WindowListener returnHome){
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());

        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));
        pHoriz.add(Box.createRigidArea(new Dimension(30, 0)));
        JPanel pVertL = new JPanel();
        JPanel pVertR = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(30, 0)));
        pHoriz.add(pVertR);
        pVertL.add(Box.createRigidArea(new Dimension(0, 20)));
        pVertR.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel l1 = new JLabel("Username", JLabel.RIGHT);
        pVertL.add(l1);
         t1 = new JTextField();
        pVertR.add(t1);

        JLabel l2 = new JLabel("Password", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 27)));
        pVertL.add(l2);
        t2 = new JPasswordField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t2);

        JLabel l3 = new JLabel("Confirm Password", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertL.add(l3);
        t3 = new JPasswordField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t3);

        JLabel l4 = new JLabel("First Name", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 25)));
        pVertL.add(l4);
        t4 = new JTextField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t4);

        JLabel l5 = new JLabel("Last Name", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 23)));
        pVertL.add(l5);
        t5 = new JTextField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t5);

        JLabel l6 = new JLabel("Phone Number", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 25)));
        pVertL.add(l6);
        t6 = new JTextField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 22)));
        pVertR.add(t6);

        JLabel l7 = new JLabel("Email", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 25)));
        pVertL.add(l7);
        t7 = new JTextField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t7);

        JLabel l8 = new JLabel("Address", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 25)));
        pVertL.add(l8);
        t8 = new JTextField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t8);

        JLabel l9 = new JLabel("Social Security Number", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 25)));
        pVertL.add(l9);
        t9 = new JTextField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t9);

        JLabel l10 = new JLabel("Credit Card Number", JLabel.RIGHT);
        pVertL.add(Box.createRigidArea(new Dimension(10, 25)));
        pVertL.add(l10);
        t10 = new JTextField();
        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertR.add(t10);

        pVertR.add(Box.createRigidArea(new Dimension(10, 20)));
        pVertL.add(Box.createRigidArea(new Dimension(10, 30)));
        pHoriz.add(Box.createRigidArea(new Dimension(30, 0)));

        mainContainer.add(pHoriz, BorderLayout.CENTER);

        JPanel pHoriz2 = new JPanel();
        pHoriz2.setLayout(new BoxLayout(pHoriz2, BoxLayout.Y_AXIS));
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.X_AXIS));
        buttonArea.add(Box.createRigidArea(new Dimension(190, 0)));
        JButton createAccount = new JButton("Create Account");
        createAccount.addActionListener(createAccountL);
        buttonArea.add(createAccount);
        JButton homeButton = new JButton("Cancel");
        homeButton.addActionListener(cancelL);
        buttonArea.add(homeButton);
        buttonArea.add(Box.createRigidArea(new Dimension(220, 0)));
        pHoriz2.add(buttonArea);
        pHoriz2.add(Box.createRigidArea(new Dimension(0, 10)));
        mainContainer.add(pHoriz2, BorderLayout.PAGE_END);
        window.addWindowListener(returnHome);
        window.add(mainContainer);
        window.pack();
        window.setVisible(true);
    }

    public void customerCancelBooking(ActionListener backL, ActionListener submitL){
        window.setTitle("Cancel a booking");
        window.setLayout(new BorderLayout());
        JLabel l1 = new JLabel("insert the booking id");
        t1 = new JTextField();
        t1.setColumns(5);
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));
        JPanel pVertL = new JPanel();
        JPanel pVertR = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(10,0)));
        pHoriz.add(pVertR);
        pVertL.add(l1);
        pVertR.add(t1);
        JButton deleteB = new JButton("Delete Booking");
        deleteB.addActionListener(submitL);
        JButton back = new JButton("Back");
        back.addActionListener(backL);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(back);
        panel.add(deleteB);
        window.add(Box.createRigidArea(new Dimension(10,0)), BorderLayout.EAST);
        window.add(Box.createRigidArea(new Dimension(10,0)), BorderLayout.WEST);
        window.add(pHoriz, BorderLayout.CENTER);
       window.add(panel, BorderLayout.SOUTH);
       window.pack();
       window.setVisible(true);
    }



    public void setManager(ActionListener returnHomeL, ActionListener submitL){
        window.setTitle("Manager Account");
        window.setVisible(false);
        window.setResizable(true);
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));
        JPanel pVertL = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        JPanel pVertR = new JPanel();
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(10, 20)));
        pHoriz.add(pVertR);

        JLabel l1 = new JLabel("Username");
        t1 = new JTextField();
        pVertL.add(l1);
        pVertR.add(t1);

        JLabel l2 = new JLabel("Password");
        t2 = new JPasswordField();
        pVertL.add(Box.createRigidArea(new Dimension(0, 25)));
        pVertL.add(l2);
        pVertR.add(Box.createRigidArea(new Dimension(0, 20)));
        pVertR.add(t2);

        JLabel l3 = new JLabel("Account type");
        String[] values ={"Manager", "Receptionist","Chief Receptionist","Maid", "Repairman"};
        c1 = new JComboBox(values);
        pVertL.add(Box.createRigidArea(new Dimension(0, 25)));
        pVertL.add(l3);
        pVertL.add(Box.createRigidArea(new Dimension(0, 25)));
        pVertR.add(Box.createRigidArea(new Dimension(0, 20)));
        pVertR.add(c1);
        pVertR.add(Box.createRigidArea(new Dimension(0, 25)));


        JButton signOut = new JButton("Sign Out");
        signOut.addActionListener(returnHomeL);
        JButton create = new JButton("Create Account");
        create.addActionListener(submitL);
        JPanel buttArea = new JPanel();
        buttArea.add(signOut);
        buttArea.add(Box.createRigidArea(new Dimension(25,0)));
        buttArea.add(create);

        JLabel data = new JLabel();
        data.setText("Welcome " + gModel.getUsername());
        JPanel test = new JPanel();
        test.setLayout(new FlowLayout());
        test.add(data);
        mainContainer.add(pHoriz, BorderLayout.CENTER);
        mainContainer.add(test, BorderLayout.NORTH);
        mainContainer.add(buttArea, BorderLayout.SOUTH);
        window.setLayout(new BorderLayout());
        window.add(test, BorderLayout.NORTH);
        window.add(pHoriz, BorderLayout.CENTER);
        window.add(buttArea, BorderLayout.PAGE_END);
        window.pack();
        window.setVisible(true);
    }

    public void customerBookingV3(int price, ActionListener createBookingL){
        JLabel priceLabel = new JLabel("Price: " + price);
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(priceLabel);
        pVert.add(Box.createRigidArea(new Dimension(0,5)));
        pVert.add(panel);
        JButton submit = new JButton("Book");
        submit.addActionListener(createBookingL);
        JPanel butAr = new JPanel(new FlowLayout());
        butAr.add(submit);
        pVert.add(Box.createRigidArea(new Dimension(0,5)));
        pVert.add(butAr);
        pVert.add(Box.createRigidArea(new Dimension(0,5)));
        masterPane.add(pVert);

        window.pack();
        window.setVisible(true);
    }

    public void customerActiveBooking(ActionListener goBackl, String text){
        window.setTitle("Hotel Cismigiu - Your active bookings");
        window.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        if(text.length() != 0) {
            JTextArea bookings = new JTextArea();
            bookings.setText(text);
            bookings.setEditable(false);
            window.add(bookings, BorderLayout.CENTER);

        }
        else{
            JTextField booking = new JTextField();
            booking.setText("No current bookings");
            booking.setEditable(false);
            window.add(booking, BorderLayout.CENTER);
        }
            JButton returnBut = new JButton("Cancel");
            returnBut.addActionListener(goBackl);
        window.add(Box.createRigidArea(new Dimension(12,0)), BorderLayout.EAST);
        window.add(Box.createRigidArea(new Dimension(10,0)), BorderLayout.WEST);
        window.add(returnBut, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
    }

    public void customerBookingV2(int nrType1, int nrType2, int nrType3, int nrType4, int nrType5, ActionListener setRoomL){
        window.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));
        JPanel pVertL = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        JPanel pVertR = new JPanel();
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));
        ArrayList<String> options = new ArrayList<>();
        if(nrType1 > 0)
            options.add("Basic single Room");
        if(nrType2 > 0)
            options.add("Basic Room with double bed");
        if(nrType3 > 0)
            options.add("Premium Room with double bed");
        if(nrType4 > 0)
            options.add("Apartment with 2 rooms");
        if(nrType5 > 0)
            options.add("Penthouse");
        String[] array = new String[options.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = options.get(i);
        }

        c1 = new JComboBox(array);
        JLabel l6 = new JLabel("Available types of rooms");
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(10,0)));
        pHoriz.add(pVertR);
        pVertR.add(c1);
        pVertL.add(l6);

        JPanel pVertMaster = new JPanel();
        pVertMaster.setLayout(new BoxLayout(pVertMaster, BoxLayout.Y_AXIS));
        pVertMaster.add(pHoriz);
        pVertMaster.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel setRoomBut = new JPanel();
        setRoomBut.setLayout(new FlowLayout());
        JButton setRoomType = new JButton("Set type");
        setRoomType.addActionListener(setRoomL);
        setRoomBut.add(setRoomType);
        pVertMaster.add(setRoomBut);
        pVertMaster.add(Box.createRigidArea(new Dimension(0,10)));
        masterPane.setLayout(new BoxLayout(masterPane, BoxLayout.Y_AXIS));
        masterPane.add(pVertMaster);
        window.add(masterPane, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    public void setCustomerBooking(ActionListener setDateL, ActionListener cancelL, ActionListener finalizeBookingL) {
        window.setTitle("Hotel Cismigiu - Book a room");
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        window.setLayout(new BorderLayout());
        calendarModel = new UtilCalendarModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        calendarPanel = new JDatePanelImpl(calendarModel, p);
        class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        }

        calendar = new JDatePickerImpl(calendarPanel, new DateComponentFormatter());
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        JLabel l1 = new JLabel("Choose the checkin date");
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout());
        JLabel l2 = new JLabel("Choose the checkout date");
        p1.add(l1);
        p1.add(calendar);
        calendarModel2 = new UtilCalendarModel();
        calendarPanel2 = new JDatePanelImpl(calendarModel2, p);
        calendar2 = new JDatePickerImpl(calendarPanel2, new DateComponentFormatter());
        p2.add(l2);
        p2.add(calendar2);
        pVert.add(p1);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        pVert.add(p2);
        JButton setDate = new JButton("Set date");
        setDate.addActionListener(setDateL);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        //pVert.add(setDate);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(cancelL);
        window.add(cancel, BorderLayout.PAGE_END);
        main.add(pVert);
        main.add(Box.createRigidArea(new Dimension(0,5)));
        JPanel butAr = new JPanel();
        butAr.setLayout(new FlowLayout());
        butAr.add(setDate);
        main.add(butAr);
        main.add(Box.createRigidArea(new Dimension(0,5)));
        window.add(main,BorderLayout.NORTH);
        window.pack();
        window.setVisible(true);
       // window.setResizable(false);
    }

    public void repairMan(ActionListener signOutL, ActionListener finishL){
        window.setLayout(new BorderLayout());
        JLabel l1 = new JLabel("Welcome "+ gModel.getUsername());
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(l1);
        window.add(p1, BorderLayout.NORTH);
        window.setTitle("Hotel Cismigiu - Repairman");
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JButton startRepairing = new JButton("Start Repairing");
        startRepairing.addActionListener(finishL);
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout());
        startPanel.add(startRepairing);
        pVert.add(startPanel);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        window.add(pVert, BorderLayout.CENTER);
        JButton signOut = new JButton("Sign Out");
        signOut.addActionListener(signOutL);
        window.add(signOut, BorderLayout.SOUTH);
        window.add(Box.createRigidArea(new Dimension(90,0)), BorderLayout.EAST);
        window.add(Box.createRigidArea(new Dimension(90,0)), BorderLayout.WEST);
        window.pack();
        window.setVisible(true);
        window.setResizable(true);
    }

    public void repairmanStart(ActionListener backL, ActionListener startRepairingL, String[] values){
        window.setTitle("Repairman - Start Repairing");
        window.setLayout(new BorderLayout());

        JLabel l0 = new JLabel("Select a room and start repairing");
        JPanel p0 = new JPanel(new FlowLayout());
        p0.add(l0);
        window.add(p0, BorderLayout.NORTH);
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));

        JPanel pVertL = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        JPanel pVertR = new JPanel();
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("Select a room in need of repairing");
        c1 = new JComboBox(values);
        pVertL.add(l1);
        pVertR.add(c1);
        pVertL.add(Box.createRigidArea(new Dimension(0,15)));
        pVertR.add(Box.createRigidArea(new Dimension(0,15)));
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(20,20)));
        pHoriz.add(pVertR);
        JPanel masterVert = new JPanel();
        masterVert.setLayout(new BoxLayout(masterVert, BoxLayout.Y_AXIS));
        masterVert.add(Box.createRigidArea(new Dimension(0,10)));
        masterVert.add(pHoriz);
        masterVert.add(Box.createRigidArea(new Dimension(0,20)));
        JButton start = new JButton("Start Repairing");
        start.addActionListener(startRepairingL);
        JPanel but = new JPanel(new FlowLayout());
        but.add(start);
        masterVert.add(but);
        window.add(masterVert, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        back.addActionListener(backL);
        window.add(Box.createRigidArea(new Dimension(35,0)), BorderLayout.WEST);
        window.add(Box.createRigidArea(new Dimension(35,0)), BorderLayout.EAST);
        window.add(back, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
    }



    public void MaidMenu(ActionListener signOutL, ActionListener startL, ActionListener finishL){
        window.setLayout(new BorderLayout());
        JLabel l1 = new JLabel("Welcome "+ gModel.getUsername());
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(l1);
        window.add(p1, BorderLayout.NORTH);
        window.setTitle("Hotel Cismigiu - Maid");
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JButton startCleaning = new JButton("Start Cleaning");
        startCleaning.addActionListener(startL);
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout());
        startPanel.add(startCleaning);
        pVert.add(startPanel);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel finishPanel = new JPanel();
        JButton finishCleaning = new JButton("Finish Cleaning");
        finishCleaning.addActionListener(finishL);
        finishPanel.setLayout(new FlowLayout());
        finishPanel.add(finishCleaning);
        pVert.add(finishPanel);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        window.add(pVert, BorderLayout.CENTER);
        JButton signOut = new JButton("Sign Out");
        signOut.addActionListener(signOutL);
        window.add(signOut, BorderLayout.SOUTH);
        window.add(Box.createRigidArea(new Dimension(90,0)), BorderLayout.EAST);
        window.add(Box.createRigidArea(new Dimension(90,0)), BorderLayout.WEST);
        window.pack();
        window.setVisible(true);
        window.setResizable(true);
    }

    public void maidStart(ActionListener backL, ActionListener startCleaningL, String[] values){
        window.setTitle("Maid - Start Cleaning");
        window.setLayout(new BorderLayout());

        JLabel l0 = new JLabel("Select a room and start cleaning");
        JPanel p0 = new JPanel(new FlowLayout());
        p0.add(l0);
        window.add(p0, BorderLayout.NORTH);
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));

        JPanel pVertL = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        JPanel pVertR = new JPanel();
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("Select an unoccupied room");
        c1 = new JComboBox(values);
        pVertL.add(l1);
        pVertR.add(c1);
        pVertL.add(Box.createRigidArea(new Dimension(0,15)));
        pVertR.add(Box.createRigidArea(new Dimension(0,15)));
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(20,20)));
        pHoriz.add(pVertR);
        JPanel masterVert = new JPanel();
        masterVert.setLayout(new BoxLayout(masterVert, BoxLayout.Y_AXIS));
        masterVert.add(Box.createRigidArea(new Dimension(0,10)));
        masterVert.add(pHoriz);
        masterVert.add(Box.createRigidArea(new Dimension(0,20)));
        JButton start = new JButton("Start Cleaning");
        start.addActionListener(startCleaningL);
        JPanel but = new JPanel(new FlowLayout());
        but.add(start);
        masterVert.add(but);
        window.add(masterVert, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        back.addActionListener(backL);
        window.add(Box.createRigidArea(new Dimension(35,0)), BorderLayout.WEST);
        window.add(Box.createRigidArea(new Dimension(35,0)), BorderLayout.EAST);
        window.add(back, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);

    }

    public void maidFinish(ActionListener backL, ActionListener finishL, String[] options){
        window.setTitle("Maid - Finish Cleaning");
        window.setLayout(new BorderLayout());

        JLabel l0 = new JLabel("Select a room to finish cleaning");
        JPanel p0 = new JPanel(new FlowLayout());
        p0.add(l0);
        window.add(p0, BorderLayout.NORTH);
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));

        JPanel pVertL = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        JPanel pVertR = new JPanel();
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("Select a room to finish the cleaning");
        c1 = new JComboBox(options);
        pVertL.add(l1);
        pVertR.add(c1);
        pVertL.add(Box.createRigidArea(new Dimension(0,15)));
        pVertR.add(Box.createRigidArea(new Dimension(0,15)));
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(20,20)));
        pHoriz.add(pVertR);
        JPanel masterVert = new JPanel();
        masterVert.setLayout(new BoxLayout(masterVert, BoxLayout.Y_AXIS));
        masterVert.add(Box.createRigidArea(new Dimension(0,10)));
        masterVert.add(pHoriz);
        masterVert.add(Box.createRigidArea(new Dimension(0,20)));
        JButton start = new JButton("Finish Cleaning");
        start.addActionListener(finishL);
        JPanel but = new JPanel(new FlowLayout());
        but.add(start);
        masterVert.add(but);
        window.add(masterVert, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        back.addActionListener(backL);
        window.add(Box.createRigidArea(new Dimension(35,0)), BorderLayout.WEST);
        window.add(Box.createRigidArea(new Dimension(35,0)), BorderLayout.EAST);
        window.add(back, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
    }


    public void setCustomerMainWindow(ActionListener bookingL, ActionListener activeBookingL, ActionListener cancelBookingL, ActionListener sCheckInL, ActionListener sCheckOutL, ActionListener signOutL){
        window.setTitle("Hotel Cismigiu - " + gModel.getUsername() + " profile");
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        JLabel data = new JLabel();
        data.setText("Welcome " + gModel.getUsername());
        JPanel test = new JPanel();
        test.add(data);
        test.setLayout(new FlowLayout());
        mainContainer.add(test, BorderLayout.NORTH);
        JButton checkRooms = new JButton("Book a Room");
        checkRooms.addActionListener(bookingL);
        JButton viewActiveBookings = new JButton("Active Bookings");
        viewActiveBookings.addActionListener(activeBookingL);
        JButton cancelBooking = new JButton("Cancel Booking");
        cancelBooking.addActionListener(cancelBookingL);
        JButton selfCheckIn = new JButton("Self CheckIn");
        selfCheckIn.addActionListener(sCheckInL);
        JButton selfCheckOut = new JButton("Self CheckOut");
        selfCheckOut.addActionListener(sCheckOutL);
        JButton signOut = new JButton("Sign Out");
        signOut.addActionListener(signOutL);
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(checkRooms);
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(viewActiveBookings);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        pVert.add(cancelBooking);
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(selfCheckIn);
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(selfCheckOut);
        pVert.add(Box.createRigidArea(new Dimension(0, 20)));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        JLabel currentDate = new JLabel();
        currentDate.setText("Today is " + formatter.format(date));
        pVert.add(currentDate);
        mainContainer.add(pVert, BorderLayout.CENTER);
        mainContainer.add(signOut, BorderLayout.SOUTH);
        mainContainer.add(Box.createRigidArea(new Dimension(110, 0)), BorderLayout.EAST);
        mainContainer.add(Box.createRigidArea(new Dimension(120, 0)), BorderLayout.WEST);
        window.add(mainContainer);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(true);
    }

    public void receptionistMenu(ActionListener bookingL, ActionListener checkInL, ActionListener checkOutL, ActionListener requireRepairL, ActionListener viewFullStatusL, ActionListener signOutL){
        window.setTitle("Hotel Cismigiu - Receptionist");
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        JLabel data = new JLabel();
        data.setText("Welcome " + gModel.getUsername());
        JPanel test = new JPanel();
        test.add(data);
        test.setLayout(new FlowLayout());
        mainContainer.add(test, BorderLayout.NORTH);
        JButton checkRooms = new JButton("Book a Room");
        checkRooms.addActionListener(bookingL);
        JButton checkIn = new JButton("CheckIn");
        checkIn.addActionListener(checkInL);
        JButton checkOut = new JButton("CheckOut");
        checkOut.addActionListener(checkOutL);

        JButton viewStatus = new JButton("Room Status");
        viewStatus.addActionListener(viewFullStatusL);

        JButton requireRepair = new JButton("Require Repaid");
        requireRepair.addActionListener(requireRepairL);

        JButton signOut = new JButton("Sign Out");
        signOut.addActionListener(signOutL);
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(checkRooms);
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(checkIn);
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(checkOut);
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(viewStatus);
        pVert.add(Box.createRigidArea(new Dimension(0, 10)));
        pVert.add(requireRepair);
        pVert.add(Box.createRigidArea(new Dimension(0, 20)));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        JLabel currentDate = new JLabel();
        currentDate.setText("Today is " + formatter.format(date));
        pVert.add(currentDate);
        mainContainer.add(pVert, BorderLayout.CENTER);
        mainContainer.add(signOut, BorderLayout.SOUTH);
        mainContainer.add(Box.createRigidArea(new Dimension(110, 0)), BorderLayout.EAST);
        mainContainer.add(Box.createRigidArea(new Dimension(120, 0)), BorderLayout.WEST);
        window.add(mainContainer);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(true);
    }

    public void BookingV3(int price, ActionListener createBookingL){
        JLabel priceLabel = new JLabel("Price: " + price);
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(priceLabel);
        pVert.add(Box.createRigidArea(new Dimension(0,5)));
        pVert.add(panel);
        JButton submit = new JButton("Book");
        submit.addActionListener(createBookingL);
        JPanel butAr = new JPanel(new FlowLayout());
        butAr.add(submit);
        pVert.add(Box.createRigidArea(new Dimension(0,5)));
        pVert.add(butAr);
        pVert.add(Box.createRigidArea(new Dimension(0,5)));
        masterPane.add(pVert);

        window.pack();
        window.setVisible(true);
    }


    public void BookingV2(int nrType1, int nrType2, int nrType3, int nrType4, int nrType5, ActionListener setRoomL){
        window.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel pHoriz = new JPanel();
        pHoriz.setLayout(new BoxLayout(pHoriz, BoxLayout.X_AXIS));
        JPanel pVertL = new JPanel();
        pVertL.setLayout(new BoxLayout(pVertL, BoxLayout.Y_AXIS));
        JPanel pVertR = new JPanel();
        pVertR.setLayout(new BoxLayout(pVertR, BoxLayout.Y_AXIS));
        ArrayList<String> options = new ArrayList<>();
        if(nrType1 > 0)
            options.add("Basic single Room");
        if(nrType2 > 0)
            options.add("Basic Room with double bed");
        if(nrType3 > 0)
            options.add("Premium Room with double bed");
        if(nrType4 > 0)
            options.add("Apartment with 2 rooms");
        if(nrType5 > 0)
            options.add("Penthouse");
        String[] array = new String[options.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = options.get(i);
        }

        c1 = new JComboBox(array);
        JLabel l6 = new JLabel("Available types of rooms");
        pHoriz.add(pVertL);
        pHoriz.add(Box.createRigidArea(new Dimension(10,0)));
        pHoriz.add(pVertR);
        pVertR.add(c1);
        pVertL.add(l6);

        JPanel pVertMaster = new JPanel();
        pVertMaster.setLayout(new BoxLayout(pVertMaster, BoxLayout.Y_AXIS));
        pVertMaster.add(pHoriz);
        pVertMaster.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel setRoomBut = new JPanel();
        setRoomBut.setLayout(new FlowLayout());
        JButton setRoomType = new JButton("Set type");
        setRoomType.addActionListener(setRoomL);
        setRoomBut.add(setRoomType);
        pVertMaster.add(setRoomBut);
        pVertMaster.add(Box.createRigidArea(new Dimension(0,10)));
        masterPane.setLayout(new BoxLayout(masterPane, BoxLayout.Y_AXIS));
        masterPane.add(pVertMaster);
        window.add(masterPane, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    public void setBooking(ActionListener setDateL, ActionListener cancelL) {
        window.setTitle("Hotel Cismigiu - Book a room");
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        window.setLayout(new BorderLayout());
        calendarModel = new UtilCalendarModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        calendarPanel = new JDatePanelImpl(calendarModel, p);
        class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        }

        calendar = new JDatePickerImpl(calendarPanel, new DateComponentFormatter());
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        JPanel p0 = new JPanel(new FlowLayout());
        JLabel l0 = new JLabel("Phone number");
        t1  = new JTextField();
        t1.setColumns(11);
        p0.add(l0);
        p0.add(t1);
        JLabel l1 = new JLabel("Choose the checkin date");
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout());
        JLabel l2 = new JLabel("Choose the checkout date");
        p1.add(l1);
        p1.add(calendar);
        calendarModel2 = new UtilCalendarModel();
        calendarPanel2 = new JDatePanelImpl(calendarModel2, p);
        calendar2 = new JDatePickerImpl(calendarPanel2, new DateComponentFormatter());
        p2.add(l2);
        p2.add(calendar2);
        pVert.add(p0);
        pVert.add(Box.createRigidArea(new Dimension(0,15)));
        pVert.add(p1);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        pVert.add(p2);
        JButton setDate = new JButton("Set date");
        setDate.addActionListener(setDateL);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(cancelL);
        window.add(cancel, BorderLayout.PAGE_END);
        main.add(pVert);
        main.add(Box.createRigidArea(new Dimension(0,5)));
        JPanel butAr = new JPanel();
        butAr.setLayout(new FlowLayout());
        butAr.add(setDate);
        main.add(butAr);
        main.add(Box.createRigidArea(new Dimension(0,5)));
        window.add(main,BorderLayout.NORTH);
        window.pack();
        window.setVisible(true);
        // window.setResizable(false);
    }



    public void requestRepair(ActionListener requstL, ActionListener cancelL) {
        window.setTitle("Hotel Cismigiu - Request Repair");
        window.setLayout(new BorderLayout());
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JPanel p0 = new JPanel(new FlowLayout());
        JLabel l0 = new JLabel("RoomNr");
        t1 = new JTextField();
        t1.setColumns(5);
        p0.add(l0);
        p0.add(Box.createRigidArea(new Dimension(0, 15)));
        p0.add(t1);
        p0.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton submit = new JButton("Request");
        submit.addActionListener(requstL);
        p0.add(submit);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(cancelL);
        window.add(p0, BorderLayout.CENTER);
        window.add(cancel, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);

    }

    public void receptionistCheckOutPhone(ActionListener l, ActionListener backL){
        window.setTitle("Hotel Cismigiu - Check out");
        window.setLayout(new BorderLayout());
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JPanel p0 = new JPanel(new FlowLayout());
        JLabel l0 = new JLabel("Phone");
        t1 = new JTextField();
        t1.setColumns(15);
        p0.add(l0);
        p0.add(Box.createRigidArea(new Dimension(10, 0)));
        p0.add(t1);

        pVert.add(p0);
        pVert.add(Box.createRigidArea(new Dimension(0,15)));
        JButton submit = new JButton("Set");
        submit.addActionListener(l);
        pVert.add(submit);
        JButton cancel = new JButton("Back");
        cancel.addActionListener(backL);
        window.add(cancel, BorderLayout.SOUTH);
        window.add(pVert, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    public void receptionistCheckInPhone(ActionListener l, ActionListener backL){
        window.setTitle("Hotel Cismigiu - Check in");
        window.setLayout(new BorderLayout());
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JPanel p0 = new JPanel(new FlowLayout());
        JLabel l0 = new JLabel("Phone");
        t1 = new JTextField();
        t1.setColumns(15);
        p0.add(l0);
        p0.add(Box.createRigidArea(new Dimension(10, 0)));
        p0.add(t1);

        pVert.add(p0);
        pVert.add(Box.createRigidArea(new Dimension(0,15)));
        JButton submit = new JButton("Set");
        submit.addActionListener(l);
        pVert.add(submit);
        JButton cancel = new JButton("Back");
        cancel.addActionListener(backL);
        window.add(cancel, BorderLayout.SOUTH);
        window.add(pVert, BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);
    }

    public void fullStatus(ActionListener l, ActionListener l2){
        window.setTitle("Hotel Cismigiu - See full status");
        window.setLayout(new BorderLayout());
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        JPanel p0 = new JPanel(new FlowLayout());
        JLabel l0 = new JLabel("RoomID");
        t1 = new JTextField();
        t1.setColumns(10);
        p0.add(l0);
        p0.add(Box.createRigidArea(new Dimension(0, 15)));
        p0.add(t1);
        p0.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton submit = new JButton("Set");
        submit.addActionListener(l);
        p0.add(submit);
        JButton cancel = new JButton("Back");
        cancel.addActionListener(l2);
        window.add(p0, BorderLayout.CENTER);
        window.add(cancel, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
    }

    public void chiefMenu(ActionListener listener1, ActionListener listener2){
        window.setTitle("Hotel Cismigiu - Chief Receptionist");
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        JPanel pVert = new JPanel();
        pVert.setLayout(new BoxLayout(pVert, BoxLayout.Y_AXIS));
        window.setLayout(new BorderLayout());
        calendarModel = new UtilCalendarModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        calendarPanel = new JDatePanelImpl(calendarModel, p);
        class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

            private String datePattern = "yyyy-MM-dd";
            private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            @Override
            public Object stringToValue(String text) throws ParseException {
                return dateFormatter.parseObject(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    return dateFormatter.format(cal.getTime());
                }

                return "";
            }
        }

        calendar = new JDatePickerImpl(calendarPanel, new DateComponentFormatter());
        JLabel l1 = new JLabel("Choose the starting date");
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout());
        JLabel l2 = new JLabel("Choose the ending date");
        p1.add(l1);
        p1.add(calendar);
        calendarModel2 = new UtilCalendarModel();
        calendarPanel2 = new JDatePanelImpl(calendarModel2, p);
        calendar2 = new JDatePickerImpl(calendarPanel2, new DateComponentFormatter());
        p2.add(l2);
        p2.add(calendar2);
        pVert.add(p1);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        pVert.add(p2);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel p0 = new JPanel(new FlowLayout());
        JLabel l0 = new JLabel("Room");
        t1 = new JTextField();
        t1.setColumns(10);
        p0.add(l0);
        p0.add(Box.createRigidArea(new Dimension(15,0)));
        p0.add(t1);
        pVert.add(p0);
        pVert.add(Box.createRigidArea(new Dimension(0,10)));
        JButton setDate = new JButton("Set date");
        setDate.addActionListener(listener1);
        pVert.add(setDate);
        JButton cancel = new JButton("SignOut");
        cancel.addActionListener(listener2);
        window.add(pVert, BorderLayout.CENTER);
        window.add(cancel, BorderLayout.SOUTH);
        window.pack();
        window.setVisible(true);
        window.setResizable(true);

    }



    public void masterReset(){
        gModel.reset();
        this.reset();
    }

    public void reset(){
        masterPane.removeAll();
        masterPane.revalidate();
        masterPane.repaint();
        window.getContentPane().removeAll();
        window.revalidate();
        window.repaint();
    }

    public void showWarning(String msg){
        JOptionPane.showMessageDialog(window, msg, "Warning :|", JOptionPane.WARNING_MESSAGE);

    }

    public void showError(String msg){
        JOptionPane.showMessageDialog(window, msg, "Error :(", JOptionPane.ERROR_MESSAGE);

    }

    public void readUsername(){
        gModel.setUsername(t1.getText());
    }

    public void readPassword(){
        gModel.setPassword(new String(t2.getPassword()));
    }

    public void message(String s){JOptionPane.showMessageDialog(window, s, "Info", JOptionPane.PLAIN_MESSAGE);}


    public void readPassword2(){gModel.setPassword2(new String((t3.getPassword())));}

    public void readFirstName(){gModel.setFirstName(t4.getText());}

    public void readLastname(){gModel.setLastName(t5.getText());}

    public void readPhoneNumber(){gModel.setPhoneNumber(t6.getText());}

    public void readEmail(){gModel.setEmail(t7.getText());}

    public void readAddress(){gModel.setAddress(t8.getText());}

    public void readSSN(){gModel.setSocialSecurityNumber(t9.getText());}

    public void readCreditCard(){gModel.setCreditCard(t10.getText());}

    public String getUserN(){return t1.getText();}

    public String getPass(){return new String(t2.getPassword());}

    public String readAccountTypeChoice(){return (String)c1.getSelectedItem();}

    public Date readCIDate(){
        Calendar selectedValue = (Calendar) calendar.getModel().getValue();
        Date selectedDate = selectedValue.getTime();
        gModel.setCIDate(selectedDate);
        return selectedDate;
    }

    public Date readCODate(){
        Calendar selectedValue = (Calendar) calendar2.getModel().getValue();
        Date selectedDate = selectedValue.getTime();
        gModel.setCODate(selectedDate);
        return selectedDate;
    }

    public String readRoomType(){
        return (String)c1.getSelectedItem();
    }

    public String readbookingID(){
        return t1.getText();
    }

    public String readChoiceMaid(){
        return (String) c1.getSelectedItem();
    }

    public String readChoiceMaid2(){
        return (String) c2.getSelectedItem();
    }

    public String readChoiceRepair(){
        return (String)c1.getSelectedItem();
    }

    public String readPhoneForID(){
        return t1.getText();
    }
}
