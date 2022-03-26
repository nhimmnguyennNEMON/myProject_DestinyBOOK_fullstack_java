
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
        <title>List all Titles</title>
        <link rel="stylesheet" href="CSS/style.css">
        <!--        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' crossorigin='anonymous'>
                <link rel="stylesheet" href="./CSS/style.css">-->
    </head>
    <body>
        <%
            if (session.getAttribute("username") == null) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                try {
                    DAOTitles dao = new DAOTitles();
                    Vector<Titles> vector = (Vector<Titles>) request.getAttribute("LIST");
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
                        <form action="TitleController" style="margin-right: 1rem;" class="right_alight" >
                            <input type="text" class="searchbar" placeholder="Search By Name" id="name" name="name" >
                            <input type="submit" value="Search By Name" name="submit" class="search_buttom">
                        </form>
                    </div>
                    <div>
                        <form action="TitleController" style="margin-right: 1rem;" class="right_alight" >
                            <input type="text" class="searchbar" placeholder="Search By ID" id="name" name="titleid" >
                            <input type="submit" value="Search By ID" name="submit" class="search_buttom">
                        </form>
                    </div>
                    <div >
                    <div >
                        <form action="TitleController" style="margin-right: 1rem;" class="right_alight">
                            <input type="text" class="searchbar" placeholder="From" id="from" name="from">
                            <input type="text" class="searchbar" placeholder="To" id="to" name="to">
                            <input type="submit" value="Search By Price" name="submit" class="search_buttom">
                        </form>
                    </div>
                    <a href='TitleController?service=insertTitle'><button class="submitkey" style="margin-left: 1rem;">Insert Title</button></a>&emsp;

                    <table class="table_style">
                        <h3><%= request.getAttribute("TABLE_TITLE")%></h3>
                        <thead class="text-center table-dark">
                            <tr>
                                <th>Title_id</th>
                                <th>Title</th>
                                <th>Type</th>
                                <th>Publisher</th>
                                <th>Price</th>
                                <th>Advance</th>
                                <th>Royalty</th>
                                <th>ytd_sales</th>
                                <th>Notes</th>
                                <th>PubishDate</th>
                                <th>Image</th>
                                <th>Update</th>
                                <th>Delete</th>
                                <!--<th>Cart</th>-->
                            </tr>
                        </thead>
                        <tbody>
                            <%for (Titles temp : vector) {%>
                            <tr>
                                <td><%=temp.getTitle_id()%></td>
                                <td><%=temp.getTitle()%></td>
                                <td><%=temp.getType()%></td>
                                <%ResultSet rs1 = dao.getData("select * from publishers where pub_id = '" + temp.getPub_id() + "'");
                                    if (rs1.next()) {%>
                                <td><%=rs1.getString(2)%></td>
                                <%}%>
                                <td><%=temp.getPrice()%></td>
                                <td><%=temp.getAdvance()%></td>
                                <td><%=temp.getRoyalty()%></td>
                                <td><%=temp.getYtd_sales()%></td>
                                <td><%=temp.getNotes()%></td>
                                <%try {%>
                                <td><%=new SimpleDateFormat("dd MMM yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(temp.getPubdate()))%></td>
                                <%} catch (Exception ex) {
                                        ex.printStackTrace();
                                    }%>
                                <td><img width ="100px" height="100px" src = "<%=temp.getImage()%>"></td>
                                <td><a class='btn btn-outline-dark' style="color: green;" href='TitleController?service=updateTitle&titleID=<%=temp.getTitle_id()%>'>Update</a></td>
                                <td><a class='btn btn-outline-dark' href='TitleController?service=deleteTitle&titleID=<%=temp.getTitle_id()%>'>Delete</a></td>
                                <%%>
                                <!--<td><a class='btn btn-outline-dark' href='CartController?service=addToCart&titleID=<%=temp.getTitle_id()%>'>Add</a></td>-->
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                    <%
                            } catch (Exception e) {
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                        }
                    %>
                </div>
            </div>
        </div> 

        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity='sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM' crossorigin='anonymous'></script>
        <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' integrity='sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p' crossorigin='anonymous'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' integrity='sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF' crossorigin='anonymous'></script>

    </body>
</html>
