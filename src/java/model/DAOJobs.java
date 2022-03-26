
package model;

import entity.jobs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOJobs extends ConnectDB {

    public int addJobs(jobs j) {
        int n = 0;
        String sql = "INSERT [dbo].[jobs] ([job_desc], [min_lvl], [max_lvl]) VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, j.getJob_desc());
            stm.setInt(2, j.getMin_lvl());
            stm.setInt(3, j.getMax_lvl());
            n = stm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<jobs> searchName(String auName) {
        Vector vector = new Vector();
        String sql = "select * from jobs where job_desc like '%" + auName + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int job_id = rs.getInt(1);
                String job_desc = rs.getString(2);
                int min_lvl = rs.getInt(3);
                int max_lvl = rs.getInt(4);
                jobs obj = new jobs(job_id, job_desc, min_lvl, max_lvl);
                vector.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int updateJobs(jobs job) {
        int n = 0;
        String sql
                = "   UPDATE [jobs] set [job_desc]=?, [min_lvl]=?, [max_lvl]=? where Job_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, job.getJob_desc());
            pre.setInt(2, job.getMin_lvl());
            pre.setInt(3, job.getMax_lvl());
            pre.setInt(4, job.getJob_id());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public int removeJobs(int id) {
        int n = 0;
        try {
            String sql = "DELETE FROM [jobs] where Job_id = " + id;
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
        }
        return n;
    }

    public Vector<jobs> viewAllJobs() {
        String sql = "SELECT * FROM jobs";
        Vector<jobs> vector = new Vector<>();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                int job_id = rs.getInt(1);
                String job_desc = rs.getString(2);
                int min_lvl = rs.getInt(3);
                int max_lvl = rs.getInt(4);
                jobs obj = new jobs(job_id, job_desc, min_lvl, max_lvl);
                vector.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;

    }


}
