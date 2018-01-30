<%@page import="core.entities.Autore"%>
<%@page import="java.util.Collection"%>
<% String nomePagina = "Biblioteche Registrate";

    Collection<Autore> autori = (Collection<Autore>) request.getAttribute("autori");
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
                            <div class="table-responsive">
                                <h3 class="widget-header">Tutti gli autori</h3>
                                <table class="table">
                                    <thead>
                                        <tr>                                            
                                            <th>Nome</th>
                                            <th>Azioni</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Autore a : autori) {%>
                                        <tr>

                                            <td><%= a.getNome()%></td>
                                            <td>
                                                <a class="btn btn-main" href="cerca-libro?criterio=autore&searchKey=<%= a.getNome()%>">
                                                    Visualizza Libri
                                                </a>
                                            </td>
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
