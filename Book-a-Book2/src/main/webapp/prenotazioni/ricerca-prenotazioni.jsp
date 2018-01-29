<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="core.entities.Bibliotecario"%>
<%@page import="core.entities.Utente"%>
<%@page import="core.entities.Autore"%>
<%@page import="core.entities.Persona"%>
<%@page import="core.entities.Libro"%>
<%@page import="core.entities.Prenotazione"%>
<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->

<% String nomePagina = "ricerca-prenotazioni";
    String pathPrenotazione = application.getContextPath() + "/prenotazioni/";
    String pathServlet = application.getContextPath();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="../skeleton-pages/head.jsp" %>
    <body>
        <%@include file="../skeleton-pages/header.jsp" %>

        <section class="dashboard section">
            <div class="container">
                <div class="row">

                    <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0">
                        <div class="sidebar">
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
                                    <%if (session.getAttribute("bibliotecario") != null) {
                                    %>
                                    <%@include file="../skeleton-pages/menuBibliotecario.jsp" %>
                                    <% } else if (session.getAttribute("persona") != null) {%>
                                    <%@include file="../skeleton-pages/menuPersona.jsp" %>
                                    <% } else { %>
                                    <li>
                                        <a href="dashboard-my-ads.html"> Biblioteche</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"> Novità</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"> Autori</a>
                                    </li>
                                    <li>
                                        <a href="dashboard-favourite-ads.html"> Popolari</a>
                                    </li>
                                    <% }
                                    %>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0">

                        <div class="widget dashboard-container my-adslist">
                            <%@include file="../skeleton-pages/searchbar.jsp" %>

                            <h3 class="widget-header"></h3>
                            <%
                                int i;
                                String message = (String) request.getAttribute("message");
                                Collection<Prenotazione> lista = null;
                                lista = (Collection<Prenotazione>) request.getAttribute("lista");

                            %>
                            <h3>Cerca la tua prenotazione</h3>
                            <div class="advance-search">
                                <form id="cercaPren" name = "cercaPren" action="prenotazioni-per-criterio" method="get">
                                    <div class="row">
                                        <!-- Store Search -->

                                        <div class="col-lg-2 col-md-12">
                                            <select  id="select" name="criterio"class="form-control mb-2 mr-sm-2 mb-sm-0">
                                                <% if (session.getAttribute("bibliotecario") != null) { %>
                                                <option id="idUtente" value="utente">Per id utente</option>
                                                <% } %>
                                                <option id="idCodice" value="codice">Per codice</option>
                                                <option id="dataRitiro" value="ritiro">Per data di ritiro</option>
                                                <option id="dataCreazione" value="creazione">Per data di creazione</option>
                                                <option id="dataScadenza" value="scadenza">Per data di scadenza</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-7 col-md-12">
                                            <div  class="block d-flex">
                                                <input style="display:none" type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="searchData" name="valore-data" placeholder="Cerca la tua prenotazione">
                                            </div>
                                            <div  class="block d-flex">
                                                <input  type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="searchPren" name="valore-codice" placeholder="Cerca la tua prenotazione">

                                            </div>
                                        </div>
                                        <div class="col-lg-2 col-md-12">
                                            <div class="block d-flex">
                                                <!-- Search Button -->
                                                <button type="submit" class="btn btn-main" >CERCA</button>
                                            </div>
                                        </div>
                                        <div class="col-lg-1"></div>
                                    </div>
                                </form>
                                <div class="alert alert-danger" style="display: none" id="search-error"></div>
                            </div>

                            <% if (message.equalsIgnoreCase("Empty") || lista == null) { %>
                            <div class="alert alert-danger">
                                <p  id="erroreSearchPren">Nessun risultato corrispondente al criterio scelto.</p>
                            </div>
                            <% } else { %>
                            </br>

                            <div class = "row"> 
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0" align="center">
                                    <div class = "row"> 
                                        <div class="col-md-10 offset-md-1 col-lg-1 offset-lg-0" align="center">
                                            Codice </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            Titolo </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            Status</div>
                                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0" align="center">
                                            Scadenza</div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center"></div>
                                    </div>
                                </div>
                                <br><br>
                                <%
                                    if (lista != null && !lista.isEmpty()) {
                                        for (Prenotazione p : lista) {
                                            String data = "" + p.getDataScadenza().get(Calendar.DAY_OF_MONTH) + "-" + (p.getDataScadenza().get(Calendar.MONTH) + 1) + "-" + p.getDataScadenza().get(Calendar.YEAR) + "";
                                %>
                                <div class="col-md-10 offset-md-1 col-lg-12 offset-lg-0" align="center">
                                    <div class = "row"> 
                                        <div class="col-md-10 offset-md-1 col-lg-1 offset-lg-0" align="center"> <%= p.getId()%> </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center"> <%= p.getCopia().getLibro().getTitolo()%> </div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center"> <%= p.getStatus()%> </div>
                                        <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0" align="center"> <%= data%></div>
                                        <div class="col-md-10 offset-md-1 col-lg-3 offset-lg-0" align="center">
                                            <div class="form-group">
                                                <a class="btn btn-main" href="dettaglio-prenotazioni?id=<%=p.getId()%>">DETTAGLI</a> 
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                                <%      } %>
                                <%    } %>
                                <%  }%>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    $(document).ready(function () {

        var str = "Per codice";

        $("#searchData").hide();
        $("#searchData").datepicker({dateFormat: "dd-mm-yy",
            onSelect: function () {
                selectedDate = $.datepicker.formatDate("dd-mm-yy", $(this).datepicker('getDate'));
            }
        });
        $("#select").change(function () {

            $("#select option:selected").each(function () {
                str = $(this).text();
                console.log(str);
            });
            if (str == "Per data di ritiro" || str == "Per data di scadenza" || str == "Per data di creazione") {
                $("#searchPren").val("");
                $("#searchData").val("");
                $("#searchPren").hide();
                $("#searchData").show();
            } else if (str == "Per id utente" || str == "Per codice") {
                $("#searchPren").val("");
                $("#searchData").val("");
                $("#searchData").hide();
                $("#searchPren").show();
            }
        });
        $("#selected").change(function () {
            $("#search").val("");
        });

        $("#cercaPren").submit(function () {
            $("#search-error").html(" ");
            $("#search-error").hide();
            var bool = true;
            var searchPren = $("#searchPren").val();           
            var searchData = $("#searchData").val();
            console.log("searchData "+searchData);
            console.log("searchPren "+searchPren);
            console.log("str "+str);

            var regex = /^[0-9]+$/;

            if (searchPren == "" && searchData == "") {
                bool = false;
                $("#searchData").focus();
                $("#searchPren").focus();
                $("#search-error").text("Il campo non può essere vuoto.");
                $("#search-error").show();                
            } else if (str == "Per id utente" && searchPren != "") {
                if (!searchPren.toString().match(regex)) {
                    $("#searchPren").focus();
                    $("#search-error").text("Il campo può contenere solo numeri.");
                    bool = false;
                }
            } else if (str == "Per codice" && searchPren != "") {
                if (!searchPren.toString().match(regex)) {
                    $("#searchPren").focus();
                    $("#search-error").text("Il campo può contenere solo numeri.");
                    $("#search-error").show();
                    bool = false;
                }
            } else {
                if(!searchData.toString().match(/^(([1-9]|0[1-9]|[12]\d|3[01])-([1-9]|0[1-9]|1[0-2])-[12]\d{3})$/)){
                    bool = false;
                    $("#searchData").focus();
                    $("#search-error").text("Formato della data errato");
                    $("#search-error").show();
                }
            }
            return bool;
        });

    });


</script>

<%@include file="../skeleton-pages/footer.jsp" %>
</body>
</html>
