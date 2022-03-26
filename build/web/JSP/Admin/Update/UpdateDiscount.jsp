
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
        <title>List all Discounts</title>
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
                <h1 class="col">Update Discount</h1>
                <div class="col-md-9">
                    <form action='DiscountController' method = 'POST'>
                <input class="form-control border-0 bg-transparent border-0 bg-transparent" type="hidden" value="updateDiscount" name="service">
                <table class="myForm">
                    <%
                        ResultSet rsDiscount = (ResultSet) request.getAttribute("rsDiscount"),
                                rsStore = (ResultSet) request.getAttribute("rsStore");
                        if (rsDiscount.next()) {
                    %>
                    <tbody>

                        <tr>
                            <td><label for='discountType'>Order Date</label></td>
                            <td>
                                <input class='form-control border-0 bg-transparent' disabled type='text' id ='discountType' name='discountType' value='<%=rsDiscount.getString(1)%>'>
                                <input class='form-control border-0 bg-transparent' type='hidden' id ='discountType' name='discountType' value='<%=rsDiscount.getString(1)%>'>
                            </td>
                        </tr>
                        <tr>
                            <td><label for='storeId'>Store</label></td>
                            <td>
                                <select class='form-select border-0 bg-transparent' name='storeId' id='storeId'  >
                                    <%if (rsDiscount.getString(2) == null) {%>
                                    <option value="" >null</option>
                                    <%}%>
                                    <%while (rsStore.next()) {
                                            if (rsStore.getString(1).equals(rsDiscount.getString(2))) {%>
                                    <option  value ='<%=rsStore.getString(1)%>' selected><%=rsStore.getString(2)%></option>
                                    <%} else {%>
                                    <option  value ='<%=rsStore.getString(1)%>' ><%=rsStore.getString(2)%></option>
                                    <%}%>
                                    <%}%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label for='lowQty'>Low Quantity</label></td>
                            <td>
                                <%if (rsDiscount.getString(3) == null) {%>
                                <input class='form-control border-0 bg-transparent' type='number' id ='lowQty' name='lowQty' value='-1'>
                                <%} else {%>
                                <input class='form-control border-0 bg-transparent' type='number' id ='lowQty' name='lowQty' value='<%=rsDiscount.getString(3)%>'>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td><label for='highQty'>High Quantity</label></td>
                            <td>
                                <%if (rsDiscount.getString(4) == null) {%>
                                <input class='form-control border-0 bg-transparent' type='number' id ='highQty' name='highQty' value='-1'>
                                <%} else {%>
                                <input class='form-control border-0 bg-transparent' type='number' id ='highQty' name='highQty' value='<%=rsDiscount.getString(4)%>'>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td><label for='discount'>Discount</label></td>
                            <td>
                                <input class='form-control border-0 bg-transparent' type='hidden' id ='discount' name='discount' value='<%=rsDiscount.getString(5)%>'>
                                <input class='form-control border-0 bg-transparent' id ='discount' disabled value='<%=rsDiscount.getString(5)%>'>
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
