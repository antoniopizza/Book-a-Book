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
                            <div class="alert alert-danger">
                                <p>Si è verificato un errore durante la prenotazione. Riprova più tardi.</p>
                            </div>
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