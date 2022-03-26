/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.publishers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import model.ConnectDB;

public class DAOPublishers extends ConnectDB {

    public int addPublishers(publishers p) {
        int n = 0;
        String sql = "INSERT dbo.publishers (pub_id, pub_name, city, state, country) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, p.getPub_id());
            stm.setString(2, p.getPub_name());
            stm.setString(3, p.getCity());
            stm.setString(4, p.getState());
            stm.setString(5, p.getCountry());
            n = stm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<publishers> searchName(String publisher) {
        Vector vector = new Vector();
        String sql = "select * from publishers where pub_name like '%" + publisher + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pub_id = rs.getString(1);
                String pub_name = rs.getString(2);
                String city = rs.getString(3);
                String state = rs.getString(4);
                String country = rs.getString(5);
                publishers obj = new publishers(pub_id, pub_name, city, state, country);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updatePublishers(publishers pub) {
        int n = 0;
        String sql
                = "   UPDATE publishers set pub_name=?, city=?, state=?, country=? where pub_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pub.getPub_name());
            pre.setString(2, pub.getCity());
            pre.setString(3, pub.getState());
            pre.setString(4, pub.getCountry());
            pre.setString(5, pub.getPub_id());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public int removePublishers(String id) {
        int n = 0;
        try {
            String sql = "DELETE FROM publishers where pub_id = '" + id + "'";
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
        }
        return n;
    }

    public Vector<publishers> viewAllpublishers() {
        String sql = "SELECT * FROM publishers";
        Vector<publishers> vector = new Vector<>();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String pub_id = rs.getString(1);
                String pub_name = rs.getString(2);
                String city = rs.getString(3);
                String state = rs.getString(4);
                String country = rs.getString(5);
                publishers obj = new publishers(pub_id, pub_name, city, state, country);
                vector.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;

    }

    public static void main(String args) {
        DAOPublishers dao = new DAOPublishers();
        dao.viewAllpublishers();
    }
}
