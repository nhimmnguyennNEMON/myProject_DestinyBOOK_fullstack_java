
package controller;

import entity.Roysched;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAORoysched;
import javax.servlet.RequestDispatcher;

public class RoyschedController extends HttpServlet {

    DAORoysched dao = new DAORoysched();

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
                service = "listAllRoyscheds";
            }

            switch (service) {

                default:
                    Vector<Roysched> vector = new Vector<>();
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
                            vector = dao.viewAllRoysched();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Royscheds Manage");
                    request.setAttribute("TABLE_TITLE", "List All Royscheds");
                    RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Admin/Display/DisplayRoysched.jsp");
                    dispath.forward(request, response);
                    break;
                case "insertRoysched":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Roysched</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf("<form action='RoyschedController' method='post' style=\"margin:5rem;\">\n"
                                + " <table class='myForm'>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='title_id'>Title</label></td>\n"
                                + "                    <td>\n"
                                + "                        <select class='form-select' name='title_id' id='title_id'>\n");
                        ResultSet rs1 = dao.getData("select * from titles");
                        while (rs1.next()) {
                            out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                        }
                        out.print("                 </select>\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "<tr>"
                                + " <td><label for='royalty'>Royalty</label></td>\n"
                                + " <td><input class='form-control' type='number' id ='royalty' name='royalty'></td>\n"
                                + " </tr>\n"
                                + "<tr>"
                                + " <td><label for='lorange'>Lorange</label></td>\n"
                                + " <td><input class='form-control' type='number' id ='lorange' name='lorange'></td>\n"
                                + " </tr>\n"
                                + "<tr>"
                                + " <td><label for='hirange'>Hirange</label></td>\n"
                                + " <td><input class='form-control' type='number' id ='hirange' name='hirange'></td>\n"
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
                                + " </form>"
                        );
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String title_id = request.getParameter("title_id");
                                int lorange = Integer.parseInt(checkNumber(request.getParameter("lorange")));
                                int hirange = Integer.parseInt(checkNumber(request.getParameter("hirange")));
                                int royalty = Integer.parseInt(checkNumber(request.getParameter("royalty")));
                                if (checkString(title_id) == null) {
                                    out.print("<h2>Cannot not leave input empty</h2>");
                                }
                                Roysched ojb = new Roysched(title_id, lorange, hirange, royalty);
                                dao.addRoysched(ojb);
                                response.sendRedirect("RoyschedController");
                                break;
                            default:
                                response.sendRedirect("RoyschedController");
                                break;
                        }
                    }

                    break;
                case "updateRoysched":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) {
                        String titleID = request.getParameter("titleID");
                        int royalty = Integer.parseInt(request.getParameter("royalty"));
                        String sql = "select * from roysched where title_id='" + titleID + "' and royalty=" + royalty;
                        ResultSet rs = dao.getData(sql);
                        ResultSet rs1 = dao.getData("select * from Titles");
                        request.setAttribute("rsRoysched", rs);
                        request.setAttribute("rsTitle", rs1);
                        dispath(request, response, "/JSP/Admin/Update/UpdateRoysched.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String title_id = request.getParameter("title_id");
                                int lorange = Integer.parseInt(checkNumber(request.getParameter("lorange")));
                                int hirange = Integer.parseInt(checkNumber(request.getParameter("hirange")));
                                int royalty = Integer.parseInt(checkNumber(request.getParameter("royalty")));
                                if (checkString(title_id) == null) {
                                    out.print("<h2>Cannot not leave input empty</h2>");
                                }
                                Roysched ojb = new Roysched(title_id, lorange, hirange, royalty);
                                dao.updateRoysched(ojb);
                                response.sendRedirect("RoyschedController");
                                break;
                            default:
                                response.sendRedirect("RoyschedController");
                                break;
                        }

                    }
                    break;

                case "deleteRoysched":
                    String submitDelete = request.getParameter("submitDelete");

                    if (submitDelete == null) {
                        String titleID = request.getParameter("titleID");
                        int royalty = Integer.parseInt(request.getParameter("royalty"));
                        String sql = "select * from roysched where title_id='" + titleID + "' and royalty=" + royalty;
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Roysched</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='RoyschedController' method='post' style=\"margin:5rem;\">");
                            out.println("<input class='form-control' type='hidden' name='service' value='deleteRoysched'>");
                            out.println("<table class='myForm'>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='title_id'>Title</label></td>\n"
                                    + "                    <td>\n"
                                    + "                        <select class='form-select' disabled name='title_id' id='title_id'>\n");
                            ResultSet rs1 = dao.getData("select * from Titles ORDER BY CASE WHEN title_id like '" + rs.getString(1) + "' THEN 0 ELSE 1 END");
                            while (rs1.next()) {
                                out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                            }
                            out.print("                 </select>\n"
                                    + "                    </td>\n"
                                    + "                </tr>\n"
                                    + "<tr>"
                                    + " <td><label for='royalty'>Royalty</label></td>\n"
                                    + " <td><input class='form-control' disabled type='number' id ='royalty' name='royalty' value='" + rs.getString(4) + "'></td>\n"
                                    + " </tr>\n"
                                    + "<tr>"
                                    + " <td><label for='lorange'>Lorange</label></td>\n"
                                    + " <td><input class='form-control' disabled type='number' id ='lorange' name='lorange' value='" + rs.getString(2) + "'></td>\n"
                                    + " </tr>\n"
                                    + "<tr>"
                                    + " <td><label for='hirange'>Hirange</label></td>\n"
                                    + " <td><input class='form-control' disabled type='number' id ='hirange' name='hirange' value='" + rs.getString(3) + "'></td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + "<td>");
                            out.println(" <h2>Do you want to delete this job ?</h2>");
                            out.println(" </td>");
                            out.println(" </tr>");
                            out.println("<tr>");
                            out.println(" <td>");
                            out.println(" <input class=\"submitkey\" style=\"color:red;\" type='submit' value='Yes' name='submitDelete'>");
                            out.println(" </td>");
                            out.println(" <td>");
                            out.println(" <input class=\"submitkey\" style=\"color:blue;\" type='submit' value='No' name='submitDelete'>");
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
                                response.sendRedirect("RoyschedController");
                                break;
                            case "Yes":
                                String title_id = request.getParameter("title_id");
                                int royalty = Integer.parseInt(checkNumber(request.getParameter("royalty")));
                                dao.removeRoysched(title_id, royalty);
                                response.sendRedirect("RoyschedController");
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
