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
public class Orders {

    private String stor_id;
    private String stor_name;
    private String stor_address;
    private String stor_state;
    private String stor_city;
    private String zip;
    private String ord_num;
    private String ord_date;
    private int ord_quantity;
    private String ord_payterms;
    private String title_id;
    private String title;
    private String type;
    private double price;
    private double advance;
    private int royalty;
    private int ytd_sales;
    private String notes;
    private String pub_date;
    private String pub_id;
    private String pub_name;
    private String pub_city;
    private String pub_state;
    private String pub_country;

    public Orders(String stor_id, String stor_name, String stor_address, String stor_state, String stor_city, String zip, String ord_num, String ord_date, int ord_quantity, String ord_payterms, String title_id, String title, String type, double price, double advance, int royalty, int ytd_sales, String notes, String pub_date, String pub_id, String pub_name, String pub_city, String pub_state, String pub_country) {
        this.stor_id = stor_id;
        this.stor_name = stor_name;
        this.stor_address = stor_address;
        this.stor_state = stor_state;
        this.stor_city = stor_city;
        this.zip = zip;
        this.ord_num = ord_num;
        this.ord_date = ord_date;
        this.ord_quantity = ord_quantity;
        this.ord_payterms = ord_payterms;
        this.title_id = title_id;
        this.title = title;
        this.type = type;
        this.price = price;
        this.advance = advance;
        this.royalty = royalty;
        this.ytd_sales = ytd_sales;
        this.notes = notes;
        this.pub_date = pub_date;
        this.pub_id = pub_id;
        this.pub_name = pub_name;
        this.pub_city = pub_city;
        this.pub_state = pub_state;
        this.pub_country = pub_country;
    }

    public Orders() {
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

    public String getStor_state() {
        return stor_state;
    }

    public void setStor_state(String stor_state) {
        this.stor_state = stor_state;
    }

    public String getStor_city() {
        return stor_city;
    }

    public void setStor_city(String stor_city) {
        this.stor_city = stor_city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getOrd_num() {
        return ord_num;
    }

    public void setOrd_num(String ord_num) {
        this.ord_num = ord_num;
    }

    public String getOrd_date() {
        return ord_date;
    }

    public void setOrd_date(String ord_date) {
        this.ord_date = ord_date;
    }

    public int getOrd_quantity() {
        return ord_quantity;
    }

    public void setOrd_quantity(int ord_quantity) {
        this.ord_quantity = ord_quantity;
    }

    public String getOrd_payterms() {
        return ord_payterms;
    }

    public void setOrd_payterms(String ord_payterms) {
        this.ord_payterms = ord_payterms;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public int getRoyalty() {
        return royalty;
    }

    public void setRoyalty(int royalty) {
        this.royalty = royalty;
    }

    public int getYtd_sales() {
        return ytd_sales;
    }

    public void setYtd_sales(int ytd_sales) {
        this.ytd_sales = ytd_sales;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
    }

    public String getPub_name() {
        return pub_name;
    }

    public void setPub_name(String pub_name) {
        this.pub_name = pub_name;
    }

    public String getPub_city() {
        return pub_city;
    }

    public void setPub_city(String pub_city) {
        this.pub_city = pub_city;
    }

    public String getPub_state() {
        return pub_state;
    }

    public void setPub_state(String pub_state) {
        this.pub_state = pub_state;
    }

    public String getPub_country() {
        return pub_country;
    }

    public void setPub_country(String pub_country) {
        this.pub_country = pub_country;
    }

    @Override
    public String toString() {
        return "Orders{" + "stor_id= " + stor_id + ", stor_name= " + stor_name + ", stor_address= " + stor_address + ", stor_state= " + stor_state + ", stor_city= " + stor_city + ", zip= " + zip
                + "\nord_num= " + ord_num + ", ord_date= " + ord_date + ", ord_quantity= " + ord_quantity + ", ord_payterms= " + ord_payterms 
                + "\ntitle_id= " + title_id + ", title= " + title + ", type= " + type + ", price= " + price + ", advance= " + advance + ", royalty= " + royalty + ", ytd_sales= " + ytd_sales + ", notes= " + notes + ", pub_date= " + pub_date 
                + "\npub_id= " + pub_id + ", pub_name= " + pub_name + ", pub_city= " + pub_city + ", pub_state= " + pub_state + ", pub_country= " + pub_country + '}';
    }
    
}
