
<%@page import="entity.employee"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Titles"%>
<%@page import="model.DAOTitles"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
        <title>List all Titles</title>
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                try {
                    employee emp = (employee) session.getAttribute("username");
        %>

        <header class="bg-dark text-center text-lg-start">
        </header>
        <div class="container">
            <div class="row">
                <div class="text-end col">
                    <form action="UserController" method="POST">
                        <!--<button name="service" value="home" class="submitkey" style=" margin-left: 15px;">Home</button>-->
                        <input disabled class="myForm_mini" value="Nguyễn Văn Sỹ - HE150242">
                        <input disabled class="myForm_mini" value="Welcome: <%= emp.getUserName()%>">
                        <input hidden="" name="username" class="border-0 bg-transparent" value="<%= emp.getUserName()%>">
                        <button type="submit" style=" margin:  1rem;" name="service" value="Logout" class="submitkey">Logout</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="myForm_mini1">
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="AuthorController">Authors</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="DiscountController">Discount</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="EmployeeController">Employee</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="JobController">Jobs</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="PubInfoController">Publisher Info</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="PublisherController">Publisher</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="RoyschedController">Roysched</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="SaleController">Sale</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="StoreController">Stores</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="TitleController">Title</a>
                    <a style=" margin:  1rem;" name="link" class="btn bg-transparent text-start" href="TitleAuthorController">Title Author</a>
                </div>
                <h1 class="col">Update Title</h1>
                <div class="col-md-9">
                    <form action='TitleController' method = 'POST'>
                        <input class="form-control border-0 bg-transparent" type="hidden" value="updateTitle" name="service">
                        <table class="myForm">
                            <%
                                ResultSet rsTitle = (ResultSet) request.getAttribute("rsTitle"),
                                        rspush = (ResultSet) request.getAttribute("rsPublisher");
                                if (rsTitle.next()) {
                            %>
                            <tbody>
                                <tr>
                                    <td><label for="titleId">TitleID</label></td>
                                    <td>
                                        <input class="form-control border-0 bg-transparent" type="text" disabled value="<%=rsTitle.getString(1)%>" id ="titleId">
                                        <input class="form-control border-0 bg-transparent" type="hidden" value="<%=rsTitle.getString(1)%>" name="titleId">
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for="title">Title</label></td>
                                    <td><input class="form-control border-0 bg-transparent" type="text" value="<%=rsTitle.getString(2)%>" id ="title" name="title" maxlength="80"></td>
                                </tr>
                                <tr>
                                    <td><label for="type">Type</label></td>
                                    <td><input class="form-control border-0 bg-transparent" type="text" value="<%=rsTitle.getString(3)%>" id ="type" name="type" maxlength="12"></td>
                                </tr>
                                <tr>
                                    <td><label for="pubid">Publisher</label></td>
                                    <td><select class="form-select border-0 bg-transparent" name="pubid" id="pubid">
                                            <% while (rspush.next()) {
                                            if (rsTitle.getString(4).equals(rspush.getString(1))) {%>
                                            <option value='<%=rspush.getString(1)%>' selected=""><%=rspush.getString(2)%></option>
                                            <%} else {%>
                                            <option value='<%=rspush.getString(1)%>'><%=rspush.getString(2)%></option>
                                            <%}%>
                                            <%}%>
                                        </select></td>
                                </tr>
                                <tr>
                                    <td><label for="price">Price</label></td>
                                    <td><input class="form-control border-0 bg-transparent" type="number" value="<%=rsTitle.getDouble(5)%>" step="any" id ="price" name="price" value="0" min="0"></td>
                                </tr>
                                <tr>
                                    <td><label for="advanced">Advanced</label></td>
                                    <td><input class="form-control border-0 bg-transparent" type="number" value="<%=rsTitle.getDouble(6)%>" step="any" id ="advanced" name="advanced" value="0" min="0"></td>
                                </tr>
                                <tr>
                                    <td><label for="royalty">Royalty</label></td>
                                    <td><input class="form-control border-0 bg-transparent" type="number" value="<%=rsTitle.getInt(7)%>" step="any" id ="royalty" name="royalty" value="0" min="0"></td>
                                </tr>
                                <tr>
                                    <td><label for="ytdsales">Year to Date sales</label></td>
                                    <td><input class="form-control border-0 bg-transparent" type="number" value="<%=rsTitle.getInt(8)%>" step="any" id ="ytdsales" name="ytdsales" value="0" min="0"></td>
                                </tr>
                                <tr>
                                    <td><label for="notes">Notes:</label></td>
                                    <td><textarea id="notes" class="form-control border-0 bg-transparent" name="notes" rows="4" cols="50"><%=rsTitle.getString(9)%></textarea></td>
                                </tr>
                                <tr>
                                    <td><label for="pubdate">Publish Date</label></td>
                                    <td><input class="form-control border-0 bg-transparent" type="date" value="<%=rsTitle.getString(10).split(" ")[0]%>" id ="pubdate" name="pubdate"></td>
                                </tr>
                                <tr>
                                    <td><label for="image">Image</label></td>
                                    <td><input type="text" value="<%=rsTitle.getString(11)%>" id ="image" name="image"></td>
                                </tr>
                            </tbody>
                            <%}%>
                        </table>
                        <div class="row float-end" style="text-align: center;">
                            <input class="submitkey" type="submit" value="Back" name="submitUpdate">
                            <input class="submitkey" type="submit" value="Update to Database" name="submitUpdate">
                            <input class="submitkey" type="reset" value="Reset">
                        </div>
                    </form>
                </div>
            </div>
        </div> 
        <%
                } catch (Exception e) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        %>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js'/>
        <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js'/>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js'/>

    </body>
</html>
