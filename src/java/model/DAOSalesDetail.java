/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Orders;
import entity.Sales;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import model.ConnectDB;

public class DAOSalesDetail extends ConnectDB {

    public int updateSalesDetail(String storeID, String orderNumber, String titleID, int status) {
        int n = 0;
        String sql = "UPDATE [sales] SET [status] = ? WHERE [stor_id] = '" + storeID + "' AND [ord_num] = '" + orderNumber + "' AND [title_id] = '" + titleID + "'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public Vector displaySalesDetail() {
        Vector vector = new Vector();
        String sql = "SELECT stores.stor_id, dbo.stores.stor_name, dbo.sales.ord_num,dbo.titles.title, dbo.sales.qty,  dbo.titles.price  FROM Sales INNER JOIN Titles ON titles.title_id=sales.title_id INNER JOIN stores ON stores.stor_id=sales.stor_id";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String stor_id = rs.getString(1);
                String stor_name = rs.getString(2);
                String stor_address = rs.getString(3);
                String stor_state = rs.getString(4);
                String stor_city = rs.getString(5);
                String zip = rs.getString(6);
                String ord_num = rs.getString(7);
                String ord_date = rs.getString(8);
                int ord_quantity = rs.getInt(9);
                String ord_payterms = rs.getString(10);
                String title_id = rs.getString(11);
                String title = rs.getString(12);
                String type = rs.getString(13);
                double price = rs.getDouble(14);
                double advance = rs.getDouble(15);
                int royalty = rs.getInt(16);
                int ytd_sales = rs.getInt(17);
                String notes = rs.getString(18);
                String pub_date = rs.getString(19);
                String pub_id = rs.getString(20);
                String pub_name = rs.getString(21);
                String pub_city = rs.getString(22);
                String pub_state = rs.getString(23);
                String pub_country = rs.getString(24);
                Orders order = new Orders(stor_id, stor_name, stor_address, stor_state, stor_city, zip, ord_num, ord_date, ord_quantity, ord_payterms, title_id, title, type, price, advance, royalty, ytd_sales, notes, pub_date, pub_id, pub_name, pub_city, pub_state, pub_country);
                vector.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }
}
