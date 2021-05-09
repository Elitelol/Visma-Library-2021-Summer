package com.example.VismaLibrary2021.DataStructures;

public class UserForm {
    private final String name;
    private final String periodDate;
    private final String guid;

    public UserForm(String name, String periodDate, String guid) {
        this.name = name;
        this.periodDate = periodDate;
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public String getPeriodDate() {
        return periodDate;
    }

    public String getGuid() {
        return guid;
    }
}
