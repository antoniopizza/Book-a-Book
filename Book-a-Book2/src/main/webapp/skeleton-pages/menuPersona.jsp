<%-- 
    Document   : menuPersona
    Created on : 13-gen-2018, 22.36.49
    Author     : salva
--%>

<%@page import="core.entities.Persona"%>

<li>
    <a href="../profilo/profiloPersonale-Utente.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i> Modifica Dati </a>
</li>
<li>
    <a href="../profilo/modifica-password.jsp?tipo=persona"><i class="fa fa-pencil"></i> Modifica Password </a>
</li>

<li>
    <a href="../profilo/rimozione-account?tipo=persona&email=<%= ((Persona) request.getSession().getAttribute("persona")).getAccount().getEmail()%>"><i class="fa fa-trash"></i>Rimuovi Account</a>
</li>

<li>
    <a href="<%=application.getContextPath()%>/prenotazioni/ricerca-prenotazioni">
        <i class="fa fa-list"></i>Visualizza Prenotazioni</a>
</li>