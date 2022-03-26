package model;

import entity.Discount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectDB;

public class DAODiscount extends ConnectDB {

    public int addDiscount(Discount d) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[discounts] ([discounttype],[stor_id],[lowqty],[highqty],[discount])\n"
                + "     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, d.getDiscounttype());
            pre.setString(2, d.getStor_id());
            pre.setInt(3, d.getLowqty());
            pre.setInt(4, d.getHighqty());
            pre.setDouble(5, d.getDiscount());
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Discount> searchName(String storID) {
        Vector vector = new Vector();
        String sql = "select * from discounts where discounttype like '%" + storID + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String discounttype = rs.getString(1);
                String stor_id = rs.getString(2);
                int lowqty = rs.getInt(3);
                int highqty = rs.getInt(4);
                double discount = rs.getDouble(5);
                Discount d = new Discount(discounttype, stor_id, lowqty, highqty, discount);
                vector.add(d);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updateDiscount(Discount d) {
        int n = 0;
        String sql = "  update discounts set stor_id = ?,lowqty = ?, highqty = ? WHERE discounttype = '" + d.getDiscounttype() + "' AND discount =" + d.getDiscount();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, d.getStor_id());
            if (d.getLowqty() == -1) {
                pre.setString(2, null);
            } else {
                pre.setInt(2, d.getLowqty());
            }
            if (d.getHighqty() == -1) {
                pre.setString(3, null);
            } else {
                pre.setInt(3, d.getHighqty());
            }
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeDiscount(String discounttype, String discount) {
        int n = 0;

        String sql = "delete from discounts where discounttype = '" + discounttype + "' AND discount =" + discount;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector viewAllDiscounts() {
        Vector<Discount> vector = new Vector<>();
        try {
            String sql = "select * from discounts";
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String discounttype = rs.getString(1);
                String stor_id = rs.getString(2);
                int lowqty = rs.getInt(3);
                int highqty = rs.getInt(4);
                double discount = rs.getDouble(5);
                Discount d = new Discount(discounttype, stor_id, lowqty, highqty, discount);
                vector.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
}
