<%-- 
    Document   : modifica-password
    Created on : 14-gen-2018, 0.10.44
    Author     : salva
--%>

<%@page import="core.entities.Account"%>
<%@page import="java.util.List"%>
<%@page import="core.managers.ManagerRegistrazione"%>
<% String nomePagina = "modifica-password";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>


        <!--==================================
        =            User Profile            =
        ===================================-->

        <section class="user-profile section">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">

                        <%                            Account account = null;
                            //  if (request.getParameter("tipo").equals("admin")) {
                            if (((Admin) request.getSession().getAttribute("admin")) != null) {
                                account = ((Admin) request.getSession().getAttribute("admin")).getAccount();

                        %>

                        <div class="sidebar">
                            <!-- User Widget -->
                            <div class="widget user-dashboard-profile">
                                <!-- User Image -->
                                <div class="profile-thumb">
                                    <img src="<%=((Admin) request.getSession().getAttribute("admin")).getAccount().getPathFoto()%>" alt="" class="rounded-circle">
                                </div>
                                <!-- User Name -->
                                <h5 class="text-center"><%= /* NOME*/((Admin) request.getSession().getAttribute("admin")).getNome()%>  <%= /*COGNOME */((Admin) request.getSession().getAttribute("admin")).getCognome()%></h5>

                            </div>
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <%@include file="../skeleton-pages/menuAdmin.jsp" %>
                                </ul>
                            </div>
                        </div>

                        <% } else if (((Persona) request.getSession().getAttribute("persona")) != null) {

                            account = ((Persona) request.getSession().getAttribute("persona")).getAccount();

                        %>

                        <div class="sidebar">
                            <!-- User Widget -->
                            <div class="widget user-dashboard-profile">
                                <!-- User Image -->
                                <div class="profile-thumb">
                                    <img src="<%=((Persona) request.getSession().getAttribute("persona")).getAccount().getPathFoto()%>" alt="" class="rounded-circle">
                                </div>
                                <!-- User Name -->
                                <h5 class="text-center"><%= /* NOME*/((Persona) request.getSession().getAttribute("persona")).getNome()%>  <%= /*COGNOME */((Persona) request.getSession().getAttribute("persona")).getCognome()%></h5>

                            </div>
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <%@include file="../skeleton-pages/menuPersona.jsp" %>
                                </ul>
                            </div>
                        </div>

                        <% } else if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")) != null) {

                            account = ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount();

                        %>

                        <div class="sidebar">
                            <!-- User Widget -->
                            <div class="widget user-dashboard-profile">
                                <!-- User Image -->
                                <div class="profile-thumb">
                                    <img src="<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getPathFoto()%>" alt="" class="rounded-circle">
                                </div>
                                <!-- User Name -->
                                <h5 class="text-center"><%= /* NOME*/((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getNome()%> <%=/* Cognome*/((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getCognome()%>  </h5>

                            </div>
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <%@include file="../skeleton-pages/menuBibliotecario.jsp" %>
                                </ul>
                            </div>
                        </div>

                        <% }
                        %>

                    </div>

                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                        <!-- Recently Favorited -->
                        <div class="widget dashboard-container my-adslist">
                            <h3 class="widget-header">Modifica Password</h3>

                            <!-- Campi modifica -->

                            <form action="modifica-password?tipoUtente=<%=  account.getTipo() %>" method="post">


                                <!-- Old Email -->
                                <div class="form-group" hidden>
                                    <input type="text" class="form-control"  name ="vecchiaEmail" id ="oldEmail"
                                           value="<%=account.getEmail()%>">
                                </div>

                                <!-- New Email -->
                                <div class="form-group" >
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control"  name ="email" id="newEmail" required>
                                </div>

                                <!-- Conferm Pass -->
                                <div class="form-group" hidden>
                                    <input type="text" class="form-control"  name ="confermapass" id ="confermapass" required
                                           value="<%= account.getPassword()%>">
                                </div>

                                <div class="form-group">
                                    <div class="row">

                                        <!-- Old Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password">Vecchia Password</label>
                                            <input type="password" class="form-control" name="oldPass" required id="oldPass">
                                        </div>

                                        <!-- Password -->
                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="password"> Nuova Password</label>
                                            <input type="password" class="form-control" name="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                                   title="Deve avere una lunghezza di almeno 8 caratteri che devono essere almeno un numero, una letterea maiuscola ed una lettera minuscola."
                                                   id="newPass">
                                        </div>


                                    </div>
                                </div>

                                <div class="row" id="submit">

                                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0">
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <!-- Submit button -->
                                        <input type="submit" 
                                               class="btn btn-transparent" 
                                               value="Conferma Modifiche"
                                               style=" background-color: #59d659; border-color: #59d659; color: white; width: 100%; text-align: center;"

                                               >
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        
                                        <a href="../skeleton-pages/index.jsp" class="btn btn-transparent"  
                                           style="background-color: #ff6347; border-color: #ff6347; color: white; width: 100%; text-align: center;"> Annulla </a>
                                    </div>


                                </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>

        <!-- Script --> 
         <script>
            var oldPassword = document.getElementById("oldPass");
            var confirm_password = document.getElementById("confermapass");
            var newPassword = document.getElementById("newPass");
            

            function validatePassword1() {
                
                
                if (oldPassword.value != confirm_password.value) {
                    oldPassword.setCustomValidity("La password deve essere uguale alla precedente!");
                } else {
                    oldPassword.setCustomValidity('');
                }
            }
            
          function validatePassword2() {
                if (oldPassword.value == newPassword.value) {
                    newPassword.setCustomValidity("La password deve essere diversa dall'ultima posseduta!");
                } else {
                    newPassword.setCustomValidity('');
                }
            }

            oldPassword.onchange = validatePassword1;
            newPassword.onkeyup = validatePassword2;
            
            
            var email = document.getElementById("newEmail");
            var oldEmail = document.getElementById("oldEmail");

            function validateEmail() {
                if (email.value != oldEmail.value) {
                    email.setCustomValidity("Inserire email corretta.");
                } else {
                    email.setCustomValidity('');
                }
            }

            email.onchange = validateEmail;
            
            

        </script>

    </body>
</html>
