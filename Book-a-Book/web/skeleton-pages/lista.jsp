<% String nomePagina = "Lista";
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

                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
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
                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                      
                        <div class="widget dashboard-container my-adslist">
                            <h3 class="widget-header">Cerca un libro</h3>
                            <%@include file="searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header"></h3>
                            <table class="table table-responsive product-dashboard-table">
                                <thead>
                                    <tr>
                                        <th>Copertina</th>
                                        <th>Descrizione</th>                                        
                                        <th class="text-center">Azioni</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>

                                        <td class="product-thumb">
                                            <img width="80px" height="auto" src="http://alessandria.bookrepublic.it/api/books/9788852044458/cover" alt="image description"></td>
                                        <td class="product-details">
                                            <h3 class="title">Il conte di Montecristo</h3>
                                            <span class="location"><strong>ISBN:</strong>9788673881126</span>
                                            <span class="add-id"><strong>Autore: </strong>Alexandre Dumas</span>
                                            <span><strong>Editore: </strong>Oscar Mondadori</span>
                                            <span class="status active"><strong>Status</strong>Disponibile</span>                                            
                                        </td>                                       
                                        <td class="action" data-title="Action">
                                            <div class="">
                                                <ul class="list-inline justify-content-center">
                                                    <li class="list-inline-item">
                                                        <a data-toggle="tooltip" data-placement="top" title="Tooltip on top" class="view" href="">
                                                            <i class="fa fa-eye"></i>
                                                        </a>		
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a class="edit" href="">
                                                            <i class="fa fa-pencil"></i>
                                                        </a>		
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a class="delete" href="">
                                                            <i class="fa fa-trash"></i>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>                                   
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="footer.jsp" %>
    </body>
</html>
