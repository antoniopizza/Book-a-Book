<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="core.entities.Utente"%>
<%@page import="java.util.List"%>
<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->


<% String nomePagina = "Ricerca Utenti";
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

                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0">
                        <div class="sidebar">
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <%@include file="../skeleton-pages/menuAdmin.jsp" %>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0">

                        <div class="widget dashboard-container my-adslist">
                            <%@include file="../skeleton-pages/searchbar.jsp" %>

                            <h3 class="widget-header"></h3>
                            <%
                                int i;
                                String message;
                                
                                if(request.getAttribute("message") != null){
                                    message = (String) request.getAttribute("message");
                                } else {
                                    message = "Empty";
                                }
                                
                                Collection<Utente> listaUtenti = null;
                                if(request.getAttribute("listaUtenti") != null){
                                    listaUtenti = (Collection<Utente>) request.getAttribute("listaUtenti");
                                } else {
                                    listaUtenti = new ArrayList<Utente>();
                                }

                            %>
                            <h3>Cerca un Utente</h3>
                            <div class="advance-search">
                                <form id="cerca" name = "cercaUtente" action="ricerca-utenti" method="get">
                                    <div class="row">
                                        <!-- Store Search -->

                                        <div class="col-lg-2 col-md-12">
                                            <select  id="select" name="criterio"class="form-control mb-2 mr-sm-2 mb-sm-0">
                                                <option id="email" value="email">Per E-mail</option>
                                                <option id="tipo" value="tipo">Per tipo</option>
                                                <option id="cognome" value="cognome">Per cognome</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-7 col-md-12">
                                            <div  class="block d-flex">
                                                <input  type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="cerca" name="valore" placeholder="Cerca Utente">

                                            </div>
                                        </div>
                                        <div class="col-lg-2 col-md-12">
                                            <div class="block d-flex">
                                                <!-- Search Button -->
                                                <button type="submit" class="btn btn-main" >CERCA</button>
                                            </div>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                </form>
                                <div class="alert alert-danger" style="display: none" id="search-error"></div>
                            </div>

                            <% if (message.equalsIgnoreCase("Empty") || listaUtenti == null) { %>
                            <div class="alert alert-danger">
                                <p  id="erroreSearchPren">Nessun risultato corrispondente al criterio scelto.</p>
                            </div>
                            <% } else { %>
                            </br>

                            <div class = "row"> 
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0" align="center">
                                    <div class = "row"> 
                                        <div class="col-md-10 offset-md-1 col-lg-1 offset-lg-0" align="center">
                                            ID </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            E-mail </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            Tipo</div>
                                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0" align="center">
                                            Cognome</div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center"></div>
                                    </div>
                                </div>
                                <br><br>
                                <%
                                    if (listaUtenti != null && !listaUtenti.isEmpty()) {
                                        for (Utente u : listaUtenti) {
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0" align="center">
                                    <div class = "row"> 
                                        <div class="col-md-10 offset-md-1 col-lg-1 offset-lg-0" align="center"> <%= u.getId()%> </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center"> <%= u.getAccount().getEmail() %> </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center"> <%= u.getAccount().getTipo() %> </div>
                                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0" align="center"> <%= u.getCognome() %></div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            <div class="form-group">
                                                <a class="btn btn-main" href="#">ELIMINA</a> 
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                                <%      } %>
                                <%    } %>
                                <%  }%>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script>
            $(document).ready(function () {

                $("#select").change(function () {
                    var str = "";
                    $("#select option:selected").each(function () {
                        str += $(this).text();
                    });
                });
                $("#selected").change(function () {
                    $("#cerca").val("");
                });

            });

            function controlloSearch() {
                var bool = true;
                var search = document.getElementById("cerca");
                var str = "";
                $("#select option:selected").each(function () {
                    str += $(this).text();
                });
                var regexLettere = /^[a-zA-Z]+$/;
                var reEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,3}(?:\.[a-z]{2,3})?)$/i;
                document.getElementById("erroreSearch").innerHTML = "";
                if (search.value == "") {
                    bool = false;
                    $("#cerca").focus();
                    $("#erroreSearch").text("Il campo non può essere vuoto.");
                } else if (str == "Per Cognome" && search.value != "") {
                    if (!search.value.match(regexLettere)) {
                        $("#cerca").focus();
                        $("#erroreSearch").text("Il campo può contenere solo lettere.");
                        bool = false;
                    }
                } else if (str == "Per Tipo" && search.value != "") {
                    if (!search.value.match(regexLettere)) {
                        $("#cerca").focus();
                        $("#erroreSearch").text("Il campo può contenere solo lettere.");
                        bool = false;
                    }
                } else if (str == "Per Email" && search.value != "") {
                    if (!search.cerca.match(reEmail)) {
                        $("#cerca").focus();
                        $("#erroreSearch").text("Il formato dell'email non è corretto.");
                        bool = false;
                    }
                }
                if (bool == true) {
                    $("cercaUtente").submit();
                }
            }

        </script>

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
