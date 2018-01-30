<%@page import="java.util.Calendar"%>
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
    Persona persona;

    if (session.getAttribute("persona") == null) {
        persona = (Persona) request.getAttribute("persona");
    } else {
        persona = prenotazione.getPersona();
    }

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
                                     <%@include file="../skeleton-pages/menu-non-loggato.jsp" %>
                                    <% }
                                    %>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0">

                        <div class="widget dashboard-container my-adslist">
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <%
                                String message = (String) request.getAttribute("message");
                                if (message != null) {
                            %>
                            <% if (message.equals("correct")) { %>
                            <div class="alert alert-success">
                                <strong>Success!</strong> La tua prenotazione e' stata effettuata con successo.
                            </div>
                            <% }%>

                            <h3>Cerca la tua prenotazione</h3>
                            <div class="advance-search">
                                <form id="cercaPren" name = "cercaPren" action="cerca-per-criterio" method="post">
                                    <div class="row">
                                        <!-- Store Search -->

                                        <div class="col-lg-2 col-md-12">
                                            <select  id="select" name="criterio "class="form-control mb-2 mr-sm-2 mb-sm-0">
                                                <% if (session.getAttribute("bibliotecario") != null) { %>
                                                <option id="idUtente" value="utente">Per id utente</option>
                                                <% }%>
                                                <option id="idCodice" value="codice">Per codice</option>
                                                <option id="dataRitiro" value="ritiro">Per data di ritiro</option>
                                                <option id="dataCreazione" value="creazione">Per data di creazione</option>
                                                <option id="dataScadenza" value="scadenza">Per data di scadenza</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-7 col-md-12">
                                            <div  class="block d-flex">
                                                <input style="display:none" type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="searchData" name="valore" placeholder="Cerca la tua prenotazione">
                                            </div>
                                            <div  class="block d-flex">
                                                <input  type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="searchPren" name="valore" placeholder="Cerca la tua prenotazione">
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

                            <h3 class="widget-header">Dettagli della prenotazione</h3>
                            <div class="row">
                                <div class="col-md-10 offset-md-1 col-lg-5 offset-lg-0">
                                    <img src="<%=libro.getPathFoto()%>" alt="" style="max-height: 350px;">
                                </div>
                                <div class="col-md-10 offset-md-1 col-lg-7 offset-lg-0">
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
                                    <% if(session.getAttribute("bibliotecario") == null){ %>
                                    <p><strong>Biblioteca in cui ritirare il libro</strong></p>
                                    <p>Nome: <%= prenotazione.getBiblioteca().getNome() %></p>
                                    <p>Indirizzo: <%= prenotazione.getBiblioteca().getIndirizzo().getVia() +" "+prenotazione.getBiblioteca().getIndirizzo().getCivico() + ", "+ prenotazione.getBiblioteca().getIndirizzo().getCitta() + "(" + prenotazione.getBiblioteca().getIndirizzo().getProvincia()+")" %> </p>
                                    <% } %>
                                    <p><strong>Persona che ha preso in prestito il libro</strong></p>
                                    <p>Nome: <%=persona.getNome()%></p>
                                    <p>Cognome: <%=persona.getCognome()%></p>
                                    <p>N° Documento: <%=persona.getNumDocumento()%></p>
                                    <%
                                        if (prenotazione.getStatus().equals("Ritirato")) {
                                    %>
                                    <p><strong>Data di scadenza della prenotazione: <%=prenotazione.getDataScadenza().get(Calendar.DAY_OF_MONTH) + "-" + (prenotazione.getDataScadenza().get(Calendar.MONTH) + 1) + "-" + prenotazione.getDataScadenza().get(Calendar.YEAR)%></strong></p>
                                    <%
                                    } else if (prenotazione.getStatus().equals("Restituito")) {
                                    %>
                                    <p><strong>Data di consegna del Libro: <%=prenotazione.getDataConsegna().get(Calendar.DAY_OF_MONTH) + "-" + (prenotazione.getDataConsegna().get(Calendar.MONTH) + 1) + "-" + prenotazione.getDataConsegna().get(Calendar.YEAR)%></strong></p>
                                    <%
                                        }
                                    %>

                                    <%
                                        if (prenotazione.getStatus().equals("Da ritirare")) {
                                            if (session.getAttribute("persona") != null) {
                                    %>
                                    <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0">  
                                        <div class="col-md">
                                            <button type="button" class="btn btn-transparent"  data-toggle="modal" data-target="#modal-annullare" >
                                                Annulla prenotazione
                                            </button>
                                        </div>
                                    </div>
                                    <%
                                    } else if (bibliotecario != null) {
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
                                        if (bibliotecario != null) {
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
                            </div>
                            <% } else if (message.equals("error")) { %>
                            <div class="alert alert-danger">
                                <strong>Errore !</strong> 
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
                        <form method="GET" name="ritiro-form" id="ritiro-form" action="conferma-ritiro" >
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
                        <form method="GET" name="restituzione-form" id="restituzione-form" action="conferma-restituzione" >
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
        <script>
            $("img").on("error", function () {
                $(this).attr("src", "../libri/images/defaultBook.png");
            });
        </script>
    </body>

</html>