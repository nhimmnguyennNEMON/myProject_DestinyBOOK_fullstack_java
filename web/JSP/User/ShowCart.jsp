
<%@page import="entity.Stores"%>
<%@page import="model.DAOStores"%>
<%@page import="model.DAOStores"%>
<%@page import="model.DAOTitles"%>
<%@page import="entity.Titles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Cart</title>
        <!--        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' crossorigin='anonymous'>
                <link rel="stylesheet" href="CSS/style.css">-->
        <link href="CSS/style_home.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <header class="bg-dark text-center text-lg-start">
        </header>

        <div class="container">
            <div class="row">
                <div class="col text-end">
                    <%
                        String username = (String) session.getAttribute("username");
                        DAOStores daoS = new DAOStores();
                        Stores s = daoS.checklog(username);
                        if (s != null) {
                    %>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-success" href="UserController?service=home&username=<%=username%>">Home</a>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-secondary" href="CartController?service=showCart&username=<%=username%>"">Show Cart</a>
                    <a style=" margin:  1rem;" class="col btn btn-outline-dark" href="UserController?service=Profile&username=<%=username%>">Welcome: <%= username%></a>
                    <a style=" margin:  1rem;" name="link" class="col btn btn-outline-dark" href="UserController?service=Logout">Logout</a>
                    <%
                    } else {
                    %>
                    <a href='index.jsp' style=" margin:  1rem;" name="link" class="btn btn-outline-success">Home</a>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-secondary" href="CartController?service=showCart">Show Cart</a>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-success" href="Register.jsp">Register</a>
                    <a href="Login.jsp" style=" margin:  1rem;" name="link" class="col btn btn-outline-dark">Login</a>
                    <h2 class="col">My Cart</h2>
                    <%}%>
                </div>
            </div>
            <% if (request.getAttribute("error") != null) {%>
            <div class="alert alert-danger d-flex align-items-center" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    <%=request.getAttribute("error")%>
                </div>
            <%}%>
            
            <% if (request.getAttribute("success") != null) {%>
            <div class="alert alert-success d-flex align-items-center" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    <%=request.getAttribute("success")%>
                </div>
            </div>
            <%}%>
            
            <%
                if (session == null) {%>

                <%} else {
                java.util.Enumeration em = session.getAttributeNames();
                double sum = 0, total = 0;
            %>
            <table  class="table_style">
                <thead class="text-center table-dark" style="color: #000000;">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        while (em.hasMoreElements()) {
                            try {
                                String key = em.nextElement().toString();
                                Titles title = (Titles) session.getAttribute(key);
                                sum = title.getPrice() * title.getQuantity();
                                total += sum;
                    %>
                    <tr class="text-center">
                        <td><%= key%></td>
                        <td class="text-start"><%= title.getTitle()%></td>
                        <td>
                            <form action="CartController" method="POST">
                                <input type="number" name="quantity" onchange="this.parentNode.submit()" value="<%= title.getQuantity()%>" class="bg-transparent form-control">
                                <input hidden="" name="titleID" value="<%=key%>">
                                <input hidden="" name="username" value="<%=username%>">
                                <input hidden="" name="service" value="updateQty">
                            </form>
                        </td>
                        <td><%= title.getPrice()%></td>
                        <td><%= sum%></td>
                        <td><a class="btn btn-outline-danger" onclick="alert('Are you sure you want to delete?')" href='CartController?service=removeCart&titleID=<%=key%>'>Remove</a></td>
                    </tr>
                    <%
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    %>
                </tbody>
                <tfoot class="text-center">
                    <tr>
                        <th class="text-end" colspan="4" >Total</th>
                        <td><%= Math.round(total * 100.0) / 100.0%></td>
                        <td><a class="btn btn-outline-danger" onclick="alert('Are you sure you want to delete?')" href='CartController?service=removeAll'>Remove All</a></td>
                    </tr>
                </tfoot>
            </table> 
            <div class=" text-end">
                <!--<a class="btn btn-outline-danger" href="CartController?service=Checkout&username=<%=username%>">Checkout</a>-->
                <form action="CartController" method="POST">
                    <input type="hidden" name="username" class="border-0 bg-transparent" value="<%=username%>">
                    <button type="submit" name="service" value="ConfirmCart"style=" margin-right: 200px;" name="link" class="btn btn-outline-primary" >Buy Now</button>
                </form>
            </div>
            <%
                }
            %>
        </div>

        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' crossorigin='anonymous'></script>
        <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js' crossorigin='anonymous'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js' crossorigin='anonymous'></script>
    </body>
</html>