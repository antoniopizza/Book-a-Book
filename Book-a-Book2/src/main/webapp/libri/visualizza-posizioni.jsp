<%@page import="core.entities.Posizione"%>
<%@page import="java.util.List"%>
<%
    String nomePagina = "Visualizza Scaffali";
    List<Posizione> posiziones = (List<Posizione>) request.getAttribute("posizioni");
    Biblioteca biblioteca = ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca();

    String message = (String) request.getAttribute("message");
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


                            <div class="row mb-2">
                                <div class="col-md-8"><h2>Gestisci Scaffali</h2></div>
                                <div class="col-md">
                                    <button type="button" class="btn btn-main"  data-toggle="modal" data-target="#form-aggiunta-modal" >
                                        Aggiungi scaffale
                                    </button>
                                </div>
                            </div>

                            <% if (message != null) {
                                    if (!message.equals("success")) { %>
                            <div class="alert alert-danger">
                                <p><%= message %></p>
                            </div>
                            <% } else { %>
                            <div class="alert alert-success">
                                <p>Operazione andata a buon fine</p>
                            </div>
                            <% }                            
                            } 
                            %>

                            <% if (posiziones.isEmpty()) { %>
                            <div class="alert alert-warning">
                                <p>Non sono presenti scaffali</p>
                            </div>
                            <% } else {%>
                            <div class="tab-content scroll-page" id="pills-tabContent">
                                <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                                    <div class="table-responsive">
                                        <table class="table product-dashboard-table">
                                            <thead>
                                                <tr>
                                                    <th style="text-align: center">Etichetta</th>
                                                    <th style="text-align: center">Copie presenti</th>
                                                    <th style="text-align: center">Azioni</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (Posizione p : posiziones) {%>

                                                <tr>
                                                    <td style="text-align: center"><%= p.getEtichetta()%></td>

                                                    <td style="text-align: center"><%= p.getCopie().size()%></td>

                                                    <td class="action">

                                                        <ul class="list-inline justify-content-center product-dashboard-table">                                                                                                                              
                                                            <li class="list-inline-item">
                                                                <a class="delete" 
                                                                   href="elimina-posizione?etichetta=<%= p.getEtichetta()%>&isil=<%= biblioteca.getIsil()%>"
                                                                   data-toggle="tooltip" 
                                                                   data-placement="top" 
                                                                   title="elimina copia">

                                                                    <i class="fa fa-trash"></i>
                                                                </a>
                                                            </li>
                                                        </ul>

                                                    </td>

                                                </tr>

                                                <% } %>
                                            </tbody>

                                        </table>
                                    </div>
                                </div>
                            </div>
                            <% }%>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Modal -->
        <div id="form-aggiunta-modal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <form method="GET" name="sposta-form" id="aggiungi-form" action="aggiunta-posizione" >
                        <div class="modal-header">
                            <h4 class="modal-title">Aggiungi Scaffale</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>                        
                        </div>
                        <div class="modal-body">
                            <div class="form-row form-group">
                                <div class="col-4">
                                    <label>Etichetta dello scaffale</label>
                                </div>    
                                <div class="col">
                                    <input type="text" class="form-control"  name="etichetta" id="id-scaffale"/>
                                    <input type="hidden" name="isil" value="<%= biblioteca.getIsil()%>" />                                                                                                           
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
    </body>
</html>
