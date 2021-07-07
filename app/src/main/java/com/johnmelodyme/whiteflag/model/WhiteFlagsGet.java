package com.johnmelodyme.whiteflag.model;


import java.time.OffsetDateTime;

public class WhiteFlagsGet
{
    private String id;
    private String userName;
    private String phoneNumber;
    private String homeAddress;
    private String description;
    private String userIP;
    private String currentLocation;
    private String networkCarrier;
    private String createdAt;

    public String getID()
    {
        return id;
    }

    public void setID(String value)
    {
        this.id = value;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String value)
    {
        this.userName = value;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String value)
    {
        this.phoneNumber = value;
    }

    public String getHomeAddress()
    {
        return homeAddress;
    }

    public void setHomeAddress(String value)
    {
        this.homeAddress = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String value)
    {
        this.description = value;
    }

    public String getUserIP()
    {
        return userIP;
    }

    public void setUserIP(String value)
    {
        this.userIP = value;
    }

    public String getCurrentLocation()
    {
        return currentLocation;
    }

    public void setCurrentLocation(String value)
    {
        this.currentLocation = value;
    }

    public String getNetworkCarrier()
    {
        return networkCarrier;
    }

    public void setNetworkCarrier(String value)
    {
        this.networkCarrier = value;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String value)
    {
        this.createdAt = value;
    }
}
