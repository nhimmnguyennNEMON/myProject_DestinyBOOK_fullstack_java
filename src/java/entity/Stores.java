/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class Stores {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   private String stor_id;
   private String stor_name;
   private String  stor_address;
   private String  city;
   private String  state;
   private String  zip;
   private String userName;
   private String password;

    public Stores() {
    }

    public Stores(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public Stores(String userName) {
        this.userName = userName;
    }
    
    public Stores(String stor_id, String stor_name, String stor_address, String city, String state, String zip) {
        this.stor_id = stor_id;
        this.stor_name = stor_name;
        this.stor_address = stor_address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Stores(String stor_id, String stor_name, String stor_address, String city, String state, String zip, String userName, String password) {
        this.stor_id = stor_id;
        this.stor_name = stor_name;
        this.stor_address = stor_address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.userName = userName;
        this.password = password;
    }

    public String getStor_id() {
        return stor_id;
    }

    public void setStor_id(String stor_id) {
        this.stor_id = stor_id;
    }

    public String getStor_name() {
        return stor_name;
    }

    public void setStor_name(String stor_name) {
        this.stor_name = stor_name;
    }

    public String getStor_address() {
        return stor_address;
    }

    public void setStor_address(String stor_address) {
        this.stor_address = stor_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
   
}
