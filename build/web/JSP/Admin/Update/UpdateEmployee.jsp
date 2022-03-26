
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
        <title>List all Employees</title>
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
                <h1 class="col">Update Employee</h1>
                <div class="col-md-9">
                    <form action='EmployeeController' method = 'POST'>
                        <input class="form-control border-0 bg-transparent" type="hidden" value="updateEmployee" name="service">
                        <table class="myForm">
                            <%
                                ResultSet rsEmployee = (ResultSet) request.getAttribute("rsEmployee"),
                                        rspush = (ResultSet) request.getAttribute("rsPublisher"),
                                        rsjob = (ResultSet) request.getAttribute("rsJob");
                                if (rsEmployee.next()) {
                            %>
                            <tbody>
                                <tr>
                                    <td><label for='empid'>Employee ID</label></td>
                                    <td>
                                        <input class='form-control border-0 bg-transparent' type='text' disabled id ='empid' value='<%=rsEmployee.getString(1)%>'>
                                        <input class='form-control border-0 bg-transparent' type='hidden' name='empid' value='<%=rsEmployee.getString(1)%>'>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='fname'>Last Name</label></td>
                                    <td><input class='form-control border-0 bg-transparent' type='text' id ='fname' name='fname' maxlength='40' value='<%=rsEmployee.getString(2)%>'></td>
                                </tr>
                                <tr>
                                    <td><label for='minit'>Middle Name (first character)</label></td>
                                    <td><input class='form-control border-0 bg-transparent' type='text' id ='minit' name='minit' maxlength='1' value='<%=rsEmployee.getString(3)%>'></td>
                                </tr>
                                <tr>
                                    <td><label for='lname'>First Name</label></td>
                                    <td><input class='form-control border-0 bg-transparent' type='text' id ='lname' name='lname' maxlength='40' value='<%=rsEmployee.getString(4)%>'></td>
                                </tr>
                                <tr>
                                    <td><label for='jobid'>jobid</label></td>
                                    <td>
                                        <select class='form-select border-0 bg-transparent' name='jobid' id='pubid'>
                                            <%while (rsjob.next()) {
                                            if (rsEmployee.getString(5).equals(rsjob.getString(1))) {%>
                                            <option value='<%=rsjob.getString(1)%>' selected=""><%=rsjob.getString(2)%></option>
                                            <%} else {%>
                                            <option value='<%=rsjob.getString(1)%>'><%=rsjob.getString(2)%></option>
                                            <%}%>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='joblvl'>Employee lvl</label></td>
                                    <td><input class='form-control border-0 bg-transparent' type='number' id ='joblvl' name='joblvl' value='<%=rsEmployee.getString(6)%>'></td>
                                </tr>
                                <tr>
                                    <td><label for='pubid'>Publisher ID</label></td>
                                    <td>
                                        <select class='form-select border-0 bg-transparent' name='pubid' id='pubid'>
                                            <% while (rspush.next()) {
                                            if (rsEmployee.getString(7).equals(rspush.getString(1))) {%>
                                            <option value='<%=rspush.getString(1)%>' selected=""><%=rspush.getString(2)%></option>
                                            <%} else {%>
                                            <option value='<%=rspush.getString(1)%>'><%=rspush.getString(2)%></option>
                                            <%}%>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label for='hiredate'>Hire Date</label></td>
                                    <td><input class='form-control border-0 bg-transparent' type='date' id ='hiredate' name='hiredate' value='<%= rsEmployee.getString(8).split(" ")[0]%>'></td>
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