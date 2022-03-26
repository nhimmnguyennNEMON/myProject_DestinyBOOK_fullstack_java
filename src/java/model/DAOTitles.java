package model;

import entity.Titles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOTitles extends ConnectDB {

    public void addTitles(Titles title) {
        int n = 0;
        String sql = "INSERT INTO titles"
                + "([title_id],[title],[type],[pub_id],[price],[advance],[royalty],[ytd_sales],[notes],[pubdate], [image])"
                + "VALUES(?,?,?,?,?,?,?,?,?,?, ?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, title.getTitle_id());
            pre.setString(2, title.getTitle());
            pre.setString(3, title.getType());
            pre.setString(4, title.getPub_id());
            pre.setDouble(5, title.getPrice());
            pre.setDouble(6, title.getAdvance());
            pre.setInt(7, title.getRoyalty());
            pre.setInt(8, title.getYtd_sales());
            pre.setString(9, title.getNotes());
            pre.setString(10, title.getPubdate());
            pre.setString(11, title.getImage());

            n = pre.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTitles(Titles title) {
        int n = 0;
        String sql
                = "   UPDATE [titles]"
                + "   SET [title] = ?"
                + "      ,[type] = ?"
                + "      ,[pub_id] = ?"
                + "      ,[price] = ?"
                + "      ,[advance] = ?"
                + "      ,[royalty] = ?"
                + "      ,[ytd_sales] = ?"
                + "      ,[notes] = ?"
                + "      ,[pubdate] = ?"
                + "      ,[image] = ?"
                + "        WHERE [title_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, title.getTitle());
            pre.setString(2, title.getType());
            pre.setString(3, title.getPub_id());
            pre.setDouble(4, title.getPrice());
            pre.setDouble(5, title.getAdvance());
            pre.setInt(6, title.getRoyalty());
            pre.setInt(7, title.getYtd_sales());
            pre.setString(8, title.getNotes());
            pre.setString(9, title.getPubdate());
            pre.setString(10, title.getImage());
            pre.setString(11, title.getTitle_id());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int changePrice(String titleID, double price) {
        int n = 0;
        String sql = "   UPDATE [titles]"
                + "   SET [price] = " + price + " WHERE title_id = '" + titleID + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;

    }

    public void removeTitles(String id) {

        try {
            String sql = "DELETE FROM [titles]"
                    + "   WHERE title_id='" + id + "'";
            Statement state = conn.createStatement();
            state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Vector<Titles> viewAllTitles() {
        String sql = "SELECT * FROM titles";
        Vector<Titles> vector = new Vector<Titles>();
        try {

            ResultSet rs = getData(sql);
            while (rs.next()) {
                String title_id = rs.getString(1);
                String title = rs.getString(2);
                String type = rs.getString(3);
                String pub_id = rs.getString(4);
                double price = rs.getDouble(5);
                double advance = rs.getDouble(6);
                int royalty = rs.getInt(7);
                int ytd_sales = rs.getInt(8);
                String notes = rs.getString(9);
                String pubdate = rs.getString(10);
                String image = rs.getString(11);
                Titles obj = new Titles(title_id, title, type, pub_id, price, advance, royalty, ytd_sales, notes, pubdate, image);
                vector.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;

    }

    public Vector<Titles> searchName(String titleName) {
        Vector vector = new Vector();
        String sql = "select * from titles where title like '%" + titleName + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String title_id = rs.getString(1);
                String title = rs.getString(2);
                String type = rs.getString(3);
                String pub_id = rs.getString(4);
                double price = rs.getDouble(5);
                double advance = rs.getDouble(6);
                int royalty = rs.getInt(7);
                int ytd_sales = rs.getInt(8);
                String notes = rs.getString(9);
                String pubdate = rs.getString(10);
                String image = rs.getString(11);
                Titles obj = new Titles(title_id, title, type, pub_id, price, advance, royalty, ytd_sales, notes, pubdate, image);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public Vector<Titles> filterPub(String pubname) {
        Vector vector = new Vector();
        String sql = "select * from titles where pub_id like '"+ pubname +"'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String title_id = rs.getString(1);
                String title = rs.getString(2);
                String type = rs.getString(3);
                String pub_id = rs.getString(4);
                double price = rs.getDouble(5);
                double advance = rs.getDouble(6);
                int royalty = rs.getInt(7);
                int ytd_sales = rs.getInt(8);
                String notes = rs.getString(9);
                String pubdate = rs.getString(10);
                String image = rs.getString(11);
                Titles obj = new Titles(title_id, title, type, pub_id, price, advance, royalty, ytd_sales, notes, pubdate, image);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public Vector<Titles> searchID(String titleName) {
        Vector vector = new Vector();
        String sql = "select * from titles where title_id like '%" + titleName + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String title_id = rs.getString(1);
                String title = rs.getString(2);
                String type = rs.getString(3);
                String pub_id = rs.getString(4);
                double price = rs.getDouble(5);
                double advance = rs.getDouble(6);
                int royalty = rs.getInt(7);
                int ytd_sales = rs.getInt(8);
                String notes = rs.getString(9);
                String pubdate = rs.getString(10);
                String image = rs.getString(11);
                Titles obj = new Titles(title_id, title, type, pub_id, price, advance, royalty, ytd_sales, notes, pubdate, image);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector searchByPrice(double from, double to) {
        Vector vector = new Vector();
        String sql = "SELECT * From titles where price BETWEEN " + from + " AND " + to + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String title_id = rs.getString(1);
                String title = rs.getString(2);
                String type = rs.getString(3);
                String pub_id = rs.getString(4);
                double price = rs.getDouble(5);
                double advance = rs.getDouble(6);
                int royalty = rs.getInt(7);
                int ytd_sales = rs.getInt(8);
                String notes = rs.getString(9);
                String pubdate = rs.getString(10);
                String image = rs.getString(11);
                Titles obj = new Titles(title_id, title, type, pub_id, price, advance, royalty, ytd_sales, notes, pubdate, image);
                vector.add(obj);

            }
            if (vector.isEmpty()) {
                System.out.println("No result of price from " + from + "to" + to);
                System.exit(0);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;

    }
}
