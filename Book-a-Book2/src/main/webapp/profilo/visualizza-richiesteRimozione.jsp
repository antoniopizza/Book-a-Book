<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>

<%@page import="core.entities.Biblioteca"%>
<%@page import="java.util.List"%>
<%@page import="core.managers.ManagerRegistrazione"%>
<% String nomePagina = "visualizza-richiesteRimozione";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>





        <!--==================================
 =            User Profile            =
 ===================================-->

        <section class="user-profile section">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 offset-md-1 col-lg-4 offset-lg-0">
                        <div class="sidebar">
                            <!-- User Widget -->
                            <div class="widget user-dashboard-profile">
                                <!-- User Image -->
                                <div class="profile-thumb">
                                    <img src="<%=((Admin) request.getSession().getAttribute("admin")).getAccount().getPathFoto()%>" alt="" class="rounded-circle">
                                </div>
                                <!-- User Name -->
                                <h5 class="text-center"><%= /* NOME*/((Admin) request.getSession().getAttribute("admin")).getNome()%>  <%= /*COGNOME */((Admin) request.getSession().getAttribute("admin")).getCognome()%></h5>

                            </div>
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <%@include file="../skeleton-pages/menuAdmin.jsp" %>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                        <!-- Recently Favorited -->
                        <div class="widget dashboard-container my-adslist">
                            <h3 class="widget-header">Richieste Rimozione </h3>
                            <table class="table table-responsive product-dashboard-table">
                                <thead>
                                    <tr>

                                        <th class= "col-md-10 offset-md-1 col-lg-2 offset-lg-0">Info</th>
                                        <th class="text-center col-md-10 offset-md-1 col-lg-2 offset-lg-0">Action</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%                                        if (richiesteR.size() == 0) { %>
                                <td >
                                    <h3> Non Ci Sono Richieste di Rimozioni Presenti!! </h3>
                                </td>
                                <td>
                                    <a title="Back" class="active" href="../skeleton-pages/index.jsp">
                                        <i class="fa fa-arrow-circle-left" style="color: #a84e00; "></i>
                                    </a>
                                </td>

                                <%    }

                                %>



                                <% for (int i = 0; i < richiesteR.size(); i++) {


                                %>

                                <tr>


                                    <td class="product-details">
                                        <h3 class="title"><%=richiesteR.get(i).getNome()%></h3>
                                        <span class="add-id"><strong>Isil:</strong> <%=richiesteR.get(i).getIsil()%></span>

                                        <span class="status active" style="color: orange"><strong>Status:</strong><%=richiesteR.get(i).getStatus()%></span>
                                        <span class="location"><strong>Location:</strong><%=richiesteR.get(i).getIndirizzo().getCitta()%>, <%=richiesteR.get(i).getIndirizzo().getProvincia()%></span>
                                    </td>


                                    <td class="action" data-title="Action">
                                        <div class="">
                                            <ul class="list-inline justify-content-center">



                                                <li class="list-inline-item">
                                                    <a title="Elimina" class="delete" href="rimozione-account?tipo=biblioteca&isil=<%=richiesteR.get(i).getIsil()%>">
                                                        <i class="fa fa-trash"></i>
                                                    </a>		
                                                </li>

                                            </ul>
                                        </div>
                                    </td>
                                </tr>

                                <% }%>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>



    </body>
</html>
