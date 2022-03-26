/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DAOSalesDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ConnectDB;

@WebServlet(name = "SalesDetailController", urlPatterns = {"/SalesDetailController"})
public class SalesDetailController extends HttpServlet {

    ConnectDB dao = new ConnectDB();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DAOSalesDetail dao = new DAOSalesDetail();
            String storeId = request.getParameter("storeID"), StoreName = "";
            ResultSet name = dao.getData("select stor_name from Stores where stor_id = '" + storeId + "'");
            if (name.next()) {
                StoreName = name.getString(1);
            }
            String service = request.getParameter("service");
            if (service != null && service.equalsIgnoreCase("updateStatus")) {
                String OrderNumber = request.getParameter("OrderNumber");
                String titleid = request.getParameter("titleid");
                request.setAttribute("TITLE", "Sales Detail");
                int status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateSalesDetail(storeId, OrderNumber, titleid, status);
            }
            ResultSet rs = dao.getData("SELECT sales.ord_num,titles.title,dbo.titles.price,sales.qty,dbo.sales.qty * dbo.titles.price AS Total,sales.status,titles.title_id"
                    + "                     FROM Sales "
                    + "                     INNER JOIN Titles ON titles.title_id=sales.title_id "
                    + "                     INNER JOIN stores ON stores.stor_id=sales.stor_id "
                    + "                     INNER JOIN Publishers ON publishers.pub_id=titles.pub_id"
                    + "			    WHERE dbo.stores.stor_id = '" + storeId + "' "
                    + "ORDER BY dbo.titles.price DESC");
            String[] status = {"Wait", "Process", "Done"};
            request.setAttribute("status", status);
            request.setAttribute("rsSalesDetail", rs);
            request.setAttribute("storeId", storeId);
            request.setAttribute("storeName", StoreName);
            RequestDispatcher dispath = request.getRequestDispatcher("/JSP/Admin/Display/DisplaySaleDetail.jsp");
            dispath.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
