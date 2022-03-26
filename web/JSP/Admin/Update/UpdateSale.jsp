
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
        <title>List all Sales</title>
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
                <h1 class="col">Update Sale</h1>
                <div class="col-md-9">
                    <form action='SaleController' method = 'POST'>
                        <input class="form-control border-0 bg-transparent" type="hidden" value="updateSale" name="service">
                        <table class="myForm">
                            <%
                                ResultSet rsSale = (ResultSet) request.getAttribute("rsSale");
                                ResultSet rsTitle = (ResultSet) request.getAttribute("rsTitle");
                                ResultSet payterm = (ResultSet) request.getAttribute("payterm");
                                ResultSet rsStore = (ResultSet) request.getAttribute("rsStore");
                                String[] status = (String[]) request.getAttribute("status");
                                if (rsSale.next()) {
                            %>
                            <tbody>
                                <tr>
                                    <td><label for='storeId'>Store</label></td>
                                    <td>
                                        <select disabled class='form-select bg-transparent' name='storeId' id='storeId'>
                                            <%while (rsStore.next()) {
                                            if (rsStore.getString(1).equals(rsSale.getString(1))) {%>
                                            <option value='<%=rsStore.getString(1)%>' selected=""><%=rsStore.getString(2)%></option>
                                            <%  } else {%>
                                            <option value='<%=rsStore.getString(1)%>'><%=rsStore.getString(2)%></option>
                                            <%  }%>
                                            <%}%>
                                        </select>
                                        <input class='form-control bg-transparent' type='text' hidden id ='royalty' name='title_id' value='<%=rsSale.getString(1)%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='ordnum'>Order Number</label></td>
                                    <td>
                                        <input class='form-control border-0 bg-transparent' type='text' disabled id ='ordnum' value='<%=rsSale.getString(2)%>'>
                                        <input class='form-control border-0 bg-transparent' type='hidden' name='ordnum' value='<%=rsSale.getString(2)%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='orddate'>Order Date</label></td>
                                    <td>
                                        <input class='form-control border-0 bg-transparent' style="margin-left: 70px; margin-bottom: 20px;" type='date' id ='orddate' name='orddate' value='<%=rsSale.getString(3).split(" ")[0]%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='qty'>Quantity</label></td>
                                    <td>
                                        <input class='form-control border-0 bg-transparent' type='number' name="qty" id ='qty' value='<%=rsSale.getInt(4)%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='payterms'>Payterms</label></td>
                                    <td>
                                        <select class='form-select bg-transparent' name='payterms' id='payterms'>
                                            <%while (payterm.next()) {
                                            if (payterm.getString(1).equals(rsSale.getString(5))) {%>
                                            <option value='<%=payterm.getString(1)%>' selected=""><%=payterm.getString(1)%></option>
                                            <%  } else {%>
                                            <option value='<%=payterm.getString(1)%>'><%=payterm.getString(1)%></option>
                                            <%  }%>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='titleId'>Title</label></td>
                                    <td>
                                        <select disabled class='form-select bg-transparent' name='titleId' id='titleId'>
                                            <%while (rsTitle.next()) {
                                            if (rsTitle.getString(1).equals(rsSale.getString(6))) {%>
                                            <option value='<%=rsTitle.getString(1)%>' selected=""><%=rsTitle.getString(2)%></option>
                                            <%  } else {%>
                                            <option value='<%=rsTitle.getString(1)%>'><%=rsTitle.getString(2)%></option>
                                            <%  }%>
                                            <%}%>
                                        </select>
                                        <input class='form-control bg-transparent' type='text' hidden id ='titleId' name='titleId' value='<%=rsSale.getString(6)%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <select disabled="" name="status" class="form-select bg-transparent statusComboBox">
                                            <%for (int i = 0; i < status.length; i++) {
                                            if (rsSale.getInt(7) - 1 == i) {%>
                                            <option selected value="<%=i + 1%>"><%=status[i]%></option>
                                            <%} else {%>
                                            <option value="<%=i + 1%>"><%=status[i]%></option>
                                            <%}%>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <%}%>
                        </table>
                        <div class="row float-end" style="text-align: center">
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
