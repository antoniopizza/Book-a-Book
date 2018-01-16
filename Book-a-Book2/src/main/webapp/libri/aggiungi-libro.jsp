<!-- Questa pagina è lo scheletro per tutte le pagine da creare successivamente -->

<%
    String nomePagina = "Aggiunta Libro";
    String message = (String) request.getAttribute("message");

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
                                    <%@include file="../skeleton-pages/menuBibliotecario.jsp" %>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- main content -->
                    <div class="col-md-10 offset-md-1 col-lg-9 offset-lg-0">

                        <div class="widget dashboard-container my-adslist">
                            <h3 class="widget-header">Cerca un libro</h3>
                            <%@include file="../skeleton-pages/searchbar.jsp" %>
                            <br>
                            <h3 class="widget-header">Inserisci un libro</h3>

                            <div class="row">
                                <div class="col-lg-2"></div>
                                <div class="col-lg-8">
                                    <form action="aggiunta-libro" id="form-add-libro" >
                                        <div class="form-group">
                                            <label>ISBN</label>
                                            <input type="text" id="isbn" class="form-control mb-2 mr-sm-2 mb-sm-0" name="isbn">
                                        </div>
                                        <div class="form-group">
                                            <!-- Search Button -->
                                            <button type="button" id="verifica-isbn" class="btn btn-main">VERIFICA ISBN</button>
                                        </div>

                                        <p id="isbn-message" class="alert  <% if(message != null) { out.print("alert-danger");}%>">
                                            <%
                                                if (message != null) {
                                                    out.print(message);
                                                }

                                            %>
                                        </p>

                                        <label>Titolo</label>
                                        <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 book-data" name="titolo" id="titolo" disabled>

                                        <label>Editore</label>
                                        <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 book-data" name="editore" id="editore" disabled> 

                                        <label>Data pubblicazione</label>
                                        <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 book-data" name="data" id="data" disabled> 

                                        <label>Url copertina</label>
                                        <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 book-data" name="path_foto" id="path_foto" disabled> 

                                        <div class="form-group">
                                            <label for="comment">Descrizione</label>
                                            <textarea class="form-control book-data" style="height: auto" rows="5" id="descrizione" name="descrizione" disabled></textarea>
                                        </div>

                                        <fieldset class="form-group" style="display: none" id="autori">
                                            <legend class="widget-header">Autori</legend>
                                            <div class="form-row form-group">
                                                <div class="col-6">
                                                    <label>Quanti autori ha il libro ?</label>
                                                </div>
                                                <div class="col">
                                                    <input type="text"  name="n-autori" id="n-autori" class="form-control mb-2 mr-sm-2 mb-sm-0" />
                                                </div>
                                            </div>

                                            <div class="form-group" id="input-autori">

                                            </div>

                                        </fieldset>

                                        <div class="row">
                                            <div class="col-lg-12"></div>
                                        </div>
                                        <input type="hidden" name="ignore" id="ignore" />
                                        <div class="block d-flex">
                                            <!-- Search Button -->
                                            <button type="submit"class="btn btn-main" id="subimt" disabled>INSERISCI</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-lg-2">                                  
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </section>                 

        <%@include file="../skeleton-pages/footer.jsp" %>
        <script type="text/javascript">
            var ignore = true;
            $(document).ready(function () {

                $("#data").datepicker({dateFormat: 'dd-mm-yy'});

                $("#n-autori").keyup(function () {

                    $("#input-autori").html("");
                    $(".alert-danger").remove();

                    var nAutori = $("#n-autori").val();
                    if (nAutori.toString().match(/[0-9]/)) {
                        if (nAutori <= 10) {
                            for (var i = 0; i < nAutori; i++) {
                                var label = $("<label></label").text("Autore");
                                var autoreInput = $("<input/>").attr({type: "text", name: "autore"}).toggleClass("form-control mb-2 mr-sm-2 mb-sm-0 author-data");
                                $("#input-autori").append(label, autoreInput);
                            }
                        } else {
                            $("#autori").append($("<p></p>").addClass("alert alert-danger").attr({id: "#autori-message"}).text("MA VERAMENTE FAI?"));
                        }
                    }
                });




                $("#verifica-isbn").click(function () {
                    $("#isbn-message").removeClass("alert-danger alert-info alert-success");
                    var isbn = $("#isbn").val().toString();
                    var isbnRegex = /^[0-9]{13}$/;
                    if (!isbn.match(isbnRegex)) {
                        $("#isbn-message").text("Formato isbn non valido. Inserire isbn-13").addClass("alert-danger");
                        return;
                    }

                    $.ajax({
                        url: "verifica-isbn",
                        data: {"isbn": isbn},
                        dataType: 'JSON',
                        type: 'GET',
                        success: function (data) {
                            if (data.message == "empty") {
                                $("#isbn-message").text("Inserire i dati del libro").addClass("alert-info");
                                $(".book-data").prop("disabled", false).val("");
                                $("#autori").show();
                                $("#ignore").val("false");
                                ignore = false;
                            } else {
                                var libro = data.books[0];
                                $("#isbn-message").text("Libro trovato").addClass("alert-success");
                                $(".book-data").prop("disabled", true);
                                $("#titolo").val(libro.titolo);
                                $("#descrizione").val(libro.descrizione);
                                $("#editore").val(libro.editore);
                                $("#path_foto").val(libro.path_foto);
                                $("#data").val(libro.data);
                                $("#ignore").val("true");
                                $("#autori").hide();
                                ignore = true;
                            }

                            $("#subimt").prop("disabled", false);
                        }
                    });
                });


                $("#form-add-libro").submit(function () {

                    $(".is-invalid").removeClass("is-invalid");
                    $(".invalid-feedback").remove();

                    var ok = true;
                    var invalidFeedback = $("<p></p>").addClass("invalid-feedback").text("Campo obbligatorio");
                    var titolo = $("#titolo").val();
                    var descrizione = $("#descrizione").val();
                    var editore = $("#editore").val();
                    var path_foto = $("#path_foto").val();
                    var data = $("#data").val();

                    if (titolo.length == 0) {
                        ok = false;
                        $("#titolo").addClass("is-invalid").after(invalidFeedback.clone());
                    } else {
                        $("#titolo").addClass("is-valid");
                    }

                    if (descrizione.length == 0) {
                        $("#descrizione").addClass("is-invalid").after(invalidFeedback.clone());
                        ok = false;
                    } else {
                        $("#descrizione").addClass("is-valid");
                    }


                    if (editore.length == 0) {
                        $("#editore").addClass("is-invalid").after(invalidFeedback.clone());
                        ok = false;
                    } else {
                        $("#editore").addClass("is-valid");
                    }

                    if (path_foto.length == 0) {
                        $("#path_foto").addClass("is-invalid").after(invalidFeedback.clone());
                        ok = false;
                    } else {
                        $("#path_foto").addClass("is-valid");
                    }

                    if (!data.toString().match(/^(([1-9]|0[1-9]|[12]\d|3[01])-([1-9]|0[1-9]|1[0-2])-[12]\d{3})$/)) {
                        $("#data").addClass("is-invalid").after(invalidFeedback.clone().text("Formato data errato"));
                        ok = false;
                    } else {
                        $("#data").addClass("is-valid");
                    }

                    if (!ignore) {
                        var autori = $(".author-data").toArray();

                        for (var i = 0; i < autori.length; i++) {
                            if (!autori[i].value.toString().match(/^[a-zA-Z][a-zA-Z ]{2,}$/g)) {
                                $(autori[i]).addClass("is-invalid").after(invalidFeedback.clone());
                                ok = false;
                            } else {
                                $(autori[i]).addClass("is-valid");
                            }
                        }
                    }
                    
                    if(!$("#n-autori").val().toString().match(/[0-9]/) || $("#n-autori").val() == ""){
                       $("#n-autori").addClass("is-invalid").after(invalidFeedback.clone()); 
                    }

                    if (ok) {
                        $(".book-data").prop("disabled", false);
                    }

                    return ok;

                });
            });
        </script>
    </body>
</html>
