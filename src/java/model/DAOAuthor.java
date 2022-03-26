/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.ConnectDB;
import entity.Authors;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOAuthor extends ConnectDB {

    public int addAuthors(Authors au) {
        int n = 0;
        String sql = "INSERT INTO [authors]([au_id],[au_lname],[au_fname],"
                + "[phone],[address],[city],[state],[zip],[contract])"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?; ? index start from 1
            //pre.setDataType(index,value)
            pre.setString(1, au.getAu_id());
            pre.setString(2, au.getAu_lname());
            pre.setString(3, au.getAu_fname());
            pre.setString(4, au.getPhone());
            pre.setString(5, au.getAddress());
            pre.setString(6, au.getCity());
            pre.setString(7, au.getState());
            pre.setString(8, au.getZip());
            pre.setInt(9, au.getContract());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Authors> searchName(String auName) {
        Vector vector = new Vector();
        String sql = "select * from authors where au_lname like '%" + auName + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String au_id = rs.getString(1);
                String au_lname = rs.getString(2);
                String au_fname = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip = rs.getString(8);
                int contract = rs.getInt(9);
                Authors obj = new Authors(au_id, au_lname, au_fname,
                        phone, address, city, state, zip, contract);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updateAuthors(Authors au) {
        int n = 0;
        String sql = "UPDATE Authors set au_lname = ?, au_fname = ?, phone = ?,address = ?, city = ?, state = ?, zip = ?, contract = ? where au_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, au.getAu_lname());
            pre.setString(2, au.getAu_fname());
            pre.setString(3, au.getPhone());
            pre.setString(4, au.getAddress());
            pre.setString(5, au.getCity());
            pre.setString(6, au.getState());
            pre.setString(7, au.getZip());
            pre.setInt(8, au.getContract());
            pre.setString(9, au.getAu_id());

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int removeAuthors(String au_id) {
        int n = 0;
        String sql = "DELETE FROM Authors where au_id='" + au_id + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Authors> viewAllAuthors() {
        Vector<Authors> author = new Vector<>();
        try {
            String sql = "SELECT * from Authors";
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String au_id = rs.getString(1);
                String au_lname = rs.getString(2);
                String au_fname = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip = rs.getString(8);
                int contract = rs.getInt(9);
                Authors obj = new Authors(au_id, au_lname, au_fname,
                        phone, address, city, state, zip, contract);
                author.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return author;
    }
}
