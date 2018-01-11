<%@page import="core.entities.Libro"%>
<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->

<% String nomePagina = "Skeleton";
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
                            <%
                            Libro book;
                            String message = (String) request.getAttribute("message");
                            
                            if (!message.equals("correct")) {
                            %>
                            <h3><%=message %></h3> <!-- Se c'è un errore, stampa il messaggio -->
                            <%
                            }
                            else {
                                book = (Libro) request.getAttribute("Libro");
                            %>
                            <p> I'm here </p>
                            <h3><%= book.getTitolo() %></h3>
                            <%
                            }
                            %>

                        </div>
                    </div>
                </div>
            </div>
        </section>                 

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
