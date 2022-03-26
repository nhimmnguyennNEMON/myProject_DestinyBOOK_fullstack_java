/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DAOTitleAuthor;
import entity.TitleAuthor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TitleAuthorController", urlPatterns = {"/TitleAuthorController"})
public class TitleAuthorController extends HttpServlet {

    DAOTitleAuthor dao = new DAOTitleAuthor();

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
                service = "listAllTitleAuthors";
            }

            switch (service) {

                default:
                    Vector<TitleAuthor> vector = new Vector<>();
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
                            vector = dao.viewAllTitleAuthor();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Title Authors Manage");
                    request.setAttribute("TABLE_TITLE", "List All Title Authors");
                    dispath(request, response, "/JSP/Admin/Display/DisplayTitleAuthor.jsp");
                    break;
                case "insertTitleAuthor":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Title & Author</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf("<form action='TitleAuthorController' method='post' style=\"margin:5rem;\">\n"
                                + "            <table class='myForm'>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='au_id'>Author</label></td>\n"
                                + "                    <td>\n"
                                + "                        <select class='form-select' name='au_id' id='au_id'>\n");
                        ResultSet rs1 = dao.getData("select * from authors");
                        while (rs1.next()) {
                            out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                        }
                        out.print("                 </select>\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='title_id'>Title</label></td>\n"
                                + "                    <td>\n"
                                + "                        <select class='form-select' name='title_id' id='title_id'>\n");
                        rs1 = dao.getData("select * from titles");
                        while (rs1.next()) {
                            out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                        }
                        out.print("                 </select>\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='auOrd'>Author Order</label></td>\n"
                                + "                    <td><input class='form-control' type='number' id ='auOrd' name='auOrd'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                        <td><label for='royaltyper'>Royal Typer</label></td>\n"
                                + "                        <td><input class='form-control' type='number' id ='royaltyper' name='royaltyper'></td>\n"
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
                                + "        </form>"
                        );
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String au_id = request.getParameter("authorId");
                                String title_id = request.getParameter("titleId");
                                String au_ord = request.getParameter("auOrd");
                                String royaltyper = request.getParameter("royaltyper");
                                if (checkString(au_id) == null && checkNumber(au_ord) == null && checkString(title_id) == null && checkNumber(royaltyper) == null) {
                                    return;
                                }
                                TitleAuthor ta = new TitleAuthor(au_id, title_id, Integer.parseInt(au_ord), Integer.parseInt(royaltyper));
                                dao.addTitleAuthor(ta);
                                response.sendRedirect("TitleAuthorController");
                                break;
                            default:
                                response.sendRedirect("TitleAuthorController");
                                break;

                        }
                    }

                    break;
                case "updateTitleAuthor":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) {
                        String authorID = request.getParameter("authorID");
                        String titleID = request.getParameter("titleID");
                        String sql = "select * from titleauthor where title_id='" + titleID + "' AND au_id='" + authorID + "'";
                        ResultSet rs = dao.getData(sql),rs1=dao.getData("select * from titles"),rs2=dao.getData("select * from authors");
                        request.setAttribute("rsTitleAuthor", rs);
                        request.setAttribute("rsTitle", rs1);
                        request.setAttribute("rsAuthor", rs2);
                        dispath(request, response, "/JSP/Admin/Update/UpdateTitleAuthor.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String au_id = request.getParameter("au_id");
                                String title_id = request.getParameter("title_id");
                                String au_ord = request.getParameter("auOrd");
                                String royaltyper = request.getParameter("royaltyper");
                                if (checkString(au_id) == null && checkNumber(au_ord) == null && checkString(title_id) == null && checkNumber(royaltyper) == null) {
                                    return;
                                }
                                TitleAuthor ta = new TitleAuthor(au_id, title_id, Integer.parseInt(au_ord), Integer.parseInt(royaltyper));
                                dao.updateTitleAuthor(ta);
                                response.sendRedirect("TitleAuthorController");
                                break;
                            default:
                                response.sendRedirect("TitleAuthorController");
                                break;
                        }

                    }
                    break;

                case "deleteTitleAuthor":
                    String submitDelete = request.getParameter("submitDelete");

                    if (submitDelete == null) {
                        String authorID = request.getParameter("authorID");
                        String titleID = request.getParameter("titleID");
                        String sql = "select * from titleauthor where title_id='" + titleID + "' AND au_id='" + authorID + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Title & Author</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='TitleAuthorController' method='post' style=\"margin:5rem;\">");
                            out.println("<input class='form-control' type='hidden' name='service' value='deleteTitleAuthor'>");
                            out.println("<table class='myForm'>");
                            out.println("<tbody>");
                            out.println("<table class='table'>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='au_id'>Author</label></td>\n"
                                    + "                    <td>\n"
                                    + "                        <select class='form-select' disabled name='au_id' id='au_id'>\n");
                            ResultSet rs1 = dao.getData("select * from Authors ORDER BY CASE WHEN au_id like '" + rs.getString(1) + "' THEN 0 ELSE 1 END");
                            while (rs1.next()) {
                                out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + " " + rs1.getString(3) + "</option>");
                            }
                            out.print("                 </select>\n"
                                    + "                    </td>\n"
                                    + "<input class='form-control' type='hidden' id ='au_id' name='au_id' value = '" + rs.getString(1) + "'>"
                                    + "                </tr>\n"
                                    + "                <td><label for='title_id'>Title</label></td>\n"
                                    + "                    <td>\n"
                                    + "                        <select class='form-select' disabled name='title_id' id='title_id'>\n");
                            rs1 = dao.getData("select * from Titles ORDER BY CASE WHEN title_id like '" + rs.getString(2) + "' THEN 0 ELSE 1 END");
                            while (rs1.next()) {
                                out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                            }
                            out.print("                 </select>\n"
                                    + "<input class='form-control' type='hidden' id ='title_id' name='title_id' value = '" + rs.getString(2) + "'>"
                                    + "                    </td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='auOrd'>Author Order</label></td>\n"
                                    + "                    <td><input class='form-control' type='number' id ='auOrd' name='auOrd' value='" + rs.getString(3) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                        <td><label for='royaltyper'>Royal Typer</label></td>\n"
                                    + "                        <td><input class='form-control' type='number' id ='royaltyper' name='royaltyper' value='" + rs.getString(4) + "'></td>\n"
                                    + "                    </tr>");
                            out.println("<tr> ");
                            out.println(" <tr>");
                            out.println(" <td>");
                            out.println(" <h2>Do you want to delete this employee ?</h2>");
                            out.println(" </td>");
                            out.println(" </tr>");
                            out.println("<tr>");
                            out.println(" <td>");
                            out.println("  <input class=\"submitkey\" style=\"color:red;\" type='submit' value='Yes' name='submitDelete'>");
                            out.println(" </td>");
                            out.println(" <td>");
                            out.println("  <input class=\"submitkey\" style=\"color:blue;\" type='submit' value='No' name='submitDelete'>");
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
                                response.sendRedirect("TitleAuthorController");
                                break;
                            case "Yes":
                                String au_id = request.getParameter("authorId");
                                String title_id = request.getParameter("titleId");
                                dao.removeTitleAuthor(title_id, au_id);
                                response.sendRedirect("TitleAuthorController");
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
