<%@page import="core.entities.Copia"%>
<%@page import="javax.swing.text.Position"%>
<%@page import="java.util.Collection"%>
<%@page import="core.entities.Posizione"%>
<%@page import="core.entities.Persona"%>
<%@page import="core.entities.Bibliotecario"%>
<%@page import="java.util.List"%>
<%@page import="core.entities.Autore"%>
<%@page import="core.entities.Libro"%>
<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->

<%

    Libro book;
    book = (Libro) request.getAttribute("libro");
    String nomePagina = "Gestisci copie";
    Collection<Posizione> posizioni = (Collection<Posizione>) request.getAttribute("posizioni");
    Biblioteca biblioteca = ((Bibliotecario) session.getAttribute("bibliotecario")).getBiblioteca();
    String message = "";
    if (request.getAttribute("message") != null) {
        message = (String) request.getAttribute("message");
    }

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
                    <div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0">

                        <div class="widget dashboard-container my-adslist">                            
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header"></h3>                            
                            <div class="row">
                                <div class="col-md-10">
                                    <h1 class="product-title"><%= book.getTitolo()%></h1>
                                </div>
                                <div class="col-md">
                                    <a href="visualizza-libro?isbn=<%= book.getIsbn()%>&isil=<%= biblioteca.getIsil()%>" class="btn btn-main" title="torna ai dettagli del libro">
                                        <i class="fa fa-reply" aria-hidden="true"></i>
                                    </a>
                                </div>
                            </div>
                            <br/>
                            <div class="row">

                                <!-- Colonna titolo e copertina libro-->
                                <div class="col-md-6">
                                    <div class="product-details">

                                        <div class="product-meta">

                                        </div>
                                        <div>

                                            <div class="carousel-inner">
                                                <div class="carousel-item active" style="width: 300px; height: 400px;">
                                                    <img src="<%= book.getPathFoto()%>" alt="Copertina libro" style="width: 100%; height: 100%;">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content">

                                        </div>
                                    </div>
                                </div>
                                <!-- Colonna laterale con dettagli libro-->
                                <div class="col-md-6">
                                    <div class="sidebar">

                                        <!-- User Profile widget -->
                                        <div class="widget user">
                                            <h3>Dettagli libro:</h3>
                                            <table width="100%">
                                                <tr>
                                                    <td><strong>ISBN:</strong></td>
                                                    <td align="right"><p><%= book.getIsbn()%></p></td>
                                                </tr>
                                                <%
                                                    List<Autore> autori = book.getAutori();
                                                    int i = 0;
                                                    for (Autore aut : autori) {
                                                %>
                                                <tr>
                                                    <%
                                                        if (i == 0) {
                                                    %>
                                                    <td><strong>Autori:</strong></td>
                                                    <%
                                                    } else {
                                                    %>
                                                    <td>&nbsp;</td>
                                                    <%
                                                        }
                                                    %>
                                                    <td align="right">
                                                        <p><%= aut.getNome()%></p>
                                                    </td>
                                                </tr>
                                                <%
                                                        i++;
                                                    }
                                                %>
                                                <tr>
                                                    <td><strong>Editore:</strong></td>
                                                    <td align="right"><p><%= book.getEditore()%></p></td>
                                                </tr>                                                
                                            </table>  

                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="row dashboard-container">
                                <% if (message.equals("error")) { %>
                                <div class="alert alert-danger">
                                    <p>Si è verificato un errore durante l'esecuzione dell'operazione</p>
                                </div>
                                <% } %>
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-8"><h2>Gestisci copie</h2></div>
                                        <div class="col-md">
                                            <button type="button" class="btn btn-main"  data-toggle="modal" data-target="#form-aggiunta-modal" >
                                                Aggiungi copia
                                            </button>
                                        </div>
                                    </div>

                                    <div class="tab-content" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                                            <% for (Posizione p : posizioni) {%>
                                            <div class="table-responsive ">

                                                <table class="table-striped table">                                                    

                                                    <thead>
                                                        <tr>
                                                    <h6>Posizione <%= p.getEtichetta()%></h6>
                                                    </tr>
                                                    <tr>    
                                                        <th>Codice copia</th>
                                                        <th>Disponibilità</th>
                                                        <th>Status</th>
                                                        <th>Azioni</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                        <% for (Copia c : p.getCopie()) {%>
                                                        <tr>
                                                            <td><%= c.getId()%></td>
                                                            <td><%= c.getDisponibilita()%></td>
                                                            <td><%= c.getStatus()%></td>
                                                            <td class="action">

                                                                <ul class="list-inline justify-content-center product-dashboard-table">
                                                                    <li class="list-inline-item">

                                                                        <a                                                                             
                                                                            data-toggle="modal" 
                                                                            data-target="#form-spostamento-modal" 
                                                                            data-placement="top" 
                                                                            title="sposta copia "
                                                                            posizione="<%= p.getEtichetta()%>"
                                                                            copia="<%= c.getId()%>"
                                                                            href="#"
                                                                            class="view sposta">

                                                                            <i class="fa fa-arrows"></i>
                                                                        </a>		
                                                                    </li>                                                                   
                                                                    <li class="list-inline-item">
                                                                        <a class="delete" 
                                                                           data-toggle="tooltip" 
                                                                           data-placement="top" 
                                                                           title="elimina copia"
                                                                           href="elimina-copia?isbn=<%= book.getIsbn()%>&isil=<%= biblioteca.getIsil()%>&etichetta=<%= p.getEtichetta()%>&id-copia=<%= c.getId()%>">
                                                                            <i class="fa fa-trash"></i>
                                                                        </a>
                                                                    </li>
                                                                </ul>

                                                            </td>
                                                        </tr>
                                                        <% } %>

                                                    </tbody>

                                                </table>
                                                <br>                                                
                                            </div>

                                            <% }%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

        </section>

        <!-- Modal -->
        <div id="form-spostamento-modal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <form method="GET" name="sposta-form" id="sposta-form" action="sposta-copie" >
                        <div class="modal-header">
                            <h4 class="modal-title">Sposta copia</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>                        
                        </div>
                        <div class="modal-body">
                            <div class="form-row">
                                <div class="col">
                                    <label>Dove vuoi riposizionare la copia ?</label>
                                </div>                                
                                <div class="col-6"> 
                                    <select name="nuova-posizione">
                                        <% for (Posizione p : biblioteca.getPosizioni()) {%>
                                        <option value="<%= p.getEtichetta()%>"><%= p.getEtichetta()%></option>
                                        <% }%>
                                    </select>
                                    <input type="hidden" name="isil" value="<%= biblioteca.getIsil()%>" />
                                    <input type="hidden" name="vecchia-posizione" id="vecchia-posizione" value="" />
                                    <input type="hidden" name="id-copia" id="id-copia" value="" />
                                    <input type="hidden" name="isbn" value="<%= book.getIsbn()%>" />
                                </div>
                            </div>                            

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
                            <button type="submit" class="btn btn-default" >Sposta</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>


        <!-- Modal -->
        <div id="form-aggiunta-modal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <form method="GET" name="sposta-form" id="aggiungi-form" action="aggiungi-copia" >
                        <div class="modal-header">
                            <h4 class="modal-title">Aggiungi copia</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>                        
                        </div>
                        <div class="modal-body">
                            <div class="form-row form-group">
                                <div class="col-4">
                                    <label>Codice della copia</label>
                                </div>    
                                <div class="col">
                                    <input type="text" class="form-control"  name="id-copia" id="id-copia"/>
                                </div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-4">
                                    <label>Scaffale</label>
                                </div>
                                <div class="col"> 
                                    <select name="posizione">
                                        <% for (Posizione p : biblioteca.getPosizioni()) {%>
                                        <option value="<%= p.getEtichetta()%>"><%= p.getEtichetta()%></option>
                                        <% }%>
                                    </select>
                                    <input type="hidden" name="isil" value="<%= biblioteca.getIsil()%>" />                                                                       
                                    <input type="hidden" name="isbn" value="<%= book.getIsbn()%>" />
                                </div>
                            </div>



                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
                            <button type="submit" class="btn btn-default" >Aggiungi</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>

        <%@include file="../skeleton-pages/footer.jsp" %>
        <script>
            $(document).ready(function () {
                $("img").on("error", function () {
                    $(this).attr("src", "images/defaultBook.png");
                });


                $(".sposta").click(function () {

                    var idCopia = $(this).attr("copia");
                    var idPosizione = $(this).attr("posizione");

                    $("#vecchia-posizione").val(idPosizione);
                    $("#id-copia").val(idCopia);

                });

            });
        </script>
    </body>
</html>
