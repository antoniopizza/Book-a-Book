<%@page import="core.entities.Bibliotecario"%>
<%@page import="core.entities.Autore"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="core.entities.Libro"%>
<% String nomePagina = "Risultati Ricerca";
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
                                        <a href="#"> Biblioteche</a>
                                    </li>
                                    <li>
                                        <a href="#"> Novità</a>
                                    </li>
                                    <li>
                                        <a href="#"> Autori</a>
                                    </li>
                                    <li>
                                        <a href="#"> Popolari</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-10 offset-lg-0 scroll-page">

                        <div class="widget dashboard-container my-adslist">                            
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header"></h3>

                            <%
                                List<Libro> libri;
                                List<Integer> disponibili;
                                Bibliotecario bibliotecario = (Bibliotecario) request.getSession().getAttribute("bibliotecario");
                                String message = (String) request.getAttribute("message");
                                if (!message.equals("correct")) {

                            %>
                            <h3 class="widget-header"><%=message%></h3>
                            <%
                            } else {

                                libri = new ArrayList<>();
                                libri = (ArrayList<Libro>) request.getAttribute("libri");
                                disponibili = new ArrayList<Integer>();
                                disponibili = (ArrayList<Integer>) request.getAttribute("disponibili");
                            %>

                            <table class="table table-responsive product-dashboard-table">
                                <thead>
                                    <tr>
                                        <th>Copertina</th>
                                        <th>Descrizione</th>                                        
                                        <th class="text-center">Azioni</th>
                                    </tr>
                                </thead>
                                <tbody>                                   
                                    <%
                                        int dispCounter = 0;
                                        for (Libro book : libri) {
                                            List<Autore> autori = book.getAutori();
                                    %>


                                    <tr>
                                        <td class="product-thumb">


                                            <img style="width:150px; height:auto" src="<%=book.getPathFoto()%>" alt="images/defaultBook.png" id="book-img"></td>
                                        <td class="product-details">
                                            <h3 class="title"><%= book.getTitolo()%> </h3>
                                            <span class="location"><strong>ISBN:</strong><%= book.getIsbn()%></span>



                                            <%
                                                int i = 0;
                                                for (Autore aut : autori) {
                                                    if (i == 0) {
                                            %>
                                            <span class="add-id"><strong>Autori:</strong>

                                                <%
                                                } else {
                                                %>
                                                <span class="add-id"><strong style="color:rgba(0, 0, 0, 0);"></strong>
                                                    <%
                                                        }
                                                    %>
                                                    <%=aut.getNome()%>
                                                </span>
                                                <%
                                                        i++;
                                                    }
                                                %>

                                                <span><strong>Editore: </strong><%= book.getEditore()%></span>
                                                <%
                                                    if (disponibili.get(dispCounter) == 0) {
                                                %>
                                                <span style="color:red;"><strong>Disponibilità: </strong>Non disponibile</span>
                                                <%
                                                } else {
                                                %>
                                                <span style="color:green;"><strong>Disponibilità: </strong>Disponibile</span>
                                                <%
                                                    }
                                                %>

                                        </td>                                       
                                        <td class="action" data-title="Action">
                                            <div class="">
                                                <form action="<%=application.getContextPath()%>/libri/visualizza-libro" method="GET">
                                                    <input type="hidden" name="isbn" value="<%= book.getIsbn()%>">
                                                    <%
                                                        if (bibliotecario != null) {
                                                    %>
                                                    <input type="hidden" name="isil" value="<%= bibliotecario.getBiblioteca().getIsil()%>">
                                                    <%
                                                        }
                                                    %>
                                                    <button class="btn btn-main">Dettagli</button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>                                   
                                    <%
                                            dispCounter++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <%
                                }
                            %>
                        </div>
                    </div>
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
