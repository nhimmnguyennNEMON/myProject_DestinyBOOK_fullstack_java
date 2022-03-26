/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.pub_info;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import model.ConnectDB;

public class DAOPub_info extends ConnectDB {

    public int addPub_info(pub_info p) {
        int n = 0;
        String sql = "INSERT [dbo].[pub_info] ([pub_id], [pr_info]) VALUES (?,?)";
        try {
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, p.getPub_id());
            stm.setString(2, p.getPr_info());
            n = stm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<pub_info> searchName(String auName) {
        Vector vector = new Vector();
        String sql = "select * from pub_info where pub_id like '%" + auName + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String pub_id = rs.getString(1);
                String logo = rs.getString(2);
                String pr_desc = rs.getString(3);
                pub_info obj = new pub_info(pub_id, logo, pr_desc);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updatePub_info(pub_info info) {
        int n = 0;
        String sql
                = "   UPDATE [Pub_info] set [pr_info]=? where [pub_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, info.getPr_info());
            pre.setString(2, info.getPub_id());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public int removePub_info(String id) {
        int n = 0;
        try {
            String sql = "DELETE FROM [Pub_info] where pub_id = '" + id + "'";
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
        }
        return n;
    }

    public Vector viewAllPub_info() {
        String sql = "SELECT * FROM Pub_info";
        Vector<pub_info> pub = new Vector<>();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String pub_id = rs.getString(1);
                String logo = rs.getString(2);
                String pr_desc = rs.getString(3);
                pub_info obj = new pub_info(pub_id, logo, pr_desc);
                pub.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pub;
    }
}
