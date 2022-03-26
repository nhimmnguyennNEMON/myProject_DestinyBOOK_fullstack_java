
package controller;

import model.DAOSales;
import entity.Sales;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaleController extends HttpServlet {

    public static DAOSales dao = new DAOSales();

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
            if (service == null) {
                service = "listAllSales";

            }
            switch (service) {
                default:
                    Vector<Sales> vector = new Vector<>();
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
                            vector = dao.viewAllSales();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Sales Manage");
                    request.setAttribute("TABLE_TITLE", "List All Sales");
                    dispath(request, response, "/JSP/Admin/Display/DisplaySales.jsp");
                    break;
                case "insertSale":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Sale</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf(" <form action='SaleController' method='post' style=\"margin:5rem;\"> <table class='myForm'>\n"
                                + "            <input class='form-control' type='hidden' name='service' value='insertSale'>\n"
                                + "            <tbody>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='storeId'>Store</label></td>\n"
                                + "                    <td><select class='form-select' name='storeId' id='storeId'>\n"
                        );
                        ResultSet rs1 = dao.getData("select * from stores");
                        while (rs1.next()) {
                            out.printf("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>\n");

                        }
                        out.printf("                    </select></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='ordnum'>Order Number</label></td>\n"
                                + "                    <td><input class='form-control' type='text' id ='ordnum' name='ordnum' maxlength='20'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='orddate'>Order Date</label></td>\n"
                                + "                    <td><input class='form-control' type='date' id ='orddate' name='orddate'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='qty'>Quantity</label></td>\n"
                                + "                    <td><input class='form-control' type='number' value='0' id ='qty' name='qty' min = '0'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='payterms'>Payterm</label></td>\n"
                                + "                    <td><select class='form-select' name='payterms' id='payterms'>\n"
                                + "                        <option value='Net 60'>Net 60</option>\n"
                                + "                        <option value='Net 30'>Net 30</option>\n"
                                + "                        <option value='ON invoice'>ON invoice</option>\n"
                                + "                    </select></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='titleId'>Title</label></td>\n"
                                + "                    <td><select class='form-select' name='titleId' id='titleId'>\n"
                        );
                        ResultSet rs2 = dao.getData("select * from titles");
                        while (rs2.next()) {
                            out.printf("<option value='" + rs2.getString(1) + "'>" + rs2.getString(2) + "</option>\n");

                        }
                        out.printf("                    </select></td>\n"
                                + "         <tr>\n"
                                + "             <td>\n"
                                + "                 <input class=\"submitkey\" type='submit' value='Insert to Database' name='submitInsert'>\n"
                                + "             </td>\n"
                                + "            <td>\n"
                                + "                <input class=\"submitkey\" type='submit' value='Go back' name='submitInsert' >\n"
                                + "            </td>\n"
                                + "             <td>\n"
                                + "                <input class=\"submitkey\" type='reset' value='Reset'>\n"
                                + "            </td>\n"
                                + "         </tr> \n"
                                + "            </tbody>\n"
                                + "        </table>\n"
                                + "    </form>");
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            default:
                                response.sendRedirect("SaleController");
                                break;
                            case "Insert to Database":
                                String stor_id = request.getParameter("storeId");
                                String ord_num = request.getParameter("ordnum");
                                String ord_date = request.getParameter("orddate");
                                int qty = Integer.parseInt(checkNumber(request.getParameter("qty")));
                                String payterms = request.getParameter("payterms");
                                String title_id = request.getParameter("titleId");

                                if (checkString(ord_num) == null) {
                                    out.print("<h2>Order Number cannot be empty</h2>\n"
                                            + "<input class='form-control' type='button' onclick='history.back();' value='Back'>");
                                    return;
                                }

                                Sales sale = new Sales(stor_id, ord_num, ord_date, qty, payterms, title_id);
                                dao.addSales(sale);
                                response.sendRedirect("SaleController");
                                break;
                        }
                    }
                    break;
                case "updateSales":
                    String submitUpdate = request.getParameter("submitUpdate");

                    if (submitUpdate == null) {
                        String idStore = request.getParameter("storeID");
                        String numOrd = request.getParameter("orderNum");
                        String idTitle = request.getParameter("titleID");
                        ResultSet rs = dao.getData("SELECT * FROM dbo.sales WHERE stor_id ='" + idStore + "' AND ord_num = '" + numOrd + "' AND title_id = '" + idTitle + "'");
                        ResultSet rs1 = dao.getData("SELECT  * FROM dbo.stores");
                        ResultSet rs2 = dao.getData("SELECT payterms FROM dbo.sales GROUP BY payterms");
                        ResultSet rs3 = dao.getData("select * from Titles");
                        String[] status = {"Wait", "Process", "Done"};
                        request.setAttribute("status", status);
                        request.setAttribute("rsTitle", rs3);
                        request.setAttribute("rsSale", rs);
                        request.setAttribute("rsStore", rs1);
                        request.setAttribute("payterm", rs2);
                        dispath(request, response, "/JSP/Admin/Update/UpdateSale.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String stor_id = request.getParameter("storeId");
                                String ord_num = request.getParameter("ordnum");
                                String title_id = request.getParameter("titleId");
                                String ord_date = request.getParameter("orddate");
                                int qty = Integer.parseInt(checkNumber(request.getParameter("qty")));
                                String payterms = request.getParameter("payterms");
                                Sales ojb = new Sales(stor_id, ord_num, ord_date, qty, payterms, title_id);
                                int n = dao.updateSales(ojb);
                                response.sendRedirect("SaleController");
                                break;
                            default:
                                response.sendRedirect("SaleController");
                                break;
                        }
                    }

                    break;
                case "deleteSales":
                    String submitDelete = request.getParameter("submitDelete");
                    if (submitDelete == null) {
                        String idStore = request.getParameter("storeID");
                        String numOrd = request.getParameter("orderNum");
                        String idTitle = request.getParameter("titleID");
                        ResultSet rs = dao.getData("SELECT * FROM dbo.sales "
                                + "WHERE stor_id ='" + idStore + "' AND ord_num = '" + numOrd + "' AND title_id = '" + idTitle + "'");
                        if (rs.next()) {

                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Sale</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf(" <form action='SaleController' method='post' style=\"margin:5rem;\"> <table class='myForm'>\n"
                                    + "            <input class='form-control' type='hidden' name='service' value='deleteSales'>\n"
                                    + "            <input class='form-control' type='hidden' name='key1' value='" + idStore + "'>\n"
                                    + "            <input class='form-control' type='hidden' name='key2' value='" + numOrd + "'>\n"
                                    + "            <input class='form-control' type='hidden' name='key3' value='" + idTitle + "'>\n"
                                    + "            <tbody>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='storeId'>Store</label></td>\n"
                                    + "                    <td><select class='form-select' disabled name='storeId' id='storeId'  >\n");
                            ResultSet rs1 = dao.getData("SELECT  * FROM dbo.stores ORDER BY CASE WHEN stor_id like '" + rs.getString(1) + "' THEN 0 ELSE 1 END");
                            while (rs1.next()) {
                                out.print("<option value='" + rs1.getString(1) + "' >" + rs1.getString(2) + "</option>");
                            }

                            out.printf("                    </select></td>\n"
                                    + "                    <input class='form-control' type='hidden' id ='storeId' name='storeId' value='" + rs.getString(1) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='ordnum'>Order Number</label></td>\n"
                                    + "                    <td><input class='form-control' disabled type='text' id ='ordnum' name='ordnum' maxlength='20' value='" + rs.getString(2) + "'  >"
                                    + "                    <input class='form-control' type='hidden' id ='ordnum' name='ordnum' value='" + rs.getString(6) + "'></td>\n"
                                    + "                    </td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='orddate'>Order Date</label></td>\n"
                                    + "                    <td><input class='form-control' type='date' id ='orddate' name='orddate' value='" + rs.getDate(3) + "' ></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='qty'>Quantity</label></td>\n"
                                    + "                    <td><input class='form-control' type='number' id ='qty' name='qty' min = '0' value='" + rs.getString(4) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='payterms'>Payterm</label></td>\n"
                                    + "                    <td><select class='form-select' name='payterms' id='payterms'>\n"
                            );
                            ResultSet rs2 = dao.getData("SELECT payterms FROM dbo.sales "
                                    + " GROUP BY payterms "
                                    + " ORDER BY CASE "
                                    + " WHEN payterms like '" + rs.getString(5) + "' THEN 0 ELSE 1 END");
                            while (rs2.next()) {
                                out.print("<option value='" + rs2.getString(1) + "' >" + rs2.getString(1) + "</option>");
                            }

                            out.printf("                    </select></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='titleId'>Title</label></td>\n"
                                    + "                    <td><select class='form-select' disabled name='titleId' id='titleId' s>\n"
                            );

                            ResultSet rs3 = dao.getData("SELECT * FROM dbo.titles "
                                    + " ORDER BY CASE "
                                    + " WHEN title_id like '" + rs.getString(6) + "' THEN 0 ELSE 1 END");
                            while (rs3.next()) {
                                out.printf("<option value='" + rs3.getString(1) + "'>" + rs3.getString(2) + "</option>\n");

                            }
                            out.printf(" </select>"
                                    + "                    <input class='form-control' type='hidden' id ='titleId' name='titleId' value='" + rs.getString(6) + "'></td>\n"
                                    + "             </td>\n"
                                    + "         <tr>\n"
                                    + "             <tr>\n"
                                    + "             <td>\n"
                                    + "                <h2>Do you want to delete this title ?</h2>\n"
                                    + "             </td>\n"
                                    + "             </tr>\n"
                                    + "         <tr>\n"
                                    + "             <td>\n"
                                    + "                 <input class=\"submitkey\" style=\"color:red;\" type='submit' value='Yes' name='submitDelete'> \n"
                                    + "             </td>\n"
                                    + "            <td>\n"
                                    + "                <input class=\"submitkey\" style=\"color:blue;\" type='submit' value='No' name='submitDelete' >\n"
                                    + "            </td>\n"
                                    + "         </tr> \n"
                                    + "            </tbody>\n"
                                    + "        </table>\n"
                                    + "    </form>");
                            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                        }
                    } else {
                        switch (submitDelete) {
                            default:
                                response.sendRedirect("SaleController");
                                break;
                            case "Yes":
                                String stor_id = request.getParameter("storeId");
                                String ord_num = request.getParameter("ordnum");
                                String title_id = request.getParameter("titleId");
                                dao.removeSale(stor_id, ord_num, title_id);
                                response.sendRedirect("SaleController");
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
