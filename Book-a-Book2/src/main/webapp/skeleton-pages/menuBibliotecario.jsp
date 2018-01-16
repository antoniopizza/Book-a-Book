<%-- 
    Document   : menuBibliotecario
    Created on : 13-gen-2018, 22.43.58
    Author     : salva
--%>

<%@page import="core.entities.Bibliotecario"%>

<%    if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo().equals("Responsabile")) && !(((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Rimuovere"))) {
%> 
    <li>
        <a href="<%=application.getContextPath()%>/libri/aggiungi-libro.jsp"><i class="fa fa-plus-square"></i>Aggiungi Libro (Biblioteca Resp)</a></li>

    <li>    
        <a href="../profilo/modifica-password.jsp?tipo=bibliotecario"> Modifica Password </a>
    </li>

            <% if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getStatus()).equalsIgnoreCase("In Sospeso")) { %>
            <li>
                <a href="../profilo/profiloPersonale-Bibliotecario.jsp?status=sospeso"> Visualizza Stato Richiesta Registrazione </a>
            </li>
            <%
                } else if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getStatus()).equalsIgnoreCase("Attiva")) {
            %>

                        <li>
                            <a href="../profilo/profiloPersonale-Bibliotecario.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i>Modifica Dati </a>
                        </li>

                        <li>
                            <a href="../registrazione/registrazione-scelta?tipo=bibliotecario"><i class="fa fa-pencil"></i> Registra Dipendente</a>
                        </li>

                        <li>
                            <a href="../profilo/rimozione-account?tipo=richiesta&isil=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil()%>&idAdmin=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getAdmin().getId()%>&email=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getEmail() %>">
                                <i class="fa fa-trash"></i>Richiedi Rimozione Biblioteca</a>
                        </li>


            <% }

  }

else if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo().equals("Dipendente")) && !(((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Rimuovere"))) {%>

        <li>
            <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads (BibliotecaDip)</a></li>
        <li>
            <a href="../profilo/profiloPersonale-Bibliotecario.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i>Modifica Dati </a>
        </li>

        <li>
            <a href="../profilo/modifica-password.jsp?tipo=bibliotecario"> Modifica Password </a>
        </li>

<% } else if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Rimuovere")) { %>

            <li>
                <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads (BibliotecaDip)</a></li>


            <li>
                <a href="../profilo/profiloPersonale-Bibliotecario.jsp"> Visualizza Stato Richiesta Rimozione </a>
            </li>

<% } %> 