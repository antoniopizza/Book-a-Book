<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>
<% String nomePagina = "registrazione-utente";
    String message = (String) request.getAttribute("message");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <section class="dashboard section">
            <div class="container">

                <div class="row">   

                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0"> </div>
                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                        <div class="widget personal-info">

                            <h3 class="widget-header user">Login</h3>

                            <form action="login" method="post">

                                <!-- email -->
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control" name="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="Inserire email qui...">
                                </div>

                                <!-- password -->
                                <div class="form-group">
                                    <label for="password">Password</label> <!--aggiungere a password: pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" -->
                                            <input type="password" class="form-control" name="password" required 
                                                   id="password" placeholder="inserire password qui...">
                                </div>

                                <%
                                if(message!=null) {
                                %>
                                    <div class="alert alert-danger">
                                        <strong>Errore!</strong> <%= message %>
                                    </div>
                                <%
                                }
                                %>
                                <br/>
                                <!-- Submit button -->
                                <input type="submit" 
                                       class="btn btn-transparent" 
                                       value="Conferma"
                                       style="margin-left: 25%; margin-right: 0%; width:50%;"
                                       >
                                
                                


                            </form>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0"> </div>

                </div>

            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
