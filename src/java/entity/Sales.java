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
public class Sales {

   private String stor_id;
    private String ord_num;
    private String ord_date;
    private int qty;
    private String payterms;
    private String title_id;
    private int status;

    public Sales() {
    }

    public Sales(String stor_id, String ord_num, String ord_date, int qty, String payterms, String title_id) {
        this.stor_id = stor_id;
        this.ord_num = ord_num;
        this.ord_date = ord_date;
        this.qty = qty;
        this.payterms = payterms;
        this.title_id = title_id;
    }

    public Sales(String stor_id, String ord_num, String ord_date, int qty, String payterms, String title_id, int status) {
        this.stor_id = stor_id;
        this.ord_num = ord_num;
        this.ord_date = ord_date;
        this.qty = qty;
        this.payterms = payterms;
        this.title_id = title_id;
        this.status = status;
    }



    public Sales(String ord_date, int qty, String payterms) {
        this.ord_date = ord_date;
        this.qty = qty;
        this.payterms = payterms;
    }

    public String getStor_id() {
        return stor_id;
    }

    public void setStor_id(String stor_id) {
        this.stor_id = stor_id;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPayterms() {
        return payterms;
    }

    public void setPayterms(String payterms) {
        this.payterms = payterms;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}

