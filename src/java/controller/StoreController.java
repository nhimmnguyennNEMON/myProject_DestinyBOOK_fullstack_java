package controller;

import model.DAOStores;
import entity.Stores;
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

@WebServlet(name = "StoreController", urlPatterns = {"/StoreController"})
public class StoreController extends HttpServlet {

    public static DAOStores dao = new DAOStores();

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
//            DAOStores dao = new DAOStores();
            //get service
            String service = request.getParameter("service");
            if (service == null) { // call controller direct
                service = "listAllStores";
            }

            if (service.equals("register")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String storename = request.getParameter("storename");
                String storeId = request.getParameter("storeId");
                String storeaddress = request.getParameter("storeaddress");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");

                DAOStores daoS = new DAOStores();
                Stores s = daoS.checklog(username);
                if (s != null) {
                    request.setAttribute("error", "Register Fail! Username or Password already exists!");
                    dispath(request, response, "Register.jsp");
                } else {
                    Stores store = new Stores(storeId, storename, storeaddress, city, state, zip, username, password);
                    dao.registerStores(store);
                    request.setAttribute("success", "Register Successfull! Login to continue");
                    dispath(request, response, "Login.jsp");
                }
//                response.sendRedirect("Register.jsp");
            }

            switch (service) {
                default:
                    Vector<Stores> vector = new Vector<>();
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
                            vector = dao.viewAllStores();
                            break;
                    }
                    request.setAttribute("LIST", vector);
                    request.setAttribute("TITLE", "Stores Manage");
                    request.setAttribute("TABLE_TITLE", "List All Stores");
                    dispath(request, response, "/JSP/Admin/Display/DisplayStores.jsp");
                    break;
                case "deleteStores":
                    String submitDelete = request.getParameter("submitDelete");
                    if (submitDelete == null) {
                        String storeID = request.getParameter("storeID");
                        String sql = "Select * from dbo.stores where stor_id='" + storeID + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Store</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                    //                            + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf(" <form action='StoreController' method='post' style=\"margin:5rem;\">\n"
                                    + "<input class='form-control' type='hidden' name='service' value='deleteStores'>"
                                    + "             <table class='myForm'>\n"
                                    + "        <tr>\n"
                                    + "            <td><label for='storeId'>StoreID</label></td>\n"
                                    + "            <td>"
                                    + "                 <input class='form-control' type='text' id ='storeId' maxlength='4' value='" + rs.getString(1) + "' disabled >"
                                    + "                 <input class='form-control' type='hidden' id ='storeId' name='storeId' maxlength='4' value='" + rs.getString(1) + "' >"
                                    + "             </td>\n"
                                    + "        </tr>\n"
                                    + "        <tr>\n"
                                    + "            <td><label for='storename'>Store Name</label></td>\n"
                                    + "            <td><input class='form-control' type='text' id ='storename' name='storename' maxlength='40' value='" + rs.getString(2) + "' disabled ></td>\n"
                                    + "        </tr>\n"
                                    + "        <tr>\n"
                                    + "            <td><label for='storeaddress'>Store Address</label></td>\n"
                                    + "            <td><input class='form-control' type='text' id ='storeaddress' name='storeaddress' maxlength='40' value='" + rs.getString(3) + "' disabled></td>\n"
                                    + "        </tr>\n"
                                    + "        <tr>\n"
                                    + "            <td><label for='city'>Store City</label></td>\n"
                                    + "            <td><input class='form-control' type='text' id ='city' name='city' maxlength='20' value='" + rs.getString(4) + "' disabled></td>\n"
                                    + "        </tr>\n"
                                    + "        <tr>\n"
                                    + "            <td><label for='state'>Store State</label></td>\n"
                                    + "            <td><input class='form-control' type='text' id ='state' name='state' maxlength='2' placeholder='(2 characters)' value='" + rs.getString(5) + "' disabled></td>\n"
                                    + "        </tr>\n"
                                    + "        <tr>\n"
                                    + "            <td><label for='zip'>Zip</label></td>\n"
                                    + "            <td><input class='form-control' type='text' id ='zip' name='zip' maxlength='5' placeholder='(5 characters)' value='" + rs.getString(6) + "' disabled></td>\n"
                                    + "        </tr>\n"
                                    + "             <tr>\n"
                                    + "             <td>\n"
                                    + "                <h2>Do you want to delete this Store ?</h2>\n"
                                    + "             </td>\n"
                                    + "             </tr>\n"
                                    + "        <tr>\n"
                                    + "            <td>\n"
                                    + "                <input class=\"submitkey\" type='submit' style=\"color:red;\" name='submitDelete' value='Yes'>\n"
                                    + "            </td>\n"
                                    + "            <td>\n"
                                    + "               <input class=\"submitkey\" type='submit' name='submitDelete' value='Cancle' style=\"color:blue;\">\n"
                                    + "           </td>\n"
                                    + "        </tr> \n"
                                    + "    </table>\n"
                                    + "        </form>");
                            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                        }

                    } else {
                        switch (submitDelete) {
                            default:
                                response.sendRedirect("StoreController");
                                break;
                            case "Yes":
                                String id = request.getParameter("storeId");
                                dao.removeStore(id);
                                response.sendRedirect("StoreController");
                                break;
                        }

                    }
                    break;

                case "updateStores":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) {
                        String storeID = request.getParameter("storeID");
                        String sql = "Select * from dbo.stores where stor_id='" + storeID + "'";
                        ResultSet rs = dao.getData(sql);
                        request.setAttribute("rsStore", rs);
                        dispath(request, response, "/JSP/Admin/Update/UpdateStore.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String stor_id = request.getParameter("storeId");
                                String stor_name = request.getParameter("storename");
                                String stor_address = request.getParameter("storeaddress");
                                String city = request.getParameter("city");
                                String state = request.getParameter("state");
                                String zip = request.getParameter("zip");
                                String username = request.getParameter("username");
                                String password = request.getParameter("password");
                                Stores ojb = new Stores(stor_id, stor_name, stor_address, city, state, zip, username, password);
                                int n = dao.updateStore(ojb);
                                response.sendRedirect("StoreController");
                                break;
                            default:
                                response.sendRedirect("StoreController");
                                break;
                        }

                    }
                    break;

                case "insertStore":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Store</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                //                            + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf(" <form action='StoreController' method='post' style=\"margin:5rem;\">\n"
                                + "            <input class='form-control' type='hidden' name='service' value='insertStore'>\n"
                                + "             <table class='myForm'>\n"
                                + "        <tr>\n"
                                + "            <td><label for='storeId'>StoreID</label></td>\n"
                                + "            <td><input class='form-control' type='text' id ='storeId' name='storeId' maxlength='4' placeholder='4 number'></td>\n"
                                + "        </tr>\n"
                                + "        <tr>\n"
                                + "            <td><label for='storename'>Store Name</label></td>\n"
                                + "            <td><input class='form-control' type='text' id ='storename' name='storename' maxlength='40'></td>\n"
                                + "        </tr>\n"
                                + "        <tr>\n"
                                + "            <td><label for='storeaddress'>Store Address</label></td>\n"
                                + "            <td><input class='form-control' type='text' id ='storeaddress' name='storeaddress' maxlength='40'></td>\n"
                                + "        </tr>\n"
                                + "        <tr>\n"
                                + "            <td><label for='city'>Store City</label></td>\n"
                                + "            <td><input class='form-control' type='text' id ='city' name='city' maxlength='20'></td>\n"
                                + "        </tr>\n"
                                + "        <tr>\n"
                                + "            <td><label for='state'>Store State</label></td>\n"
                                + "            <td><input class='form-control' type='text' id ='state' name='state' maxlength='2' placeholder='(2 characters)'></td>\n"
                                + "        </tr>\n"
                                + "        <tr>\n"
                                + "            <td><label for='zip'>Zip</label></td>\n"
                                + "            <td><input class='form-control' type='text' id ='zip' name='zip' maxlength='5' placeholder='(5 characters)'></td>\n"
                                + "        </tr>\n"
                                + "        <tr>\n"
                                + "            <td>\n"
                                + "                <input class=\"submitkey\" type='submit' value='Insert to Database' name='submitInsert' >\n"
                                + "            </td>\n"
                                + "            <td>\n"
                                + "                <input class=\"submitkey\" type='submit' value='Go back' name='submitInsert' >\n"
                                + "            </td>\n"
                                + "            <td>\n"
                                + "               <input class=\"submitkey\" type='reset' value='Reset'>\n"
                                + "           </td>\n"
                                + "        </tr> \n"
                                + "    </table>\n"
                                + "        </form>");
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String stor_id = request.getParameter("storeId");
                                String stor_name = request.getParameter("storename");
                                String stor_address = request.getParameter("storeaddress");
                                String city = request.getParameter("city");
                                String state = request.getParameter("state");
                                String zip = request.getParameter("zip");

                                if (checkString(stor_id) == null) {

                                    return;
                                }

                                Stores store = new Stores(stor_id, stor_name, stor_address, city, state, zip);
                                dao.addStores(store);
                                response.sendRedirect("StoreController");
                                break;

                            default:
                                response.sendRedirect("StoreController");
                                break;

                        }
                    }

                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
