<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>

<%@page import="core.entities.Account"%>
<%@page import="java.util.List"%>
<%@page import="core.managers.ManagerRegistrazione"%>
<% String nomePagina = "profiloPersonale-Admin";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <%  //admin fittizio 
            /* 
            Admin admin =  new Admin("Maurizio", "Sarri", new Account("maurizio.sarri@admin.it", "abc", "dasf", "admin"));
            admin.setId(1);
            request.getSession().setAttribute("admin",admin);*/
            if (request.getSession().getAttribute("modificato") != null) {
        %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="modifica">
            <h5 style="color: white;">Modifiche Effettuate Con Successo</h5></div>
            <%
                    request.getSession().removeAttribute("modificato");
                }
            %>

        <%   if (request.getSession().getAttribute("errore") != null) {
        %>
        <div class="container" style="background-color: #ff6347; text-align: center;" id="modifica">
            <h5 style="color: white;"> Errore Email già presente!</h5></div>
            <%
                    request.getSession().removeAttribute("errore");
                }
            %>




        <!--==================================
 =            User Profile            =
 ===================================-->

        <section class="user-profile section">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
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
                    </div>

                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">

                        <input type="text" value="<%= request.getParameter("pagina")%>" hidden id="pagina">

                        <!-- View Personal Info -->
                        <div class="widget personal-info" id ="visualizzaDati">

                            <h3 class="widget-header user" id="h3view">I Tuoi Dati Personali</h3>

                            <!-- Email -->
                            <div class="form-group" >
                                <label for="email">Email</label>
                                <input type="text" class="form-control"  name ="email"
                                       value="<%=((Admin) request.getSession().getAttribute("admin")).getAccount().getEmail()%>" disabled="true">
                            </div>



                            <div calss="row">
                                <input type ="button" class="btn btn-transparent" onClick="modificaDati()" value="Modifca Dati" style="margin-left: 40%" >
                            </div>
                        </div>

                        <!-- View Editable Info -->
                        <div class="widget personal-info" id ="modificaDati" hidden="true">
                            <h3 class="widget-header user" id="h3mod">Modifica Dati Personali</h3>


                            <form action="modifica-datiPersonali" method="post">

                                <!-- Campo nascosto -->
                                <div class="form-group"  hidden>
                                    <input type="text" class="form-control"  name ="tipoUtente" value="admin">
                                </div>



                                <!-- Old Email -->
                                <div class="form-group" hidden>
                                    <input type="text" class="form-control"  name ="vecchiaEmail" id ="oldEmail"
                                           value="<%=((Admin) request.getSession().getAttribute("admin")).getAccount().getEmail()%>">
                                </div>

                                <!-- Email -->
                                <div class="form-group" >
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control"  name ="nuovaEmail" id="newEmail" required
                                           value="<%=((Admin) request.getSession().getAttribute("admin")).getAccount().getEmail()%>" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" >
                                </div>



                                <div class="row" id="submit">


                                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0">

                                    </div>


                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <!-- Submit button -->
                                        <input type="submit" 
                                               class="btn btn-transparent" 
                                               value="Conferma Modifiche"
                                               style="width: 100%; text-align: center;  background-color: #59d659; border-color: #59d659; color: white;"

                                               >
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <input type ="button" class="btn btn-transparent" onClick="annulla()" value="Annulla" 
                                               style="background-color: #ff6347; border-color: #ff6347; color: white; width: 100%; text-align: center;">
                                    </div>


                                </div>

                            </form>

                        </div>

                    </div>
                </div>
            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>

        <script>

            $(document).ready(setTimeout(function () {
                $("#modifica").hide();
            }, 3000));


            if ((document.getElementById("pagina").value) == "modifica") {

                document.getElementById("visualizzaDati").setAttribute("hidden", "true");
                document.getElementById("modificaDati").removeAttribute("hidden");
            }



            function modificaDati() {

                document.getElementById("visualizzaDati").setAttribute("hidden", "true");
                document.getElementById("modificaDati").removeAttribute("hidden");

            }

            function annulla() {
                document.getElementById("modificaDati").setAttribute("hidden", "true");
                document.getElementById("visualizzaDati").removeAttribute("hidden");
            }


        </script>
    </body>
</html>
