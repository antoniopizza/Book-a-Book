<%-- 
    Document   : sceltaRegistrazione
    Created on : 23-dic-2017, 18.00.17
    Author     : salva
--%>

<%@page import="java.util.List"%>
<%@page import="core.managers.ManagerRegistrazione"%>
<% String nomePagina = "profiloPersonale-Utente";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>

    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <%            // lista di richieste per saperne il numero nella sezione visualizza richieste
            List<Biblioteca> biblioteche = (new ManagerRegistrazione().visualizzaRichieste());

            if (request.getSession().getAttribute("accettata") != null) {
        %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="modifica">
            <h5 style="color: white;">Biblioteche Confermate</h5></div>
            <%
                    request.getSession().removeAttribute("accettata");
                }
            %>




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
                                    <li>
                                        <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads</a></li>
                                    <li>
                                        <a href="../profilo/visualizza-richieste.jsp"><i class="fa fa-bolt"></i> Visualizza Richieste <span><%= biblioteche.size()%></span></a>
                                    </li>
                                    <li>
                                        <a href="dashboard-archived-ads.html"><i class="fa fa-file-archive-o"></i>Archeved Ads <span>12</span></a>
                                    </li>
                                    <li>
                                        <a href="dashboard-pending-ads.html"><i class="fa fa-bolt"></i> Pending Approval<span>23</span></a>
                                    </li>

                                    <li>
                                        <a href="delete-account.html"><i class="fa fa-power-off"></i>Delete Account</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0">
                        <!-- Recently Favorited -->
                        <div class="widget dashboard-container my-adslist">
                            <h3 class="widget-header">Richieste </h3>
                            <table class="table table-responsive product-dashboard-table">
                                <thead>
                                    <tr>
                                      
                                        <th class="text-center">Info</th>
                                        <th class="text-center">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(int i = 0; i < biblioteche.size(); i++){ 
                                    
                                            
                                    %>
                                    
                                    <tr>
                                            
                                       
                                        <td class="product-details">
                                            <h3 class="title"><%=biblioteche.get(i).getNome() %></h3>
                                            <span class="add-id"><strong>Isil:</strong> <%=biblioteche.get(i).getIsil() %></span>
                                            
                                            <span class="status active"><strong>Status:</strong><%=biblioteche.get(i).getStatus() %></span>
                                            <span class="location"><strong>Location:</strong><%=biblioteche.get(i).getIndirizzo().getCitta() %>, <%=biblioteche.get(i).getIndirizzo().getProvincia() %></span>
                                        </td>
                                        
                                        
                                        <td class="action" data-title="Action">
                                            <div class="">
                                                <ul class="list-inline justify-content-center">
                                                    
                                                    <li class="list-inline-item">
                                                        <a class="edit" href="conferma-biblioteca?isil=<%=biblioteche.get(i).getIsil() %>">
                                                            <i class="fa fa-pencil"></i>
                                                        </a>		
                                                    </li>
                                                    
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                    
                                    <% } %>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </section>


        <%@include file="../skeleton-pages/footer.jsp" %>

        <!-- Script --> 
        <script>

            $(document).ready(setTimeout(function () {
                $("#modifica").hide();
            }, 3000));


        </script>

    </body>
</html>
