/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Sales;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import model.ConnectDB;

public class DAOSales extends ConnectDB {

    public int addSales(Sales sale) {
        int n = 0;
        String sql = "INSERT INTO [sales]"
                + "           ([stor_id],[ord_num],[ord_date],[qty],[payterms],[title_id])"
                + "     VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sale.getStor_id());
            pre.setString(2, sale.getOrd_num());
            pre.setString(3, sale.getOrd_date());
            pre.setInt(4, sale.getQty());
            pre.setString(5, sale.getPayterms());
            pre.setString(6, sale.getTitle_id());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public Vector<Sales> searchName(String sale) {
        Vector vector = new Vector();
        String sql = "select * from sales where ord_num like '%" + sale + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String stor_id = rs.getString(1);
                String ord_num = rs.getString(2);
                String ord_date = rs.getString(3);
                int qty = rs.getInt(4);
                String payterm = rs.getString(5);
                String title_id = rs.getString(6);
                int status = rs.getInt(7);

                Sales obj = new Sales(stor_id, ord_num, ord_date, qty, payterm, title_id, status);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updateSales(Sales sale) {
        int n = 0;
        String sql
                = "UPDATE [sales]"
                + "   SET [stor_id] = ?"
                + "      ,[ord_num] = ?"
                + "      ,[ord_date] = ?"
                + "      ,[qty] = ?"
                + "      ,[payterms] = ?"
                + "      ,[title_id] = ?"
                + " WHERE [stor_id] = '" + sale.getStor_id() + "' AND [ord_num] = '" + sale.getOrd_num() + "' AND [title_id] = '" + sale.getTitle_id() + "'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sale.getStor_id());
            pre.setString(2, sale.getOrd_num());
            pre.setString(3, sale.getOrd_date());
            pre.setInt(4, sale.getQty());
            pre.setString(5, sale.getPayterms());
            pre.setString(6, sale.getTitle_id());

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public int removeSale(String StoreID, String orderNum, String titleID) {
        int n = 0;

        try {
            String sql = "DELETE FROM [sales]"
                    + "WHERE sales.stor_id = '" + StoreID + "' AND sales.ord_num = '" + orderNum + "' AND sales.title_id = '" + titleID + "'";
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
        }
        return n;
    }

    public Vector<Sales> viewAllSales() {
        String sql = "SELECT * FROM [sales]";
        Vector<Sales> vector = new Vector<>();
        try {

            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String stor_id = rs.getString(1);
                String ord_num = rs.getString(2);
                String ord_date = rs.getString(3);
                int qty = rs.getInt(4);
                String payterm = rs.getString(5);
                String title_id = rs.getString(6);
                int status = rs.getInt(7);

                Sales obj = new Sales(stor_id, ord_num, ord_date, qty, payterm, title_id, status);
                vector.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }

    public int removeSale(String ordnum) {
        int n = 0;

        try {
            String sql = "delete from sales where  sales.ord_num = '"+ordnum+"'";
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
        }
        return n;
    }

}
