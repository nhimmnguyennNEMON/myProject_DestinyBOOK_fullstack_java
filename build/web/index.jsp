<%-- 
    Document   : index
    Created on : Mar 1, 2022, 5:52:34 PM
    Author     : Tran Trang
--%>

<%@page import="entity.Stores"%>
<%@page import="model.DAOStores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Destiny BOOK</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/style_home.css" rel="stylesheet">
    </head>
    <body>

        <header class="bg-dark text-center text-lg-start">
        </header>

        <div class="container">
            <div class="row">
                <div class="text-end col">
                    <%
                        
                        String username = request.getParameter("username");
                        DAOStores daoS = new DAOStores();
                        Stores s = daoS.checklog(username);
                        if (s != null) {
                    %>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-secondary" href="CartController?service=showCart&username=<%=username%>">Show Cart</a>
                    <a style=" margin:  1rem;" class="col btn btn-outline-dark" href="UserController?service=Profile&username=<%=username%>">Welcome: <%= username%></a>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-dark" href="UserController?service=Logout">Logout</a>
                    <%} else {
                        try {
                    %>
                    <a style=" margin:  1rem;" name="link" class="btn btn-outline-secondary" href="CartController?service=showCart">Show Cart</a>
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
        </div>

        <div><jsp:include page="HomePage.jsp"></jsp:include></div>

        <footer class="bg-dark fixed-bottom text-center text-lg-start">
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" ></script>

    </body>
</html>