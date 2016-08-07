package com.khurana.nikhil.tuhub;

/**
 * Created by nikhil on 20-04-2016.
 */
public class Event {
    String name,society,date,venue,url,details,rlink,rfee,email,contact;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRlink() {
        return rlink;
    }

    public void setRlink(String rlink) {
        this.rlink = rlink;
    }

    public String getRfee() {
        return rfee;
    }

    public void setRfee(String rfee) {
        this.rfee = rfee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Event() {


    }

    public Event(String name, String society, String date, String venue, String url, String details, String rlink, String rfee, String email, String contact) {
        this.name = name;
        this.society = society;
        this.date = date;
        this.venue = venue;
        this.url = url;
        this.details = details;
        this.rlink = rlink;
        this.rfee = rfee;
        this.email = email;
        this.contact = contact;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



