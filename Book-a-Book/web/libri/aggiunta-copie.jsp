<%@page import="core.entities.Autore"%>
<%@page import="core.entities.Libro"%>
<% String nomePagina = "Aggiunta Copie";
    Libro book = (Libro) session.getAttribute("libro");
    int counter = 0;
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

                    <div class="col-md-10 offset-md-1 col-lg-2 offset-lg-0">
                        <div class="sidebar">
                            <!-- Dashboard Links -->
                            <div class="widget user-dashboard-menu">
                                <ul>
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
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-10 offset-md-1 col-lg-10 offset-lg-0">

                        <div class="widget dashboard-container my-adslist">
                            <h3 class="widget-header">Cerca un libro</h3>
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header">Aggiungi Copie</h3>
                            <div class="row">                                
                                <div class="col-lg-2">
                                    <img style="width: 100%;height: auto" src="<%=book.getPathFoto()%>" alt="image description">
                                </div>                                
                                <div class="col-lg-4">
                                    <h3 class="title"><%= book.getTitolo()%></h3>
                                    <p class="location"><strong>ISBN: </strong><%= book.getIsbn()%></p>
                                    <% for (Autore a : book.getAutori()) { %>

                                    <p class="add-id">

                                        <% if (counter == 0) { %>
                                        <strong>Autore: </strong>
                                        <% }%>

                                        <%=a.getNome()%>

                                    </p>
                                    <% }%>
                                    <p><strong>Editore: </strong><%= book.getEditore()%></p>
                                </div>
                                <div class="col-lg-6">
                                    <form id="form-copie" action="aggiunta-copie">
                                        <div class="form-row form-group" id="form-start-point">
                                            <div class="col-6">
                                                <label>In quante posizioni sono disposte le copie ?</label>
                                            </div>
                                            <div class="col">
                                                <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 book-data" name="n-scaffali" id="n-scaffali" />
                                            </div>                                            
                                        </div>

                                        <div class="form-row form-group">
                                            <div class="col-6"></div>
                                            <div class="col">
                                                <button type="submit"class="btn btn-main" id="subimt">INSERISCI</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="../skeleton-pages/footer.jsp" %>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#n-scaffali").keyup(function () {

                    $(".dati-scaffale").remove();

                    var nScaffali = $("#n-scaffali").val();
                    if (nScaffali.toString().match(/[0-9]/) && nScaffali <= 20) {
                        for (var i = 0; i < nScaffali; i++) {
                            var container = $("<div></div>").attr({id: "dati-scaffale-" + i}).addClass("form-group dati-scaffale");

                            var legend = $("<h6></h6>").text("Dati scaffale").addClass("widget-header");


                            // elementi HTML per l'etichetta
                            var divEtichetta = $("<div></div>").addClass("form-row form-group");

                            var divLabelEtichetta = $("<div></div>").addClass("col-6");
                            var labelEtichetta = $("<label></label>").text("Etichetta");
                            divLabelEtichetta.append(labelEtichetta);

                            var divInputEtichetta = $("<div></div>").addClass("col");
                            var inputEtichetta = $("<input />").attr({type: "text", name: "etichetta-" + i, id: "etichetta-" + i}).addClass("form-control book-data");
                            divInputEtichetta.append(inputEtichetta)

                            divEtichetta.append(divLabelEtichetta, divInputEtichetta);

                            //elementi HTML per il numero di copie
                            var divNCopie = $("<div></div>").addClass("form-row form-group");

                            var divLabelNcopie = $("<div></div>").addClass("col-6");
                            var labelNcopie = $("<label></label>").text("Quante copie in questa posizione ?");
                            divLabelNcopie.append(labelNcopie);

                            var divInputNcopie = $("<div></div>").addClass("col");
                            var inputCopie = $("<input />").attr({type: "text", name: "n-copie-" + i, id: "n-copie-" + i}).addClass("form-control book-data n-copie");
                            divInputNcopie.append(inputCopie);

                            divNCopie.append(divLabelNcopie, divInputNcopie);

                            container.append(legend, divEtichetta, divNCopie);

                            $("#form-start-point").append(container);
                        }
                    }


                });


                $(document).on("keyup", ".n-copie", function () {


                    var idNcopie = $(this).attr("id").toString();
                    var numeroPosizione = idNcopie.substring(idNcopie.lastIndexOf("-") + 1);
                    $(".copy-details" + numeroPosizione).remove();

                    var nCopie = $(this).val();

                    if (nCopie.toString().match(/[0-9]/) && nCopie <= 20) {
                        for (var i = 0; i < nCopie; i++) {
                            var divCopia = $("<div></div>").addClass("form-row form-group").addClass("copy-details" + numeroPosizione);

                            var divLabelCopia = $("<div></div>").addClass("col-6");
                            var labelCopia = $("<label></label>").text("Codice della copia");
                            divLabelCopia.append(labelCopia);


                            var divInputCopia = $("<div></div>").addClass("col");
                            var inputCopia = $("<input />").attr({type: "text", name: "copia-" + numeroPosizione + "-" + i, id: "copia-" + numeroPosizione + "-" + i}).addClass("form-control book-data input-copia");
                            divInputCopia.append(inputCopia);

                            divCopia.append(divLabelCopia, divInputCopia);
                            //alert("#dati-scaffale-"+numeroPosizione);
                            $("#dati-scaffale-" + numeroPosizione).append(divCopia);
                        }
                    }
                });


                $("#form-copie").submit(function () {

                    $(".is-invalid").removeClass("is-invalid");
                    $(".invalid-feedback").remove();

                    var inputs = $(".book-data").toArray();
                    var invalidFeedback = $("<p></p>").addClass("invalid-feedback");
                    var ok = true;

                    var nScaffali = $("#n-scaffali").val();

                    if (nScaffali <= 0 || !nScaffali.toString().match(/[0-9]/)) {
                        ok = false;
                        $("#n-scaffali").addClass("is-invalid").after(invalidFeedback.text("Il libro deve essere collocato in almeno uno scaffale !").clone());
                    } else {
                        var inputNcopie = $(".n-copie").toArray();

                        for (var i = 0; i < inputNcopie.length; i++) {
                            if (inputNcopie[i].value.length == 0 || !inputNcopie[i].value.toString().match(/[0-9]/)) {
                                $(inputNcopie[i]).addClass("is-invalid").after(invalidFeedback.text("Uno scaffale deve contenere almeno una copia").clone());
                                ok = false;
                            } else {
                                $(inputNcopie[i]).addClass("is-valid");
                            }

                        }

                        for (var i = 0; i < inputs.length; i++) {
                            if (inputs[i].value.length == 0) {
                                $(inputs[i]).addClass("is-invalid").after(invalidFeedback.text("Campo obbligatorio").clone());
                                ok = false;
                            } else {
                                $(inputs[i]).addClass("is-valid");
                            }

                        }
                    }
                    return ok;

                });

            });
        </script>
    </body>
</html>
