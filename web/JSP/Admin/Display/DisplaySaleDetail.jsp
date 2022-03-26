

<%@page import="entity.employee"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
        <title>List all Stores</title>
        <!--        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' crossorigin='anonymous'>
                <link rel="stylesheet" href="CSS/style.css">-->
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                try {
                    ResultSet rsSalesDetail = (ResultSet) request.getAttribute("rsSalesDetail");
                    double total = 0;
                    double sum = 0;
                    String storeName = (String) request.getAttribute("storeName"), storeID = (String) request.getAttribute("storeId");
                    String[] status = (String[]) request.getAttribute("status");
                    employee emp = (employee) session.getAttribute("username");
        %>

        <div class="container">
            <div class="row">

                <div class="text-end col">
                    <form action="UserController" method="POST">
                        <!--<button name="service" value="home" class="submitkey" style=" margin-left: 15px;">Home</button>-->
                        <input disabled class="myForm_mini" value="Nguyễn Văn Sỹ - HE150242">
                        <a disabled class="myForm_mini" href="Admin.jsp">Welcome: <%= emp.getUserName()%></a>
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
                <!--<h1 class="col"><%= request.getAttribute("TITLE")%></h1>-->
                <div class="col-md-9">
<!--                    <div>
                        <form action="AuthorController" style="margin-right: 1rem;" class="right_alight" >
                            <input type="text" name="name" class="searchbar" id="name" value="" placeholder="Search...">
                            <input type="submit" value="Search" name="submit" class="search_buttom">
                        </form>
                    </div>-->
                    <div class="row">
                        <div class="myForm" style=" margin-top: 50px;"><strong>Store ID:</strong> <%=storeID%></div>
                        <div class="myForm"><strong>Store Name:</strong> <%=storeName%></div>
                    </div>
                    <table class="table_style">
                        <thead class="text-center table-dark">
                            <tr>
                                <th>Order Number</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        

                        <tbody class="text-center">
                            <%
                                while (rsSalesDetail.next()) {
                                    total += rsSalesDetail.getDouble(5);
                                    sum += rsSalesDetail.getDouble(5);
                            %>  
                            <tr>
                                <td><%=rsSalesDetail.getString(1)%></td>
                                <td><%=rsSalesDetail.getString(2)%></td>
                                <td><%=rsSalesDetail.getInt(4)%></td>
                                <td><%=rsSalesDetail.getDouble(3)%></td>
                                <td><%=rsSalesDetail.getDouble(5)%></td>
                                <td>
                                    <form action="SalesDetailController" class="updateStatus" method="GET">
                                        <input hidden="" name="storeID" value="<%=storeID%>">
                                        <input hidden="" name="service" value="updateStatus">
                                        <input hidden="" name="titleid" value="<%=rsSalesDetail.getString(7)%>">
                                        <input hidden="" name="OrderNumber" value="<%=rsSalesDetail.getString(1)%>">
                                        <select onchange="this.parentNode.submit()" name="status" class="form-select text-center bg-transparent statusComboBox">
                                            <%for (int i = 0; i < status.length; i++) {
                                                    if (rsSalesDetail.getInt(6) - 1 == i) {%>
                                            <option selected value="<%=i + 1%>"><%=status[i]%></option>
                                            <%} else {%>
                                            <option value="<%=i + 1%>"><%=status[i]%></option>
                                            <%}%>
                                            <%}%>
                                        </select>
                                    </form>

                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                        <p class="myForm"><strong>Sum Total: </strong><%=sum%>$</p>
                    </table>
                </div>
            </div>
        </div> 

        <%
                } catch (Exception e) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        %>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>
        <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>
    </body>
</html>