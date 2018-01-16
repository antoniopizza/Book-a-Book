<% String nomePagina = "Login";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>
        <section class="section">
            <div class="container">
                <div class="row">
                    <div class="col-3"></div>
                    <div class="col-6">
                        <div class="widget">
                            <h3 class="widget-header">Accedi</h3>
                            <form action="#">
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control" id="email">
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password">
                                </div>

                                <button class="btn btn-transparent">Accedi</button>

                            </form>
                        </div>
                    </div>
                    <div class="col-3"></div>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
    </body>
</html>
