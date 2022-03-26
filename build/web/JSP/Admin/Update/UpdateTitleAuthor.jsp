
<%@page import="entity.employee"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
        <title>List all Title-Authors</title>
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
                <h1 class="col">Update TitleAuthor</h1>
                <div class="col-md-9">
                    <form action='TitleAuthorController' method = 'POST'>
                        <input class="form-control border-0 bg-transparent" type="hidden" value="updateTitleAuthor" name="service">
                        <table class="myForm">
                            <%
                                ResultSet rsTitleAuthor = (ResultSet) request.getAttribute("rsTitleAuthor");
                                ResultSet rsAuthor = (ResultSet) request.getAttribute("rsAuthor");
                                ResultSet rsTitle = (ResultSet) request.getAttribute("rsTitle");
                                if (rsTitleAuthor.next()) {
                            %>
                            <tbody>
                                <tr>
                                    <td><label for='au_id'>Author</label></td>
                                    <td>
                                        <select disabled class='form-select bg-transparent' name='au_id' id='au_id'>
                                            <%while (rsAuthor.next()) {
                                            if (rsAuthor.getString(1).equals(rsTitleAuthor.getString(1))) {%>
                                            <option value='<%=rsAuthor.getString(1)%>' selected=""><%=rsAuthor.getString(2)%></option>
                                            <%  } else {%>
                                            <option value='<%=rsAuthor.getString(1)%>'><%=rsAuthor.getString(2)%></option>
                                            <%  }%>
                                            <%}%>
                                        </select>
                                        <input class='form-control bg-transparent' type='text' hidden id ='titleId' name='au_id' value='<%=rsTitleAuthor.getString(1)%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='title_id'>Title</label></td>
                                    <td>
                                        <select disabled class='form-select bg-transparent' name='title_id' id='title_id'>
                                            <%while (rsTitle.next()) {
                                            if (rsTitle.getString(1).equals(rsTitleAuthor.getString(2))) {%>
                                            <option value='<%=rsTitle.getString(1)%>' selected=""><%=rsTitle.getString(2)%></option>
                                            <%  } else {%>
                                            <option value='<%=rsTitle.getString(1)%>'><%=rsTitle.getString(2)%></option>
                                            <%  }%>
                                            <%}%>
                                        </select>
                                        <input class='form-control bg-transparent' type='text' hidden id ='titleId' name='title_id' value='<%=rsTitleAuthor.getString(2)%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='auOrd'>Author Order</label></td>
                                    <td><input class='form-control border-0 bg-transparent' type='number' id ='auOrd' name='auOrd' value='<%=rsTitleAuthor.getInt(3)%>'></td>
                                </tr>
                                <tr>
                                    <td><label for='royaltyper'>Royal Typer</label></td>
                                    <td><input class='form-control border-0 bg-transparent' type='number' id ='royaltyper' name='royaltyper' value='<%=rsTitleAuthor.getInt(4)%>'></td>
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
