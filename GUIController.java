package com.Bogdan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GUIController {

    private GUIModel gModel;
    private GUIView guiView;
    private SQL sql = new SQL();

    private Manager manager = new Manager();
    private Customer customer = new Customer();
    private Maid maid = new Maid();
    private Repairman repairman = new Repairman();
    private Receptionist receptionist = new Receptionist();
    private ChiefReceptionist chiefReceptionist = new ChiefReceptionist();
    private Room[] rooms = new Room[72];
    private Booking booking = new Booking();

    public GUIController(GUIModel gModel, GUIView guiView){
        this.gModel = gModel;
        this.guiView = guiView;
    }

    public void setMainMenu(){
        guiView.masterReset();
        guiView.setMainWindow(new SignUpListener(), new SignInListener(), new ExitListener(), new Dimension(400, 200));
    }

    public void setSignIn(){
        guiView.masterReset();
        guiView.setSignIn(new LogInListener(), new ReturnHomeListener(), new ExitListener(), new Dimension(400, 130));
    }

    public  void setSignUp(){
        guiView.masterReset();
        guiView.setSignUp(new CreateUserAccountListener(), new ReturnHomeListener(), new ExitListener());
    }

    public void verifySignUpData(){
        guiView.readUsername();
        guiView.readPassword();
        guiView.readPassword2();
        guiView.readFirstName();
        guiView.readLastname();
        guiView.readPhoneNumber();
        guiView.readEmail();
        guiView.readAddress();
        guiView.readSSN();
        guiView.readCreditCard();
        System.out.println("Data read!");
        String verificationMsg = gModel.verifyInputData();
        System.out.println(verificationMsg);
        if(verificationMsg.equalsIgnoreCase("none")) {
            gModel.createUserAccount();
            setSignIn();
        }
        else {
            guiView.showError(verificationMsg);
        }

    }

    public void getEmptyRoomID(){
        int ok = 1;
        for(int i = 0; i < 6 && ok==1; i++)
            for(int j = 0; j < 12 && ok==1; j++){
                if(rooms[i*12+j].getType() == booking.getRoomType() && rooms[i*12+j].getStatus() == 5){
                    booking.setRoomId(rooms[i*12+j].getRoomID());
                    rooms[i*12+j].setStatus(2);
                    ok = 0;
                }
            }
    }



    public void signIn() {
        guiView.readUsername();
        guiView.readPassword();
        if(gModel.logIN() == 0){
            int credentialLevel = gModel.getCredentialLevel();
            switch (credentialLevel) {
                case 0: {
                    //create a manager object and the proper interface
                    manager.setLogInData(gModel.getUsername(), gModel.getPassword());
                    manDef();
                    break;
                }
                case 1: {
                    receptionistMenu();
                    break;
                }
                case 2: {
                    chiefMenu();
                    break;
                }
                case 3: {
                    MaidMenu();
                    break;
                }
                case 4: {
                    repairmanMenu();
                    break;
                }
                case 5: {
                    customer.initialize(gModel.getUsername(), gModel.getPassword(), gModel.getId());
                    customer.setInfo(gModel.getFirstName(), gModel.getLastName(), gModel.getPhoneNumber(),gModel.getEmail(), gModel.getAddress(), gModel.getSocialSecurityNumber(), gModel.getCreditCard());
                    customerMainWindow();
                    break;
                }
            }
        }
        else {
            guiView.showError("Invalid username or password!");
        }

    }

    public void chiefMenu(){
        guiView.reset();
        guiView.chiefMenu(new SetDateAndRoom(), new ReturnHomeListener());
    }

    class SetDateAndRoom implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           Date date1 = guiView.readCIDate();
           Date date2 = guiView.readCODate();
           String room = guiView.readPhoneForID();
          int total = chiefReceptionist.getRevenue(date1,date2,room);
           guiView.message("The earnings for room " + room + " are: " + total);
        }
    }




    public void receptionistMenu(){
        guiView.reset();
        guiView.receptionistMenu(new ReceptionistBookingL(), new ReceptionistCheckInL(),
                new ReceptionistCheckOut(), new ReceptionistRepairL(), new ReceptionistViewStatusL(), new ReturnHomeListener());
    }

    public void receptionistCheckInGetPhone(){
        guiView.reset();
        guiView.receptionistCheckInPhone(new CheckInPhone(), new ReceptionistBackL());
    }

    class CheckInPhone implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistCheckIn(guiView.readPhoneForID());
        }
    }

    public void receptionistCheckIn(String phone) {
        int id = sql.getNR("select login.userID from login join customerinfo on login.userID = customerinfo.ID where customerinfo.PhoneNumber= '" + phone + "';", "userID");
        booking.setCustomerId(id);
        customer.setId(id);
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
        String day = tday.format(today);
        int sum = sql.getSum("select Price from bookings where CheckInDate = '" + day + "' and CustomerID = " + id + " and CheckedIn=0;", "Price");
        int nr = sql.getSize("select Price from bookings where CheckInDate = '" + day + "' and CustomerID = " + id + " and CheckedIn=0;");
        if(nr==0)
            guiView.showError("No booking for today!");
        else if(nr ==1) {
            guiView.message("You checked in, the room number is " + sql.getRoomNr(customer.checkInQ(), "RoomID") + " and you paid " + sum + ". Your door code will be sent via SMS");
            int roomId = sql.getNR("select RoomID from bookings where CheckInDate = '" + day + "' and CustomerID = " + id + " and CheckedIn=0;", "RoomID");
            int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
            booking.updateBookingCheckInStatus(bookingID);
            booking.setPaid(bookingID);
            roomId = roomId/100*12 + roomId%100-1;
            rooms[roomId].setStatus(2);
        }
        else {
            guiView.message("You checked in, the room numbers are:  " + sql.getRoomNr(customer.checkInQ(), "RoomID") + " and you paid " + sum + ". Your door codes will be sent via SMS");
            while(nr!= 0){
                int roomId = sql.getNR("select RoomID from bookings where CheckInDate = '" + day + "' and CustomerID = " + id + " and CheckedIn=0;", "RoomID");
                int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
                booking.updateBookingCheckInStatus(bookingID);
                booking.setPaid(bookingID);
                roomId = roomId/100*12 + roomId%100-1;
                rooms[roomId].setStatus(2);
                nr--;
            }
        }
    }


    public void receptionistViewStatusL(){
        guiView.reset();
        guiView.fullStatus(new SeeFullStatus(), new ReceptionistCancelBookingL());

    }

    class SeeFullStatus implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String nr = guiView.readPhoneForID();
            String message = receptionist.getStatus(nr);
            guiView.message(message);
        }
    }

    public void receptionistChecOutnGetPhone(){
        guiView.reset();
        guiView.receptionistCheckOutPhone(new CheckOutPhone(), new ReceptionistBackL());
    }

    class CheckOutPhone implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistCheckOut(guiView.readPhoneForID());
        }
    }

    public void receptionistCheckOut(String phone){
        int id = sql.getNR("select login.userID from login join customerinfo on login.userID = customerinfo.ID where customerinfo.PhoneNumber= '" + phone + "';", "userID");
        booking.setCustomerId(id);
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
        String day = tday.format(today);
        customer.setId(id);
        int nr = sql.getSize("select Price from bookings where CheckOutDate = '" + day + "' and CustomerID = " + id + " and CheckedOut=0;");
        if(nr==0)
            guiView.showError("No check outs for today!");
        else if(nr ==1) {
            guiView.message("You checked out from the room number " + sql.getRoomNr(customer.checkOutQ(), "RoomID") + " have a nice day.");
            int roomId = sql.getNR("select RoomID from bookings where CheckOutDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedOut=0;", "RoomID");
            int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
            booking.updateBookingCheckOutStatus(bookingID);
            roomId = roomId/100*12 + roomId%100-1;
            rooms[roomId].setStatus(1);
        }
        else {
            guiView.message("You checked out, from the rooms: " + sql.getRoomNr(customer.checkOutQ(), "RoomID") + " have a nice day!");
            while(nr!= 0){
                int roomId = sql.getNR("select RoomID from bookings where CheckOutDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedOut=0;", "RoomID");
                int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
                booking.updateBookingCheckOutStatus(bookingID);
                roomId = roomId/100*12 + roomId%100-1;
                rooms[roomId].setStatus(1);
                nr--;
            }
        }
    }

    public void receptionistBooking(){
        guiView.reset();
        guiView.setBooking(new ReceptionistSetDateL(), new ReceptionistCancelBookingL());
    }



    public void receptionistRepair(){
        guiView.reset();
        guiView.requestRepair(new SubmitRepair(), new ReceptionistCancelBookingL());
    }

    class SubmitRepair implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String room = guiView.readPhoneForID();
            int roomId = Integer.parseInt(room);
            int id = roomId/100*12+roomId%100-1;
            rooms[id].setStatus(4);
            sql.executeStatement("update room set StatusID=4 where RoomID = " + roomId + ";");
        }
    }






    public void manDef(){
        guiView.reset();
        guiView.setManager(new ReturnHomeListener(), new ManagerButtonCreateAccount());
    }



    public boolean processManagerRequest(){
        String tmpUsername = guiView.getUserN();
        String tmpPassword = guiView.getPass();
        String control = gModel.verifyDataManager(tmpUsername,tmpPassword);
        boolean status;
        if(control.equals("good")) {
            manager.createAccountForStaff(tmpUsername, tmpPassword, gModel.getSelectedCredentialLevel(guiView.readAccountTypeChoice()));
            return true;
        }
        guiView.showError(control);
        return false;
    }

    public void customerMainWindow(){
        guiView.reset();
        guiView.setCustomerMainWindow(new BookingL(),new ActiveBookingsClientL(), new CancelBookingL(),new SCheckInL(), new SCheckOutL(), new ReturnHomeListener());
    }

    public void initializeRoom(){
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 12; j++){
                int id = i*100+j+1;
                rooms[i*12+j] = new Room(id);
                rooms[i*12+j].initializeStatus();
                rooms[i*12+j].setType();
            }
    }

    public void verifyRoomEmpty(){
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 12; j++){
                rooms[i*12+j].setStatusForDates(booking.getCheckInDate(), booking.getCheckOutDate());
            }
    }

    public int nrT1(){
        int nr = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 12; j++){
                if(rooms[i*12+j].getType() == 1 && rooms[i*12+j].getStatus() == 5)
                    nr++;
            }
        return nr;
    }

    public int nrT2(){
        int nr = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 12; j++){
                if(rooms[i*12+j].getType() == 2 && rooms[i*12+j].getStatus() == 5)
                    nr++;
            }
        return nr;
    }

    public int nrT3(){
        int nr = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 12; j++){
                if(rooms[i*12+j].getType() == 3 && rooms[i*12+j].getStatus() == 5)
                    nr++;
            }
        return nr;
    }

    public int nrT4(){
        int nr = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 12; j++){
                if(rooms[i*12+j].getType() == 4 && rooms[i*12+j].getStatus() == 5)
                    nr++;
            }
        return nr;
    }

    public int nrT5(){
        int nr = 0;
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 12; j++){
                if(rooms[i*12+j].getType() == 5 && rooms[i*12+j].getStatus() == 5)
                    nr++;
            }
        return nr;
    }

    public void maidStart(){
        guiView.reset();
        guiView.maidStart(new MaidBackL(), new MaidReadStartL(), maid.getArr(maid.getNrRoomsToBeCleaned()));
    }

    public void repairmanMenu(){
        guiView.reset();
        guiView.repairMan(new ReturnHomeListener(), new RepairmanStart());
    }

    public void startWorking(){
        guiView.reset();
        guiView.repairmanStart(new RepairmanReturnMenuL(), new RepairmanWork(), repairman.getArr(repairman.getRoomsToRepair()));
    }

    public void MaidMenu(){
        guiView.reset();
        guiView.MaidMenu(new ReturnHomeListener(), new MaidStartL(), new MaidFinishL());
    }

    public void maidFinish(){
        guiView.reset();
        guiView.maidFinish(new MaidBackL(), new MaidCleanedRoom(), maid.getArr(maid.getRoomsInCleaning()));
    }

    public void maidCleaned(){
        String room = guiView.readChoiceMaid();
        int roomNr = Integer.parseInt(room);
        roomNr = roomNr/100*12 + roomNr%100 - 1;
        rooms[roomNr].setStatus(5);
        maid.finishClean(room);
        maidFinish();
    }






    public void selfCheckOut(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
        String day = tday.format(today);
        int nr = sql.getSize("select Price from bookings where CheckOutDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedOut=0;");
        if(nr==0)
            guiView.showError("No check outs for today!");
        else if(nr ==1) {
            guiView.message("You checked out from the room number " + sql.getRoomNr(customer.checkOutQ(), "RoomID") + " have a nice day.");
            int roomId = sql.getNR("select RoomID from bookings where CheckOutDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedOut=0;", "RoomID");
            int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
            booking.updateBookingCheckOutStatus(bookingID);
            roomId = roomId/100*12 + roomId%100-1;
            rooms[roomId].setStatus(1);
        }
        else {
            guiView.message("You checked out, from the rooms: " + sql.getRoomNr(customer.checkOutQ(), "RoomID") + " have a nice day!");
            while(nr!= 0){
                int roomId = sql.getNR("select RoomID from bookings where CheckOutDate =' " + day + "' and CustomerID = " + customer.getID() + " and CheckedOut=0;", "RoomID");
                int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
                booking.updateBookingCheckOutStatus(bookingID);
                roomId = roomId/100*12 + roomId%100-1;
                rooms[roomId].setStatus(1);
                nr--;
            }
        }
    }

    public void setSelfCI(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
        String day = tday.format(today);
        int sum = sql.getSum("select Price from bookings where CheckInDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedIn=0;", "Price");
        int nr = sql.getSize("select Price from bookings where CheckInDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedIn=0;");
        if(nr==0)
            guiView.showError("No booking for today!");
        else if(nr ==1) {
            guiView.message("You checked in, the room number is " + sql.getRoomNr(customer.checkInQ(), "RoomID") + " and you paid " + sum + ". Your door code will be sent via SMS");
            int roomId = sql.getNR("select RoomID from bookings where CheckInDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedIn=0;", "RoomID");
            int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
            booking.updateBookingCheckInStatus(bookingID);
            booking.setPaid(bookingID);
            roomId = roomId/100*12 + roomId%100-1;
            rooms[roomId].setStatus(2);
        }
        else {
            guiView.message("You checked in, the room numbers are:  " + sql.getRoomNr(customer.checkInQ(), "RoomID") + " and you paid " + sum + ". Your door codes will be sent via SMS");
            while(nr!= 0){
            int roomId = sql.getNR("select RoomID from bookings where CheckInDate = '" + day + "' and CustomerID = " + customer.getID() + " and CheckedIn=0;", "RoomID");
            int bookingID = sql.getNR("select bookingID from bookings where roomID=" + roomId + ";", "bookingID");
            booking.updateBookingCheckInStatus(bookingID);
            booking.setPaid(bookingID);
            roomId = roomId/100*12 + roomId%100-1;
            rooms[roomId].setStatus(2);
            nr--;
            }
        }
    }

    public void customerActiveBooking(){
        guiView.reset();
        String data = customer.getBookings();
        guiView.customerActiveBooking(new ReturnHomeCustomerL(), data);
    }

    public void customerBooking(){
        guiView.reset();
        guiView.setCustomerBooking(new SetDateL(), new ReturnHomeCustomerL(), new SubmitBookingL());
    }




    class ReceptionistCheckInL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistCheckInGetPhone();
        }
    }

    class ReceptionistCheckOut implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistChecOutnGetPhone();
        }
    }


    class ReceptionistViewStatusL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistViewStatusL();
        }
    }


    class ReceptionistRepairL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistRepair();
        }
    }

    class ReceptionistBackL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistMenu();
        }
    }


    class ReceptionistSetDateL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String phone = guiView.readPhoneForID();
            int id =  sql.getNR("select login.userID from login join customerinfo on login.userID = customerinfo.ID where customerinfo.PhoneNumber= '" + phone + "';", "userID");
            booking.setCustomerId(id);
            booking.setDate(guiView.readCIDate(), guiView.readCODate());
            //booking.readPhone(guiView.readPhoneForID());
            if(booking.verifyDate()) {
                booking.setNrDays();
                guiView.BookingV2(nrT1(),nrT2(),nrT3(),nrT4(),nrT5(), new ReceptionistSetRoomType());
            }
            else{
                guiView.showWarning("Incorrect data introduced!");
            }
        }
    }

    class ReceptionistSetRoomType implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            booking.setRoomType(gModel.getRoomType(guiView.readRoomType()));
            booking.calculatePrice();
            //booking.setCustomerId(gModel.getId());
            guiView.BookingV3(booking.getPrice(), new ReceptionistSubmitBookingL());
        }
    }


    class ReceptionistBookingL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            receptionistBooking();
        }
    }

    class ReceptionistCancelBookingL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            guiView.reset();
            receptionistMenu();
        }
    }





    class ReceptionistSubmitBookingL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            getEmptyRoomID();
            booking.book();
            guiView.reset();
            booking.setCustomerId(gModel.getId());
            receptionistMenu();
        }
    }



    class RepairmanStart implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            startWorking();
        }
    }

    class RepairmanWork implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String room = guiView.readChoiceRepair();
            int roomNr = Integer.parseInt(room);
            roomNr = roomNr/100*12 + roomNr%100 - 1;
            rooms[roomNr].setStatus(5);
            repairman.finishRepair(room);
            guiView.reset();
            repairmanMenu();
        }
    }

    class RepairmanReturnMenuL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            repairmanMenu();
        }
    }


    class MaidReadStartL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String room = guiView.readChoiceMaid();
            int roomNr = Integer.parseInt(room);
            roomNr = roomNr/100*12 + roomNr%100 - 1;
            rooms[roomNr].setStatus(3);
            maid.startClean(room);
            guiView.reset();
            guiView.maidStart(new MaidBackL(), new MaidStartL(), maid.getArr(maid.getNrRoomsToBeCleaned()));
        }
    }


    class MaidStartL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            maidStart();
        }
    }

    class MaidFinishL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            maidFinish();
        }
    }

    class MaidBackL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            guiView.reset();
            MaidMenu();
        }
    }

    class MaidCleanedRoom implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            maidCleaned();
        }
    }


    class SubmitBookingL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            getEmptyRoomID();
            booking.book();
            guiView.reset();
            booking.setCustomerId(gModel.getId());
            customerMainWindow();
        }
    }


    class DeleteBookingCustomer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = guiView.readbookingID();
            int cID = sql.getClientIDForBooking("select CustomerID from bookings where bookingID = " + id + ";");
            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();
            SimpleDateFormat tday = new SimpleDateFormat("yyyy-MM-dd");
            String day = tday.format(today);
            int price = sql.getRoomPriceJoin("select roomtype.Price from roomtype join room on roomtype.TypeID = room.RoomType join bookings on room.RoomID = bookings.RoomID where bookingID= " + id + ";");
            Date checkin = sql.getDate("select CheckInDate from bookings where bookingID = " + id + ";", "CheckInDate");
            Date checkout = sql.getDate("select CheckOutDate from bookings where bookingID = " + id + ";", "CheckOutDate");
            boolean isCheckedIn = sql.isCheckedIn("select  CheckedIn from bookings where bookingID = " + id + ";");
            long difference = checkin.getTime() - today.getTime();
            if(cID == -1)
                guiView.showWarning("Booking id invalid!");
            else if(cID != customer.getID())
                guiView.showWarning("It's not you booking!");
            else if(isCheckedIn)
                guiView.showWarning("Already checked in!");
            else if(checkout.before(today))
                guiView.showWarning("Booking already finished");
            else if(day.equals(tday.format(checkin))) {
                guiView.showWarning("You paid " + price + " as the cancellation fee");
                booking.delete(id);
            }
            else {
                guiView.showWarning("You paid " + price * 0.2 + " as the cancellation fee");
                booking.delete(id);
            }
        }
    }

    public void dumpToSQL() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 12; j++) {
                rooms[i * 12 + j].dumpStatus();
            }
    }

    class ReturnHomeCustomerL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            guiView.reset();
            guiView.setCustomerMainWindow(new BookingL(),new ActiveBookingsClientL(), new CancelBookingL(),new SCheckInL(), new SCheckOutL(), new ReturnHomeListener());
        }
    }

    class SetDateL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            booking.setDate(guiView.readCIDate(), guiView.readCODate());

            if(booking.verifyDate()) {
                booking.setNrDays();
                guiView.customerBookingV2(nrT1(),nrT2(),nrT3(),nrT4(),nrT5(), new SetRoomType());
            }
                else{
                    guiView.showWarning("Incorrect data introduced!");
                }
            }
        }

    class SetRoomType implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                booking.setRoomType(gModel.getRoomType(guiView.readRoomType()));
                booking.calculatePrice();
                booking.setCustomerId(gModel.getId());
                guiView.customerBookingV3(booking.getPrice(), new SubmitBookingL());
            }
        }

    class ActiveBookingsClientL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            customerActiveBooking();
        }
    }

    class BookingL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            customerBooking();
        }
    }

    class CancelBookingL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           guiView.reset();
            guiView.customerCancelBooking(new ReturnHomeCustomerL(), new DeleteBookingCustomer());
        }
    }

    class SCheckInL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setSelfCI();
        }
    }

    class SCheckOutL implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selfCheckOut();
        }
    }

    class ManagerButtonCreateAccount implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean status = processManagerRequest();
            if(status)
                manDef();
        }
    }

    class ReturnHomeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            setMainMenu();
        }
    }

    class CreateUserAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            verifySignUpData();
        }
    }

    class SignInListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setSignIn();
        }
    }

    class SignUpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setSignUp();
        }
    }

    class LogInListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            signIn();

        }
    }

    class ExitListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            dumpToSQL();
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

}
