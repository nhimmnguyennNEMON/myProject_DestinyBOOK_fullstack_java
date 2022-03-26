package controller;

import entity.Titles;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOTitles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "TitleController", urlPatterns = {"/TitleController"})
public class TitleController extends HttpServlet {

    DAOTitles dao = new DAOTitles();

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
                service = "listAllTitles";
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
                        case "Search By ID":
                            String titleid = request.getParameter("titleid");
                            vector = dao.searchID(titleid);
                            break;
                        case "Search By Price":
                            double from = Double.parseDouble(request.getParameter("from"));
                            double to = Double.parseDouble(request.getParameter("to"));
                            if (from <= to) {
                                vector = dao.searchByPrice(from, to);
                            } else {
                                out.println("<div class='alert alert-warning alert-dismissible fade show' role='alert'>\n"
                                        + "From (" + from + ") is larger than To(" + to + ")\n"
                                        + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
                                        + "</div>");
                            }
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
                    request.setAttribute("TITLE", "Titles Manage");
                    request.setAttribute("TABLE_TITLE", "List All Titles");
                    dispath(request, response, "/JSP/Admin/Display/DisplayTitle.jsp");
                    break;
                case "insertTitle":
                    String submitInsert = request.getParameter("submitInsert");
                    if (submitInsert == null) {
                        out.println("<head>\n"
                                + "        <meta charset='UTF-8'>\n"
                                + "        <title>Insert Title</title>\n"
                                + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                //                                + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                + "    </head>");
                        out.printf(" <form action='TitleController' method='post' style=\"margin:5rem;\">");
                        out.printf(" <input class='form-control' type='hidden' name='service' value='insertTitle'>");
                        out.printf(" <table class='myForm'>");
                        out.printf(" <tbody>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='titleId'>TitleID</label></td>");
                        out.printf(" <td><input class='form-control' type='text' id ='titleId' name='titleId' maxlength='6'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='title'>Title</label></td>");
                        out.printf(" <td><input class='form-control' type='text' id ='title' name='title' maxlength='80'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='type'>Type</label></td>");
                        out.printf(" <td><input class='form-control' type='text' id ='type' name='type' maxlength='12'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='pubid'>Publisher</label></td>");
                        out.printf(" <td><select class='form-select' name='pubid' id='pubid'>");
                        ResultSet rs1 = dao.getData("select * from publishers");
                        while (rs1.next()) {
                            out.printf("<option value='" + rs1.getString(1) + "'>" + rs1.getString(2) + "</option>");
                        }
                        out.printf("</select></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='price'>Price</label></td>");
                        out.printf(" <td><input class='form-control' type='number' step='any' id ='price' name='price' value='0' min='0'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='advanced'>Advanced</label></td>");
                        out.printf(" <td><input class='form-control' type='number' step='any' id ='advanced' name='advanced' value='0' min='0'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='royalty'>Royalty</label></td>");
                        out.printf(" <td><input class='form-control' type='number' step='any' id ='royalty' name='royalty' value='0' min='0'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='ytdsales'>Year to Date sales</label></td>");
                        out.printf(" <td><input class='form-control' type='number' step='any' id ='ytdsales' name='ytdsales' value='0' min='0'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='notes'>Notes:</label></td>");
                        out.printf(" <td><textarea class='form-select' id='notes' name='notes' rows='4' cols='50'></textarea></td>");
                        out.printf(" </tr>");
                        out.printf(" ");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='pubdate'>Publish Date</label></td>");
                        out.printf(" <td><input class='form-control' type='date' id ='pubdate' name='pubdate'></td>");
                        out.printf(" </tr>");
                        out.printf(" <tr>");
                        out.printf(" <td><label for='image'>Image</label></td>");
                        out.printf(" <td><input class='form-control' type='file' id ='image' name='image'></td>");
                        out.printf(" </tr>");
                        out.printf(" ");
                        out.printf(" <tr>");
                        out.printf(" <td>");
                        out.printf(" <input class=\"submitkey\" type='submit' value='Insert to Database' name='submitInsert'>");
                        out.printf(" </td>");
                        out.printf(" <td>");
                        out.printf(" <input class=\"submitkey\" type='submit' value='Go back' name='submitInsert' >");
                        out.printf(" </td>");
                        out.printf(" <td>");
                        out.printf(" <input class=\"submitkey\" type='reset' value='Reset'>");
                        out.printf(" </td>");
                        out.printf(" </tr> ");
                        out.printf(" </tbody>");
                        out.printf(" </table>");
                        out.printf(" </form>");
                        out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");

                    } else {
                        switch (submitInsert) {
                            case "Insert to Database":
                                String title_id = request.getParameter("titleId");
                                String title = request.getParameter("title");
                                String type = request.getParameter("type");
                                String pub_id = request.getParameter("pubid");
                                double price = Double.parseDouble(checkNumber(request.getParameter("price")));
                                double advanced = Double.parseDouble(checkNumber(request.getParameter("advanced")));
                                int royalty = Integer.parseInt(checkNumber(request.getParameter("royalty")));
                                int ytd_sales = Integer.parseInt(checkNumber(request.getParameter("ytdsales")));
                                String notes = request.getParameter("notes");
                                String pubdate = request.getParameter("pubdate");
                                String images = "images/" + request.getParameter("image");

                                if (checkString(title_id) == null || checkString(title) == null
                                        || checkString(type) == null || checkString(pub_id) == null
                                        || checkString(pubdate) == null) {
                                    out.println("<div class='alert alert-warning alert-dismissible fade show' role='alert'>\n"
                                            + "Cannot be null\n"
                                            + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
                                            + "</div>");
                                    return;
                                }

                                Titles ojb = new Titles(title_id, title, type, pub_id, price, advanced,
                                        royalty, ytd_sales, notes, pubdate, images);

                                dao.addTitles(ojb);
                                response.sendRedirect("TitleController");
                                break;

                            default:
                                response.sendRedirect("TitleController");
                                break;

                        }
                    }

                    break;
                case "updateTitle":
                    String submitUpdate = request.getParameter("submitUpdate");
                    if (submitUpdate == null) { // chua nhan button submit
                        String titleID = request.getParameter("titleID");
                        String sql = "Select * from Titles where title_id='" + titleID + "'";
                        ResultSet rs = dao.getData(sql);
                        ResultSet rs1 = dao.getData("SELECT * FROM dbo.publishers");
                        request.setAttribute("rsTitle", rs);
                        request.setAttribute("rsPublisher", rs1);
                        dispath(request, response, "/JSP/Admin/Update/UpdateTitle.jsp");
                    } else {
                        switch (submitUpdate) {
                            case "Update to Database":
                                String title_id = request.getParameter("titleId");
                                String title = request.getParameter("title");
                                String type = request.getParameter("type");
                                String pub_id = request.getParameter("pubid");
                                double price = Double.parseDouble(checkNumber(request.getParameter("price")));
                                double advanced = Double.parseDouble(checkNumber(request.getParameter("advanced")));
                                int royalty = Integer.parseInt(checkNumber(request.getParameter("royalty")));
                                int ytd_sales = Integer.parseInt(checkNumber(request.getParameter("ytdsales")));
                                String notes = request.getParameter("notes");
                                String pubdate = request.getParameter("pubdate");
                                String image = request.getParameter("image");
                                if (checkString(title_id) == null || checkString(title) == null
                                        || checkString(type) == null || checkString(pub_id) == null
                                        || checkString(pubdate) == null) {
                                    out.println("<div class='alert alert-warning alert-dismissible fade show' role='alert'>\n"
                                            + "Cannot be null\n"
                                            + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"
                                            + "</div>");
                                    return;
                                }
                                Titles ojb = new Titles(title_id, title, type, pub_id, price, advanced,
                                        royalty, ytd_sales, notes, pubdate, image);

                                dao.updateTitles(ojb);
                                response.sendRedirect("TitleController");
                                break;
                            default:
                                response.sendRedirect("TitleController");
                                break;
                        }

                    }
                    break;
                case "deleteTitle":
                    String submitDelete = request.getParameter("submitDelete");
                    if (submitDelete == null) {
                        String titleID = request.getParameter("titleID");
                        String sql = "Select * from Titles where title_id='" + titleID + "'";
                        ResultSet rs = dao.getData(sql);
                        if (rs.next()) {
                            out.println("<head>\n"
                                    + "        <meta charset='UTF-8'>\n"
                                    + "        <title>Delete Title #" + titleID + "</title>\n"
                                    + "        <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n"
                                    + "        <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n"
                                    //                                    + "        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>\n"
                                    + "    <link rel=\"stylesheet\" href=\"CSS/style.css\">\n"
                                    + "    </head>");
                            out.printf("<form action='TitleController' method='post' style=\"margin:5rem;\">");
                            out.printf(" <input class='form-control' type='hidden' name='service' value='deleteTitle'>");
                            out.printf(" <table class='myForm'>");
                            out.printf(" <tbody>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='titleId'>TitleID</label></td>");
                            out.printf(" <td>");
                            out.printf(" <input class='form-control' type='text' id ='titleId' maxlength='6' value='" + rs.getString(1) + "' disabled>");
                            out.printf(" <input class='form-control' type='hidden' id ='titleId' name='titleId' maxlength='6' value='" + rs.getString(1) + "'>");
                            out.printf(" </td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='title'>Title</label></td>");
                            out.printf(" <td><input class='form-control' disabled type='text' id ='title' name='title' maxlength='80' value='" + rs.getString(2) + "' disabled ></td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='type'>Type</label></td>");
                            out.printf(" <td><input class='form-control' disabled type='text' id ='type' name='type' maxlength='12' value='" + rs.getString(3) + "' disabled ></td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='pubid'>Publisher</label></td>");
                            out.printf(" <td><select class='form-select' disabled name='pubid' id='pubid' disabled >");
                            ResultSet rs1 = dao.getData("SELECT * FROM dbo.publishers WHERE pub_id ='" + rs.getString(4) + "'");

                            while (rs1.next()) {
                                out.printf("<option value='" + rs1.getString(1) + "' >" + rs1.getString(2) + "</option>");
                            }
                            out.printf(" </select></td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='price'>Price</label></td>");
                            out.printf(" <td><input class='form-control' disabled type='number' step='any' id ='price' name='price' value='" + rs.getString(5) + "' min='0' disabled ></td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='advanced'>Advanced</label></td>");
                            out.printf(" <td><input class='form-control' disabled type='number' step='any' id ='advanced' name='advanced' value='" + rs.getString(6) + "' min='0' disabled ></td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='royalty'>Royalty</label></td>");
                            out.printf(" <td><input class='form-control' disabled type='number' step='any' id ='royalty' name='royalty' value='" + rs.getString(7) + "' min='0' disabled ></td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='ytdsales'>Year to Date sales</label></td>");
                            out.printf(" <td><input class='form-control' disabled type='number' step='any' id ='ytdsales' name='ytdsales' value='" + rs.getString(8) + "' min='0' disabled ></td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='notes'>Notes:</label></td>");
                            out.printf(" <td><textarea class='form-select' disabled id='notes' name='notes' rows='4' cols='50' disabled >" + rs.getString(9) + "</textarea></td>");
                            out.printf(" </tr>");
                            out.printf(" ");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='pubdate'>Publish Date</label></td>");
                            try {
                                out.printf(" <td><input class='form-control' disabled type='date' id ='pubdate' name='pubdate' value='" + new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(10))) + "' disabled ></td>");
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td><label for='image'>Image</label></td>");
                            out.printf(" <td><input class='form-control' disabled type='text' id ='image' name='image' value='" + rs.getString(11) + "' disabled ></td>");
                            out.printf(" </tr>");
                            out.printf(" ");
                            out.printf(" <tr>");
                            out.printf(" <td>");
                            out.printf(" <h2>Do you want to delete this title ?</h2>");
                            out.printf(" </td>");
                            out.printf(" </tr>");
                            out.printf(" <tr>");
                            out.printf(" <td>");
                            out.printf(" <input class=\"submitkey\" style=\"color:red;\" type='submit' value='Yes' name='submitDelete'>");
                            out.printf(" </td>");
                            out.printf(" <td>");
                            out.printf(" <input class=\"submitkey\" style=\"color:blue;\" type='submit' value='No' name='submitDelete'>");
                            out.printf(" </td>");
                            out.printf(" </tr> ");
                            out.printf(" </tbody>");
                            out.printf(" </table>");
                            out.printf(" </form>");

                            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>\n"
                                    + "  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>");
                        }

                    } else {
                        switch (submitDelete) {
                            default:
                                response.sendRedirect("TitleController");
                                break;
                            case "Yes":
                                String id = request.getParameter("storeId");
                                dao.removeTitles(id);
                                response.sendRedirect("TitleController");
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
