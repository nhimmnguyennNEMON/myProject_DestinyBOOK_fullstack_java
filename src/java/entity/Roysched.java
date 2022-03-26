
package entity;

public class Roysched {
    private String title_id;
    private int lorange;
    private int hirance;
    private int royalty;

    public Roysched() {
    }

    public Roysched(String title_id, int lorange, int hirance, int royalty) {
        this.title_id = title_id;
        this.lorange = lorange;
        this.hirance = hirance;
        this.royalty = royalty;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public int getLorange() {
        return lorange;
    }

    public void setLorange(int lorange) {
        this.lorange = lorange;
    }

    public int getHirance() {
        return hirance;
    }

    public void setHirance(int hirance) {
        this.hirance = hirance;
    }

    public int getRoyalty() {
        return royalty;
    }

    public void setRoyalty(int royalty) {
        this.royalty = royalty;
    }
    
    
}
