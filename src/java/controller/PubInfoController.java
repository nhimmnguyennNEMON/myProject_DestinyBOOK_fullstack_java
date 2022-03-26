
package controller;

import model.DAOPub_info;
import entity.pub_info;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PubInfoController extends HttpServlet {

    DAOPub_info dao = new DAOPub_info();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            if (service == null) { // call controller direct
                service = "listAllPubInfos";
            }

            switch (service) {

                default:
                    Vector<pub_info> vector = new Vector<>();
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
                            vector = dao.viewAllPub_info();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Publisher Info Manage");
                    request.setAttribute("TABLE_TITLE", "List All Publisher Informations");
                    dispath(request, response, "/JSP/Admin/Display/DisplayPubInfo.jsp");
                    break;
                case "insertPubInfo":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Publisher Information</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf("<form action='PubInfoController' method='post'  style=\"margin:5rem;\">\n"
                                + " <table class='myForm'>\n"
                                + " <tr>\n"
                                + " <td><label for='pubid'>Publisher ID</label></td>\n"
                                + " <td>\n"
                                + " <select class='form-select' name='pubid' id='pubid'>\n");
                        ResultSet rs1 = dao.getData("select * from publishers");
                        while (rs1.next()) {
                            out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                        }
                        out.print(" </select>\n"
                                + " </td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td style='max-width: 100px;max-height: 100px; overflow: hidden;'><label for='logo'>Logo</label></td>\n"
                                + " <td><input class='form-control' type='text' id ='logo' name='logo' maxlength='80'></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + " <td><label for='prdesc'>Describe</label></td>\n"
                                + " <td><textarea class='form-select'  type='text' id ='prdesc' name='prdesc'></textarea></td>\n"
                                + " </tr>\n"
                                + " <tr>\n"
                                + "             <td>\n"
                                + "                 <input class=\"submitkey\" type='submit' value='Insert to Database' name='submitInsert'>\n"
                                + "             </td>\n"
                                + "             <td>\n"
                                + "                 <input class=\"submitkey\" type='submit' value='Go Back' name='submitInsert'>\n"
                                + "             </td>\n"
                                + "             <td>\n"
                                + "                <input class=\"submitkey\" type='reset' value='Reset'>\n"
                                + "            </td>\n"
                                + "         </tr> \n"
                                + "               </tbody>\n"
                                + "            </table>\n"
                                + "\n"
                                + "        </form>");
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String pub_id = request.getParameter("pubid");
                                String logo = request.getParameter("logo");
                                String pr_desc = request.getParameter("prdesc");
                                pub_info ojb = new pub_info(pub_id, logo, pr_desc);
                                int n = dao.addPub_info(ojb);
                                response.sendRedirect("PubInfoController");
                                break;
                            default:
                                response.sendRedirect("PubInfoController");
                                break;
                        }
                    }

                    break;
                case "updatePubInfo":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) { // chua nhan button submit
                        String pubid = request.getParameter("pubid");
                        String sql = "select * from pub_info where pub_id = '" + pubid + "'";
                        ResultSet rs = dao.getData(sql);
                        ResultSet rs1 = dao.getData("SELECT * FROM dbo.publishers");
                        request.setAttribute("rsPubInfo", rs);
                        request.setAttribute("rsPublisher", rs1);
                        dispath(request, response, "/JSP/Admin/Update/UpdatePubInfo.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String pub_id = request.getParameter("pubid");
                                String logo = request.getParameter("logo");
                                String pr_desc = request.getParameter("prdesc");
                                pub_info ojb = new pub_info(pub_id, logo, pr_desc);
                                dao.updatePub_info(ojb);
                                response.sendRedirect("PubInfoController");
                                break;
                            default:
                                response.sendRedirect("PubInfoController");
                                break;
                        }

                    }
                    break;

                case "deletePubInfo":
                    String submitDelete = request.getParameter("submitDelete");

                    if (submitDelete == null) {
                        String pubid = request.getParameter("pubid");
                        String sql = "select * from pub_info where pub_id='" + pubid + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Publisher Information</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='PubInfoController' method='post' style=\"margin:5rem;\">");
                            out.println("<input class='form-control' type='hidden' name='service' value='deletePubInfo'>");
                            out.println("<tbody>");
                            out.println("<table class='myForm'>\n"
                                    + " <tr>\n"
                                    + " <td><label for='pubid'>Publisher ID</label></td>\n"
                                    + " <td>\n"
                                    + " <select class='form-select' disabled name='pubid' id='pubid'>\n");
                            ResultSet rs1 = dao.getData("select * from publishers ORDER BY CASE WHEN pub_id like '" + rs.getString(1) + "' THEN 0 ELSE 1 END");
                            while (rs1.next()) {
                                out.print("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                            }
                            out.print(" </select>\n"
                                    + "<input type = 'hidden' name='pubid' id='pubid' value='" + rs.getString(1) + "'>\n"
                                    + " </td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='logo'>Logo</label></td>\n"
                                    + " <td style='max-width: 100px;max-height: 100px; overflow: hidden;'><input class='form-control' disabled type='text' id ='logo' name='logo' maxlength='80' value='" + rs.getString(2) + "'></td>\n"
                                    + " </tr>\n"
                                    + " <tr>\n"
                                    + " <td><label for='prdesc'>Describe</label></td>\n"
                                    + " <td><textarea class='form-select' disabled type='text' id ='prdesc' name='prdesc'>" + rs.getString(3) + "</textarea></td>\n"
                                    + " </tr>"
                                    + "<tr>\n"
                                    + "<td>");
                            out.println(" <td>");
                            out.println(" <h2>Do you want to delete this publisher info ?</h2>");
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
                                response.sendRedirect("PubInfoController");
                                break;
                            case "Yes":
                                String pub_id = request.getParameter("pubid");
                                dao.removePub_info(pub_id);
                                response.sendRedirect("PubInfoController");
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
