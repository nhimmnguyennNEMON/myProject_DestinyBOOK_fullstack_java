
<%@page import="entity.Stores"%>
<%@page import="model.DAOStores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="CSS/login.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <% if (request.getAttribute("error") != null) {%>
            <div class="alert alert-danger d-flex align-items-center" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    <%=request.getAttribute("error")%>
                </div>
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
        </div>
        <div class="row">
            <div class="text-end col">
                <a href='index.jsp' style=" margin:  1rem;" name="link" class="btn btn-outline-success">Home</a>
                <%
                    String username = request.getParameter("username");
                    DAOStores daoS = new DAOStores();
                    Stores s = daoS.checklog(username);
                    if (s != null) {
                %>
                <a style=" margin:  1rem;" class="col btn btn-outline-dark">Welcome: <%= username%></a>
                <a style=" margin:  1rem;" name="link" class="btn btn-outline-dark" href="UserController?service=Logout">Logout</a>
                <%} else {
                    try {
                %>
                <a style=" margin:  1rem; margin-right: 120px" name="link" class="btn btn-outline-success" href="Register.jsp">Register</a>
                <%
                        } catch (Exception e) {
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                    }
                %>
            </div>
        </div>

        <section class="vh-100">
            <div class="container-fluid h-custom">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                        <form action="UserController" method="POST">
                            <h3 style="color: #000000">Login</h3>
                            <input type="hidden" name="service" value="login" >
                            <input type="text" placeholder="Username" id="username" name="username" required="">
                            <input type="password" placeholder="Password" id="password" name="password" required="" style="margin-top: 10px;">
                            <button type="submit" class="btn btn-dark btn-lg" style="padding-left: 2.5rem; padding-right: 2.5rem; margin-top: 30px;">Login</button>
                    </div>
                    </form>
                </div>
            </div>
        </section>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
