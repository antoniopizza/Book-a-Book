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

<% String nomePagina = "Informazioni Prenotazione";
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
                                        <a href="<%=pathServlet%>VisualizzaPrenotazioni.java"> Prenotazioni</a>
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
                            <h3 class="widget-header">Cerca un libro</h3>
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>

                            <h3 class="widget-header"></h3>


                            <h3>Cerca la tua prenotazione</h3>
                            <%
                                String message = (String) request.getAttribute("message");

                                if (!message.equals("correct")) {
                                    out.println("<p>" + message + "</p>");
                                } else {
                                    Prenotazione prenotazione = (Prenotazione) request.getAttribute("prenotazione");
                                    Libro libro = (Libro) request.getAttribute("libro");
                                    Persona persona = (Persona) request.getAttribute("persona");
                            %>
                            <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                                <img src="<%=libro.getPathFoto()%>" alt="">
                            </div>
                            <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                                <p><b>Codice prenotazione: <%=prenotazione.getId()%></b></p>
                                <p>Stato: <%=prenotazione.getStatus()%></p>
                                <br>
                                <p><b>ISBN: <%=libro.getIsbn()%></b></p>
                                <p>Titolo: <%=libro.getTitolo()%></p>
                                <%
                                    for (Autore autori : libro.getAutori()) {
                                        out.println("<p>" + autori.getNome() + "</p>");
                                    }
                                %>
                                <p>Casa Editrice: <%=libro.getEditore()%></p>
                                <br>
                                <p>Nome: <%=persona.getNome()%></p>
                                <p>Cognome: <%=persona.getCognome()%></p>
                                <p>N° Documento: <%=persona.getNumDocumento()%></p>
                                <%
                                    if (prenotazione.getStatus().equals("Ritirato")) {
                                %>
                                <p>Data di scadenza della prenotazione: <%=prenotazione.getDataScadenza()%></p>
                                <%
                                } else if (prenotazione.getStatus().equals("Restituito")) {
                                %>
                                <p>Data di consegna del Libro: <%=prenotazione.getDataConsegna()%></p>
                                <%
                                    }
                                %>

                                <%
                                    Utente user = (Utente) session.getAttribute("Utente");
                                    if (prenotazione.getStatus().equals("Da ritirare")) {
                                        if (user instanceof Persona) {
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0">
                                    <form class="form-group" action="/prenotazioni/annulla-prenotazione">
                                        <button class="btn btn-transparent">Annulla Prenotazione</button>
                                    </form>
                                </div>
                                <%
                                } else if (user instanceof Bibliotecario) {
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                    <form class="form-group" action="/prenotazioni/annulla-prenotazione">
                                        <button class="btn btn-transparent">Annulla Prenotazione</button>
                                    </form>
                                </div>
                                <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                    <form class="form-group" action="/prenotazioni/conferma-ritiro">
                                        <button class="btn btn-transparent">Ritira</button>
                                    </form>
                                </div>
                                <%
                                    }
                                %>
                                <%
                                } else if (prenotazione.getStatus().equals("Ritirato")) {
                                    if (user.equals((Utente) session.getAttribute("bibliotecario"))) {

                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0">
                                    <form class="form-group" action="/prenotazioni/conferma-restituzione">
                                        <button class="btn btn-transparent">Restituisci</button>
                                    </form>
                                </div>    
                                <%                                                    }
                                    }
                                %>
                            </div>
                            <%
                                } //fine else più esterno
%>
                        </div>
                    </div>
                </div>

        </section>                 

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
