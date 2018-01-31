<%-- 
    Document   : menuAdmin
    Created on : 13-gen-2018, 22.56.40
    Author     : salva
--%>
<%@page import="core.managers.ManagerRegistrazione"%>
<%@page import="core.entities.Admin"%>
<%@page import="java.util.List"%>
<%@page import="core.entities.Biblioteca"%>
<%
    List<Biblioteca> richieste = null;
                List<Biblioteca> richiesteR = null;
                if ((Admin) request.getSession().getAttribute("admin") != null) {
                    richieste = ((new ManagerRegistrazione()).visualizzaRichieste());
                    richiesteR = ((new ManagerRegistrazione()).visualizzaRichiesteRimozione());
                }
                
         %>       
<li>
    <a href="dashboard-my-ads.html"><i class="fa fa-user"></i> My Ads (Admin)</a></li>
<li>
    <a href="../profilo/visualizza-richieste.jsp"> Visualizza Richieste <span><%= richieste.size()%></span></a>
</li>
<li>
    <a href="../profilo/visualizza-richiesteRimozione.jsp"> Visualizza Richieste Eliminazione <span><%= richiesteR.size()%></span></a>
</li>
<li>
    <a href="../profilo/profiloPersonale-Admin.jsp?pagina=modifica"><i class="fa fa-file-archive-o"></i>Modifica Dati </a>
</li>

<li>
    <a href="../profilo/modifica-password.jsp?tipo=admin"> Modifica Password </a>
</li>