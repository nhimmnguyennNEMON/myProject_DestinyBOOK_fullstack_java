

<%@page import="entity.employee"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Vector"%>
<%@page import="model.DAOSales, entity.Sales"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
        <title>List all Sales</title>
        <!--        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
                <link rel="stylesheet" href="CSS/style.css">-->
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                try {
                    DAOSales dao = new DAOSales();
                    Vector<Sales> vector = (Vector<Sales>) request.getAttribute("LIST");
                    employee emp = (employee) session.getAttribute("username");
        %>

        <div class="container">
            <div class="row">

                <div class="text-end col">
                    <form action="UserController" method="POST">
                        <!--<button name="service" value="home" class="submitkey" style=" margin-left: 15px;">Home</button>-->
                        <input disabled class="myForm_mini"  value="Nguyễn Văn Sỹ - HE150242">
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
                <h1 class="col"><%= request.getAttribute("TITLE")%></h1>
                <div class="col-md-9">
                    <div>
                        <form action="SaleController" style="margin-right: 1rem;" class="right_alight" >
                            <input type="text" name="name" class="searchbar" id="name" value="" placeholder="Search...">
                            <input type="submit" value="Search" name="submit" class="search_buttom">
                        </form>
                    </div>
                    <a href='SaleController?service=insertSale'><button class="submitkey" style="margin-left: 1rem;">Insert Sale</button></a>&emsp;
                    <table class="table_style">
                        <h3><%= request.getAttribute("TABLE_TITLE")%></h3>
                        <thead class="text-center table-dark">
                            <tr>
                                <th>Store</th>
                                <th>Order Number</th>
                                <th>Order Date</th>
                                <th>Quantity</th>
                                <th>Payterm</th>
                                <th>Title</th>
                                <th>Status</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Sales temp : vector) {%>
                            <tr>
                                <% ResultSet rs1 = dao.getData("SELECT  * FROM dbo.stores WHERE stor_id like '" + temp.getStor_id() + "'");
                                    if (rs1.next()) {%>
                                <td><%=rs1.getString(2)%></td>
                                <%}%>
                                <td><%=temp.getOrd_num()%></td>
                                <%try {%>
                                <td><%=new SimpleDateFormat("dd MMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(temp.getOrd_date()))%></td>
                                <%} catch (Exception ex) {
                                        ex.printStackTrace();
                                    }%>
                                <td><%=temp.getQty()%></td>
                                <td><%=temp.getPayterms()%></td>
                                <% rs1 = dao.getData("select * from titles where title_id = '" + temp.getTitle_id() + "'");

                                    if (rs1.next()) {%>
                                <td><%=rs1.getString(2)%></td>
                                <%}%>
                                <td><%
                                    if (temp.getStatus() == 1) {
                                        out.print("Wait");
                                    }
                                    if (temp.getStatus() == 2) {
                                        out.print("Process");
                                    }
                                    if (temp.getStatus() == 3) {
                                        out.print("Done");
                                    }
                                    %></td>
                                <td><a class='btn btn-outline-dark' style="color: green" href='SaleController?service=updateSales&storeID=<%=temp.getStor_id()%>&orderNum=<%=temp.getOrd_num()%>&titleID=<%=temp.getTitle_id()%>'>Update</a></td>
                                <%if (temp.getStatus() == 1) {%>
                                <td><a class='btn btn-outline-dark' href='SaleController?service=deleteSales&storeID=<%=temp.getStor_id()%>&orderNum=<%=temp.getOrd_num()%>&titleID=<%=temp.getTitle_id()%>'>Delete</a></td>
                                <%}else{%>
                                <td><a class='btn btn-outline-dark'style="color: blue" >No Change Status</a></td>
                                <%}%>
                            </tr>
                            <%}%>
                        </tbody>
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
