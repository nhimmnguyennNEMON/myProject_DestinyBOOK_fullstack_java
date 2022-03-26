package model;

import entity.Roysched;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectDB;

public class DAORoysched extends ConnectDB {

    public int addRoysched(Roysched r) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[roysched]([title_id],[lorange],[hirange],[royalty])\n"
                + "     VALUES (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, r.getTitle_id());
            pre.setInt(2, r.getLorange());
            pre.setInt(3, r.getHirance());
            pre.setInt(4, r.getRoyalty());
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Roysched> searchName(String roysched) {
        Vector vector = new Vector();
        String sql = "select * from roysched where title_id like '%" + roysched + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String title_id = rs.getString("title_id");
                int lorange = rs.getInt("lorange");
                int hirange = rs.getInt("hirange");
                int royalty = rs.getInt("royalty");
                Roysched obj = new Roysched(title_id, lorange, hirange, royalty);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updateRoysched(Roysched r) {
        int n = 0;
        try {
            String sql = "UPDATE roysched set lorange = ?, hirange = ? WHERE royalty = ? AND title_id = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, r.getLorange());
            pre.setInt(2, r.getHirance());
            pre.setInt(3, r.getRoyalty());
            pre.setString(4, r.getTitle_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeRoysched(String id, int royalty) {
        int n = 0;
        try {
            String sql = "delete from Roysched where title_id = '" + id + "' and royalty=" + royalty;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector viewAllRoysched() {
        Vector<Roysched> vector = new Vector<>();
        try {
            String sql = "select * from Roysched";
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String title_id = rs.getString("title_id");
                int lorange = rs.getInt("lorange");
                int hirange = rs.getInt("hirange");
                int royalty = rs.getInt("royalty");
                Roysched obj = new Roysched(title_id, lorange, hirange, royalty);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
}
