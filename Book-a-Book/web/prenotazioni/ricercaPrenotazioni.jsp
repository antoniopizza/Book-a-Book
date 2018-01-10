<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="core.entities.Bibliotecario"%>
<%@page import="core.entities.Utente"%>
<%@page import="core.entities.Autore"%>
<%@page import="core.entities.Persona"%>
<%@page import="core.entities.Libro"%>
<%@page import="core.entities.Prenotazione"%>
<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->

<% String nomePagina = "Ricerca Prenotazione";
    String pathPrenotazione = application.getContextPath() + "/prenotazioni/";
    String pathServlet = application.getContextPath() + "/core.controllers/";
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
                                        <a href="<%=pathPrenotazione%>ricercaPrenotazioni.jsp"> Prenotazioni</a>
                                    </li>
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
                            <h3>Cerca un libro</h3>
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <p id="erroreSearchLibro"></p>
                            <h3 class="widget-header"></h3>


                            <h3>Cerca la tua prenotazione</h3>
                            <div class="advance-search">
                                <form action="<%=pathServlet%>PrenotazioniPerCriterioServlet.java">
                                    <div class="row">
                                        <!-- Store Search -->
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-2 col-md-12">
                                            <select class="form-control mb-2 mr-sm-2 mb-sm-0">
                                                <option id="idUtente">Per id utente</option>
                                                <option id="idCodice">Per codice</option>
                                                <option id="dataConferma">Per data di conferma</option>
                                                <option id="dataCreazione">Per data di creazione</option>
                                                <option id="dataScadenza">Per data di scadenza</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-7 col-md-12">
                                            <div class="block d-flex">
                                                <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="searchPren" placeholder="Cerca la tua prenotazione">

                                            </div>
                                        </div>
                                        <div class="col-lg-2 col-md-12">
                                            <div class="block d-flex">
                                                <!-- Search Button -->
                                                <button class="btn btn-main" onclick="controlloSearch()">CERCA</button>
                                            </div>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <p id="erroreSearchPren"></p>
                                </br>
                            </div>
                            <div class = "row"> 
                                <%--
                                    int i;
                                    String message = (String) request.getAttribute("message");
                                    List<Prenotazione> lista = null;
                                    if (!message.equals("correct")) {
                                        out.println("<p>" + message + "</p>");
                                    } else {
                                        lista = new ArrayList<>();
                                        lista = (ArrayList<Prenotazione>) request.getAttribute("lista");
                                    }
                                --%>
                                <div class="col-md-10 offset-md-1 col-lg-1 offset-lg-0" align="center">
                                    Codice
                                    <% for (int i = 0; i < 10; i++) {%>

                                    <div class="row" onclick="scrivi()" class="widget user-dashboard-menu">
                                        <div class="col-md-10 offset-md-1 col-lg-1 offset-lg-0" align="center"><a href="<%=pathPrenotazione%>ricercaPrenotazioni.jsp"> <%= "a"%></a> 

                                            <%    }%>
                                            <% for (int i = 0; i < 10 * 2; i++) {
                                                    out.println("</div>");
                                                }%>
                                        </div>
                                        <%--
                                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0" align="center">
                                            ISBN
                                            <% for (i = 0; i < lista.size(); i++) {%>

                                            <div class="row">
                                                <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0" align="center"> <%= lista.get(i).getCopia().getLibro().getIsbn()%> 

                                                    <%    }%>
                                                    <% for (i = 0; i < lista.size() * 2; i++) {
                                                            out.println("</div>");
                                                        }%>
                                                </div>
                                                <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                                    Titolo
                                                    <% for (i = 0; i < lista.size(); i++) {%>

                                                    <div class="row">
                                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center"> <%= lista.get(i).getCopia().getLibro().getTitolo()%> 

                                                            <%    }%>
                                                            <% for (i = 0; i < lista.size() * 2; i++) {
                                                                    out.println("</div>");
                                                                }%>
                                                        </div>
                                                        <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0" align="center">
                                                            Biblioteca
                                                            <% for (i = 0; i < lista.size(); i++) {%>

                                                            <div class="row">
                                                                <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0" align="center"> <%= lista.get(i).getBiblioteca().getNome()%> 

                                                                    <%    }%>
                                                                    <% for (i = 0; i < lista.size() * 2; i++) {
                                                                            out.println("</div>");
                                                                        }%>
                                                                </div>
                                                                <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0 scroll-page" align="center">
                                                                    Scadenza
                                                                    <% for (i = 0; i < lista.size(); i++) {%>

                                                                    <div class="row">
                                                                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0" align="center"> <%= lista.get(i).getDataScadenza()%> 

                                                                            <%    }%>
                                                                            <% for (i = 0; i < lista.size() * 2; i++) {
                                                                                    out.println("</div>");
                                                                                }%>
                                        --%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>                 
        <script>
            function scrivi() {
                console.log("scrivo");
            }

            function controlloSearch() {
                var boolean = true;
                var idUtente = document.getElementById("idUtente");
                var idCodice = document.getElementById("idCodice");
                var dataConferma = document.getElementById("dataConferma");
                var dataCreazione = document.getElementById("dataCreazione");
                var dataScadenza = document.getElementById("dataScadenza");
                var search = document.getElementById("search");
                var regexLettere = /^[A-Za-z ]{1,30}$/;
                var regexNumeri = /^[0-9 ]{13}$/;
                document.getElementById("erroreSearchPren").innerHTML = "";
                if (search.value == "") {
                    $("#searchPren").focus();
                    $("#erroreSearchPren").text("Il campo non può essere vuoto.");
                    boolean = false;
                } else if (autore.selected && search.value != "") {
                    if (!search.value.match(regexLettere)) {
                        $("#searchPren").focus();
                        $("#erroreSearchPren").text("Il campo deve contenere solo lettere.");
                        boolean = false;
                    }
                } else if (editore.selected && !search.value != "") {
                    if (!search.value.match(regexLettere)) {
                        $("#searchPren").focus();
                        $("#erroreSearchPren").text("Il campo può contenere solo lettere.");
                        boolean = false;
                    }
                } else if (isbn.selected && search.value != "") {
                    if (!search.value.match(regexNumeri)) {
                        $("#searchPren").focus();
                        $("#erroreSearchPren").text("Il dato inserito non corrisponde al formato desiderato.");
                        boolean = false;
                    }
                }
                if (boolean == true) {
                    document.cercaPren.submit();
                }
            }

        </script>

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
