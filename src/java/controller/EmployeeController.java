/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOEmployee;
import javax.servlet.RequestDispatcher;


public class EmployeeController extends HttpServlet {

    DAOEmployee dao = new DAOEmployee();

    public String checkString(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return string;
        }
    }

    public String checkNumber(String number) {
        if (number == null || number.isEmpty()) {
            number = "0";
        }
        return number;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //get service
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) { // call controller direct
                service = "listAllEmployees";
            }

            switch (service) {

                default:
                    Vector<employee> vector = new Vector<>();
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        submit = "ListAll";
                    }
                    switch (submit) {
                        case "Search":
                            String name = request.getParameter("name");
                            vector = dao.searchName(name);
                            break;
                        default:
                            vector = dao.viewAllEmployee();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Employees Manage");
                    request.setAttribute("TABLE_TITLE", "List All Employees");
                    dispath(request, response, "/JSP/Admin/Display/DisplayEmployee.jsp");
                    break;
                case "insertEmployee":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Employee</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf("<form action='EmployeeController' method='post'  style=\"margin:5rem;\">\n"
                                + "            <table class='myForm'>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='empid'>Employee ID</label></td>\n"
                                + "                    <td><input class='form-control' type='text' id ='empid' name='empid'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='fname'>Last Name</label></td>\n"
                                + "                    <td><input class='form-control' type='text' id ='fname' name='fname' maxlength='40'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='minit'>Middle Name (first character)</label></td>\n"
                                + "                    <td><input class='form-control' type='text' id ='minit' name='minit' maxlength='1'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='lname'>First Name</label></td>\n"
                                + "                    <td><input class='form-control' type='text' id ='lname' name='lname' maxlength='40'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='jobid'>jobid</label></td>\n"
                                + "                    <td>\n"
                                + "                        <select class='form-select' name='jobid' id='pubid'>");
                        ResultSet rs1 = dao.getData("select * from jobs");
                        while (rs1.next()) {
                            out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                        }
                        out.print("                 </select>\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='joblvl'>Job lvl</label></td>\n"
                                + "                    <td><input class='form-control' type='number' id ='minlvl' name='minlvl'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='pubid'>Publisher ID</label></td>\n"
                                + "                    <td>\n"
                                + "                        <select class='form-select' name='pubid' id='pubid'>\n");
                        rs1 = dao.getData("select * from publishers");
                        while (rs1.next()) {
                            out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                        }
                        out.print("                 </select>\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                        <td><label for='hiredate'>Hire Date</label></td>\n"
                                + "                        <td><input class='form-control' type='date' id ='hiredate' name='hiredate'></td>\n"
                                + "                    </tr>\n"
                                + "                <tr>\n"
                                + "                    <td>\n"
                                + "                        <input class=\"submitkey\" type='submit' value='Insert to Database'>\n"
                                + "                    </td>\n"
                                + "                    <td>\n"
                                + "                        <input class=\"submitkey\" type='submit' value='Go Back'>\n"
                                + "                    </td>\n"
                                + "                    <td>\n"
                                + "                        <input class=\"submitkey\" type='reset' value='Reset'>\n"
                                + "                    </td>\n"
                                + "                </tr> \n"
                                + "            </table>\n"
                                + "        </form>");
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String emp_id = request.getParameter("empid");
                                String fname = request.getParameter("fname");
                                String minit = request.getParameter("minit");
                                String lname = request.getParameter("lname");
                                int job_id = Integer.parseInt(checkNumber(request.getParameter("jobid")));
                                int job_lvl = Integer.parseInt(checkNumber(request.getParameter("joblvl")));
                                String pub_id = request.getParameter("pubid");
                                String hire_date = request.getParameter("hiredate");

                                if (checkString(emp_id) == null
                                        || checkString(fname) == null
                                        || checkString(lname) == null
                                        || checkString(pub_id) == null
                                        || checkString(hire_date) == null) {
                                    out.print("<h2>Cannot not leave input empty</h2>");
                                    return;
                                }

                                employee ojb = new employee(emp_id, fname, minit, lname, job_id, job_lvl, pub_id, hire_date);
                                int n = dao.addEmployee(ojb);
                                response.sendRedirect("EmployeeController");
                                break;
                            default:
                                response.sendRedirect("EmployeeController");
                                break;
                        }
                    }

                    break;
                case "updateEmployee":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) { // chua nhan button submit
                        String employeeID = request.getParameter("employeeID");
                        String sql = "select * from employee where emp_id='" + employeeID + "'";
                        ResultSet rs = dao.getData(sql);
                        ResultSet rs1 = dao.getData("select * from jobs");
                        request.setAttribute("rsJob", rs1);
                        request.setAttribute("rsEmployee", rs);
                        ResultSet rs2 = dao.getData("SELECT * FROM dbo.publishers");
                        request.setAttribute("rsPublisher", rs2);
                        dispath(request, response, "/JSP/Admin/Update/UpdateEmployee.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String emp_id = request.getParameter("empid");
                                String fname = request.getParameter("fname");
                                String minit = request.getParameter("minit");
                                String lname = request.getParameter("lname");
                                int job_lvl = Integer.parseInt(checkNumber(request.getParameter("joblvl")));
                                int job_id = Integer.parseInt(checkNumber(request.getParameter("jobid")));
                                String pub_id = request.getParameter("pubid");
                                String hire_date = request.getParameter("hiredate");
                                if (minit.equals("null")) {
                                    minit=null;
                                }
                                employee ojb = new employee(emp_id, fname, minit, lname, job_lvl, job_id, pub_id, hire_date);

                                dao.updateEmployee(ojb);
                                response.sendRedirect("EmployeeController");
                                break;
                            default:
                                response.sendRedirect("EmployeeController");
                                break;
                        }
                    }
                    break;

                case "deleteEmployee":
                    String submitDelete = request.getParameter("submitDelete");

                    if (submitDelete == null) {
                        String employeeID = request.getParameter("employeeID");
                        String sql = "select * from employee where emp_id='" + employeeID + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Employee</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='EmployeeController' method='post' style=\"margin:5rem;\">");
                            out.println("<input class='form-control' type='hidden' name='service' value='deleteEmployee'>");
                            out.println("<tbody>");
                            out.println(" <table class='myForm'>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='empid'>Employee ID</label></td>\n"
                                    + "                    <td>"
                                    + "                         <input class='form-control' type='text' id ='empid' disabled value='" + rs.getString(1) + "'>"
                                    + "                         <input class='form-control' type='hidden' name='empid' value='" + rs.getString(1) + "'>"
                                    + "                     </td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='fname'>Last Name</label></td>\n"
                                    + "                    <td> <input class='form-control' disabled type='text' id ='fname' name='fname' maxlength='40' value='" + rs.getString(2) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='minit'>Middle Name (first character)</label></td>\n"
                                    + "                    <td><input class='form-control' disabled type='text' id ='minit' name='minit' maxlength='1' value='" + rs.getString(3) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='lname'>First Name</label></td>\n"
                                    + "                    <td> <input class='form-control' disabled type='text' id ='lname' name='lname' maxlength='40' value='" + rs.getString(4) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='jobid'>Job</label></td>\n"
                                    + "                    <td>\n"
                                    + "                        <select class='form-select' disabled name='jobid' id='pubid'>");
                            ResultSet rs1 = dao.getData("select * from jobs ORDER BY CASE WHEN job_id = " + rs.getString(5) + " THEN 0 ELSE 1 END");
                            while (rs1.next()) {
                                out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                            }
                            out.print("                 </select>\n"
                                    + "                    </td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='joblvl'>Job lvl</label></td>\n"
                                    + "                    <td> <input class='form-control' disabled type='number' id ='minlvl' name='minlvl' value='" + rs.getString(6) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='pubid'>Publisher ID</label></td>\n"
                                    + "                    <td>\n"
                                    + "                        <select class='form-select' disabled name='pubid' id='pubid'>\n");
                            rs1 = dao.getData("select * from publishers");
                            while (rs1.next()) {
                                if (rs1.getString(1).equals(rs.getString(7))) {
                                    out.print("<option value='" + rs1.getString(1) + "' selected>" + rs1.getString(2) + "</option>");
                                } else {
                                    out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                                }
                            }
                            try {
                                out.print("                 </select>\n"
                                        + "                    </td>\n"
                                        + "                </tr>\n"
                                        + "                <tr>\n"
                                        + "                        <td><label for='hiredate'>Hire Date</label></td>\n"
                                        + "                        <td> <input class='form-control' disabled type='date' id ='hiredate' name='hiredate' value='" + new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(8))) + "'></td>\n"
                                        + "                    </tr>\n"
                                        + "                <tr>");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            out.println(" <tr>");
                            out.println(" <td>");
                            out.println(" <h2>Do you want to delete this employee ?</h2>");
                            out.println(" </td>");
                            out.println(" </tr>");
                            out.println("<tr>");
                            out.println(" <td>");
                            out.println("  <input class=\"submitkey\" type='submit' style=\"color:red;\" value='Yes' name='submitDelete'>");
                            out.println(" </td>");
                            out.println(" <td>");
                            out.println("  <input class=\"submitkey\" type='submit' value='No' style=\"color:blue;\" name='submitDelete'>");
                            out.println(" </td>");
                            out.println("</tr> ");
                            out.println("</tbody>");
                            out.println("</table>");
                            out.println("  </form>");
                            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                        }

                    } else {
                        switch (submitDelete) {
                            default:
                                response.sendRedirect("EmployeeController");
                                break;
                            case "Yes":
                                String id = request.getParameter("empid");
                                dao.removeEmployee(id);
                                response.sendRedirect("EmployeeController");
                                break;
                        }
                    }
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dispath(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispath = request.getRequestDispatcher(path);
        dispath.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
