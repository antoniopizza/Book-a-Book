<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>
<%@page import="core.entities.Telefono"%>
<%@page import="core.DAO.TelefonoDAO"%>
<% String nomePagina = "profiloPersonale-Utente";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <%            TelefonoDAO telefonoDAO = new TelefonoDAO();
            Telefono telefono = telefonoDAO.doRetriveByPersona(((Persona) request.getSession().getAttribute("persona")));

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
                    </div>
                                
                                <input type="text" value="<%= request.getParameter("pagina")%>" id="pagina" hidden>

                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">

                        <!-- View Personal Info -->
                        <div class="widget personal-info" id ="visualizzaDati">

                            <h3 class="widget-header user" id="h3view">I Tuoi Dati Personali</h3>

                            <!-- Email -->
                            <div class="form-group" >
                                <label for="email">Email</label>
                                <input type="text" class="form-control"  name ="email"
                                       value="<%=((Persona) request.getSession().getAttribute("persona")).getAccount().getEmail()%>" disabled="true">
                            </div>

                            <!-- Documento d'Identità -->
                            <div class="form-group">
                                <label for="documento">Documento di Identita'</label>
                                <input type="text" class="form-control" name="documento" id ="doc" 
                                       value="<%=((Persona) request.getSession().getAttribute("persona")).getNumDocumento()%>" disabled="true">
                            </div>

                            <!-- Indirizzo -->
                            <div class="form-group">
                                <label for="indirizzo"> Indirizzo</label>
                                <div class="row">


                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <label for="cap">Provincia</label>
                                        <input type="text" class="form-control" name="provincia" id="provincia"
                                               value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getProvincia()%>" disabled="true"  pattern="[A-Z]{2,3}$">
                                    </div>  


                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <label for="citta">Citta'</label>
                                        <input type="text" class="form-control" name="citta" id="citta"
                                               value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getCitta()%>" disabled="true" >
                                    </div>
                                </div>
                                <div class="row">

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="via">Via</label>
                                        <input type="text" class="form-control" name="via" id="via"
                                               value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getVia()%>" disabled="true">
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="cap">Numero Civico</label>
                                        <input type="text" class="form-control" name="civico" id="civico"
                                               value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getCivico()%>" disabled="true" >
                                    </div>

                                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                        <label for="cap">CAP</label>
                                        <input type="text" class="form-control" name="cap" id="cap"
                                               value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getCap()%>" disabled="true" >
                                    </div>    
                                </div>

                            </div>


                            <!-- Path Foto -->
                            <div class="form-group">
                                <label for="foto">Path Foto</label>
                                <input type="text" class="form-control" name="foto" id="foto"
                                       value="<%=((Persona) request.getSession().getAttribute("persona")).getAccount().getPathFoto()%>" disabled="true" >
                            </div>

                            <!-- Number -->
                            <div class="form-group">
                                <label for="numero">Numero di Telefono</label>
                                <input type="text" class="form-control" name="numero" id="numero"
                                       value="<%= telefono.getNumero()%>" disabled="true" pattern="[0-9]{9,11}$">
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
                                    <input type="text" class="form-control"  name ="tipoUtente" value="persona">
                                </div>

                                <!-- First Name -->
                                <div class="form-group" id="nome">
                                    <label for="first-name" id="labelName" >Nome</label>
                                    <input type="text" class="form-control"  name ="nome"
                                           value="<%=((Persona) request.getSession().getAttribute("persona")).getNome()%>">
                                </div>
                                <!-- Last Name -->
                                <div class="form-group" >
                                    <label for="last-name" >Cognome</label>
                                    <input type="text" class="form-control"  name ="cognome"
                                           value="<%=((Persona) request.getSession().getAttribute("persona")).getCognome()%>">
                                </div>

                                <!-- Old Email -->
                                <div class="form-group" hidden>
                                    <input type="text" class="form-control"  name ="vecchiaEmail" id ="oldEmail"
                                           value="<%=((Persona) request.getSession().getAttribute("persona")).getAccount().getEmail()%>">
                                </div>

                                <!-- Email -->
                                <div class="form-group" >
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control"  name ="nuovaEmail" id="newEmail"
                                           value="<%=((Persona) request.getSession().getAttribute("persona")).getAccount().getEmail()%>">
                                </div>

                                <!-- Documento d'Identità -->
                                <div class="form-group">
                                    <label for="documento">Documento di Identita'</label>
                                    <input type="text" class="form-control" name="documento" id ="doc" 
                                           value="<%=((Persona) request.getSession().getAttribute("persona")).getNumDocumento()%>" >
                                </div>

                                <!-- Indirizzo -->
                                <div class="form-group">
                                    <label for="indirizzo"> Indirizzo</label>
                                    <div class="row">


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="cap">Provincia</label>
                                            <input type="text" class="form-control" name="provincia" id="provincia"
                                                   value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getProvincia()%>"  pattern="[A-Z]{2,3}$">
                                        </div>  


                                        <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                            <label for="citta">Citta'</label>
                                            <input type="text" class="form-control" name="citta" id="citta"
                                                   value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getCitta()%>" >
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="via">Via</label>
                                            <input type="text" class="form-control" name="via" id="via"
                                                   value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getVia()%>" >
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">Numero Civico</label>
                                            <input type="text" class="form-control" name="civico" id="civico"
                                                   value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getCivico()%>"  >
                                        </div>

                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                            <label for="cap">CAP</label>
                                            <input type="text" class="form-control" name="cap" id="cap"
                                                   value="<%=((Persona) request.getSession().getAttribute("persona")).getIndirizzo().getCap()%>" >
                                        </div>    
                                    </div>

                                </div>


                                <!-- Path Foto -->
                                <div class="form-group">
                                    <label for="foto">Path Foto</label>
                                    <input type="text" class="form-control" name="foto" id="foto"
                                           value="<%=((Persona) request.getSession().getAttribute("persona")).getAccount().getPathFoto()%>"  >
                                </div>

                                <!-- Number -->
                                <div class="form-group">
                                    <label for="numero">Numero di Telefono</label>
                                    <input type="text" class="form-control" name="numero" id="numero"
                                           value="<%= telefono.getNumero()%>"  pattern="[0-9]{9,11}$">
                                </div>

                                <div class="row" id="submit">


                                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0">

                                    </div>


                                    <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                        <!-- Submit button -->
                                        <input type="submit" 
                                               class="btn btn-transparent" 
                                               value="Conferma Modifiche"
                                               style="width: 100%; text-align: center; background-color: #59d659; border-color: #59d659; color: white;"

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

            
            if ((document.getElementById("pagina").value)=="modifica") {
                
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
