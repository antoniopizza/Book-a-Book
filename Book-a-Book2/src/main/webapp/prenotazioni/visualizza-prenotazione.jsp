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
    String pathServlet = application.getContextPath();
    Prenotazione prenotazione = (Prenotazione) request.getAttribute("prenotazione");
    Libro libro = prenotazione.getCopia().getLibro();
    Persona persona = (Persona) request.getSession().getAttribute("persona");
    Bibliotecario bibliotecario = (Bibliotecario) request.getSession().getAttribute("bibliotecario");
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

                    <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">
                        <div class="sidebar">
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <%if (session.getAttribute("bibliotecario") != null) {
                                        %>
                                    <%@include file="../skeleton-pages/menuBibliotecario.jsp" %>
                                    <% } else if (session.getAttribute("persona") != null) {%>
                                    <%@include file="../skeleton-pages/menuPersona.jsp" %>
                                    <% } else { %>
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
                                    <% }
                                       %>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0 scroll-page">

                        <div class="widget dashboard-container my-adslist">
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <%
                                String message = (String) request.getAttribute("message");

                                if (message != null) {
                            %>
                            <% if(message.equals("correct")) { %>
                            <div class="alert alert-success">
                                <strong>Success!</strong> La tua prenotazione e' stata effettuata con successo.
                            </div>
                            <% } %>
                            
                            <h3 class="widget-header"></h3>
                            <h3>Cerca la tua prenotazione</h3>
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
                                   
                                    if (prenotazione.getStatus().equals("Da ritirare")) {
                                        if (persona!=null) {
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0">  
                                    <div class="col-md">
                                        <button type="button" class="btn btn-transparent"  data-toggle="modal" data-target="#modal-annullare" >
                                            Annulla prenotazione
                                        </button>
                                    </div>
                                </div>
                                <%
                                } else if (bibliotecario!=null) {
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                    <div class="col-md">
                                        <button type="button" class="btn btn-transparent"  data-toggle="modal" data-target="#modal-annullare" >
                                            Annulla prenotazione
                                        </button>
                                    </div> 
                                </div>
                                <div class="col-md-10 offset-md-1 col-lg-6 offset-lg-0">
                                    <div class="col-md">
                                        <button type="button" class="btn btn-transparent"  data-toggle="modal" data-target="#modal-ritiro" >
                                            Ritira
                                        </button>
                                    </div>  
                                </div>
                                <%
                                    }
                                %>
                                <%
                                } else if (prenotazione.getStatus().equals("Ritirato")) {
                                    if (bibliotecario!=null) {

                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0">
                                    <div class="col-md">
                                        <button type="button" class="btn btn-transparent"  data-toggle="modal" data-target="#modal-restituzione" >
                                            Restituzione
                                        </button>
                                    </div> 
                                </div>    
                                <%                                                    }
                                    }
                                %>
                            </div>
                                <% } else if(message.equals("error")){ %>
                              <div class="alert alert-danger">
                                <strong>Errore stronzo!</strong> 
                            </div>
                             <%}%>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="modal-annullare" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <form method="GET" name="annulla-form" id="annulla-form" action="annulla-prenotazione" >
                            <div class="modal-header">
                                <h4 class="modal-title">Annulla prenotazione</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p>Sei sicuro di voler annullare questa prenotazione?</p>
                            </div>
                            <input type="hidden" name="id_prenotazione" value="<%=prenotazione.getId()%>" />  
                            <div class="modal-footer">
                                 <button type="submit" class="btn btn-default">Si</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="modal-ritiro" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <form method="GET" name="ritiro-form" id="ritiro-form" action="prenotazioni/conferma-ritiro" >
                            <div class="modal-header">
                                <h4 class="modal-title">Ritira libro</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p>Sei sicuro di voler confermare il ritiro di questo libro?</p>
                            </div>
                            <input type="hidden" name="id_prenotazione" value="<%=prenotazione.getId()%>" />  
                            <div class="modal-footer">
                                 <button type="submit" class="btn btn-default">Si</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="modal-restituzione" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <form method="GET" name="restituzione-form" id="restituzione-form" action="prenotazioni/conferma-restituzione" >
                            <div class="modal-header">
                                <h4 class="modal-title">Restituzione libro</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>      
                            </div>
                            <div class="modal-body">
                                <p>Sei sicuro di voler confermare la restituzione di questo libro?</p>
                            </div>
                            <input type="hidden" name="id_prenotazione" value="<%=prenotazione.getId()%>" />  
                            <div class="modal-footer">
                                 <button type="submit" class="btn btn-default">Si</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">No</button> 
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>                 

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>

</html>
