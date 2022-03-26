
package controller;

import model.DAOPublishers;
import entity.publishers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PublisherController extends HttpServlet {

    DAOPublishers dao = new DAOPublishers();

    public String checkString(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return string;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) { // call controller direct
                service = "listAllPublishers";
            }

            switch (service) {

                default:
                    Vector<publishers> vector = new Vector<>();
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
                            vector = dao.viewAllpublishers();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Publishers Manage");
                    request.setAttribute("TABLE_TITLE", "List All Publishers");
                    dispath(request, response, "/JSP/Admin/Display/DisplayPublisher.jsp");
                    break;
                case "insertPublisher":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Publisher</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf("<form action='PublisherController' method='post' style=\"margin:5rem;\">\n"
                                + " <table class='myForm'>\n"
                                + " <tbody>\n"
                                + " <tr>\n"
                                + " <td><label for='pubid'>Publisher ID</label></td>\n"
                                + " <td><input class='form-control' type='text' id ='pubid' name='pubid' maxlength='4'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td><label for='pubname'>Publisher Name</label></td>\n"
                                + " <td><input class='form-control' type='text' id ='pubname' name='pubname' maxlength='80'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td><label for='city'>Store City</label></td>\n"
                                + " <td><input class='form-control' type='text' id ='city' name='city' maxlength='20'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td><label for='state'>Store State (2 characters)</label></td>\n"
                                + " <td><input class='form-control' type='text' id ='state' name='state' maxlength='2'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td><label for='country'>Country</label></td>\n"
                                + " <td><input class='form-control' type='text' id ='country' name='country' maxlength='50'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + "             <td>\n"
                                + "                 <input class=\"submitkey\"  type='submit' value='Insert to Database' name='submitInsert'>\n"
                                + "             </td>\n"
                                + "             <td>\n"
                                + "                 <input class=\"submitkey\"  type='submit' value='Go Back' name='submitInsert'>\n"
                                + "             </td>\n"
                                + "             <td>\n"
                                + "                <input class=\"submitkey\"  type='reset' value='Reset'>\n"
                                + "            </td>\n"
                                + "         </tr> \n"
                                + "               </tbody>\n"
                                + "            </table>\n"
                                + "\n"
                                + "        </form>"
                        );
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String pub_id = request.getParameter("pubid");
                                String pub_name = request.getParameter("pubname");
                                String city = request.getParameter("city");
                                String state = request.getParameter("state");
                                String country = request.getParameter("country");
                                if (checkString(pub_id) == null) {
                                    out.print("<h2>Cannot not leave input empty</h2>");
                                    return;
                                }
                                entity.publishers ojb = new publishers(pub_id, pub_name, city, state, country);
                                int n = dao.addPublishers(ojb);
                                response.sendRedirect("PublisherController");
                                break;
                            default:
                                response.sendRedirect("PublisherController");
                                break;
                        }
                    }
                    break;
                case "updatePublisher":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) { // chua nhan button submit
                        String publisherID = request.getParameter("publisherID");
                        String sql = "select * from publishers where pub_id='" + publisherID + "'";
                        ResultSet rs = dao.getData(sql);
                        request.setAttribute("rsPublisher", rs);
                        dispath(request, response, "/JSP/Admin/Update/UpdatePublisher.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String pub_id = request.getParameter("pubid");
                                String pub_name = request.getParameter("pubname");
                                String city = request.getParameter("city");
                                String state = request.getParameter("state");
                                String country = request.getParameter("country");
                                if (state.equals("null")) {
                                    state = null;
                                }
                                publishers obj = new publishers(pub_id, pub_name, city, state, country);
                                int n =dao.updatePublishers(obj);
                                response.sendRedirect("PublisherController");
                                break;
                            default:
                                response.sendRedirect("PublisherController");
                                break;
                        }

                    }
                    break;

                case "deletePublisher":
                    String submitDelete = request.getParameter("submitDelete");
                    if (submitDelete == null) {
                        String publisherID = request.getParameter("publisherID");
                        String sql = "select * from publishers where pub_id='" + publisherID + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Publisher</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='PublisherController' method='post' style=\"margin:5rem;\">");
                            out.println("<input class='form-control' type='hidden' name='service' value='deletePublisher'>");
                            out.println("<tbody>");
                            out.println("<table class='myForm'>\n"
                                    + " <tr>\n"
                                    + " <td><label for='pubid'>Publisher ID</label></td>\n"
                                    + " <td>"
                                    + "     <input class='form-control' disabled type='text' id ='pubid' value='" + rs.getString(1) + "'>"
                                    + "     <input class='form-control' type='hidden' id ='pubid' name='pubid' value='" + rs.getString(1) + "'>"
                                    + "</td>\n" + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='pubname'>Publisher Name</label></td>\n"
                                    + " <td><input class='form-control' disabled type='text' id ='pubname' name='pubname' maxlength='80' value='" + rs.getString(2) + "'></td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='city'>Store City</label></td>\n"
                                    + " <td><input class='form-control' disabled type='text' id ='city' name='city' maxlength='20' value='" + rs.getString(3) + "'></td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='state'>Store State (2 characters)</label></td>\n"
                                    + " <td><input class='form-control' disabled type='text' id ='state' name='state' maxlength='2' value='" + rs.getString(4) + "'></td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='country'>Country</label></td>\n"
                                    + " <td><input class='form-control' disabled type='text' id ='country' name='country' maxlength='50' value='" + rs.getString(5) + "'></td>\n"
                                    + " </tr>");
                            out.println(" <tr>");
                            out.println(" <td>");
                            out.println(" <h2>Do you want to delete this publisher ?</h2>");
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
                                response.sendRedirect("PublisherController");
                                break;
                            case "Yes":
                                String id = request.getParameter("pubid");
                                dao.removePublishers(id);
                                response.sendRedirect("PublisherController");
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
