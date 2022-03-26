
<%@page import="model.DAOStores"%>
<%@page import="entity.Stores"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Main Menu</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
        <!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link href="CSS/style.css" rel="stylesheet">-->
    </head>
    <body>
        <%
            DAOStores dao = new DAOStores();
            Vector<Stores> vector = dao.viewAllStores();
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                try {
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
                <h1 class="col">Admin Manager</h1>
                <div class="col-md-9">

                </div>
            </div>

            <table class="table_style">
                <h3>Bill Detail</h3>
                <thead class="text-center table-dark">
                    <tr>
                        <th>Store ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Zip</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Sale Detail</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Stores temp : vector) {%>
                    <tr>
                        <td><%=temp.getStor_id()%></td>
                        <td><%=temp.getStor_name()%></td>
                        <td><%=temp.getStor_address()%></td>
                        <td><%=temp.getCity()%></td>
                        <td><%=temp.getState()%></td>
                        <td><%=temp.getZip()%></td>
                        <td><%=temp.getUserName()%></td>
                        <td><%=temp.getPassword()%></td>
                        <td><a class='btn btn-outline-dark' style="color: blue;" href='SalesDetailController?storeID=<%=temp.getStor_id()%>'>Detail</a></td>
                        <td><a class='btn btn-outline-dark' style="color: green;" href='StoreController?service=updateStores&storeID=<%=temp.getStor_id()%>'>Update</a></td>
                        <td><a class='btn btn-outline-dark' href='StoreController?service=deleteStores&storeID=<%=temp.getStor_id()%>'>Delete</a></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
        <footer class="bg-dark fixed-bottom text-center text-lg-start">
            <div class="text-center p-3 text-white"></div>
        </footer>
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