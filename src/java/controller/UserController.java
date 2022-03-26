package controller;

import model.DAOEmployee;
import model.DAOStores;
import entity.Stores;
import entity.Titles;
import entity.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.mail.Store;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOSales;
import model.DAOSalesDetail;
import model.DAOTitles;

public class UserController extends HttpServlet {

    DAOTitles dao = new DAOTitles();
    DAOStores dao1 = new DAOStores();
    DAOSales dao2 = new DAOSales();
    Vector<Titles> vector = new Vector<>();
//    DAOSalesDetail dao1 = new DAOSalesDetail();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            HttpSession session = request.getSession();
            if (service == null) {
                service = "home";
            }
            if (service.equals("home")) {
                if (session.getAttribute("username") == null) {
                    dispath(request, response, "Login.jsp");
                } else {
                    String userName = request.getParameter("username");
                    session.setAttribute("username", userName);
                    dispath(request, response, "index.jsp");
                }
            }
            if (service.equals("Profile")) {
                DAOSalesDetail dao1 = new DAOSalesDetail();
                String username = request.getParameter("username"), storeId = "";
                ResultSet store_Id = dao1.getData("select stor_id from Stores where username = '" + username + "'");
                if (store_Id.next()) {
                    storeId = store_Id.getString(1);
                }
                ResultSet rs = dao1.getData("SELECT sales.ord_num,titles.title,dbo.titles.price,sales.qty,dbo.sales.qty * dbo.titles.price AS Total,sales.status,titles.image, sales.payterms,sales.stor_id, titles.title_id"
                        + "                     FROM Sales "
                        + "                     INNER JOIN Titles ON titles.title_id=sales.title_id "
                        + "                     INNER JOIN stores ON stores.stor_id=sales.stor_id "
                        + "                     INNER JOIN Publishers ON publishers.pub_id=titles.pub_id"
                        + "			    WHERE dbo.stores.stor_id = '" + storeId + "' "
                        + "ORDER BY dbo.titles.price DESC");
                ResultSet rs1 = dao1.getData("Select * from dbo.stores where stor_id='" + storeId + "'");
                request.setAttribute("rsSalesDetail1", rs1);
                request.setAttribute("rsSalesDetail", rs);
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/User/Profile.jsp");
                dispath.forward(request, response);
            }

            if (service.equals("delete")) {
                String ordnum = request.getParameter("ordNum");
                dao2.removeSale(ordnum);

                String userName = request.getParameter("username");
                session.setAttribute("username", userName);
                dispath(request, response, "index.jsp");

            }

            if (service.equals("updateStore")) {
                String stor_id = request.getParameter("storeId");
                String stor_name = request.getParameter("storename");
                String stor_address = request.getParameter("storeaddress");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zip = request.getParameter("zip");
                String username = request.getParameter("usename");
                String password = request.getParameter("password");
                Stores ojb = new Stores(stor_id, stor_name, stor_address, city, state, zip, username, password);
                int n = dao1.updateStore(ojb);

                String acc = request.getParameter("accUsername");
                session.setAttribute("username", acc);
                dispath(request, response, "index.jsp");

            }
//            if (service.equals("Search By Name")) {
//                String name = request.getParameter("name");
//                if (name == null) {
//                    vector = dao.viewAllTitles();
//                } else {
//                    vector = dao.searchName(name);
//                }
//                session.setAttribute("search", vector);
//                dispath(request, response, "index.jsp");
//            }
//            if (service.equals("Search By Price")) {
//                double from = Double.parseDouble(request.getParameter("from"));
//                double to = Double.parseDouble(request.getParameter("to"));
//                if (from <= to) {
//                    vector = dao.searchByPrice(from, to);
//                } else {
//                    vector = dao.viewAllTitles();
//                }
//                session.setAttribute("search", vector);
//                dispath(request, response, "index.jsp");
//            }
            if (service.equals("login")) {
                String userName = request.getParameter("username");
                String password = request.getParameter("password");
                DAOEmployee daoE = new DAOEmployee();
                employee e = daoE.login(userName, password);
                if (e != null) {
                    session.setAttribute("username", e);
                    dispath(request, response, "Admin.jsp");
                } else {
                    DAOStores daoS = new DAOStores();
                    Stores s = daoS.login(userName, password);
                    if (s != null) {
                        session.setAttribute("username", s);
                        dispath(request, response, "index.jsp");
                    } else {
                        request.setAttribute("error", "Login Fail!");
                        dispath(request, response, "Login.jsp");
                    }
                }
            } else if (service.equals("Logout")) {
                session.invalidate();
                request.getSession(false);
//                session.removeAttribute("username");
                dispath(request, response, "index.jsp");
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
