/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class Authors {
    private String au_id,au_lname, au_fname,phone,address,city,state,zip;
    private int contract;
    
    public Authors(){  
    }

    public Authors(String au_id, String au_lname, String au_fname, String phone, String address, String city, String state, String zip, int contract) {
        this.au_id = au_id;
        this.au_lname = au_lname;
        this.au_fname = au_fname;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.contract = contract;
    }

    public String getAu_id() {
        return au_id;
    }

    public void setAu_id(String au_id) {
        this.au_id = au_id;
    }

    public String getAu_lname() {
        return au_lname;
    }

    public void setAu_lname(String au_lname) {
        this.au_lname = au_lname;
    }

    public String getAu_fname() {
        return au_fname;
    }

    public void setAu_fname(String au_fname) {
        this.au_fname = au_fname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getContract() {
        return contract;
    }

    public void setContract(int contract) {
        this.contract = contract;
    }

    
    
    
}
