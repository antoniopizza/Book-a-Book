<%-- 
    Document   : index
    Created on : 14-dic-2017, 15.59.47
    Author     : mirko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String nomePagina = "Home";%>
    <%@include file="head.jsp" %>
    <body class="body-wrapper">
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <nav class="navbar navbar-expand-lg  navigation">
                            <a class="navbar-brand" href="index.jsp">
                                <img src="../template/images/logo-with-name.png" alt="" height="38">
                            </a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav ml-auto main-nav ">
                                    <li class="nav-item active">
                                        <a class="nav-link" href="index.jsp">Home</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Informazioni</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Aiuto</a>
                                    </li>

                                </ul>
                                <ul class="navbar-nav ml-auto mt-10">
                                    <li class="nav-item">
                                        <a class="nav-link login-button" href="index.jsp">Accedi</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link add-button" href="#"><i class="fa fa-plus-circle"></i> Registrati</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </section>

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
                        <!-- Advance Search -->
                        <div class="advance-search">
                            <form action="#">
                                <div class="row">
                                    <!-- Store Search -->
                                    <div class="col-lg-3 col-md-12">
                                        <select class="form-control mb-2 mr-sm-2 mb-sm-0">
                                            <option>Titolo</option>
                                            <option>Autore</option>
                                            <option>Editore</option>
                                            <option>ISBN</option>
                                        </select>
                                    </div>
                                    <div class="col-lg-9 col-md-12">
                                        <div class="block d-flex">
                                            <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="search" placeholder="Cerca il tuo libro">
                                            <!-- Search Button -->
                                            <button class="btn btn-main">SEARCH</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>

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
                        <div class="sidebar">
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <li>
                                        <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> Biblioteche</a></li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"><i class="fa fa-bookmark-o"></i> Novità</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="category-search-filter">
                            <div class="row">
                                <div class="col-md-6">
                                    <strong>Short</strong>
                                    <select>
                                        <option>Most Recent</option>
                                        <option value="1">Most Popular</option>
                                        <option value="2">Lowest Price</option>
                                        <option value="4">Highest Price</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <div class="view">
                                        <strong>Views</strong>
                                        <ul class="list-inline view-switcher">
                                            <li class="list-inline-item">
                                                <a href="javascript:void(0);"><i class="fa fa-th-large"></i></a>
                                            </li>
                                            <li class="list-inline-item">
                                                <a href="javascript:void(0);"><i class="fa fa-reorder"></i></a>
                                            </li>
                                        </ul>
                                    </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-1.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Electronics</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-2.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">Study Table Combo</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Furnitures</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-3.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Electronics</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-1.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Electronics</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-2.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">Study Table Combo</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Furnitures</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-3.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Electronics</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-1.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Electronics</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-2.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">Study Table Combo</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Furnitures</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
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
                                                    <img class="card-img-top img-fluid" src="images/products/products-3.jpg" alt="Card image cap">
                                                </a>
                                            </div>
                                            <div class="card-body">
                                                <h4 class="card-title"><a href="">11inch Macbook Air</a></h4>
                                                <ul class="list-inline product-meta">
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-folder-open-o"></i>Electronics</a>
                                                    </li>
                                                    <li class="list-inline-item">
                                                        <a href=""><i class="fa fa-calendar"></i>26th December</a>
                                                    </li>
                                                </ul>
                                                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, aliquam!</p>
                                                <div class="product-ratings">
                                                    <ul class="list-inline">
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item selected"><i class="fa fa-star"></i></li>
                                                        <li class="list-inline-item"><i class="fa fa-star"></i></li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>



                                </div>
                            </div>
                        </div>
                        <div class="pagination justify-content-center">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                    </li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item active"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
