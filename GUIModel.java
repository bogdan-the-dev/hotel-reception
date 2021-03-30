package com.Bogdan;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.time.*;

public class GUIModel {
    SQL sql = new SQL();

    private int id;
    private int credentialLevel;

    private String username;
    private String password;
    private String password2;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private String socialSecurityNumber;
    private String creditCard;

    private Date cIDate;
    private Date cODate;

    private int nrDays;
    private int price;

    private Customer customer;
    private Manager manager;



    public GUIModel() {
        reset();
    }

    /**
     * Reset to default values
     */
    public void reset() {
        id = -1;
        username = "";
        password = "";
        password2 = "";
        firstName = "";
        lastName = "";
        phoneNumber = "";
        email = "";
        address = "";
        socialSecurityNumber = "";
        creditCard = "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setCIDate(Date date){
        this.cIDate = date;
    }

    public void setCODate(Date date){
        this.cODate = date;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public int getCredentialLevel(){
        return this.credentialLevel;
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String verifyDataManager(String tmpUsername, String tmpPassword){
        if(tmpUsername == null || tmpUsername.length() == 0)
            return "Username required!";
        if(!sql.verifyAvailableCustomerUsername(tmpUsername))
            return "Username already in use!";
        if(tmpPassword == null || tmpPassword.length() == 0)
            return "Password required!";
        return "good";
    }

    public String verifyInputData(){
        String errorMsg="none";
        System.out.println(phoneNumber);
        if(username == null || username.length() == 0)
            return "Username required!";
        else if(!sql.verifyAvailableCustomerUsername(username))
            return "Username already in use!";
        if (password == null || password.length() == 0)
            return "Password field required!";
        if (password2 == null || password2.length() == 0)
            return "Confirm Password field required!";
        if(!password.equals(password2))
            return "Passwords do not match!";
        if (firstName == null || firstName.length() == 0)
            return "First Name field required!";
        if(lastName == null || lastName.length() == 0)
            return "Last Name field required!";
        if(phoneNumber == null)
            return "Phone Number field required!";
        if(phoneNumber.length() != 10)
            return "Please insert a valid phone number!";
        if(email == null || email.length() <= 5)
            return "Please insert a valid email adress!";
        if(address == null || address.length() == 0)
            return "Address field required!";
        if(socialSecurityNumber == null || socialSecurityNumber.length() == 0)
            return "Social Security Number required!";
        if(creditCard == null || creditCard.length() == 0)
            return "Credit Card required!";
        if(!password.equals(password2))
            return "Passwords do not match!";
        return errorMsg;
    }

    public void createUserAccount(){
        sql.executeStatement(sql.generateCreateLogInStatement(username, password, 5));
        sql.setCustomerInfo(firstName, lastName, phoneNumber, email, address, socialSecurityNumber, creditCard, sql.getID(sql.createLogInStatement(username, password)));
    }

    public int logIN(){
        int id;
        id = sql.getID(sql.createLogInStatement(username,password));
        if(id == -1)
            return -1;
        else{
            setId(id);
            setCredentialLevel();
        }
        return 0;
    }

    public void setCredentialLevel(){
        this.credentialLevel = sql.login(sql.createLogInStatement(username, password));
    }

    public int getSelectedCredentialLevel(String selection){
        if(selection.equals("Manager"))
             return 0;
        if(selection.equals("Receptionist"))
             return 1;
        if(selection.equals("Chief Receptionist"))
            return 2;
        if(selection.equals("Maid"))
            return 3;
        if(selection.equals("Repairman"))
            return 4;
        return -1;
    }

    public int getRoomType(String str){
        if(str.equals("Basic single Room"))
            return 1;
        if(str.equals("Basic Room with double bed"))
            return 2;
        if(str.equals("Premium Room with double bed"))
            return 3;
        if(str.equals("Apartment with 2 rooms"))
            return 4;
        return 5;
    }


}
