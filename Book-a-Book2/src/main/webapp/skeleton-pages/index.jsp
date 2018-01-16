<%-- 
    Document   : index
    Created on : 14-dic-2017, 15.59.47
    Author     : mirko
--%>

<%@page import="core.managers.ManagerRegistrazione"%>
<%@page import="java.util.List"%>
<%@page import="core.entities.Bibliotecario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String nomePagina = "Home";%> <!-- Parametro che viene passato alla head che definisce il titolo. In questo caso sarà: Book a Book | Home -->
    <%@include file="head.jsp" %> <!-- Includere sempre al posto della head -->
    <body class="body-wrapper">

        <%@include file="../skeleton-pages/header.jsp" %>

        <%  if (request.getSession().getAttribute("nuovoDip") != null) {
        %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="someThing">
            <h5 style="color: white;">Nuovo Dipendente Aggiunto con Successo</h5></div>
            <%
                    request.getSession().removeAttribute("nuovoDip");
                }

                if (request.getSession().getAttribute("rimozioneU") != null) {
            %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="someThing">
            <h5 style="color: white;">Account Rimosso con Successo </h5></div>
            <%
                    request.getSession().removeAttribute("rimozioneU");
                }
                if (request.getSession().getAttribute("rimozioneB") != null) {
            %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="someThing">
            <h5 style="color: white;">Biblioteca Rimossa con Successo </h5></div>
            <%
                    request.getSession().removeAttribute("rimozioneB");
                }
                if (request.getSession().getAttribute("modificaPass") != null) {
            %>
        <div class="container" style="background-color: #59d659; text-align: center;" id="someThing">
            <h5 style="color: white;">Password Modificata Con Successo </h5></div>
            <%
                    request.getSession().removeAttribute("modificaPass");
                }

            %>
        <!--===============================
        =            Hero Area            =
        ================================-->

        <section class="hero-area bg-1 text-center overly">
            <!-- Container Start -->
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <!-- Header Contetnt -->
                        <div class="content-block">
                            <h1>Scegli il libro adatto a te</h1>
                            <p>“Puoi leggere, leggere, leggere, che è la cosa più bella che si possa fare in gioventù: e piano piano ti sentirai arricchire dentro, sentirai formarsi dentro di te quell’esperienza speciale che è la cultura”</p>
                            <p>Pier Paolo Pasolini</p>

                        </div>
                        <!-- Advance Search SIDEBAR IN FILE APPOSITO -->
                        <%@include file="searchbar.jsp" %>

                    </div>
                </div>
            </div>
            <!-- Container End -->
        </section>

        <!--==================================
        =            Sidebar            =
        ===================================-->

        <section class="user-profile section">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">
                        <br> <!-- Stacca la sidebar dalla barra di ricerca principale -->
                        <div class="sidebar">
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <% if ((Persona) request.getSession().getAttribute("persona") == null && (Bibliotecario) request.getSession().getAttribute("bibliotecario") == null && (Admin) request.getSession().getAttribute("admin") == null) {%>
                                    <li>
                                        <a href="dashboard-my-ads.html"> Biblioteche</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"></i> Novità</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"></i> Autori</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"></i> Popolari</a>
                                    </li>

                                    <% } else if ((Persona) request.getSession().getAttribute("persona") != null) {
                                    %> 

                                    <%@include file="menuPersona.jsp" %>

                                    <%   } else if ((Bibliotecario) request.getSession().getAttribute("bibliotecario") != null) {
                                    %>


                                    <%@include file="menuBibliotecario.jsp" %>


                                    <% } else if ((Admin) request.getSession().getAttribute("admin") != null) {

                                    %> 
                                    <%@include file="menuAdmin.jsp" %>
                                    <%                               }
                                    %>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!--====================================================
                    =            Passi per la prenotazione            =
                    =====================================================-->

                    <div class="col-md-9">

                        <div class="product-grid-list">
                            <div class="row mt-30">
                                <div class="col-sm-12 col-lg-4 col-md-6">
                                    <!-- product card -->
                                    <div class="product-item bg-light">
                                        <div class="card">
                                            <div class="thumb-content">
                                                <!-- <div class="price">$200</div> -->

                                                <img class="card-img-top img-fluid" src="../template/images/products/cercaLibro.png" alt="Card image cap">

                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title">1 - Cerca</h4>

                                                <p class="card-text">Cerca il tuo libro tra i disponibili nelle biblioteche più vicine.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-lg-4 col-md-6">
                                    <!-- product card -->
                                    <div class="product-item bg-light">
                                        <div class="card">
                                            <div class="thumb-content">
                                                <!-- <div class="price">$200</div> -->

                                                <img class="card-img-top img-fluid" src="../template/images/products/prenotaLibro.png" alt="Card image cap">

                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title">2 - Prenota il prestito</h4>

                                                <p class="card-text">Prenota il prestito del tuo libro online, bastano pochi click.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-lg-4 col-md-6">
                                    <!-- product card -->
                                    <div class="product-item bg-light">
                                        <div class="card">
                                            <div class="thumb-content">
                                                <!-- <div class="price">$200</div> -->

                                                <img class="card-img-top img-fluid" src="../template/images/products/ritiraBiblioteca.png" alt="Card image cap">

                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title">3 - Ritira</h4>

                                                <p class="card-text">Dirigiti verso la biblioteca e ritira il tuo libro.</p>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--====================================================
                    =               Ultime Novità               =
                    =====================================================-->

                    <div class="col-md-12">
                        <div class="category-search-filter">
                            <div class="row">
                                <div class="col-md-6">
                                    <strong>Ultime Novità</strong>

                                </div>

                            </div>
                        </div>
                        <div class="product-grid-list">
                            <div class="row mt-30">
                                <div class="col-sm-12 col-lg-4 col-md-6">
                                    <!-- product card -->
                                    <div class="product-item bg-light">
                                        <div class="card">
                                            <div class="thumb-content">
                                                <!-- <div class="price">$200</div> -->
                                                <a href="">
                                                    <img class="card-img-top img-fluid" src="../template/images/products/products-1.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">

                                                    <li class="list-inline-item">
                                                        <i class="fa fa-calendar"></i> 26th December
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>

                                            </div>
                                        </div>
                                    </div>



                                </div>
                                <div class="col-sm-12 col-lg-4 col-md-6">
                                    <!-- product card -->
                                    <div class="product-item bg-light">
                                        <div class="card">
                                            <div class="thumb-content">
                                                <!-- <div class="price">$200</div> -->
                                                <a href="">
                                                    <img class="card-img-top img-fluid" src="../template/images/products/products-2.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">Study Table Combo</a></h4>
                                                <ul class="list-inline product-meta">

                                                    <li class="list-inline-item">
                                                        <i class="fa fa-calendar"></i> 26th December
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>

                                            </div>
                                        </div>
                                    </div>



                                </div>
                                <div class="col-sm-12 col-lg-4 col-md-6">
                                    <!-- product card -->
                                    <div class="product-item bg-light">
                                        <div class="card">
                                            <div class="thumb-content">
                                                <!-- <div class="price">$200</div> -->
                                                <a href="">
                                                    <img class="card-img-top img-fluid" src="../template/images/products/products-3.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">

                                                    <li class="list-inline-item">
                                                        <i class="fa fa-calendar"></i> 26th December
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>

                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="footer.jsp" %>

        <script>

            $(document).ready(setTimeout(function () {
                $("#someThing").hide();
            }, 3000));

            

        </script>
    </body>
</html>
