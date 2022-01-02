package com.example.hrtfitnessapp;

import javax.xml.namespace.QName;

public class UserInformation {
    public String EmailAddress, PersonName;
    public Boolean SurveyStatus;


    public UserInformation(){

    }

    public UserInformation(String EmailAddress,String PersonName){
        this.EmailAddress = EmailAddress;
        this.PersonName = PersonName;
        this.SurveyStatus = Boolean.FALSE;

    }
}
