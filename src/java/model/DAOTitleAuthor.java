/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.ConnectDB;
import entity.TitleAuthor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOTitleAuthor extends ConnectDB {

    public int addTitleAuthor(TitleAuthor ta) {
        int n = 0;
        String sql = "INSERT INTO [titleauthor]([au_id],[title_id],"
                + "[au_ord],[royaltyper])VALUES(?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ta.getAu_id());
            pre.setString(2, ta.getTitle_id());
            pre.setInt(3, ta.getAu_ord());
            pre.setInt(4, ta.getRoyaltyper());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<TitleAuthor> searchName(String tauthor) {
        Vector vector = new Vector();
        String sql = "select * from TitleAuthor where title_id like '%" + tauthor + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String au_id = rs.getString(1);
                String title_id = rs.getString(2);
                int au_ord = rs.getInt(3);
                int royaltyper = rs.getInt(4);
                TitleAuthor obj = new TitleAuthor(au_id, title_id, au_ord, royaltyper);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updateTitleAuthor(TitleAuthor ta) {
        int n = 0;
        String sql = "UPDATE TitleAuthor set au_ord = ?, royaltyper = ?"
                + " where au_id = ? and title_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ta.getAu_ord());
            pre.setInt(2, ta.getRoyaltyper());
            pre.setString(3, ta.getAu_id());
            pre.setString(4, ta.getTitle_id());

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int removeTitleAuthorByAu_id(String au_id) {
        int n = 0;
        String sql = "DELETE FROM TitleAuthor where au_id='" + au_id + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeTitleAuthor(String title_id, String au_id) {
        int n = 0;
        String sql = "DELETE FROM TitleAuthor where title_id='" + title_id + "' AND au_id='" + au_id + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeTitleAuthorByTitle_id(String title_id) {
        int n = 0;
        String sql = "DELETE FROM TitleAuthor where title_id='" + title_id + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<TitleAuthor> viewAllTitleAuthor() {
        Vector<TitleAuthor> vector = new Vector<TitleAuthor>();
        try {
            String sql = "SELECT * from TitleAuthor";
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String au_id = rs.getString(1);
                String title_id = rs.getString(2);
                int au_ord = rs.getInt(3);
                int royaltyper = rs.getInt(4);
                TitleAuthor obj = new TitleAuthor(au_id, title_id, au_ord, royaltyper);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
}
