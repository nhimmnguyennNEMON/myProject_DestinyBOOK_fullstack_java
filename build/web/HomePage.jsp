<%-- 
    Document   : DisplayTitle
    Created on : Feb 10, 2022, 3:19:41 PM
    Author     : SY NGUYEN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.Titles, model.DAOTitles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/style_home.css" rel="stylesheet" type="text/css"/>
        <title>Home Page</title>
    </head>
    <body>
        <%
            try {
                String username = request.getParameter("username");
//                session.setAttribute("username", userName);
                DAOTitles dao1 = new DAOTitles();
                DAOTitles dao = new DAOTitles();
                Titles title = new Titles();
//                Vector<Titles> vector = dao.viewAllTitles();
                Vector<Titles> vector = (Vector<Titles>) request.getAttribute("LIST");
                ResultSet rs1 = dao1.getData("SELECT * FROM dbo.publishers");
                if (vector == null) {
                    vector = dao.viewAllTitles();
                }
        %>
        <h2>Destiny BOOK</h2>
        <div style="margin-right: 185px;">
            <div>
                <form action="HomePageController" style="margin-right: 1rem;" method="post" class="right_alight" >
                    <input type="hidden" name="service" value="Search By Name">
                    <input type="text" class="searchbar" placeholder=" Search By Name" id="name" name="name" >
                    <input type="submit" value="Search By Name" name="submit" class="btn btn-outline-primary">
                </form>
            </div>
            <div >
                <form action="HomePageController" style="margin-right: 1rem;" method="post" class="right_alight">
                    <input type="hidden" name="service" value="Search By Price">
                    <input type="text" class="searchbar" placeholder=" From" id="from" name="from">
                    <input type="text" class="searchbar" placeholder=" To" id="to" name="to">
                    <input type="submit" value="Search By Price" name="submit" class="btn btn-outline-primary">
                </form>
            </div>
            <form action="HomePageController"  class="right_alight">
                <input type="hidden" name="service" value="Filter">
                <select id="inputState" class="right_alight" name="publisherName" width: 200px">
                        <% while (rs1.next()) {%>
                        <option value='<%=rs1.getString(1)%>' name="<%=rs1.getString(1)%>"><%=rs1.getString(2)%></option>
                    <%}%>
                </select>
                <input type="submit" value="Filter" name="submit" class="btn btn-outline-primary" style="margin: 5px;">
            </form>
        </div>
        <table class="table_style">
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Buy Now</th>
                </tr>
            </thead>
            <tbody>
                <%for (Titles temp : vector) {%>
                <tr>
                    <td><img class="rounded" style="box-shadow: 5px 5px 10px 1px #aaaaaa;" src = "<%=temp.getImage()%>"></td>
                    <td><%=temp.getTitle()%></td>
                    <td><%=temp.getPrice()%></td>
                    <td><%=temp.getRoyalty()%></td>
                    <%if (username != null) {%>
                    <td><a class="btn btn-outline-primary" href="CartController?service=addToCart&titleID=<%=temp.getTitle_id()%>&username=<%=username%>">Add</a></td>
                    <%} else {%>
                    <td><a class="btn btn-outline-primary" href='CartController?service=addToCart&titleID=<%=temp.getTitle_id()%>'>Add</a></td>
                    <%}%>
                    <%}%>
                </tr>
            </tbody>
        </table>
        <%
            } catch (Exception e) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" ></script>

    </body>
</html>
