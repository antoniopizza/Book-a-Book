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
                                    <li>
                                        <a href="dashboard-my-ads.html"> Biblioteche</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"> Novità</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"> Autori</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"> Popolari</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-10 offset-lg-0 scroll-page">

                        <div class="widget dashboard-container my-adslist">
                            <h3 class="widget-header">Cerca un libro</h3>
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header"></h3>

                            <!-- Da qui in poi puoi inserire codice per creare la pagina da questo scheletro -->                    

                            <!--=================================
                            =      Contenuto della pagine      =
                            ==================================-->      
                            <h3 class="widget-header">Cerca un Utente</h3>
                            <div class="advance-search">
                                <form id="cercaUtente" name = "cercaDipendente" action="CercaDipendenteServlet" method="post">
                                    <div class="row">
                                        <!-- Store Search -->

                                        <div class="col-lg-2 col-md-12">
                                            <select  id="select" name="criterio "class="form-control mb-2 mr-sm-2 mb-sm-0">
                                                <option id="Cognome" value="cognome">Per Cognome</option>
                                                <option id="Email" value="email">Per Email</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-7 col-md-12">
                                            <div  class="block d-flex">
                                                <input  type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="searchDipendente" name="valore" placeholder="Cerca il dipendente desiderato">
                                            </div>
                                        </div>
                                        <div class="col-lg-2 col-md-12">
                                            <div class="block d-flex">
                                                <!-- Search Button -->
                                                <button type="button" class="btn btn-main" onclick="controlloSearch()">CERCA</button>
                                            </div>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <p id="erroreSearch"></p>
                                </br>
                            </div>
                            <div class = "row"> 
                                <%
                                    String message = (String) request.getAttribute("message");
                                    List<Utente> listaDipendenti = null;
                                    if (!message.equals("correct")) {
                                        out.println("<p>" + message + "</p>");
                                    } else {
                                        listaDipendenti = new ArrayList<>();
                                        listaDipendenti = (ArrayList<Utente>) request.getAttribute("listaDipendenti");
                                    }
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0" align="center">
                                    <div class = "row"> 
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            Cognome
                                        </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            Nome
                                        </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            Email
                                        </div>
                                    </div>
                                </div>
                                <% 
                                    for (int i = 0; i < listaDipendenti.size(); i++) {
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0" align="center">
                                    <div class = "row"> 
                                        <div class="col-md-10 offset-md-1 col-lg-1 offset-lg-0" align="center"> <%= listaDipendenti.get(i).getCognome()%> </div>
                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0" align="center"> <%= listaDipendenti.get(i).getNome()%></div>
                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0" align="center"> <%= listaDipendenti.get(i).getAccount().getEmail()%></div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            <div class="form-group">
                                                <a class="btn btn-main" href="EliminaDipendenteServlet">ELIMINA</a> 
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                                <%    }%>
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
                $("#select").change(function () {
                    $("#searchDipendente").val("");
                });
            });
            
                function controlloSearch() {
                    var bool = true;
                    var search = document.getElementById("searchDipendente");
                    var str = "";
                    $("#select option:selected").each(function () {
                        str += $(this).text();
                    });
                    var regexLettere = /^[a-zA-Z]+$/;
                    var reEmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,3}(?:\.[a-z]{2,3})?)$/i;
                    document.getElementById("erroreSearch").innerHTML = "";
                    if (search.value == "") {
                        bool = false;
                        $("#erroreSearch").text("Il campo non può essere vuoto.");
                    } else if (str == "Per Cognome" && search.value != "") {
                        if (!search.value.match(regexLettere)) {
                            $("#searchDipendente").focus();
                            $("#erroreSearch").text("Il campo può contenere solo lettere.");
                            bool = false;
                        }
                    } else if (str == "Per Email" && search.value != "") {
                        if (!search.value.match(reEmail)) {
                            $("#searchDipendente").focus();
                            $("#erroreSearch").text("Il formato dell'email non è corretto.");
                            bool = false;
                        }
                    }
                    if (bool == true) {
                        $("cercaDipendente").submit();
                    }
                }
            
        </script>

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
