<%@page import="core.entities.Bibliotecario"%>
<%@page import="core.entities.Admin"%>
<%@page import="core.entities.Biblioteca"%>
<%@page import="core.entities.Persona"%>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <nav class="navbar navbar-expand-lg  navigation">
                    <a class="navbar-brand" href="../skeleton-pages/index.jsp">
                        <img src="../template/images/logo-with-name.png" alt="" height="38">
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto main-nav ">
                            <li class="nav-item active">
                                <a class="nav-link" href="../skeleton-pages/index.jsp">Home</a>
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
                                if ((Persona) request.getSession().getAttribute("persona") == null && (Bibliotecario) request.getSession().getAttribute("bibliotecario") == null && (Admin) request.getSession().getAttribute("admin") == null ) {%>
                            <li class="nav-item">
                                <a class="nav-link login-button" href="../autenticazione/login.jsp">Accedi</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link add-button" href="../registrazione/scelta-registrazione.jsp"><i class="fa fa-plus-circle"></i> Registrati</a>
                            </li>

                            <% } else if ((Persona) request.getSession().getAttribute("persona") != null) {
                            %>
                            <li class="nav-link"> Benvenuto,</li>
                            <li class="nav-item dropdown dropdown-slide" style=" display: inline;">
                                <a class="nav-link" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%=((Persona) request.getSession().getAttribute("persona")).getNome()%><span> <i class="fa fa-angle-down"></i></span>
                                </a>
                                <!-- Dropdown list -->
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="../profilo/profiloPersonale-Utente.jsp">Profilo Utente</a>
                                    <a class="dropdown-item" href="#">Impostazioni</a>
                                    <a class="dropdown-item" href="<%=application.getContextPath()%>/autenticazione/logout">Logout</a>
                                </div>
                            </li>
                            <%   } else if ((Bibliotecario) request.getSession().getAttribute("bibliotecario") != null) {

                            %> <li class="nav-link"> Benvenuto,</li>
                            <li class="nav-item dropdown dropdown-slide" style=" display: inline;">
                                <a class="nav-link" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <%=((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getNome()%><span> <i class="fa fa-angle-down"></i></span>
                                </a>
                                <!-- Dropdown list -->
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="../profilo/profiloPersonale-Bibliotecario.jsp">Profilo Biblioteca</a>
                                    <a class="dropdown-item" href="#">Impostazioni</a>
                                    <a class="dropdown-item" href="<%=application.getContextPath()%>/autenticazione/logout">Logout</a>
                                </div>
                            </li>
                            
                            <%   } else if ((Admin) request.getSession().getAttribute("admin") != null) {

                            %> <li class="nav-link"> Ciao,</li>
                            <li class="nav-item dropdown dropdown-slide" style=" display: inline;">
                                <a class="nav-link" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                   <%=((Admin) request.getSession().getAttribute("admin")).getNome() %><span> <i class="fa fa-angle-down"></i></span>
                                </a>
                                <!-- Dropdown list -->
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="../profilo/profiloPersonale-Admin.jsp">Profilo Admin</a>
                                    <a class="dropdown-item" href="#">Impostazioni</a>
                                    <a class="dropdown-item" href="<%=application.getContextPath()%>/autenticazione/logout">Logout</a>
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