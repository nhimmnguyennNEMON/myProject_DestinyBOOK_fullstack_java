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
public class TitleAuthor {
    private String au_id,title_id;
    private int au_ord,royaltyper;

    public TitleAuthor() {
    }

    public TitleAuthor(String au_id, String title_id, int au_ord, int royaltyper) {
        this.au_id = au_id;
        this.title_id = title_id;
        this.au_ord = au_ord;
        this.royaltyper = royaltyper;
    }

    public String getAu_id() {
        return au_id;
    }

    public void setAu_id(String au_id) {
        this.au_id = au_id;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public int getAu_ord() {
        return au_ord;
    }

    public void setAu_ord(int au_ord) {
        this.au_ord = au_ord;
    }

    public int getRoyaltyper() {
        return royaltyper;
    }

    public void setRoyaltyper(int royaltyper) {
        this.royaltyper = royaltyper;
    }
    
}
