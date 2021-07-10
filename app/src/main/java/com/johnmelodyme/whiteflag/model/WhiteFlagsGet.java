package com.johnmelodyme.whiteflag.model;


import java.time.OffsetDateTime;

public class WhiteFlagsGet
{

    public String id;
    public String userName;
    public String phoneNumber;
    public String homeAddress;
    public String description;
    public int status;
    public String userIP;
    public String currentLocation;
    public String networkCarrier;
    public String createdAt;

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
        return homeAddress.toUpperCase();
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

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public void setCreatedAt(String value)
    {
        this.createdAt = value;
    }
}
