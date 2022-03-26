<%-- 
    Document   : Profile
    Created on : Mar 12, 2022, 10:51:01 PM
    Author     : SY NGUYEN
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Stores"%>
<%@page import="model.DAOStores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/style_home.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                try {
                    ResultSet rsSalesDetail = (ResultSet) request.getAttribute("rsSalesDetail");
                    ResultSet rsStore = (ResultSet) request.getAttribute("rsSalesDetail1");
        %>
        <div class="container">
            <div class="row">
                <div class="text-end col">
                    <%
                        String username = request.getParameter("username");
                        DAOStores daoS = new DAOStores();
                        Stores s = daoS.checklog(username);
                        if (s != null) {
                    %>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-success" href="UserController?service=home&username=<%=username%>">Home</a>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-secondary" href="CartController?service=showCart&username=<%=username%>">Show Cart</a>
                    <a style=" margin:  1rem;" class="col btn btn-outline-dark" href="UserController?service=Profile&username=<%=username%>">Welcome: <%= username%></a>
                    <a style=" margin:  1rem; margin-right: 25px;" name="link" class="btn btn-outline-dark" href="UserController?service=Logout">Logout</a>
                    <%} else {
                        try {
                    %>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-success" href="Register.jsp">Register</a>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-dark" href="Login.jsp">Login</a>
                    <%
                            } catch (Exception e) {
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                        }
                    %>
                </div>
            </div>
            <h2>Bill Detail</h2>
            <table class="table_style">
                <thead class="text-center table-dark">
                    <tr style="color: #000000">
                        <th>Order Number</th>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Payment</th>
                        <th>Status</th>
                        <th>Delete</th>
                    </tr>
                </thead>


                <tbody class="text-center">
                    <%
                        while (rsSalesDetail.next()) {
                    %>  
                    <tr>
                        <td><%=rsSalesDetail.getString(1)%></td>
                        <td> <image src="<%=rsSalesDetail.getString(7)%>" class="rounded" style="box-shadow: 5px 5px 10px 1px #aaaaaa;"></td>
                        <td><%=rsSalesDetail.getString(2)%></td>
                        <td><%=rsSalesDetail.getInt(4)%></td>
                        <td><%=rsSalesDetail.getDouble(3)%></td>
                        <td><%=rsSalesDetail.getDouble(5)%></td>
                        <td><%=rsSalesDetail.getString(8)%></td>
                        <td><%
                            if (rsSalesDetail.getInt(6) == 1) {
                                out.print("<span style=\"color:red;\">Wait</span>");
                            }
                            if (rsSalesDetail.getInt(6) == 2) {
                                out.print("<span style=\"color:blue;\">Process</span>");
                            }
                            if (rsSalesDetail.getInt(6) == 3) {
                                out.print("<span style=\"color:green;\">Done</span>");
                            }
                            %></td>
                        <%
                            if(rsSalesDetail.getInt(6) == 1){
                        %>
                        <td><a class='btn btn-outline-dark'  href='UserController?service=delete&ordNum=<%=rsSalesDetail.getString(1)%>&username=<%=username%>'>Delete</a></td>
                        <%}else{%>
                        <td></td>
                        <%}%>
                    </tr>
                    <%}%>
                </tbody>
            </table>

            <h2 class="col">Update Imfomation Client</h2>
                <form action='UserController' method = 'POST'>
                    <input class="form-control border-0 bg-transparent" type="hidden" value="updateStore" name="service">
                    <table class="table_style">
                        <%
                            if (rsStore.next()) {
                        %>
                        <tbody>
                            <tr>
                                <td><label for='storeId'>Store ID</label></td>
                                <td>
                                    <input class='form-control border-0 bg-transparent' type='text' disabled id ='storeId' value='<%=rsStore.getString(1)%>'>
                                    <input class='form-control border-0 bg-transparent' type='hidden' name='storeId' value='<%=rsStore.getString(1)%>'>
                                </td>
                            </tr>
                            <tr>
                                <td><label for='storename'>Store Name</label></td>
                                <td><input class='form-control border-0 bg-transparent' type='text' id ='storename' name='storename' required value='<%=rsStore.getString(2)%>'></td>
                            </tr>
                            <tr>
                                <td><label for='storeaddress'>Store Address</label></td>
                                <td><input class='form-control border-0 bg-transparent' type='text' id ='storeaddress' name='storeaddress' required value='<%=rsStore.getString(3)%>'></td>
                            </tr>
                            <tr>
                                <td><label for='city'>City</label></td>
                                <td><input class='form-control border-0 bg-transparent' type='text' id ='city' name='city' required value='<%=rsStore.getString(4)%>'></td>
                            </tr>
                            <tr>
                                <td><label for='state'>State</label></td>
                                <td><input class='form-control border-0 bg-transparent' type='text' id ='state' name='state' maxlength="2" required value='<%=rsStore.getString(5)%>'></td>
                            </tr>             
                            <tr>
                                <td><label for='zip'>Zip</label></td>
                                <td><input class='form-control border-0 bg-transparent' type='text' id ='zip' name='zip' maxlength="5" required value='<%=rsStore.getString(6)%>'></td>
                            </tr>
                            <tr>
                                <td><label for='zip'>Username</label></td>
                                <td>
                                    <input class='form-control border-0 bg-transparent' type='text' id ='username' disabled required value='<%=rsStore.getString(7)%>'>
                                    <input class='form-control border-0 bg-transparent' type='hidden' id ='username' name='usename' required value='<%=rsStore.getString(7)%>'>
                                </td>
                            </tr> 
                            <tr>
                                <td><label for='zip'>Password</label></td>
                                <td>
                                    <input class='form-control border-0 bg-transparent' type='password' id ='password' disabled name='password' required value='<%=rsStore.getString(8)%>'>
                                    <input class='form-control border-0 bg-transparent' type='hidden' id ='password' name='password' required value='<%=rsStore.getString(8)%>'>
                                </td>
                            </tr> 
                        </tbody>
                        <%}%>
                    </table>
                    <div class="row float-end" style="margin-bottom: 50px">
                        <input type="hidden" value="<%=username%>" name="accUsername">
                        <input class="btn btn-outline-secondary" type="submit" value="Update Imfomation">
                    </div>
                </form>
            </div>
        <%
                } catch (Exception e) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" ></script>

    </body>
</html>
