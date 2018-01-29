<%@page import="java.util.Collection"%>
<%@page import="core.entities.Persona"%>
<%@page import="core.entities.Bibliotecario"%>
<%@page import="java.util.List"%>
<%@page import="core.entities.Autore"%>
<%@page import="core.entities.Libro"%>
<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->

<%

    Libro book;
    book = (Libro) request.getAttribute("libro");
    String nomePagina;
    String message = (String) request.getAttribute("message");
    if (!message.equals("correct")) {
        nomePagina = "Libro non trovato";
    } else {
        nomePagina = book.getTitolo();
    }

    Collection<Biblioteca> bibliotecheDisponibili = (Collection<Biblioteca>) request.getAttribute("biblioteche");
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
                            <br>
                            <h3 class="widget-header"></h3>

                            <%
                                if (!message.equals("correct")) {
                            %>
                            <h3><%=message%></h3> <!-- Se c'è un errore, stampa il messaggio -->
                            <%
                            } else {
                            %>
                            <h3></h3>


                            <div class="row">
                                <div class="col-md-12">
                                    <h1 class="product-title"><%= book.getTitolo()%></h1>
                                </div>
                            </div>
                            <br/>
                            <div class="row">

                                <!-- Colonna titolo e copertina libro-->
                                <div class="col-md-6">
                                    <div class="product-details">

                                        <div class="product-meta">

                                        </div>
                                        <div>

                                            <div class="carousel-inner">
                                                <div class="carousel-item active" style="width: 300px; height: 400px;">
                                                    <img src="<%= book.getPathFoto()%>" alt="Copertina libro" style="width: 100%; height: 100%;">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="content">

                                        </div>
                                    </div>
                                </div>
                                <!-- Colonna laterale con dettagli libro-->
                                <div class="col-md-6">
                                    <div class="sidebar">

                                        <!-- User Profile widget -->
                                        <div class="widget user">
                                            <h3>Dettagli libro:</h3>
                                            <table width="100%">
                                                <tr>
                                                    <td><strong>ISBN:</strong></td>
                                                    <td align="right"><p><%= book.getIsbn()%></p></td>
                                                </tr>
                                                <%
                                                    List<Autore> autori = book.getAutori();
                                                    int i = 0;
                                                    for (Autore aut : autori) {
                                                %>
                                                <tr>
                                                    <%
                                                        if (i == 0) {
                                                    %>
                                                    <td><strong>Autori:</strong></td>
                                                    <%
                                                    } else {
                                                    %>
                                                    <td>&nbsp;</td>
                                                    <%
                                                        }
                                                    %>
                                                    <td align="right">
                                                        <p><%= aut.getNome()%></p>
                                                    </td>
                                                </tr>
                                                <%
                                                        i++;
                                                    }
                                                %>
                                                <tr>
                                                    <td><strong>Editore:</strong></td>
                                                    <td align="right"><p><%= book.getEditore()%></p></td>
                                                </tr>                                                
                                            </table>

                                            <%
                                                if ((Bibliotecario) request.getSession().getAttribute("bibliotecario") != null) {
                                                    //SEZIONE VISUALIZZATA SOLO SE BIBLIOTECARIO LOGGATO
                                            %>
                                            <br/>
                                            <h3>Operazioni:</h3>
                                            <br/>
                                            <div class="row">
                                                <div class="col-md-12" align="center">                                                    

                                                    <a class="btn btn-main" title="gestisci copie" href="gestisci-copie?isbn=<%=book.getIsbn()%>">
                                                        <i class="fa fa-book"></i>
                                                    </a>

                                                    <a class="btn btn-success" data-toggle="modal" data-target="#person-modal" title="prenota libro" href="#">
                                                        <i class="fa fa-pencil-square-o"></i>
                                                    </a>

                                                    <a class="btn btn-danger" title="elimina libro" ><i class="fa fa-trash"></i></a>

                                                </div>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!-- div descrizione libro -->
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="tab-content" id="pills-tabContent">
                                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                                            <h3 class="tab-title">Descrizione libro:</h3>
                                            <p><%= book.getDescrizione()%></p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%
                                if ((Bibliotecario) request.getSession().getAttribute("bibliotecario") == null) {
                                    //SEZIONE VISUALIZZATA SOLO SE BIBLIOTECARIO NON LOGGATO
                                    if (!bibliotecheDisponibili.isEmpty()) {
                            %>
                            <!-- div tabella biblioteche -->                                                       
                            <div class="row table-responsive">

                                <form id="form-prenotazione-cliente" action="<%= application.getContextPath()%>/prenotazioni/prenotazione-libro ">
                                    <div class="col-lg-12">
                                        <h3 class="widget-header">Biblioteche che hanno il libro che cerchi</h3>
                                        <table class="table table-hover">
                                            <thead align="center">
                                                <tr>
                                                    <th>
                                                        ISIL
                                                    </th>
                                                    <th>
                                                        Nome
                                                    </th>
                                                    <th>
                                                        Indirizzo
                                                    </th>
                                                    <th>
                                                        Copie disponibili
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody align="center">

                                                <% if (bibliotecheDisponibili != null) {

                                                        for (Biblioteca b : bibliotecheDisponibili) {%>

                                                <tr>
                                                    <td><%= b.getIsil()%></td>
                                                    <td><%= b.getNome()%></td>
                                                    <td><%= b.getIndirizzo().getVia() + " " + b.getIndirizzo().getCivico() + " " + b.getIndirizzo().getCitta() + " " + b.getIndirizzo().getProvincia()%></td>
                                                    <td><input type="radio" name="isil" class="isil-scelta" value="<%= b.getIsil()%>"/> </td>
                                                </tr>

                                                <%    }

                                                    }%>
                                            </tbody>

                                        </table>
                                        <input type="hidden" name="isbn" value="<%= book.getIsbn()%>">
                                        <% if (session.getAttribute("persona") != null) { %>
                                        <button type="submit" class="btn btn-main">Prenota</button>
                                        <% } else { %>
                                        <button type="button" data-toggle="modal" data-target="#non-logged-modal" class="btn btn-main">Prenota</button>
                                        <% } %>
                                    </div>

                                </form>
                            </div>                           
                            <%
                            } else { %>
                            <div class="alert alert-warning">
                                <p>Il libro non è momentaneamente disponibile in alcuna biblioteca</p>
                            </div>

                            <%
                                    }
                                }
                            %>


                        </div>

                        <% }%>



                    </div>
                </div>

                <!-- Modal errore -->
                <div id="error-modal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">                                
                                <h4 class="modal-title">Attenzione !</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p id="modal-error-messsage">Nessuna biblioteca selezionata</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>

                    </div>
                </div>

                <!-- Modal errore -->
                <div id="non-logged-modal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">                                
                                <h4 class="modal-title">Attenzione !</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <p id="modal-error-messsage"> Bisogna effettuare il login per prenotare un libro</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>

                    </div>
                </div>

                <% if ((Bibliotecario) request.getSession().getAttribute("bibliotecario") != null) {%>
                <!-- Modal Persona -->
                <div id="person-modal" class="modal fade" role="dialog">
                    <form action="<%= application.getContextPath()%>/prenotazioni/prenotazione-libro">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">                                
                                    <h4 class="modal-title">Inserisci dati persona</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <div class="modal-body">


                                    <label>Nome</label>
                                    <input name="nome" class="form-control" type="text" />

                                    <label>Cognome</label>
                                    <input name="cognome" type="text" class="form-control" />

                                    <label>Numero Documento</label>
                                    <input name="numDoc" type="text" class="form-control"/>

                                    <label>Via</label>
                                    <input name="via" type="text" class="form-control"/>
                                    <label>Città</label>
                                    <input name="citta" type="text" class="form-control"/>                                    
                                    <div class="form-row">
                                        <div class="col-4">
                                            <label>CAP</label>
                                            <input name="cap" type="text" class="form-control"/>
                                        </div>
                                        <div class="col-4">
                                            <label>Numero civico</label>
                                            <input name="civico" type="text" class="form-control"/>
                                        </div>
                                        <div class="col">
                                            <label>Provincia</label>
                                            <input name="provincia" type="text" class="form-control"/>                                    
                                        </div>
                                    </div>

                                    <label>Telefono</label>
                                    <input name="telefono" type="text" class="form-control"/>  
                                    <input type="hidden" value="<%= book.getIsbn()%>" name="isbn" />
                                    <input type="hidden" value="<%=  ((Bibliotecario) session.getAttribute("bibliotecario")).getBiblioteca().getIsil()%>" name="isil" />
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-default">Prenota</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>

                <% }%>

        </section>                 

        <%@include file="../skeleton-pages/footer.jsp" %>
        <script>
            var errorCounter = 0;
            $("img").on("error", function () {
                $(this).attr("src", "images/defaultBook.png");
            });

            $(document).on('input:radio[name="isil-scelta"]', "click", function () {

                alert($(this).val());

            });


            $('#form-prenotazione-cliente').submit(function () {
                if ($('input:radio', this).is(':checked')) {
                    // everything's fine...
                } else {

                    if (errorCounter == 3) {
                        $("#modal-error-messsage").text("Ma sei idiota ?");
                    } else if (errorCounter == 4) {
                        $("#modal-error-messsage").text("Ma veramente fai ?");
                    } else if (errorCounter >= 5) {
                        $("#modal-error-messsage").text("Scusa ma da dove minchia lo vuoi prenotare sto libro ?");
                    }

                    errorCounter++;
                    $("#error-modal").modal('toggle');
                    return false;
                }
            });


        </script>
    </body>
</html>
