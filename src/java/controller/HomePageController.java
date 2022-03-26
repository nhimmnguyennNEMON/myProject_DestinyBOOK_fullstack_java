/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Stores;
import entity.Titles;
import entity.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOEmployee;
import model.DAOStores;
import model.DAOTitles;

/**
 *
 * @author SY NGUYEN
 */
public class HomePageController extends HttpServlet {

    DAOTitles dao = new DAOTitles();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            String userName = request.getParameter("username");
            HttpSession session = request.getSession();
            if (service == null) { // call controller direct
                response.sendRedirect("index.jsp");
            }
            switch (service) {
                default:
                    String submit = request.getParameter("submit");
                    Vector<Titles> vector = new Vector<>();
                    if (submit == null) {
                        submit = "ListAll";
                    }
                    switch (submit) {
                        case "Search By Name":
                            String name = request.getParameter("name");
                            vector = dao.searchName(name);
                            break;
                        case "Search By Price":
                            double from = Double.parseDouble(request.getParameter("from"));
                            double to = Double.parseDouble(request.getParameter("to"));
                            if (from <= to) {
                                vector = dao.searchByPrice(from, to);
                            } else {
                                vector = dao.viewAllTitles();
                            }
                            break;
                        case "Filter":
                            String pubname = request.getParameter("publisherName");
                            vector = dao.filterPub(pubname);
                            break;
                        default:
                            vector = dao.viewAllTitles();
                            break;
                    }
                    if (vector.isEmpty() || vector == null) {
                        out.println("<div class='alert alert-warning alert-dismissible fade show' role='alert'>\n"
                                + "No title available\n"
                                + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
                                + "</div>");
                        vector = dao.viewAllTitles();
                    }
                    request.setAttribute("LIST", vector);
                    dispath(request, response, "index.jsp");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
