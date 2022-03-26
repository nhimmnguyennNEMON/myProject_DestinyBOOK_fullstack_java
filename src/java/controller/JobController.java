
package controller;

import model.DAOJobs;
import entity.jobs;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JobController extends HttpServlet {

    DAOJobs dao = new DAOJobs();

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
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) { // call controller direct
                service = "listAllJobs";
            }
            switch (service) {

                default:
                    Vector<jobs> vector = new Vector<>();
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
                            vector = dao.viewAllJobs();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Jobs Manage");
                    request.setAttribute("TABLE_TITLE", "List All Jobs");
                    dispath(request, response, "/JSP/Admin/Display/DisplayJob.jsp");
                    break;
                case "insertJob":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Job</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf("<form action='JobController' method='post'  style=\"margin:5rem;\">\n"
                                + " <table class='myForm'>\n"
                                + " <tr>\n"
                                + " <td><label for='jobdesc'>Job Describe</label></td>\n"
                                + " <td><input class='form-control' type='text' id ='jobdesc' name='jobdesc' maxlength='500'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td><label for='minlvl'>Min lvl</label></td>\n"
                                + " <td><input class='form-control' type='number' id ='minlvl' name='minlvl'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td><label for='maxlvl'>Max lvl</label></td>\n"
                                + " <td><input class='form-control' type='number' id ='maxlvl' name='maxlvl'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td>\n"
                                + " <input class=\"submitkey\" type='submit' value='Insert to Database'>\n"
                                + " </td>\n"
                                + " <td>\n"
                                + " <input class=\"submitkey\" type='submit' value='Go Back'>\n"
                                + " </td>\n"
                                + " <td>\n"
                                + " <input class=\"submitkey\" type='reset' value='Reset'>\n"
                                + " </td>\n"
                                + " </tr> \n"
                                + " </table>\n"
                                + " </form>");
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String job_desc = request.getParameter("jobdesc");
                                int min_lvl = Integer.parseInt(checkNumber(request.getParameter("minlvl")));
                                int max_lvl = Integer.parseInt(checkNumber(request.getParameter("maxlvl")));
                                if (checkString(job_desc) == null) {
                                    out.print("<h2>Cannot not leave input empty</h2>");
                                }

                                jobs ojb = new jobs(job_desc, min_lvl, max_lvl);
                                int n = dao.addJobs(ojb);
                                response.sendRedirect("JobController");
                                break;
                            default:
                                response.sendRedirect("JobController");
                                break;
                        }
                    }

                    break;
                case "updateJob":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) { // chua nhan button submit
                        String jobID = request.getParameter("jobID");
                        String sql = "select * from jobs where job_id='" + jobID + "'";
                        ResultSet rs = dao.getData(sql);
                        request.setAttribute("rsJob", rs);
                        dispath(request, response, "/JSP/Admin/Update/UpdateJob.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                int job_id = Integer.parseInt(request.getParameter("jobid"));
                                String job_desc = request.getParameter("jobdesc");
                                int min_lvl = Integer.parseInt(checkNumber(request.getParameter("minlvl")));
                                int max_lvl = Integer.parseInt(checkNumber(request.getParameter("maxlvl")));
                                jobs obj = new jobs(job_id, job_desc, min_lvl, max_lvl);
                                dao.updateJobs(obj);
                                response.sendRedirect("JobController");
                                break;
                            default:
                                response.sendRedirect("JobController");
                                break;
                        }

                    }
                    break;

                case "deleteJob":
                    String submitDelete = request.getParameter("submitDelete");

                    if (submitDelete == null) {
                        String jobID = request.getParameter("jobID");
                        String sql = "select * from jobs where job_id='" + jobID + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Job</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='JobController' method='post' style=\"margin:5rem;\">");
                            out.println("<input class='form-control' type='hidden' name='service' value='deleteJob'>");
                            out.println("<tbody>");
                            out.println("<table class='myForm'>\n"
                                    + " <tr>\n"
                                    + " <td><label for='jobid'>Job ID</label></td>\n"
                                    + " <td>"
                                    + "     <input class='form-control' disabled type='text' id ='jobid' value='" + rs.getString(1) + "'>"
                                    + "     <input class='form-control' type='hidden' id ='jobid' name='jobid' value='" + rs.getString(1) + "'>"
                                    + "</td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='jobdesc'>Job Describe</label></td>\n"
                                    + " <td><input class='form-control' disabled type='text' id ='jobdesc' name='jobdesc' maxlength='500' value='" + rs.getString(2) + "'></td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='minlvl'>Min lvl</label></td>\n"
                                    + " <td><input class='form-control' disabled type='number' id ='minlvl' name='minlvl' value='" + rs.getString(3) + "'></td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='maxlvl'>Max lvl</label></td>\n"
                                    + " <td><input class='form-control' disabled type='number' id ='maxlvl' name='maxlvl' value='" + rs.getString(4) + "'></td>\n"
                                    + " </tr>\n"
                                    + "<td>");
                            out.println(" <tr>");
                            out.println(" <td>");
                            out.println(" <h2>Do you want to delete this job ?</h2>");
                            out.println(" </td>");
                            out.println(" </tr>");
                            out.println("<tr>");
                            out.println(" <td>");
                            out.println(" <input class=\"submitkey\" type='submit' value='Yes' style=\"color:red;\" name='submitDelete'>");
                            out.println(" </td>");
                            out.println(" <td>");
                            out.println(" <input class=\"submitkey\" type='submit' value='No' style=\"color:blue;\" name='submitDelete'>");
                            out.println(" </td>");
                            out.println("</tr> ");
                            out.println("</tbody>");
                            out.println("</table>");
                            out.println(" </form>");
                            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");
                        }
                    } else {
                        switch (submitDelete) {
                            default:
                                response.sendRedirect("JobController");
                                break;
                            case "Yes":
                                int id = Integer.parseInt(request.getParameter("jobid"));
                                dao.removeJobs(id);
                                response.sendRedirect("JobController");
                                break;
                        }
                    }
                    break;
            }

        } catch (Exception e) {
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
