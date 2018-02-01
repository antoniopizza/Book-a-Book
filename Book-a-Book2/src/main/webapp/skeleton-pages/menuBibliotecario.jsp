<%-- 
    Document   : menuBibliotecario
    Created on : 13-gen-2018, 22.43.58
    Author     : salva
--%>

<%@page import="core.entities.Bibliotecario"%>

<%    if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo().equals("Responsabile")) && !(((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Rimuovere"))) {
%> 
<li>
    <a href="<%=application.getContextPath()%>/libri/aggiungi-libro.jsp"><i class="fa fa-plus-square"></i>Aggiungi Libro</a></li>

<li>    
    <a href="<%=application.getContextPath()%>/profilo/modifica-password.jsp?tipo=bibliotecario"> Modifica Password </a>
</li>

<% if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getStatus()).equalsIgnoreCase("In Sospeso")) { %>
<li>
    <a href="../profilo/profiloPersonale-Bibliotecario.jsp?status=sospeso"> Visualizza Stato Richiesta Registrazione </a>
</li>
<%
} else if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getStatus()).equalsIgnoreCase("Attiva")) {
%>

<li>
    <a href="<%=application.getContextPath()%>/profilo/profiloPersonale-Bibliotecario.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i>Modifica Dati </a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/registrazione/registrazione-scelta?tipo=bibliotecario"> Registra Dipendente</a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/utenti/cerca-dipendente"><i class="fa fa-pencil"></i> Cerca Dipendente</a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/prenotazioni/ricerca-prenotazioni">
        <i class="fa fa-list"></i>Visualizza Prenotazioni</a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/libri/visualizza-scaffali?isil=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil()%>">
        <i class="fa fa-archive"></i>Visualizza Scaffali</a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/profilo/rimozione-account?tipo=richiesta&isil=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil()%>&idAdmin=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getAdmin().getId()%>&email=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getAccount().getEmail()%>">
        <i class="fa fa-trash"></i>Richiedi Rimozione Biblioteca</a>
</li>



<% }

} else if ((((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getTipo().equals("Dipendente")) && !(((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Rimuovere"))) {%>


<li>
    <a href="<%=application.getContextPath()%>/libri/aggiungi-libro.jsp"><i class="fa fa-plus-square"></i>Aggiungi Libro</a></li>

<li>

<li>
    <a href="<%=application.getContextPath()%>/prenotazioni/ricerca-prenotazioni">
        <i class="fa fa-list"></i>Visualizza Prenotazioni</a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/libri/visualizza-scaffali?isil=<%= ((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getBiblioteca().getIsil()%>">
        <i class="fa fa-archive"></i>Visualizza Scaffali</a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/profilo/profiloPersonale-Bibliotecario.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i>Modifica Dati </a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/profilo/modifica-password.jsp?tipo=bibliotecario"> Modifica Password </a>
</li>

<% } else if (((Bibliotecario) request.getSession().getAttribute("bibliotecario")).getStatus().equals("Rimuovere")) {%>


<li>
    <a href="<%=application.getContextPath()%>/profilo/profiloPersonale-Bibliotecario.jsp"> Visualizza Stato Richiesta Rimozione </a>
</li>

<% }%> 