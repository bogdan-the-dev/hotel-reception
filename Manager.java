package com.Bogdan;

public class Manager {
    private SQL sql = new SQL();

    private String UserName;
    private String Password;
    private int level = 0;

    public void setLogInData(String userName, String password){
        this.UserName = userName;
        this.Password = password;
    }

    public void createAccountForStaff(String inputUserName, String inputPassword, int inputAccountType){
        String statement = sql.generateCreateLogInStatement(inputUserName,inputPassword, inputAccountType);
        sql.executeStatement(statement);
    }

}
