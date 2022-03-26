
package controller;

import entity.Discount;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAODiscount;
import javax.servlet.RequestDispatcher;

public class DiscountController extends HttpServlet {

    public static DAODiscount dao = new DAODiscount();

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
                service = "listAllDiscounts";

            }
            switch (service) {
                default:
                    Vector<Discount> vector = new Vector<>();
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
                            vector = dao.viewAllDiscounts();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Discounts Manage");
                    request.setAttribute("TABLE_TITLE", "List All Discounts");
                    dispath(request, response, "/JSP/Admin/Display/DisplayDiscount.jsp");
                    break;
                case "insertDiscount":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Insert Discount</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf(" <form action='DiscountController' method='post'  style=\"margin:5rem;\" > <table class='myForm'>\n"
                                + "            <input class='form-control' type='hidden' name='service' value='insertDiscount'>\n"
                                + "            <tbody>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='discountType'>Order Date</label></td>\n"
                                + "                    <td><input class='form-control' type='text' id ='discountType' name='discountType'></td>\n"
                                + "                </tr>\n"
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
                                + "                    <td><label for='lowQty'>Low Quantity</label></td>\n"
                                + "                    <td><input class='form-control' type='number' id ='lowQty' name='lowQty'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='highQty'>High Quantity</label></td>\n"
                                + "                    <td><input class='form-control' type='number' id ='highQty' name='highQty'></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for='discount'>Discount</label></td>\n"
                                + "                    <td><input class='form-control' type='number' step='0.1' id ='discount' name='discount'></td>\n"
                                + "                </tr>\n"
                                + "         <tr>\n"
                                + "             <td>\n"
                                + "                 <input class=\"submitkey\" type='submit' value='Insert to Database' name='submitInsert'>\n"
                                + "             </td>\n"
                                + "            <td>\n"
                                + "                <input class=\"submitkey\" type='submit' value='Go back' name='submitInsert' >\n"
                                + "            </td>\n"
                                + "             <td>\n"
                                + "                <input  class=\"submitkey\" type='reset' value='Reset'>\n"
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
                                response.sendRedirect("DiscountController");
                                break;
                            case "Insert to Database":
                                String stor_id = request.getParameter("storeId");
                                String discountType = request.getParameter("discountType");
                                int lowQty = Integer.parseInt(checkNumber(request.getParameter("lowQty")));
                                int highQty = Integer.parseInt(checkNumber(request.getParameter("discount")));
                                int discount = Integer.parseInt(checkNumber(request.getParameter("discount")));

                                if (checkString(discountType) == null) {
                                    return;
                                }

                                Discount obj = new Discount(discountType, stor_id, lowQty, highQty, discount);
                                dao.addDiscount(obj);
                                response.sendRedirect("DiscountController");
                                break;
                        }
                    }
                    break;
                case "updateDiscount":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) {
                        String discount = request.getParameter("discount");
                        String discountType = request.getParameter("discountType");
                        ResultSet rs = dao.getData("SELECT * FROM dbo.discounts WHERE discounttype = '" + discountType + "' AND discount=" + discount + "");
                        ResultSet rs1 = dao.getData("Select * from Stores");
                        request.setAttribute("rsStore", rs1);
                        request.setAttribute("rsDiscount", rs);
                        dispath(request, response, "/JSP/Admin/Update/UpdateDiscount.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String stor_id = request.getParameter("storeId");
                                String discountType = request.getParameter("discountType");
                                Integer lowQty = Integer.parseInt(request.getParameter("lowQty"));
                                Integer highQty = Integer.parseInt(request.getParameter("highQty"));
                                double discount = Double.parseDouble(checkNumber(request.getParameter("discount")));
                                if (stor_id.equals("")) {
                                    stor_id = null;
                                }
                                if (checkString(discountType) == null) {
                                    return;
                                }
                                Discount obj = new Discount(discountType, stor_id, lowQty, highQty, discount);
                                int n = dao.updateDiscount(obj);
                                response.sendRedirect("DiscountController");
                                break;
                            default:
                                response.sendRedirect("DiscountController");
                                break;
                        }
                    }

                    break;
                case "deleteDiscounts":
                    String submitDelete = request.getParameter("submitDelete");
                    if (submitDelete == null) {
                        String idStore = request.getParameter("storeID");
                        String discountType = request.getParameter("discountType");
                        ResultSet rs = null;
                        if (idStore.equals("null")) {
                            rs = dao.getData("SELECT * FROM dbo.discounts WHERE stor_id is null AND discounttype = '" + discountType + "'");
                        } else {
                            rs = dao.getData("SELECT * FROM dbo.discounts WHERE stor_id ='" + idStore + "' AND discounttype = '" + discountType + "'");
                        }
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Discount</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
//                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    </head>");
                            out.printf(" <form action='DiscountController' method='post'> <table class='table'>\n"
                                    + "            <input class='form-control' type='hidden' name='service' value='deleteDiscounts'>\n"
                                    + "            <tbody>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='discountType'>Order Date</label></td>\n"
                                    + "                    <td><input class='form-control' disabled type='text' id ='discountType' name='discountType' value='" + rs.getString(1) + "'>"
                                    + "                    <input class='form-control' type='hidden' id ='discountType' name='discountType' value='" + rs.getString(1) + "'></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='storeId'>Store</label></td>\n"
                                    + "                    <td><select class='form-select' disabled name='storeId' id='storeId'  >\n");
                            if (rs.getString(2) == null) {
                                out.print("<option value='" + null + "' >null</option>");
                            }
                            ResultSet rs1 = dao.getData("SELECT  * FROM dbo.stores ORDER BY CASE WHEN stor_id like '" + rs.getString(2) + "' THEN 0 ELSE 1 END");
                            while (rs1.next()) {
                                out.print("<option value='" + rs1.getString(1) + "' >" + rs1.getString(2) + "</option>");
                            }
                            out.printf("                    </select></td>\n"
                                    + "                    <input class='form-control' type='hidden' id ='storeId' name='storeId' value='" + rs.getString(2) + "'>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='lowQty'>Low Quantity</label></td>\n"
                                    + "                    <td><input class='form-control' disabled type='number' id ='lowQty' name='lowQty' value=" + rs.getString(3) + "></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='highQty'>High Quantity</label></td>\n"
                                    + "                    <td><input class='form-control' disabled type='number' id ='highQty' name='highQty' value=" + rs.getString(4) + "></td>\n"
                                    + "                </tr>\n"
                                    + "                <tr>\n"
                                    + "                    <td><label for='discount'>Discount</label></td>\n"
                                    + "                    <td><input class='form-control' disabled type='number' step='0.1' id ='discount' name='discount' value=" + rs.getString(5) + "></td>\n"
                                    + "                </tr>\n"
                                    + "         <tr>\n"
                                    + "         <tr>\n"
                                    + "             <td>\n"
                                    + "                 <input class='btn btn-outline-dark' type='submit' value='Yes' name='submitDelete'> \n"
                                    + "             </td>\n"
                                    + "            <td>\n"
                                    + "                <input class='btn btn-outline-dark' type='submit' value='No & back' name='submitDelete' >\n"
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
                                response.sendRedirect("DiscountController");
                                break;
                            case "Yes":
                                String discount = request.getParameter("discount");
                                String discountType = request.getParameter("discountType");
                                dao.removeDiscount(discountType, discount);
                                response.sendRedirect("DiscountController");
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
