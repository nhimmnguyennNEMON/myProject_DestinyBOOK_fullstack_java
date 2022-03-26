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
public class Titles {
    private String title_id;
    private String title;
    private String type;
    private String pub_id;
    private double price;
    private double advance;
    private int royalty;
    private int ytd_sales;
    private String notes;
    private String pubdate;
    private String image;
    private int quantity;

    public Titles() {
    }

    public Titles(String title_id, String title, String type, String pub_id, double price, double advance, int royalty, int ytd_sales, String notes, String pubdate, String image) {
        this.title_id = title_id;
        this.title = title;
        this.type = type;
        this.pub_id = pub_id;
        this.price = price;
        this.advance = advance;
        this.royalty = royalty;
        this.ytd_sales = ytd_sales;
        this.notes = notes;
        this.pubdate = pubdate;
        this.image = image;
    }

    public Titles(String title_id, String title, String type, double price, double advance, int royalty, int ytd_sales, String notes, String pubdate, String image) {
        this.title_id = title_id;
        this.title = title;
        this.type = type;
        this.price = price;
        this.advance = advance;
        this.royalty = royalty;
        this.ytd_sales = ytd_sales;
        this.notes = notes;
        this.pubdate = pubdate;
        this.image = image;
    }

    public Titles(String title_id, String title, double price, int quantity) {
        this.title_id = title_id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getPub_id() {
        return pub_id;
    }

    public void setPub_id(String pub_id) {
        this.pub_id = pub_id;
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

 

    public String getPubdate() {
        return pubdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
       @Override
    public String toString() {
        return String.format("title_id = %s, title = %s, type = %s, pub_id = %s, price = %.2f, advance = %.2f, royalty = %d, ytd_sales = %d, notes = %s, pubdate = %s, image = %s",
                            title_id ,  title , type,pub_id,  price , advance, royalty,ytd_sales, notes , pubdate, image);
                     
    }
}
