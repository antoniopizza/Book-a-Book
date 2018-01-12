<%@page import="java.util.List"%>
<%@page import="core.entities.Autore"%>
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
                    <div class="col-md-10 offset-md-1 col-lg-10 offset-lg-0">

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
                            <h3><%=message%></h3> <!-- Se c'è un errore, stampa il messaggio -->
                            <%
                            } else {
                                book = (Libro) request.getAttribute("libro");

                            %>
                            <h3></h3>

                            <div class="row">
                                <div class="col-md-12">
                                    <h1 class="product-title"><%= book.getTitolo()%></h1>
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
                            <!-- div descrizione libro -->
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="tab-content" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                                            <h3 class="tab-title">Descrizione libro:</h3>
                                            <p><%= book.getDescrizione()%></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% }%>



                        </div>
                    </div>
                </div>

            </div>
        </section>                 

        <%@include file="../skeleton-pages/footer.jsp" %>
    </body>
</html>
