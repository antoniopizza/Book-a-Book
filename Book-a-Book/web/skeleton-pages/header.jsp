<%@page import="core.entities.Biblioteca"%>
<%@page import="core.entities.Persona"%>
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
                            <%
                                if ((Persona) request.getSession().getAttribute("persona") == null && (Biblioteca) request.getSession().getAttribute("biblioteca") == null) {%>
                            <li class="nav-item">
                                <a class="nav-link login-button" href="index.jsp">Accedi</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link add-button" href="#"><i class="fa fa-plus-circle"></i> Registrati</a>
                            </li>

                            <% } else if ((Persona) request.getSession().getAttribute("persona") != null) {
                            %>
                            <li class="nav-link"> Benvenuto,</li>
                            <li class="nav-item dropdown dropdown-slide" style=" display: inline;">
                                <a class="nav-link" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%=((Persona) session.getAttribute("persona")).getNome()%><span> <i class="fa fa-angle-down"></i></span>
                                </a>
                                <!-- Dropdown list -->
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="visualizzaProfiloPersonale.jsp">Profilo Utente</a>
                                    <a class="dropdown-item" href="#">Impostazioni</a>
                                    <a class="dropdown-item" href="#">Logout</a>
                                </div>
                            </li>
                            <%   } else if ((Biblioteca) request.getSession().getAttribute("biblioteca") != null) {

                            %> <li class="nav-link"> Benvenuto,</li>
                            <li class="nav-item dropdown dropdown-slide" style=" display: inline;">
                                <a class="nav-link" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                   <%=((Biblioteca) session.getAttribute("biblioteca")).getNome()%><span> <i class="fa fa-angle-down"></i></span>
                                </a>
                                <!-- Dropdown list -->
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="visualizzaProfiloPersonale.jsp">Profilo Biblioteca</a>
                                    <a class="dropdown-item" href="#">Impostazioni</a>
                                    <a class="dropdown-item" href="#">Logout</a>
                                </div>
                            </li>
                            
                            <%                               }
                            %>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</section>