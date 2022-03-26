
<%@page import="entity.employee"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.Discount"%>
<%@page import="model.DAODiscount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List all Discounts</title>
        <link rel="stylesheet" href="CSS/style.css">
<!--        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>
        <link rel="stylesheet" href="CSS/style.css">-->
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                try {
                    DAODiscount dao = new DAODiscount();
                    employee emp = (employee) session.getAttribute("username");
                    Vector<Discount> vector = (Vector<Discount>) request.getAttribute("LIST");
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
                <h1 class="col"><%= request.getAttribute("TITLE")%></h1>
                <div class="col-md-9">
                    <div>
                        <form action="DiscountController" style="margin-right: 1rem;" class="right_alight" >
                            <input type="text" name="name" class="searchbar" id="name" value="" placeholder="Search...">
                            <input type="submit" value="Search" name="submit" class="search_buttom">
                        </form>
                    </div>
                    <a href='DiscountController?service=insertDiscount'><button class="submitkey" style="margin-left: 1rem;">Insert Discount</button></a>&emsp;
                    <table class="table_style" >
                        <h3><%= request.getAttribute("TABLE_TITLE")%></h3>
                        <thead class="text-center table-dark">
                            <tr>
                                <th>Discount Type</th>
                                <th>Store</th>
                                <th>Low Quantity</th>
                                <th>High Quantity</th>
                                <th>Discount</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Discount temp : vector) {%>
                            <tr>
                                <td><%=temp.getDiscounttype()%></td>
                                <td><% ResultSet rs1 = dao.getData("SELECT  * FROM dbo.stores WHERE stor_id like '" + temp.getStor_id() + "'");
                            if (rs1.next()) {%>
                                    <%=rs1.getString(2)%>
                                    <%} else {%>
                                    null
                                    <%}%></td>
                                <td><%=temp.getLowqty()%></td>
                                <td><%=temp.getHighqty()%> </td>
                                <td><%=temp.getDiscount()%></td>
                        <form action="DiscountController" method="POST">
                            <td>
                                <input type="hidden" name="discount" value="<%=temp.getDiscount()%>">
                                <input type="hidden" name="discountType" value="<%=temp.getDiscounttype()%>">
                                <button class='btn btn-outline-dark' name="service" value="updateDiscount">Update</button>
                            </td>
                            <td><button class='btn btn-outline-dark' name="service" value="deleteDiscount">Delete</button></td>
                        </form>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" ></script>
    </body>
</html>