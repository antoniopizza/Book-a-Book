<%@page import="java.util.Collection"%>
<% String nomePagina = "Biblioteche Registrate";

    Collection<Biblioteca> biblioteche = (Collection<Biblioteca>) request.getAttribute("biblioteche");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="head.jsp" %>
    <body>
        <%@include file="header.jsp" %>

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
                            <%@include file="searchbar.jsp" %>
                            <br>
                            <div class="table-responsive">
                                <h3 class="widget-header">Tutte le biblioteche inscritte alla nostra piattaforma</h3>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Isil</th>
                                            <th>Nome</th>                
                                            <th>Indirizzo</th>                                        
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Biblioteca b : biblioteche) {%>
                                        <tr>
                                            <td><%= b.getIsil()%></td>
                                            <td><%= b.getNome()%></td>
                                            <td><%= b.getIndirizzo().getVia() + " " + b.getIndirizzo().getCivico() + " " + b.getIndirizzo().getCitta() + " " + b.getIndirizzo().getProvincia()%></td>
                                        </tr>        
                                        <% }%>
                                    </tbody>                             
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
