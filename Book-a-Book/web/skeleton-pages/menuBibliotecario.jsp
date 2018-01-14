<%-- 
    Document   : menuBibliotecario
    Created on : 13-gen-2018, 22.43.58
    Author     : salva
--%>

<%@page import="core.entities.Bibliotecario"%>

<%    if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo().equals("Responsabile")) {
%> 
<li>
    <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads (Biblioteca Resp)</a></li>

<li>
    <a href="../profilo/profiloPersonale-Bibliotecario.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i>Modifica Dati </a>
</li>

<li>
    <a href="../profilo/modifica-password.jsp?tipo=bibliotecario"> Modifica Password </a>
</li>
<% if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getStatus().equals("Attiva")) {
%>

<li>
    <a href="../registrazione/registrazione-scelta?tipo=bibliotecario"><i class="fa fa-pencil"></i> Registra Dipendente</a>
</li>

<li>
    <a href="../profilo/rimozione-account?tipo=richiesta&isil=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil()%>&idAdmin=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getAdmin().getId()%>"><i class="fa fa-trash"></i>Richiedi Rimozione Biblioteca</a>
</li>


<% }%>

<% } else if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo().equals("Dipendente")) {%>

<li>
    <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads (BibliotecaDip)</a></li>
<li>
    <a href="../profilo/profiloPersonale-Bibliotecario.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i>Modifica Dati </a>
</li>

<li>
    <a href="../profilo/modifica-password.jsp?tipo=bibliotecario"> Modifica Password </a>
</li>

<% } %>