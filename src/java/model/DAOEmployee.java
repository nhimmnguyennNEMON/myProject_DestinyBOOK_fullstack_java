/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Vector;
import model.ConnectDB;

public class DAOEmployee extends ConnectDB {

    public employee login(String username, String password) {
        employee login = null;
        String sql = "select * from employee where userName = ? and password =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                login = new employee(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }
    
    public Vector<employee> searchName(String auName) {
        Vector vector = new Vector();
        String sql = "select * from employee where fname like '%" + auName + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String emp_id = rs.getString(1);
                String fname = rs.getString(2);
                String minit = rs.getString(3);
                String lname = rs.getString(4);
                int job_id = rs.getInt(5);
                int job_lvl = rs.getInt(6);
                String pub_id = rs.getString(7);
                String hire_date = rs.getString(8);
                employee obj = new employee(emp_id, fname, minit, lname, job_lvl, job_id, pub_id, hire_date);
                vector.add(obj);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int addEmployee(employee e) {
        int n = 0;
        String sql = "INSERT [employee] ([emp_id], [fname], [minit], [lname], [job_id], [job_lvl], [pub_id], [hire_date]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, e.getEmp_id());
            stm.setString(2, e.getFname());
            stm.setString(3, e.getMinit());
            stm.setString(4, e.getLname());
            stm.setInt(5, e.getJob_id());
            stm.setInt(6, e.getJob_lvl());
            stm.setString(7, e.getPub_id());
            stm.setString(8, e.getHire_date());
            n = stm.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateEmployee(employee emp) {
        int n = 0;
        String sql = "update employee set fname = ?, minit = ?, lname = ?, job_id = ?,job_lvl=?,pub_id=?, hire_date=? where emp_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, emp.getFname());
            pre.setString(2, emp.getMinit());
            pre.setString(3, emp.getLname());
            pre.setInt(4, emp.getJob_id());
            pre.setInt(5, emp.getJob_lvl());
            pre.setString(6, emp.getPub_id());
            pre.setString(7, emp.getHire_date());
            pre.setString(8, emp.getEmp_id());

            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public int removeEmployee(String employeeid) {
        int n = 0;

        try {
            String sql = "DELETE FROM [employee]"
                    + "      WHERE emp_id = '" + employeeid + "'";
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
        }
        return n;
    }

    public Vector<employee> viewAllEmployee() {
        String sql = "SELECT * FROM [employee]";
        Vector<employee> em = new Vector<>();
        try {

            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String emp_id = rs.getString(1);
                String fname = rs.getString(2);
                String minit = rs.getString(3);
                String lname = rs.getString(4);
                int job_id = rs.getInt(5);
                int job_lvl = rs.getInt(6);
                String pub_id = rs.getString(7);
                String hire_date = rs.getString(8);
                employee obj = new employee(emp_id, fname, minit, lname, job_lvl, job_id, pub_id, hire_date);
                em.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return em;
    }
}
