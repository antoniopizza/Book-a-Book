<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->
<% String path = application.getContextPath() + "/skeleton-pages/"; %>

<% String nomePagina = "Ricerca Utenti";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="<%=path%>head.jsp" %>
    <body>
        <%@include file="<%=path%>header.jsp" %>

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
                            <%@include file="<%=path%>searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header"></h3>

                            <!-- Da qui in poi puoi inserire codice per creare la pagina da questo scheletro -->                    

                            <!--=================================
                            =      Contenuto della pagine      =
                            ==================================-->      
                            <h3 class="widget-header">Cerca un Utente</h3>
                            <div class="advance-search">
                                <form action="#">
                                    <div class="row">
                                        <!-- Store Search -->
                                        
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-2 col-md-12">
                                            <select class="form-control mb-2 mr-sm-2 mb-sm-0">
                                                <option>Cognome</option>
                                                <option>ID</option>
                                                <option>Tipo</option>
                                                <option>Email</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-7 col-md-12">
                                            <div class="block d-flex">
                                                <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="search" placeholder="Es. Rossi">
                                            </div>
                                        </div>
                                        <div class="col-lg-2 col-md-12">
                                            <div class="block d-flex">
                                                <!-- Search Button -->
                                                <button class="btn btn-main">CERCA</button>
                                            </div>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>                 

        <%@include file="<%=path%>footer.jsp" %>
    </body>
</html>
