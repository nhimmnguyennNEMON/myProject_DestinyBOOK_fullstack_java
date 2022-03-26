/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author pv
 */
public class Discount {
    private String discounttype;
    private String stor_id;
    private int lowqty;
    private int highqty;
    private double discount;

    public Discount() {
    }

    public Discount(String discounttype, String stor_id, int lowqty, int highqty, double discount) {
        this.discounttype = discounttype;
        this.stor_id = stor_id;
        this.lowqty = lowqty;
        this.highqty = highqty;
        this.discount = discount;
    }

    public String getDiscounttype() {
        return discounttype;
    }

    public void setDiscounttype(String discounttype) {
        this.discounttype = discounttype;
    }

    public String getStor_id() {
        return stor_id;
    }

    public void setStor_id(String stor_id) {
        this.stor_id = stor_id;
    }

    public int getLowqty() {
        return lowqty;
    }

    public void setLowqty(int lowqty) {
        this.lowqty = lowqty;
    }

    public int getHighqty() {
        return highqty;
    }

    public void setHighqty(int highqty) {
        this.highqty = highqty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
}
