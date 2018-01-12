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
            request.getSession().setAttribute("admin", new Admin("Maurizio", "Sarri", new Account("maurizio.sarri@admin.it", "dadfa", "dasf", "admin")));
        
            // lista di richieste per saperne il numero nella sezione visualizza richieste
           List<Biblioteca> richieste = ((new ManagerRegistrazione()).visualizzaRichieste());
            
            if (request.getSession().getAttribute("modificato") != null) {
        %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="modifica">
            <h5 style="color: white;">Modifiche Effettuate Con Successo</h5></div>
            <%
                    request.getSession().removeAttribute("modificato");
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
                                    <li>
                                        <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads</a></li>
                                    <li>
                                        <a href="../profilo/visualizza-richieste.jsp"> Visualizza Richieste <span><%= richieste.size() %></span></a>
                                    </li>
                                    <li>
                                        <a href="dashboard-archived-ads.html"><i class="fa fa-file-archive-o"></i>Archeved Ads <span>12</span></a>
                                    </li>
                                    <li>
                                        <a href="dashboard-pending-ads.html"><i class="fa fa-bolt"></i> Pending Approval<span>23</span></a>
                                    </li>

                                    <li>
                                        <a href="delete-account.html"><i class="fa fa-power-off"></i>Delete Account</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">

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

                                <!-- First Name -->
                                <div class="form-group" id="nome">
                                    <label for="first-name" id="labelName" >Nome</label>
                                    <input type="text" class="form-control"  name ="nome"
                                           value="<%=((Admin) request.getSession().getAttribute("admin")).getNome()%>">
                                </div>
                                <!-- Last Name -->
                                <div class="form-group" >
                                    <label for="last-name" >Cognome</label>
                                    <input type="text" class="form-control"  name ="cognome"
                                           value="<%=((Admin) request.getSession().getAttribute("admin")).getCognome()%>">
                                </div>

                                <!-- Old Email -->
                                <div class="form-group" hidden>
                                    <input type="text" class="form-control"  name ="vecchiaEmail" id ="oldEmail"
                                           value="<%=((Admin) request.getSession().getAttribute("admin")).getAccount().getEmail()%>">
                                </div>

                                <!-- Email -->
                                <div class="form-group" >
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control"  name ="nuovaEmail" id="newEmail"
                                           value="<%=((Admin) request.getSession().getAttribute("admin")).getAccount().getEmail()%>">
                                </div>

            

                                <div class="row" id="submit">


                                    <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">

                                    </div>


                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <!-- Submit button -->
                                        <input type="submit" 
                                               class="btn btn-transparent" 
                                               value="Conferma Modifiche"
                                               style="margin-left: 25%; margin-right: 0%;  background-color: #59d659; border-color: #59d659; color: white;"

                                               >
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">
                                        <input type ="button" class="btn btn-transparent" onClick="annulla()" value="Annulla" 
                                               style="background-color: #ff6347; border-color: #ff6347; color: white;">
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
