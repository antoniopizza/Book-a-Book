<%@page import="core.entities.Bibliotecario"%>
<%@page import="core.entities.Autore"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="core.entities.Libro"%>
<% String nomePagina = "Risultati Ricerca";
    int offset = (int) request.getAttribute("offset");
    List<Libro> libri = new ArrayList<>();
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
                                Bibliotecario bibliotecario = (Bibliotecario) request.getSession().getAttribute("bibliotecario");
                                String message = (String) request.getAttribute("message");
                                if (!message.equals("correct")) {

                            %>
                            <h3 class="widget-header"><%=message%></h3>
                            <%
                            } else {
                                
                                libri = (ArrayList<Libro>) request.getAttribute("libri");
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
                                        int pageIndex = 0;
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
                                            pageIndex++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            <%
                                }
                            %>

                            <div class="row">
                                <div class="col-3">
                                    <% if (offset > 0) {%>

                                    <a class="btn-lg btn-transparent" href="cerca-libro?searchKey=<%=request.getParameter("searchKey")%>&criterio=<%=request.getParameter("criterio")%>&offset=<%=(offset - 3)%>">Precedente</a>

                                    <% }%>
                                </div>
                                <div class="col"></div>
                                <div class="col-3">
                                    
                                    <% if(libri.size() >= 3) { %>
                                    <a class="btn-lg btn-transparent" href="cerca-libro?searchKey=<%=request.getParameter("searchKey")%>&criterio=<%=request.getParameter("criterio")%>&offset=<%=(offset + 3)%>">Successva</a>
                                    <% } %>
                                </div>
                            </div>                            
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
