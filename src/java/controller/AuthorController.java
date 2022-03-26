package controller;

import model.DAOAuthor;
import entity.Authors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    DAOAuthor dao = new DAOAuthor();

    public boolean checkString(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) { // call controller direct
                service = "listAllAuthors";
            }

            switch (service) {
                default:
                    Vector<Authors> vector = new Vector<>();
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
                            vector = dao.viewAllAuthors();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Authors Manage");
                    request.setAttribute("TABLE_TITLE", "List All Authors");
                    dispath(request, response, "/JSP/Admin/Display/DisplayAuthor.jsp");
                case "insertAuthor":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Insert Author</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                //                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.print("<form action='AuthorController' method='get' style=\"margin:5rem;\">\n"
                                + "        <table class=\"myForm\" border='0'>\n"
                                + "            <tr>\n"
                                + "                <td><label for='auId'>AuthorID (xxx-xx-xxxx)</label></td>\n"
                                + "                <td><input class='form-control' type='text' id='auId' name='auId' pattern='[0-9]{3}-[0-9]{2}-[0-9]{4}'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='auLname'>Last Name</label></td>\n"
                                + "                <td><input class='form-control' type='text' id='auLname' name='auLname' maxlength='40'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='auFname'>First Name</label></td>\n"
                                + "                <td><input class='form-control' type='text' id='auFname' name='auFname' maxlength='40'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='phone'>Phone (xxx-xxx-xxxx)</label></td>\n"
                                + "                <td><input class='form-control' type='tel' id='phone' name='phone'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='address'>Address</label></td>\n"
                                + "                <td><input class='form-control' type='text' id='address' name='address' maxlength='40'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='city'>City</label></td>\n"
                                + "                <td><input class='form-control' type='text' id='city' name='city' maxlength='20'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='state'>State (2 letters)</label></td>\n"
                                + "                <td><input class='form-control' type='text' id='state' name='state' pattern='[A-Z]{2}'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='zip'>Zip (xxxxx)</label></td>\n"
                                + "                <td><input class='form-control' type='text' id='zip' name='zip' pattern='[0-9]{5}'></td>\n"
                                + "            </tr>\n"
                                + "            <tr>\n"
                                + "                <td><label for='contract'>Contract (0/1)</label></td>\n"
                                + "                <td>"
                                + "                     <select class='form-select' id='contract' name='contract'>"
                                + "                         <option value = '1'>True</option>"
                                + "                         <option value = '0'>False</option>"
                                + "                     </select>"
                                + "                </td>\n"
                                + "            </tr>\n"
                                + "            <td>\n"
                                + "                <input class=\"submitkey\" type='submit' value='Back' name='submitUpdate'>\n"
                                + "            </td>\n");
                        out.println(" <td>");
                        out.println(" <input class=\"submitkey\" type='submit' value='Insert to Database'>");
                        out.println(" </td>"
                                + "            <td>\n"
                                + "                <input class=\"submitkey\" type='reset' value='Reset'>\n"
                                + "            </td>\n"
                                + "            </tr>\n"
                                + "        </table>\n"
                                + "    </form>");
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String au_id = request.getParameter("auId");
                                String au_lname = request.getParameter("auLname");
                                String au_fname = request.getParameter("auFname");
                                String phone = request.getParameter("phone");
                                String address = request.getParameter("address");
                                String city = request.getParameter("city");
                                String state = request.getParameter("state");
                                String zip = request.getParameter("zip");
                                String contract = request.getParameter("contract");
                                if (!checkString(address) || !checkString(city)) {
                                    out.print("<h2>Empty input!</h2>");
                                    return;
                                }
                                Authors au = new Authors(au_id, au_lname, au_fname, phone, address, city, state, zip, Integer.parseInt(contract));
                                int n = dao.addAuthors(au);
                                if (n > 0) {
                                    out.print("<h2>Successfully!</h2>");
                                } else {
                                    out.print("<h2>Fail!</h2>");
                                }
                        }
                    }
                    break;
                case "updateAuthor":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) {
                        String auID = request.getParameter("AuthorID");
                        String sql = "select * from authors where au_id = '" + auID + "'";
                        ResultSet rs = dao.getData(sql);
                        request.setAttribute("rsAuthor", rs);
                        dispath(request, response, "/JSP/Admin/Update/UpdateAuthor.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String auID = request.getParameter("auId");
                                String au_lname = request.getParameter("auLname");
                                String au_fname = request.getParameter("auFname");
                                String phone = request.getParameter("phone");
                                String address = request.getParameter("address");
                                String city = request.getParameter("city");
                                String state = request.getParameter("state");
                                String zip = request.getParameter("zip");
                                String contract = request.getParameter("contract");

                                if (!checkString(address) || !checkString(city)) {
                                    out.print("<h2>Empty input!</h2>");
                                    return;
                                }
                                Authors obj = new Authors(auID, au_lname, au_fname, phone, address, city, state, zip, Integer.parseInt(contract));
                                int n = dao.updateAuthors(obj);
                                response.sendRedirect("AuthorController");
                                break;
                            default:
                                response.sendRedirect("AuthorController");
                                break;
                        }

                    }
                    break;

                case "deleteAuthor":
                    String submitDelete = request.getParameter("submitDelete");
                    if (submitDelete == null) {
                        String auID = request.getParameter("AuthorID");
                        String sql = "select * from authors where au_id = '" + auID + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Delete Author</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                    //                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='AuthorController' method='post' style=\"margin:5rem;\">");
                            out.println("  <input class='form-control' type='hidden' name='service' value='deleteAuthor'>");
                            out.println("  <table class=\"myForm\">");
                            out.println("  <tr>");
                            out.println("  <td><label for='auId'>AuthorID</label></td>");
                            out.println("  <td>"
                                    + "         <input class='form-control' type='text' id ='auId' disabled value='" + rs.getString(1) + "'>"
                                    + "         <input class='form-control' type='hidden' id ='auId' name='auId' value='" + rs.getString(1) + "'>"
                                    + "    </td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='auLname'>Last Name</label></td>");
                            out.println("  <td><input class='form-control' disabled type='text' id ='auLname' name='auLname' maxlength='40' value='" + rs.getString(2) + "'></td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='auFname'>First Name</label></td>");
                            out.println("  <td><input class='form-control' disabled type='text' id ='auFname' name='auFname' maxlength='40' value='" + rs.getString(3) + "'></td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='phone'>Phone</label></td>");
                            out.println("  <td><input class='form-control' disabled type='tel' id ='phone' name='phone' pattern='[0-9]{3} [0-9]{3}-[0-9]{4}' placeholder='xxx xxx-xxxx' value='" + rs.getString(4) + "'></td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='address'>Address</label></td>");
                            out.println("  <td><input class='form-control' disabled type='text' id ='address' name='address' maxlength='40' value='" + rs.getString(5) + "'></td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='city'>City</label></td>");
                            out.println("  <td><input class='form-control' disabled type='text' id ='city' name='city' maxlength='20' value='" + rs.getString(6) + "'></td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='state'>State</label></td>");
                            out.println("  <td><input class='form-control' disabled type='text' id ='state' name='state' pattern='[A-Z]{2}' placeholder='2 letters' value='" + rs.getString(7) + "'></td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='zip'>Zip</label></td>");
                            out.println("  <td><input class='form-control' disabled type='text' id ='zip' name='zip' pattern='[0-9]{5}' placeholder='xxxxx' value='" + rs.getString(8) + "'></td>");
                            out.println("  </tr>");
                            out.println("  <tr>");
                            out.println("  <td><label for='contract'>Contract</label></td>");
                            out.println("  <td>");
                            out.println("                     <select class='form-select' disabled id='contract' name='contract'>");
                            if (Integer.parseInt(rs.getString(9)) == 1) {
                                out.println("<option selected value = '1'>True</option>");
                                out.println("<option value = '0'>False</option>");
                            } else {
                                out.println("<option value = '1'>True</option>");
                                out.println("<option selected value = '0'>False</option>");
                            }
                            out.println("      </select>");
                            out.println("    </td>");
                            out.println("  </tr>");
                            out.println(" <tr>");
                            out.println(" <td colspan='2'>");
                            out.println(" <h2 style=\"margin:1rem;\">Do you want to delete this Author ?</h2>");
                            out.println(" </td>");
                            out.println(" </tr>");
                            out.println("<tr>");
                            out.println(" <td>");
                            out.println(" <input class=\"submitkey\" type='submit' value='Yes' name='submitDelete' style=\"color:red;\">");
                            out.println(" </td>");
                            out.println(" <td>");
                            out.println(" <input class=\"submitkey\" type='submit' value='Cancle' name='submitDelete' style=\"color:blue;\">");
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
                                response.sendRedirect("AuthorController");
                                break;
                            case "Yes":
                                String auID = request.getParameter("auId");

                                dao.removeAuthors(auID);
                                response.sendRedirect("AuthorController");
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
