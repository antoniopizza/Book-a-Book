<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>
<%@page import="core.entities.Telefono"%>
<%@page import="core.DAO.TelefonoDAO"%>
<% String nomePagina = "profiloPersonale-Biblioteca";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <%            TelefonoDAO telefonoDAO = new TelefonoDAO();
            Telefono telefono = telefonoDAO.doRetriveByBiblioteca((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca()));

            if (request.getSession().getAttribute("modificato") != null) {
        %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="modifica">
            <h5 style="color: white;">Modifiche Effettuate Con Successo</h5></div>
            <%
                    request.getSession().removeAttribute("modificato");
                }
                if (request.getSession().getAttribute("richiestaR") != null) {
            %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="richiesta">
            <h5 style="color: white;">Richiesta di Rimozione Inviata con Successo</h5></div>
            <%
                    request.getSession().removeAttribute("richiestaR");
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
                    </div>
                    <input type="text" value="<%= request.getParameter("pagina")%>" hidden id="pagina">

                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">

                        <%
                            if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Rimuovere")) {

                        %>

                        <div class="widget personal-info" id ="visualizzaDati">

                            <h3 class="widget-header user" id="h3view">Stato della Tua Richiesta di Rimozione</h3>
                            <p class="widget-header user" >
                                Lo stato della tua richiesta di rimozione non è stato ancora Controllato.
                            </p>
                            <p class="widget-header user" style="color: red;">
                                In caso venga accettata non ti verrà più permesso di accedere alla nostra piattaforma con queste credenziali.
                            </p>

                        </div>   
                        <% } else if (request.getParameter("status") != null ) { %>


                        <h3 class="widget-header user" id="h3view">Stato della Tua Richiesta di Registrazione</h3>
                        <p class="widget-header user" >
                            Lo stato della tua richiesta di registrazione non è stato ancora Controllato.
                        </p>
                        <p class="widget-header user" style="color: green;">
                            In caso venga accettata non viusalizzerai più questa opzione.
                        </p>
                        
                        <p class="widget-header user" style="color: red;">
                            In caso non venga accettata non ti verrà più permesso di accedere alla nostra piattaforma con queste credenziali.
                        </p>

                        <%    } else {

                        %>

                        <!-- View Personal Info -->
                        <div class="widget personal-info" id ="visualizzaDati">

                            <h3 class="widget-header user" id="h3view">I Tuoi Dati Personali</h3>

                            <!-- Email -->
                            <div class="form-group" >
                                <label for="email">Email</label>
                                <input type="text" class="form-control"  name ="email"
                                       value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getEmail()%>" disabled="true">
                            </div>

                            <!-- Tipo -->
                            <div class="form-group" >
                                <label for="tipo">Tipo</label>
                                <input type="text" class="form-control"  name ="tipo"
                                       value="<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo()%>" disabled="true">
                            </div>



                            <!-- Path Foto -->
                            <div class="form-group">
                                <label for="foto">Path Foto</label>
                                <input type="text" class="form-control" name="foto" id="foto"
                                       value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getPathFoto()%>" disabled="true" >
                            </div>

                            <% if (((Bibliotecario) request.getSession()
                                        .getAttribute("bibliotecario")).getTipo().equals("Responsabile")) {%>

                            <h3 class="widget-header user" id="h3view">I Dati della Tua Biblioteca</h3>

                            <!-- Isil -->
                            <div class="form-group" >
                                <label for="isil">Isil</label>
                                <input type="text" class="form-control"  name ="isil"
                                       value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil()%>" disabled="true">
                            </div>

                            <!-- Nome Biblioteca -->
                            <div class="form-group" id="nome">
                                <label for="nome-Biblioteca" id="labelName" >Nome Biblioteca</label>
                                <input type="text" class="form-control"  name ="nomeBiblioteca" disabled
                                       value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getNome()%>">
                            </div>

                            <!-- Status-->
                            <div class="form-group">
                                <label for="status">Stato Biblioteca</label>
                                <input type="text" class="form-control" name="status" id ="doc" 
                                       value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getStatus()%>" disabled="true">
                            </div>

                            <!-- Indirizzo -->
                            <div class="form-group">
                                <label for="indirizzo"> Indirizzo</label>
                                <div class="row">


                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <label for="cap">Provincia</label>
                                        <input type="text" class="form-control" name="provincia" id="provincia"
                                               value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getProvincia()%>" disabled="true"  pattern="[A-Z]{2,3}$">
                                    </div>  


                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <label for="citta">Citta'</label>
                                        <input type="text" class="form-control" name="citta" id="citta"
                                               value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getCitta()%>" disabled="true" >
                                    </div>
                                </div>
                                <div class="row">

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="via">Via</label>
                                        <input type="text" class="form-control" name="via" id="via"
                                               value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getVia()%>" disabled="true">
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="cap">Numero Civico</label>
                                        <input type="text" class="form-control" name="civico" id="civico"
                                               value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getCivico()%>" disabled="true" >
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="cap">CAP</label>
                                        <input type="text" class="form-control" name="cap" id="cap"
                                               value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getCap()%>" disabled="true" >
                                    </div>    
                                </div>

                            </div>



                            <!-- Number -->
                            <div class="form-group">
                                <label for="numero">Numero di Telefono</label>
                                <input type="text" class="form-control" name="numero" id="numero"
                                       value="<%= telefono.getNumero()%>" disabled="true" pattern="[0-9]{9,11}$">
                            </div>
                            <% }
                            %>

                            <% if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Attiva")) { %>
                            <div calss="row">
                                <input type ="button" class="btn btn-transparent" onClick="modificaDati()" value="Modifca Dati" style="margin-left: 40%" >
                            </div>
                            <% }%>

                        </div>


                        <!-- View Editable Info -->
                        <div class="widget personal-info" id ="modificaDati" hidden="true">
                            <h3 class="widget-header user" id="h3mod">Modifica Dati Personali</h3>


                            <form action="modifica-datiPersonali" method="post">

                                <!-- Campo nascosto -->
                                <div class="form-group"  hidden>
                                    <input type="text" class="form-control"  name ="tipoUtente" value="bibliotecario">
                                </div>

                                <!-- Campo nascosto -->
                                <div class="form-group"  hidden>
                                    <input type="text" class="form-control"  name ="tipoBibliotecario" value="<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo()%>">
                                </div>



                                <!-- Email -->
                                <div class="form-group" hidden >
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control"  name ="vecchiaEmail"
                                           value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getEmail()%>" >
                                </div>



                                <!-- Email -->
                                <div class="form-group" >
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control"  name ="email"
                                           value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getEmail()%>" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$">
                                </div>

                                <!-- Path Foto -->
                                <div class="form-group">
                                    <label for="foto">Path Foto</label>
                                    <input type="text" class="form-control" name="foto" id="foto"
                                           value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getPathFoto()%>"  >
                                </div>

                                <% if (((Bibliotecario) request.getSession()
                                            .getAttribute("bibliotecario")).getTipo().equals("Responsabile")) {

                                %>

                                <h3 class="widget-header user" id="h3mod">Modifica Dati Della Tua Biblioteca</h3>

                                <!-- Nome Biblioteca -->
                                <div class="form-group" id="nome">
                                    <label for="nome-Biblioteca" id="labelName" >Nome Biblioteca</label>
                                    <input type="text" class="form-control"  name ="nomeBiblioteca"
                                           value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getNome()%>" pattern="[a-zA-Z ]{2,}$">
                                </div>

                                <!-- Isil -->
                                <div class="form-group" hidden >
                                    <label for="isil">Isil</label>
                                    <input type="text" class="form-control"  name ="isil"
                                           value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil()%>">
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="cap">Provincia</label>
                                            <input type="text" class="form-control" name="provincia" id="provincia"
                                                   value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getProvincia()%>" pattern="[A-Z]{2,3}$">
                                        </div>  


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" name="citta" id="citta"
                                                   value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getCitta()%>" pattern="[A-Za-z]{2,}$" >
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="via">Via</label>
                                            <input type="text" class="form-control" name="via" id="via"
                                                   value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getVia()%>" pattern="[A-Za-z]{2,}$" >
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">Numero Civico</label>
                                            <input type="text" class="form-control" name="civico" id="civico"
                                                   value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getCivico()%>" pattern="[0-9]{1,}$" >
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">CAP</label>
                                            <input type="text" class="form-control" name="cap" id="cap"
                                                   value="<%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIndirizzo().getCap()%>" pattern="[0-9]{5}$" >
                                        </div>    
                                    </div>

                                </div>



                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" name="numero" id="numero"
                                           value="<%= telefono.getNumero()%>" pattern="[0-9]{9,11}$">
                                </div>



                                <% }
                                %>


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

                        <% }%>

                    </div>
                </div>
            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>

        <script>

            $(document).ready(setTimeout(function () {
                $("#modifica").hide();
            }, 3000));

            $(document).ready(setTimeout(function () {
                $("#richiesta").hide();
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
