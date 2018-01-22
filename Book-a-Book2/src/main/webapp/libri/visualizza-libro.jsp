<%@page import="core.entities.Persona"%>
<%@page import="core.entities.Bibliotecario"%>
<%@page import="java.util.List"%>
<%@page import="core.entities.Autore"%>
<%@page import="core.entities.Libro"%>
<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->

<%

    Libro book;
    book = (Libro) request.getAttribute("libro");
    String nomePagina;
    String message = (String) request.getAttribute("message");
    if (!message.equals("correct")) {
        nomePagina = "Libro non trovato";
    } else {
        nomePagina = book.getTitolo();
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

                            <%
                                if (!message.equals("correct")) {
                            %>
                            <h3><%=message%></h3> <!-- Se c'è un errore, stampa il messaggio -->
                            <%
                            } else {
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

                                            <%
                                                if ((Bibliotecario) request.getSession().getAttribute("bibliotecario") != null) {
                                                    //SEZIONE VISUALIZZATA SOLO SE BIBLIOTECARIO LOGGATO
                                            %>
                                            <br/>
                                            <h3>Operazioni:</h3>
                                            <br/>
                                            <div class="row">
                                                <div class="col-md-12" align="center">

                                                    <form action="#" method="GET">
                                                        <input type="hidden" name="isbn" value="">
                                                        <button class="btn btn-main">Elimina libro</button>
                                                    </form>
                                                    <br/>
                                                    
                                                    <a class="btn btn-main" href="gestisci-copie?isbn=<%=book.getIsbn()%>">Gestisci copie</a>
                                                    
                                                    <br/>
                                                    <form action="#" method="GET">
                                                        <input type="hidden" name="isbn" value="">
                                                        <button class="btn btn-main">Prenota</button>
                                                    </form>
                                                </div>
                                            </div>
                                            <%
                                                }
                                            %>
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

                            <%
                                if ((Bibliotecario) request.getSession().getAttribute("bibliotecario") == null) {
                                    //SEZIONE VISUALIZZATA SOLO SE BIBLIOTECARIO NON LOGGATO
                            %>
                            <!-- div tabella biblioteche -->
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="advance-search">
                                        <form action="#" id="search-form-biblioteca" method="GET">
                                            <div class="row">
                                                <!-- Ricerca biblioteca -->
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2 col-md-12">
                                                    <select class="form-control mb-2 mr-sm-2 mb-sm-0" name="criterio">
                                                        <option>Filtro 1</option>
                                                        <option>Filtro 2</option>
                                                        <option>Filtro 3</option>
                                                        <option>Filtro N</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-6 col-md-12">
                                                    <div class="block d-flex">
                                                        <input type="text" minlength="2" class="form-control mb-2 mr-sm-2 mb-sm-0" id="search-main" placeholder="Cerca il tuo libro" name="searchKey" required>

                                                    </div>
                                                </div>
                                                <div class="col-lg-2 col-md-12">
                                                    <div class="block d-flex">
                                                        <!-- Search Button -->
                                                        <input type="submit" class="btn btn-main" value="CERCA">
                                                    </div>
                                                </div>
                                                <div class="col-lg-1"></div>
                                            </div>
                                        </form>
                                        <script>
                                            $("#search-form-main").submit({
                                            var text = $("#search-main").val().toString();
                                            if (text.match("/^.{2,}$/")){
                                            return true;
                                            } else {
                                            alert("Inserire almeno 2 caratteri");
                                            $("#search-main").focus();
                                            return false;
                                            }
                                            });
                                        </script>

                                    </div>
                                </div>
                            </div>
                            <br/>
                            <div class="row">

                                <div class="col-lg-12">
                                    <table class="table table-hover">
                                        <thead align="center">
                                            <tr>
                                                <th>
                                                    ISIL
                                                </th>
                                                <th>
                                                    Nome
                                                </th>
                                                <th>
                                                    Indirizzo
                                                </th>
                                                <th>
                                                    Copie disponibili
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody align="center">
                                            <tr>
                                                <td>
                                                    IT-123
                                                </td>
                                                <td>
                                                    Biblioteca Atripalda
                                                </td>
                                                <td>
                                                    Via Manfredi 6, Atripalda
                                                </td>
                                                <td>
                                                    15
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    IT-321
                                                </td>
                                                <td>
                                                    Biblioteca Svevo
                                                </td>
                                                <td>
                                                    Via Roma 21, Nocera Inferiore
                                                </td>
                                                <td>
                                                    31
                                                </td>
                                            </tr>
                                        </tbody>

                                    </table>
                                </div>

                            </div>
                            <%
                                if ((Persona) request.getSession().getAttribute("persona") != null) {
                            %>
                            <div class="row">
                                <div class="col-lg-10"></div>
                                <div class="col-lg-2">
                                    <form action="#" method="GET">
                                        <input type="hidden" name="isil" value="">
                                        <input type="hidden" name="isbn" value="">
                                        <button class="btn btn-main">Prenota</button>
                                    </form>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>


                        </div>

                        <% }%>



                    </div>
                </div>

        </section>                 

        <%@include file="../skeleton-pages/footer.jsp" %>
        <script>
            $("img")
                    .on("error", function () {
                    $(this).attr("src", "images/defaultBook.png");
                    });
        </script>
    </body>
</html>
