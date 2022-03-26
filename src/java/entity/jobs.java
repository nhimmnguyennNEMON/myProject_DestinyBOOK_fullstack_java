/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class jobs {

    private int job_id;
    private String job_desc;
    private int min_lvl, max_lvl;

    public jobs() {
    }

    public jobs(String job_desc, int min_lvl, int max_lvl) {
        this.job_desc = job_desc;
        this.min_lvl = min_lvl;
        this.max_lvl = max_lvl;
    }

    public jobs(int job_id, String job_desc, int min_lvl, int max_lvl) {
        this.job_id = job_id;
        this.job_desc = job_desc;
        this.min_lvl = min_lvl;
        this.max_lvl = max_lvl;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    public int getMin_lvl() {
        return min_lvl;
    }

    public void setMin_lvl(int min_lvl) {
        this.min_lvl = min_lvl;
    }

    public int getMax_lvl() {
        return max_lvl;
    }

    public void setMax_lvl(int max_lvl) {
        this.max_lvl = max_lvl;
    }

}
